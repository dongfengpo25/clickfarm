package com.hzl.web.shiro.dao;

import com.hzl.web.shiro.bean.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao {

    public List<UserInfo> getUserInfos();

    public UserInfo getUserInfoByPhone(String phone);

    public UserInfo getUserInfoByLogin(String param);

    public boolean addUser(UserInfo userInfo);

    public boolean editUser(UserInfo userInfo);

    public boolean disableUser(String phone);

    public boolean addRole(UserInfo userInfo);
}
