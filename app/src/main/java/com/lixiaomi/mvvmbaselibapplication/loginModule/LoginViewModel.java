package com.lixiaomi.mvvmbaselibapplication.loginModule;


import android.arch.lifecycle.MutableLiveData;

import com.lixiaomi.baselib.config.AppConfigInIt;
import com.lixiaomi.baselib.utils.NetWorkUtils;
import com.lixiaomi.mvvmbaselib.base.BaseModel;
import com.lixiaomi.mvvmbaselib.base.BaseViewModel;
import com.lixiaomi.mvvmbaselib.base.MyPresenterCallBack;
import com.lixiaomi.mvvmbaselib.baseBean.TitleBean;
import com.lixiaomi.mvvmbaselibapplication.R;
import com.lixiaomi.mvvmbaselibapplication.loginModule.bean.SendLoginBean;

import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/24<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class LoginViewModel extends BaseViewModel<BaseModel, LoginActivityLife> {

    MutableLiveData<SendLoginBean> mSendLoginBeanLiveData;
    MutableLiveData<TitleBean> mTitleBeanLiveData;
    MutableLiveData<String> mResponse = new MutableLiveData<>();
    int number = 0;

    public MutableLiveData<SendLoginBean> getmSendLoginBeanLiveData() {
        return mSendLoginBeanLiveData;
    }

    public MutableLiveData<TitleBean> getmTitleBeanLiveData() {
        return mTitleBeanLiveData;
    }

    public MutableLiveData<String> getmResponse() {
        return mResponse;
    }

    public LoginViewModel() {
        SendLoginBean sendLoginBean = new SendLoginBean("lixiaomi", "123456");
        mSendLoginBeanLiveData = new MutableLiveData<SendLoginBean>();
        mSendLoginBeanLiveData.setValue(sendLoginBean);
        mSendLoginBeanLiveData.setValue(new SendLoginBean("lixiaomi", "123456"));

        TitleBean builder = new TitleBean().builder()
                .setBackGroundColor(R.color.color_51D8BA_0x)
                .setRightTv("保存1")
                .setLeftLyVis(true)
                .setRightLyVis(true)
                .setRightImgVis(true)
                .setRightTvVis(false)
                .setTitle("提示")
                .builder();
        mTitleBeanLiveData = new MutableLiveData<>();
        mTitleBeanLiveData.setValue(builder);
    }

    public void login() {
        number++;
        mShowLoading.setValue(true);
        ((LoginModelImpl) getModelList().get(0)).login(mSendLoginBeanLiveData.getValue(), new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {
                mShowLoading.setValue(false);
                mResponse.setValue(number + "登陆成功,code:" + code + "  response: " + response);
            }

            @Override
            public void error(String message) {
                mShowLoading.setValue(false);
                mResponse.setValue("");
                mToastMessage.setValue(message);
            }

            @Override
            public void failure(Throwable e) {
                mShowLoading.setValue(false);
                mResponse.setValue("");
                mToastMessage.setValue("登陆失败：" + AppConfigInIt.getApplicationContext().getResources().getString(
                        NetWorkUtils.isNetworkConnected(AppConfigInIt.getApplicationContext()) ? R.string.http_onFailure : R.string.http_NoNetWorkError));
            }
        });
    }


    public void loginout() {
        mShowLoading.setValue(true);
        ((LoginModelImpl) getModelList().get(0)).loginout(new MyPresenterCallBack() {
            @Override
            public void success(int code, String response) {
                mShowLoading.setValue(false);
                mResponse.setValue(number + "退出登陆成功,code:" + code + "  response: " + response);
            }

            @Override
            public void error(String message) {
                mShowLoading.setValue(false);
                mResponse.setValue("");
                mToastMessage.setValue(message);
            }

            @Override
            public void failure(Throwable e) {
                mShowLoading.setValue(false);
                mResponse.setValue("");
                mToastMessage.setValue("退出登陆失败：" + AppConfigInIt.getApplicationContext().getResources().getString(
                        NetWorkUtils.isNetworkConnected(AppConfigInIt.getApplicationContext()) ? R.string.http_onFailure : R.string.http_NoNetWorkError));
            }
        });
    }

    @Override
    protected ArrayList<BaseModel> createModelList() {
        ArrayList<BaseModel> models = new ArrayList<>();
        models.add(new LoginModelImpl());
        return models;
    }
}
