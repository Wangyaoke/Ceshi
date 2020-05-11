package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/3/8.
 */
public class SearchUse {
    private String text;
    private String id;

    public SearchUse(String text,String id){
        this.text=text;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
