package com.example.administrator.testone.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;

import com.example.administrator.testone.R;

/**
 * 创建时间:2018/11/26
 * 作者:LiBW
 * 描述:
 */

public class MyDialogActivity extends Dialog implements View.OnClickListener {
    private Context context;
    private View.OnClickListener successListener;
    private View.OnClickListener cancelListener;
    private Button btn_yes,btn_no;

    public MyDialogActivity(@NonNull Context context, int themeResId, View.OnClickListener successListener,View.OnClickListener cancelListener) {
        super(context, themeResId);
        this.context=context;
        this.successListener=successListener;
        this.cancelListener=cancelListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_mydialog_layout);
        btn_yes=findViewById(R.id.btn_yes);
        btn_no=findViewById(R.id.btn_no);
        btn_yes.setOnClickListener(this);
        btn_no.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_yes:
                successListener.onClick(v);
                break;
            case R.id.btn_no:
                cancelListener.onClick(v);
                break;

        }
    }
}
