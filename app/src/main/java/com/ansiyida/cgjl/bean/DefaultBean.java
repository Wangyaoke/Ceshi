package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/3/2.
 */
public class DefaultBean {

    /**
     * status : 0001
     * message : 评论成功.
     * data : true
     */

    private String status;
    private String message;
    private boolean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isData() {
        return data;
    }

    public void setData(boolean data) {
        this.data = data;
    }
}
