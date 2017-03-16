package com.tuoyan.myapplication.previous.api;

import com.tuoyan.myapplication.previous.model.GitUserBean;
import com.tuoyan.myapplication.previous.model.PersonNumBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface ApiService {
    @GET("users/{user}")
    public Call<GitUserBean> getUserInfo(@Path("user") String user);

    @FormUrlEncoded
    @POST("phoneMobile.do?act=getNum")
    public Call<PersonNumBean> getPersonNum2(@Field("userId") String userId);

    @GET("phoneMobile.do?act=getNum")
    public Call<PersonNumBean> getPersonNum3(@Query("userId") String userId);

    @GET("phoneMobile.do?act=getNum")
    Observable<PersonNumBean> getPersonNum4(@Query("userId") String userId);
}
