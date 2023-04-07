package com.zjnbit.bbs.api.model.param;

import lombok.Data;

@Data
public class BbsUserFollowUserParam {
    private Long followUserId;
    private Boolean isFollow;
}
