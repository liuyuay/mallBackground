package com.liuyu.mall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyu.mall.domain.Hitokoto;
import com.liuyu.mall.repository.HitokotoDao;
import com.liuyu.mall.service.HitokotoService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class HitokotoServiceImpl extends ServiceImpl<HitokotoDao, Hitokoto> implements HitokotoService {

    @Resource
    HitokotoDao hitokotoDao;

    @Override
    @Cacheable(key = "#id", value = "Hitokoto")
    public Hitokoto getData(String id) {
        /**
         *
         * 076BA86AB3C24B30BD5804C936EE3C58
         * 09383AC60A66453DAA921083B3249E4F
         * 0AFE7F2C1DD847DCBDBC3A405A1B0F92
         * 0B7605471BC54D6B9478604695EAEA76
         * 0C9AD209BEF84F27B1195CC18687D8EF
         * 0D93ECC3E2B349F28F2A5332C799F5C2
         * 0DB0806EC9DF4DE482EA53D545510628
         * 0E1ED1DF0C344B098CB06CEDEEEC61F6
         * 0E8CC8FC77F143FFA2162D1661D2ED64
         * 0FACACCBF1B840DBAB7DC07EFABB70B6
         * 111ED60943154867B7285A9DAD6E4FF4
         * 1400843A318744DA936C74F402C30530
         * 158558093B324C76964067B3FF51C07D
         * 1598929221F44BB3A58339A50DC0AA94
         *
         * 也可以使用这个方法取值
         *      return hitokotoDao.findAllDate(id)
         * */
//        id = "076BA86AB3C24B30BD5804C936EE3C58";
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
