package com.example.lingcx.demoset;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/7/10 下午7:11
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class CoordinatorLayoutActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private List<String> mData = new ArrayList<>();

    private SimpleAdapter mSimpleAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinatorlayout);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        for(int i = 0;i < 60;i++){
            mData.add("item"+i);
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(CoordinatorLayoutActivity.this));
        mSimpleAdapter = new SimpleAdapter(mData);
        mRecyclerView.setAdapter(mSimpleAdapter);

    }
}
