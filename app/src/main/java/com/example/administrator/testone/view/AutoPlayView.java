package com.example.administrator.testone.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.example.administrator.testone.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间:2019/3/19
 * 描述:自定义轮播框架
 * @author WXlib
 */
public class AutoPlayView extends RelativeLayout {
    /**
     * 轮播器
     */
    private ViewPager mViewPager;
    /**
     * 指示器
     */
    private LinearLayout mIndicator;
    /**
     * 指示器当前位置
     */
    private int currentPosition;
    /**
     * 图片列表
     */
    private List<ImageView> mList = new ArrayList<>();
    /**
     * 指示器上一个位置
     */
    private int prePosition;
    /**
     * 图片总数
     */
    private int imageCount;
    /**
     * 轮播计时器Handler
     */
    private Handler carouselHandler = new Handler();
    /**
     * 图片路径
     */
    private int imageResource;
    /**
     * 轮播计时器Runnable
     */
    private Runnable carouseRunnable = new Runnable() {
        @Override
        public void run() {
            //当前页数+1
            currentPosition++;
            //翻页
            mViewPager.setCurrentItem(currentPosition);
            //2000毫秒后自动翻页
            carouselHandler.postDelayed(this, 2000);
        }
    };

    public AutoPlayView(Context context) {
        this(context, null);
    }

    public AutoPlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.item_auto_play, this);
        mViewPager = view.findViewById(R.id.mViewPager);
        mIndicator = view.findViewById(R.id.mIndicator);
        //获取图片资源位置
        if (attrs!=null){
            TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.AutoPlayView);
            imageResource=a.getResourceId(R.styleable.AutoPlayView_ImageArray,0);
            a.recycle();
        }
        //在array.xml中设置需要轮播的图片
        TypedArray imageArray = getResources().obtainTypedArray(imageResource);
        //获取需要轮播的图片总数
        imageCount = imageArray.length();
        int[] images = new int[imageCount];
        //循环遍历将图片放入ViewPager
        for (int i = 0; i < imageCount; i++) {
            //将图片ID放入数组
            images[i] = imageArray.getResourceId(i, 0);
            ImageView mView = new ImageView(context);
            mView.setImageDrawable(ContextCompat.getDrawable(context, images[i]));
            mList.add(mView);
        }
        //资源回收
        imageArray.recycle();
        //为ViewPager添加适配器
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                //将数量设置为最大值(无限循环)
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
                //官方建议
                return view == o;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                //对Viewpager页号求模获得实际位置
                position %= imageCount;

                //如果View已经在之前添加到了一个父组件，则必须先remove，否则会闪退。
                ImageView view = mList.get(position);
                ViewParent viewParent = view.getParent();
                if (viewParent != null) {
                    ViewGroup parent = (ViewGroup) viewParent;
                    parent.removeView(view);
                }
                container.addView(mList.get(position));
                return mList.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                //不要在这里removeView
            }
        });
        //将ViewPager放在中间
        int middle = Integer.MAX_VALUE / 2 - (Integer.MAX_VALUE / 2) % imageCount;
        mViewPager.setCurrentItem(middle);
        //将当前位置的值设置为中间值
        currentPosition = middle;
        //处理指示器
        for (int i = 0; i < imageCount; i++) {
            ImageView mImageView = new ImageView(context);
            //将自定义的背景色设置给指示器做背景
            mImageView.setBackgroundResource(R.drawable.icon_select_point);
            //将第一个点设置为当前要展示的颜色其他为默认颜色
            if (i == 0) {
                mImageView.setEnabled(true);
            } else {
                mImageView.setEnabled(false);
            }
            //设置指示器小圆点的间距
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(20, 0, 0, 0);
            mImageView.setLayoutParams(params);
            //将小圆点添加到指示器的布局中
            mIndicator.addView(mImageView);
        }

        //监听ViewPager的页面变动
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                //当页面变动的时候改变位置的记录
                currentPosition = position;
                //将之前改变的指示器小圆点恢复到默认
                mIndicator.getChildAt(prePosition).setEnabled(false);
                //将指示器的小圆点切换为当前颜色
                mIndicator.getChildAt(position % imageCount).setEnabled(true);
                //获取图片上一个位置
                prePosition = position % imageCount;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                //滑动状态下不滚动否则滚动
                if (ViewPager.SCROLL_STATE_DRAGGING == state) {
                    carouselHandler.removeCallbacks(carouseRunnable);
                } else if (ViewPager.SCROLL_STATE_SETTLING == state) {
                    carouselHandler.removeCallbacks(carouseRunnable);
                    carouselHandler.postDelayed(carouseRunnable, 2000);
                }
            }
        });
        //开启滚动展示
        carouselHandler.postDelayed(carouseRunnable, 2000);
    }

    public void recycleHandler() {
        carouselHandler.removeCallbacksAndMessages(null);
    }
}






