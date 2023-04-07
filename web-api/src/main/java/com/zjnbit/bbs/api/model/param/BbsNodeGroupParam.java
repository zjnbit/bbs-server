package com.zjnbit.bbs.api.model.param;

import lombok.Data;

@Data
public class BbsNodeGroupParam {
    private Long id;
    private String groupName;
    private String remark;
    private Integer sort;
}
