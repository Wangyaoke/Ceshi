package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class MenuBean {

    /**
     * data : [{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0e30033f-db35-438c-bb87-e7f1b288bc30.png","id":7,"name":"公开招标","sort":16,"status":true},{"genre":"purchaseDemand","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/560c0fdb-f391-4359-a9c0-d848bcc465b1.png","id":15,"name":"采购需求","sort":13,"status":true},{"genre":"purchaseSecret","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/b47a1dea-b207-4e1a-be3c-16d7a8696de8.png","id":16,"name":"涉密采购","sort":12,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/11555955-f377-4058-9a05-12dde4dc1cff.png","id":4,"name":"单一来源公告","sort":11,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/fc311c2c-7ec2-4926-92f1-a4a63aaf97ad.png","id":3,"name":"成交公告","sort":10,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/9d6530e2-d6b4-4848-a5ef-dbce52a76725.png","id":2,"name":"中标公告","sort":9,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/f8305c42-bc9d-459c-8ae0-f1b56987bc0d.png","id":5,"name":"更正公告","sort":9,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/750d62ef-3417-4541-8ad1-fecd68414fe4.png","id":8,"name":"询价公告","sort":7,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/cb24cd51-f03d-460b-901a-2279383df573.png","id":9,"name":"竞争性谈判","sort":6,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/a610424e-ad5d-47c0-9db4-6df52c2a177c.png","id":10,"name":"资格预审","sort":5,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/fc884529-1ccd-4cc1-91c0-db8a1d6faac0.png","id":11,"name":"邀请公告","sort":4,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/310efd35-4a74-4e4a-aa30-64cd4720f121.png","id":12,"name":"竞争性磋商","sort":3,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/a7e82d77-01d3-40c9-8201-db77e0f22aa1.png","id":13,"name":"废标流标","sort":2,"status":true},{"genre":"purchaseInfo","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/78027ce7-fe1f-4bea-8e55-69e5fddc0851.png","id":14,"name":"其他公告","sort":1,"status":true}]
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
         * genre : purchaseInfo
         * icon : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0e30033f-db35-438c-bb87-e7f1b288bc30.png
         * id : 7
         * name : 公开招标
         * sort : 16
         * status : true
         */

        private String genre;
        private String icon;
        private int id;
        private String name;
        private int sort;
        private boolean status;

        public String getGenre() {
            return genre;
        }

        public void setGenre(String genre) {
            this.genre = genre;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }
    }
}
