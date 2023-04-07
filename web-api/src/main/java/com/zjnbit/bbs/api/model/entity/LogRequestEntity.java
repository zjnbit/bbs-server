package com.zjnbit.bbs.api.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zjnbit.framework.db.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName(value = "log_request")
public class LogRequestEntity extends BaseEntity {

    @TableField(value = "request_id")
    private String requestId;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "uri")
    private String uri;

    @TableField(value = "`method`")
    private String method;

    @TableField(value = "ua")
    private String ua;

    @TableField(value = "path_param")
    private String pathParam;

    @TableField(value = "body_param")
    private String bodyParam;

    @TableField(value = "ip")
    private Long ip;

    @TableField(value = "begin_time")
    private LocalDateTime beginTime;

    @TableField(value = "end_time")
    private LocalDateTime endTime;

    @TableField(value = "consumed_time")
    private Integer consumedTime;

    @TableField(value = "response_code")
    private String responseCode;

    @TableField(value = "response_msg")
    private String responseMsg;

    @TableField(value = "response_data")
    private String responseData;

}