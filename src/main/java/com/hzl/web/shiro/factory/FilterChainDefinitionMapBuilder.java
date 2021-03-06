package com.hzl.web.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/images/**", "anon");
        map.put("/js/**", "anon");
        map.put("/css/**", "anon");

        map.put("/register", "anon");
        map.put("/register.html", "anon");
        map.put("/register/**", "anon");

        map.put("/login.html", "anon");
        map.put("/login", "anon");
        map.put("/getGifCode", "anon");
        map.put("/getJPGCode", "anon");
        map.put("/userLogin", "anon");
        map.put("/logout", "logout");
        map.put("/main.html", "user");
        //测试记住我可以访问绑定店铺
        map.put("/bindShop", "user");
        map.put("/druid/**", "authc, roles[\"admin\"]");

        map.put("/user/auth/**", "authc");
        map.put("/user/**", "anon");

        map.put("/**", "authc");
        return map;
    }

}
