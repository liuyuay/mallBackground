package com.liuyu.mall.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyu.mall.domain.User;
import com.liuyu.mall.utils.dto.input.UserQueryPara;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao extends BaseMapper<User> {

//    /**
//     * 列表分页
//     *
//     * @param page
//     * @param filter
//     * @return
//     */
//    List<User> selectUsers(Pagination page, @Param("filter") UserQueryPara filter);
//
//    /**
//     * 列表
//     *
//     * @param filter
//     * @return
//     */
//    List<User> selectUsers(@Param("filter") UserQueryPara filter);
//
//    /**
//     * 通过账号查找用户信息
//     *
//     * @param username:
//     * @return User
//     */
//    User selectUserByUsername(@Param("username") String username);
//
//    /**
//     * 通过token查找用户信息
//     *
//     * @param token:
//     * @return User
//     */
//    User getUserInfoByToken(@Param("token") String token);
//
//    /**
//     * 通过qq_oppen_id查找用户信息
//     *
//     * @param qqOppenId:
//     * @return User
//     */
//    User getUserInfoByQQ(@Param("qq_oppen_id") String qqOppenId);
//
//    /**
//     * 通过角色ID查询用户集合
//     *
//     * @param roleId:
//     * @return java.util.List<Role>
//     */
//    List<User> selectUserByRoleId(@Param("roleId") Integer roleId);
}
