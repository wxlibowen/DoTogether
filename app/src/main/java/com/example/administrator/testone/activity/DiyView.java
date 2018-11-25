package com.example.administrator.testone.activity;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.testone.R;
import com.example.administrator.testone.base.BaseActivity;
import com.example.administrator.testone.view.Clock;

public class DiyView extends BaseActivity {
    private Clock clock;
    private Handler handler=new Handler();
    private Runnable runnable=new Runnable() {
        @Override
        public void run() {
            clock.invalidate();
            handler.postDelayed(runnable,10);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diy_view);
        clock=findViewById(R.id.view_clock);
        handler.post(runnable);




    }
}
