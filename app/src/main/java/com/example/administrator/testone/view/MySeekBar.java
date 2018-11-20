package com.example.administrator.testone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.SeekBar;

import com.example.administrator.testone.R;

/**
 * 自定义滑动条
 */
public class MySeekBar extends android.support.v7.widget.AppCompatSeekBar {
    private Paint paint;
    private Drawable thumb;
    private int progressInteger = 0;

    public MySeekBar(Context context) {
        this(context, null);
    }

    public MySeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(getResources().getColor(R.color.colorBlack));
        thumb=getResources().getDrawable(R.drawable.bg_corenre);
        setThumb(thumb);
        setThumbOffset(thumb.getIntrinsicWidth());
        paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_20));//在dimen中设置文字大小来多屏幕适配
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        Rect rect = thumb.getBounds();
        //根据progress变动不停绘制新的位置
        canvas.drawText(progressInteger >0 ?"+"+progressInteger : " " + progressInteger, rect.left + (rect.width()) / 1F, rect.top - paint.ascent() + (rect.height() - (paint.descent() - paint.ascent())) / 2.3F, paint);
        canvas.restore();
    }

    @Override
    public void setOnSeekBarChangeListener(final OnSeekBarChangeListener l) {
        super.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //让activity的监听事件可以生效
                if (l!=null){
                    l.onProgressChanged(seekBar,progress,fromUser);
                }
                progressInteger = progress - 50;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    //设置thumb的偏移数值
    @Override
    public void setThumbOffset(int thumbOffset) {
        super.setThumbOffset(thumbOffset / 10);//将thumb向两侧偏移 防止thumb越界
    }
}
