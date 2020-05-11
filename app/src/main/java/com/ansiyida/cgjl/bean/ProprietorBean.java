package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/7.
 */
public class ProprietorBean {



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

        /*  "code": "泗县泗涂产业园",
      "createTime": 1544998582000,
      "dataTime": 1544998524000,
      "id": 728912,
      "isDelete": false,
      "name": "泗县泗涂产业园",
      "status": true,
      "todayNum": 1,
      "updateNum": 1*/
        private String code;
        private String createTime;
        private String id;
        private String isDelete;
        private String dataTime;
        private String name;
        private String todayNum;
        private String updateNum;
        private String status;


        public String getcode() {
            return code;
        }

        public void setcode(String code) {
            this.code = code;
        }

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getdataTime() {
            return dataTime;
        }

        public void setdataTime(String dataTime) {
            this.dataTime = dataTime;
        }

        public String getname() {
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }

        public String gettodayNum() {
            return todayNum;
        }

        public void settodayNum(String todayNum) {
            this.todayNum = todayNum;
        }
        public String getupdateNum() {
            return updateNum;
        }

        public void setupdateNum(String updateNum) {
            this.updateNum = updateNum;
        }

        public String getstatus() {
            return status;
        }

        public void setstatus(String status) {
            this.status = status;
        }

        public String getisDelete() {
            return isDelete;
        }

        public void setisDelete(String isDelete) {
            this.isDelete = isDelete;
        }


    }
}
