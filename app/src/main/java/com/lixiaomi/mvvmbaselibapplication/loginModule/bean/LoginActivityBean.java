package com.lixiaomi.mvvmbaselibapplication.loginModule.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/24<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class LoginActivityBean extends BaseObservable {

    private String loginState;

    public LoginActivityBean(String loginState) {
        this.loginState = loginState;
    }

    @Bindable
    public String getLoginState() {
        return loginState;
    }

    public void setLoginState(String loginState) {
        this.loginState = loginState;
        notifyChange();
    }
}
