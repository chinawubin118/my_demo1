package com.lotte.lottelibrary.http;

import android.graphics.Bitmap;
import android.text.TextUtils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by Lotte on 2016/10/8.
 * 项目地址:https://github.com/jeasonlzy/okhttp-OkGo
 */
public class OkGoHttp {
    private static IOkGoResult okGoResult;//自己的网络请求回调接口

    //自己后台返回的请求结果标识码
    public final static String CODE_KEY = "code";
    //自己后台返回的提示信息(000:一般认为是完全正常的请求,无需提示用户;类似于001,002等,为请求成功,但是异常的信息,比如密码错误,重复删除等,可以直接提示用户)
    public final static String INFO_KEY = "sucInfo";

    private OkGoHttp() {

    }

    //静态内部类实现懒汉式单例
    private static class LazyHolder {
        private static final OkGoHttp INSTANCE = new OkGoHttp();
    }

    public static OkGoHttp getInstance(IOkGoResult okGoResult) {
        OkGoHttp.okGoResult = okGoResult;
        return LazyHolder.INSTANCE;
    }

    /**
     * 普通的Get请求
     *
     * @param url
     * @param params 可传,也可以直接拼在url里面
     * @param params 可传,也可以直接拼在url里面
     * @param tag
     */
    public void getRequest(String url, HttpParams params, final int tag) {
        OkGo.getInstance().get(url).tag(tag).params(params).cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        try {
                            JSONObject jsonObject = new JSONObject(s);//返回的s是一个json字符串,转成JsonObject对象进行操作
                            String ourCode = jsonObject.getString(CODE_KEY);//自己后台返回的请求结果标识码
                            //提示信息(000:完全正常的请求,无需提示用户;类似于001,002等,为请求成功,但是异常的信息,比如密码错误,重复删除等,可以直接提示用户)
                            String sucInfo = jsonObject.getString(INFO_KEY);
                            if (TextUtils.equals("000", ourCode)) {
                                okGoResult.onRequestSuccess(tag, s);//使用tag标识请求,同时在OK-HTTP中,tag也是标识请求,可以用来取消请求
                            } else {
                                okGoResult.onRequestError(tag, s, ourCode, sucInfo);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        //请求错误
                        if (null != response) {
                            if (response.code() == 0) {//状态码为0,以前是回调onNoConnect()方法,现在继续这种逻辑,只不过换了个方法名
                                //response.code():Returns the HTTP status code(Http状态码)
                                okGoResult.onConnectError();
                            } else {//请求错误/失败
                                okGoResult.onRequestFaild(response.code(), "请求失败,状态码:" + (response.code()));
                            }
                        } else {
                            okGoResult.onRequestFaild(0, "请求超时");
                        }
                    }
                });
    }

    /**
     * 普通的Post请求
     *
     * @param url    请求地址
     * @param params 请求参数 HttpParams
     * @param tag    请求标识
     */
    public void postRequest(String url, HttpParams params, final int tag) {
        OkGo.getInstance().post(url).tag(tag).params(params).cacheMode(CacheMode.DEFAULT)    // 缓存模式，详细请看缓存介绍
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        // s 即为所需要的结果
                        try {
                            JSONObject jsonObject = new JSONObject(s);//返回的s是一个json字符串,转成JsonObject对象进行操作
                            String ourCode = jsonObject.getString(CODE_KEY);//自己后台返回的请求结果标识码
                            //提示信息(000:完全正常的请求,无需提示用户;类似于001,002等,为请求成功,但是异常的信息,比如密码错误,重复删除等,可以直接提示用户)
//                            String sucInfo = jsonObject.getString(INFO_KEY);
                            if (TextUtils.equals("1", ourCode)) {
                                okGoResult.onRequestSuccess(tag, s);//使用tag标识请求,同时在OK-HTTP中,tag也是标识请求,可以用来取消请求
                            } else {
                                okGoResult.onRequestError(tag, s, ourCode, "sucInfo");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        //请求错误
                        if (null != response) {
                            if (response.code() == 0) {//状态码为0,以前是回调onNoConnect()方法,现在继续这种逻辑,只不过换了个方法名
                                //response.code():Returns the HTTP status code(Http状态码)
                                okGoResult.onConnectError();
                            } else {//请求错误/失败
                                okGoResult.onRequestFaild(response.code(), "请求失败,状态码:" + (response.code()));
                            }
                        } else {
                            okGoResult.onRequestFaild(0, "请求超时");
                        }
                    }
                });
    }

    /**
     * 普通的请求 Bitmap 对象
     *
     * @param url 请求地址
     * @param tag 请求标识
     */
    public void getBitmap(String url, final int tag) {
        OkGo.getInstance().get(url).tag(tag)
                .execute(new BitmapCallback() {
                    @Override
                    public void onSuccess(Bitmap bitmap, Call call, Response response) {
                        // bitmap 即为返回的图片数据
                        okGoResult.onGetBitmap(tag, bitmap);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        //请求错误
                        if (null != response) {
                            if (response.code() == 0) {//状态码为0,以前是回调onNoConnect()方法,现在继续这种逻辑,只不过换了个方法名
                                //response.code():Returns the HTTP status code(Http状态码)
                                okGoResult.onConnectError();
                            } else {//请求错误/失败
                                okGoResult.onRequestFaild(response.code(), "图片请求失败,状态码:" + (response.code()));
                            }
                        } else {
                            okGoResult.onRequestFaild(0, "图片请求失败");
                        }
                    }
                });
    }

    /**
     * 普通的文件下载请求
     *
     * @param url        请求地址
     * @param tag        请求标识
     * @param targetPath 文件的下载目标路径
     */
    public void downLoad(String url, String targetPath, final int tag) {
        OkGo.getInstance().get(url).tag(tag)
                .execute(new FileCallback(targetPath) {  //文件下载时，需要指定下载的文件目录和文件名
                    @Override
                    public void onSuccess(File file, Call call, Response response) {
                        // file 即为文件数据，文件保存在指定目录
                        okGoResult.onGetFile(tag, file);
                    }

                    @Override
                    public void downloadProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        //这里回调下载进度(该回调在主线程,可以直接更新ui).....progress进度范围:0.0f ~ 1.0f
                        okGoResult.onDownloadProgress(currentSize, totalSize, progress, networkSpeed);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        //请求错误
                        if (null != response) {
                            if (response.code() == 0) {//状态码为0,以前是回调onNoConnect()方法,现在继续这种逻辑,只不过换了个方法名
                                //response.code():Returns the HTTP status code(Http状态码)
                                okGoResult.onConnectError();
                            } else {//请求错误/失败
                                okGoResult.onRequestFaild(response.code(), "下载失败,状态码:" + (response.code()));
                            }
                        } else {
                            okGoResult.onRequestFaild(0, "下载失败");
                        }
                    }
                });
    }

    /**
     * 普通的文件上传
     *
     * @param url 请求地址
     * @param tag 请求标识
     */
    public void uploadFile(String url, HttpParams params, final int tag) {
        OkGo.getInstance().post(url).tag(tag).params(params)
                .execute(new StringCallback() {

                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        okGoResult.onRequestSuccess(tag, s);
                    }

                    @Override
                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
                        // UI 线程，文件上传过程中回调，只有请求方式包含请求体才回调（GET,HEAD不会回调）
                        okGoResult.upProgress(currentSize, totalSize, progress, networkSpeed);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        //请求错误
                        if (null != response) {
                            if (response.code() == 0) {//状态码为0,以前是回调onNoConnect()方法,现在继续这种逻辑,只不过换了个方法名
                                //response.code():Returns the HTTP status code(Http状态码)
                                okGoResult.onConnectError();
                            } else {//请求错误/失败
                                okGoResult.onRequestFaild(response.code(), "上传失败,状态码:" + (response.code()));
                            }
                        } else {
                            okGoResult.onRequestFaild(0, "上传失败");
                        }
                    }
                });
    }
}
