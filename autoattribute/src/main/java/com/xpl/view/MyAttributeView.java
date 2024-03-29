package com.xpl.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.xpl.autoattributea.R;

public class MyAttributeView extends View {
    private  Bitmap myBg;
    private String myName;
    private int myAge;

    public MyAttributeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        /**
         * format 常用类型
         * reference    引用
         * color    颜色
         * boolean    布尔值
         * dimension    尺寸值
         * float    浮点值
         * integer    整型值
         * string   字符串
         * enum   枚举
         */
        //获取属性三种方式
        //1.用命名空间取获取
        String age = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_age");
        String name = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_name");
        String bg = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_bg");
//        System.out.println("age=="+age+",name=="+name+",bg==="+bg);

        //2.遍历属性集合
        for(int i=0;i<attrs.getAttributeCount();i++){
            System.out.println(attrs.getAttributeName(i)+"====="+attrs.getAttributeValue(i));
        }

        //3.使用系统工具获取属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyAttributeView);
        for (int i=0;i<typedArray.length();i++){
            int index = typedArray.getIndex(i);

            switch (index){
                case R.styleable.MyAttributeView_my_age:
                    myAge = typedArray.getInt(index,0);
                    break;
                case R.styleable.MyAttributeView_my_name:
                    myName = typedArray.getString(index);
                    break;
                case R.styleable.MyAttributeView_my_bg:
                    Drawable drawable = typedArray.getDrawable(index);
                    BitmapDrawable drawable1 = (BitmapDrawable) drawable;
                    myBg=drawable1.getBitmap();
                    break;
            }

        }
        //记得回收
        typedArray.recycle();
    }

    /**
     * 绘制图像
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        canvas.drawText(myName+"=="+myAge,50,50,paint);
        canvas.drawBitmap(myBg,50,50,paint);
    }
}
