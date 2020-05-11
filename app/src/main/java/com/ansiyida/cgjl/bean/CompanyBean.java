package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/7.
 */
public class CompanyBean {



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

        /*   "businessInfo": null,
      "businessKeyword": "测试、测控",
      "businessSummary": "电子仪器设备研制、生产和维护各环节所需的测试、试验、评估和诊断手段的技术研发",
      "companyName": "哈尔滨诺信科技有限公司",
      "companySummary": "公司简介：哈尔滨诺信科技有限公司，成立于2013年12月23日，公司依托于哈尔滨工业大学自动化测试与控制研究所雄厚的技术研发背景，形成了“政产学研”的商业架构。\n公司定位：国内领先、国际先进的航空航天自动测试技术及装备的研制、生产及技术服务。\n服务宗旨：守诺言，讲诚信，科学创新，技艺超群\n企业愿景：公司结合自动测试技术发展趋势以及“中国制造2025”发展政策，围绕航空航天装备、先进交通设备及新一代信息产业化，开展具有自主知识产品的小型化、智能化、高可靠、低成本的自动测试系统和测试信息处理系统的研制工作。",
      "corporation": "李磊",
      "createTime": 1544006306000,
      "id": "5001",
      "isDelete": false,
      "majorField": "制导与控制技术、电子信息",
      "status": true,
      "tradeType": "仪器仪表制造业",
      "website": ""*/
        private String businessInfo;
        private String businessKeyword;
        private String id;
        private String isDelete;
        private String businessSummary;
        private String majorField;
        private String corporation;
        private String companySummary;
        private String createTime;
        private String website;
        private String status;
        private String companyName;
        private String tradeType;


        public String getcompanyName() {
            return companyName;
        }

        public void setcompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getbusinessInfo() {
            return businessInfo;
        }

        public void setbusinessInfo(String businessInfo) {
            this.businessInfo = businessInfo;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getbusinessKeyword() {
            return businessKeyword;
        }

        public void setbusinessKeyword(String img) {
            this.businessKeyword = businessKeyword;
        }

        public String getmajorField() {
            return majorField;
        }

        public void setmajorField(String majorField) {
            this.majorField = majorField;
        }

        public String getbusinessSummary() {
            return businessSummary;
        }

        public void setbusinessSummary(String businessSummary) {
            this.businessSummary = businessSummary;
        }
        public String getcorporation() {
            return corporation;
        }

        public void setcorporation(String corporation) {
            this.corporation = corporation;
        }
        public String getcompanySummary() {
            return companySummary;
        }

        public void setcompanySummary(String companySummary) {
            this.companySummary = companySummary;
        }
        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }
        public String getstatus() {
            return status;
        }

        public void setstatus(String status) {
            this.status = status;
        }
        public String gettradeType() {
            return tradeType;
        }

        public void settradeType(String tradeType) {
            this.tradeType = tradeType;
        }
        public String getwebsite() {
            return website;
        }

        public void setwebsite(String website) {
            this.website = website;
        }
        public String getisDelete() {
            return isDelete;
        }

        public void setisDelete(String isDelete) {
            this.isDelete = isDelete;
        }

    }
}
