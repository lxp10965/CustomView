package com.xpl.customview;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ListView listview;
    List<String> datas;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview=findViewById(R.id.listview);
        initDatas();

    }

    private void initDatas() {
        System.out.println("遍历目录");
        datas = new ArrayList<>();
        File dir = Environment.getExternalStorageDirectory(); //要遍历的目录
        Log.i(TAG, "initDatas: "+dir);
        visitAllDirsAndFiles(dir);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas);//新建并配置ArrayAapeter
        listview.setAdapter(adapter);
    }

    private void visitAllDirsAndFiles(File dir) {
        System.out.println("==============="+dir.getName());
        if (dir.getName().contains(".ogg")||dir.getName().contains(".mp3")){
            datas.add(dir.getName());
            Log.i(TAG, "visitAllDirsAndFiles: "+datas.size());
        }
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                visitAllDirsAndFiles(new File(dir, children[i]));

            }
        }
    }
}