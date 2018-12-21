package com.example.administrator.testone.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.os.Build
import android.support.v4.content.ContextCompat
import android.view.View
import com.example.administrator.testone.R

class MyView(context: Context?) : View(context) {
    private var viewWidth = 0//控件尺寸

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val paint = Paint()


        paint.color = ContextCompat.getColor(context, R.color.colorGray)

        paint.strokeWidth = 10f
        viewWidth = width


    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)


    }
}
