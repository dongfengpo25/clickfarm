package com.hzl.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ImportResource({"classpath:tx/transaction.xml","classpath:shiro/shiro.xml"})
//@ComponentScan("com.hzl.web")
@EnableTransactionManagement
@SpringBootApplication
public class ClickfarmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClickfarmApplication.class, args);
	}
}
