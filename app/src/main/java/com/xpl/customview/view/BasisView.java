package com.xpl.customview.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class BasisView extends View {

    public BasisView(Context context) {
        super(context);
    }

    /**
     * 资源文件引用时调用
     * @param context
     * @param attrs
     */
    public BasisView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public BasisView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //设置画笔
//        Paint paint = new Paint();
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.STROKE); //仅描边
//        paint.setStrokeWidth(5);

//        canvas.drawCircle(190,200,150,paint);

        //画圆
//        paint.setColor(0x7EFFFF00);
//        paint.setStyle(Paint.Style.FILL); //仅描边加填充
//        canvas.drawCircle(190,200,100,paint);

//        canvas.drawRGB(255,0,255); //设置画板背景
//        canvas.drawColor(0xFFFF00FF);
//        canvas.drawARGB(0xFF,0xFF,0,0xFF);


        //画线
//        canvas.drawLine(100,500,400,500,paint);

        //画点
        Paint paint1 = new Paint();
        paint1.setColor(Color.RED);
        paint1.setStrokeWidth(100);
//        canvas.drawPoint(100,100,paint1);

        canvas.drawRect(200,200,200,200,paint1);

    }
}
