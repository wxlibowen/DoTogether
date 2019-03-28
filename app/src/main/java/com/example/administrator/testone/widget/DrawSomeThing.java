package com.example.administrator.testone.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

/**
 * 创建时间:2019/3/19
 * 描述:
 * @author WXlib
 */
public class DrawSomeThing {
    abstract class AbstractDrawShape {
        abstract void draw(Canvas canvas, Paint paint);
    }

   public class line extends AbstractDrawShape {
        int startX;
        int startY;
        int endX;
        int endY;

        public line(int startX, int startY, int endX, int endY) {
            this.startX = startX;
            this.startY = startY;
            this.endY = endY;
            this.endX = endX;
        }

        @Override
        public void draw(Canvas canvas, Paint paint) {
                canvas.drawLine(startX,startY,endX,endY,paint);
        }
    }
    public class rect extends Shape{

        @Override
        public void draw(Canvas canvas, Paint paint) {

        }
    }


}
