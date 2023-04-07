package com.zjnbit.bbs.api.api.base;

import com.zjnbit.bbs.api.model.vo.BbsAttachVo;
import com.zjnbit.bbs.api.router.base.BaseCallbackRouter;
import com.zjnbit.bbs.api.service.BaseCallbackService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CallbackApi extends BaseApi<BaseCallbackService> {

    /**
     * 阿里云oss上传回调
     *
     * @return
     */
    @PostMapping(value = BaseCallbackRouter.OSS_ALIYUN)
    public Result<BbsAttachVo> ossAliyun() {
        return success(baseService.ossAliyun());
    }

}
