package com.hangzhou.sz.baser.holder;

import android.view.ViewGroup;

import com.hangzhou.sz.baser.base.BaseRecyclerViewHolder;
import com.hangzhou.sz.baser.bean.TypeBeanOne;
import com.hangzhou.sz.baser.databinding.ItemTypeOneBinding;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class TypeOneHolder extends BaseRecyclerViewHolder<ItemTypeOneBinding,TypeBeanOne> {
    public TypeOneHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }

    /**
     * @param typeBeanOne 绑定的数据
     * @param position    数据索引
     */
    @Override
    public void onBindViewHolder(TypeBeanOne typeBeanOne, int position) {

    }
}
