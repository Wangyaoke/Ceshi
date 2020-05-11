package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class CheckRecordDetailBean {

    /**
     * data : {"checkRecord":[{"checkId":null,"content":"冰丝","createTime":1559186087000,"id":122,"infoId":5251,"infoType":"company","isFinish":false,"isShow":true,"parentId":0,"title":"提交","userId":773},{"checkId":null,"content":null,"createTime":1559186087000,"id":123,"infoId":null,"infoType":null,"isFinish":false,"isShow":true,"parentId":122,"title":"审核中","userId":null}]}
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

    @Override
    public String toString() {
        return "CheckRecordDetailBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public static class DataBean {
        private List<CheckRecordBean> checkRecord;

        public List<CheckRecordBean> getCheckRecord() {
            return checkRecord;
        }

        public void setCheckRecord(List<CheckRecordBean> checkRecord) {
            this.checkRecord = checkRecord;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "checkRecord=" + checkRecord +
                    '}';
        }

        public static class CheckRecordBean {
            /**
             * checkId : null
             * content : 冰丝
             * createTime : 1559186087000
             * id : 122
             * infoId : 5251
             * infoType : company
             * isFinish : false
             * isShow : true
             * parentId : 0
             * title : 提交
             * userId : 773
             */

            private Object checkId;
            private String content;
            private long createTime;
            private int id;
            private int infoId;
            private String infoType;
            private boolean isFinish;
            private boolean isShow;
            private int parentId;
            private String title;
            private int userId;

            public Object getCheckId() {
                return checkId;
            }

            public void setCheckId(Object checkId) {
                this.checkId = checkId;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

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

            public int getInfoId() {
                return infoId;
            }

            public void setInfoId(int infoId) {
                this.infoId = infoId;
            }

            public String getInfoType() {
                return infoType;
            }

            public void setInfoType(String infoType) {
                this.infoType = infoType;
            }

            public boolean isIsFinish() {
                return isFinish;
            }

            public void setIsFinish(boolean isFinish) {
                this.isFinish = isFinish;
            }

            public boolean isIsShow() {
                return isShow;
            }

            public void setIsShow(boolean isShow) {
                this.isShow = isShow;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            @Override
            public String toString() {
                return "CheckRecordBean{" +
                        "checkId=" + checkId +
                        ", content='" + content + '\'' +
                        ", createTime=" + createTime +
                        ", id=" + id +
                        ", infoId=" + infoId +
                        ", infoType='" + infoType + '\'' +
                        ", isFinish=" + isFinish +
                        ", isShow=" + isShow +
                        ", parentId=" + parentId +
                        ", title='" + title + '\'' +
                        ", userId=" + userId +
                        '}';
            }
        }
    }
}
