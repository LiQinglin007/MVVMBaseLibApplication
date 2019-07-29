package com.lixiaomi.mvvmbaselibapplication.loginModule;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;

import com.lixiaomi.baselib.utils.LogUtils;
import com.lixiaomi.baselib.utils.MiJsonUtil;
import com.lixiaomi.mvvmbaselib.base.BaseActivity;
import com.lixiaomi.mvvmbaselib.baseBean.TitleBean;
import com.lixiaomi.mvvmbaselibapplication.R;
import com.lixiaomi.mvvmbaselibapplication.databinding.ActivityLoginBinding;
import com.lixiaomi.mvvmbaselibapplication.loginModule.bean.LoginActivityBean;


/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/24<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class LoginActivity extends BaseActivity<LoginActivityLife, ActivityLoginBinding> implements View.OnClickListener {
    LoginViewModel loginViewModel;

    private LinearLayoutCompat mTopLy;
    private LinearLayoutCompat mTopLeftLy;
    private LinearLayoutCompat mTopRightLy;
    private AppCompatTextView mTopTitleTv;
    private AppCompatImageView mTopLeftImg;
    private AppCompatTextView mTopRightTv;
    private AppCompatImageView mTopRightImg;

    @Override
    protected void initData() {

    }

    @Override
    protected int layout() {
        return R.layout.activity_login;
    }

    @Override
    protected void init() {
        loginViewModel = ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);
        mDataBinding.setLoginActivityBean(loginViewModel.getmLoginActivityBeanLiveData().getValue());
        mDataBinding.setLoginBean(loginViewModel.getmSendLoginBeanLiveData().getValue());
        mDataBinding.setPersenter(loginViewModel);


        mTopLy = findViewById(R.id.top_ly);
        mTopLeftLy = findViewById(R.id.top_back_ly);
        mTopRightLy = findViewById(R.id.top_right_ly);
        mTopTitleTv = findViewById(R.id.top_title_tv);
        mTopLeftImg = findViewById(R.id.top_back_iv);
        mTopRightTv = findViewById(R.id.top_save_tv);
        mTopRightImg = findViewById(R.id.top_add_img);

        mTopLeftLy.setOnClickListener(this);
        mTopRightLy.setOnClickListener(this);
        setTopData(loginViewModel.getmTitleBeanLiveData().getValue());
    }

    @Override
    protected LoginActivityLife createLifeCycle() {
        return new LoginActivityLife(this);
    }

    @Override
    protected void startListenerData() {
        loginViewModel.getmLoginActivityBeanLiveData().observe(this, new Observer<LoginActivityBean>() {
            @Override
            public void onChanged(@Nullable LoginActivityBean loginActivityBean) {
                LogUtils.loge(MiJsonUtil.getJson(loginActivityBean));
                mDataBinding.setLoginActivityBean(loginActivityBean);
            }
        });


        loginViewModel.getmShowLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });

        loginViewModel.getmTitleBeanLiveData().observe(this, new Observer<TitleBean>() {
            @Override
            public void onChanged(@Nullable TitleBean titleBean) {
                setTopData(titleBean);
            }
        });
    }

    /**
     * 设置顶部菜单数据
     *
     * @param titleBean
     */
    public void setTopData(TitleBean titleBean) {
        if(titleBean==null){
            return;
        }
        mTopTitleTv.setText(titleBean.getTitle());
        mTopLeftImg.setImageResource(titleBean.getLeftImgId());
        mTopRightImg.setImageResource(titleBean.getRightImgId());
        mTopRightTv.setText(titleBean.getRightTv());
        mTopLy.setBackgroundColor(getResources().getColor(titleBean.getTopBackGroundColor()));
        mTopLeftLy.setVisibility(titleBean.isLeftLyVis() ? View.VISIBLE : View.INVISIBLE);
        mTopRightLy.setVisibility(titleBean.isRightLyVis() ? View.VISIBLE : View.INVISIBLE);
        mTopRightTv.setVisibility(titleBean.isRightTvVis() ? View.VISIBLE : View.GONE);
        mTopRightImg.setVisibility(titleBean.isRightImgVis() ? View.VISIBLE : View.GONE);
    }

    @Override
    protected int setStatusBarColor() {
        return R.color.default_color;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_back_ly:
                break;
            case R.id.top_right_ly:
                break;
            default:
                break;
        }
    }
}
