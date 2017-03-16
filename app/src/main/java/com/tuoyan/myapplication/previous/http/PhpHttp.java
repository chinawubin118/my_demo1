//package com.tuoyan.myapplication.http;
//
//import android.content.Context;
//
//import com.loopj.android.http.RequestParams;
//import com.tuoyan.asynchttp.HttpRequest;
//import com.tuoyan.asynchttp.RequestCode;
//import com.tuoyan.asynchttp.interf.INetResult;
//
//import org.json.JSONObject;
//
//import java.io.IOException;
//
///**
// * Created by Administrator on 2016/9/30.
// */
//public class PhpHttp extends HttpRequest {
//    public PhpHttp(Context context, INetResult iNetResult) {
//        super(context, iNetResult);
//    }
//
//    public void requestPhp(String name, String age, String url) {
//        RequestParams params = new RequestParams();
//        params.put("name", name);
//        params.put("age", age);
//        postRequest(url, params, RequestCode.CODE_0);
//    }
//
//    @Override
//    public void onRequestSuccess(JSONObject jsonObject, int requestCode) throws IOException {
//
//    }
//}
