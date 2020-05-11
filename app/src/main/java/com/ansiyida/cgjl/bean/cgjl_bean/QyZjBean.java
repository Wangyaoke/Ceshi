package com.ansiyida.cgjl.bean.cgjl_bean;
/*
* 企业自荐
* */
public class QyZjBean {

    /**
     * data : null
     * message : 成功
     * status : 200
     */

    private int data;
    private String message;
    private int status;

    public Object getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "QyZjBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
