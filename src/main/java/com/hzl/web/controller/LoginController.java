package com.hzl.web.controller;

import com.hzl.web.captcha.Captcha;
import com.hzl.web.captcha.GifCaptcha;
import com.hzl.web.captcha.SpecCaptcha;
import com.hzl.web.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController extends BaseController {
    private final String login_html = "/login.html";

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("captcha") String captcha,
                        @RequestParam(value = "remember", required = false, defaultValue = "false") boolean remember,
                        Map<String, Object> map) throws Exception {

        Session session = SecurityUtils.getSubject().getSession();
        String captcha_server = (String) session.getAttribute(_code);
        if (StringUtil.isEmpty(captcha_server)) {
            map.put("msg", "验证码错误！");
            return login_html;
        }
        //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
        session.removeAttribute(_code);
        if (!captcha_server.equalsIgnoreCase(captcha)) {
            map.put("msg", "验证码错误！");
            return login_html;
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
                map.put("msg", "用户名或密码错误");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException e) {
                map.put("msg", e.getMessage());
            }
        }
        if (map.size() > 0) {
            return login_html;
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
