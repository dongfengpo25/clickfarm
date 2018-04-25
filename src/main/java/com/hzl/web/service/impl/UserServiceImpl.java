package com.hzl.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.hzl.web.bean.User;
import com.hzl.web.dao.UserDao;
import com.hzl.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return userDao.getUsers();
    }

    @Override
    public User getUserByPhone(String phone) {
        return userDao.getUserByPhone(phone);
    }

    @Override
    public User getUserByNumber(String number) {
        return userDao.getUserByNumber(number);
    }

    @Override
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    public void editUser(User user) {
        userDao.editUser(user);
    }

    @Override
    public void disableUser(String phone) {
        userDao.disableUser(phone);
    }
}
