package com.example.administrator.testone.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.testone.R;
import com.example.administrator.testone.activity.FragmentActivity;
import com.example.administrator.testone.base.BaseFragment;

/**
 * 创建时间:2018/11/21
 * 作者:LiBW
 * 描述:
 */
public class FragmentOne extends BaseFragment {
    private FragmentActivity activity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_one,null);
        activity= (FragmentActivity) getActivity();
//        activity.changeFragment();

        return view;
    }
}
