package com.lixiaomi.mvvmbaselibapplication;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.Observable;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableArrayMap;
import android.databinding.ObservableList;
import android.databinding.ObservableMap;
import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.ViewGroup;

import com.lixiaomi.mvvmbaselibapplication.bean.UserBean;
import com.lixiaomi.mvvmbaselibapplication.databinding.ActivityMainBinding;
import com.lixiaomi.mvvmbaselibapplication.loginModule.LoginActivity;
import com.lixiaomi.mvvmbaselibapplication.paramenterBean.ParameterUserBean;


public class MainActivity extends AppCompatActivity {
    UserBean userMi;
    ParameterUserBean posUserMi;
    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //绑定layout
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //初始化数据
        userMi = new UserBean("小米", "男");
        ObservableMap<String, String> map = new ObservableArrayMap<>();
        map.put("userNikeName", "啦啦啦");
        String url = "https://fms.ipinyou.com/6/0B/31/B5/F001461Sm_wp001r_wtn.jpg";
        posUserMi = new ParameterUserBean("小米1", "男1", url, map);
        userMi.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                Log.e("###", "属性值发生改变");
            }
        });
        //给view设置数据
        activityMainBinding.setUser(userMi);
        activityMainBinding.setPosUser(posUserMi);

        ObservableMap<String, String> testMap = new ObservableArrayMap<>();
        testMap.put("testMap11", "testMapValue");
        activityMainBinding.setMap1(testMap);


        ObservableList<String> testList = new ObservableArrayList<>();
        testList.add("testListValue");
        activityMainBinding.setList(testList);

        activityMainBinding.setPersenter(new MainActivityPersenter());

        activityMainBinding.addOnRebindCallback(new OnRebindCallback() {
            @Override
            public boolean onPreBind(ViewDataBinding binding) {
                ViewGroup view = (ViewGroup) binding.getRoot();
                TransitionManager.beginDelayedTransition(view);
                return true;
            }
        });
    }


    public class MainActivityPersenter {
        //定义点击事件，这里注意，方法要用public修饰

        public void login() {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }

        public void intent() {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
        }

        public void updateUserName(UserBean paramenter) {
            //修改数据值，这里需要在bean对象里边设置东西，去bean.class里边看
            userMi.setUserName("小米粒" + paramenter.getUserName());
            posUserMi.userName.set("小米粒1" + paramenter.getUserSex());
        }

        public void reductionData() {
            activityMainBinding.userNameTv.setText("小米");
            activityMainBinding.posUserNameTv.setText("小米");
        }
    }


}
