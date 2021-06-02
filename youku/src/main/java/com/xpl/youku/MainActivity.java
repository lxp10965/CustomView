package com.xpl.youku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout level1;
    private RelativeLayout level2;
    private RelativeLayout level3;
    private ImageView icon_home;
    private ImageView icon_menu;

    /**
     * 是否显示第三圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel3 = true;

    /**
     * 是否显示第二圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel2 = true;


    /**
     * 是否显示第一圆环
     * true:显示
     * false隐藏
     */
    private boolean isShowLevel1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        level1 = findViewById(R.id.level1);
        level2 = findViewById(R.id.level2);
        level3 = findViewById(R.id.level3);
        icon_home = findViewById(R.id.icon_home);
        icon_menu = findViewById(R.id.icon_menu);

        icon_home.setOnClickListener(this);
        icon_menu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.icon_home:
//                Toast.makeText(this,"icon_home",Toast.LENGTH_SHORT).show();
                //如果三级菜单二级菜单显示，都设置隐藏
                if (isShowLevel2) {
                    //设置隐藏
                    isShowLevel2 = false;
                    Tools.hideView(level2,300);

                    if (isShowLevel3) {
                        //设置隐藏
                        isShowLevel3 = false;
                        Tools.hideView(level3);
                    }
                } else {
                    //如果都隐藏，那就显示二级菜单
                    isShowLevel2 = true;
                    Tools.showView(level2);

                }
                //
                break;

            case R.id.icon_menu:
//                Toast.makeText(this, "icon_menu", Toast.LENGTH_SHORT).show();
                if (isShowLevel3){
                    Tools.hideView(level3);
                    isShowLevel3=false;
                }else {
                    Tools.showView(level3);
                    isShowLevel3=true;
                }

                break;

            default:
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode==KeyEvent.KEYCODE_MENU){
            if (isShowLevel3){
                isShowLevel3=false;
                Tools.hideView(level3);
                if (isShowLevel2){
                    isShowLevel2=false;
                    Tools.hideView(level2);
                    if (isShowLevel1){
                        isShowLevel1=false;
                        Tools.hideView(level1);
                    }
                }
            }else {
                Tools.showView(level1);
                Tools.showView(level2);
            }
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }
}
