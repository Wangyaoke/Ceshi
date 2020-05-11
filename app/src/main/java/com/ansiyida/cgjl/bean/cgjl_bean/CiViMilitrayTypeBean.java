package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class CiViMilitrayTypeBean {

    /**
     * data : {"endRow":5,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"list":[{"createTime":1559725745000,"id":1,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0b945be2-e8d0-4d03-8c4c-316c02faba79.png","isShow":true,"name":"产业园入驻","sort":1},{"createTime":1559725774000,"id":2,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/bc072438-8e95-42b9-823a-4daff773d435.png","isShow":true,"name":"金融服务","sort":2},{"createTime":1559725787000,"id":3,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/c2dbd2af-90c1-4ad9-8fd2-a706a6de3d27.png","isShow":true,"name":"法律服务","sort":3},{"createTime":1559725798000,"id":4,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/5f993e12-604f-4873-a8a6-3d81094ef6fa.png","isShow":true,"name":"资质证书","sort":4},{"createTime":1559725807000,"id":5,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/708e2cb1-8c3d-4ee3-99d2-48dcc281ea5b.png","isShow":true,"name":"军品研发支撑","sort":5}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":5,"startRow":1,"total":5}
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

    public static class DataBean {
        /**
         * endRow : 5
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * list : [{"createTime":1559725745000,"id":1,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0b945be2-e8d0-4d03-8c4c-316c02faba79.png","isShow":true,"name":"产业园入驻","sort":1},{"createTime":1559725774000,"id":2,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/bc072438-8e95-42b9-823a-4daff773d435.png","isShow":true,"name":"金融服务","sort":2},{"createTime":1559725787000,"id":3,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/c2dbd2af-90c1-4ad9-8fd2-a706a6de3d27.png","isShow":true,"name":"法律服务","sort":3},{"createTime":1559725798000,"id":4,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/5f993e12-604f-4873-a8a6-3d81094ef6fa.png","isShow":true,"name":"资质证书","sort":4},{"createTime":1559725807000,"id":5,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/708e2cb1-8c3d-4ee3-99d2-48dcc281ea5b.png","isShow":true,"name":"军品研发支撑","sort":5}]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * pageNum : 1
         * pageSize : 20
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

        public static class ListBean {
            /**
             * createTime : 1559725745000
             * id : 1
             * img : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0b945be2-e8d0-4d03-8c4c-316c02faba79.png
             * isShow : true
             * name : 产业园入驻
             * sort : 1
             */

            private long createTime;
            private int id;
            private String img;
            private boolean isShow;
            private String name;
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
}
