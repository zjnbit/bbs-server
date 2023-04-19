package com.zjnbit.bbs.api.api.base;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import com.zjnbit.bbs.api.framework.template.MinioTemplate;
import com.zjnbit.bbs.api.model.vo.BaseOssUploadPolicyVo;
import com.zjnbit.bbs.api.router.base.BaseOssRouter;
import com.zjnbit.bbs.api.service.BaseOssService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.constant.StringPool;
import com.zjnbit.framework.web.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class OssApi extends BaseApi<BaseOssService> {
    @Autowired
    MinioTemplate minioTemplate;

    /**
     * oss获取上传凭据
     *
     * @author 非羽Army
     **/
    @GetMapping(value = BaseOssRouter.UPLOAD_POLICY)
    public Result<BaseOssUploadPolicyVo> basicUploadPolicy() {
        return success(baseService.getAliyunOssUploadPolicy());
    }


    @PutMapping(value = BaseOssRouter.UPLOAD)
    public Result<Boolean> upload(MultipartFile file) {
        String filePath = DateUtil.format(DateUtil.date(), "yyyyMMdd") + "/" + IdUtil.fastSimpleUUID() + StringPool.DOT + FileUtil.extName(file.getOriginalFilename());
        Boolean b = minioTemplate.upload(filePath, file);
        return success(b);
    }
}
