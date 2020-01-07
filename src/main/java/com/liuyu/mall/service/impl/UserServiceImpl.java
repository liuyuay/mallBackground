package com.liuyu.mall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuyu.mall.config.Constants;
import com.liuyu.mall.domain.User;
import com.liuyu.mall.repository.UserDao;
import com.liuyu.mall.service.UserService;
import com.liuyu.mall.utils.PasswordUtils;
import com.liuyu.mall.utils.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liuyu
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User login(String name) {
        return null;
    }

    @Override
    public User register(String username, String password, String showname, Result result) {
        User user = new User();
        String encodePassword = PasswordUtils.encodePassword(password, Constants.SALT);
        user.setId(Constants.CreateUUID());
        user.setUsername(username);
        user.setPassword(encodePassword);
        user.setShowname(showname);
        user.setSalt(Constants.SALT);
        int count = userDao.insert(user);
        if (count != 0){
            result.setSuccess(true);
            result.setCode(200);
            result.setMessage("注册成功");
            result.setResult(user);
        }else{
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("注册失败");
            result.setResult(user);
        }
        return user;
    }
}
