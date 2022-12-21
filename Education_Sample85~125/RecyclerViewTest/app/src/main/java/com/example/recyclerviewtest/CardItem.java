package com.example.recyclerviewtest;

import java.util.Objects;



// 데이터 클래스를 만드는 방법.
public class CardItem {

    private String title;
    private String contents;

    public CardItem(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "CardItem{" +
                "title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
