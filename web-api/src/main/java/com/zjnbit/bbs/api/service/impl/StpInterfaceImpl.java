package com.zjnbit.bbs.api.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.zjnbit.bbs.api.service.ManageSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    ManageSecurityService manageSecurityService;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        if (null != loginId && CharSequenceUtil.isNotBlank(loginId.toString())) {
            List<String> roleCodeList = getRoleList(loginId, loginType);
            if (CollUtil.isNotEmpty(roleCodeList)) {
                List<String> permissionList = new ArrayList<>();
                for (String roleCode : roleCodeList) {
                    List<String> rolePermissionList = manageSecurityService.stpListPermissionCodeByRoleCode(roleCode);
                    if (CollUtil.isNotEmpty(rolePermissionList)) {
                        permissionList.addAll(rolePermissionList);
                    }
                }
                if (CollUtil.isNotEmpty(permissionList)) {
                    return permissionList;
                }
            }
        }
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        if (null != loginId && CharSequenceUtil.isNotBlank(loginId.toString())) {
            return manageSecurityService.stpListRoleCodeByUserId(Long.valueOf(loginId.toString()));
        }
        return null;
    }
}
