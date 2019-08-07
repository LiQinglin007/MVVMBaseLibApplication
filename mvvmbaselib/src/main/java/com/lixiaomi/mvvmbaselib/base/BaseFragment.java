package com.lixiaomi.mvvmbaselib.base;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lixiaomi.baselib.ui.Loading.LoaderStyle;
import com.lixiaomi.baselib.ui.Loading.XiaomiLoader;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/8/5<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public abstract class BaseFragment<L extends BaseLifeCycle, B extends ViewDataBinding, VM extends BaseViewModel> extends Fragment {
    protected final String TAG = this.getClass().getSimpleName();
    protected B mDataBinding;
    protected L mLifeCycle;
    protected VM mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //这里可以用一个view来放一个布局   也可以用一个布局id来当一个布局
        if (setLayout() != 0) {
            View rootView = null;
            try {
                mDataBinding = DataBindingUtil.inflate(inflater, setLayout(), container, false);
                rootView = mDataBinding.getRoot();
                if (createLifeCycle() != null) {
                    mLifeCycle = createLifeCycle();
                    getLifecycle().addObserver(mLifeCycle);
                }
                if (creatViewModel() != null) {
                    mViewModel = creatViewModel();
                    mViewModel.setLifeCycle(mLifeCycle);
                }
            } catch (RuntimeException e) {
                rootView = inflater.inflate(setLayout(), container, false);
            }
            creatView(savedInstanceState, rootView);
            return rootView;
        } else {
            throw new RuntimeException("setLayout type must be view or int !");
        }
    }

    private void creatView(Bundle savedInstanceState, View rootView) {
        initData();
        initView(rootView, savedInstanceState);
        startListenerData();
    }


    /**
     * 设置布局/View
     *
     * @return
     */
    protected abstract int setLayout();

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化布局
     *
     * @param rootView
     * @param savedInstanceState
     */
    protected abstract void initView(View rootView, Bundle savedInstanceState);

    /**
     * 监听数据
     */
    protected abstract void startListenerData();

    /**
     * 创建LifeCycle
     * @return
     */
    protected abstract L createLifeCycle();

    /**
     * 创建ViewModel
     * @return
     */
    protected abstract VM creatViewModel();

    /**
     * 切换动画
     *
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        (getActivity()).overridePendingTransition(com.lixiaomi.baselib.R.anim.in_from_right, com.lixiaomi.baselib.R.anim.out_to_left);
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        (getActivity()).overridePendingTransition(com.lixiaomi.baselib.R.anim.toleft, com.lixiaomi.baselib.R.anim.infright);
    }


    /**
     * 设置loading的显示状态
     *
     * @param flag
     */
    protected void setLoading(boolean flag) {
        if (flag) {
            showLoading();
        } else {
            hineLoading();
        }
    }

    /**
     * 正在发起请求的数量，用来控制loading
     */
    private int mLoadingNumber = 0;

    /**
     * 显示loading
     */
    protected void showLoading() {
        try {
            mLoadingNumber++;
            XiaomiLoader.showLoading(getActivity(), getResources().getColor(com.lixiaomi.baselib.R.color.default_color));
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
            mLoadingNumber++;
            XiaomiLoader.showLoading(getActivity(), getResources().getColor(color), loaderStyle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 隐藏loading
     */
    protected void hineLoading() {
        mLoadingNumber--;
        if (mLoadingNumber == 0) {
            XiaomiLoader.stopLoading();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDataBinding = null;
        mLifeCycle = null;
        mViewModel = null;
        XiaomiLoader.stopLoading();
    }


}
