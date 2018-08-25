package com.example.lingcx.demoset;

import com.contrarywind.interfaces.IPickerViewData;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/8/8 下午9:01
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class IntervalModel implements IPickerViewData {
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IntervalModel(int code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String getPickerViewText() {
        return name;
    }
}
