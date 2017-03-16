package com.tuoyan.myapplication.mvp_demo.presenter;

import android.view.View;

/**
 * Created by Administrator on 2017/1/7.
 */

//Presenter的接口
public interface IPresenter{  //5
    void onCreate();
    void performOnClick(View view);
}
