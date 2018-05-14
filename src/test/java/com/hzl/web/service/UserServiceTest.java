package com.hzl.web.service;

import com.hzl.web.shiro.bean.UserInfo;
import com.hzl.web.shiro.service.impl.UserServiceImpl;
import com.hzl.web.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserServiceImpl userService;

    @Test
    public void testUserByLogin() {
       UserInfo user =  userService.getUserInfoByLogin("12345678922");
       System.out.println(user);
    }

    @Test
    public void testGetUsers() {
        List<UserInfo> list = userService.getUsers(1,10);
        System.out.println(list.size());
    }

    @Test
    public void testAddUser(){
        UserInfo user = new UserInfo();
        user.setPhone("12345678922");
        userService.addUser(user);
    }

    @Test
    public void testEditUser(){
        UserInfo user = new UserInfo();
        user.setPhone("12345678911");
        user.setOldPhone("12345678900");
        user.setAlipayId("5555");
        user.setEditTime(DateUtil.getCurrDateTime());
        userService.editUser(user);
    }
}
