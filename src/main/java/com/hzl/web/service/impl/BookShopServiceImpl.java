package com.hzl.web.service.impl;

import com.github.pagehelper.PageHelper;
import com.hzl.web.bean.Account;
import com.hzl.web.bean.Book;
import com.hzl.web.dao.BookShopDao;
import com.hzl.web.service.BookShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzl
 * @date 2018年3月18日
 */

@Service
public class BookShopServiceImpl implements BookShopService {

    @Autowired
    private BookShopDao bookShopDao;

//	public void setBookShopDao(BookShopDao bookShopDao) {
//		this.bookShopDao = bookShopDao;
//	}

    //@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    @Override
    public void purchase(String username, String isbn) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }

        //1. 获取书的单价
        int price = bookShopDao.findBookPriceByIsbn(isbn);

        //2. 更新数的库存
        bookShopDao.updateBookStock(isbn);

        //3. 更新用户余额
        bookShopDao.updateUserAccount(username, price);
    }

    @Override
    public List<Account> getAccounts() {
        return bookShopDao.getAccounts();
    }

    @Override
    public List<Book> getBooks(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return bookShopDao.getBooks();
    }
}
