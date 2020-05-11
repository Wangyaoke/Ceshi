package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class IndustrialParkDetailBean {


    /**
     * data : {"civilMilitary":{"address":"杭州","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":null,"id":null,"img":null,"isShow":null,"label":null,"mode":null,"name":"杭州产业园","sort":null,"typeId":null,"unitPrice":4},"listDetails":[{"createTime":1555322008000,"id":1,"isShow":true,"name":"政策新闻","parkId":1,"sort":1,"title":"亦庄经济开发区","updateTime":null},{"createTime":1555322400000,"id":2,"isShow":true,"name":"政策新闻","parkId":1,"sort":2,"title":"中关村科技园政策","updateTime":null}],"textDetails":[{"content":"<p>\r\n    <span style=\"color: rgb(25, 31, 37); font-family: &quot;Microsoft YaHei&quot;, &quot;Segoe UI&quot;, system-ui, Roboto, &quot;Droid Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif, Tahoma, &quot;Segoe UI Symbol&quot;; font-size: 14px; white-space: pre-wrap; background-color: rgb(255, 255, 255);\">1.项目名称\r\n计算未来前沿科技创新产业园（北京园区）\r\n2.项目定位\r\n以工业遗存的历史文化为基底，以科技创新为主导，打造集科技创新、信息技术、文化传播、创意设计于一体的国际创新型科技生态产业园。\r\n3.产业业态\r\n国防科技、互联网、文化创意。\r\n4.项目背景\r\n本项目位于北京市大兴区瀛海镇，原五洲燕阳院内，产业园是在北京\u201c腾退疏解促提升\u201d和老旧厂房推动转型的政策下改造的大兴区瀛海镇高品质商务园区的示范项目。\r\n5.项目概述\r\n项目整体占地面积约23700平方米，改造后建筑面积19170平方米，容积率0.81，绿化率35%。<\/span>\r\n<\/p>","id":1,"isShow":true,"name":"园区介绍","parkId":1,"sort":1},{"content":"<p>\r\n    <span style=\"color: rgb(25, 31, 37); font-family: &quot;Microsoft YaHei&quot;, &quot;Segoe UI&quot;, system-ui, Roboto, &quot;Droid Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif, Tahoma, &quot;Segoe UI Symbol&quot;; font-size: 14px; white-space: pre-wrap; background-color: rgb(255, 255, 255);\">1.针对军民融合企业，提供一站式民参军服务\r\n2.打造\u201c一企N中心\u201d概念，入一园，享多园服务\r\n3.数字化大数据管理体系的运营平台\r\n4.商务物业定制、会议活动场地及高端商务服务平台\r\n5.餐饮、公寓、 文体活动中心等园区配套设施共享平台\r\n6.引进政府一站式服务,为企业提供政策与手续办理的一站式绿色通道<\/span>\r\n<\/p>","id":2,"isShow":true,"name":"特色服务","parkId":1,"sort":2}]}
     * message : 成功
     * status : 200
     */

    private DataBean data;
    private String message;
    private int status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * civilMilitary : {"address":"杭州","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":null,"id":null,"img":null,"isShow":null,"label":null,"mode":null,"name":"杭州产业园","sort":null,"typeId":null,"unitPrice":4}
         * listDetails : [{"createTime":1555322008000,"id":1,"isShow":true,"name":"政策新闻","parkId":1,"sort":1,"title":"亦庄经济开发区","updateTime":null},{"createTime":1555322400000,"id":2,"isShow":true,"name":"政策新闻","parkId":1,"sort":2,"title":"中关村科技园政策","updateTime":null}]
         * textDetails : [{"content":"<p>\r\n    <span style=\"color: rgb(25, 31, 37); font-family: &quot;Microsoft YaHei&quot;, &quot;Segoe UI&quot;, system-ui, Roboto, &quot;Droid Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif, Tahoma, &quot;Segoe UI Symbol&quot;; font-size: 14px; white-space: pre-wrap; background-color: rgb(255, 255, 255);\">1.项目名称\r\n计算未来前沿科技创新产业园（北京园区）\r\n2.项目定位\r\n以工业遗存的历史文化为基底，以科技创新为主导，打造集科技创新、信息技术、文化传播、创意设计于一体的国际创新型科技生态产业园。\r\n3.产业业态\r\n国防科技、互联网、文化创意。\r\n4.项目背景\r\n本项目位于北京市大兴区瀛海镇，原五洲燕阳院内，产业园是在北京\u201c腾退疏解促提升\u201d和老旧厂房推动转型的政策下改造的大兴区瀛海镇高品质商务园区的示范项目。\r\n5.项目概述\r\n项目整体占地面积约23700平方米，改造后建筑面积19170平方米，容积率0.81，绿化率35%。<\/span>\r\n<\/p>","id":1,"isShow":true,"name":"园区介绍","parkId":1,"sort":1},{"content":"<p>\r\n    <span style=\"color: rgb(25, 31, 37); font-family: &quot;Microsoft YaHei&quot;, &quot;Segoe UI&quot;, system-ui, Roboto, &quot;Droid Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif, Tahoma, &quot;Segoe UI Symbol&quot;; font-size: 14px; white-space: pre-wrap; background-color: rgb(255, 255, 255);\">1.针对军民融合企业，提供一站式民参军服务\r\n2.打造\u201c一企N中心\u201d概念，入一园，享多园服务\r\n3.数字化大数据管理体系的运营平台\r\n4.商务物业定制、会议活动场地及高端商务服务平台\r\n5.餐饮、公寓、 文体活动中心等园区配套设施共享平台\r\n6.引进政府一站式服务,为企业提供政策与手续办理的一站式绿色通道<\/span>\r\n<\/p>","id":2,"isShow":true,"name":"特色服务","parkId":1,"sort":2}]
         */

        private CivilMilitaryBean civilMilitary;
        private List<ListDetailsBean> listDetails;
        private List<TextDetailsBean> textDetails;

        public CivilMilitaryBean getCivilMilitary() {
            return civilMilitary;
        }

        public void setCivilMilitary(CivilMilitaryBean civilMilitary) {
            this.civilMilitary = civilMilitary;
        }

        public List<ListDetailsBean> getListDetails() {
            return listDetails;
        }

        public void setListDetails(List<ListDetailsBean> listDetails) {
            this.listDetails = listDetails;
        }

        public List<TextDetailsBean> getTextDetails() {
            return textDetails;
        }

        public void setTextDetails(List<TextDetailsBean> textDetails) {
            this.textDetails = textDetails;
        }

        public static class CivilMilitaryBean {
            /**
             * address : 杭州
             * banner : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg
             * createTime : null
             * id : null
             * img : null
             * isShow : null
             * label : null
             * mode : null
             * name : 杭州产业园
             * sort : null
             * typeId : null
             * unitPrice : 4
             */

            private String address;
            private String banner;
            private Object createTime;
            private Object id;
            private Object img;
            private Object isShow;
            private Object label;
            private Object mode;
            private String name;
            private Object sort;
            private Object typeId;
            private int unitPrice;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getBanner() {
                return banner;
            }

            public void setBanner(String banner) {
                this.banner = banner;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public Object getId() {
                return id;
            }

            public void setId(Object id) {
                this.id = id;
            }

            public Object getImg() {
                return img;
            }

            public void setImg(Object img) {
                this.img = img;
            }

            public Object getIsShow() {
                return isShow;
            }

            public void setIsShow(Object isShow) {
                this.isShow = isShow;
            }

            public Object getLabel() {
                return label;
            }

            public void setLabel(Object label) {
                this.label = label;
            }

            public Object getMode() {
                return mode;
            }

            public void setMode(Object mode) {
                this.mode = mode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Object getSort() {
                return sort;
            }

            public void setSort(Object sort) {
                this.sort = sort;
            }

            public Object getTypeId() {
                return typeId;
            }

            public void setTypeId(Object typeId) {
                this.typeId = typeId;
            }

            public int getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(int unitPrice) {
                this.unitPrice = unitPrice;
            }
        }

        public static class ListDetailsBean {
            /**
             * createTime : 1555322008000
             * id : 1
             * isShow : true
             * name : 政策新闻
             * parkId : 1
             * sort : 1
             * title : 亦庄经济开发区
             * updateTime : null
             */

            private long createTime;
            private int id;
            private boolean isShow;
            private String name;
            private int parkId;
            private int sort;
            private String title;
            private Object updateTime;

            public long getCreateTime() {
                return createTime;
            }

            public void setCreateTime(long createTime) {
                this.createTime = createTime;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIsShow() {
                return isShow;
            }

            public void setIsShow(boolean isShow) {
                this.isShow = isShow;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParkId() {
                return parkId;
            }

            public void setParkId(int parkId) {
                this.parkId = parkId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public Object getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(Object updateTime) {
                this.updateTime = updateTime;
            }
        }

        public static class TextDetailsBean {
            /**
             * content : <p>
             <span style="color: rgb(25, 31, 37); font-family: &quot;Microsoft YaHei&quot;, &quot;Segoe UI&quot;, system-ui, Roboto, &quot;Droid Sans&quot;, &quot;Helvetica Neue&quot;, sans-serif, Tahoma, &quot;Segoe UI Symbol&quot;; font-size: 14px; white-space: pre-wrap; background-color: rgb(255, 255, 255);">1.项目名称
             计算未来前沿科技创新产业园（北京园区）
             2.项目定位
             以工业遗存的历史文化为基底，以科技创新为主导，打造集科技创新、信息技术、文化传播、创意设计于一体的国际创新型科技生态产业园。
             3.产业业态
             国防科技、互联网、文化创意。
             4.项目背景
             本项目位于北京市大兴区瀛海镇，原五洲燕阳院内，产业园是在北京“腾退疏解促提升”和老旧厂房推动转型的政策下改造的大兴区瀛海镇高品质商务园区的示范项目。
             5.项目概述
             项目整体占地面积约23700平方米，改造后建筑面积19170平方米，容积率0.81，绿化率35%。</span>
             </p>
             * id : 1
             * isShow : true
             * name : 园区介绍
             * parkId : 1
             * sort : 1
             */

            private String content;
            private int id;
            private boolean isShow;
            private String name;
            private int parkId;
            private int sort;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public boolean isIsShow() {
                return isShow;
            }

            public void setIsShow(boolean isShow) {
                this.isShow = isShow;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParkId() {
                return parkId;
            }

            public void setParkId(int parkId) {
                this.parkId = parkId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }
        }
    }
}
