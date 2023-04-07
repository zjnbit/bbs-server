package com.zjnbit.bbs.api.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjnbit.bbs.api.framework.config.AppConfig;
import com.zjnbit.bbs.api.mapper.*;
import com.zjnbit.bbs.api.model.dto.BbsTopicReplyAddDto;
import com.zjnbit.bbs.api.model.dto.BbsUserOperateDto;
import com.zjnbit.bbs.api.model.entity.*;
import com.zjnbit.bbs.api.model.param.*;
import com.zjnbit.bbs.api.model.vo.BbsTopicDetailVo;
import com.zjnbit.bbs.api.model.vo.BbsTopicReplyVo;
import com.zjnbit.bbs.api.model.vo.BbsTopicVo;
import com.zjnbit.bbs.api.service.BaseRabbitMqService;
import com.zjnbit.bbs.api.service.BbsTopicService;
import com.zjnbit.bbs.api.service.BbsUserService;
import com.zjnbit.framework.web.exception.RequestError;
import com.zjnbit.framework.web.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BbsTopicServiceImpl implements BbsTopicService {
    @Autowired
    AppConfig appConfig;
    @Autowired
    BaseRabbitMqService baseRabbitMqService;
    @Autowired
    BbsUserService bbsUserService;
    @Autowired
    BbsNodeMapper bbsNodeMapper;
    @Autowired
    BbsTopicMapper bbsTopicMapper;
    @Autowired
    BbsTopicContentMapper bbsTopicContentMapper;
    @Autowired
    BbsReplyMapper bbsReplyMapper;
    @Autowired
    BbsTopicLikeMapper bbsTopicLikeMapper;

    @Override
    public Page<BbsTopicVo> topicPage(BbsTopicPageParam data) {
        if (null == data.getNodeId()) {
            throw new RequestException(RequestError.A0003);
        }
        try {
            BbsNodeEntity bbsNodeEntity = bbsNodeMapper.selectById(data.getNodeId());
            if (null == bbsNodeEntity) {
                throw new RequestException(RequestError.A0002);
            }
        } catch (Exception e) {
            throw new RequestException(RequestError.A0002);
        }
        Page<BbsTopicEntity> pageParam = new Page<>(data.getCurrent(), data.getSize());
        List<BbsTopicEntity> topicEntityList = bbsTopicMapper.selectPage(
                pageParam
                , new LambdaQueryWrapper<BbsTopicEntity>()
                        .eq(BbsTopicEntity::getNodeId, data.getNodeId())
                        .orderByDesc(BbsTopicEntity::getLastReplyTime)
        ).getRecords();
        if (CollUtil.isEmpty(topicEntityList)) {
            return new Page<BbsTopicVo>(data.getCurrent(), data.getSize());
        } else {
            Page<BbsTopicVo> page = new Page<BbsTopicVo>(data.getCurrent(), data.getSize());
            List<BbsTopicVo> list = topicEntityList.stream().map(item -> {
                BbsTopicVo vo = new BbsTopicVo();
                BeanUtil.copyProperties(item, vo);
                return vo;
            }).collect(Collectors.toList());
            page.setRecords(list);
            return page;
        }
    }

    @Override
    public BbsTopicDetailVo topicDetail(Long id) {
        BbsTopicEntity bbsTopicEntity = bbsTopicMapper.selectById(id);
        if (null == bbsTopicEntity) {
            throw new RequestException(RequestError.A0002);
        }
        List<BbsTopicContentEntity> bbsTopicContentEntityList = bbsTopicContentMapper.selectList(
                new LambdaQueryWrapper<BbsTopicContentEntity>()
                        .eq(BbsTopicContentEntity::getTopicId, id)
                        .orderByDesc(BbsTopicContentEntity::getCreateTime)
        );
        BbsTopicDetailVo result = new BbsTopicDetailVo();
        BeanUtil.copyProperties(bbsTopicEntity, result);
        result.setContent(bbsTopicContentEntityList.get(0).getContent());
        result.setIsContentModified(bbsTopicContentEntityList.size() > 1 ? true : false);
        return result;
    }

    @Override
    public Page<BbsTopicReplyVo> topicReplyPage(BbsTopicReplyPageParam data) {
        BbsTopicEntity bbsTopicEntity = bbsTopicMapper.selectById(data.getTopicId());
        if (null == bbsTopicEntity) {
            throw new RequestException(RequestError.A0002);
        }
        Page<BbsReplyEntity> pageParam = new Page<>(data.getCurrent(), data.getSize());
        List<BbsReplyEntity> replyEntityList = bbsReplyMapper.selectPage(
                pageParam
                , new LambdaQueryWrapper<BbsReplyEntity>()
                        .eq(BbsReplyEntity::getTopicId, data.getTopicId())
        ).getRecords();
        if (CollUtil.isEmpty(replyEntityList)) {
            return new Page<BbsTopicReplyVo>(data.getCurrent(), data.getSize());
        } else {
            Page<BbsTopicReplyVo> page = new Page<>(data.getCurrent(), data.getSize());
            List<BbsTopicReplyVo> list = replyEntityList.stream().map(item -> {
                BbsTopicReplyVo vo = new BbsTopicReplyVo();
                BeanUtil.copyProperties(item, vo);
                if (!vo.getParentReplyId().equals(0L)) {
                    //添加回复的父内容
                    BbsReplyEntity parnetReplyEntity = bbsReplyMapper.selectById(vo.getParentReplyId());
                    if (null != parnetReplyEntity) {
                        vo.setParentReplyContent(parnetReplyEntity.getReplyContent());
                        vo.setParentReplyPostUserId(parnetReplyEntity.getPostUserId());
                        vo.setParentReplyPostUserNickname(parnetReplyEntity.getPostUserNickname());
                        vo.setParentReplyPostTime(parnetReplyEntity.getPostTime());
                    }
                }
                if (!vo.getQuoteReplyId().equals(0L)) {
                    //添加引用的回复
                    BbsReplyEntity quoteReplyEntity = bbsReplyMapper.selectById(vo.getQuoteReplyId());
                    if (null != quoteReplyEntity) {
                        vo.setQuoteReplyContent(quoteReplyEntity.getReplyContent());
                        vo.setQuoteReplyPostUserId(quoteReplyEntity.getPostUserId());
                        vo.setQuoteReplyPostUserNickname(quoteReplyEntity.getPostUserNickname());
                        vo.setQuoteReplyPostTime(quoteReplyEntity.getPostTime());
                    }
                }
                return vo;
            }).collect(Collectors.toList());
            page.setRecords(list);
            return page;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void topicPost(BbsTopicPostParam data) {
        if (!StpUtil.isLogin()) {
            throw new RequestException(RequestError.A0206);
        }
        BbsNodeEntity nodeEntity = bbsNodeMapper.selectById(data.getNodeId());
        if (null == nodeEntity) {
            throw new RequestException(RequestError.A0003);
        }
        if (null == data.getTopicId()) {
            BbsTopicEntity topicEntity = new BbsTopicEntity();
            topicEntity.setNodeId(data.getNodeId());
            topicEntity.setStatus(appConfig.getBbsConf().getTopic().getPostStatusDefault());
            topicEntity.setTopicTitle(data.getTopicTitle());
            BbsUserOperateDto bbsUserOperateDto = bbsUserService.getUserOperateInfo();
            topicEntity.setPostUser(bbsUserOperateDto.getUserId());
            topicEntity.setPostUserNickname(bbsUserOperateDto.getNickname());
            topicEntity.setPostTime(bbsUserOperateDto.getTime());
            topicEntity.setPostIp(bbsUserOperateDto.getIp());
            topicEntity.setLastReplyUser(bbsUserOperateDto.getUserId());
            topicEntity.setLastReplyUserNickname(bbsUserOperateDto.getNickname());
            topicEntity.setLastReplyTime(bbsUserOperateDto.getTime());
            topicEntity.setLastReplyIp(bbsUserOperateDto.getIp());
            bbsTopicMapper.insert(topicEntity);
            BbsTopicContentEntity topicContentEntity = new BbsTopicContentEntity();
            topicContentEntity.setTopicId(topicEntity.getId());
            topicContentEntity.setContent(data.getTopicContent());
            bbsTopicContentMapper.insert(topicContentEntity);
        } else {
            BbsTopicEntity topicEntity = bbsTopicMapper.selectById(data.getTopicId());
            if (null == topicEntity) {
                throw new RequestException(RequestError.A0002);
            }
            BbsTopicContentEntity topicContentEntity = new BbsTopicContentEntity();
            topicContentEntity.setTopicId(topicEntity.getId());
            topicContentEntity.setContent(data.getTopicContent());
            bbsTopicContentMapper.insert(topicContentEntity);
        }
    }

    @Override
    public void topicPostReply(BbsTopicReplyParam data) {
        BbsTopicEntity topicEntity = bbsTopicMapper.selectById(data.getTopicId());
        if (null == topicEntity) {
            throw new RequestException(RequestError.A0002);
        }
        //更新bbs_reply
        BbsReplyEntity bbsReplyEntity = BeanUtil.copyProperties(data, BbsReplyEntity.class);
        //验证 parentReply
        if (null != data.getParentReplyId() && !data.getParentReplyId().equals(0L)) {
            BbsReplyEntity parentReplyEntity = bbsReplyMapper.selectById(data.getParentReplyId());
            if (null == parentReplyEntity) {
                bbsReplyEntity.setParentReplyId(0L);
            }
        }
        //验证 quoteReply
        if (null != data.getQuoteReplyId() && !data.getQuoteReplyId().equals(0L)) {
            BbsReplyEntity quoteReplyEntity = bbsReplyMapper.selectById(data.getQuoteReplyId());
            if (null == quoteReplyEntity) {
                bbsReplyEntity.setQuoteReplyId(0L);
            }
        }
        BbsUserOperateDto bbsUserOperateDto = bbsUserService.getUserOperateInfo();
        bbsReplyEntity.setPostUserId(bbsUserOperateDto.getUserId());
        bbsReplyEntity.setPostUserNickname(bbsUserOperateDto.getNickname());
        bbsReplyEntity.setPostTime(bbsUserOperateDto.getTime());
        bbsReplyEntity.setPostIp(bbsUserOperateDto.getIp());
        bbsReplyMapper.insert(bbsReplyEntity);
        //更新Topic
        ThreadUtil.execAsync(() -> baseRabbitMqService.sendTopicReplyAdd(new BbsTopicReplyAddDto(data.getTopicId(), bbsUserOperateDto)));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTopicReply(BbsTopicReplyAddDto data) {
        BbsTopicEntity bbsTopicEntity = bbsTopicMapper.selectOne(
                new LambdaQueryWrapper<BbsTopicEntity>()
                        .eq(BbsTopicEntity::getId, data.getTopicId())
                        .last(" FOR UPDATE")
        );
        if (null != bbsTopicEntity) {
            bbsTopicEntity.setLastReplyUser(data.getBbsUserOperateDto().getUserId());
            bbsTopicEntity.setLastReplyUserNickname(data.getBbsUserOperateDto().getNickname());
            bbsTopicEntity.setLastReplyTime(data.getBbsUserOperateDto().getTime());
            bbsTopicEntity.setLastReplyIp(data.getBbsUserOperateDto().getIp());
            bbsTopicEntity.setReplyCount(bbsTopicEntity.getReplyCount() + 1);
            bbsTopicMapper.updateById(bbsTopicEntity);
        }
    }

    @Override
    public void topicLike(BbsTopicLikeParam data) {
        Long userId = (Long) StpUtil.getLoginId();
        BbsTopicLikeEntity bbsTopicLikeEntity = bbsTopicLikeMapper.selectOne(
                new LambdaQueryWrapper<BbsTopicLikeEntity>()
                        .eq(BbsTopicLikeEntity::getUserId, userId)
                        .eq(BbsTopicLikeEntity::getTopicId, data.getTopicId())
        );
        if (null == bbsTopicLikeEntity) {
            bbsTopicLikeEntity = new BbsTopicLikeEntity();
            bbsTopicLikeEntity.setTopicId(data.getTopicId());
            bbsTopicLikeEntity.setUserId(userId);
            bbsTopicLikeEntity.setIsValid(data.getIsLike());
            bbsTopicLikeMapper.insert(bbsTopicLikeEntity);
        } else {
            bbsTopicLikeEntity.setIsValid(data.getIsLike());
            bbsTopicLikeMapper.updateById(bbsTopicLikeEntity);
        }
        baseRabbitMqService.sendTopicLikeRefresh(data.getTopicId());
    }

    @Override
    public void topicLikeRefresh(Long topicId) {
        BbsTopicEntity bbsTopicEntity = bbsTopicMapper.selectById(topicId);
        Long topicLickCount = bbsTopicLikeMapper.selectCount(
                new LambdaQueryWrapper<BbsTopicLikeEntity>()
                        .eq(BbsTopicLikeEntity::getTopicId, topicId)
                        .eq(BbsTopicLikeEntity::getIsValid, true)
        );
        if (null != topicLickCount) {
            bbsTopicEntity.setLikeCount(NumberUtil.parseInt(topicLickCount + ""));
            bbsTopicMapper.updateById(bbsTopicEntity);
        }
    }
}
