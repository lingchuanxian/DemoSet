package com.example.lingcx.demoset;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.blankj.utilcode.util.Utils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/4/29 下午2:09
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        //初始化百度地图SDK
        SDKInitializer.initialize(getApplicationContext());
        super.onCreate();
        Utils.init(this);
        ZXingLibrary.initDisplayOpinion(this);
    }
}
