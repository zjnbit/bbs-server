package com.zjnbit.bbs.api.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnbit.bbs.api.framework.constant.CacheKeyConst;
import com.zjnbit.bbs.api.mapper.BbsUserFollowMapper;
import com.zjnbit.bbs.api.mapper.UserBaseMapper;
import com.zjnbit.bbs.api.model.dto.BbsUserOperateDto;
import com.zjnbit.bbs.api.model.entity.BbsUserFollowEntity;
import com.zjnbit.bbs.api.model.entity.UserBaseEntity;
import com.zjnbit.bbs.api.model.param.BbsUserFollowUserParam;
import com.zjnbit.bbs.api.model.vo.BbsUserInfoVo;
import com.zjnbit.bbs.api.service.BbsUserService;
import com.zjnbit.framework.tools.util.IpUtils;
import com.zjnbit.framework.web.exception.RequestError;
import com.zjnbit.framework.web.exception.RequestException;
import com.zjnbit.framework.web.model.BasePageParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BbsUserServiceImpl implements BbsUserService {
    @Autowired
    UserBaseMapper userBaseMapper;
    @Autowired
    BbsUserFollowMapper bbsUserFollowMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public BbsUserInfoVo getUserInfo(Long userId) {
        BbsUserInfoVo bbsUserInfoVo = new BbsUserInfoVo();
        if (redisTemplate.hasKey(CacheKeyConst.BBS_USER_INFO + userId)) {
            bbsUserInfoVo = (BbsUserInfoVo) redisTemplate.opsForValue().get(CacheKeyConst.BBS_USER_INFO + userId);
        }
        UserBaseEntity userBaseEntity = userBaseMapper.selectById(userId);
        if (null == userBaseEntity) {
            throw new RequestException(RequestError.A0002);
        }
        BeanUtil.copyProperties(userBaseEntity, bbsUserInfoVo);
        redisTemplate.opsForValue().set(CacheKeyConst.BBS_USER_INFO + userId, bbsUserInfoVo);
        return bbsUserInfoVo;
    }

    @Override
    public void followUser(BbsUserFollowUserParam data) {
        Long userId = (Long) StpUtil.getLoginId();
        UserBaseEntity followUser = userBaseMapper.selectById(data.getFollowUserId());
        if (null == followUser) {
            throw new RequestException(RequestError.A0002);
        }
        List<BbsUserFollowEntity> followEntityList = bbsUserFollowMapper.selectList(
                new LambdaQueryWrapper<BbsUserFollowEntity>()
                        .eq(BbsUserFollowEntity::getUserId, userId)
                        .eq(BbsUserFollowEntity::getFollowUserId, data.getFollowUserId())
        );
        if (CollUtil.isNotEmpty(followEntityList)) {
            bbsUserFollowMapper.update(null
                    , new LambdaUpdateWrapper<BbsUserFollowEntity>()
                            .eq(BbsUserFollowEntity::getUserId, userId)
                            .eq(BbsUserFollowEntity::getFollowUserId, data.getFollowUserId())
                            .set(BbsUserFollowEntity::getIsFollow, data.getIsFollow())
                            .set(BbsUserFollowEntity::getFollowTime, LocalDateTime.now())
            );
        } else {
            BbsUserFollowEntity bbsUserFollowEntity = new BbsUserFollowEntity();
            bbsUserFollowEntity.setUserId(userId);
            bbsUserFollowEntity.setFollowUserId(data.getFollowUserId());
            bbsUserFollowEntity.setIsFollow(data.getIsFollow());
            bbsUserFollowEntity.setFollowTime(LocalDateTime.now());
            bbsUserFollowMapper.insert(bbsUserFollowEntity);
        }
    }

    @Override
    public Page<BbsUserInfoVo> listFollowUser(BasePageParam data) {
        Page<BbsUserFollowEntity> followEntityPage = new Page<>(data.getCurrent(), data.getSize());
        followEntityPage = bbsUserFollowMapper.selectPage(
                followEntityPage
                , new LambdaQueryWrapper<BbsUserFollowEntity>()
                        .eq(BbsUserFollowEntity::getUserId, StpUtil.getLoginId())
                        .eq(BbsUserFollowEntity::getIsFollow, true)
                        .orderByDesc(BbsUserFollowEntity::getFollowTime)
        );
        List<BbsUserFollowEntity> followEntityList = followEntityPage.getRecords();
        if (CollUtil.isEmpty(followEntityList)) {
            return new Page<>(data.getCurrent(), data.getSize());
        }
        List<UserBaseEntity> userBaseEntityList = userBaseMapper.selectList(
                new LambdaQueryWrapper<UserBaseEntity>()
                        .in(UserBaseEntity::getId, followEntityList.stream().map(BbsUserFollowEntity::getFollowUserId).collect(Collectors.toList()))
        );
        List<BbsUserInfoVo> list = userBaseEntityList.stream().map(userBaseEntity -> {
                    BbsUserInfoVo bbsUserInfoVo = new BbsUserInfoVo();
                    BeanUtil.copyProperties(userBaseEntity, bbsUserInfoVo);
                    return bbsUserInfoVo;
                }
        ).collect(Collectors.toList());
        Page<BbsUserInfoVo> page = new Page<>(data.getCurrent(), data.getSize());
        page.setRecords(list);
        return page;
    }

    @Override
    public Page<BbsUserInfoVo> userFans(BasePageParam data) {
        Page<BbsUserFollowEntity> followEntityPage = new Page<>(data.getCurrent(), data.getSize());
        followEntityPage = bbsUserFollowMapper.selectPage(
                followEntityPage
                , new LambdaQueryWrapper<BbsUserFollowEntity>()
                        .eq(BbsUserFollowEntity::getFollowUserId, StpUtil.getLoginId())
                        .eq(BbsUserFollowEntity::getIsFollow, true)
                        .orderByDesc(BbsUserFollowEntity::getFollowTime)
        );
        List<BbsUserFollowEntity> followEntityList = followEntityPage.getRecords();
        if (CollUtil.isEmpty(followEntityList)) {
            return new Page<>(data.getCurrent(), data.getSize());
        }
        List<UserBaseEntity> userBaseEntityList = userBaseMapper.selectList(
                new LambdaQueryWrapper<UserBaseEntity>()
                        .in(UserBaseEntity::getId, followEntityList.stream().map(BbsUserFollowEntity::getFollowUserId).collect(Collectors.toList()))
        );
        List<BbsUserInfoVo> list = userBaseEntityList.stream().map(userBaseEntity -> {
                    BbsUserInfoVo bbsUserInfoVo = new BbsUserInfoVo();
                    BeanUtil.copyProperties(userBaseEntity, bbsUserInfoVo);
                    return bbsUserInfoVo;
                }
        ).collect(Collectors.toList());
        Page<BbsUserInfoVo> page = new Page<>(data.getCurrent(), data.getSize());
        page.setRecords(list);
        return page;
    }

    @Override
    public BbsUserOperateDto getUserOperateInfo() {
        Long userId = (Long) StpUtil.getLoginId();
        BbsUserInfoVo bbsUserInfoVo = getUserInfo(userId);
        BbsUserOperateDto bbsUserOperateDto = new BbsUserOperateDto();
        bbsUserOperateDto.setUserId(userId);
        bbsUserOperateDto.setNickname(bbsUserInfoVo.getNickname());
        bbsUserOperateDto.setIp(IpUtils.getIpAddress());
        bbsUserOperateDto.setTime(LocalDateTime.now());
        return bbsUserOperateDto;
    }
}
