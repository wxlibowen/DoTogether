package com.example.administrator.testone.activity;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.administrator.testone.R;
import com.example.administrator.testone.base.BaseActivity;

public class XYzhouActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xyzhou);
        //去除状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
