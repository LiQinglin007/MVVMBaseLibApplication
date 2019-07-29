package com.lixiaomi.mvvmbaselibapplication.loginModule;


import com.lixiaomi.mvvmbaselib.base.BaseModel;
import com.lixiaomi.mvvmbaselib.base.MyPresenterCallBack;
import com.lixiaomi.mvvmbaselibapplication.loginModule.bean.SendLoginBean;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/24<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public interface LoginModel extends BaseModel {
    void login(SendLoginBean sendLoginBean, MyPresenterCallBack myPresenterCallBack);

    void loginout(MyPresenterCallBack myPresenterCallBack);
}
