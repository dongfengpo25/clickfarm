package com.hzl.web.dao.impl;

import com.hzl.web.bean.Account;
import com.hzl.web.bean.Book;
import com.hzl.web.dao.BookShopDao;
import com.hzl.web.exception.BookStockException;
import com.hzl.web.exception.UserAccountException;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 书店操作实现类
 *
 * @author hzl
 * @date 2018年3月18日
 */
@Repository
public class BookShopDaoImpl implements BookShopDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

//	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
//
//	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
//		this.sqlSessionTemplate = sqlSessionTemplate;
//	}

    public List<Account> getAccounts() {
        return sqlSessionTemplate.selectList("account.query");
    }

    public List<Book> getBooks(){
        return sqlSessionTemplate.selectList("book.query");
    }

    @Override
    public int findBookPriceByIsbn(String isbn) {
        String sql = "SELECT price FROM book WHERE isbn = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
    }

    @Override
    public void updateBookStock(String isbn) {
        // 检查书的库存是否足够, 若不够, 则抛出异常
        String sql2 = "SELECT stock FROM book_stock WHERE isbn = ?";
        int stock = jdbcTemplate.queryForObject(sql2, Integer.class, isbn);
        if (stock == 0) {
            throw new BookStockException("库存不足!");
        }

        String sql = "UPDATE book_stock SET stock = stock -1 WHERE isbn = ?";
        jdbcTemplate.update(sql, isbn);
    }

    @Override
    public void updateUserAccount(String username, int price) {
        // 验证余额是否足够, 若不足, 则抛出异常
        String sql2 = "SELECT balance FROM account WHERE username = ?";
        int balance = jdbcTemplate.queryForObject(sql2, Integer.class, username);
        if (balance < price) {
            throw new UserAccountException("余额不足!");
        }

        String sql = "UPDATE account SET balance = balance - ? WHERE username = ?";
        jdbcTemplate.update(sql, price, username);
    }

}
