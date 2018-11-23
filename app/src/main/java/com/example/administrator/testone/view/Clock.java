package com.example.administrator.testone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 创建时间:2018/11/23
 * 作者:LiBW
 * 描述:
 */
public class Clock extends View {
    private int viewWidth;
    private int viewHeight;
    public Clock(Context context) {
        this(context,null);
    }

    public Clock(Context context,  AttributeSet attrs) {
        super(context, attrs);
        WindowManager manager= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        windowWidth=manager.getDefaultDisplay().getWidth();
//        windowHeight=manager.getDefaultDisplay().getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();

        //内圆外圆组成了圆环
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(0,0,viewWidth,viewWidth,0,360,false,paint);
            paint.setColor(Color.WHITE);
            canvas.drawArc(2,2,viewWidth-2,viewWidth-2,0,360,false,paint);
        }
        //大刻度线
        paint.setStrokeWidth(10);
        paint.setColor(Color.BLACK);
        //绘制大刻度线

        for (int i=0;i<12;i++){
            drawKeDu(i*30,paint,canvas,true);
        }
        //绘制小刻度线
        paint.setStrokeWidth(5);
        for (int i=0;i<60;i++){
            drawKeDu(i*6,paint,canvas,false);
        }
        //秒针
        Date date=new Date();
        SimpleDateFormat ssFormat=new SimpleDateFormat("ss");
        String ss=ssFormat.format(date);
        float s=Float.valueOf(ss);
        paint.setStrokeWidth(5);
        canvas.save();
        canvas.rotate(s*6,viewWidth/2,viewWidth/2);
        canvas.drawLine(viewWidth/2,viewWidth/2,viewWidth/2,viewWidth/18,paint);
        canvas.restore();

        //分针

        SimpleDateFormat ffFormat=new SimpleDateFormat("mm");
        String ff=ffFormat.format(date);
        float f=Float.valueOf(ff);
        paint.setStrokeWidth(8);
        canvas.save();
        canvas.rotate(f*6,viewWidth/2,viewWidth/2);
        canvas.drawLine(viewWidth/2,viewWidth/2,viewWidth/2,viewWidth/7,paint);
        canvas.restore();
        //时针
        SimpleDateFormat SZFormat=new SimpleDateFormat("HH");
        String sz=SZFormat.format(date);
        float z=Float.valueOf(sz);
        paint.setStrokeWidth(15);
        canvas.save();
        canvas.rotate(z*6,viewWidth/2,viewWidth/2);
        canvas.drawLine(viewWidth/2,viewWidth/2,viewWidth/2,viewWidth/5,paint);
        canvas.restore();









    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        viewHeight=MeasureSpec.getSize(heightMeasureSpec);
        viewWidth=MeasureSpec.getSize(widthMeasureSpec);
    }
    private void drawKeDu(float angle,Paint paint,Canvas canvas,boolean isBig){
        canvas.save();
        canvas.rotate(angle,viewWidth/2,viewWidth/2);
       if (isBig){
           canvas.drawLine(viewWidth/2,0,viewWidth/2,viewWidth/20,paint);
       }else{

           canvas.drawLine(viewWidth/2,0,viewWidth/2,viewWidth/40,paint);
       }
        canvas.restore();
    }
}
