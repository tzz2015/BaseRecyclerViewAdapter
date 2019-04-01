package com.hangzhou.sz.baser.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hangzhou.sz.baser.BR;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public abstract class BaseRecyclerViewHolder< D extends ViewDataBinding,T extends BaseRecyclerBean> extends RecyclerView.ViewHolder {
    public D binding;
    public BaseRecyclerViewHolder(ViewGroup viewGroup, int layoutId) {
        // 注意要依附 viewGroup，不然显示item不全!!
        super(DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), layoutId, viewGroup, false).getRoot());
        // 得到这个View绑定的Binding
        binding = DataBindingUtil.getBinding(this.itemView);
    }

    /**
     * @param t   绑定的数据
     * @param position 数据索引
     */
    public abstract void onBindViewHolder(T t, final int position);

    /**
     * 当数据改变时，binding会在下一帧去改变数据，如果我们需要立即改变，就去调用executePendingBindings方法。
     */
    void onBaseBindViewHolder(T t, final int position) {
        onBindViewHolder(t, position);
        binding.executePendingBindings();
    }
}
