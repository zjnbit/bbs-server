package com.zjnbit.bbs.api.model.param;

import com.zjnbit.framework.web.model.BasePageParam;
import lombok.Data;

@Data
public class BbsTopicReplyPageParam extends BasePageParam {
    private Long topicId;
}
