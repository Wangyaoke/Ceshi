package com.ansiyida.cgjl.bean;

import java.util.List;

public class law_detail_listbean {

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

        private after_bean after;
        private before_bean before;
        private current_bean current;
        public after_bean getafter_bean() {
            return after;
        }
        public void setafter_bean(after_bean after) {
            this.after = after;
        }
        public before_bean getbefore_bean() {
            return before;
        }
        public void setbefore_bean(before_bean before) {
            this.before = before;
        }
        public current_bean getcurrent_bean() {
            return current;
        }
        public void setcurrent_bean(current_bean current) {
            this.current = current;
        }
    public static class after_bean
    {
        private String status;//
         private String id;//
        private String content;//
        private String createTime;//
        private String isCollection;//
        private String isDelete;//
        private String publishCompany;//
        private String issuedNumber;//
        private String startTime;//
        private String title;//
        private String type;//
        private String publishTime;//

        public String getissuedNumber() {
            return issuedNumber;
        }

        public void setissuedNumber(String issuedNumber) {
            this.issuedNumber = issuedNumber;
        }

        public String getstatus() {
            return status;
        }

        public void setstatus(String status) {
            this.status = status;
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



        public String gettype() {
            return type;
        }

        public void settype(String type) {
            this.type = type;
        }

        public String getpublishCompany() {
            return publishCompany;
        }

        public void setpublishCompany(String publishCompany) {
            this.publishCompany = publishCompany;
        }

        public String getpublishTime() {
            return publishTime;
        }

        public void setpublishTime(String publishTime) {
            this.publishTime = publishTime;
        }
    }
        public static class before_bean
        {
            private String status;//
            private String id;//
            private String content;//
            private String createTime;//
            private String isCollection;//
            private String isDelete;//
            private String publishCompany;//
            private String issuedNumber;//
            private String startTime;//
            private String title;//
            private String type;//
            private String publishTime;//

            public String getissuedNumber() {
                return issuedNumber;
            }

            public void setissuedNumber(String issuedNumber) {
                this.issuedNumber = issuedNumber;
            }

            public String getstatus() {
                return status;
            }

            public void setstatus(String status) {
                this.status = status;
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



            public String gettype() {
                return type;
            }

            public void settype(String type) {
                this.type = type;
            }

            public String getpublishCompany() {
                return publishCompany;
            }

            public void setpublishCompany(String publishCompany) {
                this.publishCompany = publishCompany;
            }

            public String getpublishTime() {
                return publishTime;
            }

            public void setpublishTime(String publishTime) {
                this.publishTime = publishTime;
            }
        }
        public static class current_bean
        {
            private String status;//
            private String id;//
            private String content;//
            private String createTime;//
            private String isCollection;//
            private String isDelete;//
            private String publishCompany;//
            private String issuedNumber;//
            private String startTime;//
            private String title;//
            private String type;//
            private String publishTime;//

            public String getissuedNumber() {
                return issuedNumber;
            }

            public void setissuedNumber(String issuedNumber) {
                this.issuedNumber = issuedNumber;
            }

            public String getstatus() {
                return status;
            }

            public void setstatus(String status) {
                this.status = status;
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



            public String gettype() {
                return type;
            }

            public void settype(String type) {
                this.type = type;
            }

            public String getpublishCompany() {
                return publishCompany;
            }

            public void setpublishCompany(String publishCompany) {
                this.publishCompany = publishCompany;
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
