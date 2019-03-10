package com.hangzhou.sz.baser.utils;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class RandomString {
    private static String string = "abcdefghijklmnopqrstuvwxyz";

    private static int getRandom(int count) {
        return (int) Math.round(Math.random() * (count));
    }


    public static String getRandomString(int length) {
        StringBuffer sb = new StringBuffer();
        int len = string.length();
        for (int i = 0; i < length; i++) {
            sb.append(string.charAt(getRandom(len - 1)));
        }
        return sb.toString();
    }
}
