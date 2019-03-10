package com.hangzhou.sz.baser.adapter;

import android.view.ViewGroup;

import com.hangzhou.sz.baser.R;
import com.hangzhou.sz.baser.base.BaseRecyclerViewAdapter;
import com.hangzhou.sz.baser.base.BaseRecyclerViewHolder;
import com.hangzhou.sz.baser.holder.TypeOneHolder;
import com.hangzhou.sz.baser.holder.TypeTwoHolder;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class TestAdapter extends BaseRecyclerViewAdapter {

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 1:
                return new TypeOneHolder(parent, R.layout.item_type_one);
            case 2:
                return new TypeTwoHolder(parent,R.layout.item_type_two);
        }
        return null;
    }
}
