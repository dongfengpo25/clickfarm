package com.hzl.web.controller.menu;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.annotation.ServletSecurity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class MenuController {
    @RequestMapping(value = "/deployTask")
    public String deployTask(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "web/task/deploytask.html";
    }

    @RequestMapping(value = "/payTask")
    public String payTask(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "web/task/paytask.html";
    }

    @RequestMapping(value = "/browseTask")
    public String browseTask(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "web/task/browsetask.html";
    }

    @RequestMapping(value = "/shop")
    public String shop(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "web/shop/shop.html";
    }

    @RequestMapping(value = "/bindShop")
    public String bindShop(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "web/shop/bindshop.html";
    }

    @RequestMapping(value = "/showShops")
    public String showShop(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "web/shop/showshops.html";
    }

    @RequestMapping(value = "/uploadFile")
    public String uploadFile(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "test/uploadFile.html";
    }

    @RequestMapping(value = "/membermgr")
    @RequiresRoles(value = {"admin", "shop"}, logical = Logical.OR)
    public String membermgr(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "web/mgr/membermgr.html";
    }

    @RequestMapping(value = "/shopmgr")
    @RequiresRoles({"admin"})
    public String shopmgr(Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "web/mgr/shopmgr.html";
    }
}
