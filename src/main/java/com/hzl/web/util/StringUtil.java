package com.hzl.web.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    /**
     * 手机号是否合法
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        if (isEmpty(phone))
            return false;
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }
}
