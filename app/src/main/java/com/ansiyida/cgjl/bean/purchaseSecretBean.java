package com.ansiyida.cgjl.bean;

import java.util.List;

public class purchaseSecretBean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fdf3b970a1a845e0a5e39425fe256ac3.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=8Kd%2Fi57%2Ff%2BwCGOAVVKNnUlwHxxQ%3D","jai_title":"社会","jai_tpo":null,"jai_id":85,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:51:43"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-31 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fd250677148f4d19b7fc1aadf9647c6b.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=QR6wqZptYwOSOC6ZUV29P7lNSFA%3D","jai_title":"身边","jai_tpo":null,"jai_id":86,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:16"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/e06798e6728640a2abde3da0a56cd744.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=gqKDcco%2BituQEaS2tP5Zprs3ATI%3D","jai_title":"前沿","jai_tpo":null,"jai_id":87,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:45"}]
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

        private String nextPage;
        private String pageNum;
        private String pageSize;
        private String pages;
        private String prePage;
        private String size;
        private String startRow;
        private String total;
        private List<list_law_bean> list;
        private String navigateFirstPage;
        private String navigateLastPage;
        private String navigatePages;
        private List<String> navigatepageNums;
        private String endRow;
        private String hasNextPage;
        private String hasPreviousPage;
        private String isFirstPage;
        private String isLastPage;
        public List<list_law_bean> getlist_law_bean() {
            return list;
        }

        public void setlist_law_bean(List<list_law_bean> list) {
            this.list = list;
        }

    public static class list_law_bean
    {
          /*  "createTime": 1548652092000,
        "endTime": 1549123200000,
        "id": 2436,
        "infoColumn": "中标公告",
        "isCollection": false,
        "isDelete": false,
        "publishTime": 1548482972000,
        "secretDegree": "秘密",
        "title": "质心角反干扰设备研制项目中标公告"
        */
        private String infoColumn;
        private String id;
        private boolean isCollection;
        private String isDelete;
        private String secretDegree;
        private String createTime;
        private String title;
        private String endTime;
        private String publishTime;

        public String getinfoColumn() {
            return infoColumn;
        }

        public void setinfoColumn(String infoColumn) {
            this.infoColumn = infoColumn;
        }
        public String getsecretDegree() {
            return secretDegree;
        }

        public void setsecretDegree(String secretDegree) {
            this.secretDegree = secretDegree;
        }


        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
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


        public String getpublishTime() {
            return publishTime;
        }

        public void setpublishTime(String publishTime) {
            this.publishTime = publishTime;
        }
    }

    }
}
