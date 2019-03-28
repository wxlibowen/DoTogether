package com.example.administrator.testone.activity;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.testone.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author WXlib
 */
public class PopupWindowActivity extends AppCompatActivity {
    private ListView mPullDownLV;
    private boolean flag = true;
    private List<String> companyList = new ArrayList<>();
    private final String TAG = "日志";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        TextView mPullDownTV = findViewById(R.id.mPullDownTV);
        mPullDownLV = findViewById(R.id.mPullDownLV);
        initList();
        mPullDownLV.setVisibility(View.GONE);

        mPullDownTV.setOnClickListener(v -> {
                    flag = !flag;
                    if (flag) {
                        animationHide(mPullDownLV);

                        mPullDownLV.setVisibility(View.GONE);
                    } else {
                        animationShow(mPullDownLV);

                        mPullDownLV.setVisibility(View.VISIBLE);
                    }
                }
        );
        SparseArray<String> array = new SparseArray<>();
        array.put(1, "今天");
        array.put(1, "天气");
        array.put(2, "不错");
        array.put(3, "移除");
        array.put(-1, "可以的");
        array.put(0, "有点意思");
        array.remove(3);
        Log.d(TAG, "onCreate: "+array);


    }

    private void animationShow(View view) {
//        TranslateAnimation mShowAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
//        AlphaAnimation mShowAction=new AlphaAnimation(0.0f,1.0f);
        Animation mShowAction = AnimationUtils.loadAnimation(PopupWindowActivity.this, R.anim.translate_show);
        view.startAnimation(mShowAction);
//        TranslateAnimation mShowAction=new TranslateAnimation(0f,0f,-500f,0f);


        mShowAction.setInterpolator(new DecelerateInterpolator());
        view.setAnimation(mShowAction);
    }


    private void animationHide(View view) {
//        TranslateAnimation mHiddenAction = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
//                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
//                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
//                -1.0f);
//        AlphaAnimation mHiddenAction=new AlphaAnimation(1.0f,0.0f);
        Animation mHiddenAction = AnimationUtils.loadAnimation(this, R.anim.translate_hide);
        mHiddenAction.setInterpolator(new DecelerateInterpolator());
        view.setAnimation(mHiddenAction);
    }

    private void initList() {
        companyList.add("高达");
        companyList.add("中行高科");
        companyList.add("红枫科技有限公司");
        companyList.add("哈尔滨制药六厂集团有限公司");
        CompanyAdapter mAdapter = new CompanyAdapter(this, companyList);
        mPullDownLV.setAdapter(mAdapter);
    }


    class CompanyAdapter extends BaseAdapter {
        private Context mContext;
        private List<String> dataList;

        CompanyAdapter(Context mContext, List<String> dataList) {
            this.mContext = mContext;
            this.dataList = dataList;
        }

        @Override
        public int getCount() {
            return dataList.size();
        }

        @Override
        public Object getItem(int position) {
            return dataList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder holder;
            if (convertView == null) {
                view = LayoutInflater.from(mContext).inflate(R.layout.item_list_company_name, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            String companyName = dataList.get(position);
            holder.mCompanyNameTV.setText(companyName);
            return view;
        }

        class ViewHolder {
            private TextView mCompanyNameTV;

            ViewHolder(View view) {
                mCompanyNameTV = view.findViewById(R.id.mCompanyNameTV);
            }
        }


    }


}
