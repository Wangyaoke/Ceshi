package com.ansiyida.cgjl.bean;

public class purchaseDemand_detailn {

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
    {/*

      "endTime": 1544630400000,
      "function": "用于开展车辆装备在室内模拟高原环境条件下的性能试验，解决目前缺少可控条件的车辆装备野外高原性能试验场地的问题，为定量评价车辆装备的高原环境适应能力提供条件。",
      "id": 41556,
      "isCollection": false,
      "isDelete": false,
      "link": "http://www.weain.mil.cn/cgxq/ky/cpsbl/596531.html",
      "major": null,
      "projectNumber": null,
      "projectType": null,
      "publishTime": 1543507200000,
      "quota": "<p>1.功能要求 （1）系统组成 由底盘测功系统、设备承载基础及保温系统组成。 （2）系统功能 底盘测功系统用滚筒模拟路面，可完成车辆装备功率曲线测试、定点测功、恒扭矩测功、系统阻力测试等试验内容；设备承载基础需在原岩石地基的基础上进行建设（填充地下室），可承托底盘测功系统本体及试验样车的质量；保温系统通过在舱体地面、舱壁及舱顶加装保温材料可维持舱内环境温度。 2.指标要求 （1）底盘测功系统 ①轴重≥13t； ②滚筒直径≥210mm； ③适合轴距：5轴（含5轴）以下（相邻两轴轴距不小于1250mm）的各种轴距的车型； ④车速测试范围：0km/h～120km/h； ⑤车速分辨率：0.1km/h； ⑥车速测试误差≤1%； ⑦发动机功率测试范围：0kW～800kW； ⑧功率测试分辨率：0.1kW； ⑨功率测试误差：≤1%； ⑩扭矩测试误差≤1%。 （2）设备承载基础 采用整体式钢筋混凝土结构，长度为18m，宽度为6m，高度为6m，除去底盘测功系统本体镶嵌部分，其体积约为600m3。 3.使用要求 （1）保证试验样车试验时的各个方向振动不会影响实验室主体结构的变化； （2）满足高原低温、常温使用需求； （3）具有防潮、防霉设计。</p>\n",
      "startTime": null,
      "status": true,
      "title": "高原环境模拟测试系统20181129",
      "type": "采购需求"
    */
        private String status;
        private String id;
        private boolean isCollection;
        private String isDelete;
        private String quota;
        private String startTime;
        private String createTime;
        private String title;
        private String type;
        private String endTime;
        private String function;
        private String link;
        private String major;
        private String projectNumber;
        private String projectType;
        private String publishTime;

        public String getprojectTyper() {
            return projectType;
        }

        public void setprojectType(String projectType) {
            this.projectType = projectType;
        }
        public String getprojectNumber() {
            return projectNumber;
        }

        public void setprojectNumber(String projectNumber) {
            this.projectNumber = projectNumber;
        }
        public String getmajor() {
            return major;
        }

        public void setmajor(String major) {
            this.major = major;
        }
        public String getlink() {
            return link;
        }

        public void setlink(String link) {
            this.link = link;
        }

        public String getquota() {
            return quota;
        }

        public void setquota(String quota) {
            this.quota = quota;
        }

        public String getstatus() {
            return status;
        }

        public void setstatus(String address) {
            this.status = status;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String gettype() {
            return type;
        }

        public void settype(String type) {
            this.type = type;
        }

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public boolean getisCollection() {
            return isCollection;
        }

        public void setisCollection(boolean isCollection) {
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



        public String getendTime() {
            return endTime;
        }

        public void setendTime(String endTime) {
            this.endTime = endTime;
        }

        public String getfunction() {
            return function;
        }

        public void setfunction(String function) {
            this.function = function;
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
            private String status;
            private String id;
            private boolean isCollection;
            private String isDelete;
            private String quota;
            private String startTime;
            private String createTime;
            private String title;
            private String type;
            private String endTime;
            private String function;
            private String link;
            private String major;
            private String projectNumber;
            private String projectType;
            private String publishTime;

            public String getprojectTyper() {
                return projectType;
            }

            public void setprojectType(String projectType) {
                this.projectType = projectType;
            }
            public String getprojectNumber() {
                return projectNumber;
            }

            public void setprojectNumber(String projectNumber) {
                this.projectNumber = projectNumber;
            }
            public String getmajor() {
                return major;
            }

            public void setmajor(String major) {
                this.major = major;
            }
            public String getlink() {
                return link;
            }

            public void setlink(String link) {
                this.link = link;
            }

            public String getquota() {
                return quota;
            }

            public void setquota(String quota) {
                this.quota = quota;
            }

            public String getstatus() {
                return status;
            }

            public void setstatus(String address) {
                this.status = status;
            }

            public String getid() {
                return id;
            }

            public void setid(String id) {
                this.id = id;
            }

            public String gettype() {
                return type;
            }

            public void settype(String type) {
                this.type = type;
            }

            public String getcreateTime() {
                return createTime;
            }

            public void setcreateTime(String createTime) {
                this.createTime = createTime;
            }

            public boolean getisCollection() {
                return isCollection;
            }

            public void setisCollection(boolean isCollection) {
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



            public String getendTime() {
                return endTime;
            }

            public void setendTime(String endTime) {
                this.endTime = endTime;
            }

            public String getfunction() {
                return function;
            }

            public void setfunction(String function) {
                this.function = function;
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
            private String status;
            private String id;
            private boolean isCollection;
            private String isDelete;
            private String quota;
            private String startTime;
            private String createTime;
            private String title;
            private String type;
            private String endTime;
            private String function;
            private String link;
            private String major;
            private String projectNumber;
            private String projectType;
            private String publishTime;

            public String getprojectTyper() {
                return projectType;
            }

            public void setprojectType(String projectType) {
                this.projectType = projectType;
            }
            public String getprojectNumber() {
                return projectNumber;
            }

            public void setprojectNumber(String projectNumber) {
                this.projectNumber = projectNumber;
            }
            public String getmajor() {
                return major;
            }

            public void setmajor(String major) {
                this.major = major;
            }
            public String getlink() {
                return link;
            }

            public void setlink(String link) {
                this.link = link;
            }

            public String getquota() {
                return quota;
            }

            public void setquota(String quota) {
                this.quota = quota;
            }

            public String getstatus() {
                return status;
            }

            public void setstatus(String address) {
                this.status = status;
            }

            public String getid() {
                return id;
            }

            public void setid(String id) {
                this.id = id;
            }

            public String gettype() {
                return type;
            }

            public void settype(String type) {
                this.type = type;
            }

            public String getcreateTime() {
                return createTime;
            }

            public void setcreateTime(String createTime) {
                this.createTime = createTime;
            }

            public boolean getisCollection() {
                return isCollection;
            }

            public void setisCollection(boolean isCollection) {
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



            public String getendTime() {
                return endTime;
            }

            public void setendTime(String endTime) {
                this.endTime = endTime;
            }

            public String getfunction() {
                return function;
            }

            public void setfunction(String function) {
                this.function = function;
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
