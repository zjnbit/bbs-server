package com.zjnbit.bbs.api.api.base;

import com.zjnbit.bbs.api.router.base.BaseVerifyRouter;
import com.zjnbit.bbs.api.service.BaseVerifyService;
import com.zjnbit.framework.web.api.BaseApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerifyApi extends BaseApi<BaseVerifyService> {

    @GetMapping(value = BaseVerifyRouter.CAPTCHA)
    public void captcha() {
        baseService.captcha();
    }


}
