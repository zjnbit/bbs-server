package com.zjnbit.bbs.api.api.base;

import com.zjnbit.bbs.api.model.vo.BaseOssUploadPolicyVo;
import com.zjnbit.bbs.api.router.base.BaseOssRouter;
import com.zjnbit.bbs.api.service.BaseOssService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OssApi extends BaseApi<BaseOssService> {

    /**
     * oss获取上传凭据
     *
     * @author 非羽Army
     **/
    @GetMapping(value = BaseOssRouter.UPLOAD_POLICY)
    public Result<BaseOssUploadPolicyVo> basicUploadPolicy() {
        return success(baseService.getAliyunOssUploadPolicy());
    }

}
