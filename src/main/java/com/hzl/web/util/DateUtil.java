package com.hzl.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static String getCurrDateTime() {
        return getCurrDateStr("yyyy-MM-dd HH:mm:ss");
    }

    public static String getCurrDate() {
        return getCurrDateStr("yyyy-MM-dd");
    }

    public static String getUploadDate() {
        return getCurrDateStr("yyyyMMdd");
    }

    public static String getCurrDateStr(String formt) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(formt);
        return sdf.format(d);
    }
}
