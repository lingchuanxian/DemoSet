package com.example.lingcx.demoset;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/7/15 下午5:16
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class NoScrollWebView extends WebView {

    @SuppressLint("NewApi")
    public NoScrollWebView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public NoScrollWebView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NoScrollWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollWebView(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }

}
