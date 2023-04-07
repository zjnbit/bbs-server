package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_user_favorite")
public class BbsUserFavoriteEntity extends BaseEntity {
    /**
     * 用户ID
     */
    @TableField(value = "user_id")
    private Long userId;

    /**
     * 收藏夹名称
     */
    @TableField(value = "favorite_name")
    private String favoriteName;

    /**
     * 收藏数量
     */
    @TableField(value = "favorite_count")
    private Integer favoriteCount;
}