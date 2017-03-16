package com.lotte.lottelibrary.http;

import android.graphics.Bitmap;

import java.io.File;

/**
 * Created by Administrator on 2016/10/8.
 */
public interface IOkGoResult {

    /**
     * 请求成功
     */
    public void onRequestSuccess(int tag, String jsonStr);

    /**
     * 请求成功Bitmap的时候执行
     */
    public void onGetBitmap(int tag, Bitmap bitmap);

    /**
     * 文件下载中的时候执行
     *
     * @param currentSize  当前大小
     * @param totalSize    文件总大小
     * @param progress     进度范围:0.0f ~ 1.0f
     * @param networkSpeed 网速
     */
    public void onDownloadProgress(long currentSize, long totalSize, float progress, long networkSpeed);

    /**
     * 文件下载成功的时候执行
     */
    public void onGetFile(int tag, File file);

    /**
     * 文件上传中的时候执行
     *
     * @param currentSize
     * @param totalSize
     * @param progress
     * @param networkSpeed
     */
    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed);

    /**
     * 请求错误:其实也是请求成功的一种,主要表现为
     *
     * @param errorCode 自己家定义的,像是"001","002",003等非"000"码
     * @param errorInfo 用来提示用户的错误信息
     */
    public void onRequestError(int tag,String jsonStr, String errorCode, String errorInfo);

    /**
     * 请求失败
     *
     * @param statusCode HTTP状态码
     */
    public void onRequestFaild(int statusCode, String failMessage);

    /**
     * 连接错误
     */
    public void onConnectError();
}
