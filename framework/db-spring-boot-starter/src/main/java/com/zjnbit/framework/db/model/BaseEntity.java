package com.zjnbit.framework.db.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.zjnbit.framework.tools.constant.DatePatternConst;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class BaseEntity implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    @JsonSerialize(using = ToStringSerializer.class)
    @DateTimeFormat(pattern = DatePatternConst.PATTERN_DATETIME)
    @JsonFormat(pattern = DatePatternConst.PATTERN_DATETIME)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    @JsonSerialize(using = ToStringSerializer.class)
    @DateTimeFormat(pattern = DatePatternConst.PATTERN_DATETIME)
    @JsonFormat(pattern = DatePatternConst.PATTERN_DATETIME)
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
    private Boolean isDeleted;
    private Integer version;
}
