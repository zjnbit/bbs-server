package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_point_item")
public class UserPointItemEntity extends BaseEntity {
    /**
     * 积分项名称
     */
    @TableField(value = "item_name")
    private String itemName;
}