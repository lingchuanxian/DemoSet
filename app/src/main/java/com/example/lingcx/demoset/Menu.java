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
    private boolean isSwitch = false;
    private boolean isSwitchOpen = false;
    private String key;

    public Menu(String text) {
        this.text = text;
    }

    public Menu(String text, boolean isSwitch, boolean isSwitchOpen,String key) {
        this.text = text;
        this.isSwitch = isSwitch;
        this.isSwitchOpen = isSwitchOpen;
        this.key = key;
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

    public boolean isSwitch() {
        return isSwitch;
    }

    public void setSwitch(boolean aSwitch) {
        isSwitch = aSwitch;
    }

    public boolean isSwitchOpen() {
        return isSwitchOpen;
    }

    public void setSwitchOpen(boolean switchOpen) {
        isSwitchOpen = switchOpen;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
