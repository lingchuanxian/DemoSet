package com.example.lingcx.demoset;


import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/5/23 下午9:11
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class SectionItem extends SectionEntity<Menu> {
    public SectionItem(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public SectionItem(Menu menu) {
        super(menu);
    }
}
