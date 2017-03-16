package com.tuoyan.myapplication.mvp_demo.model;

/**
 * Created by Administrator on 2017/1/7.
 */

//Model接口
public interface IModel {//9 内嵌ICallback接口

    void getData(ICallback callback);

    public interface ICallback {
        public void onResult(String data);
    }
}