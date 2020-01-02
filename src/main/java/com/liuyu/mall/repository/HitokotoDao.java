package com.liuyu.mall.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyu.mall.domain.Hitokoto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface HitokotoDao extends BaseMapper<Hitokoto> {

    Hitokoto findAllData(@Param("id") String id);
}
