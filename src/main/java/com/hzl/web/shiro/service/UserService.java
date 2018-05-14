package com.hzl.web.shiro.service;

import com.hzl.web.shiro.bean.UserInfo;

import java.util.List;

public interface UserService {

    public List<UserInfo> getUsers(int pageNo, int pageSize);

    public UserInfo getUserByPhone(String phone);

    public UserInfo getUserInfoByLogin(String param);

    public boolean addUser(UserInfo userInfo);

    public boolean editUser(UserInfo userInfo);

    public boolean disableUser(String phone);

    public boolean editPassword(String phone, String password);

    public boolean addRole(UserInfo userInfo);
}
