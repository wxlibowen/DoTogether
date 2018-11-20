package com.example.administrator.testone.activity;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.testone.R;
import com.example.administrator.testone.base.BaseActivity;
import com.example.administrator.testone.view.MyShanPing;

import java.util.ArrayList;
import java.util.List;

public class ViewPageActivity extends BaseActivity {
    private ViewPager viewPager;
    private List<MyShanPing> mList =new ArrayList<>();
    private ImageView iv_0,iv_1,iv_2,iv_3,iv_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        getSupportActionBar().hide();
        viewPager=findViewById(R.id.vp_photo);
        iv_0=findViewById(R.id.iv_zero);
        iv_1=findViewById(R.id.iv_one);
        iv_2=findViewById(R.id.iv_two);
        iv_3=findViewById(R.id.iv_three);
        iv_4=findViewById(R.id.iv_four);
        for (int i=0;i<5;i++){
            MyShanPing view=new MyShanPing(this,i);
            mList.add(view);
        }

        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return mList.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                return view==o;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
               container.removeView(mList.get(position));
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(mList.get(position), 0);
                return mList.get(position);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        iv_0.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_sel));
                        iv_1.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_2.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_3.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_4.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        break;
                    case 1:
                        iv_0.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_1.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_sel));
                        iv_2.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_3.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_4.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        break;
                    case 2:
                        iv_0.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_1.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_2.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_sel));
                        iv_3.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_4.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        break;
                    case 3:
                        iv_0.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_1.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_2.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_3.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_sel));
                        iv_4.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        break;
                    case 4:
                        iv_0.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_1.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_2.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_3.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_nor));
                        iv_4.setImageDrawable(getResources().getDrawable(R.drawable.guide_point_sel));
                        break;

                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });





    }
}
