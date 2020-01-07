package com.liuyu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyu.mall.domain.User;
import com.liuyu.mall.utils.Result;
import org.apache.ibatis.annotations.Param;

public interface UserService extends IService<User> {

    /**
     * 用户用户登录
     *
     * @param name 用户登录名
     * @return User 用户实体
     * */
    User login(@Param("name") String name);

    /**
     * 用户用户登录
     *
     * @param username 用户登录名
     * @param password 用户密码
     * @param showname 用户昵称
     * @return User 用户实体
     * */
    User register(@Param("name") String username,@Param("name") String password,@Param("name") String showname, Result result);
}
