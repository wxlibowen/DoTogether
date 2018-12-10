package com.example.administrator.testone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.testone.R;

/**
 * 创建时间:2018/12/10
 * 作者:LiBW
 * 描述:
 */
public class MyXY extends View {
    private String TAG="日志";
    private int width;
    private int height;
    public MyXY(Context context) {
        this(context,null);
    }

    public MyXY(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        WindowManager manager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        width=manager.getDefaultDisplay().getWidth();
        height=manager.getDefaultDisplay().getHeight();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();
        paint.setStrokeWidth(10);
        paint.setColor(getResources().getColor(R.color.red));
        canvas.drawLine(width/2,0,width/2,height,paint);
        canvas.drawLine(0,height/2,width,height/2,paint);

        canvas.save();
        canvas.rotate(-45,width,height/2);
        canvas.drawLine(width,height/2,width,height/2-50,paint);
        canvas.restore();

        canvas.save();
        canvas.rotate(-135,width,height/2);
        canvas.drawLine(width,height/2,width,height/2-50,paint);
        canvas.restore();



        canvas.save();
        canvas.rotate(-45,width/2,height);
        canvas.drawLine(width/2,height,width/2,height-50,paint);
        canvas.restore();

        canvas.save();
        canvas.rotate(45,width/2,height);
        canvas.drawLine(width/2,height,width/2,height-50,paint);
        canvas.restore();





    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure: "+widthMeasureSpec);
        Log.d(TAG, "onMeasure: "+heightMeasureSpec);
    }
}
