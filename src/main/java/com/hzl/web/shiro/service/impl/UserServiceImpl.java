package com.hzl.web.shiro.service.impl;

import com.github.pagehelper.PageHelper;
import com.hzl.web.shiro.bean.UserInfo;
import com.hzl.web.shiro.dao.UserInfoDao;
import com.hzl.web.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    public List<UserInfo> getUsers(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return userInfoDao.getUserInfos();
    }

    @Override
    public UserInfo getUserByPhone(String phone) {
        return userInfoDao.getUserInfoByPhone(phone);
    }

    @Override
    public UserInfo getUserByNumber(String number) {
        return userInfoDao.getUserInfoByNumber(number);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfoDao.addUser(userInfo);
    }

    @Override
    public void editUser(UserInfo userInfo) {
        userInfoDao.editUser(userInfo);
    }

    @Override
    public void disableUser(String phone) {
        userInfoDao.disableUser(phone);
    }
}
