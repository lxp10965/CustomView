package com.xpl.quickindex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 作用：快速索引
 * 绘制快速索引的字母
 * 1.把26个字母放入数组
 * 2.在onMeasure计算每条的高itemHeight和宽itemWidth,
 * 3.在onDraw和wordWidth,wordHeight,wordX,wordY
 * <p/>
 * 手指按下文字变色
 * 1.重写onTouchEvent(),返回true,在down/move的过程中计算
 * int touchIndex = Y / itemHeight; 强制绘制
 * <p/>
 * 2.在onDraw()方法对于的下标设置画笔变色
 * <p/>
 * 3.在up的时候
 * touchIndex  = -1；
 * 强制绘制
 */
public class IndexView extends View {

    private String[] words = {"A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z"};

    private final Paint paint;  //画笔

    //item宽高
    private int itemHeight;
    private int itemWidth;
    //当前点击的位置
    private int touchIndex = -1;
    private OnIndexChangeListener mOnIndexChangeListener;

    /**
     * 从XML扩展视图时调用的构造函数。
     */
    public IndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.WHITE);//字体颜色
        paint.setTextSize(24);//字体大小
        paint.setAntiAlias(true);//抗锯齿
        paint.setTypeface(Typeface.DEFAULT_BOLD);//粗体
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth(); //测量宽
        itemHeight = getMeasuredHeight() / words.length; //测量每个item的高，控件高/26个字母
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < words.length; i++) {

            if (touchIndex == i) {
                paint.setColor(Color.GRAY);//点击设为灰色
            } else {
                paint.setColor(Color.WHITE);
            }

            String word = words[i];

            Rect rect = new Rect();
            /*
            *检索文本边界框并存储到边界。
            *
            *在bounds(由调用者分配)中返回最小的矩形
            *包含所有字符，暗示原点为(0,0)。
            *
            * @param文本字符串，测量并返回其边界
            * @param开始的索引字符串中的第一个字符测量
            * @param结束1超过最后一个字符在字符串中测量
            *返回所有文本的并集边界。必须由来电者分配
            */
            paint.getTextBounds(word, 0, 1, rect);//矩形绘制在字母的上面

            //字母的高和宽
            int wordWidth = rect.width();
            int wordHeight = rect.height();

            //计算每个字母在视图上的坐标位置
            float wordX = itemWidth / 2 - wordWidth / 2;
            float wordY = itemHeight / 2 + wordHeight / 2 + i * itemHeight;

            canvas.drawText(word, wordX, wordY, paint);

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float Y = event.getY();
                int index = (int) (Y / itemHeight); //字母索引，当前点击的是第几个字母
                if (index != touchIndex) {

                    touchIndex = index;
                    invalidate();//强制绘制OnDraw
                    if (mOnIndexChangeListener != null && touchIndex < words.length)
                        mOnIndexChangeListener.onIndexChange(words[touchIndex]); // 创建回调
                }
                break;
            case MotionEvent.ACTION_UP:
                //手松开时，清空高亮
                touchIndex = -1;
                invalidate();
                break;
        }
        return true;
    }


    public void setOnIndexChangeListener(@Nullable OnIndexChangeListener l) {
        this.mOnIndexChangeListener = l;
    }

    /**
     * 创建传递索引回调接口
     */
    public interface OnIndexChangeListener {

        /**
         * 当字母下标位置发生变化的时候回调
         *
         * @param word 字母（A~Z）
         */
        void onIndexChange(String word);
    }


}
