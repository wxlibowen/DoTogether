package com.example.administrator.testone.util;

import android.util.Log;

/**
 * 日志类
 */
public class LL {
    private static boolean flag = true;
    private static String TAG = "日志";

    public static void d(Object msg) {
        if (flag) {
            Log.d(TAG, " " + msg);
        }
    }

}
