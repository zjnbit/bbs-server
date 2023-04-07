package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_base")
public class UserBaseEntity extends BaseEntity {
    /**
     * 仅支持中国11位手机号
     */
    @TableField(value = "mobile")
    private String mobile;

    /**
     * 邮箱地址
     */
    @TableField(value = "email")
    private String email;

    /**
     * 用户名
     */
    @TableField(value = "username")
    private String username;

    /**
     * 加密后的密码
     */
    @TableField(value = "`password`")
    private String password;

    /**
     * 昵称
     */
    @TableField(value = "nickname")
    private String nickname;

    /**
     * 性别(M:男性,F:女性,N:未知)
     */
    @TableField(value = "gender")
    private String gender;

    /**
     * 头像，URL地址
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 积分
     */
    @TableField(value = "score")
    private Integer score;

    /**
     * 状态
     */
    @TableField(value = "`status`")
    private String status;

    /**
     * 级别
     */
    @TableField(value = "`level`")
    private String level;

    /**
     * 是否激活
     */
    @TableField(value = "is_activated")
    private Boolean isActivated;
}