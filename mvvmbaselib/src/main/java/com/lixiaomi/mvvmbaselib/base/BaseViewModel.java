package com.lixiaomi.mvvmbaselib.base;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/26<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public abstract class BaseViewModel<M extends BaseModel, L extends BaseLifeCycle> extends ViewModel {
    protected MutableLiveData<Boolean> mShowLoading = new MutableLiveData<>();
    protected MutableLiveData<String> mToastMessage = new MutableLiveData<>();
    protected L mLifeCycle;

    public BaseViewModel() {
        mShowLoading.setValue(false);
    }

    public void setLifeCycle(L lifeCycle) {
        mLifeCycle = lifeCycle;
    }

    public L getLifeCycle() {
        return mLifeCycle;
    }

    public MutableLiveData<Boolean> getmShowLoading() {
        return mShowLoading;
    }

    public MutableLiveData<String> getmToastMessage() {
        return mToastMessage;
    }

    /**
     * Model的弱引用
     */
    protected Reference<ArrayList<M>> mModelReferList;

    /**
     * 创建model
     *
     * @return
     */
    protected abstract ArrayList<M> createModelList();

    protected ArrayList<M> getModelList() {
        ArrayList<M> modelList = createModelList();
        if (modelList != null) {
            mModelReferList = new WeakReference<ArrayList<M>>(modelList);
            //在Presenter中获取Model接口的引用
            return mModelReferList.get();
        } else {
            return null;
        }
    }


    /**
     * 判断有没有引用model
     *
     * @return
     */
    public boolean isModelAttached() {
        return mModelReferList != null && mModelReferList.get() != null;
    }

}
