package com.tuoyan.myapplication.mvp_demo.model;

import static android.os.AsyncTask.execute;

/**
 * Created by Administrator on 2017/1/7.
 */

//Model实现
public class Model implements IModel {

    public void getData(final ICallback callback) {
        execute(new Runnable() {
            public void run() {             //ugly
//                try {
//                    Thread.sleep(3000);//这里是耗时操作
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                callback.onResult("hello world(this data is from model)");//10 返回数据
            }
        });
    }
}
