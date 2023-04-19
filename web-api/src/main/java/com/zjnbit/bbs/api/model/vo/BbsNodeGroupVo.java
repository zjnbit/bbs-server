package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class BbsNodeGroupVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String groupName;
    private String remark;
    private Integer sort;
    private List<BbsNodeVoOld> nodeList;
}
