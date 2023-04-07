package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_topic_attach")
public class BbsTopicAttachEntity extends BaseEntity {
    /**
     * 主题ID
     */
    @TableField(value = "topic_id")
    private Long topicId;

    /**
     * 附件ID
     */
    @TableField(value = "attach_id")
    private Long attachId;

    /**
     * 附件名称
     */
    @TableField(value = "attach_name")
    private String attachName;

    /**
     * 附件地址
     */
    @TableField(value = "attach_url")
    private String attachUrl;
}