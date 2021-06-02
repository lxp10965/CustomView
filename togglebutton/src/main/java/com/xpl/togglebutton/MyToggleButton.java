package com.xpl.togglebutton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 开关切换按钮
 * 一个视图从创建到显示过程中的主要方法
 * * //1.构造方法实例化类
 * * //2.测量-measure(int,int)-->onMeasure();
 * * 如果当前View是一个ViewGroup,还有义务测量孩子
 * * 孩子有建议权
 * * //3.指定位置-layout()-->onLayout();
 * * 指定控件的位置，一般View不用写这个方法，ViewGroup的时候才需要，一般View不需要重写该方法
 * * //4.绘制视图--draw()-->onDraw(canvas)
 * * 根据上面两个方法参数，进入绘制
 */
public class MyToggleButton extends View implements View.OnClickListener {

    //画笔
    private Paint paint;
    //使用位图得到资源
    private Bitmap backgroundBitmap;
    private Bitmap slidingBitmap;
    private int slidLeftMax;
    /**
     * 开关  默认是关
     */
    private boolean isOpen = false;
    private float slidLeft = 0;
    /*
    按下的位置
     */
    private float startX;
    //结束的位置
    private float endX;

    private boolean isEnabledClick = true;
    private float lastX;

    public MyToggleButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setAntiAlias(true);//抗锯齿
        backgroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
        slidingBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.slide_button);
        //得到滑动的最大距离
        slidLeftMax = backgroundBitmap.getWidth() - slidingBitmap.getWidth();
        setOnClickListener(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
                lastX = startX = event.getX();
                break;
            case MotionEvent.ACTION_MOVE://移动
                endX = event.getX();
                //3.计算偏移量
                float distanceX = endX - startX;
                slidLeft = slidLeft + distanceX;
                Log.d("slidLeft: " + slidLeft, "distanceX: " + distanceX);

                if (slidLeft < 0) {
                    slidLeft = 0;
                } else if (slidLeft > slidLeftMax) {
                    slidLeft = slidLeftMax;
                }

                //4.屏蔽非法值
                //5.刷新
                invalidate();
                //6.数据还原
                startX = event.getX();
//                Log.d("onTouchEvent", "Math.abs(endX-startX): "+Math.abs(endX-startX));

                if (Math.abs(endX - lastX) > 1) {
                    Log.d("onTouchEvent", "Math.abs(endX-lastX): " + Math.abs(endX - lastX));
                    //判断为滑动
                    isEnabledClick = false;
                }
                break;
            case MotionEvent.ACTION_UP://松开
                if (!isEnabledClick) {
                    if (slidLeft > slidLeftMax / 2) {
                        isOpen = true;
                    } else {
                        isOpen = false;
                    }
                    flushView();
                }

                break;
        }
        return true;
    }

    /**
     * 视图测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(backgroundBitmap.getWidth(), backgroundBitmap.getHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        canvas.drawBitmap(backgroundBitmap, 0, 0, paint);
        canvas.drawBitmap(slidingBitmap, slidLeft, 0, paint);
    }

    @Override
    public void onClick(View v) {
        if (isEnabledClick) {
            isOpen = !isOpen;
            flushView();
        }
    }

    private void flushView() {
        if (isOpen) {
            slidLeft = slidLeftMax;
        } else {
            slidLeft = 0;
        }
        invalidate(); //会导致onDraw()执行
    }
}
