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
