package com.lotte.lottelibrary.base;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.lotte.lottelibrary.http.IOkGoResult;
import com.lotte.lottelibrary.http.ProgressHUD;
import com.lotte.lottelibrary.util.MyToast;

import java.io.File;

/**
 * Created by Administrator on 2016/10/8.
 */
public class BaseActivity extends AppCompatActivity implements View.OnClickListener, IOkGoResult {

    /**
     * 点击事件:子类的控件可直接设置点击监听,重新该方法处理点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {

    }

    private ProgressHUD mProgressHUD;

    /**
     * 进度条是否正在显示
     */
    public boolean isProgressShowing() {
        if (null == mProgressHUD) {
            return false;
        }
        try {
            return mProgressHUD.isShowing();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 显示加载进度条
     *
     * @param show
     */
    public void showProgress(boolean show) {
        try {
            showProgressWithText(show, "Loading...");
        } catch (Exception e) {

        }
    }

    /**
     * 显示加载进度条
     *
     * @param show
     * @param message
     */
    public void showProgressWithText(boolean show, String message) {
        try {
            if (show) {
                if (isProgressShowing()) {//显示进度条的时候,如果发现有一个正在显示着,则先取消显示
                    if (mProgressHUD != null) {
                        mProgressHUD.dismiss();
                    }
                }
                //Context context, CharSequence message, boolean indeterminate, boolean cancelable,DialogInterface.OnCancelListener cancelListener
                mProgressHUD = ProgressHUD.show(this, message, true, true, null);
            } else {
                if (mProgressHUD != null) {
                    mProgressHUD.dismiss();
                }
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onRequestSuccess(int tag, String jsonStr) {
        showProgress(false);
    }

    @Override
    public void onGetBitmap(int tag, Bitmap bitmap) {
        showProgress(false);
    }

    @Override
    public void onDownloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

    }

    @Override
    public void onGetFile(int tag, File file) {
        showProgress(false);
    }

    @Override
    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {

    }

    @Override
    public void onRequestError(int tag, String jsonStr, String errorCode, String errorInfo) {
        showProgress(false);
        MyToast.showShortToast(getApplicationContext(), errorInfo);
    }

    @Override
    public void onRequestFaild(int statusCode, String failMessage) {
        showProgress(false);
        MyToast.showShortToast(getApplicationContext(), failMessage);
    }

    @Override
    public void onConnectError() {
        showProgress(false);
        MyToast.showShortToast(getApplicationContext(), "连接错误,请重试");
    }
}
