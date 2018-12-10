package com.example.administrator.testone.activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testone.R;
import com.example.administrator.testone.base.BaseActivity;
import com.example.administrator.testone.util.Util;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import static com.example.administrator.testone.BuildConfig.Name;
import static com.example.administrator.testone.BuildConfig.WEB_URL;

public class TestActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setData();



    }

    private void setData() {
        Util.putString("name","神户西瓜");
        TextView tv_1 = findViewById(R.id.tv_1);
        tv_1.setText("BuildConfig中的渠道字段:" + WEB_URL);
        TextView tv_2 = findViewById(R.id.tv_2);
        tv_2.setText("BuildConfig中的环境字段:" + Name);
        TextView tv_3 = findViewById(R.id.tv_3);
        TextView tv_4 = findViewById(R.id.tv_4);
        EditText et_name=findViewById(R.id.et_name);
        EditText et_password=findViewById(R.id.et_password);
        et_name.setText(Util.getString("name"));


        try {
            tv_3.setText("当前版本号:" + Util.getVersionCode());
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                connect("yhWBI1wocwluJTBLASCzqf+x9yrgeZKDs1BIPrZDfU/gFWGa6Hw/sNUKI0nuaS45F3i9mjhXfj0=");
            }
        }).start();

        tv_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, TestActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putBoolean("aaa", true);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();


            }
        });
    }


    private void connect(String token) {
        Log.e("日志", "进入connect方法");
        RongIM.connect(token, new RongIMClient.ConnectCallback() {

            @Override
            public void onTokenIncorrect() {
                Log.e("日志", "--onTokenIncorrect");
            }

            @Override
            public void onSuccess(String userid) {
                Log.e("日志", "--onSuccess--" + userid);
                Toast.makeText(TestActivity.this, "登录成功,用户：" + userid, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(TestActivity.this, ConversationListActivity.class));
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.d("日志", "错误码 " + errorCode.toString());
                Log.e("日志", "--onError");
            }
        });
        Log.e("日志", "connect方法结束");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View mDecorView = getWindow().getDecorView();
        mDecorView.setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar
                | View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );
    }
}
