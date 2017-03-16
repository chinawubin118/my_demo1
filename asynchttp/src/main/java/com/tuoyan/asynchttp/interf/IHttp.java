package com.tuoyan.asynchttp.interf;

import android.content.Context;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Administrator on 2015/10/31 0031.
 */
public interface IHttp {
    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    public void download(String url, BinaryHttpResponseHandler responseHandler);

    //指定context 统一管理
    public void get(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    public void post(Context context, String url, RequestParams params, AsyncHttpResponseHandler responseHandler);

    public void download(Context context, String url, BinaryHttpResponseHandler responseHandler);

    /**
     * post json
     * <p>
     * JSONObject jsonParams = new JSONObject();
     * jsonParams.put("notes", "Test api support");
     * jsonParams.toString()
     */
    public void post(Context context, String url, String jsonParams, AsyncHttpResponseHandler responseHandler);


    public void cancelRequests(Context context);

    public void cancelAllRequests();
}
