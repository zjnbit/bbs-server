package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "auth_permission")
public class AuthPermissionEntity extends BaseEntity {
    /**
     * 分组ID
     */
    @TableField(value = "group_id")
    private Long groupId;

    /**
     * 权限标识
     */
    @TableField(value = "permission_code")
    private String permissionCode;

    /**
     * 权限名称
     */
    @TableField(value = "permission_name")
    private String permissionName;

    /**
     * 权限地址
     */
    @TableField(value = "permission_path")
    private String permissionPath;

    /**
     * 备注
     */
    @TableField(value = "permission_remark")
    private String permissionRemark;
}