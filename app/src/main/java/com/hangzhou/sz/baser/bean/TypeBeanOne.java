package com.hangzhou.sz.baser.bean;

import com.hangzhou.sz.baser.utils.RandomString;
import com.hangzhou.sz.baser.base.BaseRecyclerBean;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class TypeBeanOne extends BaseRecyclerBean {
    public int index;
    public String random= RandomString.getRandomWord()+RandomString.getRandomString(4);

    public TypeBeanOne(int index) {
        this.index = index;
    }
}
