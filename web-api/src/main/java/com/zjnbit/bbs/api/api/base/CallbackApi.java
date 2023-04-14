package com.zjnbit.bbs.api.api.base;

import com.zjnbit.bbs.api.model.dto.BaseAliyunOssCallbackDto;
import com.zjnbit.bbs.api.model.vo.BbsAttachVo;
import com.zjnbit.bbs.api.router.base.BaseCallbackRouter;
import com.zjnbit.bbs.api.service.BaseCallbackService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class CallbackApi extends BaseApi<BaseCallbackService> {

    /**
     * 阿里云oss上传回调
     *
     * @return
     */
    @RequestMapping(value = BaseCallbackRouter.OSS_ALIYUN)
    public Result<BbsAttachVo> ossAliyun(@RequestBody BaseAliyunOssCallbackDto param, HttpServletResponse response) {
        return success(baseService.ossAliyun(param, response));
    }

}
