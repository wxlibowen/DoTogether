package com.example.administrator.testone.activity;

import android.animation.AnimatorSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.administrator.testone.R;
import com.example.administrator.testone.base.BaseActivity;
import com.example.administrator.testone.view.DianZanView;

/**
 * @author WXlib
 */
public class DianZanActivity extends BaseActivity {
    private ImageView iv;
    private DianZanView mShine;
    private boolean flag=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dianzan);
        iv = findViewById(R.id.zan_iv);
        mShine = findViewById(R.id.mShine);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (flag){
                   iv.setImageResource(R.drawable.zan_one);
                   flag=false;
               }else {
                   iv.setImageResource(R.drawable.zan_two);
                   Animation animation=AnimationUtils.loadAnimation(DianZanActivity.this,R.anim.zan_animation);
                   iv.startAnimation(animation);
                   mShine.setVisibility(View.VISIBLE);
                   mShine.invalidate();
                   mShine.startAnimator();
                   flag=true;
               }

            }
        });


    }
}
