package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

public class BbsAttachVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String attachPath;
    private String ossUrl;
    private String cdnUrl;
    private String mimeType;

}
