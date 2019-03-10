package com.hangzhou.sz.baser.holder;

import android.view.ViewGroup;

import com.hangzhou.sz.baser.base.BaseRecyclerViewHolder;
import com.hangzhou.sz.baser.bean.TypeBeanTwo;
import com.hangzhou.sz.baser.databinding.ItemTypeTwoBinding;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class TypeTwoHolder extends BaseRecyclerViewHolder<ItemTypeTwoBinding,TypeBeanTwo> {
    public TypeTwoHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }

    /**
     * @param typeBeanTwo 绑定的数据
     * @param position    数据索引
     */
    @Override
    public void onBindViewHolder(TypeBeanTwo typeBeanTwo, int position) {

    }
}
