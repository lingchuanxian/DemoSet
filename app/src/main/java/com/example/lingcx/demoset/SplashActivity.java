package com.example.lingcx.demoset;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.blankj.utilcode.util.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/8/11 上午7:14
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.imgv_splash)
    ImageView mImgvSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        initViews();
        initData();
    }

    protected void initViews() {
        mImgvSplash.setImageBitmap(ImageLoader.load(this, R.mipmap.splash));
    }

    protected void initData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                ActivityUtils.startActivity(HomeActivity.class);
                finish();
            }
        }, 2000);
    }
}
