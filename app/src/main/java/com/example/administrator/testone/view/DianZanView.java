package com.example.administrator.testone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.testone.R;


/**
 * 创建时间:2018/12/14
 * 作者:LiBW
 * 描述:
 * @author WXlib
 */
public class DianZanView extends View {
    private float height;
    private float width;
    private float lineLongStart = 1f;
    private float lineLongEnd = 0f;
    private Handler handler = new Handler();

    private Runnable runnableStart = new Runnable() {
        @Override
        public void run() {
            if (lineLongStart >= 1f) {
                return;
            }
            if (lineLongStart == 0.5f) {
                lineLongEnd = 0f;
                handler.post(runnableEnd);
            }
            lineLongStart += 0.1f;
            invalidate();
            handler.postDelayed(runnableStart, 30);
        }
    };

    private Runnable runnableEnd = new Runnable() {
        @Override
        public void run() {
            if (lineLongEnd >= 1f) {
                return;
            }
            lineLongEnd += 0.1f;
            invalidate();
            handler.postDelayed(runnableEnd, 30);
        }
    };

    public DianZanView(Context context, float lineLong) {
        this(context, null);
        this.lineLongStart = lineLong;
        this.setVisibility(GONE);
    }

    public DianZanView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.setVisibility(GONE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(getResources().getColor(R.color.yellow_zan));
        paint.setStrokeWidth(20);
        float startY = height - height / 7 * lineLongEnd;
        float stopY = height - height / 7 * lineLongStart;
        canvas.drawLine(width / 2, startY, width / 2, stopY, paint);
        canvas.save();
        canvas.rotate(-45, width / 2, height);
        canvas.drawLine(width / 2 - width / 120, startY, width / 2 - width / 120, stopY, paint);
        canvas.restore();
        canvas.save();
        canvas.rotate(-90, width / 2, height);
        canvas.drawLine(width / 2 - width / 100, startY, width / 2 - width / 100, stopY, paint);
        canvas.restore();
        canvas.save();
        canvas.rotate(45, width / 2, height);
        canvas.drawLine(width / 2 + width / 120, startY, width / 2 + width / 120, stopY, paint);
        canvas.restore();
        canvas.save();
        canvas.rotate(90, width / 2, height);
        canvas.drawLine(width / 2 + width / 100, startY, width / 2 + width / 100, stopY, paint);
        canvas.restore();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec) * 0.98f;
        width = MeasureSpec.getSize(widthMeasureSpec);
    }

    public void startAnimator() {
        lineLongEnd = 0f;
        lineLongStart = 0f;
        this.post(runnableStart);
    }
}
