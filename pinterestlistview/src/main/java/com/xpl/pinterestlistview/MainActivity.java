package com.xpl.pinterestlistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView lv1;
    private ListView lv3;
    private ListView lv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = (ListView) findViewById(R.id.lv1);
        lv2 = (ListView) findViewById(R.id.lv2);
        lv3 = (ListView) findViewById(R.id.lv3);

        lv1.setAdapter(new MyAdapter());
        lv2.setAdapter(new MyAdapter());
        lv3.setAdapter(new MyAdapter());
    }

    private int ids[] = new int[]{
            R.drawable.default1,
            R.drawable.girl1,
            R.drawable.girl2,
            R.drawable.girl3,
    };

    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 300;
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
         * @return A View corresponding to the data at the specified position.
         * 返回与指定位置的数据对应的视图
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView iv = new ImageView(MainActivity.this);
            int resId = (int) (Math.random()*4);
            iv.setBackgroundResource(ids[resId]);

            return iv;
        }
    }
}
