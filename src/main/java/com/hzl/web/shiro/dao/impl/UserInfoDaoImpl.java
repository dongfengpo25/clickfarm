package com.hzl.web.shiro.dao.impl;

import com.hzl.web.shiro.bean.UserInfo;
import com.hzl.web.dao.impl.BaseDaoImpl;
import com.hzl.web.shiro.dao.UserInfoDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {

    @Override
    public List<UserInfo> getUserInfos() {
        return sqlSession.selectList("userInfo.query");
    }

    @Override
    public UserInfo getUserInfoByPhone(String phone) {
        return sqlSession.selectOne("userInfo.getUserInfoByPhone", phone);
    }

    @Override
    public UserInfo getUserInfoByLogin(String param) {
        return sqlSession.selectOne("userInfo.getUserInfoByLogin", param);
    }

    @Override
    public boolean addUser(UserInfo userInfo) {
        return sqlSession.update("userInfo.addUser", userInfo) > 0;
    }

    @Override
    public boolean editUser(UserInfo userInfo) {
        return sqlSession.update("userInfo.editUser", userInfo) > 0;
    }

    @Override
    public boolean disableUser(String phone) {
        return sqlSession.update("userInfo.disableUser", phone) >0;
    }

    @Override
    public boolean addRole(UserInfo userInfo) {
        return sqlSession.update("userInfo.addRole", userInfo) > 0;
    }
}
