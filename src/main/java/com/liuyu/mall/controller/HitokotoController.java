package com.liuyu.mall.controller;

import com.liuyu.mall.domain.Hitokoto;
import com.liuyu.mall.service.HitokotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/hitokoto")
@Api(value = "HitokotoController|一言")
public class HitokotoController{

    @Resource
    private HitokotoService hitokotoService;

    @Resource
    Hitokoto hitokoto;

    @RequestMapping(value = "/getHitokotoById", method = RequestMethod.POST)
    @ApiOperation(value = "根据ID获取一言", notes = "ID为必定提交的值，不为空")
    @ApiImplicitParam(paramType = "query", name = "Id", value = "一言id", required = true, dataType = "string")
    public String getHitokotoById(@RequestParam String Id){
        hitokoto = hitokotoService.getData(Id);
        System.out.println(hitokoto.getHitokoto());
        return hitokoto.getHitokoto();
    }


}
