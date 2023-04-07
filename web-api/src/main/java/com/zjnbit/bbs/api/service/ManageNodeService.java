package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.param.BbsNodeGroupParam;
import com.zjnbit.bbs.api.model.param.BbsNodeParam;
import com.zjnbit.bbs.api.model.vo.BbsNodeGroupVo;

import java.util.List;

public interface ManageNodeService {

    /**
     * 查询所有节点
     *
     * @author 非羽Army
     **/
    List<BbsNodeGroupVo> listAllNodes();

    /**
     * 添加节点分组
     *
     * @param data
     */
    void addNodeGroup(BbsNodeGroupParam data);

    /**
     * 修改节点分组
     *
     * @param data
     */
    void updateNodeGroup(BbsNodeGroupParam data);

    /**
     * 删除节点分组
     *
     * @param id
     */
    void remove(Long id);

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
