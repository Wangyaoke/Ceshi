package com.ansiyida.cgjl.bean;

import java.util.List;

public class readhistory_bean {

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

        private String source;
        private String id;
        private String genre;
        private String createTime;
        private String infoId;
        private String province;
        private String userId;
        private String title;
        private String type;

        private String publishTime;




        public String getgenre() {
            return genre;
        }

        public void setgenre(String genre) {
            this.genre = genre;
        }

        public String getsource() {
            return source;
        }

        public void setsource(String source) {
            this.source = source;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String getinfoId() {
            return infoId;
        }

        public void setinfoId(String infoId) {
            this.infoId = infoId;
        }

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getprovince() {
            return province;
        }

        public void setprovince(String province) {
            this.province = province;
        }

        public String getuserId() {
            return userId;
        }

        public void setuserId(String userId) {
            this.userId = userId;
        }

        public String gettype() {
            return type;
        }

        public void settype(String startTime) {
            this.type = startTime;
        }

        public String gettitle() {
            return title;
        }

        public void settitle(String jai_tpo) {
            this.title = title;
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
