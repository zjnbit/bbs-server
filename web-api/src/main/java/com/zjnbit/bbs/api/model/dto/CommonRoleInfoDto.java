package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/18 15:56
 * @Description
 **/
@Data
public class CommonRoleInfoDto {
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 角色标识
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 接口权限
     */
    private List<CommonRoleInfoPermissionDto> apis;
    /**
     * 菜单权限
     */
    private List<CommonRoleInfoPermissionDto> menus;
    /**
     * 节点权限
     */
    private List<CommonRoleInfoPermissionDto> nodes;

}
