package com.ansiyida.cgjl.bean;

import java.util.List;

public class college_bean {

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

    @Override
    public String toString() {
        return "college_bean{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
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

        /* "content": "<p>财预〔2017〕87号</p>\n<p>各省、自治区、直辖市、计划单列市财政厅（局）：</p>\n<p>《国务院办公厅关于政府向社会力量购买服务的指导意见》（国办发〔2013〕96号）印发后，各地稳步推进政府购买服务工作，取得了良好成效。同时，一些地区存在违法违规扩大政府购买服务范围、超越管理权限延长购买服务期限等问题，加剧了财政金融风险。根据《中华人民共和国预算法》、《中华人民共和国政府采购法》、《国务院关于实行中期财政规划管理的意见》（国发〔2015〕3号）、国办发〔2013〕96号文件等规定，为规范政府购买服务管理，制止地方政府违法违规举债融资行为，防范化解财政金融风险，现就有关事项通知如下：</p>\n<p>一、坚持政府购买服务改革正确方向。推广政府购买服务是党的十八届三中全会决定明确的一项重要改革任务，有利于加快转变政府职能、改善公共服务供给、推进财政支出方式改革。政府购买服务所需资金应当在年度预算和中期财政规划中据实足额安排。实施政府购买服务改革，要坚持费随事转，注重与事业单位改革、行业协会商会与行政主管部门脱钩转制改革、支持社会组织培育发展等政策相衔接，带动和促进政事分开、政社分开。地方政府及其所属部门要始终准确把握并牢固坚持政府购买服务改革的正确方向，依法依规、积极稳妥地加以推进。</p>\n<p>二、严格按照规定范围实施政府购买服务。政府购买服务内容应当严格限制在属于政府职责范围、适合采取市场化方式提供、社会力量能够承担的服务事项，重点是有预算安排的基本公共服务项目。科学制定并适时完善分级分部门政府购买服务指导性目录，增强指导性目录的约束力。对暂时未纳入指导性目录又确需购买的服务事项，应当报财政部门审核备案后调整实施。</p>\n<p>严格按照《中华人民共和国政府采购法》确定的服务范围实施政府购买服务，不得将原材料、燃料、设备、产品等货物，以及建筑物和构筑物的新建、改建、扩建及其相关的装修、拆除、修缮等建设工程作为政府购买服务项目。严禁将铁路、公路、机场、通讯、水电煤气，以及教育、科技、医疗卫生、文化、体育等领域的基础设施建设，储备土地前期开发，农田水利等建设工程作为政府购买服务项目。严禁将建设工程与服务打包作为政府购买服务项目。严禁将金融机构、融资租赁公司等非金融机构提供的融资行为纳入政府购买服务范围。政府建设工程项目确需使用财政资金，应当依照《中华人民共和国政府采购法》及其实施条例、《中华人民共和国招标投标法》规范实施。</p>\n<p>三、严格规范政府购买服务预算管理。政府购买服务要坚持先有预算、后购买服务，所需资金应当在既有年度预算中统筹考虑，不得把政府购买服务作为增加预算单位财政支出的依据。地方各级财政部门应当充分考虑实际财力水平，妥善做好政府购买服务支出与年度预算、中期财政规划的衔接，足额安排资金，保障服务承接主体合法权益。年度预算未安排资金的，不得实施政府购买服务。购买主体应当按照批准的预算执行，从部门预算经费或经批准的专项资金等既有年度预算中统筹安排购买服务资金。购买主体签订购买服务合同，应当确认涉及的财政支出已在年度预算和中期财政规划中安排。政府购买服务期限应严格限定在年度预算和中期财政规划期限内。党中央、国务院统一部署的棚户区改造、易地扶贫搬迁工作中涉及的政府购买服务事项，按照相关规定执行。</p>\n<p>四、严禁利用或虚构政府购买服务合同违法违规融资。金融机构涉及政府购买服务的融资审查，必须符合政府预算管理制度相关要求，做到依法合规。承接主体利用政府购买服务合同向金融机构融资时，应当配合金融机构做好合规性管理，相关合同在购买内容和期限等方面必须符合政府购买服务有关法律和制度规定。地方政府及其部门不得利用或虚构政府购买服务合同为建设工程变相举债，不得通过政府购买服务向金融机构、融资租赁公司等非金融机构进行融资，不得以任何方式虚构或超越权限签订应付（收）账款合同帮助融资平台公司等企业融资。</p>\n<p>五、切实做好政府购买服务信息公开。各地应当将年度预算中政府购买服务总金额、纳入中期财政规划的政府购买服务总金额以及政府购买服务项目有关预算信息，按规定及时向社会公开，提高预算透明度。购买主体应当依法在中国政府采购网及其地方分网及时公开政府购买服务项目相关信息，包括政府购买服务内容、购买方式、承接主体、合同金额、分年财政资金安排、合同期限、绩效评价等，确保政府购买服务项目信息真实准确，可查询、可追溯。坚决防止借政府购买服务名义进行利益输送等违法违规行为。</p>\n<p>各省级财政部门要充分认识规范政府购买服务管理、防范财政金融风险的重要性，统一思想，加强领导，周密部署，报经省级政府批准后，会同相关部门组织全面摸底排查本地区政府购买服务情况，发现违法违规问题的，督促相关地区和单位限期依法依规整改到位，并将排查和整改结果于2017年10月底前报送财政部。</p>\n<p>特此通知。</p>\n<p>财  政  部</p>\n<p>2017年5月28日</p>\n",
        "createTime": 1543368538000,
        "id": 1,
        "isCollection": false,
        "isDelete": false,
        "issuedNumber": null,
        "publishCompany": null,
        "publishTime": null,
        "startTime": null,
        "status": true,
        "title": "关于坚决制止地方以政府购买服务名义违法违规融资的通知_中国政府采购网",
        "typeId": 3
        "address":"厦门市湖滨南路81号光大银行大厦21层，电话：0592-230888"
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

        "endRow": 20,
    "hasNextPage": true,
    "hasPreviousPage": false,
    "isFirstPage": true,
    "isLastPage": false,
    "list":
     "navigateFirstPage": 1,
    "navigateLastPage": 8,
    "navigatePages": 8,
    "navigatepageNums": [
      1,
      2,
      3,
      4,
      5,
      6,
      7,
      8
    ],
    "nextPage": 2,
    "pageNum": 1,
    "pageSize": 20,
    "pages": 10,
    "prePage": 0,
    "size": 20,
    "startRow": 1,
    "total": 188
      "endRow": 20,
    "hasNextPage": true,
    "hasPreviousPage": false,
    "isFirstPage": true,
    "isLastPage": false,*/
        private String nextPage;
        private String pageNum;
        private String pageSize;
        private String pages;
        private String prePage;
        private String size;
        private String startRow;
        private String total;
        private List<list_law_bean> list;
        private String navigateFirstPage;
        private String navigateLastPage;
        private String navigatePages;
        private List<String> navigatepageNums;
        private String endRow;
        private String hasNextPage;
        private String hasPreviousPage;
        private String isFirstPage;
        private String isLastPage;
        public List<list_law_bean> getlist_law_bean() {
            return list;
        }

        public void setlist_law_bean(List<list_law_bean> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "nextPage='" + nextPage + '\'' +
                    ", pageNum='" + pageNum + '\'' +
                    ", pageSize='" + pageSize + '\'' +
                    ", pages='" + pages + '\'' +
                    ", prePage='" + prePage + '\'' +
                    ", size='" + size + '\'' +
                    ", startRow='" + startRow + '\'' +
                    ", total='" + total + '\'' +
                    ", list=" + list +
                    ", navigateFirstPage='" + navigateFirstPage + '\'' +
                    ", navigateLastPage='" + navigateLastPage + '\'' +
                    ", navigatePages='" + navigatePages + '\'' +
                    ", navigatepageNums=" + navigatepageNums +
                    ", endRow='" + endRow + '\'' +
                    ", hasNextPage='" + hasNextPage + '\'' +
                    ", hasPreviousPage='" + hasPreviousPage + '\'' +
                    ", isFirstPage='" + isFirstPage + '\'' +
                    ", isLastPage='" + isLastPage + '\'' +
                    '}';
        }

        public static class list_law_bean
    {
          /* "content": "<p>财预〔2017〕87号</p>\n<p>各省、自治区、直辖市、计划单列市财政厅（局）：</p>\n<p>《国务院办公厅关于政府向社会力量购买服务的指导意见》（国办发〔2013〕96号）印发后，各地稳步推进政府购买服务工作，取得了良好成效。同时，一些地区存在违法违规扩大政府购买服务范围、超越管理权限延长购买服务期限等问题，加剧了财政金融风险。根据《中华人民共和国预算法》、《中华人民共和国政府采购法》、《国务院关于实行中期财政规划管理的意见》（国发〔2015〕3号）、国办发〔2013〕96号文件等规定，为规范政府购买服务管理，制止地方政府违法违规举债融资行为，防范化解财政金融风险，现就有关事项通知如下：</p>\n<p>一、坚持政府购买服务改革正确方向。推广政府购买服务是党的十八届三中全会决定明确的一项重要改革任务，有利于加快转变政府职能、改善公共服务供给、推进财政支出方式改革。政府购买服务所需资金应当在年度预算和中期财政规划中据实足额安排。实施政府购买服务改革，要坚持费随事转，注重与事业单位改革、行业协会商会与行政主管部门脱钩转制改革、支持社会组织培育发展等政策相衔接，带动和促进政事分开、政社分开。地方政府及其所属部门要始终准确把握并牢固坚持政府购买服务改革的正确方向，依法依规、积极稳妥地加以推进。</p>\n<p>二、严格按照规定范围实施政府购买服务。政府购买服务内容应当严格限制在属于政府职责范围、适合采取市场化方式提供、社会力量能够承担的服务事项，重点是有预算安排的基本公共服务项目。科学制定并适时完善分级分部门政府购买服务指导性目录，增强指导性目录的约束力。对暂时未纳入指导性目录又确需购买的服务事项，应当报财政部门审核备案后调整实施。</p>\n<p>严格按照《中华人民共和国政府采购法》确定的服务范围实施政府购买服务，不得将原材料、燃料、设备、产品等货物，以及建筑物和构筑物的新建、改建、扩建及其相关的装修、拆除、修缮等建设工程作为政府购买服务项目。严禁将铁路、公路、机场、通讯、水电煤气，以及教育、科技、医疗卫生、文化、体育等领域的基础设施建设，储备土地前期开发，农田水利等建设工程作为政府购买服务项目。严禁将建设工程与服务打包作为政府购买服务项目。严禁将金融机构、融资租赁公司等非金融机构提供的融资行为纳入政府购买服务范围。政府建设工程项目确需使用财政资金，应当依照《中华人民共和国政府采购法》及其实施条例、《中华人民共和国招标投标法》规范实施。</p>\n<p>三、严格规范政府购买服务预算管理。政府购买服务要坚持先有预算、后购买服务，所需资金应当在既有年度预算中统筹考虑，不得把政府购买服务作为增加预算单位财政支出的依据。地方各级财政部门应当充分考虑实际财力水平，妥善做好政府购买服务支出与年度预算、中期财政规划的衔接，足额安排资金，保障服务承接主体合法权益。年度预算未安排资金的，不得实施政府购买服务。购买主体应当按照批准的预算执行，从部门预算经费或经批准的专项资金等既有年度预算中统筹安排购买服务资金。购买主体签订购买服务合同，应当确认涉及的财政支出已在年度预算和中期财政规划中安排。政府购买服务期限应严格限定在年度预算和中期财政规划期限内。党中央、国务院统一部署的棚户区改造、易地扶贫搬迁工作中涉及的政府购买服务事项，按照相关规定执行。</p>\n<p>四、严禁利用或虚构政府购买服务合同违法违规融资。金融机构涉及政府购买服务的融资审查，必须符合政府预算管理制度相关要求，做到依法合规。承接主体利用政府购买服务合同向金融机构融资时，应当配合金融机构做好合规性管理，相关合同在购买内容和期限等方面必须符合政府购买服务有关法律和制度规定。地方政府及其部门不得利用或虚构政府购买服务合同为建设工程变相举债，不得通过政府购买服务向金融机构、融资租赁公司等非金融机构进行融资，不得以任何方式虚构或超越权限签订应付（收）账款合同帮助融资平台公司等企业融资。</p>\n<p>五、切实做好政府购买服务信息公开。各地应当将年度预算中政府购买服务总金额、纳入中期财政规划的政府购买服务总金额以及政府购买服务项目有关预算信息，按规定及时向社会公开，提高预算透明度。购买主体应当依法在中国政府采购网及其地方分网及时公开政府购买服务项目相关信息，包括政府购买服务内容、购买方式、承接主体、合同金额、分年财政资金安排、合同期限、绩效评价等，确保政府购买服务项目信息真实准确，可查询、可追溯。坚决防止借政府购买服务名义进行利益输送等违法违规行为。</p>\n<p>各省级财政部门要充分认识规范政府购买服务管理、防范财政金融风险的重要性，统一思想，加强领导，周密部署，报经省级政府批准后，会同相关部门组织全面摸底排查本地区政府购买服务情况，发现违法违规问题的，督促相关地区和单位限期依法依规整改到位，并将排查和整改结果于2017年10月底前报送财政部。</p>\n<p>特此通知。</p>\n<p>财  政  部</p>\n<p>2017年5月28日</p>\n",
     //   "createTime": 1543368538000,
     //   "id": 1,
      //  "isCollection": false,
      //  "isDelete": false,
    //    "issuedNumber": null,
      //  "publishCompany": null,
        "publishTime": null,
      //  "startTime": null,
        "status": true,
        "title": "关于坚决制止地方以政府购买服务名义违法违规融资的通知_中国政府采购网",
        "typeId": 3

        */
        private String source;
        private String id;
        private String genre;
        private String createTime;
        private String infoId;
        private String province;
        private String userId;
        private String title;
        private String type;
        private long endTime;
        private String publishTime;
        private String secretDegree;
        private boolean isWarn;
        public boolean getisWarn() {
            return isWarn;
        }

        public void setisWarn(boolean isWarn) {
            this.isWarn = isWarn;
        }
        public long getendTime() {
            return endTime;
        }

        public void setendTime(long endTime) {
            this.endTime = endTime;
        }
        public String getsecretDegree() {
            return secretDegree;
        }

        public void setsecretDegree(String secretDegree) {
            this.secretDegree = secretDegree;
        }



        public String getgenre() {
            return genre;
        }

        public void setgenre(String genre) {
            this.genre = genre;
        }

        public String getsource() {
            return source;
        }

        public void setsource(String source) {
            this.source = source;
        }

        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }

        public String getinfoId() {
            return infoId;
        }

        public void setinfoId(String infoId) {
            this.infoId = infoId;
        }

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getprovince() {
            return province;
        }

        public void setprovince(String province) {
            this.province = province;
        }

        public String getuserId() {
            return userId;
        }

        public void setuserId(String userId) {
            this.userId = userId;
        }

        public String gettype() {
            return type;
        }

        public void settype(String startTime) {
            this.type = startTime;
        }

        public String gettitle() {
            return title;
        }

        public void settitle(String jai_tpo) {
            this.title = title;
        }


        public String getpublishTime() {
            return publishTime;
        }

        public void setpublishTime(String publishTime) {
            this.publishTime = publishTime;
        }

        @Override
        public String toString() {
            return "list_law_bean{" +
                    "source='" + source + '\'' +
                    ", id='" + id + '\'' +
                    ", genre='" + genre + '\'' +
                    ", createTime='" + createTime + '\'' +
                    ", infoId='" + infoId + '\'' +
                    ", province='" + province + '\'' +
                    ", userId='" + userId + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", endTime=" + endTime +
                    ", publishTime='" + publishTime + '\'' +
                    ", secretDegree='" + secretDegree + '\'' +
                    ", isWarn=" + isWarn +
                    '}';
        }
    }

    }
}
