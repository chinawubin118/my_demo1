package com.tuoyan.myapplication.previous.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.lotte.lottelibrary.base.BaseActivity;
import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.api.ApiServiceFactory;
import com.tuoyan.myapplication.previous.model.GitUserBean;
import com.tuoyan.myapplication.previous.model.PersonNumBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class Retrofit2Activity extends BaseActivity {
    private TextView tv_result;
//    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit2);

        tv_result = (TextView) findViewById(R.id.tv_result);

//        progressDialog = new ProgressDialog(this);
//        progressDialog.show();
//
//        Window window = progressDialog.getWindow();
//        window.setBackgroundDrawableResource(R.color.white);
//        window.setGravity(Gravity.CENTER);
//        WindowManager.LayoutParams layoutParams = progressDialog.getWindow().getAttributes();
//        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
//        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
//        layoutParams.gravity = Gravity.CENTER;
//        window.setAttributes(layoutParams);
        showProgress(true);

        ApiServiceFactory.getApiService().getUserInfo("basil2style").enqueue(new Callback<GitUserBean>() {
            @Override
            public void onResponse(Call<GitUserBean> call, Response<GitUserBean> response) {
//                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show();
                tv_result.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<GitUserBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ApiServiceFactory.getOurApiService().getPersonNum2("402880f55809e6d201580a347c950017").enqueue(new Callback<PersonNumBean>() {
            @Override
            public void onResponse(Call<PersonNumBean> call, Response<PersonNumBean> response) {
                Log.i("wubin", "请求2...raw = " + response.raw());
                PersonNumBean personNumBean = response.body();
                Log.i("wubin", "请求2...关注人数 = " + personNumBean.getfNum() + ",粉丝人数 = " + personNumBean.getpNum() + ",访客人数 = " + personNumBean.getlNum());
            }

            @Override
            public void onFailure(Call<PersonNumBean> call, Throwable t) {

            }
        });

        ApiServiceFactory.getOurApiService().getPersonNum3("402880f55809e6d201580a347c950017").enqueue(new Callback<PersonNumBean>() {
            @Override
            public void onResponse(Call<PersonNumBean> call, Response<PersonNumBean> response) {
                Log.i("wubin", "请求3...raw = " + response.raw());
                PersonNumBean personNumBean = response.body();
                Log.i("wubin", "请求3...关注人数 = " + personNumBean.getfNum() + ",粉丝人数 = " + personNumBean.getpNum() + ",访客人数 = " + personNumBean.getlNum());
            }

            @Override
            public void onFailure(Call<PersonNumBean> call, Throwable t) {

            }
        });

        ApiServiceFactory.getOurApiService().getPersonNum4("402880f55809e6d201580a347c950017")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PersonNumBean>() {
                    @Override
                    public void onCompleted() {
                        Log.i("wubin", "请求4..onCompleted.....");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("wubin", "请求4..onError.....");
                        JsonObject jsonObject = new JsonObject();
                    }

                    @Override
                    public void onNext(PersonNumBean personNumBean) {
                        Log.i("wubin", "请求4..onNext.....");
                        Log.i("wubin", "请求4...关注人数 = " + personNumBean.getfNum() + ",粉丝人数 = " + personNumBean.getpNum() + ",访客人数 = " + personNumBean.getlNum());
                    }
                });
    }
}
