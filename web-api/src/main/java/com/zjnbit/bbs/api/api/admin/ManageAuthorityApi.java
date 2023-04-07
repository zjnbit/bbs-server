package com.zjnbit.bbs.api.api.admin;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.zjnbit.bbs.api.model.vo.ManagePermissionGroupVo;
import com.zjnbit.bbs.api.model.vo.ManagePermissionVo;
import com.zjnbit.bbs.api.model.vo.ManageRoleVo;
import com.zjnbit.bbs.api.router.manage.ManageAuthorityRouter;
import com.zjnbit.bbs.api.service.ManageAuthorityService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SaCheckRole(value = "root")
public class ManageAuthorityApi extends BaseApi<ManageAuthorityService> {

    @GetMapping(value = ManageAuthorityRouter.ROLE_LIST)
    public Result<List<ManageRoleVo>> roleList() {
        return success(baseService.roleList());
    }

    @GetMapping(value = ManageAuthorityRouter.PERMISSION_GROUP)
    public Result<List<ManagePermissionGroupVo>> permissionGroupList() {
        return success(baseService.permissionGroupList());
    }

    @GetMapping(value = ManageAuthorityRouter.PERMISSION_LIST)
    public Result<List<ManagePermissionVo>> permissionList() {
        return success(baseService.permissionList());
    }
}
