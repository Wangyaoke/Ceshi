package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class PurchaseWordCloudBean {

    /**
     * data : [{"cloud_img":null,"key_word":"服务采购","numbers":23595,"score":0.28918630731330663},{"cloud_img":null,"key_word":"专业设备","numbers":7531,"score":0.09230184701744065},{"cloud_img":null,"key_word":"会计","numbers":5705,"score":0.06992192766359034},{"cloud_img":null,"key_word":"工程咨询管理","numbers":5290,"score":0.06483558235589709},{"cloud_img":null,"key_word":"综合项目","numbers":5162,"score":0.06326678187545196},{"cloud_img":null,"key_word":"材料设备","numbers":5082,"score":0.062286281575173734},{"cloud_img":null,"key_word":"生产设备","numbers":4563,"score":0.0559252858771188},{"cloud_img":null,"key_word":"工程施工","numbers":3313,"score":0.04060496868527166},{"cloud_img":null,"key_word":"建筑工程","numbers":3039,"score":0.037246755156818766},{"cloud_img":null,"key_word":"审计","numbers":2788,"score":0.034170435464695864},{"cloud_img":null,"key_word":"行政办公","numbers":2187,"score":0.026804426958855754},{"cloud_img":null,"key_word":"维修","numbers":2086,"score":0.025566545329754508},{"cloud_img":null,"key_word":"文化","numbers":1950,"score":0.023899694819281538},{"cloud_img":null,"key_word":"监理咨询","numbers":1543,"score":0.01891139954161611},{"cloud_img":null,"key_word":"信息系统","numbers":1490,"score":0.018261818092681792},{"cloud_img":null,"key_word":"体育","numbers":1468,"score":0.01799218051010528},{"cloud_img":null,"key_word":"交通工程","numbers":1389,"score":0.017023936463580542},{"cloud_img":null,"key_word":"道路","numbers":1383,"score":0.016950398941059677},{"cloud_img":null,"key_word":"装修工程","numbers":1065,"score":0.013052910247453763},{"cloud_img":null,"key_word":"绿化","numbers":962,"score":0.01179051611084556}]
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
        return "PurchaseWordCloudBean{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * cloud_img : null
         * key_word : 服务采购
         * numbers : 23595
         * score : 0.28918630731330663
         */

        private Object cloud_img;
        private String key_word;
        private int numbers;
        private double score;
        private int color;

        public int getColor() {
            return color;
        }

        public void setColor(int color) {
            this.color = color;
        }

        public Object getCloud_img() {
            return cloud_img;
        }

        public void setCloud_img(Object cloud_img) {
            this.cloud_img = cloud_img;
        }

        public String getKey_word() {
            return key_word;
        }

        public void setKey_word(String key_word) {
            this.key_word = key_word;
        }

        public int getNumbers() {
            return numbers;
        }

        public void setNumbers(int numbers) {
            this.numbers = numbers;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "cloud_img=" + cloud_img +
                    ", key_word='" + key_word + '\'' +
                    ", numbers=" + numbers +
                    ", score=" + score +
                    '}';
        }
    }
}
