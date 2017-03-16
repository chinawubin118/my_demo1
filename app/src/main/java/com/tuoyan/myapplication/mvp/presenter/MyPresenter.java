package com.tuoyan.myapplication.mvp.presenter;

import com.tuoyan.myapplication.mvp.ui.IMyActivity;
import com.tuoyan.myapplication.previous.api.ApiServiceFactory;
import com.tuoyan.myapplication.previous.model.PersonNumBean;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/7.
 */

public class MyPresenter implements IMyPresenter {
    private IMyActivity iMyActivity;

    public MyPresenter(IMyActivity iMyActivity) {
        this.iMyActivity = iMyActivity;
    }

    @Override
    public void onQueryClick() {
        String content = iMyActivity.getData();

        ApiServiceFactory.getOurApiService().getPersonNum4(content)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PersonNumBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PersonNumBean personNumBean) {
                        String data = "访客:" + personNumBean.getfNum() + ",粉丝:" + personNumBean.getlNum() + "关注:" + personNumBean.getpNum();
                        iMyActivity.setDataToView(data);
                    }
                });
    }
}
