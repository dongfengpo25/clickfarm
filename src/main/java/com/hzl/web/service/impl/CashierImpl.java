package com.hzl.web.service.impl;

import com.github.pagehelper.PageInfo;
import com.hzl.web.bean.Account;
import com.hzl.web.bean.Book;
import com.hzl.web.service.BookShopService;
import com.hzl.web.service.Cashier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hzl
 * @date 2018年3月18日
 */
@Service
public class CashierImpl implements Cashier {

    @Autowired
    private BookShopService bookShopService;

//    public void setBookShopService(BookShopService bookShopService) {
//        this.bookShopService = bookShopService;
//    }

    @Override
    public void checkout(String username, List<String> isbns) {
        for (String isbn : isbns) {
            bookShopService.purchase(username, isbn);
        }
        List<Account> accounts = bookShopService.getAccounts();
        System.out.println(accounts);

        List<Book> books = bookShopService.getBooks(2,10);
        System.out.println(books);
        PageInfo<Book> pages = new PageInfo<>(books);
        System.out.println(pages.getPrePage());
    }

}
