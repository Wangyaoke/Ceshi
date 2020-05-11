package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class ProjectListBean {

    /**
     * data : [{"article":"江苏大洲工程项目管理有限公司呼吸机（BSZC2019-J1-11003-JSDZ）竞争性谈判公告","id":2,"isShow":true,"source":"中国政府采购网","userId":773},{"article":"深圳先进技术研究院磁控溅射薄膜沉积系统采购项目公开招标公告","id":7,"isShow":true,"source":"中国政府采购网","userId":773},{"article":"北京市监狱管理局清河分局柳林监狱柳林监狱监狱围墙鼓包脱落修复工程公开招标公告","id":8,"isShow":true,"source":"中国政府采购网","userId":773},{"article":"仪器设备购置\u2014\u2014背包式移动三维激光扫描系统公开招标公告","id":9,"isShow":true,"source":"中国政府采购网","userId":773},{"article":"生产调度指挥中心改造项目公告","id":10,"isShow":true,"source":"全军武器装备采购网","userId":773}]
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
        return "ProjectListBean{" +
                "message='" + message + '\'' +
                ", status=" + status +
                ", data=" + data +
                '}';
    }

    public static class DataBean {
        /**
         * article : 江苏大洲工程项目管理有限公司呼吸机（BSZC2019-J1-11003-JSDZ）竞争性谈判公告
         * id : 2
         * isShow : true
         * source : 中国政府采购网
         * userId : 773
         */
        private String article;
        private int id;
        private boolean isShow;
        private String source;
        private int userId;

        public String getArticle() {
            return article;
        }

        public void setArticle(String article) {
            this.article = article;
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

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "article='" + article + '\'' +
                    ", id=" + id +
                    ", isShow=" + isShow +
                    ", source='" + source + '\'' +
                    ", userId=" + userId +
                    '}';
        }
    }
}
