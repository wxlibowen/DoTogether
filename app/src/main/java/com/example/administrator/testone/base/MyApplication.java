package com.example.administrator.testone.base;

import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import io.rong.imkit.RongIM;

public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        //热更新请求
        SophixManager.getInstance().queryAndLoadNewPatch();
        Log.d("日志", "初始化融云");
        RongIM.init(this);//初始化融云

    }
}
