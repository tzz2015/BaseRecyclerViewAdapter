package com.hangzhou.sz.baser.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hangzhou.sz.baser.MainActivity;
import com.hangzhou.sz.baser.R;
import com.hangzhou.sz.baser.adapter.TestAdapter;
import com.hangzhou.sz.baser.base.BaseRecyclerBean;
import com.hangzhou.sz.baser.bean.TypeBeanOne;
import com.hangzhou.sz.baser.bean.TypeBeanTwo;
import com.hangzhou.sz.baser.databinding.LayoutRecyclerviewBinding;
import com.hangzhou.sz.baser.recyclerview.XRecyclerView;
import com.hangzhou.sz.baser.repository.ItemClickPresenter;
import com.hangzhou.sz.baser.utils.PinyinComparator;
import com.hangzhou.sz.baser.utils.PinyinUtils;
import com.hangzhou.sz.baser.utils.TitleItemDecoration;
import com.hangzhou.sz.baser.viewmodel.TestViewModel;

import java.util.Collections;
import java.util.List;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class GroupFragment extends Fragment implements XRecyclerView.LoadingListener, ItemClickPresenter {

    private LayoutRecyclerviewBinding mBinding;
    private TestAdapter mAdapter;
    private TestViewModel viewModel;
    private TitleItemDecoration mDecoration;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_recyclerview, container, false);
        mBinding.setText("GroupFragment");
        viewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        initView();
        return mBinding.getRoot();
    }

    private void initView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mBinding.rvList.setLayoutManager(manager);
        mBinding.rvList.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        mAdapter = new TestAdapter();
        mBinding.rvList.setAdapter(mAdapter);
        mBinding.rvList.setLoadingListener(this);
        // 点击对应的按钮生效
        mAdapter.setItemPresenter(this, 2);
        viewModel.refreshData();
        viewModel.getLiveDate().observe(this, new Observer<List<BaseRecyclerBean>>() {
            @Override
            public void onChanged(@Nullable List<BaseRecyclerBean> baseRecyclerBeans) {

                mBinding.rvList.refreshComplete();
                int page = viewModel.getPage();
                if (page == 1) {
                    mAdapter.clear();
                }
                if(page>3){
                    mBinding.rvList.noMoreLoading();
                }
                mAdapter.addAll(baseRecyclerBeans);
                sortData(mAdapter.getData());
                if(mDecoration!=null){
                    mBinding.rvList.removeItemDecoration(mDecoration);
                }
                mDecoration = new TitleItemDecoration(getContext(), baseRecyclerBeans);
                mBinding.rvList.addItemDecoration(mDecoration);
            }
        });
    }

    private void sortData(List<BaseRecyclerBean> list) {
        PinyinComparator  mComparator = new PinyinComparator();
        for(BaseRecyclerBean bean:list){
            //汉字转换成拼音
            String pinyin;
            if(bean instanceof TypeBeanOne){
                pinyin = PinyinUtils.getPingYin(((TypeBeanOne)bean).random);
               bean.setLetters(pinyin.substring(0, 1).toUpperCase());
            }else {
                pinyin = PinyinUtils.getPingYin(((TypeBeanTwo)bean).random);
                bean.setLetters(pinyin.substring(0, 1).toUpperCase());
            }
        }
        // 根据a-z进行排序源数据
        Collections.sort(list, mComparator);
    }


    @Override
    public void onRefresh() {
        viewModel.refreshData();
    }

    /**
     * 加载更多回调
     */
    @Override
    public void onLoadMore() {
        viewModel.loadMoreData();
    }

    @Override
    public void onItemClick(View v, Object t) {
        if (t instanceof TypeBeanOne)
            Toast.makeText(getContext(), ((TypeBeanOne) t).viewType + "---" + ((TypeBeanOne) t).index, Toast.LENGTH_SHORT).show();
        else if (t instanceof TypeBeanTwo)
            Toast.makeText(getContext(), ((TypeBeanTwo) t).viewType + "---" + ((TypeBeanTwo) t).index, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewModel.onDestroy();
    }
}
