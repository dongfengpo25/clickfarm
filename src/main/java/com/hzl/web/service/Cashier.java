package com.hzl.web.service;

import java.util.List;

/**
 * 
 * 
 * @author hzl
 * @date 2018年3月18日
 */
public interface Cashier {

	public void checkout(String username, List<String> isbns);
	
}
