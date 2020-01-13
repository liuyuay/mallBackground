package com.liuyu.mall.controller;


import com.liuyu.mall.domain.User;
import com.liuyu.mall.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 一个用来测试swagger注解的控制器
 * 注意@ApiImplicitParam的使用会影响程序运行，如果使用不当可能造成控制器收不到消息
 *
 * @author liuyu
 */
@RestController
@RequestMapping
@Api(value = "SwaggerController|一个用来测试Swagger2的控制器")
public class SwaggerController {

    @RequestMapping(value = "/getUserName", method = RequestMethod.POST)
    @ApiOperation(value = "根据编号获取英雄姓名", notes = "只有1和2能返回正确的英雄姓名")
//    @ApiImplicitParam(paramType = "query", name = "user", value = "英雄编号", required = true, dataType = "User")
    public Result getUserName(@RequestBody User user){
        Result result = new Result();
        if(user.getUsername().equals("1")){
            result.setResult("貂蝉");
            result.setMessage("成功!");
            result.setSuccess(true);
            result.setCode(200);
            return result;
        }else if(user.getUsername().equals("2")){
            result.setResult("露娜");
            result.setMessage("成功!");
            result.setSuccess(true);
            result.setCode(200);
            return result;
        }else{
            result.setResult("null");
            result.setMessage("没有该英雄!");
            result.setSuccess(false);
            result.setCode(400);
            return result;
        }
    }

    @RequestMapping(value = "/updatePassword", method = RequestMethod.PUT)
    @ApiOperation(value = "修改用户密码", notes = "根据Id和旧密码修改密码")
    @ApiImplicitParams({
        @ApiImplicitParam(paramType = "query", name = "userId", value = "英雄编号", required = true, dataType = "int"),
        @ApiImplicitParam(paramType = "query", name = "oldPassword", value = "旧密码", required = true, dataType = "String"),
        @ApiImplicitParam(paramType = "query", name = "newPassword", value = "新密码", required = true, dataType = "String")
    })
    public String updatePassword(@RequestParam(value = "userId") Integer userId,
                                 @RequestParam(value = "oldPassword") String oldPassword,
                                 @RequestParam(value = "newPassword") String newPassword){
        if(userId <= 0 || userId >2){
            return "该英雄不存在";
        }
        if(StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)){
            if(StringUtils.isEmpty(oldPassword)){
                return "旧密码不能为空";
            }else if(StringUtils.isEmpty(newPassword)){
                return "新密码不能为空";
            }
        }
        if(oldPassword.equals(newPassword)){
            return "新密码不能和旧密码相同";
        }
        return "修改成功";
    }
}
