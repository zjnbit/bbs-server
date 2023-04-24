package com.zjnbit.bbs.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjnbit.bbs.api.model.entity.BbsNodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/24 23:02
 * @Description ${description}
 **/
@Mapper
public interface BbsNodeMapper extends BaseMapper<BbsNodeEntity> {
    int batchInsert(@Param("list") List<BbsNodeEntity> list);
}