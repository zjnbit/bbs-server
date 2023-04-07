package com.zjnbit.bbs.api.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.RandomGenerator;
import com.zjnbit.bbs.api.service.BaseVerifyService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

@Service
public class BaseVerifyServiceImpl implements BaseVerifyService {

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    @SneakyThrows
    public void captcha() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) requestAttributes).getResponse();
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(300, 100);
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        captcha.setGenerator(randomGenerator);
//        redisTemplate.opsForValue().set(CacheKeyConst.VERIFY_CAPTCHA_CODE+ captcha.getCode(), captcha.getCode(), 2, TimeUnit.MINUTES);
        response.setContentType("image/png");
        OutputStream outputStream = response.getOutputStream();
        StpUtil.getAnonTokenSession().set("captcha", captcha.getCode());
        try {
            captcha.write(outputStream);
        } finally {
            if (null != outputStream) {
                outputStream.close();
            }
        }
    }
}
