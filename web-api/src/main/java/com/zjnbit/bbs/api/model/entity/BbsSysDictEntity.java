package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_sys_dict")
public class BbsSysDictEntity extends BaseEntity {
    /**
     * 字典名称
     */
    @TableField(value = "dict_name")
    private String dictName;

    /**
     * 字典键
     */
    @TableField(value = "dict_key")
    private String dictKey;

    /**
     * 字典值
     */
    @TableField(value = "dict_value")
    private String dictValue;

    /**
     * 备注
     */
    @TableField(value = "dict_remark")
    private String dictRemark;
}