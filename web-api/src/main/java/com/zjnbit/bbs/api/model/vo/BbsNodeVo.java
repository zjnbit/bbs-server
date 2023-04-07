package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class BbsNodeVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parnetId;
    private String nodeCode;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long nodeGroupId;
    private String nodeName;
    private String nodeIcon;
    private Boolean isPublish;
    private Integer sort;
    private List<BbsNodeVo> childNodeList;
}
