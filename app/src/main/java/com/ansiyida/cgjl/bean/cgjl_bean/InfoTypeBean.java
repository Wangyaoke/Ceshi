package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class InfoTypeBean {

    /**
     * data : [{"code":"公开招标公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/e81f219c-261d-40ff-85bf-52f36b3e2bc0.png","id":7,"isDelete":false,"name":"公开招标","sort":16,"status":true},{"code":"采购需求","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/b4579371-58a4-48fc-afdd-be4cd73037c1.png","id":15,"isDelete":false,"name":"采购需求","sort":13,"status":true},{"code":"涉密采购","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/e2ff9cd8-da0f-4114-b8b6-1303b59719da.png","id":16,"isDelete":false,"name":"涉密采购","sort":12,"status":true},{"code":"单一来源公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/12e424e4-90d9-4d4d-827f-152001855901.png","id":4,"isDelete":false,"name":"单一来源公示","sort":11,"status":true},{"code":"成交公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/2596f70d-4d18-4b9c-9e2a-aeff1ec03e31.png","id":3,"isDelete":false,"name":"成交公告","sort":10,"status":true},{"code":"中标公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/005692fc-90d6-4ed9-a1bd-98d2d55f45e8.png","id":2,"isDelete":false,"name":"中标公告","sort":9,"status":true},{"code":"更正公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/64d20816-4530-46dd-895d-83f8412d6ad9.png","id":5,"isDelete":false,"name":"更正公告","sort":9,"status":true},{"code":"询价公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/fb6bd4ff-a3db-4257-aa63-b896c6b31700.png","id":8,"isDelete":false,"name":"询价公告","sort":7,"status":true},{"code":"竞争性谈判公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/477ab2eb-a457-4714-ab9b-318614178c51.png","id":9,"isDelete":false,"name":"竞争性谈判","sort":6,"status":true},{"code":"资格预审公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/8f908c3e-8ace-4da6-a81b-a69d2b48e416.png","id":10,"isDelete":false,"name":"资格预审","sort":5,"status":true},{"code":"邀请招标公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ef4a73b5-39bd-47a2-9e0f-62f27a9280cf.png","id":11,"isDelete":false,"name":"邀请公告","sort":4,"status":true},{"code":"竞争性磋商公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7af56142-62da-47e2-ab09-6ba3caebfcba.png","id":12,"isDelete":false,"name":"竞争性磋商","sort":3,"status":true},{"code":"终止公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/084b8cca-cb20-454b-a857-c6835aa60850.png","id":13,"isDelete":false,"name":"废标流标","sort":2,"status":true},{"code":"其他公告","icon":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/032c3e63-35c6-402b-91a3-500ea360cbc8.png","id":14,"isDelete":false,"name":"其他公告","sort":1,"status":true}]
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
         * code : 公开招标公告
         * icon : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/e81f219c-261d-40ff-85bf-52f36b3e2bc0.png
         * id : 7
         * isDelete : false
         * name : 公开招标
         * sort : 16
         * status : true
         */

        private String code;
        private String icon;
        private int id;
        private boolean isDelete;
        private String name;
        private int sort;
        private boolean status;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
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

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
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
