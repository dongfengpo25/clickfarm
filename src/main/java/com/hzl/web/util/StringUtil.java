package com.hzl.web.util;

public class StringUtil {

    /**
     * 是否为空
     *
     * @return
     */
    public static boolean isEmpty(String s) {
        return null == s || "".equals(s);
    }

    /**
     * 不为空
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return null != s || !"".equals(s);
    }
}
