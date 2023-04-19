package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.framework.constant.CacheKeyConst;
import com.zjnbit.bbs.api.mapper.BbsNodeGroupMapper;
import com.zjnbit.bbs.api.mapper.BbsNodeMapper;
import com.zjnbit.bbs.api.model.entity.BbsNodeEntity;
import com.zjnbit.bbs.api.model.entity.BbsNodeGroupEntity;
import com.zjnbit.bbs.api.model.vo.BbsNodeGroupVo;
import com.zjnbit.bbs.api.model.vo.BbsNodeVo;
import com.zjnbit.bbs.api.model.vo.BbsNodeVoOld;
import com.zjnbit.bbs.api.service.BbsNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
    public List<BbsNodeVo> listNodesByUserId(Long userId) {
        //todo 获取用户权限，根据权限选择是否展示所有，暂时不做
        //获取用户角色

//        if (redisTemplate.hasKey(CacheKeyConst.BBS_NODE_ALL)) {
//            List<BbsNodeVo> result=
//        }
        return null;
    }

    @Override
    public List<BbsNodeGroupVo> listAllPublicNodesOld() {
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
        redisTemplate.opsForValue().set(CacheKeyConst.BBS_NODE_ALL, result);
        return result;
    }

}
