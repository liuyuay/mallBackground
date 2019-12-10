package com.liuyu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyu.mall.domain.Hitokoto;
import com.liuyu.mall.repository.HitokotoDao;
import com.liuyu.mall.service.HitokotoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HitokotoServiceImpl extends ServiceImpl<HitokotoDao, Hitokoto> implements HitokotoService {

    @Resource
    HitokotoDao hitokotoDao;

    @Override
    public Hitokoto getData(String id) {
        /**
         * 021BCD54B59045F3AED360D5091FB7C3
         * 076BA86AB3C24B30BD5804C936EE3C58
         * 09383AC60A66453DAA921083B3249E4F
         * 0AFE7F2C1DD847DCBDBC3A405A1B0F92
         *
         * 也可以使用这个方法取值
         *      return hitokotoDao.findAllDate(id)
         * */
        id = "076BA86AB3C24B30BD5804C936EE3C58";
//        QueryWrapper<Hitokoto> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id",id);
//        Hitokoto hitokoto = this.getOne(queryWrapper);
//        Hitokoto hitokoto = hitokotoDao.findAllData(id);
        Hitokoto hitokoto = hitokotoDao.selectById(id);
        return hitokoto;
    }

    @Override
    public String getHitokoto() {
        return "";
    }
}
