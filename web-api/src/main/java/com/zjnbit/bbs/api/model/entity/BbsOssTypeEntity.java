package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_oss_type")
public class BbsOssTypeEntity extends BaseEntity {
    /**
     * 名称
     */
    @TableField(value = "oss_name")
    private String ossName;

    /**
     * 类型：'aliyun','tencent','amazon'
     */
    @TableField(value = "oss_type")
    private String ossType;

    @TableField(value = "bucket_name")
    private String bucketName;

    /**
     * BUCKET/prefix路径前缀
     */
    @TableField(value = "`prefix`")
    private String prefix;

    @TableField(value = "oss_host")
    private String ossHost;

    @TableField(value = "cdn_host")
    private String cdnHost;

    /**
     * 是否公共读
     */
    @TableField(value = "is_pub_read")
    private Boolean isPubRead;
}