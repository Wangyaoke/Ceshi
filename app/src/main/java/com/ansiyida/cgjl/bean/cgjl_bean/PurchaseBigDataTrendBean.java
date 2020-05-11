package com.ansiyida.cgjl.bean.cgjl_bean;

import java.io.Serializable;
import java.util.List;

public class PurchaseBigDataTrendBean implements Serializable {

    /**
     * data : [{"daily_data_volume":1933,"id":334,"publish_time":"2019-08-21"},{"daily_data_volume":2369,"id":222,"publish_time":"2019-08-20"},{"daily_data_volume":2620,"id":221,"publish_time":"2019-08-19"},{"daily_data_volume":800,"id":220,"publish_time":"2019-08-18"},{"daily_data_volume":308,"id":219,"publish_time":"2019-08-17"},{"daily_data_volume":2290,"id":218,"publish_time":"2019-08-16"},{"daily_data_volume":2645,"id":217,"publish_time":"2019-08-15"},{"daily_data_volume":2756,"id":216,"publish_time":"2019-08-14"},{"daily_data_volume":2570,"id":215,"publish_time":"2019-08-13"},{"daily_data_volume":2469,"id":214,"publish_time":"2019-08-12"},{"daily_data_volume":1012,"id":213,"publish_time":"2019-08-11"},{"daily_data_volume":273,"id":212,"publish_time":"2019-08-10"},{"daily_data_volume":2230,"id":211,"publish_time":"2019-08-09"},{"daily_data_volume":2642,"id":210,"publish_time":"2019-08-08"}]
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

    public static class DataBean  implements Serializable {
        /**
         * daily_data_volume : 1933
         * id : 334
         * publish_time : 2019-08-21
         */

        private int daily_data_volume;
        private int id;
        private String publish_time;

        public int getDaily_data_volume() {
            return daily_data_volume;
        }

        public void setDaily_data_volume(int daily_data_volume) {
            this.daily_data_volume = daily_data_volume;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPublish_time() {
            return publish_time;
        }

        public void setPublish_time(String publish_time) {
            this.publish_time = publish_time;
        }
    }
}
