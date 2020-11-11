package com.feifei.config;

import com.feifei.pojo.User;
import com.feifei.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

// 自定义 UserRealm 继承 AuthorizingRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了' 授权 '操作：doGetAuthorizationInfo");
        // 创建一个 SimpleAuthorizationInfo 对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 设置 授权格式
        info.addStringPermission("user:add");
        // 获取当前对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal();
        currentUser.setPerms("user:add");
        //设置当前用户的权限   --> 获取当前用户所拥有的权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }
    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了' 认证 '操作：doGetAuthorizationInfo");
        //转换
        UsernamePasswordToken userToke = (UsernamePasswordToken) token;

        User user = this.userService.findByUserCode(userToke.getUsername());
        //判断  --- > 如果不存在 则返回null,  抛出异常 ： UnknownAccountException  用户名不存在的异常
        if(user == null){
            return null;
        }
        //判断  --- > 如果不存在 则返回null,  抛出异常 ： UnknownAccountException  用户名不存在的异常
//        if(!userToke.getUsername().equals(username)){
//            return null;
//        }
        //  shiro 做 密码认证。。。
        AuthenticationInfo info = new SimpleAuthenticationInfo("", user.getUserPassWord(), "");
        return info;
    }
}
