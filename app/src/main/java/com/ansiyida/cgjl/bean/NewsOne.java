package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by chenyaoxiang on 2017/11/8.
 */

public class NewsOne {


//      status : 0
//    data : [{"image":"https://zdmaker.oss-cn-beijing.aliyuncs.com/2017-08-15/5992bd95113f6.png","orders":"4","url":"","e_id":33,"title":"111","describe":"222","status":"4"},{"image":"https://zdmaker.oss-cn-beijing.aliyuncs.com/2017-06-28/5953742be6e90.png","orders":"3","url":"","e_id":30,"title":"中国将射近30颗北斗卫星 国人可享受厘米级导航服务","describe":"[环球网军事5月24日报道 环球时报赴上海特派记者 刘扬]5月23日，以\u201c定位，万物互联\u201d为主题的第八届中国卫星导航学术年会在上海召开，会上公布了详细而周密的\u201c北斗导航系统全球布网\u201d的计划与时间表。据《环球时报》记者了解，除了开始在太空中构建能覆盖全球的北斗星座，中国还在地面上打造可以大幅增强导航性能的北斗地基增强系统。随着这两张大网的织就，北斗的导航精度将会大幅提升，未来国人在中国的国土上享受到厘米级的导航服务不再是梦想。","status":"4"},{"image":"https://zdmaker.oss-cn-beijing.aliyuncs.com/2017-06-28/5953740c5252d.png","orders":"1","url":"","e_id":31,"title":"111","describe":"222","status":"4"},{"image":"https://zdmaker.oss-cn-beijing.aliyuncs.com/2017-06-28/5953741de8cf8.png","orders":"1","url":"","e_id":26,"title":"十分钟创新思维训练术","describe":"十分钟创新思维训练术","status":"4"}]


    private int status;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * image : https://zdmaker.oss-cn-beijing.aliyuncs.com/2017-08-15/5992bd95113f6.png
         * orders : 4
         * url :
         * e_id : 33
         * title : 111
         * describe : 222
         * status : 4
         */

        private String image;
        private String orders;
        private String url;
        private int e_id;
        private String title;
        private String describe;
        private String status;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getOrders() {
            return orders;
        }

        public void setOrders(String orders) {
            this.orders = orders;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getE_id() {
            return e_id;
        }

        public void setE_id(int e_id) {
            this.e_id = e_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
