package com.example.administrator.testone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.example.administrator.testone.R;

public class MyLoginActivity extends AppCompatActivity {
    private AutoCompleteTextView tv_user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_login);
        tv_user_name=findViewById(R.id.tv_user_name);



    }
}
