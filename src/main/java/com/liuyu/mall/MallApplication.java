package com.liuyu.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuyu
 * @ MapperScan("com.liuyu.mall.repository") 注解扫描mapper，这里添加了，就不用在dao中写@Mapper注解了
 * 可以添加多个扫描路径，也可以用*代替包以扫描不同包下面的DAO
 */
@SpringBootApplication
@MapperScan("com.liuyu.mall.repository")
public class MallApplication {

    public static void main (String[] args) {
        SpringApplication.run(MallApplication.class, args);
    }

}
