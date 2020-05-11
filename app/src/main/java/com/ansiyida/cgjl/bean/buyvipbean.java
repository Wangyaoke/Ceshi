package com.ansiyida.cgjl.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class buyvipbean {

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
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         "package": "Sign=WXPay",
         "appid": "wx8855ca7656c29232",
         "sign": "8DD388BBE1317405B27E49139BFCB575",
         "partnerid": "1525551981",
         "prepayid": "wx280015576869095bc03330660273105835",
         "noncestr": "b919adcb86144da2bc1eca735ef31776",
         "timestamp": "1551284157"*/
        @SerializedName("package")
        private String package_string;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;



        public String getpackage() {
            return package_string;
        }

        public void setpackage(String package_string) {
            this.package_string = package_string;
        }

        public String getappid() {
            return appid;
        }

        public void setappid(String appid) {
            this.appid = appid;
        }

        public String getsign() {
            return sign;
        }

        public void setsign(String sign) {
            this.sign = sign;
        }

        public String getpartnerid() {
            return partnerid;
        }

        public void setpartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getprepayid() {
            return prepayid;
        }

        public void setprepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getnoncestr() {
            return noncestr;
        }

        public void setnoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String gettimestamp() {
            return timestamp;
        }

        public void settimestamp(String timestamp) {
            this.timestamp = timestamp;
        }




    }
}
