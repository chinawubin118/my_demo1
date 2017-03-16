package com.tuoyan.myapplication.previous.http;

/**
 * Created by Administrator on 2016/10/19.
 */
public class Test {
    private static Test test;

    private Test() {

    }

    public static Test getInstance() {
        if (null == test) {
            test = new Test();
        }
        return test;
    }
}
