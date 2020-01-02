package com.liuyu.mall.config;

import com.liuyu.mall.security.filter.AdminAuthenticationProcessingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.PrintWriter;

/**
 * @EnableWebSecurity 启用Spring Security的Web安全支持
 * @author liuyu
 * */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 用户密码校验过滤器
     */
    private final AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter;

    public SecurityConfig(AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter) {
        this.adminAuthenticationProcessingFilter = adminAuthenticationProcessingFilter;
    }

    /**
     * 权限配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.antMatcher("/**").authorizeRequests();

        // 禁用CSRF 开启跨域
        http.csrf().disable().cors();

        // 登录处理 - 前后端一体的情况下
//        registry.and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
//                // 自定义登陆用户名和密码属性名，默认为 username和password
//                .usernameParameter("username").passwordParameter("password")
//                // 异常处理
//                .failureUrl("/login/error").permitAll()
//                // 退出登录
//                .and().logout().permitAll();

        // 标识只能在 服务器本地ip[127.0.0.1或localhost] 访问`/home`接口，其他ip地址无法访问
        registry.antMatchers("/home").hasIpAddress("127.0.0.1");
        // 允许匿名的url - 可理解为放行接口 - 多个接口使用,分割
        registry.antMatchers("/login", "/index").permitAll();
        // OPTIONS(选项)：查找适用于一个特定网址资源的通讯选择。 在不需执行具体的涉及数据传输的动作情况下， 允许客户端来确定与资源相关的选项以及 / 或者要求， 或是一个服务器的性能
        registry.antMatchers(HttpMethod.OPTIONS, "/**").denyAll();
        // 自动登录 - cookie储存方式
        registry.and().rememberMe();
        // 其余所有请求都需要认证
        registry.anyRequest().authenticated();
        // 防止iframe 造成跨域
        registry.and().headers().frameOptions().disable();

        // 自定义过滤器认证用户名密码
        http.addFilterAt(adminAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class);
    }

    /**
     * 将用户设置在内存中
     * @param auth ay
     * @throws Exception ay
     */
    @Autowired
    public void config(AuthenticationManagerBuilder auth) throws Exception{
        // 在内存中配置用户，配置多个用户调用`and()`方法
        auth.inMemoryAuthentication()
                //指定加密方式
                .passwordEncoder(passwordEncoder())
                //管理员身份
                .withUser("liuyu").password(passwordEncoder().encode("liuyu0113")).roles("ADMIN")
                .and()
                //普通用户身份
                .withUser("liuyuay").password(passwordEncoder().encode("liuyuay0113")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        // BCryptPasswordEncoder：Spring Security 提供的加密工具，可快速实现加密加盐
        return new BCryptPasswordEncoder();
    }

    /**
     * 登录处理
     * @param http ay
     * @throws Exception ay
     * */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        //开启登录配置
//        http.authorizeRequests()
//            // 标识访问 `/index` 这个接口，需要具备`ADMIN`角色
//            .antMatchers("/index").hasRole("ADMIN")
//            // 允许匿名的url - 可理解为放行接口 - 多个接口使用,分割
//            .antMatchers("/", "/home").permitAll()
//            // 其余所有请求都需要认证
//            .anyRequest().authenticated()
//            .and()
//            // 设置登录认证页面
//            .formLogin().loginPage("/login")
//            // 登录成功后的处理接口 - 方式①
//            .loginProcessingUrl("/home")
//            // 自定义登陆用户名和密码属性名，默认为 username和password
//            .usernameParameter("username")
//            .passwordParameter("password")
//            // 登录成功后的处理器  - 方式②
////                .successHandler((req, resp, authentication) -> {
////                    resp.setContentType("application/json;charset=utf-8");
////                    PrintWriter out = resp.getWriter();
////                    out.write("登录成功...");
////                    out.flush();
////                })
//            // 配置登录失败的回调
//            .failureHandler((req, resp, exception) -> {
//                resp.setContentType("application/json;charset=utf-8");
//                PrintWriter out = resp.getWriter();
//                out.write("登录失败...");
//                out.flush();
//            })
//            .permitAll()//和表单登录相关的接口统统都直接通过
//            .and()
//            .logout().logoutUrl("/logout")
//            // 配置注销成功的回调
//            .logoutSuccessHandler((req, resp, authentication) -> {
//                resp.setContentType("application/json;charset=utf-8");
//                PrintWriter out = resp.getWriter();
//                out.write("注销成功...");
//                out.flush();
//            })
//            .permitAll()
//            .and()
//            .httpBasic()
//            .and()
//            // 关闭CSRF跨域
//            .csrf().disable();
//    }

    /**
     * 忽略拦截
     * @param web ay
     * @throws Exception ay
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略url - 会直接过滤该url - 将不会经过Spring Security过滤器链
        web.ignoring().antMatchers("/getUserInfo");
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }
}
