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
public class LoginController {

    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("captcha") String captcha,
                        @RequestParam(value = "remember", required = false, defaultValue = "false") boolean remember,
                        Map<String, Object> map) throws Exception {

        Session session = SecurityUtils.getSubject().getSession();
        String captcha_server = (String) session.getAttribute("_code");
        if (StringUtil.isEmpty(captcha_server)) {
            map.put("status", 500);
            map.put("msg", "验证码错误！");
            return "/login.html";
        }
        //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
        session.removeAttribute("_code");
        if (!captcha_server.equalsIgnoreCase(captcha)) {
            map.put("status", 500);
            map.put("msg", "验证码错误！");
            //request.getRequestDispatcher("/login.html").forward(request,response);
            return "/login.html";
        }

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
            } catch (IncorrectCredentialsException e) {
                System.out.println("用户名或密码错误");
                map.put("msg", "用户名或密码错误");
                model.addAttribute("msg", "用户名或密码错误");
            }
            // ... catch more exceptions here (maybe custom ones specific to your application?
            // 所有认证时异常的父类.
            catch (AuthenticationException e) {
                //unexpected condition?  error?
                System.out.println("登录失败: " + e.getMessage());
                map.put("msg", e.getMessage());
                model.addAttribute("msg", e.getMessage());
            }
        }
        if (map.size() > 0) {
            return "login.html";
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

    /**
     * 获取验证码（Gif版本）
     *
     * @param response
     */
    @RequestMapping(value = "getGifCode", method = RequestMethod.GET)
    public void getGifCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146, 33, 4);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute("_code", captcha.text().toLowerCase());
        } catch (Exception e) {
            System.err.println("获取验证码异常：" + e.getMessage());
        }
    }

    /**
     * 获取验证码（jpg版本）
     *
     * @param response
     */
    @RequestMapping(value = "getJPGCode", method = RequestMethod.GET)
    public void getJPGCode(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");
            /**
             * jgp格式验证码
             * 宽，高，位数。
             */
            Captcha captcha = new SpecCaptcha(146, 33, 4);
            //输出
            captcha.out(response.getOutputStream());
            HttpSession session = request.getSession(true);
            //存入Session
            session.setAttribute("_code", captcha.text().toLowerCase());
        } catch (Exception e) {
            System.err.println("获取验证码异常：" + e.getMessage());
        }
    }
}
