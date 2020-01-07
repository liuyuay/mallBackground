package com.liuyu.mall.controller;

import com.liuyu.mall.security.dto.SecurityUser;
import com.liuyu.mall.security.service.impl.UserDetailsServiceImpl;
import com.liuyu.mall.utils.dto.output.ApiResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 登录控制器
 * @author liuyu
 */
@RestController
@RequestMapping
public class LoginController {

    @Resource
    UserDetailsServiceImpl userDetailsService;

//    @Resource
//    SecurityUser securityUser;
//    @GetMapping("/")
//    public ModelAndView showHome() {
//        return new ModelAndView("home.html");
//    }

//    @GetMapping(value = "/login", produces = "application/json;charset=utf-8")
//    @ApiOperation(value = "登录系统", httpMethod = "GET", response = ApiResult.class)
//    public ApiResult login(@RequestParam String username,@RequestParam String password) {
//        securityUser = (SecurityUser)userDetailsService.loadUserByUsername(username);
//        return ApiResult.ok("登录系统成功", securityUser);
//    }

//    @GetMapping("/login")
//    public ModelAndView login() {
//        return new ModelAndView("login.html");
//    }

    @GetMapping("/home")
    public String home() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
//        log.info("登陆人：" + name);
        return "Hello~ " + name;
    }

    @GetMapping(value ="/admin")
    // 访问路径`/admin` 具有`ADMIN`角色权限   【这种是写死方式】
//    @PreAuthorize("hasPermission('/admin','ADMIN')")
    public String admin() {
        return "Hello~ 管理员";
    }

    @GetMapping("/test")
    public String test() {
        return "Hello~ 测试权限访问接口";
    }
}
