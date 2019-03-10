package com.hangzhou.sz.baser.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hangzhou.sz.baser.R
import com.hangzhou.sz.baser.adapter.TestAdapter
import com.hangzhou.sz.baser.bean.TypeBeanOne
import com.hangzhou.sz.baser.bean.TypeBeanTwo
import com.hangzhou.sz.baser.databinding.LayoutRecyclerviewBinding
import com.hangzhou.sz.baser.recyclerview.XRecyclerView
import com.hangzhou.sz.baser.repository.ItemClickPresenter
import com.hangzhou.sz.baser.utils.toast
import com.hangzhou.sz.baser.viewmodel.TestViewModel

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */
class KotlinFragment : Fragment(), XRecyclerView.LoadingListener,ItemClickPresenter {


    private lateinit var mBinding: LayoutRecyclerviewBinding
    private val viewModel: TestViewModel by lazy { ViewModelProviders.of(this).get(TestViewModel::class.java) }
    private val mAdapter: TestAdapter by lazy { TestAdapter() }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.layout_recyclerview, container, false)
        mBinding.text = "KotlinFragment"
        initView()
        viewModel.refreshData()
        return mBinding.root
    }

    private fun initView() {
        mBinding.rvList.layoutManager = LinearLayoutManager(context)
        mBinding.rvList.adapter = mAdapter
        mBinding.rvList.setLoadingListener(this)
        // 点击整个item 生效
        mAdapter.setItemPresenter(this, 1)
        viewModel.liveDate.observe(this, Observer {
            mBinding.rvList.refreshComplete()
            it?.let { data ->
                if (viewModel.page == 1)
                    mAdapter.clear()
                mAdapter.addAll(data)

            }
        })

    }

    override fun onRefresh() {
        viewModel.refreshData()
    }

    override fun onLoadMore() {

        viewModel.loadMoreData()
    }

    override fun onItemClick(v: View?, t: Any?) {
        if (t is TypeBeanOne)
            context.toast(t.viewType.toString() + "---" + t.index)
        else if (t is TypeBeanTwo)
            context.toast(t.viewType.toString() + "---" + t.index)

    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.onDestroy()
    }

}