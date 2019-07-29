package com.lixiaomi.mvvmbaselib.bindAdapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.lixiaomi.baselib.utils.loadImageUtils.MiLoadImageUtil;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/23<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class LoadImageAdapter {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, String url) {
        MiLoadImageUtil.loadImage(imageView.getContext(), url, imageView);
    }


}
