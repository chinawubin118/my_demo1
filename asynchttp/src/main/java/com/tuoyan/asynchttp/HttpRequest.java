package com.tuoyan.asynchttp;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.tuoyan.asynchttp.interf.INetResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

/**
 * 公用的网络请求辅助类
 * 网路请求的第一层过滤（统一处理错误结果）
 */
public abstract class HttpRequest {
    public final static String TAG = "httplog";
    public final static String SUCCEED_KEY = "succeed";
    public final static String SUCINFO_KEY = "sucInfo";
    protected INetResult mResult;
    protected Context mContext;

    public HttpRequest(Context context, INetResult iNetResult) {
        mContext = context;
        mResult = iNetResult;
    }

    /**
     * 得到结果后，对结果处理逻辑
     *
     * @param jsonObject  网络请求返回的结果
     * @param requestCode 区别请求号码
     * @throws IOException
     */
    public abstract void onRequestSuccess(JSONObject jsonObject, int requestCode) throws IOException;

    private void _getRequest(String url, Map<String, String> params, final int requestCode) {

        RequestParams ajaxParams = new RequestParams(params);
        Log.d(TAG, AsyncHttpClient.getUrlWithQueryString(true, url, ajaxParams));

        Http.getInstance().get(mContext, url, ajaxParams, new TextHttpResponseHandler() {

            @Override
            public void onCancel() {
                super.onCancel();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                Log.d(TAG, responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    String succeed = jsonObject.getString(SUCCEED_KEY);
                    String sucInfo = jsonObject.getString(SUCINFO_KEY);
                    if (ResponseCode.SUCCESS.equals(succeed)) {
                        onRequestSuccess(jsonObject, requestCode);
                        mResult.onRequestSuccess(requestCode);
                    } else {
                        mResult.onRequestError(requestCode, succeed, sucInfo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
                Log.d(TAG, "statusCode:" + statusCode + " Body:" + responseBody);
                if (statusCode == 0)
                    mResult.onNoConnect();
                else
                    mResult.onRequestFaild("" + statusCode, responseBody);
            }
        });
    }

    /**
     * POST 请求
     *
     * @param requestCode 自定义这是第几个post请求，用于结果的区分
     */
    protected void postRequest(String url, RequestParams params, final int requestCode) {

        Log.d(TAG, "POST: " + AsyncHttpClient.getUrlWithQueryString(true, url, params));

        Http.getInstance().post(mContext, url, params, new TextHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                Log.d(TAG, responseBody);
                String errorInfo = "";
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    String succeed = jsonObject.getString(SUCCEED_KEY);
                    String sucInfo = jsonObject.getString(SUCINFO_KEY);
                    if (ResponseCode.SUCCESS.equals(succeed)) {
                        onRequestSuccess(jsonObject, requestCode);
                        mResult.onRequestSuccess(requestCode);
                    } else {
                        mResult.onRequestError(requestCode, succeed, sucInfo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable error) {
                Log.d(TAG, "statusCode:" + statusCode + " Body:" + responseBody);

                if (statusCode == 0)
                    mResult.onNoConnect();
                else
                    mResult.onRequestFaild("" + statusCode, responseBody);
            }
        });
    }

    /**
     * Post json 请求
     *
     * @param url         接口地址
     * @param jsonParams  请求参数 json字符串
     * @param requestCode 请求编号，区分返回的结果
     */
    protected void postRequest(String url, String jsonParams, final int requestCode) {
        Log.d(TAG, "POST: " + url + " JSONParams:" + jsonParams);

        Http.getInstance().post(mContext, url, jsonParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseBody, Throwable throwable) {
                Log.d(TAG, "statusCode:" + statusCode + " Body:" + responseBody);

                if (statusCode == 0)
                    mResult.onNoConnect();
                else
                    mResult.onRequestFaild("" + statusCode, responseBody);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseBody) {
                Log.d(TAG, responseBody);
                String errorInfo = "";
                try {
                    JSONObject jsonObject = new JSONObject(responseBody);
                    String succeed = jsonObject.getString(SUCCEED_KEY);
                    String sucInfo = jsonObject.getString(SUCINFO_KEY);
                    if (ResponseCode.SUCCESS.equals(succeed)) {
                        onRequestSuccess(jsonObject, requestCode);
                        mResult.onRequestSuccess(requestCode);
                    } else {
                        mResult.onRequestError(requestCode, succeed, sucInfo);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}