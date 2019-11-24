package com.liuyu.mall.test.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {

    @RequestMapping("/hello")
    public String hello() {
        return "hello,this is a springboot demo by liuyu.";
    }

    @RequestMapping("/world")
    public String world() {
        return "hello,World.";
    }
}
