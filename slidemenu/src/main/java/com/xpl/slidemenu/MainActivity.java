package com.xpl.slidemenu;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ListView lv_main;

    //数据
    ArrayList<MyBean> myBeans;

    private SlideLayout slideLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //准备数据
        myBeans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            myBeans.add(new MyBean("Content" + i));
        }

        lv_main = (ListView) findViewById(R.id.lv_main);
        lv_main.setAdapter(new Slidedapter());
    }

    private class Slidedapter extends BaseAdapter {

        @Override
        public int getCount() {
            return myBeans.size();
        }


        @Override
        public Object getItem(int position) {
            return null;
        }


        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_mian, null);
                viewHolder = new ViewHolder();
                viewHolder.item_content = (TextView) convertView.findViewById(R.id.item_content);
                viewHolder.item_menu = (TextView) convertView.findViewById(R.id.item_menu);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
                if (slideLayout!=null)
                        slideLayout.closeMenu(); //重用item是要关闭
            }
            viewHolder.item_content.setText(myBeans.get(position).getName());

            viewHolder.item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(MainActivity.this,"点击"+myBeans.get(position).getName(),Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.item_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myBeans.remove(position);
                    notifyDataSetChanged();//刷新适配器
                    SlideLayout slideLayout= (SlideLayout) v.getParent();
                        slideLayout.closeMenu();
                }
            });

            //监听Item的操作状态
            SlideLayout slideLayout = (SlideLayout) convertView;
            slideLayout.setOnStateChangListenter(new MyOnStateChangListenter());

            return convertView;
        }
    }

    class MyOnStateChangListenter implements SlideLayout.OnStateChangListenter {

        @Override
        public void onClose(SlideLayout layout) {
//            Log.d(TAG, "onClose: ");
            if (slideLayout == layout) {
                Log.d(TAG, "onClose: "+slideLayout);
                slideLayout = null;
            }
        }

        @Override
        public void onOpen(SlideLayout layout) {
            Log.d(TAG, "onOpen: "+layout);
            slideLayout=layout;


        }

        @Override
        public void onDown(SlideLayout layout) {
//            Log.d(TAG, "onDown: ");
            if (slideLayout != layout && slideLayout != null) {
                Log.d(TAG, "onDown: "+slideLayout);
                slideLayout.closeMenu();
            }

        }
    }

    static class ViewHolder {
        TextView item_content;
        TextView item_menu;
    }

}
