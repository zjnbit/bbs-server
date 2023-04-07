package com.zjnbit.bbs.api.api.base;

import cn.dev33.satoken.stp.StpUtil;
import com.zjnbit.bbs.api.model.param.BaseOauthSigninParam;
import com.zjnbit.bbs.api.model.param.BaseOauthSignupCodeParam;
import com.zjnbit.bbs.api.model.param.BaseOauthSignupParam;
import com.zjnbit.bbs.api.model.vo.BaseOauthProfileVo;
import com.zjnbit.bbs.api.model.vo.BaseOauthSigninVo;
import com.zjnbit.bbs.api.router.base.BaseOauthRouter;
import com.zjnbit.bbs.api.service.BaseOauthService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OauthApi extends BaseApi<BaseOauthService> {

    /**
     * 发送验证码
     *
     * @param param
     * @return
     */
    @PostMapping(value = BaseOauthRouter.SIGNUP_CODE)
    public Result<Boolean> signupCode(@RequestBody BaseOauthSignupCodeParam param) {
        return success(baseService.signupCode(param));
    }

    /**
     * 注册
     *
     * @param param
     * @return
     */
    @PostMapping(value = BaseOauthRouter.SIGNUP)
    public Result<BaseOauthSigninVo> signup(@RequestBody @Validated BaseOauthSignupParam param) {
        return success(baseService.signup(param));
    }

    /**
     * 登录
     *
     * @param param
     * @return
     */
    @PostMapping(value = BaseOauthRouter.SIGNIN)
    public Result<BaseOauthSigninVo> signin(@RequestBody @Validated BaseOauthSigninParam param) {
        return success(baseService.signin(param));
    }

    /**
     * 登出
     *
     * @return
     */
    @GetMapping(value = BaseOauthRouter.SIGNOUT)
    public Result<Boolean> signout() {
        StpUtil.logout();
        return success();
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping(value = BaseOauthRouter.PROFILE)
    public Result<BaseOauthProfileVo> profile() {
        return success(baseService.profile());
    }
}
