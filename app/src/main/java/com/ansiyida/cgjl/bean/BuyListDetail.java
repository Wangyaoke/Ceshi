package com.ansiyida.cgjl.bean;

public class BuyListDetail {

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

    @Override
    public String toString() {
        return "BuyListDetail{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * jai_status : Y
         * jal_id : 1
         * jal_end_time : 2018-03-30 00:00:00
         * jal_desc : http://47.74.147.41:8081/back/advertising-readone
         * jal_recomment : Y
         * jal_images : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fdf3b970a1a845e0a5e39425fe256ac3.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=8Kd%2Fi57%2Ff%2BwCGOAVVKNnUlwHxxQ%3D
         * jai_title : 社会
         * jai_tpo : null
         * jai_id : 85
         * isdel : Y
         * jai_tupe : A
         * jai_path : null
         * jai_sort : 1
         * jai_url : http://47.74.147.41:8081/back/advertising-readone
         * jal_start_time : 2018-03-01 14:51:43
         */

        /*"address":"厦门市湖滨南路81号光大银行大厦21层，电话：0592-230888"
       "content":""
       "createTime":1539038831000
       "id":"35"
       "isCollection":false
       "link":"http://www.ccgp.gov.cn/cggg/dfgg/jzxtpgg/201711/t20171129_9251258.htm"
       "province":"福建"
       "publishTime":1511967300000
       "source":"中国政府采购网"
       "title":"厦门公物-竞争性谈判-GW2017-SH668-智慧图书馆系统-采购公告"
       "type":"竞争性谈判公告"*/
        private String address;
        private String id;
        private String content;
        private String createTime;
        private String isCollection;
        private String link;
        private String province;
        private String title;
        private String type;
        private String source;
        private String publishTime;
        private String sourceId;
        private String status;
        private String typeId;
        private String isDelete;
        private Boolean isReportErrors;

        public String getsourceId() {
            return sourceId;
        }

        public void setsourceId(String sourceId) {
            this.sourceId = sourceId;
        }

        public String getstatus() {
            return status;
        }

        public void setstatus(String status) {
            this.status = status;
        }
        public String gettypeId() {
            return typeId;
        }

        public void settypeId(String typeId) {
            this.typeId = typeId;
        }
        public String getisDelete() {
            return isDelete;
        }

        public void setisDelete(String isDelete) {
            this.isDelete = isDelete;
        }
        public boolean getisReportErrors() {
            return isReportErrors;
        }

        public void setisReportErrors(boolean isReportErrors) {
            this.isReportErrors = isReportErrors;
        }
        public String getaddress() {
            return address;
        }

        public void setaddress(String address) {
            this.address = address;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String getcontent() {
            return content;
        }

        public void setcontent(String content) {
            this.content = content;
        }

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getisCollection() {
            return isCollection;
        }

        public void setisCollection(String isCollection) {
            this.isCollection = isCollection;
        }

        public String getlink() {
            return link;
        }

        public void setlink(String link) {
            this.link = link;
        }

        public String getprovince() {
            return province;
        }

        public void setprovince(String province) {
            this.province = province;
        }

        public String gettitle() {
            return title;
        }

        public void settitle(String jai_tpo) {
            this.title = title;
        }



        public String gettype() {
            return type;
        }

        public void settype(String type) {
            this.type = type;
        }

        public String getsource() {
            return source;
        }

        public void setsource(String source) {
            this.source = source;
        }

        public String getpublishTime() {
            return publishTime;
        }

        public void setpublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "address='" + address + '\'' +
                    ", id='" + id + '\'' +
                    ", content='" + content + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", isCollection='" + isCollection + '\'' +
                    ", link='" + link + '\'' +
                    ", province='" + province + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", source='" + source + '\'' +
                    ", publishTime='" + publishTime + '\'' +
                    ", sourceId='" + sourceId + '\'' +
                    ", status='" + status + '\'' +
                    ", typeId='" + typeId + '\'' +
                    ", isDelete='" + isDelete + '\'' +
                    ", isReportErrors=" + isReportErrors +
                    '}';
        }
    }
}
