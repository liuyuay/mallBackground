package com.liuyu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyu.mall.domain.Hitokoto;
import org.apache.ibatis.annotations.Param;

public interface HitokotoService extends IService<Hitokoto> {

    /**
     * 通过ID获取一言
     *
     * @param id 传入ID获取一言
     * @return Hitokoto
     */
    Hitokoto getData(@Param("id") String id);

    String getHitokoto();
}
