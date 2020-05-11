package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/8.
 */
public class FansCareBean {

    /**
     * sign : G
     * haveMore : 0
     * pager : {"total":8,"pageSize":10,"pageCount":1,"page":1,"begin":0,"haveMore":0}
     * list : [{"jmi_id":4,"jmi_username":"百里屠苏","jmi_des":"","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/4f3c325a75d946c89aca1bde58eb3a5f.png?Expires=1522055204&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=GhDBWRCxlAv0q%2Bp3OSm2bNeFdIY%3D","jpa_datatime":"2018-03-06 17:49:20","jpa_type":"P","jpa_id":16,"jpa_ids":""},{"jmi_id":3,"jmi_username":"mrqb_286A99W0","jmi_des":"","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b9417172869a4e8e9ad42ee011690b5e.png?Expires=1521271389&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=kvXewa5gzzr%2F6HM7nTEKegZUsn0%3D","jpa_datatime":"2018-03-06 17:16:33","jpa_type":"P","jpa_id":13,"jpa_ids":""},{"jmi_id":0,"jmi_username":"","jmi_des":"","jmi_img":"","jpa_datatime":"2018-03-06 17:12:01","jpa_type":"","jpa_id":12,"jpa_ids":""}]
     */

    private String sign;
    private int haveMore;
    private PagerBean pager;
    private List<ListBean> list;

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

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

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class PagerBean {
        /**
         * total : 8
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
         * jmi_id : 4
         * jmi_username : 百里屠苏
         * jmi_des :
         * jmi_img : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/4f3c325a75d946c89aca1bde58eb3a5f.png?Expires=1522055204&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=GhDBWRCxlAv0q%2Bp3OSm2bNeFdIY%3D
         * jpa_datatime : 2018-03-06 17:49:20
         * jpa_type : P
         * jpa_id : 16
         * jpa_ids :
         */

        private int jmi_id;
        private String jmi_username;
        private String jmi_des;
        private String jmi_img;
        private String jpa_datatime;
        private String jpa_type;
        private int jpa_id;
        private String jpa_ids;
        private String is_g_z;
        private String is_f_s;

        public String getIs_g_z() {
            return is_g_z;
        }

        public void setIs_g_z(String is_g_z) {
            this.is_g_z = is_g_z;
        }

        public String getIs_f_s() {
            return is_f_s;
        }

        public void setIs_f_s(String is_f_s) {
            this.is_f_s = is_f_s;
        }

        public int getJmi_id() {
            return jmi_id;
        }

        public void setJmi_id(int jmi_id) {
            this.jmi_id = jmi_id;
        }

        public String getJmi_username() {
            return jmi_username;
        }

        public void setJmi_username(String jmi_username) {
            this.jmi_username = jmi_username;
        }

        public String getJmi_des() {
            return jmi_des;
        }

        public void setJmi_des(String jmi_des) {
            this.jmi_des = jmi_des;
        }

        public String getJmi_img() {
            return jmi_img;
        }

        public void setJmi_img(String jmi_img) {
            this.jmi_img = jmi_img;
        }

        public String getJpa_datatime() {
            return jpa_datatime;
        }

        public void setJpa_datatime(String jpa_datatime) {
            this.jpa_datatime = jpa_datatime;
        }

        public String getJpa_type() {
            return jpa_type;
        }

        public void setJpa_type(String jpa_type) {
            this.jpa_type = jpa_type;
        }

        public int getJpa_id() {
            return jpa_id;
        }

        public void setJpa_id(int jpa_id) {
            this.jpa_id = jpa_id;
        }

        public String getJpa_ids() {
            return jpa_ids;
        }

        public void setJpa_ids(String jpa_ids) {
            this.jpa_ids = jpa_ids;
        }
    }
}
