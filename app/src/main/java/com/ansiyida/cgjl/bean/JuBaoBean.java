package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/5.
 */
public class JuBaoBean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"jl_status":"Y","jl_time":"2018-02-13 16:55:16","jl_sort":1,"isdel":"Y","jl_id":239,"jl_type":24,"jl_name":"色情淫秽"},{"jl_status":"Y","jl_time":"2018-02-13 16:55:32","jl_sort":1,"isdel":"Y","jl_id":240,"jl_type":24,"jl_name":"地域攻击"},{"jl_status":"Y","jl_time":"2018-02-13 16:55:43","jl_sort":1,"isdel":"Y","jl_id":241,"jl_type":24,"jl_name":"营销诈骗"},{"jl_status":"Y","jl_time":"2018-02-13 16:56:47","jl_sort":1,"isdel":"Y","jl_id":242,"jl_type":24,"jl_name":"政治反动"}]
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
         * jl_status : Y
         * jl_time : 2018-02-13 16:55:16
         * jl_sort : 1
         * isdel : Y
         * jl_id : 239
         * jl_type : 24
         * jl_name : 色情淫秽
         */

        private String jl_status;
        private String jl_time;
        private int jl_sort;
        private String isdel;
        private int jl_id;
        private int jl_type;
        private String jl_name;

        public String getJl_status() {
            return jl_status;
        }

        public void setJl_status(String jl_status) {
            this.jl_status = jl_status;
        }

        public String getJl_time() {
            return jl_time;
        }

        public void setJl_time(String jl_time) {
            this.jl_time = jl_time;
        }

        public int getJl_sort() {
            return jl_sort;
        }

        public void setJl_sort(int jl_sort) {
            this.jl_sort = jl_sort;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public int getJl_id() {
            return jl_id;
        }

        public void setJl_id(int jl_id) {
            this.jl_id = jl_id;
        }

        public int getJl_type() {
            return jl_type;
        }

        public void setJl_type(int jl_type) {
            this.jl_type = jl_type;
        }

        public String getJl_name() {
            return jl_name;
        }

        public void setJl_name(String jl_name) {
            this.jl_name = jl_name;
        }
    }
}
