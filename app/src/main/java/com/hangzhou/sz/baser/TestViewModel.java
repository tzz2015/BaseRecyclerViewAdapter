package com.hangzhou.sz.baser;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Handler;
import android.os.Message;

import com.hangzhou.sz.baser.base.BaseRecyclerBean;
import com.hangzhou.sz.baser.bean.TypeBeanOne;
import com.hangzhou.sz.baser.bean.TypeBeanTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建 by 刘宇飞 on 2019/3/10.
 * 描述：
 */

public class TestViewModel extends ViewModel {
    private MutableLiveData<List<BaseRecyclerBean>> liveDate = new MutableLiveData<>();
    private int page = 1;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            backData();
        }
    };

    /**
     * 模拟网络返回数据
     */
    private void backData() {
        List<BaseRecyclerBean> localList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                TypeBeanOne typeBeanOne = new TypeBeanOne(i);
                typeBeanOne.viewType = 1;
                localList.add(typeBeanOne);
            } else {
                TypeBeanTwo typeBeanTwo = new TypeBeanTwo(i);
                typeBeanTwo.viewType = 2;
                localList.add(typeBeanTwo);
            }
        }
        liveDate.setValue(localList);
    }

    /**
     * 监听数据变化
     *
     * @return
     */
    public MutableLiveData<List<BaseRecyclerBean>> getLiveDate() {
        return liveDate;
    }

    /**
     * 返回页码
     */
    public int getPage() {
        return page;
    }

    /**
     * 模拟网络请求数据
     */
    public void getDataFormNet() {
        handler.sendEmptyMessageDelayed(1, 1000);
    }

    /**
     * 刷新数据
     */
    public void refreshData() {
        page = 1;
        getDataFormNet();
    }

    /**
     * 加载更多
     */
    public void loadMoreData() {
        page++;
        getDataFormNet();
    }

    public void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        handler.removeMessages(1);

    }
}
