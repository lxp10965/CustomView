package com.xpl.pinterestlistview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * onInterceptTouchEvent();拦截触摸事件
     * 1.如果返回的是true,将会触发当前View的onTouchEvent(); 事件不会传递给孩子
     * 2.如果返回的是false,事件将会传给孩子
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return true;    //拦截了子控件的触摸事件
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        super.onTouchEvent(event);
        //分发给子控件的触摸事件
        int width = getWidth() / getChildCount();//每个子控件的宽度
        int height = getHeight();
        int count = getChildCount();

        float eventX = event.getX();
//        System.out.println("width: " + width + " height: " + height + " count: " + count + " eventX: " + eventX);

        if (eventX < width) {  //第一个
            getChildAt(0).dispatchTouchEvent(event);
            return true;
        } else if (eventX > width && eventX < width * 2) { //第二个
            float eventY = event.getY();
            if (eventY < height / 2) {

                for (int i = 0; i < count; i++) {
                    View child = getChildAt(i);
                    child.dispatchTouchEvent(event);
                }
                return true;
            } else if (eventY > height / 2) {
                getChildAt(1).dispatchTouchEvent(event);
                return true;
            }

        } else if (eventX > width * 2) { //第三个
            getChildAt(2).dispatchTouchEvent(event);
            return true;
        }

        return true;
    }
}
