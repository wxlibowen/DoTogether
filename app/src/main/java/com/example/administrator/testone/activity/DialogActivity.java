package com.example.administrator.testone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import com.example.administrator.testone.R;
import com.example.administrator.testone.widget.MyDialogActivity;

import java.util.List;

public class DialogActivity extends AppCompatActivity {
    private Button btn_1;
    private MyDialogActivity mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dalog);
        btn_1=findViewById(R.id.btn_1);
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new MyDialogActivity(DialogActivity.this,0,success,cancel);
                mDialog.setCancelable(false);
                mDialog.show();



            }
        });




    }
    private View.OnClickListener success=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            btn_1.setText("改为确认");
        }
    };

    private View.OnClickListener cancel=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           mDialog.cancel();
        }
    };



}
