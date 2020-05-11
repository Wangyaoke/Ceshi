package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class StudySearchBean {

    /**
     * data : {"endRow":5,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"list":[{"startPage":1,"createTime":1557380905000,"isDelete":false,"isCollection":0,"typeId":2,"id":2,"endPage":20,"title":"《招标采购法律法规与政策》辅导材料","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/3ea21a79-7e33-4ac8-bcda-7b6ed05fd1e6.pdf"},{"startPage":76,"createTime":1557381275000,"isDelete":false,"isCollection":0,"typeId":2,"id":5,"endPage":89,"title":"招标采购法律法规与政策(重点归纳)","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1599e040-65f1-41af-a39c-508cdc85977c.pdf"},{"startPage":90,"createTime":1557381344000,"isDelete":false,"isCollection":0,"typeId":2,"id":6,"endPage":136,"title":"招标采购法律法规与政策重点","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/49b5bff2-2c92-4cfb-8a6f-c1ed269a7732.pdf"},{"startPage":137,"createTime":1557381475000,"isDelete":false,"isCollection":0,"typeId":2,"id":7,"endPage":152,"title":"招投标法律法规汇编","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/a9c378e0-fe23-4de8-8c2e-a4b88edfcd34.pdf"},{"startPage":153,"createTime":1557381588000,"isDelete":false,"isCollection":0,"typeId":2,"id":8,"endPage":158,"title":"政府采购法律法规","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/89e3442b-6aca-4216-ab2f-422963afe84e.pdf"}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":10,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}
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
        return "StudySearchBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public static class DataBean {
        /**
         * endRow : 5
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * list : [{"startPage":1,"createTime":1557380905000,"isDelete":false,"isCollection":0,"typeId":2,"id":2,"endPage":20,"title":"《招标采购法律法规与政策》辅导材料","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/3ea21a79-7e33-4ac8-bcda-7b6ed05fd1e6.pdf"},{"startPage":76,"createTime":1557381275000,"isDelete":false,"isCollection":0,"typeId":2,"id":5,"endPage":89,"title":"招标采购法律法规与政策(重点归纳)","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1599e040-65f1-41af-a39c-508cdc85977c.pdf"},{"startPage":90,"createTime":1557381344000,"isDelete":false,"isCollection":0,"typeId":2,"id":6,"endPage":136,"title":"招标采购法律法规与政策重点","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/49b5bff2-2c92-4cfb-8a6f-c1ed269a7732.pdf"},{"startPage":137,"createTime":1557381475000,"isDelete":false,"isCollection":0,"typeId":2,"id":7,"endPage":152,"title":"招投标法律法规汇编","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/a9c378e0-fe23-4de8-8c2e-a4b88edfcd34.pdf"},{"startPage":153,"createTime":1557381588000,"isDelete":false,"isCollection":0,"typeId":2,"id":8,"endPage":158,"title":"政府采购法律法规","content":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/89e3442b-6aca-4216-ab2f-422963afe84e.pdf"}]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * pageNum : 1
         * pageSize : 10
         * pages : 1
         * prePage : 0
         * size : 5
         * startRow : 1
         * total : 5
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
             * startPage : 1
             * createTime : 1557380905000
             * isDelete : false
             * isCollection : 0
             * typeId : 2
             * id : 2
             * endPage : 20
             * title : 《招标采购法律法规与政策》辅导材料
             * content : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/3ea21a79-7e33-4ac8-bcda-7b6ed05fd1e6.pdf
             */

            private int startPage;
            private long createTime;
            private boolean isDelete;
            private int isCollection;
            private int typeId;
            private int id;
            private int endPage;
            private String title;
            private String content;

            public int getStartPage() {
                return startPage;
            }

            public void setStartPage(int startPage) {
                this.startPage = startPage;
            }

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public boolean isIsDelete() {
                return isDelete;
            }

            public void setIsDelete(boolean isDelete) {
                this.isDelete = isDelete;
            }

            public int getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(int isCollection) {
                this.isCollection = isCollection;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getEndPage() {
                return endPage;
            }

            public void setEndPage(int endPage) {
                this.endPage = endPage;
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

            @Override
            public String toString() {
                return "ListBean{" +
                        "startPage=" + startPage +
                        ", createTime=" + createTime +
                        ", isDelete=" + isDelete +
                        ", isCollection=" + isCollection +
                        ", typeId=" + typeId +
                        ", id=" + id +
                        ", endPage=" + endPage +
                        ", title='" + title + '\'' +
                        ", content='" + content + '\'' +
                        '}';
            }
        }
    }
}
