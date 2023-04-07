package com.zjnbit.bbs.api.api.bbs;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnbit.bbs.api.model.param.BbsUserFollowUserParam;
import com.zjnbit.bbs.api.model.vo.BbsUserInfoVo;
import com.zjnbit.bbs.api.router.bbs.BbsUserRouter;
import com.zjnbit.bbs.api.service.BbsUserService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.BasePageParam;
import com.zjnbit.framework.web.model.Result;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserApi extends BaseApi<BbsUserService> {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @GetMapping(BbsUserRouter.USER_INFO)
    public Result<BbsUserInfoVo> getUserInfo(@RequestParam Long userId) {
        return success(baseService.getUserInfo(userId));
    }

    /**
     * 关注用户
     *
     * @param param
     * @return
     */
    @PostMapping(value = BbsUserRouter.FOLLOW_USER)
    public Result<String> followUser(@RequestBody BbsUserFollowUserParam param) {
        baseService.followUser(param);
        return success();
    }

    /**
     * 获取关注的用户列表
     *
     * @param pageParam
     * @return
     */
    @GetMapping(value = BbsUserRouter.LIST_FOLLOW_USER)
    public Result<Page<BbsUserInfoVo>> listFollowUser(BasePageParam pageParam) {
        return success(baseService.listFollowUser(pageParam));
    }

    /**
     * 获取粉丝列表
     *
     * @author 非羽Army
     **/
    @GetMapping(value = BbsUserRouter.USER_FANS)
    public Result<Page<BbsUserInfoVo>> userFans(BasePageParam pageParam) {
        return success(baseService.userFans(pageParam));
    }
}
