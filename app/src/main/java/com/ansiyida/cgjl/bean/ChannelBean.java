package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/2/26.
 */
public class ChannelBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"otherChannel":[{"jat_id":110,"jat_pid":92,"jat_name":"人工智能","jat_sort":97,"jat_status":"Y"},{"jat_id":114,"jat_pid":92,"jat_name":"先进制造","jat_sort":93,"jat_status":"Y"},{"jat_id":136,"jat_pid":88,"jat_name":"军备控制","jat_sort":94,"jat_status":"Y"},{"jat_id":117,"jat_pid":93,"jat_name":"农业","jat_sort":99,"jat_status":"Y"},{"jat_id":104,"jat_pid":88,"jat_name":"反恐安全","jat_sort":97,"jat_status":"Y"},{"jat_id":138,"jat_pid":88,"jat_name":"国防科技发展战略","jat_sort":92,"jat_status":"Y"},{"jat_id":102,"jat_pid":88,"jat_name":"战略规划","jat_sort":99,"jat_status":"Y"},{"jat_id":115,"jat_pid":92,"jat_name":"探索","jat_sort":93,"jat_status":"Y"},{"jat_id":120,"jat_pid":93,"jat_name":"教育","jat_sort":97,"jat_status":"Y"},{"jat_id":123,"jat_pid":122,"jat_name":"新闻","jat_sort":97,"jat_status":"Y"},{"jat_id":113,"jat_pid":92,"jat_name":"海洋","jat_sort":94,"jat_status":"Y"},{"jat_id":111,"jat_pid":92,"jat_name":"生物医学","jat_sort":96,"jat_status":"Y"},{"jat_id":112,"jat_pid":92,"jat_name":"电子信息","jat_sort":95,"jat_status":"Y"},{"jat_id":116,"jat_pid":93,"jat_name":"社会","jat_sort":100,"jat_status":"Y"},{"jat_id":105,"jat_pid":88,"jat_name":"管理与政策","jat_sort":96,"jat_status":"Y"},{"jat_id":118,"jat_pid":93,"jat_name":"经济","jat_sort":98,"jat_status":"Y"},{"jat_id":103,"jat_pid":88,"jat_name":"综合保障","jat_sort":98,"jat_status":"Y"},{"jat_id":101,"jat_pid":88,"jat_name":"防务策略","jat_sort":100,"jat_status":"Y"}],"myChannel":[{"jat_id":109,"jat_pid":92,"jat_name":"能源材料","jat_sort":98,"jat_status":"Y"},{"jat_id":108,"jat_pid":92,"jat_name":"航空航天","jat_sort":99,"jat_status":"Y"}]}
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
        private List<OtherChannelBean> otherChannel;
        private List<MyChannelBean> myChannel;

        public List<OtherChannelBean> getOtherChannel() {
            return otherChannel;
        }

        public void setOtherChannel(List<OtherChannelBean> otherChannel) {
            this.otherChannel = otherChannel;
        }

        public List<MyChannelBean> getMyChannel() {
            return myChannel;
        }

        public void setMyChannel(List<MyChannelBean> myChannel) {
            this.myChannel = myChannel;
        }

        public static class OtherChannelBean {
            /**
             * jat_id : 110
             * jat_pid : 92
             * jat_name : 人工智能
             * jat_sort : 97
             * jat_status : Y
             */

            private int jat_id;
            private int jat_pid;
            private String jat_name;
            private int jat_sort;
            private String jat_status;

            public int getJat_id() {
                return jat_id;
            }

            public void setJat_id(int jat_id) {
                this.jat_id = jat_id;
            }

            public int getJat_pid() {
                return jat_pid;
            }

            public void setJat_pid(int jat_pid) {
                this.jat_pid = jat_pid;
            }

            public String getJat_name() {
                return jat_name;
            }

            public void setJat_name(String jat_name) {
                this.jat_name = jat_name;
            }

            public int getJat_sort() {
                return jat_sort;
            }

            public void setJat_sort(int jat_sort) {
                this.jat_sort = jat_sort;
            }

            public String getJat_status() {
                return jat_status;
            }

            public void setJat_status(String jat_status) {
                this.jat_status = jat_status;
            }
        }

        public static class MyChannelBean {
            /**
             * jat_id : 109
             * jat_pid : 92
             * jat_name : 能源材料
             * jat_sort : 98
             * jat_status : Y
             */

            private int jat_id;
            private int jat_pid;
            private String jat_name;
            private int jat_sort;
            private String jat_status;

            public int getJat_id() {
                return jat_id;
            }

            public void setJat_id(int jat_id) {
                this.jat_id = jat_id;
            }

            public int getJat_pid() {
                return jat_pid;
            }

            public void setJat_pid(int jat_pid) {
                this.jat_pid = jat_pid;
            }

            public String getJat_name() {
                return jat_name;
            }

            public void setJat_name(String jat_name) {
                this.jat_name = jat_name;
            }

            public int getJat_sort() {
                return jat_sort;
            }

            public void setJat_sort(int jat_sort) {
                this.jat_sort = jat_sort;
            }

            public String getJat_status() {
                return jat_status;
            }

            public void setJat_status(String jat_status) {
                this.jat_status = jat_status;
            }
        }
    }
}
