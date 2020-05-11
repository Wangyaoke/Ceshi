package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/3/12.
 */
public class LoginBean {

    /**
     * status : 0001
     * message : 登录成功
     * data : {"shenhetime":null,"phone":null,"re_tel":null,"jzt_id":null,"remark":null,"jmi_mail_verify":"N","jmi_out_time":null,"jmi_pho_verify":"N","is_fan":"Y","jmi_pwd":"123456","nation_code":null,"token":null,"jmi_img":"","is_zan":"Y","iden_photo_f":null,"jmi_acc":"13240333391","jmi_status":"Y","strand_user":null,"jmi_real_name":null,"jmi_pwd_jm":"E10ADC3949BA59ABBE56E057F20F883E","jmi_type":"P","jmi_soure":"A","is_login_free":null,"jmi_des":"","strand_id":null,"jmi_datetimes":"2018-03-12 15:33:59","job":null,"jmi_zktype":null,"token_datetime":null,"iden_code":null,"jmi_mile":null,"jmi_id":25,"jmi_open_id":null,"iden_photo_z":null,"is_hui":"Y","jmi_username":"mrqb_0XL1R3VV","is_guan":"Y","real_name":null,"app_token":"token_39639d0c65c243a4bf425b0b9e929223","app_id":"f3263599266e51641db491c6a59f7ede","channel":null,"fgi_id":null}
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

   /* public static class DataBean {
        /**
         * shenhetime : null
         * phone : null
         * re_tel : null
         * jzt_id : null
         * remark : null
         * jmi_mail_verify : N
         * jmi_out_time : null
         * jmi_pho_verify : N
         * is_fan : Y
         * jmi_pwd : 123456
         * nation_code : null
         * token : null
         * jmi_img :
         * is_zan : Y
         * iden_photo_f : null
         * jmi_acc : 13240333391
         * jmi_status : Y
         * strand_user : null
         * jmi_real_name : null
         * jmi_pwd_jm : E10ADC3949BA59ABBE56E057F20F883E
         * jmi_type : P
         * jmi_soure : A
         * is_login_free : null
         * jmi_des :
         * strand_id : null
         * jmi_datetimes : 2018-03-12 15:33:59
         * job : null
         * jmi_zktype : null
         * token_datetime : null
         * iden_code : null
         * jmi_mile : null
         * jmi_id : 25
         * jmi_open_id : null
         * iden_photo_z : null
         * is_hui : Y
         * jmi_username : mrqb_0XL1R3VV
         * is_guan : Y
         * real_name : null
         * app_token : token_39639d0c65c243a4bf425b0b9e929223
         * app_id : f3263599266e51641db491c6a59f7ede
         * channel : null
         * fgi_id : null


        private Object shenhetime;
        private Object phone;
        private Object re_tel;
        private Object jzt_id;
        private Object remark;
        private String jmi_mail_verify;
        private Object jmi_out_time;
        private String jmi_pho_verify;
        private String is_fan;
        private String jmi_pwd;
        private Object nation_code;
        private Object token;
        private String jmi_img;
        private String is_zan;
        private Object iden_photo_f;
        private String jmi_acc;
        private String jmi_status;
        private Object strand_user;
        private Object jmi_real_name;
        private String jmi_pwd_jm;
        private String jmi_type;
        private String jmi_soure;
        private Object is_login_free;
        private String jmi_des;
        private Object strand_id;
        private String jmi_datetimes;
        private Object job;
        private Object jmi_zktype;
        private Object token_datetime;
        private Object iden_code;
        private Object jmi_mile;
        private int jmi_id;
        private Object jmi_open_id;
        private Object iden_photo_z;
        private String is_hui;
        private String jmi_username;
        private String is_guan;
        private Object real_name;
        private String app_token;
        private String app_id;
        private Object channel;
        private Object fgi_id;

        public Object getShenhetime() {
            return shenhetime;
        }

        public void setShenhetime(Object shenhetime) {
            this.shenhetime = shenhetime;
        }

        public Object getPhone() {
            return phone;
        }

        public void setPhone(Object phone) {
            this.phone = phone;
        }

        public Object getRe_tel() {
            return re_tel;
        }

        public void setRe_tel(Object re_tel) {
            this.re_tel = re_tel;
        }

        public Object getJzt_id() {
            return jzt_id;
        }

        public void setJzt_id(Object jzt_id) {
            this.jzt_id = jzt_id;
        }

        public Object getRemark() {
            return remark;
        }

        public void setRemark(Object remark) {
            this.remark = remark;
        }

        public String getJmi_mail_verify() {
            return jmi_mail_verify;
        }

        public void setJmi_mail_verify(String jmi_mail_verify) {
            this.jmi_mail_verify = jmi_mail_verify;
        }

        public Object getJmi_out_time() {
            return jmi_out_time;
        }

        public void setJmi_out_time(Object jmi_out_time) {
            this.jmi_out_time = jmi_out_time;
        }

        public String getJmi_pho_verify() {
            return jmi_pho_verify;
        }

        public void setJmi_pho_verify(String jmi_pho_verify) {
            this.jmi_pho_verify = jmi_pho_verify;
        }

        public String getIs_fan() {
            return is_fan;
        }

        public void setIs_fan(String is_fan) {
            this.is_fan = is_fan;
        }

        public String getJmi_pwd() {
            return jmi_pwd;
        }

        public void setJmi_pwd(String jmi_pwd) {
            this.jmi_pwd = jmi_pwd;
        }

        public Object getNation_code() {
            return nation_code;
        }

        public void setNation_code(Object nation_code) {
            this.nation_code = nation_code;
        }

        public Object getToken() {
            return token;
        }

        public void setToken(Object token) {
            this.token = token;
        }

        public String getJmi_img() {
            return jmi_img;
        }

        public void setJmi_img(String jmi_img) {
            this.jmi_img = jmi_img;
        }

        public String getIs_zan() {
            return is_zan;
        }

        public void setIs_zan(String is_zan) {
            this.is_zan = is_zan;
        }

        public Object getIden_photo_f() {
            return iden_photo_f;
        }

        public void setIden_photo_f(Object iden_photo_f) {
            this.iden_photo_f = iden_photo_f;
        }

        public String getJmi_acc() {
            return jmi_acc;
        }

        public void setJmi_acc(String jmi_acc) {
            this.jmi_acc = jmi_acc;
        }

        public String getJmi_status() {
            return jmi_status;
        }

        public void setJmi_status(String jmi_status) {
            this.jmi_status = jmi_status;
        }

        public Object getStrand_user() {
            return strand_user;
        }

        public void setStrand_user(Object strand_user) {
            this.strand_user = strand_user;
        }

        public Object getJmi_real_name() {
            return jmi_real_name;
        }

        public void setJmi_real_name(Object jmi_real_name) {
            this.jmi_real_name = jmi_real_name;
        }

        public String getJmi_pwd_jm() {
            return jmi_pwd_jm;
        }

        public void setJmi_pwd_jm(String jmi_pwd_jm) {
            this.jmi_pwd_jm = jmi_pwd_jm;
        }

        public String getJmi_type() {
            return jmi_type;
        }

        public void setJmi_type(String jmi_type) {
            this.jmi_type = jmi_type;
        }

        public String getJmi_soure() {
            return jmi_soure;
        }

        public void setJmi_soure(String jmi_soure) {
            this.jmi_soure = jmi_soure;
        }

        public Object getIs_login_free() {
            return is_login_free;
        }

        public void setIs_login_free(Object is_login_free) {
            this.is_login_free = is_login_free;
        }

        public String getJmi_des() {
            return jmi_des;
        }

        public void setJmi_des(String jmi_des) {
            this.jmi_des = jmi_des;
        }

        public Object getStrand_id() {
            return strand_id;
        }

        public void setStrand_id(Object strand_id) {
            this.strand_id = strand_id;
        }

        public String getJmi_datetimes() {
            return jmi_datetimes;
        }

        public void setJmi_datetimes(String jmi_datetimes) {
            this.jmi_datetimes = jmi_datetimes;
        }

        public Object getJob() {
            return job;
        }

        public void setJob(Object job) {
            this.job = job;
        }

        public Object getJmi_zktype() {
            return jmi_zktype;
        }

        public void setJmi_zktype(Object jmi_zktype) {
            this.jmi_zktype = jmi_zktype;
        }

        public Object getToken_datetime() {
            return token_datetime;
        }

        public void setToken_datetime(Object token_datetime) {
            this.token_datetime = token_datetime;
        }

        public Object getIden_code() {
            return iden_code;
        }

        public void setIden_code(Object iden_code) {
            this.iden_code = iden_code;
        }

        public Object getJmi_mile() {
            return jmi_mile;
        }

        public void setJmi_mile(Object jmi_mile) {
            this.jmi_mile = jmi_mile;
        }

        public int getJmi_id() {
            return jmi_id;
        }

        public void setJmi_id(int jmi_id) {
            this.jmi_id = jmi_id;
        }

        public Object getJmi_open_id() {
            return jmi_open_id;
        }

        public void setJmi_open_id(Object jmi_open_id) {
            this.jmi_open_id = jmi_open_id;
        }

        public Object getIden_photo_z() {
            return iden_photo_z;
        }

        public void setIden_photo_z(Object iden_photo_z) {
            this.iden_photo_z = iden_photo_z;
        }

        public String getIs_hui() {
            return is_hui;
        }

        public void setIs_hui(String is_hui) {
            this.is_hui = is_hui;
        }

        public String getJmi_username() {
            return jmi_username;
        }

        public void setJmi_username(String jmi_username) {
            this.jmi_username = jmi_username;
        }

        public String getIs_guan() {
            return is_guan;
        }

        public void setIs_guan(String is_guan) {
            this.is_guan = is_guan;
        }

        public Object getReal_name() {
            return real_name;
        }

        public void setReal_name(Object real_name) {
            this.real_name = real_name;
        }

        public String getApp_token() {
            return app_token;
        }

        public void setApp_token(String app_token) {
            this.app_token = app_token;
        }

        public String getApp_id() {
            return app_id;
        }

        public void setApp_id(String app_id) {
            this.app_id = app_id;
        }

        public Object getChannel() {
            return channel;
        }

        public void setChannel(Object channel) {
            this.channel = channel;
        }

        public Object getFgi_id() {
            return fgi_id;
        }

        public void setFgi_id(Object fgi_id) {
            this.fgi_id = fgi_id;
        }*/

}
