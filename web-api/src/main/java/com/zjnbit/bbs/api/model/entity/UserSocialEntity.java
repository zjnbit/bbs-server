package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "user_social")
public class UserSocialEntity extends BaseEntity {
    /**
     * 用户ID，user_base.id
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 第三方应用标识
     */
    @TableField(value = "social_id")
    private String socialId;

    /**
     * 第三方应用(wxma:小程序,qq:QQ,wxmp:微信公众号)
     */
    @TableField(value = "social_type")
    private String socialType;

    /**
     * 第三方内的用户标识
     */
    @TableField(value = "open_id")
    private String openId;

    /**
     * 多应用统一标识
     */
    @TableField(value = "union_id")
    private String unionId;

    /**
     * 第三方应用内的用户密钥
     */
    @TableField(value = "user_secret_key")
    private String userSecretKey;

    /**
     * 第三方应用内的用户信息
     */
    @TableField(value = "userinfo")
    private String userinfo;
}