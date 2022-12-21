package com.example.jjh10.jsouprecycler;

/**
 * Created by jjh10 on 2019-03-25.
 */

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

}