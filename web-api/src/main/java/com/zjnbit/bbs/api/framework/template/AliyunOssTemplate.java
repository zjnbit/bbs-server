package com.zjnbit.bbs.api.framework.template;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.zjnbit.bbs.api.framework.prop.AliyunOssProperties;
import com.zjnbit.bbs.api.framework.prop.AliyunSecurityProperties;
import com.zjnbit.bbs.api.model.dto.BaseAliyunOssCallbackDto;
import com.zjnbit.bbs.api.model.dto.BaseAliyunOssDto;
import com.zjnbit.bbs.api.model.vo.BaseOssUploadPolicyVo;
import com.zjnbit.framework.web.constant.StringPool;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Slf4j
public class AliyunOssTemplate {

    private final OSSClient client;
    private final AliyunSecurityProperties securityProperties;
    private final AliyunOssProperties ossProperties;

    public AliyunOssTemplate(OSSClient ossClient, AliyunSecurityProperties aliyunSecurityProperties, AliyunOssProperties aliyunOssProperties) {
        this.client = ossClient;
        this.securityProperties = aliyunSecurityProperties;
        this.ossProperties = aliyunOssProperties;
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
            policyConds.addConditionItem(MatchMode.Exact, PolicyConditions.COND_CONTENT_TYPE, "image/jpg");
            policyConds.addConditionItem(MatchMode.Exact, PolicyConditions.COND_CONTENT_TYPE, "image/jpeg");
            policyConds.addConditionItem(MatchMode.Exact, PolicyConditions.COND_CONTENT_TYPE, "image/png");
            policyConds.addConditionItem(MatchMode.Exact, PolicyConditions.COND_CONTENT_TYPE, "image/gif");
            policyConds.addConditionItem(MatchMode.Exact, PolicyConditions.COND_CONTENT_TYPE, "application/x-bmpg");
            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            vo.setAccessid(securityProperties.getAccessKeyId());
            vo.setPolicy(encodedPolicy);
            vo.setSignature(postSignature);
            vo.setDir(dir);
            vo.setHost("https://" + ossProperties.getBucket() + StringPool.DOT + ossProperties.getEndpoint());
            vo.setExpire(String.valueOf(expireEndTime / 1000));
            JSONObject jasonCallback = new JSONObject();
            jasonCallback.put("callbackUrl", ossProperties.getCallbackUrl());
            jasonCallback.put("callbackBody", "{\"filename\":${object},\"mimeType\":${mimeType},\"size\":${size}}");
            jasonCallback.put("callbackBodyType", "application/json");
            String base64CallbackBody = BinaryUtil.toBase64String(jasonCallback.toString().getBytes());
            vo.setCallback(base64CallbackBody);
            return vo;
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    @SneakyThrows
    public BaseAliyunOssDto callback(BaseAliyunOssCallbackDto data) {
        log.info("callback:" + JSONUtil.toJsonStr(data));
        BaseAliyunOssDto dto = new BaseAliyunOssDto();
        dto.setAttachPath(data.getFilename());
        dto.setOssUrl("https://" + ossProperties.getBucket() + StringPool.DOT + ossProperties.getEndpoint() + "/" + data.getFilename());
        dto.setCdnUrl(ossProperties.getCdnUrl() + "/" + data.getFilename());
        dto.setMimeType(data.getMimeType());
        dto.setSize(data.getSize());
        return dto;
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

}
