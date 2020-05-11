package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/2/26.
 */
public class DropBean {

    private String name;
    private boolean choiced = false;
    public DropBean(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isChoiced() {
        return choiced;
    }
    public void setChoiced(boolean choiced) {
        this.choiced = choiced;
    }

    }

