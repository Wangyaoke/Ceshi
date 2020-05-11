package com.ansiyida.cgjl.bean.cgjl_bean;

import java.io.Serializable;
import java.util.List;

public class EnterpriseDataBean implements Serializable {

    /**
     * data : [{"id":1,"provinceName":"广东省","provinceNums":2102},{"id":2,"provinceName":"山东省","provinceNums":1815},{"id":3,"provinceName":"广西壮族自治区","provinceNums":1251},{"id":4,"provinceName":"河南省","provinceNums":1174},{"id":5,"provinceName":"北京市","provinceNums":1025},{"id":6,"provinceName":"福建省","provinceNums":748},{"id":7,"provinceName":"云南省","provinceNums":572},{"id":8,"provinceName":"安徽省","provinceNums":452},{"id":9,"provinceName":"江苏省","provinceNums":405},{"id":10,"provinceName":"甘肃省","provinceNums":401},{"id":11,"provinceName":"上海市","provinceNums":385},{"id":12,"provinceName":"江西省","provinceNums":375},{"id":13,"provinceName":"陕西省","provinceNums":373},{"id":14,"provinceName":"四川省","provinceNums":286},{"id":15,"provinceName":"吉林省","provinceNums":273},{"id":16,"provinceName":"辽宁省","provinceNums":266},{"id":17,"provinceName":"海南省","provinceNums":258},{"id":18,"provinceName":"浙江省","provinceNums":255},{"id":19,"provinceName":"黑龙江省","provinceNums":252},{"id":20,"provinceName":"贵州省","provinceNums":232},{"id":21,"provinceName":"河北省","provinceNums":217},{"id":22,"provinceName":"重庆市","provinceNums":216},{"id":23,"provinceName":"湖北省","provinceNums":199},{"id":24,"provinceName":"宁夏回族自治区","provinceNums":186},{"id":25,"provinceName":"青海省","provinceNums":168},{"id":26,"provinceName":"湖南省","provinceNums":162},{"id":27,"provinceName":"内蒙古自治区","provinceNums":104},{"id":28,"provinceName":"天津市","provinceNums":92},{"id":29,"provinceName":"山西省","provinceNums":60},{"id":30,"provinceName":"新疆维吾尔自治区","provinceNums":17},{"id":31,"provinceName":"西藏自治区","provinceNums":13},{"id":32,"provinceName":"香港特别行政区","provinceNums":6},{"id":33,"provinceName":"台湾省","provinceNums":5}]
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
        return "EnterpriseDataBean{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Serializable{
        /**
         * id : 1
         * provinceName : 广东省
         * provinceNums : 2102
         */

        private int id;
        private String provinceName;
        private int provinceNums;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public int getProvinceNums() {
            return provinceNums;
        }

        public void setProvinceNums(int provinceNums) {
            this.provinceNums = provinceNums;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", provinceName='" + provinceName + '\'' +
                    ", provinceNums=" + provinceNums +
                    '}';
        }
    }
}
