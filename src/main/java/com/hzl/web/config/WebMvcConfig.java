package com.hzl.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//使用WebMvcConfigurerAdapter可以来扩展SpringMVC的功能
//@EnableWebMvc   不要接管SpringMVC
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        super.addViewControllers(registry);
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("web/main");
    }

//    //所有的WebMvcConfigurerAdapter组件都会一起起作用
//    @Bean //将组件注册在容器
//    public WebMvcConfigurerAdapter webMvcConfigurerAdapter() {
//        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter() {
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/").setViewName("web/login");
//                registry.addViewController("/login").setViewName("web/login");
//                registry.addViewController("/login.html").setViewName("web/login");
//                registry.addViewController("/index").setViewName("web/login");
//                registry.addViewController("/index.html").setViewName("web/login");
//                //registry.addViewController("/main.html").setViewName("web/main");
//            }
//        };
//        return adapter;
//    }
//
//    //注册拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//        //静态资源；  *.css , *.js
//        //SpringBoot已经做好了静态资源映射
//        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
//                .excludePathPatterns("/js/**", "/images/**", "/index.html", "/", "/login", "/login.html", "/index", "/mlogin");
//    }
}
