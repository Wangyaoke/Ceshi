package com.ansiyida.cgjl.bean;

public class cpml_detail_listbean {

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

        public static class after_bean {
            /*      "companySummary": {
                  "click": true,
                          "id": 425,
                          "name": "北京七维航测科技股份有限公司"
              },
                      "degree": "国内先进",
                      "id": 324,
                      "img": "http://pyspider-crawl-image-v01.oss-cn-qingdao.aliyuncs.com/weain.mil.cn/2018/11/23061f7d1a3acc9daffe1d969ad24d610.jpg",
                      "isDelete": false,
                      "majorField": "航天/航天平台技术 ; 信息技术/计算机技术 ; 电子技术/电子元器件 ; 制导导航控制/制导技术",
                      "productKeyword": "军工车载环境标准； 抗冲击 / 振动 / 防尘； 高低温特性",
                      "productSummary": "SDI-Compass是我公司研制的一款高性能北斗/GPS双天线定位测向产品，它可以同时接收BDS B1、B2和GPS L1、L2导航卫星信号，实现多系统组合导航定位和授时功能，并通过测量两个天线构成的基线矢量的方位角，快速准确的测出载体的方位和姿态信息。",
                      "publishTime": 1435910068000,
                      "source": "自荐",
                      "status": true,
                      "title": "SDI-COMPASS 北斗双天线定位定向接收机"*/
            private companySummarybean companySummary;//
            private String id;//
            private String degree;//
            private String img;//
            private String majorField;//
            private String productKeyword;//
            private String productSummary;//
            private String source;//
            private String isDelete;//
            private String title;//
            private String status;//
            private String publishTime;//
            public companySummarybean getcompanySummary() {
                return companySummary;
            }

            public void setcompanySummary(companySummarybean companySummary) {
                this.companySummary = companySummary;
            }
            public String getdegree() {
                return degree;
            }

            public void setdegree(String degree) {
                this.degree = degree;
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

            public String getisDelete() {
                return isDelete;
            }

            public void setisDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getproductSummary() {
                return productSummary;
            }

            public void setproductSummary(String productSummary) {
                this.productSummary = productSummary;
            }

            public String gettitle() {
                return title;
            }

            public void settitle(String jai_tpo) {
                this.title = title;
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

            public static class companySummarybean {
                private String click;//
                private String id;//
                private String name;//

                public String getclick() {
                    return click;
                }

                public void setclick(String click) {
                    this.click = click;
                }

                public String getname() {
                    return name;
                }

                public void setname(String name) {
                    this.name = name;
                }

                public String getid() {
                    return id;
                }

                public void setid(String id) {
                    this.id = id;
                }
            }
        }

        public static class before_bean {
            private companySummarybean companySummary;//
            private String id;//
            private String degree;//
            private String img;//
            private String majorField;//
            private String productKeyword;//
            private String productSummary;//
            private String source;//
            private String isDelete;//
            private String title;//
            private String status;//
            private String publishTime;//
            public companySummarybean getcompanySummary() {
                return companySummary;
            }

            public void setcompanySummary(companySummarybean companySummary) {
                this.companySummary = companySummary;
            }
            public String getdegree() {
                return degree;
            }

            public void setdegree(String degree) {
                this.degree = degree;
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

            public String getisDelete() {
                return isDelete;
            }

            public void setisDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getproductSummary() {
                return productSummary;
            }

            public void setproductSummary(String productSummary) {
                this.productSummary = productSummary;
            }

            public String gettitle() {
                return title;
            }

            public void settitle(String jai_tpo) {
                this.title = title;
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

            public static class companySummarybean {
                private String click;//
                private String id;//
                private String name;//

                public String getclick() {
                    return click;
                }

                public void setclick(String click) {
                    this.click = click;
                }

                public String getname() {
                    return name;
                }

                public void setname(String name) {
                    this.name = name;
                }

                public String getid() {
                    return id;
                }

                public void setid(String id) {
                    this.id = id;
                }
            }
        }

        public static class current_bean {
            private companySummarybean companySummary;//
            private String id;//
            private String degree;//
            private String img;//
            private String majorField;//
            private String productKeyword;//
            private String productSummary;//
            private String source;//
            private String isDelete;//
            private String title;//
            private String status;//
            private String publishTime;//

            public companySummarybean getcompanySummary() {
                return companySummary;
            }

            public void setcompanySummary(companySummarybean companySummary) {
                this.companySummary = companySummary;
            }
            public String getdegree() {
                return degree;
            }

            public void setdegree(String degree) {
                this.degree = degree;
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

            public String getisDelete() {
                return isDelete;
            }

            public void setisDelete(String isDelete) {
                this.isDelete = isDelete;
            }

            public String getproductSummary() {
                return productSummary;
            }

            public void setproductSummary(String productSummary) {
                this.productSummary = productSummary;
            }

            public String gettitle() {
                return title;
            }

            public void settitle(String jai_tpo) {
                this.title = title;
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

            public static class companySummarybean {
                private String click;//
                private String id;//
                private String name;//

                public String getclick() {
                    return click;
                }

                public void setclick(String click) {
                    this.click = click;
                }

                public String getname() {
                    return name;
                }

                public void setname(String name) {
                    this.name = name;
                }

                public String getid() {
                    return id;
                }

                public void setid(String id) {
                    this.id = id;
                }
            }
        }
    }
}
