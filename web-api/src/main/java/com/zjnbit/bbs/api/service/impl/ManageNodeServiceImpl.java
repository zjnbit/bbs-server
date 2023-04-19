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
import com.zjnbit.bbs.api.model.vo.BbsNodeVoOld;
import com.zjnbit.bbs.api.service.ManageNodeService;
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
public class ManageNodeServiceImpl implements ManageNodeService {

    @Autowired
    BbsNodeGroupMapper bbsNodeGroupMapper;
    @Autowired
    BbsNodeMapper bbsNodeMapper;
    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public List<BbsNodeGroupVo> listAllNodes() {
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
        List<BbsNodeVoOld> parentNodeList = nodeEntityList.stream().filter(parentNodeEntity -> parentNodeEntity.getParentId().equals(0L)).map(parentNodeEntity -> {
            BbsNodeVoOld bbsNodeVoOld = new BbsNodeVoOld();
            BeanUtil.copyProperties(parentNodeEntity, bbsNodeVoOld);
            return bbsNodeVoOld;
        }).collect(Collectors.toList());
        for (BbsNodeVoOld parentNode : parentNodeList) {
            List<BbsNodeVoOld> childNodeList = new ArrayList<>();
            for (BbsNodeEntity nodeEntity : nodeEntityList) {
                if (parentNode.getId().equals(nodeEntity.getParentId())) {
                    BbsNodeVoOld childNode = new BbsNodeVoOld();
                    BeanUtil.copyProperties(nodeEntity, childNode);
                    childNodeList.add(childNode);
                }
            }
            parentNode.setChildNodeList(childNodeList);
        }
        for (BbsNodeGroupVo groupVo : result) {
            List<BbsNodeVoOld> groupNodeList = new ArrayList<>();
            for (BbsNodeVoOld parentNode : parentNodeList) {
                if (groupVo.getId().equals(parentNode.getNodeGroupId())) {
                    groupNodeList.add(parentNode);
                }
            }
            groupVo.setNodeList(groupNodeList);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addNodeGroup(BbsNodeGroupParam data) {
        List<BbsNodeGroupEntity> nodeGroupEntityList = bbsNodeGroupMapper.selectList(new LambdaQueryWrapper<BbsNodeGroupEntity>().eq(BbsNodeGroupEntity::getGroupName, data.getGroupName()));
        if (CollUtil.isNotEmpty(nodeGroupEntityList)) {
            throw new RequestException(RequestError.A0004);
        }
        BbsNodeGroupEntity bbsNodeGroupEntity = new BbsNodeGroupEntity();
        BeanUtil.copyProperties(data, bbsNodeGroupEntity);
        bbsNodeGroupMapper.insert(bbsNodeGroupEntity);
        if (redisTemplate.hasKey(CacheKeyConst.BBS_NODE_ALL)) {
            redisTemplate.delete(CacheKeyConst.BBS_NODE_ALL);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateNodeGroup(BbsNodeGroupParam data) {
        List<BbsNodeGroupEntity> nodeGroupEntityList = bbsNodeGroupMapper.selectList(new LambdaQueryWrapper<BbsNodeGroupEntity>().eq(BbsNodeGroupEntity::getId, data.getId()));
        if (CollUtil.isEmpty(nodeGroupEntityList)) {
            throw new RequestException(RequestError.A0002);
        }
        nodeGroupEntityList = bbsNodeGroupMapper.selectList(new LambdaQueryWrapper<BbsNodeGroupEntity>().eq(BbsNodeGroupEntity::getGroupName, data.getGroupName()));
        if (CollUtil.isNotEmpty(nodeGroupEntityList)) {
            if (nodeGroupEntityList.get(0).getId().equals(data.getId())) {
                return;
            } else {
                throw new RequestException(RequestError.A0004);
            }
        }
        BbsNodeGroupEntity updateEntity = new BbsNodeGroupEntity();
        BeanUtil.copyProperties(data, updateEntity);
        bbsNodeGroupMapper.updateById(updateEntity);
        if (redisTemplate.hasKey(CacheKeyConst.BBS_NODE_ALL)) {
            redisTemplate.delete(CacheKeyConst.BBS_NODE_ALL);
        }
    }

    @Override
    public void remove(Long id) {
        List<BbsNodeGroupEntity> nodeGroupEntityList = bbsNodeGroupMapper.selectList(new LambdaQueryWrapper<BbsNodeGroupEntity>().eq(BbsNodeGroupEntity::getId, id));
        if (CollUtil.isEmpty(nodeGroupEntityList)) {
            throw new RequestException(RequestError.A0002);
        }
        bbsNodeGroupMapper.deleteById(id);
        if (redisTemplate.hasKey(CacheKeyConst.BBS_NODE_ALL)) {
            redisTemplate.delete(CacheKeyConst.BBS_NODE_ALL);
        }
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
