package com.zjnbit.bbs.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zjnbit.bbs.api.model.entity.BbsNodeGroupEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 陈俊羽
 * @emp chenjunyu1 211100011
 * @date 2023/4/18 15:13
 * @Description ${description}
 **/
@Mapper
public interface BbsNodeGroupMapper extends BaseMapper<BbsNodeGroupEntity> {
    int batchInsert(@Param("list") List<BbsNodeGroupEntity> list);
}