package com.example.administrator.testone.base;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.testone.util.ActivityCollector;

public class BaseActivity extends AppCompatActivity {
    protected String TAG="日志";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        changStatusIconCollor(true);




    }

    /**
     * 设置状态栏图片为亮还是暗
     * @param setDark
     */
    private void changStatusIconCollor(boolean setDark) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = getWindow().getDecorView();
            if (decorView != null) {
                int vis = decorView.getSystemUiVisibility();
                if (setDark) {
                    vis |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                } else {
                    vis &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
                }
                decorView.setSystemUiVisibility(vis);
            }
        }
    }

    /**
     * 封装跳转
     * @param clazz 跳转的activity
     * @param close 是否关闭当前actviity
     */
    public void GoActivity(Class clazz ,boolean close){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
        if (close){
            finish();
        }
    }

    /**
     * 封装跳转携带参数
     * @param clazz 跳转的activity
     * @param bundle    携带的参数
     * @param close 否关闭当前actviity
     */
    public void GoActivity(Class clazz ,Bundle bundle,boolean close){
        Intent intent=new Intent(this,clazz);
        if (bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        if (close){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);


    }
}
