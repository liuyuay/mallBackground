package com.liuyu.mall.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liuyu.mall.domain.Hitokoto;
import com.liuyu.mall.service.HitokotoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping
@Api(value = "HitokotoController|一言")
public class HitokotoController{

    @Resource
    private HitokotoService hitokotoService;

    @Resource
    Hitokoto hitokoto;

    @Resource
    RedisTemplate<String, Object> redis;

    @RequestMapping(value = "/getHitokotoById", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID获取一言", notes = "ID为必定提交的值，不为空")
    @ApiImplicitParam(paramType = "query", name = "Id", value = "一言id", required = true, dataType = "string")
    public String getHitokotoById(@RequestParam String Id){
        hitokoto = hitokotoService.getData(Id);
        Object object = JSONArray.toJSON(hitokoto);
        String json = object.toString();
        System.out.println(json);
        return json;
    }

    @RequestMapping(value = "/getHitokotoByRedis", method = RequestMethod.GET)
    @ApiOperation(value = "根据ID获取一言", notes = "ID为必定提交的值，不为空")
    @ApiImplicitParam(paramType = "query", name = "Id", value = "一言id", required = true, dataType = "string")
    public String getHitokotoByRedis(@RequestParam String Id){
        hitokoto = (Hitokoto) redis.opsForValue().get(Id);
        if(hitokoto == null){
            hitokoto = hitokotoService.getData(Id);
            redis.opsForValue().set(hitokoto.getId(),hitokoto);
        }
        Object object = JSONArray.toJSON(hitokoto);
        String json = object.toString();
        System.out.println(json);
        return json;
    }


}
