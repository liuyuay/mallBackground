package com.liuyu.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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

//    private CorsConfiguration buildConfig() {
//        CorsConfiguration corsConfiguration = new CorsConfiguration();
//        corsConfiguration.addAllowedOrigin("*");
//        corsConfiguration.addAllowedHeader("*");
//        corsConfiguration.addAllowedMethod("*");
//        corsConfiguration.addExposedHeader(HttpHeaderConStant.X_TOTAL_COUNT);
//        return corsConfiguration;
//    }
//
//    /**
//     * 跨域过滤器
//     *
//     * @return
//     */
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", buildConfig()); // 4
//        return new CorsFilter(source);
//    }

}
