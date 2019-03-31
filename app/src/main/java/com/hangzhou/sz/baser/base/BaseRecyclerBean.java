package com.hangzhou.sz.baser.base;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class BaseRecyclerBean {
    public int viewType=1;
    private String letters;//显示拼音的首字母
    public String getLetters() {
        return letters;
    }
    public void setLetters(String letters) {
        this.letters = letters;
    }
}
