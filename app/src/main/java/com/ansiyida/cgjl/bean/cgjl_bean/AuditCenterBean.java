package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class AuditCenterBean {

    /**
     * data : {"endRow":3,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"list":[{"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/aaecbeef-b015-461f-b3c8-4339304eedda.png","infoId":5229,"infoType":"product","createTime":1559119723000,"id":101,"isFinish":false,"title":"提交","content":"大炮","parentId":0},{"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/b5199b6f-62f6-44b8-8940-3d4890a06195.png","infoId":5227,"infoType":"product","createTime":1559119586000,"id":99,"isFinish":false,"title":"提交","content":"飞机","parentId":0},{"img":",https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/13b5a593-5511-4d7c-bb9f-0b0d1309e663.png,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/4a18aeb8-bebb-44ae-b5f1-44a039c871bb.png","infoId":4607,"infoType":"product","createTime":1559035255000,"id":79,"isFinish":false,"title":"提交","content":"汽车维修","parentId":0}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":3,"startRow":1,"total":3}
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
        return "AuditCenterBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public static class DataBean {
        /**
         * endRow : 3
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * list : [{"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/aaecbeef-b015-461f-b3c8-4339304eedda.png","infoId":5229,"infoType":"product","createTime":1559119723000,"id":101,"isFinish":false,"title":"提交","content":"大炮","parentId":0},{"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/b5199b6f-62f6-44b8-8940-3d4890a06195.png","infoId":5227,"infoType":"product","createTime":1559119586000,"id":99,"isFinish":false,"title":"提交","content":"飞机","parentId":0},{"img":",https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/13b5a593-5511-4d7c-bb9f-0b0d1309e663.png,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/4a18aeb8-bebb-44ae-b5f1-44a039c871bb.png","infoId":4607,"infoType":"product","createTime":1559035255000,"id":79,"isFinish":false,"title":"提交","content":"汽车维修","parentId":0}]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * prePage : 0
         * size : 3
         * startRow : 1
         * total : 3
         */

        private int endRow;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int navigatePages;
        private int nextPage;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "endRow=" + endRow +
                    ", hasNextPage=" + hasNextPage +
                    ", hasPreviousPage=" + hasPreviousPage +
                    ", isFirstPage=" + isFirstPage +
                    ", isLastPage=" + isLastPage +
                    ", navigateFirstPage=" + navigateFirstPage +
                    ", navigateLastPage=" + navigateLastPage +
                    ", navigatePages=" + navigatePages +
                    ", nextPage=" + nextPage +
                    ", pageNum=" + pageNum +
                    ", pageSize=" + pageSize +
                    ", pages=" + pages +
                    ", prePage=" + prePage +
                    ", size=" + size +
                    ", startRow=" + startRow +
                    ", total=" + total +
                    ", list=" + list +
                    ", navigatepageNums=" + navigatepageNums +
                    '}';
        }

        public static class ListBean {
            /**
             * img : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/aaecbeef-b015-461f-b3c8-4339304eedda.png
             * infoId : 5229
             * infoType : product
             * createTime : 1559119723000
             * id : 101
             * isFinish : false
             * title : 提交
             * content : 大炮
             * parentId : 0
             */

            private String img;
            private int infoId;
            private String infoType;
            private long createTime;
            private int id;
            private boolean isFinish;
            private String title;
            private String content;
            private int parentId;

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public int getInfoId() {
                return infoId;
            }

            public void setInfoId(int infoId) {
                this.infoId = infoId;
            }

            public String getInfoType() {
                return infoType;
            }

            public void setInfoType(String infoType) {
                this.infoType = infoType;
            }

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

            public boolean isIsFinish() {
                return isFinish;
            }

            public void setIsFinish(boolean isFinish) {
                this.isFinish = isFinish;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "img='" + img + '\'' +
                        ", infoId=" + infoId +
                        ", infoType='" + infoType + '\'' +
                        ", createTime=" + createTime +
                        ", id=" + id +
                        ", isFinish=" + isFinish +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        ", parentId=" + parentId +
                        '}';
            }
        }
    }
}
