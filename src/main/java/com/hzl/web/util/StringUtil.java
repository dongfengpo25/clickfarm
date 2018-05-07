package com.hzl.web.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Random;
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
        String regex = "^[1][3,4,5,7,8][0-9]{9}$";
        if (phone.length() != 11) {
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            return m.matches();
        }
    }

    /**
     * MD5加密
     *
     * @param s
     * @return
     */
    public static String md5(String s) {
        Object result = new SimpleHash("MD5", s, ByteSource.Util.bytes(""), 1);
        return result.toString();
    }

    public static String getRandomString(int length){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str="zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        //由Random生成随机数
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        //长度为几就循环几次
        for(int i=0; i<length; ++i){
            //产生0-61的数字
            int number=random.nextInt(62);
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }
}
