package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/1.
 */
public class BannerBean {

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

        private String jai_status;
        private int jal_id;
        private String jal_end_time;
        private String jal_desc;
        private String jal_recomment;
        private String jal_images;
        private String jai_title;
        private Object jai_tpo;
        private int jai_id;
        private String isdel;
        private String jai_tupe;
        private Object jai_path;
        private int jai_sort;
        private String jai_url;
        private String jal_start_time;

        public String getJai_status() {
            return jai_status;
        }

        public void setJai_status(String jai_status) {
            this.jai_status = jai_status;
        }

        public int getJal_id() {
            return jal_id;
        }

        public void setJal_id(int jal_id) {
            this.jal_id = jal_id;
        }

        public String getJal_end_time() {
            return jal_end_time;
        }

        public void setJal_end_time(String jal_end_time) {
            this.jal_end_time = jal_end_time;
        }

        public String getJal_desc() {
            return jal_desc;
        }

        public void setJal_desc(String jal_desc) {
            this.jal_desc = jal_desc;
        }

        public String getJal_recomment() {
            return jal_recomment;
        }

        public void setJal_recomment(String jal_recomment) {
            this.jal_recomment = jal_recomment;
        }

        public String getJal_images() {
            return jal_images;
        }

        public void setJal_images(String jal_images) {
            this.jal_images = jal_images;
        }

        public String getJai_title() {
            return jai_title;
        }

        public void setJai_title(String jai_title) {
            this.jai_title = jai_title;
        }

        public Object getJai_tpo() {
            return jai_tpo;
        }

        public void setJai_tpo(Object jai_tpo) {
            this.jai_tpo = jai_tpo;
        }

        public int getJai_id() {
            return jai_id;
        }

        public void setJai_id(int jai_id) {
            this.jai_id = jai_id;
        }

        public String getIsdel() {
            return isdel;
        }

        public void setIsdel(String isdel) {
            this.isdel = isdel;
        }

        public String getJai_tupe() {
            return jai_tupe;
        }

        public void setJai_tupe(String jai_tupe) {
            this.jai_tupe = jai_tupe;
        }

        public Object getJai_path() {
            return jai_path;
        }

        public void setJai_path(Object jai_path) {
            this.jai_path = jai_path;
        }

        public int getJai_sort() {
            return jai_sort;
        }

        public void setJai_sort(int jai_sort) {
            this.jai_sort = jai_sort;
        }

        public String getJai_url() {
            return jai_url;
        }

        public void setJai_url(String jai_url) {
            this.jai_url = jai_url;
        }

        public String getJal_start_time() {
            return jal_start_time;
        }

        public void setJal_start_time(String jal_start_time) {
            this.jal_start_time = jal_start_time;
        }
    }
}
