package com.hangzhou.sz.baser.holder;

import android.view.ViewGroup;

import com.hangzhou.sz.baser.base.BaseRecyclerViewHolder;
import com.hangzhou.sz.baser.bean.GroupBean;
import com.hangzhou.sz.baser.databinding.ItemLinearContentBinding;

/**
 * @author by 刘宇飞 on 2019/4/1.
 * @version 描述：
 */

public class GroupContentHolder extends BaseRecyclerViewHolder<ItemLinearContentBinding,GroupBean> {
    public GroupContentHolder(ViewGroup viewGroup, int layoutId) {
        super(viewGroup, layoutId);
    }

    /**
     * @param groupBean 绑定的数据
     * @param position  数据索引
     */
    @Override
    public void onBindViewHolder(GroupBean groupBean, int position) {

    }
}
