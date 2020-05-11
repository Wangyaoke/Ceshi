package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class OptionBean {

    /**
     * data : [{"createTime":1555381927000,"id":7,"isDelete":false,"name":"通用设备制造业","parentId":1,"sort":13},{"createTime":1555483372000,"id":15,"isDelete":false,"name":"专业设备制造业","parentId":1,"sort":12}]
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
         * createTime : 1555381927000
         * id : 7
         * isDelete : false
         * name : 通用设备制造业
         * parentId : 1
         * sort : 13
         */

        private long createTime;
        private int id;
        private boolean isDelete;
        private String name;
        private int parentId;
        private int sort;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }
    }
}
