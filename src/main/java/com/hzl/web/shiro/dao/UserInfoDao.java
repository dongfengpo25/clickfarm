package com.hzl.web.shiro.dao;

import com.hzl.web.shiro.bean.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInfoDao {

    public List<UserInfo> getUserInfos();

    public UserInfo getUserInfoByPhone(String phone);

    public UserInfo getUserInfoByNumber(String number);

    public void addUser(UserInfo userInfo);

    public void editUser(UserInfo userInfo);

    public void disableUser(String phone);
}
