package com.hzl.web.shiro.factory;

import java.util.LinkedHashMap;

public class FilterChainDefinitionMapBuilder {

    public LinkedHashMap<String, String> buildFilterChainDefinitionMap() {
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("/images/**", "anon");
        map.put("/js/**", "anon");

        map.put("/login.html", "anon");
        map.put("/login", "anon");
        map.put("/userLogin", "anon");
        map.put("/logout", "logout");
        map.put("/main.html", "authc,roles[user]");
        map.put("/main.html", "user");

        map.put("/**", "authc");

        return map;
    }

}
