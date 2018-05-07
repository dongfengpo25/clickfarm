package com.hzl.web.controller;

import com.hzl.web.bean.response.ResponseJson;
import com.hzl.web.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
    private final String login_html = "/login.html";

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "captcha", defaultValue = "") String captcha,
                        @RequestParam(value = "remember", required = false, defaultValue = "false") boolean remember,
                        Map<String, Object> map) throws Exception {
        ResponseJson json = doLogin(username, password, captcha, remember);
        if (json.get(_succ).equals(true)) {
            return "redirect:/main.html";
        } else {
            map.put(_msg, json.get(_msg));
            return login_html;
        }

        //request.getRequestDispatcher("/login").forward(request, response);
    }

    @ResponseBody
    @RequestMapping(value = "/mlogin", method = RequestMethod.POST)
    public Object mlogin(Model model, HttpServletRequest request, HttpServletResponse response,
                         @RequestParam("username") String username,
                         @RequestParam("password") String password) throws Exception {
        return doLogin(username, password, null, false);
    }

    private ResponseJson doLogin(String username, String password, String captcha, boolean remember) throws Exception {
        ResponseJson json = new ResponseJson();
        json.put(_succ, false);
        try {
            if (captcha != null) {
                Session session = SecurityUtils.getSubject().getSession();
                String captcha_server = (String) session.getAttribute(_code);
                if (StringUtil.isEmpty(captcha_server)) {
                    json.put(_msg, "验证码错误！");
                    return json;
                }
                //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
                session.removeAttribute(_code);
                if (!captcha_server.equalsIgnoreCase(captcha)) {
                    json.put(_msg, "验证码错误！");
                    return json;
                }
            }

            Subject currentUser = SecurityUtils.getSubject();
            if (!currentUser.isAuthenticated()) {
                // 把用户名和密码封装为 UsernamePasswordToken 对象
                UsernamePasswordToken token = new UsernamePasswordToken(username, password);
                // rememberme
                token.setRememberMe(remember);
                try {
                    // 执行登录.
                    currentUser.login(token);
                } catch (IncorrectCredentialsException e) {
                    json.put(_msg, "用户名或密码错误");
                    return json;
                }
                // ... catch more exceptions here (maybe custom ones specific to your application?
                // 所有认证时异常的父类.
                catch (AuthenticationException e) {
                    json.put(_msg, e.getMessage());
                    return json;
                }
            }

            json.put(_succ, true);
            //request.getRequestDispatcher("/login").forward(request, response);
        } catch (Exception e) {
            json.put(_msg, e.getMessage());
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
