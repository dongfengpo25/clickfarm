package com.hzl.web.service;

import com.hzl.web.bean.User;

import java.util.List;

public interface UserService {

    public List<User> getUsers(int pageNo, int pageSize);

    public User getUserByPhone(String phone);

    public User getUserByNumber(String number);

    public void addUser(User user);

    public void editUser(User user);

    public void disableUser(String phone);
}
