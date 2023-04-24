package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.param.BbsNodeParam;
import com.zjnbit.bbs.api.model.vo.BbsNodeVo;

import java.util.List;

public interface ManageNodeService {

    /**
     * 查询所有节点
     *
     * @author 非羽Army
     **/
    List<BbsNodeVo> listAllNodes();


    /**
     * 添加节点
     *
     * @param data
     */
    void add(BbsNodeParam data);

    /**
     * 修改节点
     *
     * @param data
     */
    void update(BbsNodeParam data);

}
