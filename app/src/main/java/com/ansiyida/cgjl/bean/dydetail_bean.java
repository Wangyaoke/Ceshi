package com.ansiyida.cgjl.bean;

import java.util.List;

public class dydetail_bean {

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


        private boolean status;
        private String id;
        private String updateNum;
        private String createTime;
        private String address;
        private int sourceId;
        private String province;
        private String source;
        private String readTime;
        private String type;
        private int typeId;
        private String keyword;
        private String name;


        public String getkeyword() {
            return keyword;
        }

        public void setkeyword(String keyword) {
            this.keyword = keyword;
        }

        public String getupdateNum() {
            return updateNum;
        }

        public void setupdateNum(String updateNum) {
            this.updateNum = updateNum;
        }

        public boolean getstatus() {
            return status;
        }

        public void setstatus(boolean status) {
            this.status = status;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String getaddress() {
            return address;
        }

        public void setaddress(String address) {
            this.address = address;
        }

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public int getsourceId() {
            return sourceId;
        }

        public void setsourceId(int sourceId) {
            this.sourceId = sourceId;
        }

        public String getprovince() {
            return province;
        }

        public void setprovince(String province) {
            this.province = province;
        }

        public String getsource() {
            return source;
        }

        public void setsource(String source) {
            this.source = source;
        }

        public String getreadTime() {
            return readTime;
        }

        public void setreadTime(String readTime) {
            this.readTime = readTime;
        }



        public String gettype() {
            return type;
        }

        public void settype(String type) {
            this.type = type;
        }
        public int gettypeId() {
            return typeId;
        }

        public void settypeId(int typeId) {
            this.typeId = typeId;
        }
        public String getname() {
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }

    }
}
