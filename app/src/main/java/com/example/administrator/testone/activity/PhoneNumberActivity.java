package com.example.administrator.testone.activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.administrator.testone.R;
import com.example.administrator.testone.adapter.PhoneNumberAdapter;
import com.example.administrator.testone.adapter.PhoneNumberRecycleViewAdapter;
import com.example.administrator.testone.base.BaseActivity;
import com.example.administrator.testone.entity.PhoneNumberEntity;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import org.jetbrains.anko.ToastsKt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.internal.operators.observable.BlockingObservableNext;

public class PhoneNumberActivity extends BaseActivity {
    private ListView lv_phone;
    private RecyclerView rv_phone;
    private List<PhoneNumberEntity> mList = new ArrayList<>();
    private List<PhoneNumberEntity> newList = new ArrayList<>();
    private List<String> pinyinList = new ArrayList<>();



    private int flag=1;//1.ListView 2.RecycleView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        initView();
        lv_phone.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                Log.d("日志", "onScrollStateChanged: ");
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
        lv_phone.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(PhoneNumberActivity.this,HomeActivity.class);
                Bundle bundle=new Bundle();
                bundle.putBoolean("aaa",true);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();


            }
        });

    }







    private void initView() {
        lv_phone = findViewById(R.id.lv_phone);
        rv_phone = findViewById(R.id.rv_phone);
        readContacts();
        chuliPinYin();
        switch (flag){
            case 2:
                lv_phone.setVisibility(View.GONE);
                rv_phone.setVisibility(View.VISIBLE);
                PhoneNumberRecycleViewAdapter recycleViewAdapter=new PhoneNumberRecycleViewAdapter(this,newList);
                StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL);
                rv_phone.setLayoutManager(manager);
                rv_phone.setAdapter(recycleViewAdapter);
                break;
            default:
                lv_phone.setVisibility(View.VISIBLE);
                rv_phone.setVisibility(View.GONE);
                PhoneNumberAdapter ListViewAdapter = new PhoneNumberAdapter(newList, this);
                lv_phone.setAdapter(ListViewAdapter);
                break;
        }









    }

    private void chuliPinYin() {
        for (int i = 0; i < mList.size(); i++) {
            String a = mList.get(i).getPinYin();
            pinyinList.add(a);
        }
        Collections.sort(pinyinList);
        for (String a : pinyinList) {
            for (int i = 0; i < mList.size(); i++) {
                if (mList.get(i).getPinYin().equalsIgnoreCase(a)) {
                    PhoneNumberEntity entity = new PhoneNumberEntity();
                    entity.setName(mList.get(i).getName());
                    entity.setNumber(mList.get(i).getNumber());
                    entity.setPinYin(mList.get(i).getPinYin());
                    newList.add(entity);
                }
            }
        }

    }


    //查询联系人数据
    private void readContacts() {
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                PhoneNumberEntity entity = new PhoneNumberEntity();
                entity.setName(name);
                entity.setNumber(number);
                entity.setPinYin(getPingYin(name));
                mList.add(entity);
            }
        }


    }

    private String getPingYin(String inputString) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = inputString.trim().toCharArray();
        String output = "";

        try {
            for (char curchar : input) {
                if (java.lang.Character.toString(curchar).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(curchar, format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(curchar);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }

    class withName implements Comparable<PhoneNumberEntity> {
        private String name;
        private String number;
        private String pinyin;

        public withName(String name, String number, String pinyin) {
            this.name = name;
            this.number = number;
            this.pinyin = pinyin;
        }

        @Override
        public int compareTo(@NonNull PhoneNumberEntity o) {


            return 0;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case 3: {
                //在此处回调方法中可以再次调用需要申请权限的方法或者设置TOAST进行提示用户申请权限


            }
        }
    }
}

