package com.example.administrator.testone.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 创建时间:2018/11/28
 * 作者:LiBW
 * 描述:
 */
public class MyHttp {
    private static MyHttp myHttp;
    private MyHttp() {
    }
    public static MyHttp getMyHttp(){
        if (myHttp==null){
            myHttp=new MyHttp();
        }
        return myHttp;
    }
    public void getHttp(String url,Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }


    public void postThengetHttp(String url, LinkedHashMap<String,String> map, Callback callback){
        OkHttpClient client=new OkHttpClient();
        FormBody.Builder formBody=new FormBody.Builder();
        for (String key:map.keySet()){
            formBody.add(key,map.get(key));
        }
        Request request=new Request.Builder().url(url).post(formBody.build()).build();
        client.newCall(request).enqueue(callback);





    }








}
