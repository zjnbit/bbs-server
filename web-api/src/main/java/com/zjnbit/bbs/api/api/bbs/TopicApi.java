package com.zjnbit.bbs.api.api.bbs;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnbit.bbs.api.model.param.*;
import com.zjnbit.bbs.api.model.vo.BbsTopicDetailVo;
import com.zjnbit.bbs.api.model.vo.BbsTopicReplyVo;
import com.zjnbit.bbs.api.model.vo.BbsTopicVo;
import com.zjnbit.bbs.api.router.bbs.BbsTopicRouter;
import com.zjnbit.bbs.api.service.BbsTopicService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.Result;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopicApi extends BaseApi<BbsTopicService> {


    /**
     * 分页查询主题
     *
     * @author 非羽Army
     **/
    @PostMapping(value = BbsTopicRouter.PAGE)
    public Result<Page<BbsTopicVo>> topicPage(@RequestBody BbsTopicPageParam param) {
        return success(baseService.topicPage(param));
    }

    /**
     * 主题内容
     *
     * @author 非羽Army
     **/
    @GetMapping(value = BbsTopicRouter.DETAIL)
    public Result<BbsTopicDetailVo> topicDetail(@RequestParam Long id) {
        return success(baseService.topicDetail(id));
    }

    /**
     * 查看主题回复
     *
     * @author 非羽Army
     **/
    @PostMapping(value = BbsTopicRouter.REPLY)
    public Result<Page<BbsTopicReplyVo>> topicReply(@RequestBody BbsTopicReplyPageParam param) {
        return success(baseService.topicReplyPage(param));
    }

    /**
     * 发帖
     *
     * @author 非羽Army
     **/
    @PostMapping(value = BbsTopicRouter.POST)
    public Result<String> topicPost(@RequestBody BbsTopicPostParam param) {
        baseService.topicPost(param);
        return success();
    }

    /**
     * 回帖
     *
     * @author 非羽Army
     **/
    @PostMapping(value = BbsTopicRouter.POST_REPLY)
    public Result<String> topicPostReply(@RequestBody BbsTopicReplyParam param) {
        baseService.topicPostReply(param);
        return success();
    }

    /**
     * 点赞
     *
     * @author 非羽Army
     **/
    @PostMapping(value = BbsTopicRouter.LIKE)
    public Result<String> topicLike(@RequestBody BbsTopicLikeParam param) {
        baseService.topicLike(param);
        return success();
    }
}
