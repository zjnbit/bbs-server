package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class ManagePermissionVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Long groupId;
    private String permissionCode;
    private String permissionName;
    private String permissionPath;
    private String permissionRemark;
}
