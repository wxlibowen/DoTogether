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
 * 描述:
 */
public class MyShanPing extends RelativeLayout {
    private ImageView rl;
    private TextView tv_one;
    private TextView tv_two;

    public MyShanPing(Context context,int position) {
        this(context,null,position);
    }

    public MyShanPing(Context context, AttributeSet attrs,int position) {
        super(context, attrs);
        View view=LayoutInflater.from(context).inflate(R.layout.item_splash_layout,this);
        rl=view.findViewById(R.id.rl_background);
        tv_one=view.findViewById(R.id.tv_one);
        tv_two=view.findViewById(R.id.tv_two);

        switch (position){
            case 0:
                setData(R.drawable.guide_img__one,"喵喵机4.0","全新设计,极致体验");
                break;
            case 1:
                setData(R.drawable.guide_img_two,"丰富错题打印功能","拍题优化,更快更准");
                break;
            case 2:
                setData(R.drawable.guide_img_three,"广场社区全新升级","优质内容推荐,使用技巧分享");
                break;
            case 3:
                setData(R.drawable.guide_img__four,"第四页第一行","第四页第二行");
                break;
            case 4:
                setData(R.drawable.guide_img_five,"第五页第一行","第五页第二行");
                break;
        }
    }
    private void setData(int image,String one,String two){
        Drawable drawable=getResources().getDrawable(image);
        rl.setBackground(drawable);
        tv_one.setText(one);
        tv_two.setText(two);
    }






}
