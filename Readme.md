#SpringSecurity核心功能
#####1.认证
#####2.授权
#####3.攻击防护
#核心
其核心就是一组过滤器链，项目启动后将会自动配置。最核心的就是 BasicAuthenticationFilter 用来认证用户的身份，一个在spring security中一种过滤器处理一种认证方式
#Interface
####一、UserDetailsService接口提供了loadUserByUsername方法，即通过用户名加载用户
####二、UsernamePasswordAuthenticationFilter用户名密码认证过滤器