package com.zjnbit.bbs.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjnbit.bbs.api.model.entity.BbsAttachEntity;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/11 16:50
 * @Description ${description}
 **/
@Mapper
public interface BbsAttachMapper extends BaseMapper<BbsAttachEntity> {
    int batchInsert(@Param("list") List<BbsAttachEntity> list);
}