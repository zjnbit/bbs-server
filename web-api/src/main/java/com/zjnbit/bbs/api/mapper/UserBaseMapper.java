package com.zjnbit.bbs.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjnbit.bbs.api.model.entity.UserBaseEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserBaseMapper extends BaseMapper<UserBaseEntity> {
}