package com.example.jjh10.recyclerviewddd;

/**
 * Created by jjh10 on 2019-04-01.
 */

public class MyData {
    String date = "";
    String img_url = "";

    public MyData(String date, String url){
        this.img_url = url;
        this.date = date;
    }

    public String getImg_url(){
        return img_url;
    }

    public String getDate(){
        return date;
    }

}
