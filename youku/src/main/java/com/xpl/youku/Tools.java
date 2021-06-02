package com.xpl.youku;

import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;


public class Tools {

    public static void hideView(ViewGroup level) {
        hideView(level,0);
    }

    public static void showView(ViewGroup level) {
        showView(level,0);
    }

    public static void hideView(ViewGroup level,int startOffset) {
                                                                                                    //圆心
//        RotateAnimation rotateAnimation = new RotateAnimation(0,180,level.getWidth()/2,level.getHeight());
//        rotateAnimation.setDuration(500);//设置动画播放持续的时间
//        rotateAnimation.setFillAfter(true);//动画停留在播放完成的状态
//        rotateAnimation.setStartOffset(startOffset);//延迟多久后播放动画
//        level.startAnimation(rotateAnimation);
//
//        //在隐藏时设置子控件不可点
//        for (int i=0;i<level.getChildCount();i++){
//            View children = level.getChildAt(i);
//            children.setEnabled(false);
//        }
//            旋转动画
//        level.setRotation();

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(level,"rotation",0,180);
        objectAnimator.setDuration(500);
        objectAnimator.setStartDelay(startOffset);
        objectAnimator.start();

        level.setPivotX(level.getWidth()/2);
        level.setPivotY(level.getHeight());

    }

    public static void showView(ViewGroup level,int startOffset) {
        //旋转动画
//        RotateAnimation rotateAnimation = new RotateAnimation(180,360,level.getWidth()/2,level.getHeight());
//        rotateAnimation.setDuration(500);//设置动画播放持续的时间
//        rotateAnimation.setFillAfter(true);//动画停留在播放完成的状态
//        rotateAnimation.setStartOffset(startOffset);//延迟多久后播放动画
//        level.startAnimation(rotateAnimation);
//
//        //在显示时设置子控件为可点
//        for (int i=0;i<level.getChildCount();i++){
//            View children = level.getChildAt(i);
//            children.setEnabled(true);
//        }

        //属性动画

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(level,"rotation",180,360);
        objectAnimator.setDuration(500);
        objectAnimator.setStartDelay(startOffset);
        objectAnimator.start();

        level.setPivotX(level.getWidth()/2);
        level.setPivotY(level.getHeight());
    }
}
