package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/3/16.
 */
public class FriendInfoBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"likeNum":4,"isLike":false,"userId":1,"userName":"明日","fansNum":6,"userDes":"","userImg":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8="}
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

    public static class DataBean {
        /**
         * likeNum : 4
         * isLike : false
         * userId : 1
         * userName : 明日
         * fansNum : 6
         * userDes :
         * userImg : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=
         */

        private int likeNum;
        private Object isLike;
        private int userId;
        private String userName;
        private int fansNum;
        private String userDes;
        private String userImg;

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public Object isIsLike() {
            return isLike;
        }

        public void setIsLike(Object isLike) {
            this.isLike = isLike;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
            this.fansNum = fansNum;
        }

        public String getUserDes() {
            return userDes;
        }

        public void setUserDes(String userDes) {
            this.userDes = userDes;
        }

        public String getUserImg() {
            return userImg;
        }

        public void setUserImg(String userImg) {
            this.userImg = userImg;
        }
    }
}
