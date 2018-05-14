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
    public UserInfo getUserInfoByLogin(String param) {
        return userInfoDao.getUserInfoByLogin(param);
    }

    @Override
    public boolean addUser(UserInfo userInfo) {
        return userInfoDao.addUser(userInfo);
    }

    @Override
    public boolean editUser(UserInfo userInfo) {
        return userInfoDao.editUser(userInfo);
    }

    @Override
    public boolean disableUser(String phone) {
        return userInfoDao.disableUser(phone);
    }

    @Override
    public boolean editPassword(String phone, String password) {
        UserInfo user = new UserInfo();
        user.setPhone(phone);
        user.setPassword(password);
        return userInfoDao.editUser(user);
    }

    public boolean addRole(UserInfo userInfo){
        return userInfoDao.addRole(userInfo);
    }
}
