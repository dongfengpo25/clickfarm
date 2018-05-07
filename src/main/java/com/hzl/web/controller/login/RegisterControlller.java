package com.hzl.web.controller.login;

import com.hzl.web.bean.response.ResponseJson;
import com.hzl.web.controller.BaseController;
import com.hzl.web.shiro.bean.UserInfo;
import com.hzl.web.shiro.service.impl.UserServiceImpl;
import com.hzl.web.util.AuthUtil;
import com.hzl.web.util.DateUtil;
import com.hzl.web.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping(value = "register")
@Controller
public class RegisterControlller extends BaseController {
    private final String _register_html = "/web/register.html";
    private final String redirect_register_html = _redirect + "/register";

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "")
    public String index() {
        return _register_html;
    }

    /**
     * PC端
     *
     * @param model
     * @param request
     * @param response
     * @param name
     * @param phone
     * @param password
     * @param captcha
     * @param map
     * @param redirectAttributes
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/pcuser", method = RequestMethod.POST)
    public String register(Model model, HttpServletRequest request, HttpServletResponse response,
                           @RequestParam("name") String name,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam("captcha") String captcha,
                           Map<String, Object> map,
                           RedirectAttributes redirectAttributes) throws Exception {
        ResponseJson json = doRegister(name, phone, password, captcha);
        if (json.get(_succ).equals(true)) {
            return "redirect:/login.html";
        } else {
            redirectAttributes.addAttribute(_msg, json.get(_msg));
            return redirect_register_html;
        }
    }

    /**
     * 手机端
     *
     * @param model
     * @param request
     * @param response
     * @param name
     * @param phone
     * @param password
     * @param captcha
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping(value = "/muser", method = RequestMethod.POST)
    public Object register(Model model, HttpServletRequest request, HttpServletResponse response,
                           @RequestParam(value = "name", required = false, defaultValue = "") String name,
                           @RequestParam("phone") String phone,
                           @RequestParam("password") String password,
                           @RequestParam(value = "captcha", required = false, defaultValue = "") String captcha) throws Exception {
        return doRegister(null, phone, password, null);
    }

    private ResponseJson doRegister(String name, String phone, String password, String captcha) throws Exception {
        ResponseJson json = new ResponseJson();
        json.put(_succ, false);
        try {
//            if (StringUtil.isEmpty(name)) {
//                json.put(_msg, "姓名不能为空！");
//                return json;
//            }

            if (StringUtil.isEmpty(password)) {
                json.put(_msg, "密码不能为空！");
                return json;
            }

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

            if (!StringUtil.isPhone(phone)) {
                json.put(_msg, "手机号码格式不正确！");
                return json;
            }

            try {
                UserInfo userInfo = userService.getUserByPhone(phone);
                if (userInfo != null) {
                    json.put(_msg, "手机号码已注册！");
                    return json;
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
                json.put(_msg, "注册失败！");
                return json;
            }
            json.put(_succ, true);
        } catch (Exception e) {
            json.put(_msg, e.getMessage());
        }
        return json;
    }

}
