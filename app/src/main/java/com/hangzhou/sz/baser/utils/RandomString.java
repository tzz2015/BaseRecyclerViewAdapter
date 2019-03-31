package com.hangzhou.sz.baser.utils;

import java.io.UnsupportedEncodingException;
import java.util.Random;

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

    /**
     *      * 获取随机汉字
     *      * @return
     *     
     */

    public static String getRandomWord() {
        String str = "";
        int heightPos;
        int lowPos;
        Random rd = new Random();
        heightPos = 176 + Math.abs(rd.nextInt(39));
        lowPos = 161 + Math.abs(rd.nextInt(93));
        byte[] bt = new byte[2];
        bt[0] = Integer.valueOf(heightPos).byteValue();
        bt[1] = Integer.valueOf(lowPos).byteValue();
        try {
            str = new String(bt, "GBK");
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str;
    }

}
