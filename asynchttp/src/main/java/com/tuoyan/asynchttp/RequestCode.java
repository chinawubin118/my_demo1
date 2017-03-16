package com.tuoyan.asynchttp;

/**
 * http请求编号，由于像登录这种接口，可能会在不同的地方调用，
 * 因此需要设置独特的编号，已防止与其他编号冲突,独特编号从100开始
 * Created by hua on 2015/8/26.
 */
public class RequestCode {
    public final static int CODE_0 = 0;
    public final static int CODE_1 = 1;
    public final static int CODE_2 = 2;
    public final static int CODE_3 = 3;
    public final static int CODE_4 = 4;
    public final static int CODE_5 = 5;
    public final static int CODE_6 = 6;

    public final static int CODE_LOGIN = 101;
}