package com.zjnbit.bbs.api.model.vo;

import lombok.Data;

@Data
public class BaseOssUploadPolicyVo {

    private String accessid;
    private String policy;
    private String signature;
    private String dir;
    private String host;
    private String expire;
    private String callback;

}
