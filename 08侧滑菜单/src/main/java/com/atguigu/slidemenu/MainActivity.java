package com.atguigu.slidemenu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv_main;

    private ArrayList<MyBean> myBeans;

    private MyAdapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_main = (ListView) findViewById(R.id.lv_main);

        //设置适配器
        //准备数据
        myBeans = new ArrayList<>();
        for(int i=0;i<100;i++){
            myBeans.add(new MyBean("Content"+i));
        }
        myAdapter = new MyAdapter();
        lv_main.setAdapter(myAdapter);

    }

    class MyAdapter extends BaseAdapter{

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
            if(convertView ==null){
                convertView = View.inflate(MainActivity.this,R.layout.item_main,null);
                viewHolder = new ViewHolder();
                viewHolder.item_content = (TextView) convertView.findViewById(R.id.item_content);
                viewHolder.item_menu = (TextView) convertView.findViewById(R.id.item_menu);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //根据位置得到内容
            final MyBean myBean = myBeans.get(position);
            viewHolder.item_content.setText(myBean.getName());

            viewHolder.item_content.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyBean myBean1 = myBeans.get(position);
                    Toast.makeText(MainActivity.this, myBean1.getName(), Toast.LENGTH_SHORT).show();
                }
            });

            viewHolder.item_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    SlideLayout slideLayout = (SlideLayout) v.getParent();
                    slideLayout.closeMenu();
                    myBeans.remove(myBean); //删除一项
//                    myAdapter.notifyDataSetChanged();
                    notifyDataSetChanged(); //刷新
                }
            });

            SlideLayout slideLayout = (SlideLayout) convertView;
            slideLayout.setOnStateChangeListenter(new MyOnStateChangeListenter());

            return convertView;
        }
    }

    private SlideLayout slideLayout;

    class MyOnStateChangeListenter implements SlideLayout.OnStateChangeListenter {

        @Override
        public void onClose(SlideLayout layout) {
            if(slideLayout ==layout){
                slideLayout = null;
            }

        }

        @Override
        public void onDown(SlideLayout layout) {
            if(slideLayout != null && slideLayout!=layout){
                slideLayout.closeMenu();
            }

        }

        @Override
        public void onOpen(SlideLayout layout) {
            slideLayout = layout;
        }
    }

    static class ViewHolder{
        TextView item_content;
        TextView item_menu;
    }
}
