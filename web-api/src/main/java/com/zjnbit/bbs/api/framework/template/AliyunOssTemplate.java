package com.zjnbit.bbs.api.framework.template;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.zjnbit.bbs.api.framework.prop.AliyunOssProperties;
import com.zjnbit.bbs.api.framework.prop.AliyunSecurityProperties;
import com.zjnbit.bbs.api.model.conf.AliyunConf;
import com.zjnbit.bbs.api.model.dto.BaseAliyunOssDto;
import com.zjnbit.bbs.api.model.vo.BaseOssUploadPolicyVo;
import com.zjnbit.framework.web.constant.StringPool;
import lombok.SneakyThrows;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URI;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

public class AliyunOssTemplate {

    private final OSSClient client;
    private final AliyunSecurityProperties securityProperties;
    private final AliyunOssProperties ossProperties;

    public AliyunOssTemplate(OSSClient ossClient, AliyunSecurityProperties aliyunSecurityProperties,AliyunOssProperties aliyunOssProperties) {
        this.client = ossClient;
        this.securityProperties = aliyunSecurityProperties;
        this.ossProperties = aliyunOssProperties;
    }

    /**
     * 验证RSA
     *
     * @param content
     * @param sign
     * @param publicKey
     * @return
     */
    private static boolean doCheck(String content, byte[] sign, String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = BinaryUtil.fromBase64String(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            java.security.Signature signature = java.security.Signature.getInstance("MD5withRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            boolean bverify = signature.verify(sign);
            return bverify;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取上传签名
     *
     * @return
     */
    public BaseOssUploadPolicyVo getUploadPolicy() {
        try {
            BaseOssUploadPolicyVo vo = new BaseOssUploadPolicyVo();
            long expireTime = 300;
            long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 10L * 1024 * 1024);
            String dir = genUploadDir();
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);
            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            vo.setAccessid(securityProperties.getAccessKeyId());
            vo.setPolicy(encodedPolicy);
            vo.setSignature(postSignature);
            vo.setDir(dir);
            vo.setHost(ossProperties.getBucket()+ StringPool.DOT+ossProperties.getEndpoint());
            vo.setExpire(String.valueOf(expireEndTime / 1000));
            JSONObject jasonCallback = new JSONObject();
            jasonCallback.put("callbackUrl", ossProperties.getCallbackUrl());
            jasonCallback.put("callbackBody",
                    "filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
            jasonCallback.put("callbackBodyType", "application/x-www-form-urlencoded");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            vo.setCallback(base64CallbackBody);
            return vo;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @SneakyThrows
    public BaseAliyunOssDto callback(HttpServletRequest request) {
        BaseAliyunOssDto dto = null;
        String ossCallbackBody = getPostBody(request.getInputStream(), Integer.parseInt(request.getHeader("content-length")));
        boolean ret = verifyOSSCallbackRequest(request, ossCallbackBody);
        if (ret) {
            dto = new BaseAliyunOssDto();
            dto.setAttachPath(request.getParameter("filename"));
            dto.setOssUrl("https://" + ossProperties.getBucket()+ StringPool.DOT+ossProperties.getEndpoint() + "/" + request.getParameter("filename"));
            dto.setCdnUrl("https://" + ossProperties.getCdnUrl() + "/" + request.getParameter("filename"));
            dto.setMimeType(request.getParameter("mimeType"));
            return dto;
        }
        return dto;
    }

    /**
     * 获取Post消息体
     *
     * @param is
     * @param contentLen
     * @return
     */
    private String getPostBody(InputStream is, int contentLen) {
        if (contentLen > 0) {
            int readLen = 0;
            int readLengthThisTime = 0;
            byte[] message = new byte[contentLen];
            try {
                while (readLen != contentLen) {
                    readLengthThisTime = is.read(message, readLen, contentLen - readLen);
                    if (readLengthThisTime == -1) {// Should not happen.
                        break;
                    }
                    readLen += readLengthThisTime;
                }
                return new String(message);
            } catch (IOException e) {
            }
        }
        return "";
    }

    /**
     * 验证上传回调的Request
     *
     * @param request
     * @param ossCallbackBody
     * @return
     */
    @SneakyThrows
    private boolean verifyOSSCallbackRequest(HttpServletRequest request, String ossCallbackBody) {
        boolean ret = false;
        String autorizationInput = new String(request.getHeader("Authorization"));
        String pubKeyInput = request.getHeader("x-oss-pub-key-url");
        byte[] authorization = BinaryUtil.fromBase64String(autorizationInput);
        byte[] pubKey = BinaryUtil.fromBase64String(pubKeyInput);
        String pubKeyAddr = new String(pubKey);
        if (!pubKeyAddr.startsWith("http://gosspublic.alicdn.com/")
                && !pubKeyAddr.startsWith("https://gosspublic.alicdn.com/")) {
            System.out.println("pub key addr must be oss addrss");
            return false;
        }
        String retString = executeGet(pubKeyAddr);
        retString = retString.replace("-----BEGIN PUBLIC KEY-----", "");
        retString = retString.replace("-----END PUBLIC KEY-----", "");
        String queryString = request.getQueryString();
        String uri = request.getRequestURI();
        String decodeUri = java.net.URLDecoder.decode(uri, "UTF-8");
        String authStr = decodeUri;
        if (queryString != null && !queryString.equals("")) {
            authStr += "?" + queryString;
        }
        authStr += "\n" + ossCallbackBody;
        ret = doCheck(authStr, authorization, retString);
        return ret;
    }

    /**
     * 获取上传路径及文件名
     *
     * @author chenjy
     **/
    private String genUploadDir() {
        StringBuilder key = new StringBuilder();
        key.append("bbs/");
        key.append(DateUtil.format(new Date(), "yyMMdd"));
        key.append("/");
        return key.toString();
    }

    /**
     * 获取public key
     *
     * @param url
     * @return
     */
    @SuppressWarnings({"finally"})
    private String executeGet(String url) {
        BufferedReader in = null;
        String content = null;
        try {
            // 定义HttpClient。
            @SuppressWarnings("resource")
            DefaultHttpClient client = new DefaultHttpClient();
            // 实例化HTTP方法。
            HttpGet request = new HttpGet();
            request.setURI(new URI(url));
            HttpResponse response = client.execute(request);

            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
            in.close();
            content = sb.toString();
        } catch (Exception e) {
        } finally {
            if (in != null) {
                try {
                    in.close();// 关闭BufferedReader。
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return content;
        }
    }

}
