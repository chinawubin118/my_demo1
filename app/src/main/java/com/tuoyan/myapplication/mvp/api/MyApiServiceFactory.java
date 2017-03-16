package com.tuoyan.myapplication.mvp.api;

import com.tuoyan.myapplication.Constant;
import com.tuoyan.myapplication.previous.api.ApiService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Administrator on 2017/1/5.
 */

public class MyApiServiceFactory {

//    private static MyApiService ourApiService;
//
//    public static MyApiService getOurApiService() {
//        if (null == ourApiService) {
//            // 可以通过实现 Logger 接口更改日志保存位置
//            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
//            OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                    .addInterceptor(logging)
//                    .build();
//
//            Retrofit retrofit = new Retrofit.Builder()
//                    //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
//                    .baseUrl(Constant.BASE_URL)
//                    //增加返回值为String的支持
//                    .addConverterFactory(ScalarsConverterFactory.create())
//                    //增加返回值为Gson的支持(以实体类返回)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    //增加返回值为Oservable<T>的支持
//                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
//                    .client(okHttpClient)
//                    .build();
//            ourApiService = retrofit.create(MyApiService.class);
//        }
//
//        return ourApiService;
//    }

    private static ApiService ourApiService;

    public static ApiService getOurApiService() {
        if (null == ourApiService) {
            // 可以通过实现 Logger 接口更改日志保存位置
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Retrofit retrofit = new Retrofit.Builder()
                    //这里建议：- Base URL: 总是以/结尾；- @Url: 不要以/开头
                    .baseUrl(Constant.BASE_URL)
                    //增加返回值为String的支持
                    .addConverterFactory(ScalarsConverterFactory.create())
                    //增加返回值为Gson的支持(以实体类返回)
                    .addConverterFactory(GsonConverterFactory.create())
                    //增加返回值为Oservable<T>的支持
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            ourApiService = retrofit.create(ApiService.class);
        }

        return ourApiService;
    }
}
