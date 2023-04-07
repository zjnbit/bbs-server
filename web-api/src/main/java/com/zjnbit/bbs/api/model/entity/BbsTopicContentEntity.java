package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_topic_content")
public class BbsTopicContentEntity extends BaseEntity {
    /**
     * 主题ID
     */
    @TableField(value = "topic_id")
    private Long topicId;

    /**
     * 主题内容
     */
    @TableField(value = "content")
    private String content;
}