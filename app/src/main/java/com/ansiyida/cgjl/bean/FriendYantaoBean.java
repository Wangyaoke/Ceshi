package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/16.
 */
public class FriendYantaoBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"haveMore":0,"pager":{"total":1,"pageSize":10,"pageCount":1,"page":1,"begin":0,"haveMore":0},"id":1,"page":1,"list":[{"art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cb2f706624c24f59a5ba2fb611b17903.jpg?Expires=4674540715&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=bJGv%2FogOQYdGcPUG4V0K%2BdJiOJE%3D","art_type":"D","art_type_id":3,"art_des":"1111","art_answertnum":2,"jmi_id":1,"art_id":16,"art_time":"2018-03-13 19:46:27","jmi_username":"明日","art_thumbusnum":2,"jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_title":"11111","art_browsenum":8}]}
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
         * pager : {"total":1,"pageSize":10,"pageCount":1,"page":1,"begin":0,"haveMore":0}
         * id : 1
         * page : 1
         * list : [{"art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cb2f706624c24f59a5ba2fb611b17903.jpg?Expires=4674540715&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=bJGv%2FogOQYdGcPUG4V0K%2BdJiOJE%3D","art_type":"D","art_type_id":3,"art_des":"1111","art_answertnum":2,"jmi_id":1,"art_id":16,"art_time":"2018-03-13 19:46:27","jmi_username":"明日","art_thumbusnum":2,"jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_title":"11111","art_browsenum":8}]
         */

        private int haveMore;
        private PagerBean pager;
        private int id;
        private int page;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
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
             * pageSize : 10
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
             * art_img : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cb2f706624c24f59a5ba2fb611b17903.jpg?Expires=4674540715&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=bJGv%2FogOQYdGcPUG4V0K%2BdJiOJE%3D
             * art_type : D
             * art_type_id : 3
             * art_des : 1111
             * art_answertnum : 2
             * jmi_id : 1
             * art_id : 16
             * art_time : 2018-03-13 19:46:27
             * jmi_username : 明日
             * art_thumbusnum : 2
             * jmi_img : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=
             * art_title : 11111
             * art_browsenum : 8
             */

            private String art_img;
            private String art_type;
            private int art_type_id;
            private String art_des;
            private int art_answertnum;
            private int jmi_id;
            private int art_id;
            private String art_time;
            private String jmi_username;
            private int art_thumbusnum;
            private String jmi_img;
            private String art_title;
            private int art_browsenum;

            public String getArt_img() {
                return art_img;
            }

            public void setArt_img(String art_img) {
                this.art_img = art_img;
            }

            public String getArt_type() {
                return art_type;
            }

            public void setArt_type(String art_type) {
                this.art_type = art_type;
            }

            public int getArt_type_id() {
                return art_type_id;
            }

            public void setArt_type_id(int art_type_id) {
                this.art_type_id = art_type_id;
            }

            public String getArt_des() {
                return art_des;
            }

            public void setArt_des(String art_des) {
                this.art_des = art_des;
            }

            public int getArt_answertnum() {
                return art_answertnum;
            }

            public void setArt_answertnum(int art_answertnum) {
                this.art_answertnum = art_answertnum;
            }

            public int getJmi_id() {
                return jmi_id;
            }

            public void setJmi_id(int jmi_id) {
                this.jmi_id = jmi_id;
            }

            public int getArt_id() {
                return art_id;
            }

            public void setArt_id(int art_id) {
                this.art_id = art_id;
            }

            public String getArt_time() {
                return art_time;
            }

            public void setArt_time(String art_time) {
                this.art_time = art_time;
            }

            public String getJmi_username() {
                return jmi_username;
            }

            public void setJmi_username(String jmi_username) {
                this.jmi_username = jmi_username;
            }

            public int getArt_thumbusnum() {
                return art_thumbusnum;
            }

            public void setArt_thumbusnum(int art_thumbusnum) {
                this.art_thumbusnum = art_thumbusnum;
            }

            public String getJmi_img() {
                return jmi_img;
            }

            public void setJmi_img(String jmi_img) {
                this.jmi_img = jmi_img;
            }

            public String getArt_title() {
                return art_title;
            }

            public void setArt_title(String art_title) {
                this.art_title = art_title;
            }

            public int getArt_browsenum() {
                return art_browsenum;
            }

            public void setArt_browsenum(int art_browsenum) {
                this.art_browsenum = art_browsenum;
            }
        }
    }
}
