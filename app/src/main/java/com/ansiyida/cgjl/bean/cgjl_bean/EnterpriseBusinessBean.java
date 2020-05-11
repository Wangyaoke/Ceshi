package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class EnterpriseBusinessBean {

    /**
     * data : [{"businessName":"经营活动","businessNum":434,"id":1},{"businessName":"相关部门","businessNum":306,"id":2},{"businessName":"工程造价咨询","businessNum":174,"id":3},{"businessName":"工程招标代理","businessNum":101,"id":4},{"businessName":"计算机系统集成","businessNum":95,"id":5},{"businessName":"设备安装工程","businessNum":88,"id":6},{"businessName":"房屋租赁","businessNum":57,"id":7},{"businessName":"政府采购代理","businessNum":56,"id":8},{"businessName":"批发零售","businessNum":55,"id":9},{"businessName":"招标代理","businessNum":54,"id":10},{"businessName":"咨询服务","businessNum":50,"id":11},{"businessName":"房屋建筑工程","businessNum":49,"id":12},{"businessName":"软件开发","businessNum":49,"id":13},{"businessName":"建筑装修装饰","businessNum":48,"id":14},{"businessName":"建筑工程施工","businessNum":47,"id":15},{"businessName":"工程项目管理","businessNum":47,"id":16},{"businessName":"园林绿化工程","businessNum":47,"id":17},{"businessName":"技术开发技术","businessNum":41,"id":18},{"businessName":"设备电子产品","businessNum":40,"id":19},{"businessName":"物业管理","businessNum":37,"id":20}]
     * message : 成功
     * status : 200
     */

    private String message;
    private int status;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "EnterpriseBusinessBean{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * businessName : 经营活动
         * businessNum : 434
         * id : 1
         */

        private String businessName;
        private int businessNum;
        private int id;
        private int color;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }

        public int getBusinessNum() {
            return businessNum;
        }

        public void setBusinessNum(int businessNum) {
            this.businessNum = businessNum;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "businessName='" + businessName + '\'' +
                    ", businessNum=" + businessNum +
                    ", id=" + id +
                    '}';
        }
    }
}
