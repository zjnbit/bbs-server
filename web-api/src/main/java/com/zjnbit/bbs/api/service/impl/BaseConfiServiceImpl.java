package com.zjnbit.bbs.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.framework.constant.SysConfAliyunConst;
import com.zjnbit.bbs.api.framework.constant.SysConfAuthConst;
import com.zjnbit.bbs.api.framework.constant.SysConfBbsConst;
import com.zjnbit.bbs.api.mapper.BbsSysConfMapper;
import com.zjnbit.bbs.api.model.conf.*;
import com.zjnbit.bbs.api.model.entity.BbsSysConfEntity;
import com.zjnbit.bbs.api.service.BaseConfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseConfiServiceImpl implements BaseConfService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    BbsSysConfMapper bbsSysConfMapper;

    @Override
    public AliyunConf initAliyunConf() {
        AliyunConf confDto = new AliyunConf();
        AliyunSecurityConf securityConfDto = new AliyunSecurityConf();
        AliyunOssConf ossConfDto = new AliyunOssConf();
        List<BbsSysConfEntity> confEntityList = bbsSysConfMapper.selectList(
                new LambdaQueryWrapper<BbsSysConfEntity>()
                        .likeRight(BbsSysConfEntity::getConfKey, SysConfAliyunConst.CONF_KEY_PREFIX)
        );
        for (BbsSysConfEntity bbsSysConfEntity : confEntityList) {
            switch (bbsSysConfEntity.getConfKey()) {
                case SysConfAliyunConst.CONF_KEY_SECURITY_ACCESS_KEY:
                    securityConfDto.setAccessKey(bbsSysConfEntity.getConfValue());
                    break;
                case SysConfAliyunConst.CONF_KEY_SECURITY_ACCESS_KEY_SECRET:
                    securityConfDto.setAccessKeySecret(bbsSysConfEntity.getConfValue());
                    break;
                case SysConfAliyunConst.CONF_KEY_OSS_BUCKET:
                    ossConfDto.setBucket(bbsSysConfEntity.getConfValue());
                    break;
                case SysConfAliyunConst.CONF_KEY_OSS_ENDPOINT:
                    ossConfDto.setEndpoint(bbsSysConfEntity.getConfValue());
                    break;
                case SysConfAliyunConst.CONF_KEY_OSS_BUCKET_URL:
                    ossConfDto.setBucketUrl(bbsSysConfEntity.getConfValue());
                    break;
                case SysConfAliyunConst.CONF_KEY_OSS_CDN_URL:
                    ossConfDto.setCdnUrl(bbsSysConfEntity.getConfValue());
                    break;
                case SysConfAliyunConst.CONF_KEY_OSS_CALLBACK_URL:
                    ossConfDto.setCallbackUrl(bbsSysConfEntity.getConfValue());
                    break;
            }
        }
        confDto.setSecurity(securityConfDto);
        confDto.setOss(ossConfDto);
        redisTemplate.opsForHash().put(SysConfAliyunConst.CACHE_KEY_BASIC, SysConfAliyunConst.CACHE_KEY_SECURITY, securityConfDto);
        redisTemplate.opsForHash().put(SysConfAliyunConst.CACHE_KEY_BASIC, SysConfAliyunConst.CACHE_KEY_OSS, ossConfDto);
        return confDto;
    }

    @Override
    public AliyunConf getAliyunConf() {
        Boolean securityFlag = redisTemplate.opsForHash().hasKey(SysConfAliyunConst.CACHE_KEY_BASIC, SysConfAliyunConst.CACHE_KEY_SECURITY);
        if (!securityFlag) {
            return initAliyunConf();
        }
        Boolean ossFlag = redisTemplate.opsForHash().hasKey(SysConfAliyunConst.CACHE_KEY_BASIC, SysConfAliyunConst.CACHE_KEY_OSS);
        if (!ossFlag) {
            return initAliyunConf();
        }
        AliyunSecurityConf security = (AliyunSecurityConf) redisTemplate.opsForHash().get(SysConfAliyunConst.CACHE_KEY_BASIC, SysConfAliyunConst.CACHE_KEY_SECURITY);
        AliyunOssConf oss = (AliyunOssConf) redisTemplate.opsForHash().get(SysConfAliyunConst.CACHE_KEY_BASIC, SysConfAliyunConst.CACHE_KEY_OSS);
        AliyunConf confDto = new AliyunConf();
        confDto.setSecurity(security);
        confDto.setOss(oss);
        return confDto;
    }

    @Override
    public AuthConf initAuthConf() {
        AuthConf confDto = new AuthConf();
        AuthCasConf casConfDto = new AuthCasConf();
        AuthSecretConf secretConfDto = new AuthSecretConf();
        List<BbsSysConfEntity> confEntityList = bbsSysConfMapper.selectList(
                new LambdaQueryWrapper<BbsSysConfEntity>()
                        .likeRight(BbsSysConfEntity::getConfKey, SysConfAuthConst.CONF_KEY_PREFIX)
        );
        for (BbsSysConfEntity bbsSysConfEntity : confEntityList) {
            switch (bbsSysConfEntity.getConfKey()) {
                case SysConfAuthConst.CONF_KEY_SECRET_AES_KEY:
                    secretConfDto.setAesKey(bbsSysConfEntity.getConfValue());
                case SysConfAuthConst.CONF_KEY_CAS_CLIENT_ID:
                    casConfDto.setClientId(bbsSysConfEntity.getConfValue());
                    break;
                case SysConfAuthConst.CONF_KEY_CAS_CLIENT_SECRET:
                    casConfDto.setClientSecret(bbsSysConfEntity.getConfValue());
                    break;
                case SysConfAuthConst.CONF_KEY_CAS_SERVER_URL:
                    casConfDto.setServerUrl(bbsSysConfEntity.getConfValue());
                    break;
            }
        }
        confDto.setSecret(secretConfDto);
        confDto.setCas(casConfDto);
        redisTemplate.opsForHash().put(SysConfAuthConst.CACHE_KEY_BASIC, SysConfAuthConst.CACHE_KEY_SECRET, secretConfDto);
        redisTemplate.opsForHash().put(SysConfAuthConst.CACHE_KEY_BASIC, SysConfAuthConst.CACHE_KEY_CAS, casConfDto);
        return confDto;
    }

    @Override
    public AuthConf getAuthConf() {
        Boolean secretFlag = redisTemplate.opsForHash().hasKey(SysConfAuthConst.CACHE_KEY_BASIC, SysConfAuthConst.CACHE_KEY_SECRET);
        if (!secretFlag) {
            return initAuthConf();
        }
        Boolean casFlag = redisTemplate.opsForHash().hasKey(SysConfAuthConst.CACHE_KEY_BASIC, SysConfAuthConst.CACHE_KEY_CAS);
        if (!casFlag) {
            return initAuthConf();
        }
        AuthSecretConf secret = (AuthSecretConf) redisTemplate.opsForHash().get(SysConfAuthConst.CACHE_KEY_BASIC, SysConfAuthConst.CACHE_KEY_SECRET);
        AuthCasConf cas = (AuthCasConf) redisTemplate.opsForHash().get(SysConfAuthConst.CACHE_KEY_BASIC, SysConfAuthConst.CACHE_KEY_CAS);
        AuthConf confDto = new AuthConf();
        confDto.setSecret(secret);
        confDto.setCas(cas);
        return confDto;
    }

    @Override
    public BbsConf initBbsConf() {
        BbsConf confDto = new BbsConf();
        BbsTopicConf topicConfDto = new BbsTopicConf();
        List<BbsSysConfEntity> confEntityList = bbsSysConfMapper.selectList(
                new LambdaQueryWrapper<BbsSysConfEntity>()
                        .likeRight(BbsSysConfEntity::getConfKey, SysConfBbsConst.CONF_KEY_PREFIX)
        );
        for (BbsSysConfEntity bbsSysConfEntity : confEntityList) {
            switch (bbsSysConfEntity.getConfKey()) {
                case SysConfBbsConst.CONF_KEY_CAS_CLIENT_ID:
                    topicConfDto.setPostStatusDefault(bbsSysConfEntity.getConfValue());
                    break;
            }
        }
        confDto.setTopic(topicConfDto);
        redisTemplate.opsForHash().put(SysConfBbsConst.CACHE_KEY_BASIC, SysConfBbsConst.CACHE_KEY_TOPIC, topicConfDto);
        return confDto;
    }

    @Override
    public BbsConf getBbsConf() {
        Boolean topicFlag = redisTemplate.opsForHash().hasKey(SysConfBbsConst.CACHE_KEY_BASIC, SysConfBbsConst.CACHE_KEY_TOPIC);
        if (!topicFlag) {
            return initBbsConf();
        }
        BbsTopicConf topic = (BbsTopicConf) redisTemplate.opsForHash().get(SysConfBbsConst.CACHE_KEY_BASIC, SysConfBbsConst.CACHE_KEY_TOPIC);
        BbsConf confDto = new BbsConf();
        confDto.setTopic(topic);
        return confDto;
    }
}
