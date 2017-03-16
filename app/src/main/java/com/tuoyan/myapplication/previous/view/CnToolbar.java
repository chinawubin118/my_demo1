package com.tuoyan.myapplication.previous.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

import com.tuoyan.myapplication.R;

/**
 * Created by Administrator on 2016/12/27.
 */

public class CnToolbar extends Toolbar {
    public CnToolbar(Context context) {
        this(context, null);
    }

    public CnToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CnToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //设置toolbar的边距
        setContentInsetsRelative(10, 10);

        if (attrs != null) {
            final TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(getContext(), attrs, R.styleable.CnToolbar, defStyleAttr, 0);

            /**
             * 自定义控件的模式：1.通过TintTypedArray类从attrs.xml中取得自定义属性值
             * 2.如果属性值非空，将其赋值
             */
            final Drawable rightIcon = tintTypedArray.getDrawable(R.styleable.CnToolbar_rightButtonIcon);
            //一定要在这里进行条件判断
            if (rightIcon != null) {
                //setNavigationIcon(navIcon);
//                setRightButtonIcon(rightIcon);
            }

            //默认false
            boolean isShowSearchView = tintTypedArray.getBoolean(R.styleable.CnToolbar_isShowSearchView, false);
            //如果isShowSearchView为true，把Title隐藏
            if (isShowSearchView) {
//                showSearchView();
//                hideTitleView();
            }

            CharSequence rightButtonText = tintTypedArray.getText(R.styleable.CnToolbar_rightButtonText);
            if (rightButtonText != null) {
//                setRightButtonText(rightButtonText);
            }
            tintTypedArray.recycle();
        }
    }
}
