package com.ansiyida.cgjl.bean;

import java.util.List;

public class purchaseDemandBean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fdf3b970a1a845e0a5e39425fe256ac3.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=8Kd%2Fi57%2Ff%2BwCGOAVVKNnUlwHxxQ%3D","jai_title":"社会","jai_tpo":null,"jai_id":85,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:51:43"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-31 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fd250677148f4d19b7fc1aadf9647c6b.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=QR6wqZptYwOSOC6ZUV29P7lNSFA%3D","jai_title":"身边","jai_tpo":null,"jai_id":86,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:16"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/e06798e6728640a2abde3da0a56cd744.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=gqKDcco%2BituQEaS2tP5Zprs3ATI%3D","jai_title":"前沿","jai_tpo":null,"jai_id":87,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:45"}]
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
        /*
         "createTime": 1548335710000,//
      "dataId": 635648,
      "endTime": 1547481600000,//
    //  "function": "作为平台，能在升空大于6000米空域进行不小于1小时作业；具备自动起飞、程序飞行和自动降落功能（一键起降）；具备飞控自主改造能力；无人机采用模块化设计，能够实现快速拆装和转运，箱式存储，装箱体积不大于2×2×1米（长宽高），单装箱重量不超过60千克；能在复杂气象环境下遂行任务，6级风条件下能安全起降；具备短距滑跑起降或垂直起降能力。",
      "id": 40907,//
      "isCollection": false,//
      "isDelete": false,//
      "isFinish": false,
      "link": "http://www.weain.mil.cn/cgxq/ky/cpsbl/635648.html",//
      "major": null,//
      "projectNumber": null,//
      "projectType": null,//
      "publishTime": 1547136000000,//
      "quota": null,//
      "startTime": null,//
      "status": true,//
      "title": "高空长航时小型无人机",//
      "type": "采购需求"*///
        private String dataId;
        private boolean isFinish;
        private String status;
        private String id;
        private boolean isCollection;
        private String isDelete;
        private String quota;
        private String startTime;
        private String createTime;
        private String title;
        private String type;
        private String endTime;
        private String function;
        private String link;
        private String major;
        private String projectNumber;
        private String projectType;
        private String publishTime;

        public String getdataId(String dataId) {
            return dataId;
        }
        public void setdataId(String dataId) {
            this.dataId = dataId;
        }
        public boolean getisFinish() {
            return isFinish;
        }

        public void setisFinish(boolean isFinish) {
            this.isFinish = isFinish;
        }

        public String getprojectTyper() {
            return projectType;
        }

        public void setprojectType(String projectType) {
            this.projectType = projectType;
        }
        public String getprojectNumber() {
            return projectNumber;
        }

        public void setprojectNumber(String projectNumber) {
            this.projectNumber = projectNumber;
        }
        public String getmajor() {
            return major;
        }

        public void setmajor(String major) {
            this.major = major;
        }
        public String getlink() {
            return link;
        }

        public void setlink(String link) {
            this.link = link;
        }

        public String getquota() {
            return quota;
        }

        public void setquota(String quota) {
            this.quota = quota;
        }

        public String getstatus() {
            return status;
        }

        public void setstatus(String address) {
            this.status = status;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String gettype() {
            return type;
        }

        public void settype(String type) {
            this.type = type;
        }

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean getisCollection() {
            return isCollection;
        }

        public void setisCollection(boolean isCollection) {
            this.isCollection = isCollection;
        }

        public String getisDelete() {
            return isDelete;
        }

        public void setisDelete(String isDelete) {
            this.isDelete = isDelete;
        }

        public String getstartTime() {
            return startTime;
        }

        public void setstartTime(String startTime) {
            this.startTime = startTime;
        }

        public String gettitle() {
            return title;
        }

        public void settitle(String jai_tpo) {
            this.title = title;
        }



        public String getendTime() {
            return endTime;
        }

        public void setendTime(String endTime) {
            this.endTime = endTime;
        }

        public String getfunction() {
            return function;
        }

        public void setfunction(String function) {
            this.function = function;
        }

        public String getpublishTime() {
            return publishTime;
        }

        public void setpublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

    }
}
