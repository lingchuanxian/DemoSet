package com.example.lingcx.demoset;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.idescout.sql.SqlScoutServer;

import cn.lingcx.demoset.activity.GreenDaoActivity;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/4/30 上午8:52
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SqlScoutServer.create(this, getPackageName());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_bd:
                Intent intentBd = new Intent(this,BaiduMapActivity.class);
                startActivity(intentBd);
                break;
            case R.id.btn_gd:
                Intent intentGd = new Intent(this,GaodeMapActivity.class);
                startActivity(intentGd);
                break;
            case R.id.btn_fz:
                Intent intentFz = new Intent(this,GroupActivity.class);
                startActivity(intentFz);
                break;
            case R.id.btn_greendao:
                Intent intentGreenDao = new Intent(this,GreenDaoActivity.class);
                startActivity(intentGreenDao);
                break;
            case R.id.btn_switch:
                Intent intentSwitch = new Intent(this,SwitchActivity.class);
                startActivity(intentSwitch);
                break;
            case R.id.btn_XhsEmoticonsKeyboard:
                Intent intentEmotionKeyboard = new Intent(this,EmoticonsKeyboardActivity.class);
                startActivity(intentEmotionKeyboard);
                break;
            case R.id.btn_notification:
                Intent intentNotification = new Intent(this,NotificationActivity.class);
                startActivity(intentNotification);
                break;
            case R.id.btn_coordinatorLayout:
                Intent intentCoordinatorLayout = new Intent(this,CoordinatorLayoutActivity.class);
                startActivity(intentCoordinatorLayout);
                break;
            case R.id.btn_webview:
                Intent intentWebView = new Intent(this,WebViewActivity.class);
                startActivity(intentWebView);
                break;
            default:
                break;
        }
    }
}
