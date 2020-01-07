package com.liuyu.mall.config;

import com.liuyu.mall.security.filter.AdminAuthenticationProcessingFilter;
import com.liuyu.mall.security.filter.MyAuthenticationFilter;
import com.liuyu.mall.security.login.AdminAuthenticationEntryPoint;
import com.liuyu.mall.security.url.UrlAccessDecisionManager;
import com.liuyu.mall.security.url.UrlAccessDeniedHandler;
import com.liuyu.mall.security.url.UrlFilterInvocationSecurityMetadataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @EnableWebSecurity 启用Spring Security的Web安全支持
 * @author liuyu
 * */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    MyProperties myProperties;

    /**
     * 访问鉴权 - 认证token、签名...
     */
    private final MyAuthenticationFilter myAuthenticationFilter;
    /**
     * 访问权限认证异常处理
     */
    private final AdminAuthenticationEntryPoint adminAuthenticationEntryPoint;
    /**
     * 用户密码校验过滤器
     */
    private final AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter;

    // 上面是登录认证相关  下面为url权限相关 - ========================================================================================

    /**
     * 获取访问url所需要的角色信息
     */
    private final UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
    /**
     * 认证权限处理 - 将上面所获得角色权限与当前登录用户的角色做对比，如果包含其中一个角色即可正常访问
     */
    private final UrlAccessDecisionManager urlAccessDecisionManager;
    /**
     * 自定义访问无权限接口时403响应内容
     */
    private final UrlAccessDeniedHandler urlAccessDeniedHandler;

    /**
     * 写一个构造方法初始化类中的常量
     * */
    public SecurityConfig(MyAuthenticationFilter myAuthenticationFilter, AdminAuthenticationEntryPoint adminAuthenticationEntryPoint, AdminAuthenticationProcessingFilter adminAuthenticationProcessingFilter, UrlFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource, UrlAccessDeniedHandler urlAccessDeniedHandler, UrlAccessDecisionManager urlAccessDecisionManager) {
        this.myAuthenticationFilter = myAuthenticationFilter;
        this.adminAuthenticationEntryPoint = adminAuthenticationEntryPoint;
        this.adminAuthenticationProcessingFilter = adminAuthenticationProcessingFilter;
        this.urlFilterInvocationSecurityMetadataSource = urlFilterInvocationSecurityMetadataSource;
        this.urlAccessDeniedHandler = urlAccessDeniedHandler;
        this.urlAccessDecisionManager = urlAccessDecisionManager;
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
//        http.authorizeRequests().and().formLogin()
//                .loginProcessingUrl("/login")
//                .loginPage("/loginPage")
//                //其他配置
//                .permitAll()
//                .and()
//                // 禁用CSRF 开启跨域
//                .csrf().disable().cors();


//        http.csrf().disable().cors();

        // 未登录认证异常
        http.exceptionHandling().authenticationEntryPoint(adminAuthenticationEntryPoint);
        // 登录过后访问无权限的接口时自定义403响应内容
        http.exceptionHandling().accessDeniedHandler(urlAccessDeniedHandler);

        // url权限认证处理
        registry.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
                o.setAccessDecisionManager(urlAccessDecisionManager);
                return o;
            }
        });
        // 不创建会话 - 即通过前端传token到后台过滤器中验证是否存在访问权限
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 登录处理 - 前后端一体的情况下
//        registry.and().formLogin().loginPage("/login").defaultSuccessUrl("/").permitAll()
//                // 自定义登陆用户名和密码属性名，默认为 username和password
//                .usernameParameter("username").passwordParameter("password")
//                // 异常处理
//                .failureUrl("/login/error").permitAll()
//                // 退出登录
//                .and().logout().permitAll();

        // 标识访问 `/home` 这个接口，需要具备`ADMIN`角色
//        registry.antMatchers("/home").hasRole("ADMIN");
        // 标识只能在 服务器本地ip[127.0.0.1或localhost] 访问`/home`接口，其他ip地址无法访问
        registry.antMatchers("/home").hasIpAddress("127.0.0.1");
        // 允许匿名的url - 可理解为放行接口 - 多个接口使用,分割
        for (String url : myProperties.getAuth().getIgnoreUrls()) {
            registry.antMatchers(url).permitAll();
        }
//        registry.antMatchers("/login", "/index").permitAll();
        // swagger start    配置给Swagger2放行,已经在myProperties中进行配置了
//        registry.antMatchers("/swagger-ui.html").permitAll();
//        registry.antMatchers("/swagger-resources/**").permitAll();
//        registry.antMatchers("/images/**").permitAll();
//        registry.antMatchers("/webjars/**").permitAll();
//        registry.antMatchers("/v2/api-docs").permitAll();
//        registry.antMatchers("/configuration/ui").permitAll();
//        registry.antMatchers("/configuration/security").permitAll();
        // swagger end
        // OPTIONS(选项)：查找适用于一个特定网址资源的通讯选择。 在不需执行具体的涉及数据传输的动作情况下， 允许客户端来确定与资源相关的选项以及 / 或者要求， 或是一个服务器的性能
        registry.antMatchers(HttpMethod.OPTIONS, "/**").denyAll();
        // 自动登录 - cookie储存方式
        registry.and().rememberMe();
        // 其余所有请求都需要认证
        registry.anyRequest().authenticated();
        // 防止iframe 造成跨域
        registry.and().headers().frameOptions().disable();

        // 自定义过滤器认证用户名密码
        http.addFilterAt(adminAuthenticationProcessingFilter, UsernamePasswordAuthenticationFilter.class)
            .addFilterBefore(myAuthenticationFilter, BasicAuthenticationFilter.class);
    }

    /**
     * 将用户设置在内存中
     * @param auth ay
     * @throws Exception ay
     */
//    @Autowired
//    public void config(AuthenticationManagerBuilder auth) throws Exception{
//        // 在内存中配置用户，配置多个用户调用`and()`方法
//        auth.inMemoryAuthentication()
//                //指定加密方式
//                .passwordEncoder(passwordEncoder())
//                //管理员身份
//                .withUser("liuyu").password(passwordEncoder().encode("liuyu0113")).roles("ADMIN")
//                .and()
//                //普通用户身份
//                .withUser("liuyuay").password(passwordEncoder().encode("liuyuay0113")).roles("USER");
//    }

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
     * 配置忽略拦截
     * @param web ay
     * @throws Exception ay
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略url - 会直接过滤该url - 将不会经过Spring Security过滤器链
        web.ignoring().antMatchers(HttpMethod.GET,"/login");
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers(HttpMethod.GET,"/css/**", "/js/**");
    }
}
