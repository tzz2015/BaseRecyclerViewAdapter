package com.hangzhou.sz.baser.recyclerview;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hangzhou.sz.baser.base.BaseRecyclerViewAdapter;
import com.hangzhou.sz.baser.base.BaseRecyclerViewHolder;

/**
 * PinnedHeader对应的ItemDecoration
 */
public class XPinnedHeaderItemDecoration extends RecyclerView.ItemDecoration implements IPinnedHeaderDecoration {

    private Rect mPinnedHeaderRect = null;
    private int mPinnedHeaderPosition = -1;

    /**
     * 把要固定的View绘制在上层
     */
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent instanceof XRecyclerView) {
            WrapAdapter wrapAdapter = (WrapAdapter) parent.getAdapter();
            RecyclerView.Adapter mAdapter = wrapAdapter.getAdapter();
            if (mAdapter instanceof BaseRecyclerViewAdapter && parent.getChildCount() > 0) {
                BaseRecyclerViewAdapter adapter = (BaseRecyclerViewAdapter) mAdapter;
                //找到要固定的pin view
                View firstView = parent.getChildAt(0);
                int firstAdapterPosition = parent.getChildAdapterPosition(firstView)-wrapAdapter.getHeadersCount();
                int pinnedHeaderPosition = getPinnedHeaderViewPosition(firstAdapterPosition, adapter);
                mPinnedHeaderPosition = pinnedHeaderPosition;
                if (pinnedHeaderPosition != -1) {
                    RecyclerView.ViewHolder pinnedHeaderViewHolder = adapter.onCreateViewHolder(parent, adapter.getItemViewType(pinnedHeaderPosition));
                    adapter.onBindViewHolder((BaseRecyclerViewHolder) pinnedHeaderViewHolder, pinnedHeaderPosition);
                    //要固定的view
                    View pinnedHeaderView = ((BaseRecyclerViewHolder) pinnedHeaderViewHolder).itemView;
                    ensurePinnedHeaderViewLayout(pinnedHeaderView, parent);
                    int sectionPinOffset = 0;
                    for (int index = wrapAdapter.getHeadersCount(); index < parent.getChildCount()  - wrapAdapter.getFootersCount(); index++) {
                        if (adapter.isPinnedPosition(parent.getChildAdapterPosition(parent.getChildAt(index))-wrapAdapter.getHeadersCount())) {
                            View sectionView = parent.getChildAt(index);
                            int sectionTop = sectionView.getTop();
                            int pinViewHeight = pinnedHeaderView.getHeight();
                            if (sectionTop < pinViewHeight && sectionTop > 0) {
                                sectionPinOffset = sectionTop - pinViewHeight;
                            }
                        }
                    }
                    int saveCount = c.save();
                    RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) pinnedHeaderView.getLayoutParams();
                    if (layoutParams == null) {
                        throw new NullPointerException("PinnedHeaderItemDecoration");
                    }
                    c.translate(layoutParams.leftMargin, sectionPinOffset);
                    c.clipRect(0, 0, parent.getWidth(), pinnedHeaderView.getMeasuredHeight());
                    pinnedHeaderView.draw(c);
                    c.restoreToCount(saveCount);
                    if (mPinnedHeaderRect == null) {
                        mPinnedHeaderRect = new Rect();
                    }
                    mPinnedHeaderRect.set(0, 0, parent.getWidth(), pinnedHeaderView.getMeasuredHeight() + sectionPinOffset);
                } else {
                    mPinnedHeaderRect = null;
                }

            }
        }
    }

    /**
     * 要给每个item设置间距主要靠这个函数来实现
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
    }

    /**
     * 根据第一个可见的adapter的位置去获取临近的一个要固定的position的位置
     *
     * @param adapterFirstVisible 第一个可见的adapter的位置
     * @return -1：未找到 >=0 找到位置
     */
    private int getPinnedHeaderViewPosition(int adapterFirstVisible, BaseRecyclerViewAdapter adapter) {
        for (int index = adapterFirstVisible; index >= 0; index--) {
            if (adapter.isPinnedPosition(index)) {
                return index;
            }
        }
        return -1;
    }

    private void ensurePinnedHeaderViewLayout(View pinView, RecyclerView recyclerView) {
        if (pinView.isLayoutRequested()) {
            /**
             * 用的是RecyclerView的宽度测量，和RecyclerView的宽度一样
             */
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) pinView.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("PinnedHeaderItemDecoration");
            }
            int widthSpec = View.MeasureSpec.makeMeasureSpec(
                    recyclerView.getMeasuredWidth() - layoutParams.leftMargin - layoutParams.rightMargin, View.MeasureSpec.EXACTLY);

            int heightSpec;
            if (layoutParams.height > 0) {
                heightSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.height, View.MeasureSpec.EXACTLY);
            } else {
                heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            }
            pinView.measure(widthSpec, heightSpec);
            pinView.layout(0, 0, pinView.getMeasuredWidth(), pinView.getMeasuredHeight());
        }
    }

    @Override
    public Rect getPinnedHeaderRect() {
        return mPinnedHeaderRect;
    }

    @Override
    public int getPinnedHeaderPosition() {
        return mPinnedHeaderPosition;
    }
}
