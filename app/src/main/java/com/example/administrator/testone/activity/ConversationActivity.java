package com.example.administrator.testone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.testone.R;

public class ConversationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        String sName = getIntent().getData().getQueryParameter("title");//获取昵称
        setTitle("与" + sName + "聊天中");
    }
}
