package com.liuyu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyu.mall.domain.User;
import org.apache.ibatis.annotations.Param;

public interface UserService extends IService<User> {

    User login(@Param("name") String name);
}
