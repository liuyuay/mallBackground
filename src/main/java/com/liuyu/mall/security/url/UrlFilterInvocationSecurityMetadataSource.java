package com.liuyu.mall.security.url;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liuyu.mall.config.Constants;
import com.liuyu.mall.config.MyProperties;
import com.liuyu.mall.domain.Permission;
import com.liuyu.mall.domain.Role;
import com.liuyu.mall.domain.RolePermission;
import com.liuyu.mall.repository.PermissionDao;
import com.liuyu.mall.repository.RoleDao;
import com.liuyu.mall.repository.RolePermissionDao;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 执行完之后到 `UrlAccessDecisionManager` 中认证权限
 * @author liuyu
 */
@Component
public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

    @Resource
    private RoleDao roleDao;

    @Resource
    private MyProperties myProperties;

    /**
     * 获取访问该url所需要的角色权限信息
     * @param object 储存请求url信息
     * @return null 标识不需要任何权限都可以访问
     * */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取当前请求url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // TODO 忽略url请放在此处进行过滤放行
        String loginUrl = "/login";

        for (String ignoreUrl : myProperties.getAuth().getIgnoreUrls()) {
            if (ignoreUrl.equals(requestUrl)){
                return null;
            }
        }

//        if (loginUrl.equals(requestUrl) || requestUrl.contains("logout") || requestUrl.contains("/register")) {
//            return null;
//        }

        if (requestUrl.contains("/login") || requestUrl.contains("/register")){
            return null;
        }
        // 数据库中所有url
        List<Permission> permissionList = permissionDao.selectList(null);
        for (Permission permission : permissionList) {
            // 获取该url所对应的权限
            if (requestUrl.equals(permission.getUrl())) {
                List<RolePermission> permissions = rolePermissionDao.selectList(new QueryWrapper<RolePermission>().eq("permissionId", permission.getId()));
                List<String> roles = new LinkedList<>();
                if (!CollectionUtils.isEmpty(permissions)){
                    String roleId = permissions.get(0).getRoleid();
                    Role role = roleDao.selectById(roleId);
                    roles.add(role.getCode());
                }
                // 保存该url对应角色权限信息
                return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
            }
        }
        // 如果数据中没有找到相应url资源则为非法访问，要求用户登录再进行操作
        return SecurityConfig.createList(Constants.ROLE_LOGIN);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
