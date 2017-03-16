package com.tuoyan.myapplication.previous.view;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.OvershootInterpolator;
import android.view.animation.Transformation;

/**
 * Created by Administrator on 2016/8/9.
 */
public class ResetHeightAnimation extends Animation {
    private View view;//需要执行动画的view
    private int targetHeight;//目标高度
    private int originalHeight;//变化前原始高度
    private int totalValue;

    public ResetHeightAnimation(View view, int targetHeight) {
        this.view = view;

        if (null != view) {
            originalHeight = view.getHeight();
        }
        this.targetHeight = targetHeight;
        totalValue = targetHeight - originalHeight;

        setDuration(300);
        setInterpolator(new OvershootInterpolator());
    }

    /**
     * @param interpolatedTime 标识动画执行的百分比
     * @param t
     */
    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        int newHeight = (int) (originalHeight + totalValue * interpolatedTime);
        view.getLayoutParams().height = newHeight;
        view.requestLayout();
    }
}
