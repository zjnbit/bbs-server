package com.zjnbit.bbs.api.model.param;

import lombok.Data;

@Data
public class BbsNodeParam {
    private Long id;
    private Long parentId;
    private String nodeCode;
    private Long nodeGroupId;
    private String nodeName;
    private String nodeIcon;
    private Boolean isShow;
    private Boolean isPublish;
    private Integer sort;

}
