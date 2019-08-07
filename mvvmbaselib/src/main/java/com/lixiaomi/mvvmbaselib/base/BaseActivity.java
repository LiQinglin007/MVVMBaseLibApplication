package com.lixiaomi.mvvmbaselib.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.lixiaomi.baselib.ui.Loading.LoaderStyle;
import com.lixiaomi.baselib.ui.Loading.XiaomiLoader;
import com.lixiaomi.baselib.utils.BaseAppManager;
import com.lixiaomi.mvvmbaselib.R;


public abstract class BaseActivity<L extends BaseLifeCycle, B extends ViewDataBinding, VM extends BaseViewModel>
        extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    protected B mDataBinding;
    protected L mLifeCycle;
    protected VM mViewModel;
    /**
     * 设置状态栏
     */
    private ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
        BaseAppManager.getInstance().addActivity(this);
        if (setLayout() != 0) {
            try {
                mDataBinding = DataBindingUtil.setContentView(this, setLayout());
                if (createLifeCycle() != null) {
                    mLifeCycle = createLifeCycle();
                    getLifecycle().addObserver(mLifeCycle);
                }
                if (creatViewModel() != null) {
                    mViewModel = creatViewModel();
                    mViewModel.setLifeCycle(mLifeCycle);
                }
            } catch (RuntimeException e) {
                //如果layout 没有设置databinding布局，就不获取mDataBinding了
                setContentView(setLayout());
            }
            initView(savedInstanceState);
            //设置透明(隐藏)状态栏
            if (setStatusBarColor() == 0) {
                //透明状态栏，不写默认透明色
                mImmersionBar = ImmersionBar.with(this).transparentStatusBar();
            } else {
                //fitsSystemWindows属性,必须指定状态栏颜色
                mImmersionBar = ImmersionBar.with(this)
                        .fitsSystemWindows(true)
                        .statusBarColor(setStatusBarColor());
            }
            //所有子类都将继承这些相同的属性
            mImmersionBar.init();
            startListenerData();
        }
    }


    /**
     * 页面初始化数据
     */
    protected abstract void initData();

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int setLayout();

    protected abstract void initView(@Nullable Bundle savedInstanceState);

    /**
     * 监听数据
     */
    protected abstract void startListenerData();

    /**
     * 创建LifeCycle
     *
     * @return
     */
    protected abstract L createLifeCycle();

    /**
     * 创建viewModel
     *
     * @return
     */
    protected abstract VM creatViewModel();

    /**
     * 设置顶部状态栏颜色
     *
     * @return 0 隐藏状态栏  其他要传R.color资源进来
     */
    protected abstract int setStatusBarColor();

    protected void setLoading(boolean flag) {
        if (flag) {
            showLoading();
        } else {
            hideLoading();
        }
    }

    protected void showLoading() {
        try {
            XiaomiLoader.showLoading(this, getResources().getColor(com.lixiaomi.baselib.R.color.default_color));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示loading
     *
     * @param color       颜色
     * @param loaderStyle 样式
     */
    protected void showLoading(int color, Enum<LoaderStyle> loaderStyle) {
        try {
            XiaomiLoader.showLoading(this, getResources().getColor(color), loaderStyle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideLoading() {
        XiaomiLoader.stopLoading();
    }

    /**
     * 返回的切换动画
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.toleft, R.anim.infright);
    }

    /**
     * 处理切换动画
     *
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.toleft, R.anim.infright);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mImmersionBar != null) {
            //必须调用该方法，防止内存泄漏
            mImmersionBar.destroy();
        }
        XiaomiLoader.stopLoading();
        BaseAppManager.getInstance().removeActivity(this);
    }
}
