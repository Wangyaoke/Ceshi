package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class StudyRecyclerBean {

    /**
     * data : {"all":[{"content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/c843d4b2-9cfb-47c8-8f86-bba591b5c054.pdf","createTime":1557380636000,"endPage":33,"id":1,"isDelete":false,"publishTime":null,"sort":1,"startPage":1,"title":"全国招标师职业资格考试大纲(2015年版)","typeId":1}],"current":{"content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/c843d4b2-9cfb-47c8-8f86-bba591b5c054.pdf","createTime":1557380636000,"endPage":33,"id":1,"isDelete":false,"publishTime":null,"sort":1,"startPage":1,"title":"全国招标师职业资格考试大纲(2015年版)","typeId":1},"total":33}
     * message : 成功
     * status : 200
     */

    private DataBean data;
    private String message;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

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

    @Override
    public String toString() {
        return "StudyRecyclerBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public static class DataBean {
        /**
         * all : [{"content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/c843d4b2-9cfb-47c8-8f86-bba591b5c054.pdf","createTime":1557380636000,"endPage":33,"id":1,"isDelete":false,"publishTime":null,"sort":1,"startPage":1,"title":"全国招标师职业资格考试大纲(2015年版)","typeId":1}]
         * current : {"content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/c843d4b2-9cfb-47c8-8f86-bba591b5c054.pdf","createTime":1557380636000,"endPage":33,"id":1,"isDelete":false,"publishTime":null,"sort":1,"startPage":1,"title":"全国招标师职业资格考试大纲(2015年版)","typeId":1}
         * total : 33
         */

        private CurrentBean current;
        private int total;
        private List<AllBean> all;

        public CurrentBean getCurrent() {
            return current;
        }

        public void setCurrent(CurrentBean current) {
            this.current = current;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<AllBean> getAll() {
            return all;
        }

        public void setAll(List<AllBean> all) {
            this.all = all;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "current=" + current +
                    ", total=" + total +
                    ", all=" + all +
                    '}';
        }

        public static class CurrentBean {
            /**
             * content : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/c843d4b2-9cfb-47c8-8f86-bba591b5c054.pdf
             * createTime : 1557380636000
             * endPage : 33
             * id : 1
             * isDelete : false
             * publishTime : null
             * sort : 1
             * startPage : 1
             * title : 全国招标师职业资格考试大纲(2015年版)
             * typeId : 1
             */

            private String content;
            private long createTime;
            private int endPage;
            private int id;
            private boolean isDelete;
            private Object publishTime;
            private int sort;
            private int startPage;
            private String title;
            private int typeId;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getEndPage() {
                return endPage;
            }

            public void setEndPage(int endPage) {
                this.endPage = endPage;
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

            public Object getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(Object publishTime) {
                this.publishTime = publishTime;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getStartPage() {
                return startPage;
            }

            public void setStartPage(int startPage) {
                this.startPage = startPage;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }
        }

        public static class AllBean {
            /**
             * content : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/c843d4b2-9cfb-47c8-8f86-bba591b5c054.pdf
             * createTime : 1557380636000
             * endPage : 33
             * id : 1
             * isDelete : false
             * publishTime : null
             * sort : 1
             * startPage : 1
             * title : 全国招标师职业资格考试大纲(2015年版)
             * typeId : 1
             */

            private String content;
            private long createTime;
            private int endPage;
            private int id;
            private boolean isDelete;
            private Object publishTime;
            private int sort;
            private int startPage;
            private String title;
            private int typeId;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getEndPage() {
                return endPage;
            }

            public void setEndPage(int endPage) {
                this.endPage = endPage;
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

            public Object getPublishTime() {
                return publishTime;
            }

            public void setPublishTime(Object publishTime) {
                this.publishTime = publishTime;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getStartPage() {
                return startPage;
            }

            public void setStartPage(int startPage) {
                this.startPage = startPage;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            @Override
            public String toString() {
                return "AllBean{" +
                        "content='" + content + '\'' +
                        ", createTime=" + createTime +
                        ", endPage=" + endPage +
                        ", id=" + id +
                        ", isDelete=" + isDelete +
                        ", publishTime=" + publishTime +
                        ", sort=" + sort +
                        ", startPage=" + startPage +
                        ", title='" + title + '\'' +
                        ", typeId=" + typeId +
                        '}';
            }
        }
    }
}
