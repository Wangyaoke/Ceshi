package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/6.
 */
public class UserBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"member":{"jmi_id":1,"jmi_acc":"13718231452","jmi_username":"mrqb_DC26CA2W","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jmi_des":"","is_zan":null,"is_guan":null,"is_hui":null,"is_fan":null,"strand_id":"航空航天,能源材料","phone":null,"job":null,"strand_user":null,"likeNum":5,"fansNum":2,"msgNum":0,"jmi_type":"P","jmi_zktype":null,"trendNum":2}}
     */

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
         * member : {"jmi_id":1,"jmi_acc":"13718231452","jmi_username":"mrqb_DC26CA2W","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jmi_des":"","is_zan":null,"is_guan":null,"is_hui":null,"is_fan":null,"strand_id":"航空航天,能源材料","phone":null,"job":null,"strand_user":null,"likeNum":5,"fansNum":2,"msgNum":0,"jmi_type":"P","jmi_zktype":null,"trendNum":2}
         */
 /*"expireTime": null,
         "headImg": "http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLXdbpoWw6vOlGJvmvO0vMZCTpYA5VCfaDAGqcxFK8aUB7zeNE8777At5msS10W6TKEhibDgc2LFCg/132",
         "id": 28,
         "isActive": false,
         "loginType": "WeChat",
         "phone": null,
         "userName": "周经文",
         "vipLevel": 0*/
        private String id;
        private String headImg;
        private String isActive;
        private String loginType;
        private String userName;
        private String vipLevel;
        private String phone;

        public String getphone() {
            return phone;
        }

        public void setphone(String phone) {
            this.phone = phone;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String getheadImg() {
            return headImg;
        }

        public void setheadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getisActive() {
            return isActive;
        }

        public void setisActive(String isActive) {
            this.isActive = isActive;
        }

        public String getloginType() {
            return loginType;
        }

        public void setloginType(String loginType) {
            this.loginType = loginType;
        }

        public String getuserName() {
            return userName;
        }

        public void setuserName(String userName) {
            this.userName = userName;
        }

        public String getvipLevel() {
            return vipLevel;
        }

        public void setvipLevel(String vipLevel) {
            this.vipLevel = vipLevel;
        }








    }
}
