package com.ansiyida.cgjl.bean;

import java.util.List;

public class yzml_detail_listbean {

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

        private List<tender_bean> tender;
        private proprietorInfo_bean proprietorInfo;


        public List<tender_bean> gettender() {
            return tender;
        }

        public void settender(List<tender_bean> tender) {
            this.tender = tender;
        }

        public proprietorInfo_bean getproprietorInfo() {
            return proprietorInfo;
        }

        public void setproprietorInfo(proprietorInfo_bean proprietorInfo) {
            this.proprietorInfo = proprietorInfo;
        }



        public static class tender_bean {
            private List<companySummaries_bean> companySummaries;
            private purchaseInfoSummary_bean purchaseInfoSummary;

            public List<companySummaries_bean> getcompanySummaries() {
                return companySummaries;
            }

            public void setcompanySummaries(List<companySummaries_bean> companySummaries) {
                this.companySummaries = companySummaries;
            }
            public purchaseInfoSummary_bean getpurchaseInfoSummary() {
                return purchaseInfoSummary;
            }

            public void setpurchaseInfoSummary(purchaseInfoSummary_bean purchaseInfoSummary) {
                this.purchaseInfoSummary = purchaseInfoSummary;
            }


            public static class companySummaries_bean {
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
            public static class purchaseInfoSummary_bean {
                private String click;//
                private String id;//
                private String money;
                private String title;//

                public String getclick() {
                    return click;
                }

                public void setclick(String click) {
                    this.click = click;
                }

                public String getmoney() {
                    return money;
                }

                public void setmoney(String money) {
                    this.money = money;
                }

                public String getid() {
                    return id;
                }

                public void setid(String id) {
                    this.id = id;
                }
                public String gettitle() {
                    return title;
                }

                public void settitle(String title) {
                    this.title = title;
                }
            }
        }
/*  "code": "平舆县交通局",
      "createTime": 1545185169000,
      "dataTime": 1545198017000,
      "id": 956252,
      "isDelete": false,
      "name": "平舆县交通局",
      "status": true,
      "todayNum": 3,
      "updateNum": 3*/
        public static class proprietorInfo_bean {

            private String code;//-
            private String createTime;//-
            private String dataTime;//-
            private String id;//-
            private String name;//-
            private String status;//-
            private String todayNum;//-
            private String updateNum;//


            public String getcode() {
                return code;
            }

            public void setcode(String code) {
                this.code = code;
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

            public String getcreateTime() {
                return createTime;
            }

            public void setcreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getdataTime() {
                return dataTime;
            }

            public void setdataTime(String dataTime) {
                this.dataTime = dataTime;
            }

            public String getname() {
                return name;
            }

            public void setname(String name) {
                this.name = name;
            }

            public String gettodayNum() {
                return todayNum;
            }

            public void settodayNum(String todayNum) {
                this.todayNum = todayNum;
            }

            public String getupdateNum() {
                return updateNum;
            }

            public void setupdateNum(String updateNum) {
                this.updateNum = updateNum;
            }


        }
    }
}
