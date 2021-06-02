package com.android.recyclerviewdome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
//        recycler_show_saveRadio_adapter.notifyDataSetChanged();
        RecyclerCoverFlow recycler_show_saveRadio = findViewById(R.id.recycler_radio_freq);
        CoverFlowLayoutManager manager = recycler_show_saveRadio.getCoverFlowLayout();
        recycler_show_saveRadio.setLayoutManager(manager);
        RadioFreqRecyclerViewAdapter recycler_show_saveRadio_adapter = new RadioFreqRecyclerViewAdapter(this,datas,recycler_show_saveRadio);
        recycler_show_saveRadio.setAdapter(recycler_show_saveRadio_adapter);


    }

    private void initData() {
        datas = new ArrayList<String>();
        for (int i=0;i<100;i++){
            datas.add("======="+i+"======");
        }
    }
}
