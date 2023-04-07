package com.zjnbit.bbs.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnbit.bbs.api.model.dto.BbsUserOperateDto;
import com.zjnbit.bbs.api.model.param.BbsUserFollowUserParam;
import com.zjnbit.bbs.api.model.vo.BbsUserInfoVo;
import com.zjnbit.framework.web.model.BasePageParam;

public interface BbsUserService {

    BbsUserInfoVo getUserInfo(Long userId);

    void followUser(BbsUserFollowUserParam data);

    Page<BbsUserInfoVo> listFollowUser(BasePageParam data);

    Page<BbsUserInfoVo> userFans(BasePageParam data);

    BbsUserOperateDto getUserOperateInfo();

}