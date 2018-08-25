package com.example.lingcx.demoset;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/7/27 下午9:34
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class KindToolbar extends ViewGroup {

    /**
     * Toolbar中的三个容器
     */
    private LinearLayout mLeftItemContainer;
    private LinearLayout mCenterItemContainer;
    private LinearLayout mRightItemContainer;

    public KindToolbar(Context context) {
        super(context);
    }

    public KindToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public KindToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // 添加左部容器
        mLeftItemContainer = new LinearLayout(getContext());
        LinearLayout.LayoutParams leftParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        leftParams.gravity = Gravity.START | Gravity.TOP;
        mLeftItemContainer.setLayoutParams(leftParams);
        mLeftItemContainer.setGravity(Gravity.CENTER_VERTICAL);
        addView(mLeftItemContainer);

        // 添加右部容器
        mRightItemContainer = new LinearLayout(getContext());
        LinearLayout.LayoutParams rightParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        rightParams.gravity = Gravity.END | Gravity.TOP;
        mRightItemContainer.setLayoutParams(rightParams);
        mRightItemContainer.setGravity(Gravity.CENTER_VERTICAL);
        addView(mRightItemContainer);

        // 添加中间容器(最后添加, 它的Gravity不会影响其他位置Child的改变)
        mCenterItemContainer = new LinearLayout(getContext());
        LinearLayout.LayoutParams centerParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        centerParams.gravity = Gravity.CENTER | Gravity.TOP;
        mCenterItemContainer.setLayoutParams(centerParams);
        mCenterItemContainer.setGravity(Gravity.CENTER);
        addView(mCenterItemContainer);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    public KindToolbar setNavigationView(View view){
        mLeftItemContainer.addView(view);
        return this;
    }


}
