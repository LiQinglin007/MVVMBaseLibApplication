package com.lixiaomi.mvvmbaselibapplication.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.lixiaomi.mvvmbaselib.BR;
import com.lixiaomi.mvvmbaselibapplication.R;
import com.lixiaomi.mvvmbaselibapplication.bean.UserBean;

import java.util.List;


/**
 * @describe：<br>
 * @author：Xiaomi<br>
 * @createTime：2019/7/23<br>
 * @remarks：<br>
 * @changeTime:<br>
 */
public class MainActivity2Adapter extends RecyclerView.Adapter<BindingViewHolder> {
    private final int USER_TYPE_0 = 0;
    private final int USER_TYPE_1 = 1;

    private List<UserBean> mListData;

    public MainActivity2Adapter(List<UserBean> mListData) {
        this.mListData = mListData;
    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ViewDataBinding binding;
        if (viewType == USER_TYPE_0) {
            binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_main2, viewGroup, false);
        } else {
            binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item_main2_type2, viewGroup, false);
        }
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder viewHolder, int position) {
        UserBean userBean = mListData.get(position);
        viewHolder.getInflate().setVariable(BR.userBean, userBean);
        viewHolder.getInflate().executePendingBindings();
    }

    @Override
    public int getItemViewType(int position) {
        UserBean userBean = mListData.get(position);
        if (userBean.getUserType() == 0) {
            return USER_TYPE_0;
        } else {
            return USER_TYPE_1;
        }
    }

    @Override
    public int getItemCount() {
        if (mListData == null) {
            return 0;
        } else {
            return mListData.size();
        }
    }

}
