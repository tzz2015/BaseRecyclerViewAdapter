package com.hangzhou.sz.baser.adapter;

import android.view.ViewGroup;

import com.hangzhou.sz.baser.R;
import com.hangzhou.sz.baser.base.BaseRecyclerViewAdapter;
import com.hangzhou.sz.baser.base.BaseRecyclerViewHolder;
import com.hangzhou.sz.baser.holder.GroupContentHolder;
import com.hangzhou.sz.baser.holder.GroupTitleHolder;
import com.hangzhou.sz.baser.holder.TypeOneHolder;
import com.hangzhou.sz.baser.holder.TypeTwoHolder;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class TestAdapter extends BaseRecyclerViewAdapter {
    public static final int GROUP_TITLE=3;
    public static final int GROUP_CONTENT=4;


    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                return new TypeOneHolder(parent, R.layout.item_type_one);
            case 2:
                return new TypeTwoHolder(parent, R.layout.item_type_two);
            case GROUP_TITLE:
                return new GroupTitleHolder(parent,R.layout.item_linear_title);
            case GROUP_CONTENT:
                return new GroupContentHolder(parent,R.layout.item_linear_content);
            default:
        }
        return null;
    }

    @Override
    public boolean isPinnedPosition(int position) {
        return getItemViewType(position)==GROUP_TITLE;
    }
}
