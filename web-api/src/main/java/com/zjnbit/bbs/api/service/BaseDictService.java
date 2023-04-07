package com.zjnbit.bbs.api.service;

import com.zjnbit.bbs.api.model.dto.BaseDictDto;
import com.zjnbit.bbs.api.model.dto.BaseDictItemDto;

import java.util.List;

public interface BaseDictService {
    List<BaseDictDto> initSysDict();

    List<BaseDictDto> listSysDict();

    BaseDictDto getSysDict(String name);

    BaseDictItemDto getSysDictItem(String name, String key);

    String getValue(String name, String key);
}
