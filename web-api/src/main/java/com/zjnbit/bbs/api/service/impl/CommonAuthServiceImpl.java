package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.framework.constant.CacheKeyConst;
import com.zjnbit.bbs.api.mapper.AuthRoleMapper;
import com.zjnbit.bbs.api.model.dto.CommonRoleInfoDto;
import com.zjnbit.bbs.api.model.dto.CommonRoleInfoPermissionDto;
import com.zjnbit.bbs.api.model.entity.AuthRoleEntity;
import com.zjnbit.bbs.api.service.CommonAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/18 16:22
 * @Description
 **/
@Service
public class CommonAuthServiceImpl implements CommonAuthService {

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    AuthRoleMapper authRoleMapper;

    @Override
    public CommonRoleInfoDto getRoleInfoByCode(String roleCode) {
        CommonRoleInfoDto result = null;
        if (StrUtil.isNotBlank(roleCode)) {
            if (redisTemplate.hasKey(CacheKeyConst.SECURITY_ROLE_INFO + roleCode)) {
                result = (CommonRoleInfoDto) redisTemplate.opsForValue().get(CacheKeyConst.SECURITY_ROLE_INFO + roleCode);
            } else {
                AuthRoleEntity authRoleEntity = authRoleMapper.selectOne(
                        new LambdaQueryWrapper<AuthRoleEntity>().eq(AuthRoleEntity::getRoleCode, roleCode)
                );
                if (null != authRoleEntity) {
                    result.setRoleId(authRoleEntity.getId());
                    result.setRoleCode(authRoleEntity.getRoleCode());
                    result.setRoleName(authRoleEntity.getRoleName());
                    //设置权限
                    //接口权限
                    result.setApis(getRoleInfoPermissionByCode(result.getRoleCode()));
                    result.setMenus(new ArrayList<>());
                    result.setNodes(new ArrayList<>());
                }
            }
        }
        return result;
    }

    @Override
    public List<CommonRoleInfoDto> getRoleInfoByCodes(List<String> roleCodes) {
        List<CommonRoleInfoDto> resultList = new ArrayList<>();
        if (CollUtil.isNotEmpty(roleCodes)) {
            roleCodes.forEach(roleCode -> {
                CommonRoleInfoDto commonRoleInfoDto = getRoleInfoByCode(roleCode);
                if (null != commonRoleInfoDto) {
                    resultList.add(commonRoleInfoDto);
                }
            });
        }
        return resultList;
    }

    @Override
    public List<CommonRoleInfoPermissionDto> getRoleInfoPermissionByCode(String roleCode) {
        return null;
    }
}
