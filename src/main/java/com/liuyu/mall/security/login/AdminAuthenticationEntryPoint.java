package com.liuyu.mall.security.login;

import com.liuyu.mall.utils.ResponseUtils;
import com.liuyu.mall.utils.dto.output.ApiResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这里是认证权限入口 - 未登录的情况下访问所有接口都会拦截到此(除了放行忽略接口)
 * 前后端分离情况下返回json格式数据
 * @author liuyu
 * @date 2020-01-04
 */
@Component
public class AdminAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        ResponseUtils.out(response, ApiResult.fail("您未登录！！！"));
    }
}
