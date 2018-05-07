package com.hzl.web.controller;

import com.hzl.web.bean.IConstant;
import org.springframework.stereotype.Controller;

@Controller
public class BaseController implements IConstant {
    public final String _code = "_code";
    public final String _redirect = "redirect:";

}
