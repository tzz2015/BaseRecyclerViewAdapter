package com.hangzhou.sz.baser.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hangzhou.sz.baser.R;
import com.hangzhou.sz.baser.adapter.TestAdapter;
import com.hangzhou.sz.baser.base.BaseRecyclerBean;
import com.hangzhou.sz.baser.bean.GroupBean;
import com.hangzhou.sz.baser.databinding.LayoutGroup2RecyclerviewBinding;
import com.hangzhou.sz.baser.databinding.LayoutGroupRecyclerviewBinding;
import com.hangzhou.sz.baser.recyclerview.BPinnedHeaderItemDecoration;
import com.hangzhou.sz.baser.recyclerview.XPinnedHeaderItemDecoration;
import com.hangzhou.sz.baser.recyclerview.XRecyclerView;
import com.hangzhou.sz.baser.repository.ItemClickPresenter;
import com.hangzhou.sz.baser.utils.RandomString;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class Group2Fragment extends Fragment implements ItemClickPresenter, XRecyclerView.LoadingListener {

    private LayoutGroup2RecyclerviewBinding mBinding;
    private TestAdapter mAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_group2_recyclerview, container, false);
        mBinding.setText("Group2Fragment");
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mBinding.rvList.setLayoutManager(manager);
//        mAdapter = new LinearRecyclerAdapter();
        mAdapter = new TestAdapter();
        mBinding.rvList.setAdapter(mAdapter);
        mBinding.rvList.setLoadingListener(this);
        // 点击对应的按钮生效
        mAdapter.setItemPresenter(this, 1);
        mBinding.rvList.addItemDecoration(new XPinnedHeaderItemDecoration());
        mBinding.rvList.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        initData();

    }

    private void initData() {
        mBinding.rvList.refreshComplete();
        List<BaseRecyclerBean> list=new ArrayList<>();
        for(int i=0;i<30;i++){
            GroupBean groupBean=new GroupBean();
            if(i%5==0){
                groupBean.viewType=TestAdapter.GROUP_TITLE;
                groupBean.content="2019-4-"+(i+1);
            }else {
                groupBean.viewType=TestAdapter.GROUP_CONTENT;
                groupBean.content= RandomString.getRandomWord()+RandomString.getRandomWord()+RandomString.getRandomString(3);
            }
            list.add(groupBean);
        }
//        mAdapter.setData(list);
        mAdapter.addAll(list);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onItemClick(View v, Object t) {
      if(t instanceof GroupBean){
          Toast.makeText(getContext(),((GroupBean) t).content,Toast.LENGTH_SHORT).show();
      }
    }

    @Override
    public void onRefresh() {
        mAdapter.clear();
        initData();
    }

    @Override
    public void onLoadMore() {
        mBinding.rvList.postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        },1000);

    }
}
