package com.hzl.web.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:shiro/shiro.xml")
@Configuration
public class ShiroConfig {

    /**
     * 超级关键，thymeleaf模板引擎和shiro整合时使用，否则没效果
     *
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

}
