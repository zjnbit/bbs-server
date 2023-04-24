package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.framework.constant.CacheKeyConst;
import com.zjnbit.bbs.api.mapper.BbsNodeGroupMapper;
import com.zjnbit.bbs.api.mapper.BbsNodeMapper;
import com.zjnbit.bbs.api.model.entity.BbsNodeEntity;
import com.zjnbit.bbs.api.model.vo.BbsNodeVo;
import com.zjnbit.bbs.api.service.BbsNodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<BbsNodeVo> listAllNodes() {
        List<BbsNodeVo> result = new ArrayList<>();
        if (redisTemplate.hasKey(CacheKeyConst.BBS_NODE_ALL)) {
            result = (List<BbsNodeVo>) redisTemplate.opsForValue().get(CacheKeyConst.BBS_NODE_ALL);
        } else {
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
            redisTemplate.opsForValue().set(CacheKeyConst.BBS_NODE_ALL, result);
        }
        return result;
    }
}
