package com.xpl.slidemenu;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;


public class SlideLayout extends FrameLayout {

    private static final String TAG = "SlideLayout";
    //滚动者
    private final Scroller scroller;
    //item
    private View contentView;
    private View menutView;

    private int contentWidth;
    private int menutWidth;

    //控件高度相同
    private int viewHeight;

    private float startX;
    private float downX;
    private float startY;
    private float downY;


    public SlideLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        scroller = new Scroller(context);
    }

    /**
     * 当布局文件加载完成后回调这个方法
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);
        menutView = getChildAt(1);
    }

    /**
     * 测量各个控件的宽高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        contentWidth = contentView.getMeasuredWidth();
        menutWidth = menutView.getMeasuredWidth();

        viewHeight = getMeasuredHeight();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        //指定菜单的位置  视图左上角和左下角的坐标
        menutView.layout(contentWidth, 0, contentWidth + menutWidth, viewHeight);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //1.记录开始的坐标
                downX = startX = event.getX();
                downY = startY = event.getY();

                if (onStateChangListenter != null) {
                    onStateChangListenter.onDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //2.记录结束值
                float endX = event.getX();
                float endY = event.getY();
                //3.计算偏移值
                float distanceX = endX - startX;//触摸滑动的偏移量

                int toScrollX = (int) (getScrollX() - distanceX); //getScrollX()=视图偏移量
                Log.d("getScrollX(): " + getScrollX(), " distanceX: " + distanceX);
                if (toScrollX < 0) {    //屏蔽非法值
                    toScrollX = 0;
                } else if (toScrollX > menutWidth) {
                    toScrollX = menutWidth;
                }
                scrollTo(toScrollX, getScrollY());

//                startX = event.getX();
//                startY = event.getY();

                //在X轴和Y轴滑动的距离
                float DX = Math.abs(endX - downX);
                float DY = Math.abs(endY - downY);
                if (DX > DY && DX > 8) {
                    //
                    //水平方向滑动
                    //响应侧滑
                    //反拦截-事件给SlideLayout
                    //getParent()返回此视图的父视图。
                    getParent().requestDisallowInterceptTouchEvent(true);//如果子进程不希望父进程这样做，则为 True截获 触摸事件。
                } else if (DY > DX) {
                    Log.d(TAG, "onTouchEvent: DY > DX");
//                    closeMenu();
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "onTouchEvent: ACTION_UP");
                int totalScroll = getScrollX();
                if (totalScroll < menutWidth / 2) {
                    //关闭Menu
                    closeMenu();
                } else {
                    //打开Menu
                    openMenu();
                }
                break;

        }
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        boolean intercept = false;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //1.记录开始的坐标
                downX = startX = event.getX();
                downY = startY = event.getY();

                if (onStateChangListenter != null) {
                    onStateChangListenter.onDown(this);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //2.记录结束值
                float endX = event.getX();
                float endY = event.getX();
                startX = event.getX();
                //在X轴和Y轴滑动的距离
                float DX = Math.abs(endX - downX);
                float DY = Math.abs(endY - downY);
                if (DX > 8) {
                    intercept = true; //拦截事件，不再往子视图传递
                }
                break;
            case MotionEvent.ACTION_UP:
                closeMenu();
                break;

        }
        return intercept;
    }

    /*
        打开菜单
         */
    public void openMenu() {

        int distanceX = menutWidth - getScrollX();//左滑为 正
        scroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate();//强制刷新  自动回调computeScroll
        if (onStateChangListenter != null) {
            onStateChangListenter.onOpen(this);
        }

    }

    /*
    关闭菜单
     */
    public void closeMenu() {
        int distanceX = 0 - getScrollX(); //右滑为 负
        scroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
        invalidate();//强制刷新
        if (onStateChangListenter != null) {
            onStateChangListenter.onClose(this);
        }
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            invalidate();
        }
    }

    /**
     * 创建Item状态改变接口
     */
    public interface OnStateChangListenter {
        void onClose(SlideLayout layout);

        void onOpen(SlideLayout layout);

        void onDown(SlideLayout layout);
    }


    private OnStateChangListenter onStateChangListenter;

    public void setOnStateChangListenter(OnStateChangListenter l) {
        this.onStateChangListenter = l;
    }
}
