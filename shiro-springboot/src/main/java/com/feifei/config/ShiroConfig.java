package com.feifei.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *      Shiro 配置类
 * */
@Configuration
public class ShiroConfig {


    // ShiroFilterFactoryBean  3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加 shiro的内置过滤器
        /**
         *  anon ：无需认证就能访问
         *  ahthc：必须认证才能访问
         *  user：必须拥有 ‘记住我’ 才能访问
         *  perms：拥有对某个资源的权限才能访问
         *  role：拥有某个角色权限才能访问
         * */
        Map<String, String> map = new LinkedHashMap<>();
        //设置授权 ，正常情况下 没有授权会跳转到未授权页面
        map.put("/user/add","perms[user:add]");
        // 设置 访问权限
        map.put("/user/add","authc");
        map.put("/user/update","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //认证失败 进入登录页面  设置登录请求地址
        shiroFilterFactoryBean.setLoginUrl("/tologin");
        //设置未授权 跳转的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/weishouquan");

        return shiroFilterFactoryBean;
    }

    //DefaultWebSecurityManager  2
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 创建 realm 对象     1
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }


    // shiroDiglect 用来整合 shiro thymeleaf
    @Bean
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }





}
