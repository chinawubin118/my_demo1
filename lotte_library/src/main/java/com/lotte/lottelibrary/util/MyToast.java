package com.lotte.lottelibrary.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Lotte on 2016/10/8.
 * Toast工具,不创建新的Toast对象(可以直接修改Toast上面的文字)
 */
public class MyToast {
    private static Toast mToast;

    /**
     * 显示短时间的Toast
     */
    public static void showShortToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    public static void showLongToast(Context context, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }
}
