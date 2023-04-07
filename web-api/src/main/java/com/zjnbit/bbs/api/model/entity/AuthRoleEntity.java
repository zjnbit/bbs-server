package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "auth_role")
public class AuthRoleEntity extends BaseEntity {
    /**
     * 角色标识
     */
    @TableField(value = "role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @TableField(value = "role_name")
    private String roleName;
}