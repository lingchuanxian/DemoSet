package com.example.lingcx.demoset;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/6/2 下午6:28
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_send)
    void click(View view){
        switch (view.getId()){
            case R.id.btn_send:
                NotificationUtils.createNotification(this,R.mipmap.ic_launcher,"123","title","content",new Intent(),1);
                break;
            default:
                break;
        }
    }
}
