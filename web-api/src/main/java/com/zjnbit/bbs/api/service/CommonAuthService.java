package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.dto.CommonRoleInfoDto;
import com.zjnbit.bbs.api.model.dto.CommonRoleInfoPermissionDto;

import java.util.List;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/18 16:21
 * @Description
 **/
public interface CommonAuthService {
    /**
     * 根据角色codes，获取角色权限信息
     * @param roleCode
     * @return
     */
    CommonRoleInfoDto getRoleInfoByCode(String roleCode);
    /**
     * 根据角色codes，获取角色权限信息
     * @param roleCodes
     * @return
     */
    List<CommonRoleInfoDto> getRoleInfoByCodes(List<String> roleCodes);

    List<CommonRoleInfoPermissionDto> getRoleInfoPermissionByCode(String roleCode);
}
