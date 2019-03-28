package com.example.administrator.testone.base;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.taobao.sophix.SophixManager;


/**
 * @author WXlib
 */
public class MyApplication extends MultiDexApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        //热更新请求
        SophixManager.getInstance().queryAndLoadNewPatch();
        context = this;

    }

    public static Context getContext() {
        return context;
    }


}
