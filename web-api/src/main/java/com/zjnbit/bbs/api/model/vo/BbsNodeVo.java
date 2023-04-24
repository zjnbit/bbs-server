package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/18 15:39
 * @Description
 **/
@Data
public class BbsNodeVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    private String nodeCode;
    private String nodeName;
    private String nodeIcon;
    private Boolean isPublish;
    private Integer sort;
    private List<BbsNodeVo> childNodeList;
}
