package com.example.administrator.testone.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.example.administrator.testone.base.MyApplication;

public class Util {
    private static Context context;
    private static void getContext(){
//        if (context==null){
//            context=MyApplication.getContext();
//        }

    }


    /**
     * 获取当前app版本号
     * @return
     * @throws PackageManager.NameNotFoundException
     */
    public static int getVersionCode() throws PackageManager.NameNotFoundException {
        getContext();
        PackageManager manager=context.getPackageManager();
        PackageInfo info=manager.getPackageInfo(context.getPackageName(),0);
        return  info.versionCode;
    }

    public static String getToken(){
//        String appKey = "ik1qhw09ip9jp";
//        String appSecret = "ZgtalBISU0NH";
//
//        RongCloud rongCloud = RongCloud.getInstance(appKey, appSecret);
//        User User = rongCloud.user;
//
//        /**
//         * API 文档: http://www.rongcloud.cn/docs/server_sdk_api/user/user.html#register
//         *
//         * 注册用户，生成用户在融云的唯一身份标识 Token
//         */
//        UserModel user = new UserModel()
//                .setId("hHjap87")
//                .setName("RongCloud")
//                .setPortrait("http://www.rongcloud.cn/images/logo.png");
//        TokenResult result = null;
//        try {
//            result = User.register(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result.toString();
        return "";
    }





}
