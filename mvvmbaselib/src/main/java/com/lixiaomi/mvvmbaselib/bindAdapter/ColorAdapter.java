package com.lixiaomi.mvvmbaselib.bindAdapter;

import android.databinding.BindingAdapter;
import android.view.View;


/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/29<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class ColorAdapter {
    @BindingAdapter("android:background")
    public static void setBackGroundColor(View view, int colorId) {
        view.setBackgroundColor(colorId);
    }
}
