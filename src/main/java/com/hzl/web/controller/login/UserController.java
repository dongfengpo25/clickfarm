package com.hzl.web.controller.login;

import com.hzl.web.bean.response.ResponseJson;
import com.hzl.web.controller.BaseController;
import com.hzl.web.shiro.bean.UserInfo;
import com.hzl.web.shiro.service.impl.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/user")
@RestController
public class UserController extends BaseController {
    @Autowired
    UserServiceImpl userService;

    /**
     * 验证短信验证码
     *
     * @param sms
     * @return
     */
    @RequestMapping("/checkSms")
    public Object checkSms(@RequestParam("phone") String phone, @RequestParam("sms") String sms) {
        return new ResponseJson();
    }

    /**
     * 修改密码
     *
     * @param phone
     * @param password
     * @return
     */
    @RequestMapping("/forgotPwd")
    public Object forgotPwd(@RequestParam("phone") String phone, @RequestParam("password") String password) {
        ResponseJson json = new ResponseJson();
        try {
            boolean succ = userService.editPassword(phone, password);
            json.setSucc(succ);
            if (!succ) {
                json.setMsg("修改密码失败");
            }
        } catch (Exception e) {
            json.setSucc(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }

    @RequestMapping("/auth/editPwd")
    public Object editPassword(@RequestParam("phone") String phone, @RequestParam("oldPassword") String oldPassword, @RequestParam("password") String password) {
        ResponseJson json = new ResponseJson();
        try {
            Subject currentUser = SecurityUtils.getSubject();
            UserInfo user = (UserInfo) currentUser.getPrincipal();
            if (password.equals(oldPassword) || user.getPassword().equals(password)) {
                json.setSucc(false);
                json.setMsg("新密码和旧密码不能一样，请重新输入！");
                return json;
            }
            if (!user.getPassword().equals(oldPassword)) {
                json.setSucc(false);
                json.setMsg("旧密码不匹配，请重新输入！");
                return json;
            }
            boolean succ = userService.editPassword(phone, password);
            json.setSucc(succ);
            if (!succ) {
                json.setMsg("修改密码失败");
            }
        } catch (Exception e) {
            json.setSucc(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }
}
