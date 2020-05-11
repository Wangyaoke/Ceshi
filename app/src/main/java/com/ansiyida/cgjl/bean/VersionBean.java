package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/5/16.
 */
public class VersionBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"jvr_id":3,"version":"v1.0.1","create_time":"2018-05-16 13:16:43","weight":2,"status":"Y"}
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
         * jvr_id : 3
         * version : v1.0.1
         * create_time : 2018-05-16 13:16:43
         * weight : 2
         * status : Y
         */

        private int jvr_id;
        private String version;
        private String create_time;
        private int weight;
        private String status;

        public int getJvr_id() {
            return jvr_id;
        }

        public void setJvr_id(int jvr_id) {
            this.jvr_id = jvr_id;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
