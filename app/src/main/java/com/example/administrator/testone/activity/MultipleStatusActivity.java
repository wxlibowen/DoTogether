package com.example.administrator.testone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.testone.R;

public class MultipleStatusActivity extends AppCompatActivity {
    private com.classic.common.MultipleStatusView mView_multiple;
    private Button mBtn_error;
    private Button mBtn_loading;
    private Button mBtn_no_wifi;
    private Button mBtn_content;
    private Button mBtn_empty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_status);
        initView();
    }

    private void initView() {
        mView_multiple = findViewById(R.id.view_multiple);
        mBtn_error = findViewById(R.id.btn_error);
        mBtn_loading =  findViewById(R.id.btn_loading);
        mBtn_no_wifi = findViewById(R.id.btn_no_wifi);
        mBtn_content =  findViewById(R.id.btn_content);
        mBtn_empty =  findViewById(R.id.btn_empty);
        mBtn_error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView_multiple.showError();
            }
        });
        mBtn_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView_multiple.showLoading();
            }
        });
        mBtn_no_wifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView_multiple.showNoNetwork();
            }
        });
        mBtn_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView_multiple.showContent();
            }
        });
        mBtn_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView_multiple.showEmpty();
            }
        });



    }
}
