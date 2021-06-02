package com.xpl.fanviewpager.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.OverScroller;
import android.widget.Toast;

public class MyViewPager extends ViewPager {

    private static final String TAG = "MyViewPager";
    //手势识别器
    private GestureDetector detector;
    //开始的X轴位置
    private float startX;
    //当前显示的位置
    private int currentIndex;
    //SDK自带缓慢回弹
    private OverScroller scroller;
    //自定义缓慢回弹
//    private MyScroller scroller;
    private OnPagerChangeListener mOnPagerChangeListener;
    private float downX;
    private float downY;

    /**
     * 加载布局文件会走这里
     *
     * @param context
     * @param attrs
     */
    public MyViewPager(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        scroller = new OverScroller(context); //SDK自带缓慢回弹
//        scroller = new MyScroller(context); //自定义缓慢回弹
        //手势识别器
        detector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public void onLongPress(MotionEvent e) {
                super.onLongPress(e);
                Toast.makeText(getContext(), "长按", Toast.LENGTH_SHORT).show();
            }

            /**
             *
             * @param e1
             * @param e2
             * @param distanceX 在X轴滑动了的距离
             * @param distanceY 在Y轴滑动了的距离
             * @return
             */
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

//                移动视图的滚动位置。这将导致调用
//                 { @link #onScrollChanged( int,int,int,int)}和视图将是
//                 失效。
//                 @param x水平滚动的像素量
//                 @param y垂直滚动像素的数量
                scrollBy((int) distanceX, 0);

                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                Toast.makeText(getContext(), "双击", Toast.LENGTH_SHORT).show();
                return super.onDoubleTap(e);
            }
        });
    }

    /**
     * 如果当前方法，返回ture,拦截事件，将会触发当前控件的onTouchEvent()方法
     * 如果当前方法,返回false,不拦截事件，事件继续传递给孩子
     *
     * @param ev
     * @return
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean result = false; //默认不拦截
        detector.onTouchEvent(ev);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "onInterceptTouchEvent: ACTION_DOWN");
                //记录开始的坐标
                downX = ev.getX();
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "onInterceptTouchEvent: ACTION_MOVE");
                //2.记录结束值
                float endX = ev.getX();
                float endY = ev.getY();

                //3.计算绝对值
                float distanceX = Math.abs(endX - downX);
                float distanceY = Math.abs(endY - downY);

                if (distanceX > distanceY && distanceX > 5) {
                    result = true;
                } else {
                    scroolToPager(currentIndex);
                }

                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onInterceptTouchEvent: ACTION_UP");

                break;
        }
        return result;
    }

    float endX;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        super.onTouchEvent(ev);
        //关联手势识别
        detector.onTouchEvent(ev);

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //记录开始的X轴坐标
                startX = ev.getX();
                break;
            case MotionEvent.ACTION_MOVE:
                //移动过程在手势识别器里面做了
//                endX = ev.getX();
//                MyViewPager.this.scrollBy((int) ((int) downX - startX), 0);
//                startX = downX;
                break;
            case MotionEvent.ACTION_UP:
                //手放开的X轴坐标

                //下标的位置
                int tempIndex = currentIndex;
                float liftX = startX - endX;
                float rightX = endX - startX;
                Log.d(TAG, "liftX: " + liftX + " rightX: " + rightX);
                //左滑
                if ((liftX) > getWidth() / 2) {
                    Log.d(TAG, "左滑: " + liftX);
                    //显示下一个页面
                    tempIndex++;
                } else if ((rightX) > getWidth() / 2) {
                    Log.d(TAG, "右滑: " + rightX);
                    //显示上一个页面
                    tempIndex--;
                }

                //根据下标的位置显示指定的页面
                scroolToPager(tempIndex);

                break;
        }

        return true;
    }

    /**
     * 屏蔽非法值
     *
     * @param tempIndex
     */
    public void scroolToPager(int tempIndex) {
        if (tempIndex < 0) {
            tempIndex = 0;
        }

        if (tempIndex > getChildCount() - 1) {
            tempIndex = getChildCount() - 1;
        }
        //当前页面显示的下标位置
        currentIndex = tempIndex;

        //移动到指定的位置  currentIndex * 图片的宽度=X轴的位置
//        scrollTo(currentIndex*getWidth(),getScrollY());
        Log.d(TAG, " currentIndex*getWidth(): " + currentIndex * getWidth() + " getScrollX: " + getScrollX());
        //
        int distanceX = currentIndex * getWidth() - getScrollX();

        //移动到指定的位置  getScrollX()=currentIndex * 图片的宽度 + 滑动的巨离
        scroller.startScroll(getScrollX(), getScrollY(), distanceX, 0, Math.abs(distanceX)); //平滑回弹
        Log.d(TAG, " getScrollX(): " + getScrollX() + " distanceX: " + distanceX);

        //这里就是回调
        if (mOnPagerChangeListener != null) {
            mOnPagerChangeListener.OnPagerChange(tempIndex);
        }

        invalidate();//会调用onDraw() 、 computeScroll()
    }

    @Override
    public void computeScroll() {
//        super.computeScroll();
        if (scroller.computeScrollOffset()) {//SDK自带缓慢回弹
//        if (scroller.computeScrollOffset()) {

            float currX = scroller.getCurrX();

            scrollTo((int) currX, 0);

            invalidate();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //把每个子控件遍历出来
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            //传入每一个子控件的左上角和右下角的X,Y坐标
            view.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
        }

    }

    //
    public void setOnPagerChangeListener(OnPagerChangeListener listener) {
        mOnPagerChangeListener = listener;
    }

    public interface OnPagerChangeListener {
        /**
         * 页面滑动时回调这个方法
         *
         * @param pagerId the unique identifier of the newly checked radio button
         */
        public void OnPagerChange(int pagerId);
    }

    /**
     * @param widthMeasureSpec
     * @param heightMeasureSpec 1.测量的时候测量多次
     *                          2.widthMeasureSpec父层视图给当前视图的宽和模式
     *                          系统的onMesaure中所干的事：
     *                           1、根据 widthMeasureSpec 求得宽度width，和父view给的模式
     *                           2、根据自身的宽度width 和自身的padding 值，相减，求得子view可以拥有的宽度newWidth
     *                           3、根据 newWidth 和模式求得一个新的MeasureSpec值:
     *                            MeasureSpec.makeMeasureSpec(newSize, newmode);
     *                           4、用新的MeasureSpec来计算子view
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);

        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
//        MeasureSpec.AT_MOST;
//        MeasureSpec.UNSPECIFIED;
//        MeasureSpec.EXACTLY;
        int newsize = MeasureSpec.makeMeasureSpec(size, mode);
        System.out.println("widthMeasureSpec==" + widthMeasureSpec + "size==" + size + ",mode==" + mode);
        System.out.println("widthMeasureSpec==" + widthMeasureSpec + "sizeHeight==" + sizeHeight + ",modeHeight==" + modeHeight);

        //测量每个子控件
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            child.measure(widthMeasureSpec, heightMeasureSpec);
        }
    }

}
