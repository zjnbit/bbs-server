package com.zjnbit.bbs.api.service.impl;

import com.zjnbit.bbs.api.framework.template.AliyunOssTemplate;
import com.zjnbit.bbs.api.model.vo.BaseOssUploadPolicyVo;
import com.zjnbit.bbs.api.service.BaseConfService;
import com.zjnbit.bbs.api.service.BaseOssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class BaseOssServiceImpl implements BaseOssService {

    @Autowired
    AliyunOssTemplate ossTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    BaseConfService baseConfService;


    @Override
    public BaseOssUploadPolicyVo getAliyunOssUploadPolicy() {
        return ossTemplate.getUploadPolicy();
    }
}
