package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_reply")
public class BbsReplyEntity extends BaseEntity {
    /**
     * 主题ID
     */
    @TableField(value = "topic_id")
    private Long topicId;

    /**
     * 回复ID(非0为回复中的回复)
     */
    @TableField(value = "parent_reply_id")
    private Long parentReplyId;

    /**
     * 引用的回复ID
     */
    @TableField(value = "quote_reply_id")
    private Long quoteReplyId;

    /**
     * 发布者ID
     */
    @TableField(value = "post_user_id")
    private Long postUserId;

    /**
     * 发布者昵称
     */
    @TableField(value = "post_user_nickname")
    private String postUserNickname;

    /**
     * 发布时间
     */
    @TableField(value = "post_time")
    private LocalDateTime postTime;

    /**
     * 发布IP
     */
    @TableField(value = "post_ip")
    private String postIp;

    /**
     * 点赞统计
     */
    @TableField(value = "like_count")
    private Integer likeCount;

    /**
     * 回复内容
     */
    @TableField(value = "reply_content")
    private String replyContent;

    /**
     * 是否匿名
     */
    @TableField(value = "is_anonymous")
    private Boolean isAnonymous;
}