package com.example.lingcx.demoset;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/7/10 下午7:31
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class SimpleAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public SimpleAdapter(@Nullable List<String> data) {
        super(R.layout.layout_item_menu_head,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_head, item);
    }
}
