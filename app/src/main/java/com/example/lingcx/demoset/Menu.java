package com.example.lingcx.demoset;

/**
 * @author ling_cx
 * @version 1.0
 * @description
 * @date 2018/5/23 下午9:28
 * @copyright: 2018 www.kind.com.cn Inc. All rights reserved.
 */
public class Menu {
    private int images;
    private String text;

    public Menu(String text) {
        this.text = text;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
