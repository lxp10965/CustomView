package com.xpl.wave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * 水波纹效果
 */
public class WaveView extends View {

    private int r;//半径
    private Paint paint;

    //点击的坐标
    private float startX;
    private float startY;

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView() {
        r = 5;
        paint = new Paint();
        paint.setColor(Color.RED);//设置颜色
        paint.setAntiAlias(true);//抗据此
        paint.setStyle(Paint.Style.STROKE);//空心圆
        paint.setStrokeWidth(r / 3);//内圆半径

//        invalidate();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            r += 5;

            int alpha = paint.getAlpha();
            alpha -= 5;
            if (alpha<0){
                alpha=0;
            }
            paint.setAlpha(alpha);//设置透明度

            paint.setStrokeWidth(r/3);

            invalidate();

        }
    };

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (paint.getAlpha()>0) {
            canvas.drawCircle(startX, startY, r, paint);
            handler.sendEmptyMessageDelayed(0,50);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                startX = event.getX();
                startY = event.getY();

                initView();
                invalidate();

                break;
            case MotionEvent.ACTION_UP:
                break;
        }

        return super.onTouchEvent(event);
    }
}
