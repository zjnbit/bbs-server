package com.zjnbit.bbs.api.api.bbs;

import com.zjnbit.bbs.api.model.vo.BbsNodeGroupVo;
import com.zjnbit.bbs.api.router.bbs.BbsNodeRouter;
import com.zjnbit.bbs.api.service.BbsNodeService;
import com.zjnbit.framework.web.api.BaseApi;
import com.zjnbit.framework.web.model.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NodeApi extends BaseApi<BbsNodeService> {

    /**
     * 获取所有节点
     *
     * @author 非羽Army
     **/
    @GetMapping(value = BbsNodeRouter.ALL)
    public Result<List<BbsNodeGroupVo>> all() {
        return success(baseService.listAllPublicNodes());
    }


}
