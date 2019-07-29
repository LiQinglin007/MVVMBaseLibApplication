package com.lixiaomi.mvvmbaselib.baseBean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.lixiaomi.mvvmbaselib.R;


/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/26<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class TitleBean extends BaseObservable {
    //todo  这里的图标有问题，显示不出来，看一下
    /**
     * 标题
     */
    private String title = "提示";
    /**
     * 左边图片
     */
    private int leftImgId = R.drawable.icon_back;
    /**
     * 右边文字
     */
    private String rightTv = "保存";
    /**
     * 右边图片
     */
    private int rightImgId = R.drawable.icon_save;
    /**
     * 背景色
     */
    private int topBackGroundColor = R.color.default_color;


    /**
     * 左边是否显示
     */
    private boolean leftLyVis = true;
    /**
     * 右边是否显示
     */
    private boolean rightLyVis = true;
    /**
     * 右边文字是否显示
     */
    private boolean rightTvVis = false;
    /**
     * 右边图片是否显示
     */
    private boolean rightImgVis = false;

    public int getTopBackGroundColor() {
        return topBackGroundColor;
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    @Bindable
    public int getLeftImgId() {
        return leftImgId;
    }

    @Bindable
    public String getRightTv() {
        return rightTv;
    }

    @Bindable
    public int getRightImgId() {
        return rightImgId;
    }

    @Bindable
    public boolean isLeftLyVis() {
        return leftLyVis;
    }

    @Bindable
    public boolean isRightLyVis() {
        return rightLyVis;
    }

    @Bindable
    public boolean isRightTvVis() {
        return rightTvVis;
    }

    @Bindable
    public boolean isRightImgVis() {
        return rightImgVis;
    }


    public TitleBean builder() {
        return this;
    }

    public TitleBean setTitle(@NonNull String title) {
        this.title = title;
        return this;
    }

    public TitleBean setLeftImg(@NonNull int leftImgId) {
        this.leftImgId = leftImgId;
        return this;
    }

    public TitleBean setRightTv(@NonNull String rightStr) {
        this.rightTv = rightStr;
        return this;
    }


    public TitleBean setRightImg(@NonNull int rightImgId) {
        this.rightImgId = rightImgId;
        return this;
    }


    public TitleBean setLeftLyVis(boolean leftLyVis) {
        this.leftLyVis = leftLyVis;
        return this;
    }

    public TitleBean setRightLyVis(boolean rightLyVis) {
        this.rightLyVis = rightLyVis;
        return this;
    }

    public TitleBean setRightTvVis(boolean rightTvVis) {
        this.rightTvVis = rightTvVis;
        return this;
    }

    public TitleBean setRightImgVis(boolean rightImgVis) {
        this.rightImgVis = rightImgVis;
        return this;
    }

    public TitleBean setBackGroundColor(@NonNull int color) {
        this.topBackGroundColor = color;
        return this;
    }

    public TitleBean build() {
        if (TextUtils.isEmpty(title)) {
            title = "提示";
        }

        if (leftImgId == 0) {
            leftImgId = R.drawable.icon_back;
        }

        if (TextUtils.isEmpty(rightTv)) {
            rightTv = "保存";
        }

        if (rightImgId == 0) {
            rightImgId = R.drawable.icon_save;
        }

        //如果右边让显示
        if (rightLyVis) {
            //如果tv和img同时显示，就让img隐藏
            if (rightTvVis && rightImgVis) {
                rightImgVis = false;
            }
        }
        notifyChange();
        return this;
    }
}
