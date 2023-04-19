package com.zjnbit.bbs.api.model.dto;

import lombok.Data;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/18 15:58
 * @Description
 **/
@Data
public class CommonRoleInfoPermissionDto {
    private Long id;
    private Long groupId;
    private String permissionCode;
    private String permissionName;
    private String permissionPath;
    private String permissionRemark;
}
