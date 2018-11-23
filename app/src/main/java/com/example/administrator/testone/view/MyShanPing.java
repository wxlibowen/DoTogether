package com.example.administrator.testone.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.testone.R;


/**
 * 创建时间:2018/11/19
 * 作者:LiBW
 * 描述:自定义layout 用来展示闪屏页
 */
public class MyShanPing extends RelativeLayout {
    private ImageView rl;//背景图
    private ImageView bg_image;//背景图

    private TextView tv_one;//第一行文字
    private TextView tv_two;//第二行文字

    //此处传入上下文 和 当前需要加载第几个页面的position
    public MyShanPing(Context context, int position) {
        this(context, null, position);
    }

    public MyShanPing(Context context, AttributeSet attrs, int position) {
        super(context, attrs);
        //加载自定义layout的布局
        View view = LayoutInflater.from(context).inflate(R.layout.item_splash_layout, this);
        rl = view.findViewById(R.id.rl_background);
        tv_one = view.findViewById(R.id.tv_one);
        tv_two = view.findViewById(R.id.tv_two);
        bg_image = view.findViewById(R.id.bg_image);
        //根据不同页面展示不同数据
        switch (position) {
            case 0:
                setData(R.drawable.guide_img_one, R.drawable.guide_bg_one,"喵喵机4.0", "全新设计,极致体验");
                break;
            case 1:
                setData(R.drawable.guide_img_two, R.drawable.guide_bg_two,"丰富错题打印功能", "拍题优化,更快更准");
                break;
            case 2:
                setData(R.drawable.guide_img_three,R.drawable.guide_bg_three, "广场社区全新升级", "优质内容推荐,使用技巧分享");
                break;
            case 3:
                setData(R.drawable.guide_img_five, R.drawable.guide_bg_four,"第四页第一行", "第四页第二行");
                break;
            case 4:
                setData(R.drawable.guide_img_five, R.drawable.guide_bg_five,"第五页第一行", "第五页第二行");
                break;
        }
    }

    private void setData(int preImage,int lastImage, String one, String two) {

        Drawable drawable = getResources().getDrawable(preImage);
        rl.setImageDrawable(drawable);
        drawable=getResources().getDrawable(lastImage);
        bg_image.setImageDrawable(drawable);

        tv_one.setText(one);
        tv_two.setText(two);




    }
}
