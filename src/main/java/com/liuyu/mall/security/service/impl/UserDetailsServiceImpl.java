package com.liuyu.mall.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyu.mall.domain.Role;
import com.liuyu.mall.domain.User;
import com.liuyu.mall.domain.UserRole;
import com.liuyu.mall.repository.RoleDao;
import com.liuyu.mall.repository.UserDao;
import com.liuyu.mall.repository.UserRoleDao;
import com.liuyu.mall.security.dto.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author liuyu
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private RoleDao roleDao;

    /***
     * 根据账号获取用户信息
     * @param username 用户名
     * @return org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 从数据库中取出用户信息
        List<User> userList = userDao.selectList(new QueryWrapper<User>().eq("username", username));
        User user;
        // 判断用户是否存在
        if (!CollectionUtils.isEmpty(userList)) {
            user = userList.get(0);
        } else {
            throw new UsernameNotFoundException("用户名不存在！");
        }
        // 返回UserDetails实现类
        return new SecurityUser(user, getUserRoles(user.getId()));
    }

    /***
     * 根据token获取用户权限与基本信息
     *
     * @param token token-从前台传过来的用户认证的值
     * @return a
     */
    public SecurityUser getUserByToken(String token) {
        User user = null;
        List<User> loginList = userDao.selectList(new QueryWrapper<User>().eq("token", token));
        if (!CollectionUtils.isEmpty(loginList)) {
            user = loginList.get(0);
        }
        return user != null ? new SecurityUser(user, getUserRoles(user.getId())) : null;
    }

    /**
     * 根据用户id获取角色权限信息
     *
     * @param userId 用户Id
     * @return List<Role>
     */
    private List<Role> getUserRoles(String userId) {
        List<UserRole> userRoles = userRoleDao.selectList(new QueryWrapper<UserRole>().eq("userId", userId));
        List<Role> roleList = new LinkedList<>();
        for (UserRole userRole : userRoles) {
            Role role = roleDao.selectById(userRole.getRoleid());
            roleList.add(role);
        }
        return roleList;
    }

}
