package com.example.administrator.testone.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Printer;
import android.view.View;
import android.view.WindowManager;

import com.example.administrator.testone.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 创建时间:2018/11/23
 * 作者:LiBW
 * 描述:
 */
public class Clock extends View {
    private int viewWidth;//控件尺寸
    public Clock(Context context) {
        this(context,null);
    }

    public Clock(Context context,  AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint=new Paint();//画笔
        paint.setAntiAlias(true);//抗锯齿
        paint.setTextSize(viewWidth/20);//设置字体大小

        //内圆外圆组成了圆环
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            canvas.drawArc(0,0,viewWidth,viewWidth,0,360,false,paint);
            paint.setColor(Color.WHITE);
            canvas.drawArc(2,2,viewWidth-2,viewWidth-2,0,360,false,paint);
        }
        //刻度线
        paint.setStrokeWidth(viewWidth/60);
        paint.setColor(Color.BLACK);
        //绘制大刻度线
        for (int i=0;i<12;i++){
            drawKeDu(i*30,paint,canvas,true);
        }
        //绘制小刻度线
        paint.setStrokeWidth(viewWidth/80);
        for (int i=0;i<60;i++){
            drawKeDu(i*6,paint,canvas,false);
        }
        //时间
        Date date=new Date();
        float hao=Calendar.getInstance().get(Calendar.MILLISECOND);//毫秒
        //秒针
        paint.setColor(Color.RED);
        SimpleDateFormat ssFormat=new SimpleDateFormat("ss");
        String ss=ssFormat.format(date);
        float s=Float.valueOf(ss)+hao/1000;
        canvas.save();
        canvas.rotate(s*6,viewWidth/2,viewWidth/2);
        Path path=new Path();
        path.moveTo(viewWidth/2,0);
        path.lineTo(viewWidth/1.95f,viewWidth/1.9f);
        path.lineTo(viewWidth/2.05f,viewWidth/1.9f);
        path.close();
        canvas.drawPath(path,paint);
        canvas.restore();

        //分针
        paint.setColor(Color.BLACK);
        SimpleDateFormat ffFormat=new SimpleDateFormat("mm");
        String ff=ffFormat.format(date);
        float f=Float.valueOf(ff)+s/60;
        paint.setStrokeWidth(viewWidth/80);
        canvas.save();
        canvas.rotate(f*6,viewWidth/2,viewWidth/2);
        canvas.drawLine(viewWidth/2,viewWidth/2,viewWidth/2,viewWidth/7,paint);
        canvas.restore();
        //时针
        SimpleDateFormat SZFormat=new SimpleDateFormat("HH");
        String sz=SZFormat.format(date);
        float z=Float.valueOf(sz)+f/12;
        paint.setStrokeWidth(viewWidth/60);
        canvas.save();
        canvas.rotate(z*6,viewWidth/2,viewWidth/2);
        canvas.drawLine(viewWidth/2,viewWidth/2,viewWidth/2,viewWidth/5,paint);
        canvas.restore();
        //中间圆点
        paint.setColor(Color.GREEN);
        canvas.drawCircle(viewWidth/2,viewWidth/2,viewWidth/60,paint);



    }


    private void drawKeDu(float angle,Paint paint,Canvas canvas,boolean isBig){
        canvas.save();
        canvas.rotate(angle,viewWidth/2,viewWidth/2);
       if (isBig){
           canvas.drawLine(viewWidth/2,0,viewWidth/2,viewWidth/20,paint);

           int time=(int)(angle/30);
            if (time==0){
                time=12;
            }
            //将数字对齐
            if (time<10){
                canvas.drawText(time+"",viewWidth/2.05f,viewWidth/10,paint);
            }else {
                canvas.drawText(time+"",viewWidth/2.1f,viewWidth/10,paint);
            }

       }else{

           canvas.drawLine(viewWidth/2,0,viewWidth/2,viewWidth/40,paint);
       }
        canvas.restore();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        viewHeight=MeasureSpec.getSize(heightMeasureSpec);
        if (widthMeasureSpec>=heightMeasureSpec){
            viewWidth=MeasureSpec.getSize(heightMeasureSpec);
        }else {
            viewWidth=MeasureSpec.getSize(widthMeasureSpec);

        }


    }
}
