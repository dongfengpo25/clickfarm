package com.hzl.web.dao;

import com.hzl.web.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    public List<User> getUsers();

    public User getUserByPhone(String phone);

    public User getUserByNumber(String number);

    public void addUser(User user);

    public void editUser(User user);

    public void disableUser(String phone);
}
