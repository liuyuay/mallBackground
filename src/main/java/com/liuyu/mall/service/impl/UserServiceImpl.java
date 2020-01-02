package com.liuyu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyu.mall.domain.User;
import com.liuyu.mall.repository.UserDao;
import com.liuyu.mall.service.UserService;

import javax.annotation.Resource;

public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User login(String name) {
        return null;
    }
}
