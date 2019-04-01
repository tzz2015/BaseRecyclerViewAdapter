package com.hangzhou.sz.baser.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.hangzhou.sz.baser.R;
import com.hangzhou.sz.baser.base.PinnedHeaderAdapter;
import com.hangzhou.sz.baser.bean.GroupBean;

import java.util.List;

public class LinearRecyclerAdapter extends PinnedHeaderAdapter<RecyclerView.ViewHolder> {

	private static final int VIEW_TYPE_ITEM_TIME    = 0;
	private static final int VIEW_TYPE_ITEM_CONTENT = 1;

	private List<GroupBean> mDataList;

	public LinearRecyclerAdapter() {

	}



	public void setData(List<GroupBean> dataList) {
		mDataList = dataList;
		notifyDataSetChanged();
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		if (viewType == VIEW_TYPE_ITEM_TIME) {
			return new TitleHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linear_title, parent, false));
		} else {
			return new ContentHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_linear_content, parent, false));
		}
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		if (getItemViewType(position) == VIEW_TYPE_ITEM_TIME) {
			TitleHolder titleHolder = (TitleHolder) holder;
			titleHolder.mTextTitle.setText(mDataList.get(position).content);
		} else {
			ContentHolder contentHolder = (ContentHolder) holder;
			contentHolder.mTextTitle.setText(mDataList.get(position).content);
		}
	}

	@Override
	public int getItemCount() {
		return mDataList == null ? 0 : mDataList.size();
	}

	@Override
	public int getItemViewType(int position) {
		if (position % 5 == 0) {
			return VIEW_TYPE_ITEM_TIME;
		} else {
			return VIEW_TYPE_ITEM_CONTENT;
		}
	}

	@Override
	public boolean isPinnedPosition(int position) {
		return getItemViewType(position) == VIEW_TYPE_ITEM_TIME;
	}

	static class ContentHolder extends RecyclerView.ViewHolder {

		TextView mTextTitle;

		ContentHolder(View itemView) {
			super(itemView);
			mTextTitle = itemView.findViewById(R.id.text_adapter_content_name);
		}
	}

	static class TitleHolder extends RecyclerView.ViewHolder {

		TextView mTextTitle;

		TitleHolder(View itemView) {
			super(itemView);
			mTextTitle = itemView.findViewById(R.id.text_adapter_title_name);
		}
	}

}
