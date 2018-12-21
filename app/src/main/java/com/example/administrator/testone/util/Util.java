package com.example.administrator.testone.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.administrator.testone.base.MyApplication;

/**
 * 工具合集
 */
public class Util {
    private static Context context=MyApplication.getContext();
    private static String spName = "testOneSP";

    private static void getContext() {
        if (context == null) {
            context = MyApplication.getContext();
        }

    }


    /**
     * 获取当前app版本号
     *
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static int getVersionCode() throws PackageManager.NameNotFoundException {
        getContext();
        PackageManager manager = context.getPackageManager();
        PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
        return info.versionCode;
    }

    public static void putString(String key, String value) {
        getContext();
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(String key) {
        getContext();
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        return sp.getString(key, "");
    }
    public static Object getObject(String key,Object msg){
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        String type=msg.getClass().getSimpleName();
        if (type.equals("Boolean")){
            return sp.getBoolean(key,(Boolean) msg);
        }else if (type.equals("Integer")){
            return sp.getInt(key,(int) msg);
        }else {
            return sp.getString(key,"");
        }
    }


}
