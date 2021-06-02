package com.xpl.popupwindow;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText et_input;
    ImageView iv_down_arrow;

    private PopupWindow popupWindow;   //弹出窗口
    private ListView listview;
    private ArrayList<String> arr;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_input = (EditText) findViewById(R.id.et_input);
        iv_down_arrow = (ImageView) findViewById(R.id.iv_down_arrow);

        iv_down_arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("TAG", "onClick: ");
                if (popupWindow == null) {
                    popupWindow = new PopupWindow(MainActivity.this);
                    popupWindow.setWidth(et_input.getWidth());

                    popupWindow.setContentView(listview);
                    popupWindow.setFocusable(true);
                }
                //在一个弹出窗口中显示内容视图锚定在锚定视图的左下角指定的x和y坐标偏移量。
                popupWindow.showAsDropDown(et_input, 0, 0);
            }
        });

        listview = new ListView(this);
        //设置背景资源
        listview.setBackgroundResource(R.drawable.listview_background);

        //准备数据
        arr = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            arr.add(i + "====qwe====" + i);
        }

        myAdapter=new MyAdapter();
        listview.setAdapter(myAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //得到数据
                String msg = arr.get(position);
                //设置到输入框
                et_input.setText(msg);

                if (popupWindow!=null&&popupWindow.isShowing()){
                    popupWindow.dismiss();
                    popupWindow=null;
                }
            }
        });
    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return arr.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 返回一个view对象，这个view对象显示在指定的位置
         *
         * @param position    item的位置
         * @param convertView 回收的view
         * @param parent      父容器
         * @return 返回的view对象
         */
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHoler viewHoler;
            if (convertView == null) {
                convertView = View.inflate(MainActivity.this, R.layout.item_main, null);
                viewHoler = new ViewHoler();
                viewHoler.textView = (TextView) convertView.findViewById(R.id.textView);
                viewHoler.imageView = (ImageView) convertView.findViewById(R.id.imageView);
                convertView.setTag(viewHoler);

            } else {
                viewHoler = (ViewHoler) convertView.getTag();
            }
            //根据位置得到数据
            final String msg = arr.get(position);
            viewHoler.textView.setText(msg);

            //设置删除
            viewHoler.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //重集合中删除
                    arr.remove(msg);
                    //刷新==刷新适配器
                    myAdapter.notifyDataSetChanged();
                }
            });
            return convertView;
        }

    }

    static class ViewHoler {
        TextView textView;
        ImageView imageView;
    }
}
