package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "auth_role_permission")
public class AuthRolePermissionEntity extends BaseEntity {
    /**
     * 角色ID
     */
    @TableField(value = "role_id")
    private Long roleId;

    /**
     * 权限ID
     */
    @TableField(value = "permission_id")
    private Long permissionId;
}