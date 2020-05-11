package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/6/5.
 */
public class DYListBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"pageNum":1,"pageSize":20,"size":3,"orderBy":null,"startRow":1,"endRow":3,"total":3,"pages":1,"list":[{"js_id":5,"js_name":"明日情报","js_code":"明日情报","js_remark":"明日情报","js_logo":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/3fce497a020541f2838af6e1a7c0150c.jpg","js_status":"Y","js_create_time":"2018-05-23 10:15:41","jst_id":null,"sourceType":null,"isSubscribe":"N"},{"js_id":6,"js_name":"人民日报","js_code":"人民日报","js_remark":"人民日报","js_logo":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/2e85c56ca8374ac79c17d5f471e69a77.jpg","js_status":"Y","js_create_time":"2018-05-23 10:16:13","jst_id":null,"sourceType":null,"isSubscribe":"N"},{"js_id":7,"js_name":"中科院","js_code":"中国科学院","js_remark":"中国科学院","js_logo":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/33d034191e95464bb899761d3afdf19b.jpg","js_status":"Y","js_create_time":"2018-05-23 10:16:56","jst_id":null,"sourceType":null,"isSubscribe":"N"}],"firstPage":1,"prePage":0,"nextPage":0,"lastPage":1,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1]}
     */

    private String status;
    private String message;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * pageNum : 1
         * pageSize : 20
         * size : 3
         * orderBy : null
         * startRow : 1
         * endRow : 3
         * total : 3
         * pages : 1
         * list : [{"js_id":5,"js_name":"明日情报","js_code":"明日情报","js_remark":"明日情报","js_logo":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/3fce497a020541f2838af6e1a7c0150c.jpg","js_status":"Y","js_create_time":"2018-05-23 10:15:41","jst_id":null,"sourceType":null,"isSubscribe":"N"},{"js_id":6,"js_name":"人民日报","js_code":"人民日报","js_remark":"人民日报","js_logo":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/2e85c56ca8374ac79c17d5f471e69a77.jpg","js_status":"Y","js_create_time":"2018-05-23 10:16:13","jst_id":null,"sourceType":null,"isSubscribe":"N"},{"js_id":7,"js_name":"中科院","js_code":"中国科学院","js_remark":"中国科学院","js_logo":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/33d034191e95464bb899761d3afdf19b.jpg","js_status":"Y","js_create_time":"2018-05-23 10:16:56","jst_id":null,"sourceType":null,"isSubscribe":"N"}]
         * firstPage : 1
         * prePage : 0
         * nextPage : 0
         * lastPage : 1
         * isFirstPage : true
         * isLastPage : true
         * hasPreviousPage : false
         * hasNextPage : false
         * navigatePages : 8
         * navigatepageNums : [1]
         */

        private int pageNum;
        private int pageSize;
        private int size;
        private Object orderBy;
        private int startRow;
        private int endRow;
        private int total;
        private int pages;
        private int firstPage;
        private int prePage;
        private int nextPage;
        private int lastPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private boolean hasPreviousPage;
        private boolean hasNextPage;
        private int navigatePages;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

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

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public Object getOrderBy() {
            return orderBy;
        }

        public void setOrderBy(Object orderBy) {
            this.orderBy = orderBy;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getFirstPage() {
            return firstPage;
        }

        public void setFirstPage(int firstPage) {
            this.firstPage = firstPage;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getLastPage() {
            return lastPage;
        }

        public void setLastPage(int lastPage) {
            this.lastPage = lastPage;
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

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
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
             * js_id : 5
             * js_name : 明日情报
             * js_code : 明日情报
             * js_remark : 明日情报
             * js_logo : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/3fce497a020541f2838af6e1a7c0150c.jpg
             * js_status : Y
             * js_create_time : 2018-05-23 10:15:41
             * jst_id : null
             * sourceType : null
             * isSubscribe : N
             */

            private int js_id;
            private String js_name;
            private String js_code;
            private String js_remark;
            private String js_logo;
            private String js_status;
            private String js_create_time;
            private Object jst_id;
            private Object sourceType;
            private String isSubscribe;

            public int getJs_id() {
                return js_id;
            }

            public void setJs_id(int js_id) {
                this.js_id = js_id;
            }

            public String getJs_name() {
                return js_name;
            }

            public void setJs_name(String js_name) {
                this.js_name = js_name;
            }

            public String getJs_code() {
                return js_code;
            }

            public void setJs_code(String js_code) {
                this.js_code = js_code;
            }

            public String getJs_remark() {
                return js_remark;
            }

            public void setJs_remark(String js_remark) {
                this.js_remark = js_remark;
            }

            public String getJs_logo() {
                return js_logo;
            }

            public void setJs_logo(String js_logo) {
                this.js_logo = js_logo;
            }

            public String getJs_status() {
                return js_status;
            }

            public void setJs_status(String js_status) {
                this.js_status = js_status;
            }

            public String getJs_create_time() {
                return js_create_time;
            }

            public void setJs_create_time(String js_create_time) {
                this.js_create_time = js_create_time;
            }

            public Object getJst_id() {
                return jst_id;
            }

            public void setJst_id(Object jst_id) {
                this.jst_id = jst_id;
            }

            public Object getSourceType() {
                return sourceType;
            }

            public void setSourceType(Object sourceType) {
                this.sourceType = sourceType;
            }

            public String getIsSubscribe() {
                return isSubscribe;
            }

            public void setIsSubscribe(String isSubscribe) {
                this.isSubscribe = isSubscribe;
            }
        }
    }
}
