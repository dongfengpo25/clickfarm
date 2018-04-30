package com.hzl.web.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/images/**", "anon");
        map.put("/js/**", "anon");

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

        map.put("/**", "authc");
        return map;
    }

}
