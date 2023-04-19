package com.zjnbit.bbs.api.service;

import java.util.List;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/18 15:52
 * @Description
 **/
public interface CommonUserService {

    /**
     * 根据用户ID获取拥有的角色roleCode
     * @param userId
     * @return
     */
    List<String> getRoleCodesByUserId(Long userId);
}
