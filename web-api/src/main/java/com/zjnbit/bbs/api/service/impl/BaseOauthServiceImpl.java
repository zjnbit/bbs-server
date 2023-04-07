package com.zjnbit.bbs.api.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.framework.constant.CacheKeyConst;
import com.zjnbit.bbs.api.mapper.UserBaseMapper;
import com.zjnbit.bbs.api.model.constant.BaseOauthSigninTypeEnum;
import com.zjnbit.bbs.api.model.entity.UserBaseEntity;
import com.zjnbit.bbs.api.model.param.BaseOauthSigninParam;
import com.zjnbit.bbs.api.model.param.BaseOauthSignupCodeParam;
import com.zjnbit.bbs.api.model.param.BaseOauthSignupParam;
import com.zjnbit.bbs.api.model.vo.BaseOauthProfileVo;
import com.zjnbit.bbs.api.model.vo.BaseOauthSigninVo;
import com.zjnbit.bbs.api.service.BaseOauthService;
import com.zjnbit.framework.web.exception.RequestError;
import com.zjnbit.framework.web.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class BaseOauthServiceImpl implements BaseOauthService {

    @Autowired
    UserBaseMapper userBaseMapper;
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public Boolean signupCode(BaseOauthSignupCodeParam data) {
        String captchaCode = (String) StpUtil.getAnonTokenSession().get("captcha");
        if (!data.getCode().equalsIgnoreCase(captchaCode)) {
            throw new RequestException(RequestError.A0106);
        }
        if ("email".equals(data.getType()) && Validator.isEmail(data.getTarget())) {
            if (redisTemplate.hasKey(CacheKeyConst.OAUTH_SIGNUP_EMAIL_TIMER + data.getTarget())) {
                throw new RequestException(RequestError.A0107);
            }
            long emailCount = userBaseMapper.selectCount(new LambdaQueryWrapper<UserBaseEntity>().eq(UserBaseEntity::getEmail, data.getTarget()));
            if (emailCount > 0) {
                throw new RequestException(RequestError.A0102);
            }
            String code = RandomUtil.randomNumbers(6);
            redisTemplate.opsForValue().set(CacheKeyConst.OAUTH_SIGNUP_EMAIL_CODE + data.getTarget(), code, 30, TimeUnit.MINUTES);
            redisTemplate.opsForValue().set(CacheKeyConst.OAUTH_SIGNUP_EMAIL_TIMER + data.getTarget(), null, 1, TimeUnit.MINUTES);
            MailUtil.send(data.getTarget(), "注册验证码", "您的验证码为：" + code, false);
        } else if ("phone".equals(data.getType())) {

        } else {
            throw new RequestException(RequestError.A0003);
        }
        return true;
    }

    @Override
    public BaseOauthSigninVo signup(BaseOauthSignupParam data) {
        //验证用户名是否存在
        long usernameCount = userBaseMapper.selectCount(new LambdaQueryWrapper<UserBaseEntity>().eq(UserBaseEntity::getUsername, data.getUsername()));
        if (usernameCount > 0) {
            throw new RequestException(RequestError.A0105);
        }
        long emailCount = userBaseMapper.selectCount(new LambdaQueryWrapper<UserBaseEntity>().eq(UserBaseEntity::getEmail, data.getEmail()));
        if (emailCount > 0) {
            throw new RequestException(RequestError.A0102);
        }
        //验证验证码
        if (redisTemplate.hasKey(CacheKeyConst.OAUTH_SIGNUP_EMAIL_CODE + data.getEmail())) {
            String code = (String) redisTemplate.opsForValue().get(CacheKeyConst.OAUTH_SIGNUP_EMAIL_CODE + data.getEmail());
            if (!code.equals(data.getCode())) {
                throw new RequestException(RequestError.A0106);
            }
        } else {
            throw new RequestException(RequestError.A0106);
        }
        //校验完毕，创建账号
        UserBaseEntity userBaseEntity = new UserBaseEntity();
        userBaseEntity.setEmail(data.getEmail());
        userBaseEntity.setUsername(data.getUsername());
        userBaseEntity.setPassword(BCrypt.hashpw(data.getPassword().toLowerCase(), BCrypt.gensalt()));
        userBaseEntity.setNickname(data.getNickname());
        userBaseEntity.setAvatar("https://oss.zjnbit.com/static/default_avatar.jpeg");
        userBaseMapper.insert(userBaseEntity);
        BaseOauthSigninVo vo = new BaseOauthSigninVo();
        vo.setUserId(userBaseEntity.getId());
        vo.setNickname(userBaseEntity.getNickname());
        StpUtil.login(userBaseEntity.getId());
        vo.setTokenKey(StpUtil.getTokenName());
        vo.setToken(StpUtil.getTokenValue());
        redisTemplate.delete(CacheKeyConst.OAUTH_SIGNUP_EMAIL_CODE + data.getEmail());
        return vo;
    }

    @Override
    public BaseOauthSigninVo signin(BaseOauthSigninParam data) {
        if (!data.getCode().equalsIgnoreCase((String) StpUtil.getAnonTokenSession().get("captcha"))) {
            throw new RequestException(RequestError.A0106);
        }
        UserBaseEntity userBaseEntity = null;
        if (BaseOauthSigninTypeEnum.EMAIL.getCode().equals(data.getSigninType())) {
            userBaseEntity = userBaseMapper.selectOne(
                    new LambdaQueryWrapper<UserBaseEntity>()
                            .eq(UserBaseEntity::getEmail, data.getAccount())
            );
        } else if (BaseOauthSigninTypeEnum.USERNAME.getCode().equals(data.getSigninType())) {
            userBaseEntity = userBaseMapper.selectOne(
                    new LambdaQueryWrapper<UserBaseEntity>()
                            .eq(UserBaseEntity::getUsername, data.getAccount())
            );
        }
        if (null == userBaseEntity) {
            throw new RequestException(RequestError.A0202);
        }
        if (!BCrypt.checkpw(data.getPassword().toLowerCase(), userBaseEntity.getPassword())) {
            throw new RequestException(RequestError.A0203);
        }
        BaseOauthSigninVo vo = new BaseOauthSigninVo();
        vo.setUserId(userBaseEntity.getId());
        vo.setNickname(userBaseEntity.getNickname());
        StpUtil.login(userBaseEntity.getId());
        vo.setTokenKey(StpUtil.getTokenName());
        vo.setToken(StpUtil.getTokenValue());
        return vo;
    }

    @Override
    public BaseOauthProfileVo profile() {
        String userId = (String) StpUtil.getLoginId();
        UserBaseEntity userBaseEntity = userBaseMapper.selectById(userId);
        BaseOauthProfileVo profile = new BaseOauthProfileVo();
        BeanUtil.copyProperties(userBaseEntity, profile);
        return profile;
    }
}
