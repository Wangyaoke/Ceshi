package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/6.
 */
public class CollegeListBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"haveMore":0,"pager":{"total":1,"pageSize":20,"pageCount":1,"page":1,"begin":0,"haveMore":0},"id":"1","list":[{"jca_id":1023,"jc_id":410,"jc_datatime":"2018-03-02 13:20:09","jca_time":"2018-02-28 09:47:53","jca_title":"Mercury Systems Receives $12M RF Microelectronics Order For Airborne Electronic Warfare Application","jca_img":"","jca_type":"P","jca_des":"美国水星系统公司宣布收到一家领先的防务主承包商订购SWaP优化射频（RF）模块的1200万美元订单。"}]}
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
         * haveMore : 0
         * pager : {"total":1,"pageSize":20,"pageCount":1,"page":1,"begin":0,"haveMore":0}
         * id : 1
         * list : [{"jca_id":1023,"jc_id":410,"jc_datatime":"2018-03-02 13:20:09","jca_time":"2018-02-28 09:47:53","jca_title":"Mercury Systems Receives $12M RF Microelectronics Order For Airborne Electronic Warfare Application","jca_img":"","jca_type":"P","jca_des":"美国水星系统公司宣布收到一家领先的防务主承包商订购SWaP优化射频（RF）模块的1200万美元订单。"}]
         */

        private int haveMore;
        private PagerBean pager;
        private String id;
        private List<ListBean> list;

        public int getHaveMore() {
            return haveMore;
        }

        public void setHaveMore(int haveMore) {
            this.haveMore = haveMore;
        }

        public PagerBean getPager() {
            return pager;
        }

        public void setPager(PagerBean pager) {
            this.pager = pager;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagerBean {
            /**
             * total : 1
             * pageSize : 20
             * pageCount : 1
             * page : 1
             * begin : 0
             * haveMore : 0
             */

            private int total;
            private int pageSize;
            private int pageCount;
            private int page;
            private int begin;
            private int haveMore;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getBegin() {
                return begin;
            }

            public void setBegin(int begin) {
                this.begin = begin;
            }

            public int getHaveMore() {
                return haveMore;
            }

            public void setHaveMore(int haveMore) {
                this.haveMore = haveMore;
            }
        }

        public static class ListBean {
            /**
             * jca_id : 1023
             * jc_id : 410
             * video_time：03:10
             * jc_datatime : 2018-03-02 13:20:09
             * jca_time : 2018-02-28 09:47:53
             * jca_title : Mercury Systems Receives $12M RF Microelectronics Order For Airborne Electronic Warfare Application
             * jca_img :
             * jca_type : P
             * jca_des : 美国水星系统公司宣布收到一家领先的防务主承包商订购SWaP优化射频（RF）模块的1200万美元订单。
             */

            private int jca_id;
            private int jc_id;
            private String jc_datatime;
            private String jca_time;
            private String jca_title;
            private String jca_img;
            private String jca_type;
            private String jca_des;
            private String video_time;

            public String getVideo_time() {
                return video_time;
            }

            public void setVideo_time(String video_time) {
                this.video_time = video_time;
            }

            public int getJca_id() {
                return jca_id;
            }

            public void setJca_id(int jca_id) {
                this.jca_id = jca_id;
            }

            public int getJc_id() {
                return jc_id;
            }

            public void setJc_id(int jc_id) {
                this.jc_id = jc_id;
            }

            public String getJc_datatime() {
                return jc_datatime;
            }

            public void setJc_datatime(String jc_datatime) {
                this.jc_datatime = jc_datatime;
            }

            public String getJca_time() {
                return jca_time;
            }

            public void setJca_time(String jca_time) {
                this.jca_time = jca_time;
            }

            public String getJca_title() {
                return jca_title;
            }

            public void setJca_title(String jca_title) {
                this.jca_title = jca_title;
            }

            public String getJca_img() {
                return jca_img;
            }

            public void setJca_img(String jca_img) {
                this.jca_img = jca_img;
            }

            public String getJca_type() {
                return jca_type;
            }

            public void setJca_type(String jca_type) {
                this.jca_type = jca_type;
            }

            public String getJca_des() {
                return jca_des;
            }

            public void setJca_des(String jca_des) {
                this.jca_des = jca_des;
            }
        }
    }
}
