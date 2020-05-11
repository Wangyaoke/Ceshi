package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class AdvertisementBean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"clickUrl":null,"itemUrl":"http://item.taobao.com/item.htm?id=570402570277","nick":"东方书苑图书专营店","numIid":570402570277,"pictUrl":"https://img.alicdn.com/tfscom/i2/2384466945/TB2jsgOkRyWBuNkSmFPXXXguVXa_!!2384466945-0-item_pic.jpg","provcity":"北京","reservePrice":"15.00","sellerId":2384466945,"shopTitle":null,"smallImages":null,"title":"ARMS军事装备杂志2010年10月第5期 日耳曼利刃 军事类期刊","tkRate":null,"userType":1,"volume":0,"zkFinalPrice":"15.00","zkFinalPriceWap":null}]
     */

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

    @Override
    public String toString() {
        return "AdvertisementBean{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * clickUrl : null
         * itemUrl : http://item.taobao.com/item.htm?id=570402570277
         * nick : 东方书苑图书专营店
         * numIid : 570402570277
         * pictUrl : https://img.alicdn.com/tfscom/i2/2384466945/TB2jsgOkRyWBuNkSmFPXXXguVXa_!!2384466945-0-item_pic.jpg
         * provcity : 北京
         * reservePrice : 15.00
         * sellerId : 2384466945
         * shopTitle : null
         * smallImages : null
         * title : ARMS军事装备杂志2010年10月第5期 日耳曼利刃 军事类期刊
         * tkRate : null
         * userType : 1
         * volume : 0
         * zkFinalPrice : 15.00
         * zkFinalPriceWap : null
         */

        private Object clickUrl;
        private String itemUrl;
        private String nick;
        private long numIid;
        private String pictUrl;
        private String provcity;
        private String reservePrice;
        private long sellerId;
        private Object shopTitle;
        private Object smallImages;
        private String title;
        private Object tkRate;
        private int userType;
        private int volume;
        private String zkFinalPrice;
        private Object zkFinalPriceWap;

        public Object getClickUrl() {
            return clickUrl;
        }

        public void setClickUrl(Object clickUrl) {
            this.clickUrl = clickUrl;
        }

        public String getItemUrl() {
            return itemUrl;
        }

        public void setItemUrl(String itemUrl) {
            this.itemUrl = itemUrl;
        }

        public String getNick() {
            return nick;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public long getNumIid() {
            return numIid;
        }

        public void setNumIid(long numIid) {
            this.numIid = numIid;
        }

        public String getPictUrl() {
            return pictUrl;
        }

        public void setPictUrl(String pictUrl) {
            this.pictUrl = pictUrl;
        }

        public String getProvcity() {
            return provcity;
        }

        public void setProvcity(String provcity) {
            this.provcity = provcity;
        }

        public String getReservePrice() {
            return reservePrice;
        }

        public void setReservePrice(String reservePrice) {
            this.reservePrice = reservePrice;
        }

        public long getSellerId() {
            return sellerId;
        }

        public void setSellerId(long sellerId) {
            this.sellerId = sellerId;
        }

        public Object getShopTitle() {
            return shopTitle;
        }

        public void setShopTitle(Object shopTitle) {
            this.shopTitle = shopTitle;
        }

        public Object getSmallImages() {
            return smallImages;
        }

        public void setSmallImages(Object smallImages) {
            this.smallImages = smallImages;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Object getTkRate() {
            return tkRate;
        }

        public void setTkRate(Object tkRate) {
            this.tkRate = tkRate;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public int getVolume() {
            return volume;
        }

        public void setVolume(int volume) {
            this.volume = volume;
        }

        public String getZkFinalPrice() {
            return zkFinalPrice;
        }

        public void setZkFinalPrice(String zkFinalPrice) {
            this.zkFinalPrice = zkFinalPrice;
        }

        public Object getZkFinalPriceWap() {
            return zkFinalPriceWap;
        }

        public void setZkFinalPriceWap(Object zkFinalPriceWap) {
            this.zkFinalPriceWap = zkFinalPriceWap;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "clickUrl=" + clickUrl +
                    ", itemUrl='" + itemUrl + '\'' +
                    ", nick='" + nick + '\'' +
                    ", numIid=" + numIid +
                    ", pictUrl='" + pictUrl + '\'' +
                    ", provcity='" + provcity + '\'' +
                    ", reservePrice='" + reservePrice + '\'' +
                    ", sellerId=" + sellerId +
                    ", shopTitle=" + shopTitle +
                    ", smallImages=" + smallImages +
                    ", title='" + title + '\'' +
                    ", tkRate=" + tkRate +
                    ", userType=" + userType +
                    ", volume=" + volume +
                    ", zkFinalPrice='" + zkFinalPrice + '\'' +
                    ", zkFinalPriceWap=" + zkFinalPriceWap +
                    '}';
        }
    }
}
