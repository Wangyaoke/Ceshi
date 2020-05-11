package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/8.
 */
public class SearchHistoryBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"pager":{"total":56,"pageSize":10,"pageCount":6,"page":1,"begin":0,"haveMore":1},"searchRecordList":[{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:42:29","jhwr_sort":0,"jhw_type":"N","jhw_id":2065,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:42:29","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":1,"jhwr_id":522,"jhwr_name":"空姐吃多份飞机餐"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:42:10","jhwr_sort":0,"jhw_type":"N","jhw_id":2064,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:42:10","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":1,"jhwr_id":521,"jhwr_name":"高速"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:41:33","jhwr_sort":0,"jhw_type":"N","jhw_id":2063,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:41:33","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":1,"jhwr_id":520,"jhwr_name":"高速上现骏马狂奔"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:41:29","jhwr_sort":0,"jhw_type":"N","jhw_id":2062,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:41:29","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":1,"jhwr_id":519,"jhwr_name":"大一新生斗殴死亡"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:41:18","jhwr_sort":0,"jhw_type":"N","jhw_id":2061,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:41:18","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":1,"jhwr_id":518,"jhwr_name":"温碧泉曝不合格"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:41:13","jhwr_sort":0,"jhw_type":"N","jhw_id":2060,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:41:13","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":1,"jhwr_id":517,"jhwr_name":"回顾津巴布与上帝的"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:38:47","jhwr_sort":0,"jhw_type":"N","jhw_id":2059,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:41:07","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":3,"jhwr_id":516,"jhwr_name":"回顾津巴布与上帝的约会"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:38:47","jhwr_sort":0,"jhw_type":"N","jhw_id":2058,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:40:51","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":3,"jhwr_id":516,"jhwr_name":"回顾津巴布与上帝的约会"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:38:47","jhwr_sort":0,"jhw_type":"N","jhw_id":2057,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:38:47","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":3,"jhwr_id":516,"jhwr_name":"回顾津巴布与上帝的约会"},{"jhwr_status":"Y","login_status":"Y","jhwr_datetime":"2018-03-08 12:29:15","jhwr_sort":0,"jhw_type":"N","jhw_id":2056,"jhw_sort":0,"jhw_datetime":"2018-03-08 12:34:15","user_id":1,"jhw_ip":"0.0.0.0","jhwr_num":3,"jhwr_id":515,"jhwr_name":"美国"}]}

     {
     "data": {
     "endRow": 0,
     "hasNextPage": false,
     "hasPreviousPage": false,
     "isFirstPage": true,
     "isLastPage": true,
     "list": [],
     "navigateFirstPage": 0,
     "navigateLastPage": 0,
     "navigatePages": 8,
     "navigatepageNums": [],
     "nextPage": 0,
     "pageNum": 1,
     "pageSize": 20,
     "pages": 0,
     "prePage": 0,
     "size": 0,
     "startRow": 0,
     "total": 0
     },
     "message": "成功",
     "status": 200
     }
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
            private String userId;

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

            public String getuserId() {
                return userId;
            }

            public void setuserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
