package com.zjnbit.bbs.api.model.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
public class ManageRoleVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private String roleCode;
    private String roleName;
    private List<ManagePermissionVo> permissionList;
}
