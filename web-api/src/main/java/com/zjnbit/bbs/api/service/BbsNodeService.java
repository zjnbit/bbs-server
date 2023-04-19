package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.vo.BbsNodeGroupVo;
import com.zjnbit.bbs.api.model.vo.BbsNodeVo;

import java.util.List;

public interface BbsNodeService {

    /**
     * 查询所有公开节点
     *
     * @author 非羽Army
     **/
    List<BbsNodeGroupVo> listAllPublicNodesOld();

    /**
     * 查询节点
     * @return
     */
    List<BbsNodeVo> listNodesByUserId(Long userId);
}
