package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_topic_like")
public class BbsTopicLikeEntity extends BaseEntity {
    /**
     * 主题ID
     */
    @TableField(value = "topic_id")
    private Long topicId;

    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 是否有效
     */
    @TableField(value = "is_valid")
    private Boolean isValid;
}