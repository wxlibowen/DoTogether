package com.example.administrator.testone.activity;

import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import com.example.administrator.testone.R;
import com.example.administrator.testone.adapter.DoubanAdapter;
import com.example.administrator.testone.base.BaseActivity;
import com.example.administrator.testone.entity.DouBanEntity;
import com.example.administrator.testone.http.MyHttp;
import com.example.administrator.testone.view.MyDecoration;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RecycleActivity extends BaseActivity {
    private String Url="http://api.douban.com/v2/movie/top250";
    private DouBanEntity entity;
    private RecyclerView rv_douban;
    private DoubanAdapter adapter;
    private List<DouBanEntity.SubjectsBean> mList =new ArrayList<>();
    private MyHandler handler=new MyHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        rv_douban = findViewById(R.id.rv_douban);
        LinearSnapHelper helper=new LinearSnapHelper();
        helper.attachToRecyclerView(rv_douban);


        LinkedHashMap<String,String> map=new LinkedHashMap<>();
        map.put("start","0");
        map.put("count","20");

        MyHttp.getMyHttp().postThengetHttp(Url, map, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson=new Gson();
                entity=gson.fromJson(response.body().string(),DouBanEntity.class);
                Log.d("日志", "onResponse: "+entity.getSubjects().get(0).getTitle());
                Log.d("日志", "onResponse: "+entity.getSubjects().get(1).getTitle());
                for (DouBanEntity.SubjectsBean bean:entity.getSubjects()){
                    mList.add(bean);
                }
                handler.sendEmptyMessage(1);
            }
        });
        rv_douban.addItemDecoration(new MyDecoration());


    }
    private void getGet(){
        MyHttp.getMyHttp().getHttp("http://api.douban.com/v2/movie/top250?start=25&count=25", new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message message = new Message();
                message.obj = response.body().string();


            }
        });
    }
    class  MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    adapter=new DoubanAdapter(RecycleActivity.this, mList);
                    rv_douban.setAdapter(adapter);
                    LinearLayoutManager layoutManager=new LinearLayoutManager(RecycleActivity.this,LinearLayoutManager.HORIZONTAL,false);
                    rv_douban.setLayoutManager(layoutManager);

                    break;


            }

        }
    }
}
