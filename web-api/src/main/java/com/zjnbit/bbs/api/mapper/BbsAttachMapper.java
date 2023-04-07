package com.zjnbit.bbs.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjnbit.bbs.api.model.entity.BbsAttachEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BbsAttachMapper extends BaseMapper<BbsAttachEntity> {
    int batchInsert(@Param("list") List<BbsAttachEntity> list);
}