package com.lixiaomi.mvvmbaselibapplication.http;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/24<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class MyUrl {
    private static final String LOGIN_POST = "user/login";
    private static final String LOGIN_OUT_GET = "user/logout/json";

    public static String getLoginPost() {
        return LOGIN_POST;
    }

    public static String getLoginOutGet() {
        return LOGIN_OUT_GET;
    }
}
