package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.mapper.*;
import com.zjnbit.bbs.api.model.entity.AuthPermissionEntity;
import com.zjnbit.bbs.api.model.entity.AuthPermissionGroupEntity;
import com.zjnbit.bbs.api.model.entity.AuthRoleEntity;
import com.zjnbit.bbs.api.model.entity.AuthRolePermissionEntity;
import com.zjnbit.bbs.api.model.vo.ManagePermissionGroupVo;
import com.zjnbit.bbs.api.model.vo.ManagePermissionVo;
import com.zjnbit.bbs.api.model.vo.ManageRoleVo;
import com.zjnbit.bbs.api.service.ManageAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ManageAuthorityServiceImpl implements ManageAuthorityService {

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

    @Override
    public List<ManageRoleVo> roleList() {
        List<AuthRoleEntity> authRoleEntityList = authRoleMapper.selectList(new LambdaQueryWrapper<AuthRoleEntity>());
        if (CollUtil.isNotEmpty(authRoleEntityList)) {
            List<ManageRoleVo> result = BeanUtil.copyToList(authRoleEntityList, ManageRoleVo.class);
            if (CollUtil.isNotEmpty(result)) {
                for (ManageRoleVo manageRoleVo : result) {
                    List<AuthRolePermissionEntity> authRolePermissionEntityList = authRolePermissionMapper.selectList(
                            new LambdaQueryWrapper<AuthRolePermissionEntity>()
                                    .eq(AuthRolePermissionEntity::getRoleId, manageRoleVo.getId())
                    );
                    if (CollUtil.isNotEmpty(authRolePermissionEntityList)) {
                        List<AuthPermissionEntity> authPermissionEntityList = authPermissionMapper.selectList(
                                new LambdaQueryWrapper<AuthPermissionEntity>()
                                        .in(
                                                AuthPermissionEntity::getId
                                                , authRolePermissionEntityList.stream()
                                                        .map(AuthRolePermissionEntity::getPermissionId)
                                                        .collect(Collectors.toList())
                                        )
                                        .orderByAsc(AuthPermissionEntity::getPermissionCode)
                        );
                        if (CollUtil.isNotEmpty(authPermissionEntityList)) {
                            List<ManagePermissionVo> managePermissionVoList = BeanUtil.copyToList(authPermissionEntityList, ManagePermissionVo.class);
                            manageRoleVo.setPermissionList(managePermissionVoList);
                        }
                    }
                }
                return result;
            }
        }
        return new ArrayList<>();
    }

    @Override
    public List<ManagePermissionGroupVo> permissionGroupList() {
        List<AuthPermissionGroupEntity> authPermissionGroupEntityList = authPermissionGroupMapper.selectList(new LambdaQueryWrapper<>());
        if (CollUtil.isNotEmpty(authPermissionGroupEntityList)) {
            List<ManagePermissionGroupVo> result = BeanUtil.copyToList(authPermissionGroupEntityList, ManagePermissionGroupVo.class);
            for (ManagePermissionGroupVo managePermissionGroupVo : result) {
                List<AuthPermissionEntity> authPermissionEntityList = authPermissionMapper.selectList(
                        new LambdaQueryWrapper<AuthPermissionEntity>()
                                .eq(AuthPermissionEntity::getGroupId, managePermissionGroupVo.getId())
                                .orderByAsc(AuthPermissionEntity::getPermissionCode)
                );
                if (CollUtil.isNotEmpty(authPermissionEntityList)) {
                    managePermissionGroupVo.setPermissionList(BeanUtil.copyToList(authPermissionEntityList, ManagePermissionVo.class));
                }
            }
            return result;
        }
        return new ArrayList<>();
    }

    @Override
    public List<ManagePermissionVo> permissionList() {
        List<AuthPermissionEntity> authPermissionEntityList = authPermissionMapper.selectList(
                new LambdaQueryWrapper<AuthPermissionEntity>()
                        .orderByAsc(AuthPermissionEntity::getPermissionCode)
        );
        if (CollUtil.isNotEmpty(authPermissionEntityList)) {
            return BeanUtil.copyToList(authPermissionEntityList, ManagePermissionVo.class);
        }
        return new ArrayList<>();
    }
}
