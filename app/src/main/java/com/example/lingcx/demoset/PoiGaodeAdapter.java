package com.example.lingcx.demoset;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/4/30 下午3:49
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class PoiGaodeAdapter extends BaseQuickAdapter<PoiAddressBean,BaseViewHolder> {
    private int mSelectedItem ;
    public PoiGaodeAdapter(@Nullable List<PoiAddressBean> data) {
        super(R.layout.layout_item_poi,data);
        mSelectedItem = -1;
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiAddressBean item) {
        helper.setText(R.id.tv_name, item.getText())
                    .setText(R.id.tv_address,item.getDetailAddress());

        if(helper.getAdapterPosition() == mSelectedItem){
            helper.setVisible(R.id.imgv_current,true);
        }else{
            helper.setVisible(R.id.imgv_current,false);
        }
    }

    public void setSelectedItem(int selectedItem){
        this.mSelectedItem = selectedItem;
        this.notifyDataSetChanged();
    }
}
