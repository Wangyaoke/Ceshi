package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/1/23.
 */
public class NewBean {
    private int type;
    private String tabName;
    private int[] picNum;
    private String[] picMsg;



    public NewBean(int type,String tabName,int[] picNum,String[] picMsg){
        this.type=type;
        this.tabName=tabName;
        this.picNum=picNum;
        this.picMsg=picMsg;
    }
    public String[] getPicMsg() {
        return picMsg;
    }

    public void setPicMsg(String[] picMsg) {
        this.picMsg = picMsg;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public int[] getPicNum() {
        return picNum;
    }

    public void setPicNum(int[] picNum) {
        this.picNum = picNum;
    }
}
