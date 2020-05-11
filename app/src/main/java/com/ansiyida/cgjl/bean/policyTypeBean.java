package com.ansiyida.cgjl.bean;

import java.util.List;

public class policyTypeBean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fdf3b970a1a845e0a5e39425fe256ac3.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=8Kd%2Fi57%2Ff%2BwCGOAVVKNnUlwHxxQ%3D","jai_title":"社会","jai_tpo":null,"jai_id":85,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:51:43"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-31 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fd250677148f4d19b7fc1aadf9647c6b.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=QR6wqZptYwOSOC6ZUV29P7lNSFA%3D","jai_title":"身边","jai_tpo":null,"jai_id":86,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:16"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/e06798e6728640a2abde3da0a56cd744.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=gqKDcco%2BituQEaS2tP5Zprs3ATI%3D","jai_title":"前沿","jai_tpo":null,"jai_id":87,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:45"}]
     */

    private String status;
    private String message;

    private List<DataBean> data;
    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }
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
    public static class DataBean {
        private String code;
        private int id;
        private String name;
        private String sort;
        public String getcode() {
            return code;
        }

        public void setcode(String code) {
            this.code = code;
        }

        public int getid() {
            return id;
        }

        public void setid(int id) {
            this.id = id;
        }

        public String getname() {
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }

        public String getsort() {
            return sort;
        }

        public void setsort(String sort) {
            this.sort = sort;
        }

   /* private String endRow;
    private String hasNextPage;
    private String hasPreviousPage;
    private String isFirstPage;
    private String isLastPage;
    private String navigateFirstPage;
    private String navigateLastPage;
    private String navigatePages;
    private List<String> navigatepageNums;
    private String nextPage;
    private String pageNum;
    private String pageSize;
    private String pages;
    private String prePage;
    private String size;
    private String startRow;
    private String total;
    private List<listBean> list;



    public void setlist(List<listBean> list) {
        this.list = list;
    }
    public List<listBean> getlist() {
        return list;
    }


    public static class listBean {
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
       "type":"竞争性谈判公告"
        private String code;
        private int id;
        private String name;
        private String sort;
        public String getcode() {
            return code;
        }

        public void setcode(String code) {
            this.code = code;
        }

        public int getid() {
            return id;
        }

        public void setid(int id) {
            this.id = id;
        }

        public String getname() {
            return name;
        }

        public void setname(String name) {
            this.name = name;
        }

        public String getsort() {
            return sort;
        }

        public void setsort(String sort) {
            this.sort = sort;
        }*/

    }








}
