package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/6/5.
 */
public class DYTypeBean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"jst_id":1,"jst_name":"生物医学","jst_sort":"1","jst_status":"Y","jst_create_time":"2018-05-30 09:33:59"},{"jst_id":2,"jst_name":"基础科学","jst_sort":"2","jst_status":"Y","jst_create_time":"2018-05-30 09:34:17"},{"jst_id":3,"jst_name":"AI","jst_sort":"3","jst_status":"Y","jst_create_time":"2018-05-30 09:34:27"},{"jst_id":4,"jst_name":"物理学","jst_sort":"1","jst_status":"Y","jst_create_time":"2018-05-30 10:17:50"}]
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
        /**
         * jst_id : 1
         * jst_name : 生物医学
         * jst_sort : 1
         * jst_status : Y
         * jst_create_time : 2018-05-30 09:33:59
         */

        private int jst_id;
        private String jst_name;
        private String jst_sort;
        private String jst_status;
        private String jst_create_time;

        public int getJst_id() {
            return jst_id;
        }

        public void setJst_id(int jst_id) {
            this.jst_id = jst_id;
        }

        public String getJst_name() {
            return jst_name;
        }

        public void setJst_name(String jst_name) {
            this.jst_name = jst_name;
        }

        public String getJst_sort() {
            return jst_sort;
        }

        public void setJst_sort(String jst_sort) {
            this.jst_sort = jst_sort;
        }

        public String getJst_status() {
            return jst_status;
        }

        public void setJst_status(String jst_status) {
            this.jst_status = jst_status;
        }

        public String getJst_create_time() {
            return jst_create_time;
        }

        public void setJst_create_time(String jst_create_time) {
            this.jst_create_time = jst_create_time;
        }
    }
}
