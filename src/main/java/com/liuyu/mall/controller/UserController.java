package com.liuyu.mall.controller;

import com.liuyu.mall.domain.User;
import com.liuyu.mall.service.impl.UserServiceImpl;
import com.liuyu.mall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author liuyu
 */
@RestController
@Api(value = "用户用户登录以及注册")
public class UserController {

    @Resource
    UserServiceImpl userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "注册-添加用户", notes = "a")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户登录名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "showname", value = "用户显示名", required = true, dataType = "String")
    })
    public Result register(@RequestParam String username,@RequestParam String password,@RequestParam String showname){
        Result result = new Result();
        userService.register(username,password,showname,result);
        return result;
    }
}
