package com.hzl.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "remember", required = false, defaultValue = "false") boolean remember,
                        Map<String, Object> map) throws Exception {

        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            // 把用户名和密码封装为 UsernamePasswordToken 对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // rememberme
            token.setRememberMe(remember);
            try {
                System.out.println("1. " + token.hashCode());
                // 执行登录.
                currentUser.login(token);
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                System.out.println("登录失败: " + ae.getMessage());
            }
        }

        return "redirect:/main.html";
        //request.getRequestDispatcher("/login").forward(request, response);
    }

    @ResponseBody
    @RequestMapping(value = "/mlogin", method = RequestMethod.POST)
    public Object mlogin(Model model, HttpServletRequest request, HttpServletResponse response,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password) throws Exception {
        JSONObject json = new JSONObject();
        if (!StringUtils.isEmpty(username) && "admin".equals(password)) {
            json.put("succ", "true");
        } else {
            json.put("succ", "false");
            json.put("msg", "用户名密码错误");
        }
        return json;
    }

    @RequestMapping(value = "/loginOut")
    public String loginOut(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "redirect:/login.html";
    }
}
