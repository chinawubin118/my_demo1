package com.tuoyan.myapplication.previous.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

/**
 * Created by Lotte on 2016/9/28.
 * 下拉视差,并且支持悬浮控件的ScrollView
 */
public class ParallaxAndFloatScrollView extends ScrollView {
    private Context context;

    public ParallaxAndFloatScrollView(Context context) {
        super(context);
    }

    public ParallaxAndFloatScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ParallaxAndFloatScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private RelativeLayout rl_bg;
    private int originalHeight;//原始高度

    public void setParallaxImageView(RelativeLayout rl_bg) {
        this.rl_bg = rl_bg;
    }

    public void setOriginalHeight(int originalHeight) {
        this.originalHeight = originalHeight;
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        // deltaY : 竖直方向的瞬时偏移量 / 变化量 dx   顶部到头下拉为-, 底部到头上拉为+
        // scrollY : 竖直方向的偏移量 / 变化量
        // scrollRangeY : 竖直方向滑动的范围
        // maxOverScrollY : 竖直方向最大滑动范围
        // isTouchEvent : 是否是手指触摸滑动, true为手指, false为惯性
        if (isTouchEvent && deltaY < 0) {//到头,并且是手动向下滑动
            float newHeight = (int) (rl_bg.getHeight() + Math.abs(deltaY / 5.6f));
            rl_bg.getLayoutParams().height = (int) newHeight;
            rl_bg.requestLayout();
            if (Math.abs(deltaY) > 100) {
                maxOverScrollY = 100;
            }
            if (null != onSizeChangeListener) {
                onSizeChangeListener.onSizeChange(Math.abs((rl_bg.getHeight() - originalHeight) / 1.3f));
            }
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

    private OnSizeChangeListener onSizeChangeListener;//尺寸改变监听器

    public void setOnSizeChangeListener(OnSizeChangeListener onSizeChangeListener) {
        this.onSizeChangeListener = onSizeChangeListener;
    }

    public interface OnSizeChangeListener {
        public void onSizeChange(float distanceY);

        public void onRelease(float originalHeight);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_UP:
                ResetHeightAnimation animation = new ResetHeightAnimation(rl_bg, originalHeight);
                startAnimation(animation);
                if (null != onSizeChangeListener) {
                    onSizeChangeListener.onRelease(originalHeight);
                }
                break;
        }
        return super.onTouchEvent(ev);
    }

    /**
     * 悬浮的布局是否显示了
     */
    private boolean isFloatVisible = false;
    /**
     * 悬浮布局,距离顶部的偏移量
     */
    private int deltaTop = 0;

    public void setDeltaTop(int deltaTop) {
        this.deltaTop = deltaTop;
    }

    public boolean isFloatVisible() {
        return isFloatVisible;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);

        if (null != srcollingListener) {
            srcollingListener.srcrolling(l, t, oldl, oldt);//执行滚动监听
        }

        if (mTopView != null && mFlowView != null) {
            if (t >= mTopView.getHeight() - deltaTop) {// - deltaTop .....fixed by wubin
                mFlowView.setVisibility(View.VISIBLE);

                isFloatVisible = true;
            } else {
                mFlowView.setVisibility(View.GONE);

                isFloatVisible = false;
            }
        }
    }

    /**
     * 监听浮动view的滚动状态
     *
     * @param topView  顶部区域view，即当ScrollView滑动的高度要大于等于哪个view的时候隐藏floatview
     * @param flowView 浮动view，即要哪个view停留在顶部
     */
    public void listenerFlowViewScrollState(View topView, View flowView) {
        mTopView = topView;
        mFlowView = flowView;
    }

    View mTopView;
    View mFlowView;

    SrcollingListener srcollingListener;//滚动事件监听器

    public void setSrcollingListener(SrcollingListener srcollingListener) {
        this.srcollingListener = srcollingListener;
    }

    public interface SrcollingListener {
        void srcrolling(int l, int t, int oldl, int oldt);
    }
}
