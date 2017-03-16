package com.tuoyan.myapplication.previous.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * 长按可以选择文字复制的textView
 * Created by Administrator on 2016/5/11.
 */
public class MyTextView extends EditText {
    public MyTextView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected boolean getDefaultEditable() {//等同于在布局文件中设置 android:editable="false"
        return false;
    }
}

