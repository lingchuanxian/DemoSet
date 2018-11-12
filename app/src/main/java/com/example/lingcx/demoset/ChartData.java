package com.example.lingcx.demoset;

/**
 * Created by lcx on 2018/11/10.
 */
public class ChartData {
    private String name;
    private int count;

    public ChartData(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
