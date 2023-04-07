package com.zjnbit.bbs.api.service;

import java.util.List;

public interface ManageSecurityService {


    List<String> stpListRoleCodeByUserId(Long userId);

    List<String> stpListPermissionCodeByRoleCode(String roleCode);

    void cleanAllStpPermission();
}
