package com.feifei.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {
    @GetMapping({"/","/index"})
    public String index(Model model){
        model.addAttribute("msg","Hello,Shiro");
        return "index";
    }
    @GetMapping("/user/add")
    public String add(){
        return "/user/add";
    }
    @GetMapping("/user/update")
    public String update(){
        return "/user/update";
    }
    @GetMapping("tologin")
    public String tologin(){
        return "login";
    }

    //登录
    @RequestMapping("/login")
    public String login(String username,String password,Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登陆信息
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {  //  用户名不存在
            model.addAttribute("msg","用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e){  // 密码不存在
            model.addAttribute("msg","密码不存在");
            return "login";
        }
    }

    // 为授权跳转的页面
    @GetMapping("/weishouquan")
    @ResponseBody
    public String weishouquan(){
        return "未授权。。。";
    }



}
