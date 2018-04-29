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
    public UserInfo getUserInfoByNumber(String number) {
        return sqlSession.selectOne("userInfo.getUserInfoByNumber", number);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        sqlSession.update("userInfo.addUser", userInfo);
    }

    @Override
    public void editUser(UserInfo userInfo) {
        sqlSession.update("userInfo.editUser", userInfo);
    }

    @Override
    public void disableUser(String phone) {
        sqlSession.update("userInfo.disableUser", phone);
    }
}
