package com.hzl.web.dao.impl;

import com.hzl.web.bean.User;
import com.hzl.web.dao.UserDao;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl extends BaseDaoImpl implements UserDao {

    @Override
    public List<User> getUsers() {
        return sqlSession.selectList("user.query");
    }

    @Override
    public User getUserByPhone(String phone) {
        return sqlSession.selectOne("user.getUserByPhone", phone);
    }

    @Override
    public User getUserByNumber(String number) {
        return sqlSession.selectOne("user.getUserByNumber", number);
    }

    @Override
    public void addUser(User user) {
        sqlSession.update("user.addUser", user);
    }

    @Override
    public void editUser(User user) {
        sqlSession.update("user.editUser", user);
    }

    @Override
    public void disableUser(String phone) {
        sqlSession.update("user.disableUser", phone);
    }
}
