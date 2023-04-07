package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "auth_permission_group")
public class AuthPermissionGroupEntity extends BaseEntity {
    /**
     * 分组名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 备注
     */
    @TableField(value = "group_remark")
    private String groupRemark;
}