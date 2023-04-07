package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.framework.constant.CacheKeyConst;
import com.zjnbit.bbs.api.mapper.BbsNodeGroupMapper;
import com.zjnbit.bbs.api.mapper.BbsNodeMapper;
import com.zjnbit.bbs.api.model.entity.BbsNodeEntity;
import com.zjnbit.bbs.api.model.entity.BbsNodeGroupEntity;
import com.zjnbit.bbs.api.model.param.BbsNodeGroupParam;
import com.zjnbit.bbs.api.model.param.BbsNodeParam;
import com.zjnbit.bbs.api.model.vo.BbsNodeGroupVo;
import com.zjnbit.bbs.api.model.vo.BbsNodeVo;
import com.zjnbit.bbs.api.service.BbsNodeService;
import com.zjnbit.framework.web.exception.RequestError;
import com.zjnbit.framework.web.exception.RequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BbsNodeServiceImpl implements BbsNodeService {
    @Autowired
    BbsNodeGroupMapper bbsNodeGroupMapper;
    @Autowired
    BbsNodeMapper bbsNodeMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<BbsNodeGroupVo> listAllPublicNodes() {
        if (redisTemplate.hasKey(CacheKeyConst.BBS_NODE_ALL)) {
            List<BbsNodeGroupVo> result = (List<BbsNodeGroupVo>) redisTemplate.opsForValue().get(CacheKeyConst.BBS_NODE_ALL);
            return result;
        }
        //获取所有NodeGroup
        List<BbsNodeGroupEntity> groupEntityList = bbsNodeGroupMapper.selectList(new LambdaQueryWrapper<>());
        if (CollUtil.isEmpty(groupEntityList)) {
            return new ArrayList<>();
        }
        List<BbsNodeGroupVo> result = groupEntityList.stream().map(groupEntity -> {
            BbsNodeGroupVo groupVo = new BbsNodeGroupVo();
            BeanUtil.copyProperties(groupEntity, groupVo);
            return groupVo;
        }).collect(Collectors.toList());
        //获取NodeGroup下的Node集合
        List<BbsNodeEntity> nodeEntityList = bbsNodeMapper.selectList(new LambdaQueryWrapper<BbsNodeEntity>().in(BbsNodeEntity::getNodeGroupId, result.stream().map(BbsNodeGroupVo::getId).collect(Collectors.toList())).eq(BbsNodeEntity::getIsShow, true).orderByAsc(BbsNodeEntity::getSort));
        List<BbsNodeVo> parentNodeList = nodeEntityList.stream().filter(parentNodeEntity -> parentNodeEntity.getParentId().equals(0L)).map(parentNodeEntity -> {
            BbsNodeVo bbsNodeVo = new BbsNodeVo();
            BeanUtil.copyProperties(parentNodeEntity, bbsNodeVo);
            return bbsNodeVo;
        }).collect(Collectors.toList());
        for (BbsNodeVo parentNode : parentNodeList) {
            List<BbsNodeVo> childNodeList = new ArrayList<>();
            for (BbsNodeEntity nodeEntity : nodeEntityList) {
                if (parentNode.getId().equals(nodeEntity.getParentId())) {
                    BbsNodeVo childNode = new BbsNodeVo();
                    BeanUtil.copyProperties(nodeEntity, childNode);
                    childNodeList.add(childNode);
                }
            }
            parentNode.setChildNodeList(childNodeList);
        }
        for (BbsNodeGroupVo groupVo : result) {
            List<BbsNodeVo> groupNodeList = new ArrayList<>();
            for (BbsNodeVo parentNode : parentNodeList) {
                if (groupVo.getId().equals(parentNode.getNodeGroupId())) {
                    groupNodeList.add(parentNode);
                }
            }
            groupVo.setNodeList(groupNodeList);
        }
        redisTemplate.opsForValue().set(CacheKeyConst.BBS_NODE_ALL, result);
        return result;
    }

}
