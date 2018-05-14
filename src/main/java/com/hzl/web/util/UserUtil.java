package com.hzl.web.util;

import java.util.Random;

public class UserUtil {

    public static void main(String[] args) {
        System.out.println(generateNumber());
    }

    /**
     * 从六位到九位之间
     *
     * @return
     */
    public static int generateNumber() {
        Random random = new Random();
        return random.nextInt(99900000) + 100000;
    }

}
