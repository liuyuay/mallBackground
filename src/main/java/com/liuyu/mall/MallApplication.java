package com.liuyu.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("com.liuyu.mall.repository")        //注解扫描mapper，这里添加了，就不用在dao中写@Mapper注解了
public class MallApplication {

    public static void main (String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
