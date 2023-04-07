package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.framework.constant.CacheKeyConst;
import com.zjnbit.bbs.api.mapper.*;
import com.zjnbit.bbs.api.model.entity.*;
import com.zjnbit.bbs.api.service.ManageSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ManageSecurityServiceImpl implements ManageSecurityService {

    @Autowired
    UserBaseMapper userBaseMapper;
    @Autowired
    AuthPermissionMapper authPermissionMapper;
    @Autowired
    AuthPermissionGroupMapper authPermissionGroupMapper;
    @Autowired
    AuthRoleMapper authRoleMapper;
    @Autowired
    AuthRolePermissionMapper authRolePermissionMapper;
    @Autowired
    AuthUserRoleMapper authUserRoleMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<String> stpListRoleCodeByUserId(Long userId) {
        if (null == userId) {
            return null;
        }
        if (redisTemplate.hasKey(CacheKeyConst.SECURITY_USER_ROLE + userId)) {
            return redisTemplate.opsForList().range(CacheKeyConst.SECURITY_USER_ROLE + userId, 0, -1);
        } else {
            UserBaseEntity userBaseEntity = userBaseMapper.selectById(userId);
            if (null != userBaseEntity) {
                List<AuthUserRoleEntity> authUserRoleEntityList = authUserRoleMapper.selectList(
                        new LambdaQueryWrapper<AuthUserRoleEntity>()
                                .eq(AuthUserRoleEntity::getUserId, userId)
                );
                if (CollUtil.isNotEmpty(authUserRoleEntityList)) {
                    List<AuthRoleEntity> authRoleEntityList = authRoleMapper.selectList(
                            new LambdaQueryWrapper<AuthRoleEntity>()
                                    .in(AuthRoleEntity::getId, authUserRoleEntityList.stream().map(AuthUserRoleEntity::getRoleId).collect(Collectors.toList()))
                    );
                    if (CollUtil.isNotEmpty(authRoleEntityList)) {
                        List<String> roleCodeList = authRoleEntityList.stream().map(AuthRoleEntity::getRoleCode).collect(Collectors.toList());
                        redisTemplate.opsForList().rightPushAll(CacheKeyConst.SECURITY_USER_ROLE + userId, roleCodeList);
                        return roleCodeList;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public List<String> stpListPermissionCodeByRoleCode(String roleCode) {
        if (CharSequenceUtil.isNotBlank(roleCode)) {
            if (redisTemplate.hasKey(CacheKeyConst.SECURITY_ROLE_PERMISSION + roleCode)) {
                return redisTemplate.opsForList().range(CacheKeyConst.SECURITY_ROLE_PERMISSION + roleCode, 0, -1);
            } else {
                AuthRoleEntity authRoleEntity = authRoleMapper.selectOne(
                        new LambdaQueryWrapper<AuthRoleEntity>()
                                .eq(AuthRoleEntity::getRoleCode, roleCode)
                );
                if (null != authRoleEntity) {
                    List<AuthRolePermissionEntity> authRolePermissionEntityList = authRolePermissionMapper.selectList(
                            new LambdaQueryWrapper<AuthRolePermissionEntity>()
                                    .eq(AuthRolePermissionEntity::getRoleId, authRoleEntity.getId())
                    );
                    if (CollUtil.isNotEmpty(authRolePermissionEntityList)) {
                        List<AuthPermissionEntity> authPermissionEntityList = authPermissionMapper.selectList(
                                new LambdaQueryWrapper<AuthPermissionEntity>()
                                        .in(AuthPermissionEntity::getId, authRolePermissionEntityList.stream().map(AuthRolePermissionEntity::getPermissionId).collect(Collectors.toList()))
                        );
                        if (CollUtil.isNotEmpty(authPermissionEntityList)) {
                            redisTemplate.opsForList().rightPushAll(CacheKeyConst.SECURITY_ROLE_PERMISSION + roleCode, authPermissionEntityList.stream().map(AuthPermissionEntity::getPermissionCode).collect(Collectors.toList()));
                            return authPermissionEntityList.stream().map(AuthPermissionEntity::getPermissionCode).collect(Collectors.toList());
                        }
                    }
                }

            }
        }
        return null;
    }

    @Override
    public void cleanAllStpPermission() {
        Set<String> userRoleKeys = redisTemplate.keys(CacheKeyConst.SECURITY_USER_ROLE + "*");
        if (CollUtil.isNotEmpty(userRoleKeys)) {
            redisTemplate.delete(userRoleKeys);
        }
        Set<String> rolePermissionKeys = redisTemplate.keys(CacheKeyConst.SECURITY_ROLE_PERMISSION + "*");
        if (CollUtil.isNotEmpty(rolePermissionKeys)) {
            redisTemplate.delete(rolePermissionKeys);
        }
    }
}
