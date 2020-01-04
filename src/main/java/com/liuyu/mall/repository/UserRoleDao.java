package com.liuyu.mall.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuyu.mall.domain.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author liuyu
 */
public interface UserRoleDao extends BaseMapper<UserRole> {

    /**
     * 通过userId查找用户角色
     *
     * @param userId 用户Id
     * @return List<Role>
     */
    List<UserRole> selectListByUserId(@Param("userId") String userId);
}
