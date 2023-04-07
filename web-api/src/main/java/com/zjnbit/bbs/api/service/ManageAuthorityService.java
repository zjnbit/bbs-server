package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.vo.ManagePermissionGroupVo;
import com.zjnbit.bbs.api.model.vo.ManagePermissionVo;
import com.zjnbit.bbs.api.model.vo.ManageRoleVo;

import java.util.List;

public interface ManageAuthorityService {

    List<ManageRoleVo> roleList();

    List<ManagePermissionGroupVo> permissionGroupList();

    List<ManagePermissionVo> permissionList();
}
