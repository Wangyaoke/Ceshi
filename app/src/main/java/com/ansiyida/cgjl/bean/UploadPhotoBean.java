package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/3/12.
 */
public class UploadPhotoBean {

    /**
     * status : 0001
     * message : 成功
     * data : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b80d620c19044255a7780418d968f2e2.jpg?Expires=4674448421&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=aUrtBUzDCQXAw84SdsqvsJ0sN%2FI%3D
     */

    private String status;
    private String message;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
