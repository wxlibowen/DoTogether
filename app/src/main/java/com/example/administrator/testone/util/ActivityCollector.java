package com.example.administrator.testone.util;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2018/11/20
 * 作者:LiBW
 * 描述:活动的管理类 方便一次性退出所有Activity
 */
public class ActivityCollector {
    public static List<Activity> mList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        mList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        mList.remove(activity);
    }

    public static void finishAll() {

        for (Activity activity : mList) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
        mList.clear();
    }

}
