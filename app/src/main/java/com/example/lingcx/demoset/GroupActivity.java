package com.example.lingcx.demoset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/5/23 下午9:08
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class GroupActivity extends AppCompatActivity {
    protected final String TAG = this.getClass().getSimpleName();
    private RecyclerView mRclvGroup;
    private GroupAdapter mGroupAdapter;
    private List<SectionItem> mData = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        initView();
    }

    private void initView() {
        mRclvGroup = findViewById(R.id.rclv_group);
        mRclvGroup.setLayoutManager(new LinearLayoutManager(this));
        mData.add(new SectionItem(true,"1"));
        mData.add(new SectionItem(new Menu("账号与安全")));
        mData.add(new SectionItem(true,"2"));
        mData.add(new SectionItem(new Menu("新消息提醒")));
        mData.add(new SectionItem(new Menu("勿扰模式")));
        mData.add(new SectionItem(new Menu("聊天")));
        mData.add(new SectionItem(new Menu("隐私")));
        mData.add(new SectionItem(new Menu("通用")));
        mData.add(new SectionItem(true,"3"));
        mData.add(new SectionItem(new Menu("关于微信")));
        mData.add(new SectionItem(new Menu("帮助与反馈")));
        mData.add(new SectionItem(true,"4"));
        mData.add(new SectionItem(new Menu("插件")));
        mData.add(new SectionItem(true,"5"));
        mData.add(new SectionItem(new Menu("切换帐号")));
        mData.add(new SectionItem(new Menu("退出")));
        mData.add(new SectionItem(true,"6"));
        mGroupAdapter = new GroupAdapter(mData);
        mRclvGroup.setAdapter(mGroupAdapter);

    }
}
