package com.hzl.web.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class AuthUtil {

    /**
     * 获取HASH密码串
     *
     * @param user
     * @param password
     * @return
     */
    public static String getHashPassword(String user, String password) {
        Object result = new SimpleHash("MD5", password, ByteSource.Util.bytes(user), 1024);
        return result.toString();
    }
}
