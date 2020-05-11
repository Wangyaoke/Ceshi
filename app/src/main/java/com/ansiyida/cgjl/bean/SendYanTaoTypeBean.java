package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/26.
 */
public class SendYanTaoTypeBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"navList":[{"jpt_time":"2017-11-17 09:49:30","jpt_id":1,"isdel":"Y","jpt_status":"Y","jpt_sort":1,"jpt_name":"焦点"},{"jpt_time":"2017-11-17 09:49:30","jpt_id":2,"isdel":"Y","jpt_status":"Y","jpt_sort":2,"jpt_name":"精选"},{"jpt_time":"2017-11-17 09:49:30","jpt_id":3,"isdel":"Y","jpt_status":"Y","jpt_sort":3,"jpt_name":"科技战略"},{"jpt_time":"2017-11-17 09:49:30","jpt_id":8,"isdel":"Y","jpt_status":"Y","jpt_sort":4,"jpt_name":"国防科技"},{"jpt_time":"2017-11-17 09:49:30","jpt_id":9,"isdel":"Y","jpt_status":"Y","jpt_sort":5,"jpt_name":"基础科学"},{"jpt_time":"2017-11-17 09:49:30","jpt_id":10,"isdel":"Y","jpt_status":"Y","jpt_sort":9,"jpt_name":"人文社科"},{"jpt_time":"2017-11-17 09:49:30","jpt_id":11,"isdel":"Y","jpt_status":"Y","jpt_sort":7,"jpt_name":"航空航天"},{"jpt_time":"2017-11-17 09:49:30","jpt_id":12,"isdel":"Y","jpt_status":"Y","jpt_sort":8,"jpt_name":"生物医学"},{"jpt_time":"2018-02-13 13:38:39","jpt_id":19,"isdel":"Y","jpt_status":"Y","jpt_sort":10,"jpt_name":"能源材料"},{"jpt_time":"2018-02-13 13:38:51","jpt_id":20,"isdel":"Y","jpt_status":"Y","jpt_sort":11,"jpt_name":"信息技术"},{"jpt_time":"2018-02-13 13:40:04","jpt_id":21,"isdel":"Y","jpt_status":"Y","jpt_sort":12,"jpt_name":"人工智能"},{"jpt_time":"2018-02-13 13:40:15","jpt_id":22,"isdel":"Y","jpt_status":"Y","jpt_sort":13,"jpt_name":"先进制造"},{"jpt_time":"2018-02-13 13:40:25","jpt_id":23,"isdel":"Y","jpt_status":"Y","jpt_sort":14,"jpt_name":"船舶海洋"},{"jpt_time":"2018-02-13 14:31:19","jpt_id":24,"isdel":"Y","jpt_status":"Y","jpt_sort":20,"jpt_name":"其它"}]}
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
        private List<NavListBean> navList;

        public List<NavListBean> getNavList() {
            return navList;
        }

        public void setNavList(List<NavListBean> navList) {
            this.navList = navList;
        }

        public static class NavListBean {
            /**
             * jpt_time : 2017-11-17 09:49:30
             * jpt_id : 1
             * isdel : Y
             * jpt_status : Y
             * jpt_sort : 1
             * jpt_name : 焦点
             */

            private String jpt_time;
            private int jpt_id;
            private String isdel;
            private String jpt_status;
            private int jpt_sort;
            private String jpt_name;

            public String getJpt_time() {
                return jpt_time;
            }

            public void setJpt_time(String jpt_time) {
                this.jpt_time = jpt_time;
            }

            public int getJpt_id() {
                return jpt_id;
            }

            public void setJpt_id(int jpt_id) {
                this.jpt_id = jpt_id;
            }

            public String getIsdel() {
                return isdel;
            }

            public void setIsdel(String isdel) {
                this.isdel = isdel;
            }

            public String getJpt_status() {
                return jpt_status;
            }

            public void setJpt_status(String jpt_status) {
                this.jpt_status = jpt_status;
            }

            public int getJpt_sort() {
                return jpt_sort;
            }

            public void setJpt_sort(int jpt_sort) {
                this.jpt_sort = jpt_sort;
            }

            public String getJpt_name() {
                return jpt_name;
            }

            public void setJpt_name(String jpt_name) {
                this.jpt_name = jpt_name;
            }
        }
    }
}
