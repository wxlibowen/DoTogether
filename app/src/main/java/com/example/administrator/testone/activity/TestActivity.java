package com.example.administrator.testone.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testone.R;
import com.example.administrator.testone.base.BaseActivity;
import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import static com.example.administrator.testone.BuildConfig.Name;
import static com.example.administrator.testone.BuildConfig.WEB_URL;

public class TestActivity extends FragmentActivity {
    private static final int GET_DATA = 0x01;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GET_DATA:
                    //对取得的数据进行处理
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        setData();


    }

    private void setData() {
        TextView tv_1 = findViewById(R.id.tv_1);
        tv_1.setText("BuildConfig中的渠道字段:" + WEB_URL);
        TextView tv_2 = findViewById(R.id.tv_2);
        tv_2.setText("BuildConfig中的环境字段:" + Name);

//        String strToken=Util.getToken();
//        TextView tv_3=findViewById(R.id.tv_3);
//        tv_3.setText("融云getToken取得的值:"+strToken);
        new Thread(new Runnable() {
            @Override
            public void run() {
                connect("yhWBI1wocwluJTBLASCzqf+x9yrgeZKDs1BIPrZDfU/gFWGa6Hw/sNUKI0nuaS45F3i9mjhXfj0=");
            }
        }).start();
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
                Log.d("日志", "错误码 "+errorCode.toString());
                Log.e("日志", "--onError");
            }
        });
        Log.e("日志", "connect方法结束");
    }
}