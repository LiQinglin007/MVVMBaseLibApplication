package com.lixiaomi.mvvmbaselibapplication.paramenterBean;

import android.databinding.ObservableField;
import android.databinding.ObservableMap;


/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/23<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class ParameterUserBean {
    //    实现数据绑定的方式二：通过ObservableField等来实现数据同步，不需要get、set方法
//    官方封装了基本数据类型ObservableBoolean、ObservableByte、ObservableChar、ObservableShort、ObservableInt、ObservableLong、ObservableFloat、ObservableDouble 以及 ObservableParcelable
//    也可以使用ObservableField来声明其他类型
//     来替代List的ObservableList和替代Map的ObservableMap


    public ObservableField<String> userName;
    public ObservableField<String> userSex;
    public ObservableField<String> userImage;
    public ObservableMap<String, String> userNike;

    public ParameterUserBean(String userName, String userSex, String userImage, ObservableMap<String, String> userNike) {
        this.userName = new ObservableField<>(userName);
        this.userSex = new ObservableField<>(userSex);
        this.userImage = new ObservableField<>(userImage);
        this.userNike = userNike;
    }


}
