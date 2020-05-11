package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/8.
 */
public class HotSearchBean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"jhwr_status":"Y","jhwr_datetime":"2018-03-08 09:50:07","jhwr_sort":0,"jhwr_num":14,"jhwr_id":508,"jhwr_name":"研讨"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-06 09:33:34","jhwr_sort":0,"jhwr_num":14,"jhwr_id":483,"jhwr_name":"e"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-07 14:25:01","jhwr_sort":0,"jhwr_num":13,"jhwr_id":498,"jhwr_name":"研究"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-05 15:36:18","jhwr_sort":0,"jhwr_num":12,"jhwr_id":478,"jhwr_name":"中国"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-08 10:04:14","jhwr_sort":0,"jhwr_num":8,"jhwr_id":509,"jhwr_name":"你好"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-08 15:04:10","jhwr_sort":0,"jhwr_num":6,"jhwr_id":526,"jhwr_name":"美团外卖故障"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-07 11:44:59","jhwr_sort":0,"jhwr_num":5,"jhwr_id":497,"jhwr_name":"大韩航空购买庞巴迪环保型CS300飞机"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-08 12:41:33","jhwr_sort":0,"jhwr_num":5,"jhwr_id":520,"jhwr_name":"高速上现骏马狂奔"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-08 12:38:47","jhwr_sort":0,"jhwr_num":4,"jhwr_id":516,"jhwr_name":"回顾津巴布与上帝的约会"},{"jhwr_status":"Y","jhwr_datetime":"2018-03-08 12:29:15","jhwr_sort":0,"jhwr_num":3,"jhwr_id":515,"jhwr_name":"美国"}]
     */

    private String status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String createTime;
        private String id;
        private String keyword;
        private String sort;

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String getkeyword() {
            return keyword;
        }

        public void setkeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getsort() {
            return sort;
        }

        public void setsort(String sort) {
            this.sort = sort;
        }
        /**
         * jhwr_status : Y
         * jhwr_datetime : 2018-03-08 09:50:07
         * jhwr_sort : 0
         * jhwr_num : 14
         * jhwr_id : 508
         * jhwr_name : 研讨

"endRow": 4,
        "hasNextPage": false,
        "hasPreviousPage": false,
        "isFirstPage": true,
        "isLastPage": true,

         "navigateFirstPage": 1,
         "navigateLastPage": 1,
         "navigatePages": 8,
         "navigatepageNums": [
         1
         ],
         "nextPage": 0,
         "pageNum": 1,
         "pageSize": 20,
         "pages": 1,
         "prePage": 0,
         "size": 4,
         "startRow": 1,
         "total": 4

        private String endRow;
        private String hasNextPage;
        private String hasPreviousPage;
        private String isFirstPage;
        private String isLastPage;
        private List<listbean> list;
        private String navigateFirstPage;
        private String navigateLastPage;
        private String navigatePages;
        private List<String> navigatepageNums;
        private String nextPage;
        private String pageNum;
        private String pageSize;
        private String pages;
        private String prePage;
        private String size;
        private String startRow;
        private String total;
        public List<listbean> getlist() {
            return list;
        }

        public void setlist(List<listbean> list) {
            this.list = list;
        }
        public static class listbean {
            private String createTime;
            private String id;
            private String keyword;
            private String sort;

            public String getcreateTime() {
                return createTime;
            }

            public void setcreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getid() {
                return id;
            }

            public void setid(String id) {
                this.id = id;
            }

            public String getkeyword() {
                return keyword;
            }

            public void setkeyword(String keyword) {
                this.keyword = keyword;
            }

            public String getsort() {
                return sort;
            }

            public void setsort(String sort) {
                this.sort = sort;
            }
        }*/

    }
}
