package com.liuyu.mall.controller;

import com.liuyu.mall.service.impl.UserServiceImpl;
import com.liuyu.mall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author liuyu
 */
@RestController
@Api(value = "用户用户登录以及注册")
public class UserController {

    @Resource
    UserServiceImpl userService;

    /**
     * RESTful API风格
     * GET ： 获取、读取资源
     * POST ： 添加资源
     * PUT ： 修改资源
     * DELETE ： 删除资源
     * */

    @PostMapping(value = "/user")
    @ApiOperation(value = "注册", notes = "添加用户")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "username", value = "用户登录名", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "showName", value = "用户显示名", required = true, dataType = "String")
    })
    public Result register(@RequestParam String username,@RequestParam String password,@RequestParam String showName){
        Result result = new Result();
        userService.register(username,password,showName,result);
        return result;
    }

    @PutMapping(value = "/user/{id}")
    @ApiOperation(value = "修改", notes = "更新用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "用户Id", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "password", value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "showName", value = "用户显示名", required = true, dataType = "String")
    })
    public Result updateUser(@PathVariable Serializable id, @RequestParam String password, @RequestParam String showName){
        Result result = new Result();
        userService.updateInfo(id, password, showName, result);
        return result;
    }

    @GetMapping(value = "/user/{id}")
    @ApiOperation(value = "查询", notes = "查询个人信息")
    @ApiImplicitParam(paramType = "query", name = "id", value = "用户Id", required = true, dataType = "String")
    public Result getUserInfo(@PathVariable Serializable id){
        Result result = new Result();
        userService.getUserInfo(id, result);
        return result;
    }

    @DeleteMapping(value = "/user/{id}")
    @ApiOperation(value = "删除", notes = "删除用户")
    @ApiImplicitParam(paramType = "query", name = "id", value = "用户Id", required = true, dataType = "String")
    public Result deleteUser(@PathVariable Serializable id){
        Result result = new Result();
        userService.deleteUser(id, result);
        return result;
    }
}
