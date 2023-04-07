package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_user_follow")
public class BbsUserFollowEntity extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 关注的用户的ID
     */
    @TableField(value = "follow_user_id")
    private Long followUserId;

    /**
     * 是否关注
     */
    @TableField(value = "is_follow")
    private Boolean isFollow;

    /**
     * 关注时间
     */
    @TableField(value = "follow_time")
    private LocalDateTime followTime;
}