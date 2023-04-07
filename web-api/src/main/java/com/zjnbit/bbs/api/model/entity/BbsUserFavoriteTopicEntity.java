package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_user_favorite_topic")
public class BbsUserFavoriteTopicEntity extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 收藏夹ID
     */
    @TableField(value = "favorite_id")
    private Long favoriteId;

    /**
     * 主题ID
     */
    @TableField(value = "topic_id")
    private Long topicId;

    /**
     * 主题名称
     */
    @TableField(value = "topic_title")
    private String topicTitle;

    /**
     * 发布者ID
     */
    @TableField(value = "post_user_id")
    private Long postUserId;

    /**
     * 发布者昵称
     */
    @TableField(value = "post_user_name")
    private String postUserName;

    /**
     * 发布时间
     */
    @TableField(value = "post_time")
    private LocalDateTime postTime;
}