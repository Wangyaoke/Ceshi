package com.ansiyida.cgjl.bean;

import com.google.gson.annotations.SerializedName;

public class buyvipaliPaybean {

    /**
     {
     "data": {
     "package": "Sign=WXPay",
     "appid": "wx8855ca7656c29232",
     "sign": "8DD388BBE1317405B27E49139BFCB575",
     "partnerid": "1525551981",
     "prepayid": "wx280015576869095bc03330660273105835",
     "noncestr": "b919adcb86144da2bc1eca735ef31776",
     "timestamp": "1551284157"
     },
     "message": "成功",
     "status": 200
     }     */

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
