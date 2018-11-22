package com.example.administrator.testone.activity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.testone.R;
import com.example.administrator.testone.base.BaseActivity;

public class TestActivity2 extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);



        TextView tv=new TextView(this);
        tv.setText("用户协议");
        LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);







    }


}
