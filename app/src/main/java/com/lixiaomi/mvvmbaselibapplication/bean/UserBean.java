package com.lixiaomi.mvvmbaselibapplication.bean;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.lixiaomi.mvvmbaselib.BR;


/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/23<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class UserBean extends BaseObservable {
    //实现数据绑定的方式一：
    // 1、bean对象继承BaseObservable；
    // 2、在get方法上添加@Bindable注解；
    // 3、在set方法中使用notifyPropertyChanged/notifyChange方法来更新属性的值
    //实现方式二：见parameterBean

    private String userName;
    private String userSex;
    private int userType;

    public UserBean(String userName, String userSex) {
        this.userName = userName;
        this.userSex = userSex;
    }

    public UserBean(String userName, String userSex, int userType) {
        this.userName = userName;
        this.userSex = userSex;
        this.userType = userType;
    }

    @Bindable
    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
        notifyChange();
    }

    @Bindable
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        //只更新本字段
        notifyPropertyChanged(BR.userName);
//        notifyChange();
    }

    @Bindable
    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
        //更新全部字段
        notifyChange();
    }
}
