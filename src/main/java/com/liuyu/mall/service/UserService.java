package com.liuyu.mall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liuyu.mall.domain.User;
import com.liuyu.mall.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;

public interface UserService extends IService<User> {

    /**
     * 用户注册
     *
     * @param username 用户登录名
     * @param password 用户密码
     * @param showName 用户昵称
     * @param result 返回前台的数据对象
     * @return User 用户实体
     * */
    User register(@Param("username") String username,@Param("password") String password,@Param("showName") String showName, Result result);

    /**
     * 修改用户信息
     *
     * @param id 用户Id
     * @param password 用户密码
     * @param showName 用户昵称
     * @param result 返回前台的数据对象
     * @return Serializable 返回修改成功的用户Id
     * */
    Serializable updateInfo(@Param("id") Serializable id, @Param("password") String password, @Param("showName") String showName, Result result);

    /**
     * 查询用户信息
     *
     * @param id 用户Id
     * @param result 返回前台的数据对象
     * @return User 返回一个用户实体对象
     * */
    User getUserInfo(@Param("id") Serializable id, Result result);

    /**
     * 查询用户信息
     *
     * @param id 用户Id
     * @param result 返回前台的数据对象
     * @return Serializable 返回一个用户Id
     * */
    Serializable deleteUser(@Param("id") Serializable id, Result result);
}
