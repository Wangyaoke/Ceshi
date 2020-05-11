package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/7.
 */
public class ProductBean {



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

        /*  "companyName": "北京中航智科技有限公司",
                   "degree": "国内领先",
                   "id": "821",
                   "img": "http://pyspider-crawl-image-v01.oss-cn-qingdao.aliyuncs.com/weain.mil.cn/2018/12/69c996fa8d96a762d2e4abf0b2596d270.jpg",
                   "isDelete": false,
                   "majorField": "信息技术/计算机技术 ; 电子技术/半导体技术 ; 制导导航控制/制导技术 ; 保障工程技术/可靠性测试性维修性保障性技术",
                   "productKeyword": "无人机、共轴反桨、自主控制",
                   "productSummary": "该产品是北京中航智科技有限公司经过多年研究和论证，成功研发的无人直升机，具有完全自主的知识产权，是目前中国最成熟、有效载荷比最大的国产无人直升机。",
                   "publishTime": 1435912326000,
                   "source": "自荐",
                   "status": true,
                   "title": "陆军小型通用无人直升机系统"*/
        private String companyName;
        private String degree;
        private String id;
        private String isDelete;
        private String img;
        private String majorField;
        private String productKeyword;
        private String productSummary;
        private String publishTime;
        private String source;
        private String status;
        private String title;

        public String getcompanyName() {
            return companyName;
        }

        public void setcompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getdegree() {
            return degree;
        }

        public void setdegree(String degree) {
            this.degree = degree;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getimg() {
            return img;
        }

        public void setimg(String img) {
            this.img = img;
        }

        public String getmajorField() {
            return majorField;
        }

        public void setmajorField(String majorField) {
            this.majorField = majorField;
        }

        public String getproductKeyword() {
            return productKeyword;
        }

        public void setproductKeyword(String productKeyword) {
            this.productKeyword = productKeyword;
        }
        public String getproductSummary() {
            return productSummary;
        }

        public void setproductSummary(String productSummary) {
            this.productSummary = productSummary;
        }
        public String getpublishTime() {
            return publishTime;
        }

        public void setpublishTime(String publishTime) {
            this.publishTime = publishTime;
        }
        public String getsource() {
            return source;
        }

        public void setsource(String source) {
            this.source = source;
        }
        public String getstatus() {
            return status;
        }

        public void setstatus(String status) {
            this.status = status;
        }
        public String gettitle() {
            return title;
        }

        public void settitle(String title) {
            this.title = title;
        }
        public String getisDelete() {
            return isDelete;
        }

        public void setisDelete(String isDelete) {
            this.isDelete = isDelete;
        }


    }
}
