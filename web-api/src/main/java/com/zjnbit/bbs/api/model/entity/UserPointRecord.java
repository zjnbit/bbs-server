package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_point_record")
public class UserPointRecord extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 发生的积分
     */
    @TableField(value = "modify_point")
    private Integer modifyPoint;

    /**
     * 发生前积分
     */
    @TableField(value = "before_point")
    private Integer beforePoint;

    /**
     * 发生后积分
     */
    @TableField(value = "result_point")
    private Integer resultPoint;
}