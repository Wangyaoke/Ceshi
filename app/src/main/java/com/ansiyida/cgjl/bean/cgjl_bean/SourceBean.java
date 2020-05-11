package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class SourceBean {

    /**
     * data : [{"code":"中国政府采购网","id":1,"isDelete":false,"name":"中国政府采购网","sort":1,"status":true},{"code":"全军武器装备采购网","id":2,"isDelete":false,"name":"全军武器装备采购网","sort":2,"status":true},{"code":"武警物资采购网","id":4,"isDelete":false,"name":"武警物资采购网","sort":4,"status":true},{"code":"军队采购网","id":5,"isDelete":false,"name":"军队采购网","sort":5,"status":true},{"code":"机电产品招标投标电子交易平台","id":6,"isDelete":false,"name":"机电产品招标投标电子交易平台","sort":6,"status":true}]
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
         * code : 中国政府采购网
         * id : 1
         * isDelete : false
         * name : 中国政府采购网
         * sort : 1
         * status : true
         */

        private String code;
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
