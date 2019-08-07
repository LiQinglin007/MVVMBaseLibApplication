package com.lixiaomi.mvvmbaselibapplication.loginModule;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.TextView;

import com.lixiaomi.baselib.utils.T;
import com.lixiaomi.mvvmbaselib.base.BaseActivity;
import com.lixiaomi.mvvmbaselib.baseBean.TitleBean;
import com.lixiaomi.mvvmbaselibapplication.R;
import com.lixiaomi.mvvmbaselibapplication.databinding.ActivityLoginBinding;


/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/24<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class LoginActivity extends BaseActivity<LoginActivityLife, ActivityLoginBinding, LoginViewModel> implements View.OnClickListener {

    private LinearLayoutCompat mTopLy;
    private LinearLayoutCompat mTopLeftLy;
    private LinearLayoutCompat mTopRightLy;
    private AppCompatTextView mTopTitleTv;
    private AppCompatImageView mTopLeftImg;
    private AppCompatTextView mTopRightTv;
    private AppCompatImageView mTopRightImg;
    private TextView mResponseTv;


    @Override
    protected void initData() {

    }

    @Override
    protected int setLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView(@Nullable Bundle savedInstanceState) {

        mResponseTv = findViewById(R.id.http_response_tv);
        mDataBinding.setLoginBean(mViewModel.getmSendLoginBeanLiveData().getValue());
        mDataBinding.setPersenter(mViewModel);

        mTopLy = findViewById(R.id.top_ly);
        mTopLeftLy = findViewById(R.id.top_back_ly);
        mTopRightLy = findViewById(R.id.top_right_ly);
        mTopTitleTv = findViewById(R.id.top_title_tv);
        mTopLeftImg = findViewById(R.id.top_back_iv);
        mTopRightTv = findViewById(R.id.top_save_tv);
        mTopRightImg = findViewById(R.id.top_add_img);

        mTopLeftLy.setOnClickListener(this);
        mTopRightLy.setOnClickListener(this);
        setTopData(mViewModel.getmTitleBeanLiveData().getValue());
    }


    @Override
    protected LoginActivityLife createLifeCycle() {
        return new LoginActivityLife(this);
    }

    @Override
    protected LoginViewModel creatViewModel() {
        return ViewModelProviders.of(LoginActivity.this).get(LoginViewModel.class);
    }

    @Override
    protected void startListenerData() {
        mViewModel.getmShowLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                setLoading(aBoolean);
            }
        });


        mViewModel.getmToastMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                T.shortToast(LoginActivity.this, s);
            }
        });


        mViewModel.getmTitleBeanLiveData().observe(this, new Observer<TitleBean>() {
            @Override
            public void onChanged(@Nullable TitleBean titleBean) {
                setTopData(titleBean);
            }
        });

        mViewModel.getmResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String response) {
                mResponseTv.setText(response);
            }
        });
    }

    /**
     * 设置顶部菜单数据
     *
     * @param titleBean
     */
    public void setTopData(TitleBean titleBean) {
        if (titleBean == null) {
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
