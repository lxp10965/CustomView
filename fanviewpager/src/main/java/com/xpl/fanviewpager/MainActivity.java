package com.xpl.fanviewpager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.xpl.fanviewpager.view.MyViewPager;

import java.io.OptionalDataException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int[] ids = {
            R.drawable.a1,
            R.drawable.a2,
            R.drawable.a3,
            R.drawable.a4,
            R.drawable.a5,
            R.drawable.a6,
    };

    private MyViewPager myViewPager;
    private RadioGroup rg_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myViewPager=(MyViewPager) findViewById(R.id.myviewpager);
        rg_main=(RadioGroup) findViewById(R.id.rd_main);

        for (int i=0;i<ids.length;i++){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(ids[i]);

            //将图片添加到Viewpager中
            myViewPager.addView(imageView);
        }

        //添加测试页面
        View view=View.inflate(this,R.layout.test,null);
        myViewPager.addView(view,2);

        for (int i=0;i<myViewPager.getChildCount();i++){
            RadioButton radioButton = new RadioButton(this);
            radioButton.setId(i);

            if (i==0){
                radioButton.setChecked(true);
            }

            //添加到RadioGroup
            rg_main.addView(radioButton);

        }

        //监听RadioGroup点击
        rg_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                myViewPager.scroolToPager(checkedId);
            }
        });

        //监听Viewpager变化，改变RadioButton选中
        myViewPager.setOnPagerChangeListener(new MyViewPager.OnPagerChangeListener() {
            @Override
            public void OnPagerChange(int pagerId) {
                rg_main.check(pagerId);
            }
        });
    }
}
