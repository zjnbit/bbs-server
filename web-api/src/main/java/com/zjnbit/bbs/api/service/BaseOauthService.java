package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.param.BaseOauthSigninParam;
import com.zjnbit.bbs.api.model.param.BaseOauthSignupCodeParam;
import com.zjnbit.bbs.api.model.param.BaseOauthSignupParam;
import com.zjnbit.bbs.api.model.vo.BaseOauthProfileVo;
import com.zjnbit.bbs.api.model.vo.BaseOauthSigninVo;

public interface BaseOauthService {

    /**
     * 获取验证码
     *
     * @param data
     * @return
     */
    Boolean signupCode(BaseOauthSignupCodeParam data);

    /**
     * 注册
     *
     * @param data
     * @return
     */
    BaseOauthSigninVo signup(BaseOauthSignupParam data);

    /**
     * 登录
     *
     * @param data
     * @return
     */
    BaseOauthSigninVo signin(BaseOauthSigninParam data);

    /**
     * 获取用户信息
     *
     * @return
     */
    BaseOauthProfileVo profile();

}
