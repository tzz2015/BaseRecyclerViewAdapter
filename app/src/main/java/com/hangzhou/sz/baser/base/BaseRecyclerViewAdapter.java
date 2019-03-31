package com.hangzhou.sz.baser.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hangzhou.sz.baser.BR;
import com.hangzhou.sz.baser.repository.ItemClickPresenter;
import com.hangzhou.sz.baser.repository.ItemLongClickPresenter;
import com.hangzhou.sz.baser.utils.PinyinComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    private List<BaseRecyclerBean> data = new ArrayList<>();
    private ItemClickPresenter itemPresenter;
    private ItemLongClickPresenter itemLongClickPresenter;
    // 1:整个item点击事件 2：布局点个控件点击事件 需要在布局绑定点击事件
    private int type = 1;


    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, final int position) {
        holder.binding.setVariable(BR.item, data.get(position));
        if (itemPresenter != null) {
            if (type == 2)
                holder.binding.setVariable(BR.presenter, itemPresenter);
            else
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemPresenter.onItemClick(v, data.get(position));
                    }
                });
        }
        if(itemLongClickPresenter!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    itemLongClickPresenter.onItemClick(v, data.get(position));
                    return false;
                }
            });
        }


    }

    /**
     * 设置item 控件点击事件回调
     */
    public void setItemPresenter(ItemClickPresenter itemPresenter, int type) {
        this.itemPresenter = itemPresenter;
        this.type = type;
    }

    /**
     * 长按事件
     */
    public void setItemLongClickPresenter(ItemLongClickPresenter itemLongClickPresenter){
        this.itemLongClickPresenter=itemLongClickPresenter;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position) instanceof BaseRecyclerBean) {
            return (data.get(position)).viewType;
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void addAll(List<BaseRecyclerBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }



    public void add(BaseRecyclerBean object) {
        data.add(object);
    }

    public void add(int position, BaseRecyclerBean object) {
        data.add(position, object);
    }

    public void clear() {
        data.clear();
    }

    public void remove(BaseRecyclerBean object) {
        data.remove(object);
    }

    public void remove(int position) {
        data.remove(position);
    }

    public void removeAll(List<BaseRecyclerBean> data) {
        this.data.retainAll(data);
        notifyDataSetChanged();
    }

    public List<BaseRecyclerBean> getData() {
        return data;
    }
}
