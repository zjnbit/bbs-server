package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.framework.constant.CacheKeyConst;
import com.zjnbit.bbs.api.mapper.AuthRoleMapper;
import com.zjnbit.bbs.api.mapper.AuthUserRoleMapper;
import com.zjnbit.bbs.api.mapper.UserBaseMapper;
import com.zjnbit.bbs.api.model.entity.AuthRoleEntity;
import com.zjnbit.bbs.api.model.entity.AuthUserRoleEntity;
import com.zjnbit.bbs.api.model.entity.UserBaseEntity;
import com.zjnbit.bbs.api.service.CommonUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/18 15:52
 * @Description
 **/
@Service
public class CommonUserServiceImpl implements CommonUserService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    UserBaseMapper userBaseMapper;
    @Autowired
    AuthUserRoleMapper authUserRoleMapper;
    @Autowired
    AuthRoleMapper authRoleMapper;

    @Override
    public List<String> getRoleCodesByUserId(Long userId) {
        //获取用户拥有的角色code
        List<String> userRoleCodeList = new ArrayList<>();
        if (redisTemplate.hasKey(CacheKeyConst.SECURITY_USER_ROLE + userId)) {
            userRoleCodeList = redisTemplate.opsForList().range(CacheKeyConst.SECURITY_USER_ROLE + userId, 0, -1);
        } else {
            //获取用户信息
            UserBaseEntity userBaseEntity = userBaseMapper.selectById(userId);
            if (null != userBaseEntity
                    && userBaseEntity.getIsActivated()
                    && !"locked".equals(userBaseEntity.getStatus())) {
                List<AuthUserRoleEntity> authUserRoleEntityList = authUserRoleMapper.selectList(
                        new LambdaQueryWrapper<AuthUserRoleEntity>()
                                .eq(AuthUserRoleEntity::getUserId, userId)
                );
                if (CollUtil.isEmpty(authUserRoleEntityList)) {
                    //如果未配置角色，则默认为普通用户
                    userRoleCodeList.add("normal");
                    redisTemplate.opsForList().rightPushAll(CacheKeyConst.SECURITY_USER_ROLE + userId, userRoleCodeList);
                } else {
                    List<AuthRoleEntity> authRoleEntityList = authRoleMapper.selectList(
                            new LambdaQueryWrapper<AuthRoleEntity>()
                                    .in(AuthRoleEntity::getId, authUserRoleEntityList.stream().map(AuthUserRoleEntity::getRoleId).collect(Collectors.toList()))
                    );
                    if (CollUtil.isEmpty(authRoleEntityList)) {
                        //未查询到角色，默认未普通用户
                        userRoleCodeList.add("normal");
                        redisTemplate.opsForList().rightPushAll(CacheKeyConst.SECURITY_USER_ROLE + userId, userRoleCodeList);
                    } else {
                        userRoleCodeList.addAll(authRoleEntityList.stream().map(AuthRoleEntity::getRoleCode).collect(Collectors.toList()));
                        redisTemplate.opsForList().rightPushAll(CacheKeyConst.SECURITY_USER_ROLE + userId, userRoleCodeList);
                    }
                }
            }
        }
        return userRoleCodeList;
    }
}
