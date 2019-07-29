package com.lixiaomi.mvvmbaselibapplication;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.lixiaomi.mvvmbaselibapplication.adapter.MainActivity2Adapter;
import com.lixiaomi.mvvmbaselibapplication.bean.UserBean;
import com.lixiaomi.mvvmbaselibapplication.databinding.ActivityMain2Binding;

import java.util.Random;

/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/23<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class MainActivity2 extends AppCompatActivity {


    ObservableList<UserBean> userBeanList = new ObservableArrayList<>();
    ActivityMain2Binding activityMain2Binding;
    MainActivity2Adapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMain2Binding = DataBindingUtil.setContentView(MainActivity2.this, R.layout.activity_main2);
        for (int i = 0; i < 50; i++) {
            userBeanList.add(new UserBean("小米" + i, "男", i % 5 == 0 ? 0 : 1));
        }
        initView();
        activityMain2Binding.setPersenter(new ChangeDataPersenter());
    }

    private void initView() {
        RecyclerView recyclerview = findViewById(R.id.test_recycler);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new MainActivity2Adapter(userBeanList);
        recyclerview.setAdapter(mAdapter);

    }

    public class ChangeDataPersenter {
        public void addData() {
            for (int i = 0; i < 10; i++) {
                userBeanList.add(new UserBean("小米" + String.valueOf(new Random().nextInt() * 4), "男", i % 3 == 0 ? 0 : 1));
            }
            mAdapter.notifyDataSetChanged();
        }

        public void removeData() {
            userBeanList.remove(0);
            userBeanList.remove(10);
            userBeanList.remove(20);
            userBeanList.remove(30);
            userBeanList.remove(40);
            mAdapter.notifyDataSetChanged();
        }

        public void updateData() {
            for (int i = 0; i < userBeanList.size(); i++) {
                userBeanList.get(i).setUserName("修改：" + userBeanList.get(i).getUserName());
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
