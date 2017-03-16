package com.tuoyan.myapplication.previous.ui.tools;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/1/6.
 */

public class MyBehavior extends CoordinatorLayout.Behavior<TextView> {
    String TAG = "wubin";

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 判断child的布局是否依赖dependency
     *
     * @return 返回false表示child不依赖dependency，ture表示依赖
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, TextView child, View dependency) {
//        super.layoutDependsOn(parent, child, dependency);

        Log.i(TAG, "layoutDependsOn: ");
        return true;
//        return dependency instanceof Button;
    }

    /**
     * 当dependency发生改变时（位置、宽高等），执行这个函数
     *
     * @return 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, TextView child, View dependency) {
        Log.i(TAG, "onDependentViewChanged: ");
        int left = dependency.getLeft();
        int right = dependency.getRight();

        setPosition(child, left, right);
        return true;
    }

    private void setPosition(TextView button, int left, int right) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) button.getLayoutParams();
        params.leftMargin = left;
        params.rightMargin = right;
        button.setLayoutParams(params);
    }
}
