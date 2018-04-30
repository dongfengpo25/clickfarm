package com.hzl.web.controller.login;

import com.hzl.web.controller.BaseController;
import com.hzl.web.shiro.bean.UserInfo;
import com.hzl.web.shiro.service.impl.UserServiceImpl;
import com.hzl.web.util.AuthUtil;
import com.hzl.web.util.DateUtil;
import com.hzl.web.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping(value = "register")
@Controller
public class RegisterControlller extends BaseController {
    private final String register_html = "web/register.html";

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping
    public String index() {
        return register_html;
    }

    @RequestMapping(value = "/commit", method = RequestMethod.POST)
    public String register(Model model, HttpServletRequest request, HttpServletResponse response,
                           @RequestParam("name") String name,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("captcha") String captcha,
                           Map<String, Object> map) throws Exception {
        if (StringUtil.isEmpty(name)) {
            map.put("msg", "姓名不能为空！");
            return register_html;
        }

        if (StringUtil.isEmpty(password)) {
            map.put("msg", "密码不能为空！");
            return register_html;
        }

        Session session = SecurityUtils.getSubject().getSession();
        String captcha_server = (String) session.getAttribute(_code);
        if (StringUtil.isEmpty(captcha_server)) {
            map.put("msg", "验证码错误！");
            return register_html;
        }
        //还可以读取一次后把验证码清空，这样每次登录都必须获取验证码
        session.removeAttribute(_code);
        if (!captcha_server.equalsIgnoreCase(captcha)) {
            map.put("msg", "验证码错误！");
            return register_html;
        }

        if (!StringUtil.isPhone(phone)) {
            map.put("msg", "手机号码格式不正确！");
            return register_html;
        }

        try {
            UserInfo userInfo =  userService.getUserByPhone(phone);
            if (userInfo != null){
                map.put("msg", "手机号码已注册！");
                return register_html;
            }

            //手机号码当用户名，获取加密后的字符串当密码，即将插入手台
            String saltPwd = AuthUtil.getHashPassword(phone, password);

            UserInfo user = new UserInfo();
            user.setNumber(phone);
            user.setPhone(phone);
            user.setName(name);
            user.setPassword(saltPwd);
            user.setStatusId(2);
            user.setWriteTime(DateUtil.getCurrDateTime());
            userService.addUser(user);
        } catch (Exception e) {
            map.put("msg", "注册失败！");
            return register_html;
        }
        return "redirect:/login.html";
    }
}
