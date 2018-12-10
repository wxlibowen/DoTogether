package com.example.administrator.testone.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;

import io.rong.imkit.RongIM;

public class MyApplication extends MultiDexApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //热更新请求
        SophixManager.getInstance().queryAndLoadNewPatch();
        RongIM.init(this);//初始化融云
        context = this;

    }

    public static Context getContext() {
        return context;
    }


}
