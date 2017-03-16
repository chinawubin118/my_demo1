package com.lotte.lottelibrary.http;

import com.google.gson.Gson;

/**
 * Created by Lotte on 2016/10/9.
 * 得到一个单例Gson对象
 */
public class MyGson {

    private MyGson() {

    }

    //静态内部类实现懒汉式单例
    private static class LazyHolder {
        private static final Gson INSTANCE = new Gson();
    }

    public static final Gson getInstance() {
        return LazyHolder.INSTANCE;
    }
}
