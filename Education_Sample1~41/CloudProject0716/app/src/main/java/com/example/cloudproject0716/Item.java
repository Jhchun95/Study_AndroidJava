package com.example.cloudproject0716;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

//  ListView에 올려질 데이터 한 건을 기억하는 클래스
public class Item {

    int iv;
    String tv;

    public Item() { }
    public Item(int iv, String tv) {
        this.iv = iv;
        this.tv = tv;
    }

    public int getIv() {
        return iv;
    }
    public void setIv(int iv) {
        this.iv = iv;
    }
    public String getTv() {
        return tv;
    }
    public void setTv(String tv) {
        this.tv = tv;
    }

    @Override
    public String toString() {
        return "Item{" +
                "iv=" + iv +
                ", tv='" + tv + '\'' +
                '}';
    }
}

