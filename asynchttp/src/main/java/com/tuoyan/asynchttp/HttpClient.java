package com.tuoyan.asynchttp;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.io.IOException;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Administrator on 2015/10/31 0031.
 */
public class HttpClient {
    public final static String TAG = "async";
    /**
     * 私有化的构造方法，保证外部的类不能通过构造器来实例化。
     */
    private HttpClient() {

    }
    /**
     * 内部类，用于实现lzay机制
     */
    private static class SingletonHolder {
        /**
         * 单例变量
         */
        private static HttpClient instance = new HttpClient();
    }


}
