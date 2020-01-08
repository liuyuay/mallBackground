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
import java.io.Serializable;

/**
 * 对用户进行操作
 *
 * @author liuyu
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User register(String username, String password, String showname, Result result) {
        User user = new User();
        String encodePassword = PasswordUtils.encodePassword(password, Constants.SALT);
        user.setId(Constants.createUuid());
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

    @Override
    public Serializable updateInfo(Serializable id, String password, String showName, Result result) {
        User user = userDao.selectById(id);
        Serializable userId = "";
        if(user == null){
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("用户不存在");
            result.setResult("");
        }else{
            String encodePassword = PasswordUtils.encodePassword(password, Constants.SALT);
            user.setShowname(showName);
            user.setPassword(encodePassword);
            userId = userDao.updateById(user);
            result.setSuccess(true);
            result.setCode(200);
            result.setMessage("修改成功");
            result.setResult(user);
        }
        return userId;
    }

    @Override
    public User getUserInfo(Serializable id, Result result) {
        User user = userDao.selectById(id);
        if(user == null){
            result.setSuccess(false);
            result.setCode(500);
            result.setMessage("系统错误！");
            result.setResult("");
        }else{
            result.setSuccess(true);
            result.setCode(200);
            result.setMessage("查询个人信息成功");
            result.setResult(user);
        }
        return user;
    }

    @Override
    public Serializable deleteUser(Serializable id, Result result) {
        Serializable userId = userDao.deleteById(id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage("删除成功");
        result.setResult("");
        return userId;
    }
}
