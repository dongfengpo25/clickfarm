package com.hzl.web.shiro.service;

import com.hzl.web.shiro.bean.UserInfo;

import java.util.List;

public interface UserService {

    public List<UserInfo> getUsers(int pageNo, int pageSize);

    public UserInfo getUserByPhone(String phone);

    public UserInfo getUserByNumber(String number);

    public void addUser(UserInfo userInfo);

    public void editUser(UserInfo userInfo);

    public void disableUser(String phone);
}
