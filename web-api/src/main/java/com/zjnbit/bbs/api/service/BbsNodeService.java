package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.vo.BbsNodeGroupVo;

import java.util.List;

public interface BbsNodeService {

    /**
     * 查询所有公开节点
     *
     * @author 非羽Army
     **/
    List<BbsNodeGroupVo> listAllPublicNodes();

}
