package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class FinancialServeBean {

    /**
     * data : [{"address":"北京","applyForNumber":40,"coverImg":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","financialServeName":"金融服务","id":1,"label":"信息技术,军民融合,新材料行业,投资与服务","name":"腾飞资本"},{"address":"北京","applyForNumber":40,"coverImg":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","financialServeName":"金融服务","id":2,"label":"文创,消息,科技","name":"艺苑兴文股权投资基金"},{"address":"北京","applyForNumber":40,"coverImg":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","financialServeName":"金融服务","id":3,"label":"消费服务,聚焦于TMT,医疗健康,智能制造","name":"达晨创业投资有限公司"}]
     * message : 成功
     * status : 200
     */

    private String message;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 北京
         * applyForNumber : 40
         * coverImg : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png
         * financialServeName : 金融服务
         * id : 1
         * label : 信息技术,军民融合,新材料行业,投资与服务
         * name : 腾飞资本
         */

        private String address;
        private int applyForNumber;
        private String coverImg;
        private String financialServeName;
        private int id;
        private String label;
        private String name;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getApplyForNumber() {
            return applyForNumber;
        }

        public void setApplyForNumber(int applyForNumber) {
            this.applyForNumber = applyForNumber;
        }

        public String getCoverImg() {
            return coverImg;
        }

        public void setCoverImg(String coverImg) {
            this.coverImg = coverImg;
        }

        public String getFinancialServeName() {
            return financialServeName;
        }

        public void setFinancialServeName(String financialServeName) {
            this.financialServeName = financialServeName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
