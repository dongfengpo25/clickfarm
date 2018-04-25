package com.hzl.web.dao.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDaoImpl {

    @Autowired
    protected SqlSessionTemplate sqlSession;

}
