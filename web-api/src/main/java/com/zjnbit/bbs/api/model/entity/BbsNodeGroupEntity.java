package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_node_group")
public class BbsNodeGroupEntity extends BaseEntity {
    /**
     * 分钟名称
     */
    @TableField(value = "group_name")
    private String groupName;

    /**
     * 备注
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 排序，从小到达
     */
    @TableField(value = "sort")
    private Integer sort;
}