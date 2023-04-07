package com.zjnbit.bbs.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjnbit.bbs.api.model.entity.LogRequestEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LogRequestMapper extends BaseMapper<LogRequestEntity> {
    int batchInsert(@Param("list") List<LogRequestEntity> list);
}