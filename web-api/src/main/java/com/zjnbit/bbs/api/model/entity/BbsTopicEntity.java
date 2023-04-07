package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_topic")
public class BbsTopicEntity extends BaseEntity {
    /**
     * 节点ID
     */
    @TableField(value = "node_id")
    private Long nodeId;

    /**
     * 状态(open:开启,closed:关闭,locked:锁定,auditing:审核中)
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 异常详情
     */
    @TableField(value = "status_detail")
    private String statusDetail;

    /**
     * 主题名称
     */
    @TableField(value = "topic_title")
    private String topicTitle;

    /**
     * 发布者ID
     */
    @TableField(value = "post_user")
    private Long postUser;

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
     * 发布者IP
     */
    @TableField(value = "post_ip")
    private String postIp;

    /**
     * 最后回复用户ID
     */
    @TableField(value = "last_reply_user")
    private Long lastReplyUser;

    /**
     * 最后回复用户昵称
     */
    @TableField(value = "last_reply_user_nickname")
    private String lastReplyUserNickname;

    /**
     * 最后回复时间
     */
    @TableField(value = "last_reply_time")
    private LocalDateTime lastReplyTime;

    /**
     * 最后回复IP
     */
    @TableField(value = "last_reply_ip")
    private String lastReplyIp;

    /**
     * 是否匿名
     */
    @TableField(value = "is_anonymous")
    private Boolean isAnonymous;

    /**
     * 回复数量
     */
    @TableField(value = "reply_count")
    private Integer replyCount;

    /**
     * 点赞数量
     */
    @TableField(value = "like_count")
    private Integer likeCount;

    /**
     * 收藏数量
     */
    @TableField(value = "favorite_count")
    private Integer favoriteCount;
}