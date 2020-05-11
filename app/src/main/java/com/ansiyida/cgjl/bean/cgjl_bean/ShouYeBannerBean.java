package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class ShouYeBannerBean {

    /**
     * data : [{"createTime":1557388541000,"id":4,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/966d670d-2f26-4b85-b0fb-a89b1adb6620.jpg","isShow":true,"link":"","position":"banner","sort":4,"target":"业主分析","targetType":"内部","title":"业主分析"},{"createTime":1557388524000,"id":3,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1673bcff-d26b-4ffd-977d-983270291419.jpg","isShow":true,"link":"","position":"banner","sort":3,"target":"产品自荐","targetType":"内部","title":"产品自荐"},{"createTime":1557388508000,"id":2,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc5d87af-5b59-417e-98bc-23adcc3c5e82.jpg","isShow":true,"link":"","position":"banner","sort":2,"target":"企业自荐","targetType":"内部","title":"企业自荐"},{"createTime":1557388470000,"id":1,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/33f3e838-fb13-4916-8bb3-dae75b78cced.jpg","isShow":true,"link":"","position":"banner","sort":1,"target":"订阅","targetType":"内部","title":"订阅"}]
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

    @Override
    public String toString() {
        return "ShouYeBannerBean{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * createTime : 1557388541000
         * id : 4
         * img : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/966d670d-2f26-4b85-b0fb-a89b1adb6620.jpg
         * isShow : true
         * link :
         * position : banner
         * sort : 4
         * target : 业主分析
         * targetType : 内部
         * title : 业主分析
         */

        private long createTime;
        private int id;
        private String img;
        private boolean isShow;
        private String link;
        private String position;
        private int sort;
        private String target;
        private String targetType;
        private String title;

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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public boolean isIsShow() {
            return isShow;
        }

        public void setIsShow(boolean isShow) {
            this.isShow = isShow;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public String getTarget() {
            return target;
        }

        public void setTarget(String target) {
            this.target = target;
        }

        public String getTargetType() {
            return targetType;
        }

        public void setTargetType(String targetType) {
            this.targetType = targetType;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "createTime=" + createTime +
                    ", id=" + id +
                    ", img='" + img + '\'' +
                    ", isShow=" + isShow +
                    ", link='" + link + '\'' +
                    ", position='" + position + '\'' +
                    ", sort=" + sort +
                    ", target='" + target + '\'' +
                    ", targetType='" + targetType + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
