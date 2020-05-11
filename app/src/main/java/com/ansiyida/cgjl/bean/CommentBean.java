package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/2/28.
 */
public class CommentBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"content":[{"comment_id":4,"jc_type":"A","jc_id":731,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:25","jc_cont":"http://47.74.147.41:8081/","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"2分钟前","auditstatus":"Y","is_del":"N","user_id":1},{"comment_id":4,"jc_type":"A","jc_id":730,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:23","jc_cont":"http://47.74.147.41:8081/","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"2分钟前","auditstatus":"Y","is_del":"N","user_id":1},{"comment_id":4,"jc_type":"A","jc_id":729,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:19","jc_cont":"你好","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"2分钟前","auditstatus":"Y","is_del":"N","user_id":1},{"comment_id":4,"jc_type":"A","jc_id":728,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:12","jc_cont":"hello\n","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"3分钟前","auditstatus":"Y","is_del":"N","user_id":1},{"comment_id":4,"jc_type":"A","jc_id":727,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:06","jc_cont":"非常好","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"3分钟前","auditstatus":"Y","is_del":"N","user_id":1}],"last":true,"totalElements":5,"totalPages":1,"sort":null,"first":true,"numberOfElements":5,"size":20,"number":0}
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
         * content : [{"comment_id":4,"jc_type":"A","jc_id":731,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:25","jc_cont":"http://47.74.147.41:8081/","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"2分钟前","auditstatus":"Y","is_del":"N","user_id":1},{"comment_id":4,"jc_type":"A","jc_id":730,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:23","jc_cont":"http://47.74.147.41:8081/","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"2分钟前","auditstatus":"Y","is_del":"N","user_id":1},{"comment_id":4,"jc_type":"A","jc_id":729,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:19","jc_cont":"你好","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"2分钟前","auditstatus":"Y","is_del":"N","user_id":1},{"comment_id":4,"jc_type":"A","jc_id":728,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:12","jc_cont":"hello\n","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"3分钟前","auditstatus":"Y","is_del":"N","user_id":1},{"comment_id":4,"jc_type":"A","jc_id":727,"jc_status_show":"Y","jc_remark":null,"userid":1,"article_type":"T","jc_title":null,"jc_time":"2018-02-28 18:57:06","jc_cont":"非常好","jc_status":"Y","commnum":0,"greadnum":0,"userhead":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D","jc_p_id":0,"usernickname":"mrqb_DC26CA2W","dateformat":"3分钟前","auditstatus":"Y","is_del":"N","user_id":1}]
         * last : true
         * totalElements : 5
         * totalPages : 1
         * sort : null
         * first : true
         * numberOfElements : 5
         * size : 20
         * number : 0
         */

        private boolean last;
        private int totalElements;
        private int totalPages;
        private Object sort;
        private boolean first;
        private int numberOfElements;
        private int size;
        private int number;
        private List<ContentBean> content;

        public boolean isLast() {
            return last;
        }

        public void setLast(boolean last) {
            this.last = last;
        }

        public int getTotalElements() {
            return totalElements;
        }

        public void setTotalElements(int totalElements) {
            this.totalElements = totalElements;
        }

        public int getTotalPages() {
            return totalPages;
        }

        public void setTotalPages(int totalPages) {
            this.totalPages = totalPages;
        }

        public Object getSort() {
            return sort;
        }

        public void setSort(Object sort) {
            this.sort = sort;
        }

        public boolean isFirst() {
            return first;
        }

        public void setFirst(boolean first) {
            this.first = first;
        }

        public int getNumberOfElements() {
            return numberOfElements;
        }

        public void setNumberOfElements(int numberOfElements) {
            this.numberOfElements = numberOfElements;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }

        public List<ContentBean> getContent() {
            return content;
        }

        public void setContent(List<ContentBean> content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * comment_id : 4
             * jc_type : A
             * jc_id : 731
             * jc_status_show : Y
             * jc_remark : null
             * userid : 1
             * article_type : T
             * jc_title : null
             * jc_time : 2018-02-28 18:57:25
             * jc_cont : http://47.74.147.41:8081/
             * jc_status : Y
             * isGread:Y
             * commnum : 0
             * greadnum : 0
             * userhead : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/05dea52412cf479b83d885e0042bce37.png?Expires=1521272347&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pVZg7PwTA5hrMew%2F%2Bqj90vKY2%2Fs%3D
             * jc_p_id : 0
             * usernickname : mrqb_DC26CA2W
             * dateformat : 2分钟前
             * auditstatus : Y
             * is_del : N
             * user_id : 1
             */

            private int comment_id;
            private String jc_type;
            private int jc_id;
            private String jc_status_show;
            private Object jc_remark;
            private int userid;
            private String article_type;
            private Object jc_title;
            private String jc_time;
            private String jc_cont;
            private String jc_status;
            private int commnum;
            private int greadnum;
            private String userhead;
            private int jc_p_id;
            private String usernickname;
            private String dateformat;
            private String auditstatus;
            private String is_del;
            private int user_id;
            private String isGread;

            public String getIsGread() {
                return isGread;
            }

            public void setIsGread(String isGread) {
                this.isGread = isGread;
            }

            public int getComment_id() {
                return comment_id;
            }

            public void setComment_id(int comment_id) {
                this.comment_id = comment_id;
            }

            public String getJc_type() {
                return jc_type;
            }

            public void setJc_type(String jc_type) {
                this.jc_type = jc_type;
            }

            public int getJc_id() {
                return jc_id;
            }

            public void setJc_id(int jc_id) {
                this.jc_id = jc_id;
            }

            public String getJc_status_show() {
                return jc_status_show;
            }

            public void setJc_status_show(String jc_status_show) {
                this.jc_status_show = jc_status_show;
            }

            public Object getJc_remark() {
                return jc_remark;
            }

            public void setJc_remark(Object jc_remark) {
                this.jc_remark = jc_remark;
            }

            public int getUserid() {
                return userid;
            }

            public void setUserid(int userid) {
                this.userid = userid;
            }

            public String getArticle_type() {
                return article_type;
            }

            public void setArticle_type(String article_type) {
                this.article_type = article_type;
            }

            public Object getJc_title() {
                return jc_title;
            }

            public void setJc_title(Object jc_title) {
                this.jc_title = jc_title;
            }

            public String getJc_time() {
                return jc_time;
            }

            public void setJc_time(String jc_time) {
                this.jc_time = jc_time;
            }

            public String getJc_cont() {
                return jc_cont;
            }

            public void setJc_cont(String jc_cont) {
                this.jc_cont = jc_cont;
            }

            public String getJc_status() {
                return jc_status;
            }

            public void setJc_status(String jc_status) {
                this.jc_status = jc_status;
            }

            public int getCommnum() {
                return commnum;
            }

            public void setCommnum(int commnum) {
                this.commnum = commnum;
            }

            public int getGreadnum() {
                return greadnum;
            }

            public void setGreadnum(int greadnum) {
                this.greadnum = greadnum;
            }

            public String getUserhead() {
                return userhead;
            }

            public void setUserhead(String userhead) {
                this.userhead = userhead;
            }

            public int getJc_p_id() {
                return jc_p_id;
            }

            public void setJc_p_id(int jc_p_id) {
                this.jc_p_id = jc_p_id;
            }

            public String getUsernickname() {
                return usernickname;
            }

            public void setUsernickname(String usernickname) {
                this.usernickname = usernickname;
            }

            public String getDateformat() {
                return dateformat;
            }

            public void setDateformat(String dateformat) {
                this.dateformat = dateformat;
            }

            public String getAuditstatus() {
                return auditstatus;
            }

            public void setAuditstatus(String auditstatus) {
                this.auditstatus = auditstatus;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }
        }
    }
}
