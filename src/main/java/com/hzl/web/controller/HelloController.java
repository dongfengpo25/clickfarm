package com.hzl.web.controller;

import com.hzl.web.bean.Person;
import com.hzl.web.service.impl.CashierImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RequestMapping("/test")
@RestController
public class HelloController {

    @Autowired
    Person person;
    @Autowired
    CashierImpl cashier;

    @RequestMapping("/hello")
    public Object hello() {
        person.work();
        return "Hello World...";
    }

    @RequestMapping("error")
    public Object error() {
        return 1 / 0;
    }

    @RequestMapping("/buybook")
    public void buyBook() {
        cashier.checkout("hzl", Arrays.asList("1001", "1002"));
    }
}
