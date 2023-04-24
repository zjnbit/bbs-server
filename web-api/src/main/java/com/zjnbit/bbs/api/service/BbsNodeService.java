package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.vo.BbsNodeVo;

import java.util.List;

public interface BbsNodeService {

    /**
     * 查询节点
     *
     * @return
     */
    List<BbsNodeVo> listNodesByUserId(Long userId);

    List<BbsNodeVo> listAllNodes();
}
