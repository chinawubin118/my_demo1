package com.tuoyan.myapplication.previous.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.lotte.lottelibrary.base.BaseActivity;
import com.tuoyan.myapplication.Constant;
import com.tuoyan.myapplication.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class RetrofitActivity extends BaseActivity {
    private TextView tv_request_info, tv_request_result;//请求信息,请求结果
    private TextView tv_request_info_2, tv_request_result_2;//请求信息2,请求结果2

    //Retrofit 接口
    public interface RequestServes {
        @POST("phoneMobile.do?act=getNum")
        Call<String> requestNum(@Query("userId") String userId);

        @POST("phoneMobile.do?act=getNum")
        public Observable<String> requestNum2(@Query("userId") String userId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        tv_request_info = (TextView) findViewById(R.id.tv_request_info);
        tv_request_result = (TextView) findViewById(R.id.tv_request_result);

        tv_request_info_2 = (TextView) findViewById(R.id.tv_request_info_2);
        tv_request_result_2 = (TextView) findViewById(R.id.tv_request_result_2);

        //Retrofit传统的 Callback 形式的 API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        RequestServes requestSerives = retrofit.create(RequestServes.class);//这里采用的是Java的动态代理模式
        Call<String> call = requestSerives.requestNum("402880f55809e6d201580a347c950017");//传入我们请求的键值对的值

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.e("wubin", "raw = " + response.raw());
                tv_request_info.setText(response.raw().toString());
                if (response.isSuccessful()) {//请求成功
                    Log.e("wubin", "body = " + response.body());
                    tv_request_result.setText(response.body());
                } else {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("wubin", "失败");
            }
        });

        /* ********* RxJava ********* */
        Observable<String> myObservable = Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> sub) {
                        sub.onNext("Hello, world!");
                        sub.onCompleted();
                    }
                }
        );

        Subscriber<String> mySubscriber = new Subscriber<String>() {
            @Override
            public void onNext(String s) {
                Log.e("wubin", "s = " + s);
            }

            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }
        };

        myObservable.subscribe(mySubscriber);
//        myObservable.subscribe(s -> System.out.print(s));
//        Consumer<String> consumer;

        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
            }
        };

        Observable.just(1, 2, 3, 4)
                .subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer number) {
                        Log.d("wubin", "currentThread name:" + Thread.currentThread().getName());
                    }
                });

        /* ******** RxJava版Retrofit ******** */
        requestSerives.requestNum2("402880f55809e6d201580a347c950017")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onNext(String user) {
                        tv_request_result_2.setText("RxJava版Retrofit user = " + user);
                    }

                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d("wubin", "请求失败了.....");
                    }
                });
    }
}
