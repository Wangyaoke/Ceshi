package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/1.
 */
public class WXPayBean {
    private String appid;
    private String body;
    private String package_vip;
    private String mch_id;//商户ID1525551981
    private String nonce_str;
    private String notify_url;
    private String put_trade_no;
    private String spbill_create_ip;
    private int total_fee;
    private String trade_type;
    private String sign;
    private String partnerid;
    private String prepayid;
    private String noncestr;
    private String timestamp;
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
    public String getpackage_vip() {
        return package_vip;
    }

    public void setpackage_vip(String package_vip) {
        this.package_vip = package_vip;
    }
    public String getappid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getbody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getmch_id() {
        return mch_id;
    }

    public void setMach_id(String mch_id) {
        this.mch_id = mch_id;
    }
    public String getnonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }
    public String getnotify_url() {
        return notify_url;
    }

    public void setNotify_url(String notify_url) {
        this.notify_url = notify_url;
    }
    public String getput_trade_no() {
        return put_trade_no;
    }

    public void setOut_trade_no(String put_trade_no) {
        this.put_trade_no = put_trade_no;
    }
    public String gettrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }
    public int gettotal_fee() {
        return total_fee;
    }
    public void setTotal_fee(int total_fee) {
        this.total_fee = total_fee;
    }
    public String getspbill_create_ip() {
        return spbill_create_ip;
    }

    public void setSpbill_create_ip(String spbill_create_ip) {
        this.spbill_create_ip = spbill_create_ip;
    }
    public String getsign() {
        return sign;
    }

    public void setsign(String sign) {
        this.sign = sign;
    }
}
