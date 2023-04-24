package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/24 23:02
 * @Description ${description}
 **/

/**
 * 论坛节点表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_node")
public class BbsNodeEntity extends BaseEntity {
    /**
     * 父节点ID，0为顶级节点，0或非0
     */
    @TableField(value = "parent_id")
    private Long parentId;

    /**
     * 节点编码
     */
    @TableField(value = "node_code")
    private String nodeCode;

    /**
     * 节点名称
     */
    @TableField(value = "node_name")
    private String nodeName;

    /**
     * 图标
     */
    @TableField(value = "node_icon")
    private String nodeIcon;

    /**
     * 介绍
     */
    @TableField(value = "remark")
    private String remark;

    /**
     * 是否展示(1开放，0不开放)
     */
    @TableField(value = "is_show")
    private Boolean isShow;

    /**
     * 是否开放发帖功能(1开放，0关闭)
     */
    @TableField(value = "is_publish")
    private Boolean isPublish;

    /**
     * 排序，从小到大
     */
    @TableField(value = "sort")
    private Integer sort;
}