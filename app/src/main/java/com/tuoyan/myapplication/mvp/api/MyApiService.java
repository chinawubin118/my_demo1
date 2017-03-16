package com.tuoyan.myapplication.mvp.api;

import com.tuoyan.myapplication.previous.model.PersonNumBean;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/1/5.
 */

public interface MyApiService {

    @FormUrlEncoded
    @POST("phoneMobile.do?act=getNum")
    public Call<PersonNumBean> getPersonNum2(@Field("userId") String userId);

    @GET("phoneMobile.do?act=getNum")
    public Call<PersonNumBean> getPersonNum3(@Query("userId") String userId);
}
