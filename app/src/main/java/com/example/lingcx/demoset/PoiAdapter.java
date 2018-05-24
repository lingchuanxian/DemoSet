package com.example.lingcx.demoset;

import android.support.annotation.Nullable;

import com.baidu.mapapi.search.core.PoiInfo;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/4/29 下午4:51
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class PoiAdapter extends BaseQuickAdapter<PoiInfo,BaseViewHolder> {
    private int mSelectedItem ;
    public PoiAdapter(@Nullable List<PoiInfo> data) {
        super(R.layout.layout_item_poi,data);
        mSelectedItem = -1;
    }

    @Override
    protected void convert(BaseViewHolder helper, PoiInfo item) {
        helper.setText(R.id.tv_name, item.name)
                .setText(R.id.tv_address,item.address);
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
