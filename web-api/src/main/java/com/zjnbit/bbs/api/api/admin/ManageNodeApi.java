package com.zjnbit.bbs.api.api.admin;

import com.zjnbit.bbs.api.model.param.BbsNodeParam;
import com.zjnbit.bbs.api.model.vo.BbsNodeVo;
import com.zjnbit.bbs.api.router.manage.ManageNodeRouter;
import com.zjnbit.bbs.api.service.ManageNodeService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManageNodeApi extends BaseApi<ManageNodeService> {

    /**
     * 获取所有节点
     *
     * @author 非羽Army
     **/
    @GetMapping(value = ManageNodeRouter.ALL)
    public Result<List<BbsNodeVo>> all() {
        return success(baseService.listAllNodes());
    }

    /**
     * 添加节点
     *
     * @author 非羽Army
     **/
    @PostMapping(value = ManageNodeRouter.ADD)
    public Result<String> add(@RequestBody BbsNodeParam param) {
        baseService.add(param);
        return success();
    }

    /**
     * 修改节点
     *
     * @author 非羽Army
     **/
    @PostMapping(value = ManageNodeRouter.UPDATE)
    public Result<String> update(@RequestBody BbsNodeParam param) {
        baseService.update(param);
        return success();
    }

}
