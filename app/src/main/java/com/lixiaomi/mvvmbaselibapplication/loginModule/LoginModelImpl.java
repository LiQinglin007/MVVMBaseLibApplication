package com.lixiaomi.mvvmbaselibapplication.loginModule;


import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.net.okhttp.MiOkHttpCallBack;
import com.lixiaomi.baselib.net.okhttp.MiSendRequestOkHttp;
import com.lixiaomi.mvvmbaselib.base.MyPresenterCallBack;
import com.lixiaomi.mvvmbaselibapplication.R;
import com.lixiaomi.mvvmbaselibapplication.http.HttpData;
import com.lixiaomi.mvvmbaselibapplication.http.MyUrl;
import com.lixiaomi.mvvmbaselibapplication.loginModule.bean.SendLoginBean;


import java.util.WeakHashMap;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/24<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class LoginModelImpl implements LoginModel {

    @Override
    public void login(SendLoginBean sendLoginBean, final MyPresenterCallBack myPresenterCallBack) {
        WeakHashMap<String, String> map = new WeakHashMap<>();
        map.put("username", sendLoginBean.getUsername());
        map.put("password", sendLoginBean.getPassword());
        MiSendRequestOkHttp.sendPost(null, map, MyUrl.getLoginPost(), 0, new MiOkHttpCallBack() {
            @Override
            public void onSuccess(int code, String response) {
                if (code != HttpData.HTTP_SUCCESS_CODE) {
                    myPresenterCallBack.error(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_ServiceError));
                    return;
                }
                if (response.contains(HttpData.SERVER_ERROR_STR)) {
                    myPresenterCallBack.error(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_ServiceError));
                    return;
                }
                myPresenterCallBack.success(code, response);
            }

            @Override
            public void onFailure(Throwable e) {
                myPresenterCallBack.failure(e);
            }
        });
    }

    @Override
    public void loginout(final MyPresenterCallBack myPresenterCallBack) {
        MiSendRequestOkHttp.sendGet(null, null, MyUrl.getLoginOutGet(), 0, new MiOkHttpCallBack() {
            @Override
            public void onSuccess(int code, String response) {
                if (code != HttpData.HTTP_SUCCESS_CODE) {
                    myPresenterCallBack.error(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_ServiceError));
                    return;
                }
                if (response.contains(HttpData.SERVER_ERROR_STR)) {
                    myPresenterCallBack.error(AppConfigInIt.getApplicationContext().getResources().getString(R.string.http_ServiceError));
                    return;
                }
                myPresenterCallBack.success(code, response);
            }

            @Override
            public void onFailure(Throwable e) {
                myPresenterCallBack.failure(e);
            }
        });
    }
}
