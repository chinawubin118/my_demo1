package com.tuoyan.myapplication.mvp_demo.presenter;

import android.view.View;

import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.mvp_demo.model.IModel;
import com.tuoyan.myapplication.mvp_demo.model.Model;
import com.tuoyan.myapplication.mvp_demo.view.IView;

/**
 * Created by Administrator on 2017/1/7.
 */

//Presenter的实现
public class Presenter implements IPresenter {
    private IView view;   //6 拥有View与Model
    private IModel model;

    public Presenter(IView view) {
        this.view = view;
        model = new Model();
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void performOnClick(View myView) {
        switch (myView.getId()) {
            case R.id.button:
                model.getData(new IModel.ICallback() {//7 传接口给Model
                    public void onResult(String data) {
                        String dataFromPresenter = data + " new data(new data is from presenter)"; //8 加工数据
                        view.setData(dataFromPresenter);
                    }
                });
                break;
            case R.id.my_button:
                break;
        }
    }
}
