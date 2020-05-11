package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/26.
 */
public class InterestBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"other":[{"jl_status":"Y","jl_time":"2018-02-07 18:03:52","jl_sort":1,"isdel":"Y","jl_id":147,"jl_type":5,"jl_name":"能源材料"},{"jl_status":"Y","jl_time":"2018-02-07 18:04:33","jl_sort":1,"isdel":"Y","jl_id":149,"jl_type":5,"jl_name":"生物医学"},{"jl_status":"Y","jl_time":"2018-02-07 18:05:00","jl_sort":1,"isdel":"Y","jl_id":150,"jl_type":5,"jl_name":"电子信息"},{"jl_status":"Y","jl_time":"2018-02-07 18:05:43","jl_sort":1,"isdel":"Y","jl_id":152,"jl_type":5,"jl_name":"先进制造"},{"jl_status":"Y","jl_time":"2018-02-07 18:05:55","jl_sort":1,"isdel":"Y","jl_id":153,"jl_type":5,"jl_name":"探索"},{"jl_status":"Y","jl_time":"2018-02-07 18:06:22","jl_sort":1,"isdel":"Y","jl_id":154,"jl_type":5,"jl_name":"防务策略"},{"jl_status":"Y","jl_time":"2018-02-07 18:06:37","jl_sort":1,"isdel":"Y","jl_id":155,"jl_type":5,"jl_name":"战略规划"},{"jl_status":"Y","jl_time":"2018-02-07 18:06:51","jl_sort":1,"isdel":"Y","jl_id":156,"jl_type":5,"jl_name":"综合保障"},{"jl_status":"Y","jl_time":"2018-02-07 18:07:16","jl_sort":1,"isdel":"Y","jl_id":158,"jl_type":5,"jl_name":"反恐安全"},{"jl_status":"Y","jl_time":"2018-02-07 18:07:49","jl_sort":1,"isdel":"Y","jl_id":159,"jl_type":5,"jl_name":"管理与政策"},{"jl_status":"Y","jl_time":"2018-02-07 18:08:05","jl_sort":1,"isdel":"Y","jl_id":160,"jl_type":5,"jl_name":"社会"},{"jl_status":"Y","jl_time":"2018-02-07 18:08:39","jl_sort":1,"isdel":"Y","jl_id":161,"jl_type":5,"jl_name":"农业"},{"jl_status":"Y","jl_time":"2018-02-07 18:08:50","jl_sort":1,"isdel":"Y","jl_id":162,"jl_type":5,"jl_name":"经济"},{"jl_status":"Y","jl_time":"2018-02-07 18:09:00","jl_sort":1,"isdel":"Y","jl_id":163,"jl_type":5,"jl_name":"教育"}],"my":[{"jl_status":"Y","jl_time":"2018-02-07 18:03:27","jl_sort":1,"isdel":"Y","jl_id":146,"jl_type":5,"jl_name":"航空航天"},{"jl_status":"Y","jl_time":"2018-02-07 18:04:05","jl_sort":1,"isdel":"Y","jl_id":148,"jl_type":5,"jl_name":"人工智能"},{"jl_status":"","jl_time":"","jl_sort":"","isdel":"","jl_id":0,"jl_type":"","jl_name":"王治国"},{"jl_status":"","jl_time":"","jl_sort":"","isdel":"","jl_id":0,"jl_type":"","jl_name":"240"}]}
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
        private List<OtherBean> other;
        private List<MyBean> my;

        public List<OtherBean> getOther() {
            return other;
        }

        public void setOther(List<OtherBean> other) {
            this.other = other;
        }

        public List<MyBean> getMy() {
            return my;
        }

        public void setMy(List<MyBean> my) {
            this.my = my;
        }

        public static class OtherBean {
            /**
             * jl_status : Y
             * jl_time : 2018-02-07 18:03:52
             * jl_sort : 1
             * isdel : Y
             * jl_id : 147
             * jl_type : 5
             * jl_name : 能源材料
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

        public static class MyBean {
            /**
             * jl_status : Y
             * jl_time : 2018-02-07 18:03:27
             * jl_sort : 1
             * isdel : Y
             * jl_id : 146
             * jl_type : 5
             * jl_name : 航空航天
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
}
