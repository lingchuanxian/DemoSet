package com.example.lingcx.demoset;

import android.util.Log;

import com.blankj.utilcode.util.SPUtils;
import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import lingcx.kind.com.cn.library.KindSwitchButton;

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
        final Menu menu = item.t;
        helper.setText(R.id.tv_text,menu.getText());
        if(menu.isSwitch()){
            helper.setGone(R.id.kind_sw,true);
            KindSwitchButton switchButton = helper.getView(R.id.kind_sw);
            if(menu.isSwitchOpen()){
                switchButton.setOpen(true);
            }else{
                switchButton.setOpen(false);
            }
            //状态监听
            switchButton.setOnStateChangedListener(new KindSwitchButton.OnStateChangedListener() {
                @Override
                public void toggleToOn() {
                    Log.d(TAG, "toggleToOn: ");
                    updateSpValueByKey(menu.getKey(),1);
                }

                @Override
                public void toggleToOff() {
                    Log.d(TAG, "toggleToOff: ");
                    updateSpValueByKey(menu.getKey(),0);
                }
            });
        }else{
            helper.setGone(R.id.kind_sw,false);
        }
    }

    /**
     * 更新存储
     * @param key
     * @param value
     */
    private void updateSpValueByKey(String key, int value) {
        SPUtils.getInstance().put(key,value);
    }
}
