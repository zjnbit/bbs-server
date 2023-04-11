package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/11 16:35
 * @Description
 **/
@Data
public class BaseAliyunOssCallbackDto {
    private String filename;
    private String mimeType;
    private Long size;
}
