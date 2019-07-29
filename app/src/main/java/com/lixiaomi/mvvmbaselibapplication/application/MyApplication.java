package com.lixiaomi.mvvmbaselibapplication.application;

import android.app.Activity;
import android.app.Application;

import com.lixiaomi.baselib.config.AppConfigInIt;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/24<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        String SharedPreferences = "DataBindingSP";
        AppConfigInIt.init(this)
                //设置调试模式，默认false
                .withDebug(true)
                //配置SharedPreferences
                .withSharedPreferences(getSharedPreferences(SharedPreferences, Activity.MODE_PRIVATE))
                //默认文件根地址(网络请求缓存使用)
                .withBaseFile("com.xiaomi.lib")
                //baseUrl(网络请求使用)
                .withBaseUrl("https://www.wanandroid.com/")
                //是否信任全部证书,不信任全部，则传进去证书流(网络请求使用)
                .withHttpCertificateFlag(true, null)
                //连接失败后是否重连(网络请求使用)
                .withHttpRetryConnection(false)
                .configure();
    }
}
