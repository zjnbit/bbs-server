package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author 陈俊羽
* @emp chenjunyu1 211100011
* @date 2023/4/18 15:13
* @Description ${description}
**/

/**
 * 论坛节点分组
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_node_group")
public class BbsNodeGroupEntity extends BaseEntity {
    /**
     * 父分组
     */
    @TableField(value = "parent_id")
    private Long parentId;

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