package com.xpl.viewpage_guanggaotiao;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ViewPager viewpager;
    private TextView tv_title;
    private LinearLayout ll_point_group;

    /**
     * 图片资源ID
     */
    private int[] imageIds = {
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.d,
            R.drawable.e
    };
    // 图片标题集合
    private String[] imageDescriptions = {
            "尚硅谷波河争霸赛！",
            "凝聚你我，放飞梦想！",
            "抱歉没座位了！",
            "7月就业名单全部曝光！",
            "平均起薪11345元"
    };
    private ArrayList<ImageView> imageViews;
    /**
     * 上一次高亮显示的位置
     */
    private int prePosition = 0;

    /**
     * 是否已经滚动
     */
    private boolean isDragging;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager = findViewById(R.id.viewpager);
        tv_title = findViewById(R.id.tv_title);
        ll_point_group = findViewById(R.id.ll_point_group);

        //ViewPager的使用
        //1.在布局文件中定义ViewPager
        //2.在代码中实例化ViewPager
        //3.准备数据
        imageViews = new ArrayList<>();
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(imageIds[i]);
            //添加到集合中
            imageViews.add(imageView);

            //添加点
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point_selector);
            //控件参数
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 8);
            if (i == 0) {
                point.setEnabled(true); //显示红色
            } else {
                point.setEnabled(false);//显示灰色
                params.leftMargin = 8; //左外边距
            }

            point.setLayoutParams(params);

            ll_point_group.addView(point);
        }
        //设置适配器
        viewpager.setAdapter(new MyPagerAdapter());
        //监听Viewpage页面改变
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        //设置Viewpager初始为中间页
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageViews.size();
        viewpager.setCurrentItem(item);

        handler.sendEmptyMessageDelayed(0, 3000);
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            int item = viewpager.getCurrentItem() + 1;
            viewpager.setCurrentItem(item);

            handler.sendEmptyMessageDelayed(0, 3000);
        }
    };

    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
//            return imageViews.size();
            return Integer.MAX_VALUE;  //实现无限循环
        }

        /**
         * 相当于getView方法
         *
         * @param container ViewPager自身
         * @param position  当前实例化页面的位置
         * @return
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {

            int realPosition = position % imageViews.size();
            //关联Adapter
            final ImageView imageView = imageViews.get(realPosition);
            //添加进viewpager
            container.addView(imageView);

            imageView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            //手指按下
                            handler.removeCallbacksAndMessages(null);
                            break;

                        case MotionEvent.ACTION_MOVE:   //手指移动
                            break;

                        case MotionEvent.ACTION_CANCEL:
//                            Log.e(TAG,"onTouch==事件取消");
                            break;

                        case MotionEvent.ACTION_UP://手指离开
                            handler.sendEmptyMessageDelayed(0, 3000);
                            break;
                    }
                    return false;
                }
            });

            //*设置与此视图相关的标签   param tag一个用来标记视图的对象
            imageView.setTag(position);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: 点击事件");
                    int i = (int)v.getTag() % imageViews.size();
                    String text = imageDescriptions[i];
                    Toast.makeText(MainActivity.this,"text=="+text,Toast.LENGTH_SHORT).show();
                }
            });

            return imageView;
        }

        /**
         * 比较view和object是否同一个实例
         *
         * @param view 页面
         * @param o    这个方法instantiateItem返回的结果
         * @return
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        /**
         * 释放资源
         *
         * @param container viewpager
         * @param position  要释放的位置
         * @param object    要释放的页面
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//            Log.e(TAG, "destroyItem==" + position + ",---object==" + object);
            container.removeView((View) object);
        }
    }

    /**
     * 监听viewpager改变
     */
    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面滚动了的时候回调这个方法
         *
         * @param i  当前页面的位置
         * @param v  滑动页面的百分比
         * @param i1 在屏幕上滑动的像数
         */
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        /**
         * 当某个页面被选中了的时候回调
         *
         * @param position 被选中页面的位置
         */
        @Override
        public void onPageSelected(int position) {

            //当前位置
            int realPosition = position % imageViews.size(); //实现无限循环，防止数组越界
            //设置对应页面的文本信息
            tv_title.setText(imageDescriptions[realPosition]);

            //把上一个高亮的设置默认-灰色
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            //当前的设置为高亮-红色
            ll_point_group.getChildAt(realPosition).setEnabled(true);

            prePosition = realPosition;
        }


        /**
         * 当页面滚动状态变化的时候回调这个方法
         * 静止->滑动
         * 滑动-->静止
         * 静止-->拖拽
         */
        @Override
        public void onPageScrollStateChanged(int state) {

            if (state == ViewPager.SCROLL_STATE_DRAGGING) {
                isDragging = true;
                handler.removeCallbacksAndMessages(null);
                Log.e(TAG, "SCROLL_STATE_DRAGGING----------1---------");
            } else if (state == ViewPager.SCROLL_STATE_SETTLING) {
                Log.e(TAG, "SCROLL_STATE_SETTLING--------2---------");

            } else if (state == ViewPager.SCROLL_STATE_IDLE && isDragging) {
                isDragging = false;
                Log.e(TAG, "SCROLL_STATE_IDLE------3------");
                handler.removeCallbacksAndMessages(null);
                handler.sendEmptyMessageDelayed(0, 4000);
            }
        }
    }
}
