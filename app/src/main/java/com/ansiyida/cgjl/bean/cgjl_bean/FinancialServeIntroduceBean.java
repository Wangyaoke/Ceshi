package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class FinancialServeIntroduceBean {

    /**
     * data : {"applyForNumber":"40","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","financialServeModelLists":[{"createTime":null,"figureIntroduce":"中国人民大学学士，硕士，管理学博士。","financialServeId":1,"headPortrait":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","id":1,"isShow":true,"name":"任溶","sort":1,"title":"投资团队"},{"createTime":null,"figureIntroduce":"北京邮电大学工商管理硕士，天使成长营三期学院/班长","financialServeId":1,"headPortrait":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","id":2,"isShow":true,"name":"杨鹏","sort":2,"title":"投资团队"},{"createTime":null,"figureIntroduce":"人们大学，北京理工大学硕士研究生，教授级高级工程师","financialServeId":1,"headPortrait":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","id":3,"isShow":true,"name":"柳进军","sort":3,"title":"投资团队"},{"createTime":null,"figureIntroduce":"美国加州大学电子工程专业博士，教授级高级工程师","financialServeId":1,"headPortrait":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","id":4,"isShow":true,"name":"杨春云","sort":4,"title":"投资团队"}],"financialServeModelTexts":[{"content":"<p>\r\n    腾飞天使(北京)投资管理有限公司，中国证券投资基金业协会基金管理人登记号：P1061229，是国内一家专注于对新一代信息技术和新材料技术企业进行股权投资、投资管理等业务的新锐资本管理机构。其\u201c腾飞\u201d之名和飞马之形象，受启于我国古代晋初文学家傅玄《墙上难为趋府》诗句\u201c门有车马客，骖服若腾飞\u201d，象征着在时代发展的大潮中迅速崛起和发展，孕育着飞跃进步的雄心和希望\r\n<\/p>","financialServeId":1,"id":1,"isShow":true,"sort":1,"title":"企业简介"}],"investCaseImg":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","label":"信息技术,军民融合,新材料行业,投资与服务","name":"腾飞资本","title":null}
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
         * applyForNumber : 40
         * banner : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg
         * financialServeModelLists : [{"createTime":null,"figureIntroduce":"中国人民大学学士，硕士，管理学博士。","financialServeId":1,"headPortrait":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","id":1,"isShow":true,"name":"任溶","sort":1,"title":"投资团队"},{"createTime":null,"figureIntroduce":"北京邮电大学工商管理硕士，天使成长营三期学院/班长","financialServeId":1,"headPortrait":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","id":2,"isShow":true,"name":"杨鹏","sort":2,"title":"投资团队"},{"createTime":null,"figureIntroduce":"人们大学，北京理工大学硕士研究生，教授级高级工程师","financialServeId":1,"headPortrait":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","id":3,"isShow":true,"name":"柳进军","sort":3,"title":"投资团队"},{"createTime":null,"figureIntroduce":"美国加州大学电子工程专业博士，教授级高级工程师","financialServeId":1,"headPortrait":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","id":4,"isShow":true,"name":"杨春云","sort":4,"title":"投资团队"}]
         * financialServeModelTexts : [{"content":"<p>\r\n    腾飞天使(北京)投资管理有限公司，中国证券投资基金业协会基金管理人登记号：P1061229，是国内一家专注于对新一代信息技术和新材料技术企业进行股权投资、投资管理等业务的新锐资本管理机构。其\u201c腾飞\u201d之名和飞马之形象，受启于我国古代晋初文学家傅玄《墙上难为趋府》诗句\u201c门有车马客，骖服若腾飞\u201d，象征着在时代发展的大潮中迅速崛起和发展，孕育着飞跃进步的雄心和希望\r\n<\/p>","financialServeId":1,"id":1,"isShow":true,"sort":1,"title":"企业简介"}]
         * investCaseImg : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg
         * label : 信息技术,军民融合,新材料行业,投资与服务
         * name : 腾飞资本
         * title : null
         */

        private String applyForNumber;
        private String banner;
        private String investCaseImg;
        private String label;
        private String name;
        private Object title;
        private List<FinancialServeModelListsBean> financialServeModelLists;
        private List<FinancialServeModelTextsBean> financialServeModelTexts;

        public String getApplyForNumber() {
            return applyForNumber;
        }

        public void setApplyForNumber(String applyForNumber) {
            this.applyForNumber = applyForNumber;
        }

        public String getBanner() {
            return banner;
        }

        public void setBanner(String banner) {
            this.banner = banner;
        }

        public String getInvestCaseImg() {
            return investCaseImg;
        }

        public void setInvestCaseImg(String investCaseImg) {
            this.investCaseImg = investCaseImg;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getTitle() {
            return title;
        }

        public void setTitle(Object title) {
            this.title = title;
        }

        public List<FinancialServeModelListsBean> getFinancialServeModelLists() {
            return financialServeModelLists;
        }

        public void setFinancialServeModelLists(List<FinancialServeModelListsBean> financialServeModelLists) {
            this.financialServeModelLists = financialServeModelLists;
        }

        public List<FinancialServeModelTextsBean> getFinancialServeModelTexts() {
            return financialServeModelTexts;
        }

        public void setFinancialServeModelTexts(List<FinancialServeModelTextsBean> financialServeModelTexts) {
            this.financialServeModelTexts = financialServeModelTexts;
        }

        public static class FinancialServeModelListsBean {
            /**
             * createTime : null
             * figureIntroduce : 中国人民大学学士，硕士，管理学博士。
             * financialServeId : 1
             * headPortrait : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png
             * id : 1
             * isShow : true
             * name : 任溶
             * sort : 1
             * title : 投资团队
             */

            private Object createTime;
            private String figureIntroduce;
            private int financialServeId;
            private String headPortrait;
            private int id;
            private boolean isShow;
            private String name;
            private int sort;
            private String title;

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }

            public String getFigureIntroduce() {
                return figureIntroduce;
            }

            public void setFigureIntroduce(String figureIntroduce) {
                this.figureIntroduce = figureIntroduce;
            }

            public int getFinancialServeId() {
                return financialServeId;
            }

            public void setFinancialServeId(int financialServeId) {
                this.financialServeId = financialServeId;
            }

            public String getHeadPortrait() {
                return headPortrait;
            }

            public void setHeadPortrait(String headPortrait) {
                this.headPortrait = headPortrait;
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
        }

        public static class FinancialServeModelTextsBean {
            /**
             * content : <p>
             腾飞天使(北京)投资管理有限公司，中国证券投资基金业协会基金管理人登记号：P1061229，是国内一家专注于对新一代信息技术和新材料技术企业进行股权投资、投资管理等业务的新锐资本管理机构。其“腾飞”之名和飞马之形象，受启于我国古代晋初文学家傅玄《墙上难为趋府》诗句“门有车马客，骖服若腾飞”，象征着在时代发展的大潮中迅速崛起和发展，孕育着飞跃进步的雄心和希望
             </p>
             * financialServeId : 1
             * id : 1
             * isShow : true
             * sort : 1
             * title : 企业简介
             */

            private String content;
            private int financialServeId;
            private int id;
            private boolean isShow;
            private int sort;
            private String title;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getFinancialServeId() {
                return financialServeId;
            }

            public void setFinancialServeId(int financialServeId) {
                this.financialServeId = financialServeId;
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
        }
    }
}
