package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
* @author 陈俊羽
* @emp chenjunyu1 211100011
* @date 2023/4/11 16:50
* @Description ${description}
**/

/**
 * 论坛附件表
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "bbs_attach")
public class BbsAttachEntity extends BaseEntity {
    /**
     * 路径
     */
    @TableField(value = "attach_path")
    private String attachPath;

    /**
     * OSS上的地址
     */
    @TableField(value = "oss_url")
    private String ossUrl;

    /**
     * CDN地址
     */
    @TableField(value = "cdn_url")
    private String cdnUrl;

    @TableField(value = "mime_type")
    private String mimeType;

    @TableField(value = "`size`")
    private Long size;
}