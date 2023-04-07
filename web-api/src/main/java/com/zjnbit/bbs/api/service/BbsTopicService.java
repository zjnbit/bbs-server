package com.zjnbit.bbs.api.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnbit.bbs.api.model.dto.BbsTopicReplyAddDto;
import com.zjnbit.bbs.api.model.param.*;
import com.zjnbit.bbs.api.model.vo.BbsTopicDetailVo;
import com.zjnbit.bbs.api.model.vo.BbsTopicReplyVo;
import com.zjnbit.bbs.api.model.vo.BbsTopicVo;

public interface BbsTopicService {

    /**
     * 查询话题列表
     *
     * @param data
     * @return
     */
    Page<BbsTopicVo> topicPage(BbsTopicPageParam data);

    /**
     * 查询话题详情
     *
     * @param id
     * @return
     */
    BbsTopicDetailVo topicDetail(Long id);

    /**
     * 查询话题回复列表
     *
     * @param data
     * @return
     */
    Page<BbsTopicReplyVo> topicReplyPage(BbsTopicReplyPageParam data);

    /**
     * 发布话题
     *
     * @param data
     */
    void topicPost(BbsTopicPostParam data);

    /**
     * 回复话题
     *
     * @param data
     */
    void topicPostReply(BbsTopicReplyParam data);

    /**
     * 添加话题回复数量
     *
     * @param bbsTopicReplyAddDto
     */
    void addTopicReply(BbsTopicReplyAddDto bbsTopicReplyAddDto);

    /**
     * 点赞/取消点赞话题
     *
     * @param data
     */
    void topicLike(BbsTopicLikeParam data);

    /**
     * 更新话题点赞数量
     *
     * @param topicId
     */
    void topicLikeRefresh(Long topicId);
}
