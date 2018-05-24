package com.example.lingcx.demoset;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/5/23 下午9:11
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class GroupAdapter extends BaseSectionQuickAdapter<SectionItem,BaseViewHolder> {

    public GroupAdapter(List<SectionItem> data) {
        super(R.layout.layout_item_menu, R.layout.layout_item_menu_head, data);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, SectionItem item) {
        //helper.setText(R.id.tv_head,item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, SectionItem item) {
        helper.setText(R.id.tv_text,item.t.getText());
    }
}
