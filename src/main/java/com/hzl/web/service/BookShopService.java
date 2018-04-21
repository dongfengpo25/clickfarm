package com.hzl.web.service;

import com.hzl.web.bean.Account;
import com.hzl.web.bean.Book;

import java.util.List;

/**
 * 
 * 
 * @author hzl
 * @date 2018年3月18日
 */
public interface BookShopService {

	public void purchase(String username, String isbn);
	
	public List<Account> getAccounts();

	public List<Book> getBooks(int pageNo, int pageSize);
}
