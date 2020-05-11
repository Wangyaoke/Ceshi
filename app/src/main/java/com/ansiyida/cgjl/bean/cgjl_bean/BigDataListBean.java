package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class BigDataListBean {

    /**
     * data : [{"createTime":"2019-08-23 17:09:05.0","id":1,"image":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/621fd50a-9fc4-4786-9854-68569376a113.png","isShow":true,"name":"企业大数据","sort":1},{"createTime":"2019-08-23 17:09:06.0","id":2,"image":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/06cc51ff-40af-477c-ba25-ab55b3025374.png","isShow":true,"name":"采购大数据","sort":2}]
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
         * createTime : 2019-08-23 17:09:05.0
         * id : 1
         * image : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/621fd50a-9fc4-4786-9854-68569376a113.png
         * isShow : true
         * name : 企业大数据
         * sort : 1
         */

        private String createTime;
        private int id;
        private String image;
        private boolean isShow;
        private String name;
        private int sort;

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public boolean isIsShow() {
            return isShow;
        }

        public void setIsShow(boolean isShow) {
            this.isShow = isShow;
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
    }
}
