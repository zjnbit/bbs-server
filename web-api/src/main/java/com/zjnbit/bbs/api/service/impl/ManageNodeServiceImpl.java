package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.mapper.BbsNodeGroupMapper;
import com.zjnbit.bbs.api.mapper.BbsNodeMapper;
import com.zjnbit.bbs.api.model.entity.BbsNodeEntity;
import com.zjnbit.bbs.api.model.param.BbsNodeParam;
import com.zjnbit.bbs.api.model.vo.BbsNodeVo;
import com.zjnbit.bbs.api.service.ManageNodeService;
import com.zjnbit.framework.web.exception.RequestError;
import com.zjnbit.framework.web.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ManageNodeServiceImpl implements ManageNodeService {

    @Autowired
    BbsNodeGroupMapper bbsNodeGroupMapper;
    @Autowired
    BbsNodeMapper bbsNodeMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<BbsNodeVo> listAllNodes() {
        List<BbsNodeVo> result = new ArrayList<>();
        List<BbsNodeEntity> nodeEntityList = bbsNodeMapper.selectList(
                new LambdaQueryWrapper<BbsNodeEntity>()
                        .eq(BbsNodeEntity::getIsShow, true)
                        .orderByAsc(BbsNodeEntity::getSort)
        );
        result = nodeEntityList.stream()
                .filter(parentNodeEntity -> parentNodeEntity.getParentId().equals(0L))
                .map(parentNodeEntity -> BeanUtil.copyProperties(parentNodeEntity, BbsNodeVo.class))
                .toList();
        for (BbsNodeVo parentNode : result) {
            List<BbsNodeVo> childNodeList = new ArrayList<>();
            for (BbsNodeEntity nodeEntity : nodeEntityList) {
                if (!nodeEntity.getParentId().equals(0L) && parentNode.getId().equals(nodeEntity.getParentId())) {
                    childNodeList.add(BeanUtil.copyProperties(nodeEntity, BbsNodeVo.class));
                }
            }
            parentNode.setChildNodeList(childNodeList);
        }
        return result;
    }

    @Override
    public void add(BbsNodeParam data) {
        if (null != data && !data.getId().equals(0L)) {
            update(data);
        }
        BbsNodeEntity bbsNodeEntity = bbsNodeMapper.selectOne(new LambdaQueryWrapper<BbsNodeEntity>().eq(BbsNodeEntity::getNodeCode, data.getNodeCode()));
        if (bbsNodeEntity != null) {
            throw new RequestException(RequestError.A0004);
        }
        bbsNodeEntity = new BbsNodeEntity();
        BeanUtil.copyProperties(data, bbsNodeEntity);
        bbsNodeMapper.insert(bbsNodeEntity);
    }

    @Override
    public void update(BbsNodeParam data) {
        BbsNodeEntity bbsNodeEntity = bbsNodeMapper.selectById(data.getId());
        if (null == bbsNodeEntity) {
            throw new RequestException(RequestError.A0002);
        }
        bbsNodeEntity = bbsNodeMapper.selectOne(new LambdaQueryWrapper<BbsNodeEntity>().eq(BbsNodeEntity::getNodeCode, data.getNodeCode()));
        if (bbsNodeEntity != null) {
            throw new RequestException(RequestError.A0004);
        }
        bbsNodeEntity = new BbsNodeEntity();
        BeanUtil.copyProperties(data, bbsNodeEntity);
        bbsNodeMapper.updateById(bbsNodeEntity);
    }
}
