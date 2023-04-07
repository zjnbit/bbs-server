package com.zjnbit.bbs.api.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zjnbit.bbs.api.mapper.BbsSysDictMapper;
import com.zjnbit.bbs.api.model.dto.BaseDictDto;
import com.zjnbit.bbs.api.model.dto.BaseDictItemDto;
import com.zjnbit.bbs.api.model.entity.BbsSysDictEntity;
import com.zjnbit.bbs.api.service.BaseDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BaseDictServiceImpl implements BaseDictService {
    private static final String CACHE_KEY = "dict:";

    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    BbsSysDictMapper bbsSysDictMapper;

    @Override
    public List<BaseDictDto> initSysDict() {
        List<BaseDictDto> baseDictDtoList = new ArrayList<>();
        List<BbsSysDictEntity> dictEntityList = bbsSysDictMapper.selectList(new LambdaQueryWrapper<>());
        if (CollUtil.isNotEmpty(dictEntityList)) {
            List<String> dictNameList = dictEntityList.stream().map(BbsSysDictEntity::getDictName).distinct().collect(Collectors.toList());
            baseDictDtoList = dictNameList.stream().map(dictName -> {
                BaseDictDto baseDictDto = new BaseDictDto();
                baseDictDto.setName(dictName);
                List<BaseDictItemDto> baseDictItemDtoList = new ArrayList<>();
                dictEntityList.forEach(dictEntity -> {
                    if (dictName.equals(dictEntity.getDictName())) {
                        BaseDictItemDto baseDictItemDto = new BaseDictItemDto();
                        baseDictItemDto.setKey(dictEntity.getDictKey());
                        baseDictItemDto.setValue(dictEntity.getDictValue());
                        baseDictItemDtoList.add(baseDictItemDto);
                    }
                });
                baseDictDto.setItems(baseDictItemDtoList);
                return baseDictDto;
            }).collect(Collectors.toList());
        }
        baseDictDtoList.forEach(baseDictDto -> {
            Set<String> keys = redisTemplate.opsForHash().keys(CACHE_KEY + baseDictDto.getName());
            keys.forEach(key -> {
                redisTemplate.opsForHash().delete(CACHE_KEY + baseDictDto.getName(), key);
            });
            if (CollUtil.isNotEmpty(baseDictDto.getItems())) {
                baseDictDto.getItems().forEach(baseDictItemDto -> {
                    redisTemplate.opsForHash().put(CACHE_KEY + baseDictDto.getName(), baseDictItemDto.getKey(), baseDictItemDto.getValue());
                });
            }
        });
        return baseDictDtoList;
    }

    @Override
    public List<BaseDictDto> listSysDict() {
        List<BaseDictDto> baseDictDtoList = new ArrayList<>();
        Set<String> names = redisTemplate.keys(CACHE_KEY + "*");
        if (names.isEmpty()) {
            return initSysDict();
        } else {
            names.forEach(name -> {
                Set<String> keys = redisTemplate.opsForHash().keys(name);
                if (!keys.isEmpty()) {
                    BaseDictDto baseDictDto = new BaseDictDto();
                    baseDictDto.setName(name.replace(CACHE_KEY, ""));
                    List<BaseDictItemDto> baseDictItemDtoList = new ArrayList<>();
                    keys.forEach(key -> {
                        BaseDictItemDto baseDictItemDto = new BaseDictItemDto();
                        baseDictItemDto.setKey(key);
                        baseDictItemDto.setValue((String) redisTemplate.opsForHash().get(name, key));
                        baseDictItemDtoList.add(baseDictItemDto);
                    });
                    baseDictDto.setItems(baseDictItemDtoList);
                    baseDictDtoList.add(baseDictDto);
                }
            });
        }
        return baseDictDtoList;
    }

    @Override
    public BaseDictDto getSysDict(String name) {
        BaseDictDto baseDictDto = new BaseDictDto();
        Set<String> keys = redisTemplate.opsForHash().keys(CACHE_KEY + name);
        if (!keys.isEmpty()) {
            baseDictDto.setName(name);
            List<BaseDictItemDto> baseDictItemDtoList = new ArrayList<>();
            keys.forEach(key -> {
                BaseDictItemDto baseDictItemDto = new BaseDictItemDto();
                baseDictItemDto.setKey(key);
                baseDictItemDto.setValue((String) redisTemplate.opsForHash().get(CACHE_KEY + name, key));
                baseDictItemDtoList.add(baseDictItemDto);
            });
            baseDictDto.setItems(baseDictItemDtoList);
        } else {
            List<BaseDictDto> baseDictDtoList = initSysDict();
            for (BaseDictDto dto : baseDictDtoList) {
                if (dto.getName().equals(name)) {
                    baseDictDto = dto;
                }
            }
        }
        return baseDictDto;
    }

    @Override
    public BaseDictItemDto getSysDictItem(String name, String key) {
        if (redisTemplate.opsForHash().hasKey(CACHE_KEY + name, key)) {
            String value = (String) redisTemplate.opsForHash().get(CACHE_KEY + name, key);
            BaseDictItemDto itemDto = new BaseDictItemDto();
            itemDto.setKey(key);
            itemDto.setValue(value);
            return itemDto;
        } else {
            BaseDictItemDto itemDto = new BaseDictItemDto();
            List<BaseDictDto> baseDictDtoList = initSysDict();
            for (BaseDictDto dto : baseDictDtoList) {
                if (dto.getName().equals(name)) {
                    for (BaseDictItemDto item : dto.getItems()) {
                        if (item.getKey().equals(key)) {
                            itemDto.setKey(key);
                            itemDto.setValue(item.getValue());
                        }
                    }
                }
            }
            return itemDto;
        }
    }

    @Override
    public String getValue(String name, String key) {
        return getSysDictItem(name, key).getValue();
    }
}
