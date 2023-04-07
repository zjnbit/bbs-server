package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_sys_conf")
public class BbsSysConfEntity extends BaseEntity {
    @TableField(value = "conf_key")
    private String confKey;

    @TableField(value = "conf_value")
    private String confValue;
}