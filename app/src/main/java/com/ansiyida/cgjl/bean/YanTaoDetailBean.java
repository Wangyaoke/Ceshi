package com.ansiyida.cgjl.bean;

/**
 * Created by ansiyida on 2018/3/14.
 */
public class YanTaoDetailBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"question":{"jp_is_hot":1,"jp_status":"Y","jp_user_id":207,"jp_img":"","jp_title":"军民能到底要融什么？怎么融？","jp_is_open":null,"commnum":1,"answerCount":1,"jp_check":1,"jmi_username":"wqz","is_focus":null,"isCollection":"N","clickNum":null,"jac_browse":19,"jp_code":"1522150827341","jp_content":"这几年军民能融合成为了国家战略，到底要融什么？怎么融？个人可以做什么？企业可以做什么？","jp_atime":"2018-03-27 19:40:27","isClick":"N","jmi_img":"http://qzapp.qlogo.cn/qzapp/101452432/C0F7E5E13891269407711375F08F0134/100","is_del":"N","jp_type_id":3,"jp_strand_id":"军民融合","jp_recommend":"N","jp_id":101}}
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
         * question : {"jp_is_hot":1,"jp_status":"Y","jp_user_id":207,"jp_img":"","jp_title":"军民能到底要融什么？怎么融？","jp_is_open":null,"commnum":1,"answerCount":1,"jp_check":1,"jmi_username":"wqz","is_focus":null,"isCollection":"N","clickNum":null,"jac_browse":19,"jp_code":"1522150827341","jp_content":"这几年军民能融合成为了国家战略，到底要融什么？怎么融？个人可以做什么？企业可以做什么？","jp_atime":"2018-03-27 19:40:27","isClick":"N","jmi_img":"http://qzapp.qlogo.cn/qzapp/101452432/C0F7E5E13891269407711375F08F0134/100","is_del":"N","jp_type_id":3,"jp_strand_id":"军民融合","jp_recommend":"N","jp_id":101}
         */

        private QuestionBean question;

        public QuestionBean getQuestion() {
            return question;
        }

        public void setQuestion(QuestionBean question) {
            this.question = question;
        }

        public static class QuestionBean {
            /**
             * jp_is_hot : 1
             * jp_status : Y
             * jp_user_id : 207
             * jp_img :
             * jp_title : 军民能到底要融什么？怎么融？
             * jp_is_open : null
             * commnum : 1
             * answerCount : 1
             * jp_check : 1
             * jmi_username : wqz
             * is_focus : null
             * isCollection : N
             * clickNum : null
             * jac_browse : 19
             * jp_code : 1522150827341
             * jp_content : 这几年军民能融合成为了国家战略，到底要融什么？怎么融？个人可以做什么？企业可以做什么？
             * jp_atime : 2018-03-27 19:40:27
             * isClick : N
             * jmi_img : http://qzapp.qlogo.cn/qzapp/101452432/C0F7E5E13891269407711375F08F0134/100
             * is_del : N
             * jp_type_id : 3
             * jp_strand_id : 军民融合
             * jp_recommend : N
             * jp_id : 101
             */

            private int jp_is_hot;
            private String jp_status;
            private int jp_user_id;
            private String jp_img;
            private String jp_title;
            private Object jp_is_open;
            private int commnum;
            private int answerCount;
            private int jp_check;
            private String jmi_username;
            private Object is_focus;
            private String isCollection;
            private Object clickNum;
            private int jac_browse;
            private String jp_code;
            private String jp_content;
            private String jp_atime;
            private String isClick;
            private String jmi_img;
            private String is_del;
            private int jp_type_id;
            private String jp_strand_id;
            private String jp_recommend;
            private int jp_id;

            public int getJp_is_hot() {
                return jp_is_hot;
            }

            public void setJp_is_hot(int jp_is_hot) {
                this.jp_is_hot = jp_is_hot;
            }

            public String getJp_status() {
                return jp_status;
            }

            public void setJp_status(String jp_status) {
                this.jp_status = jp_status;
            }

            public int getJp_user_id() {
                return jp_user_id;
            }

            public void setJp_user_id(int jp_user_id) {
                this.jp_user_id = jp_user_id;
            }

            public String getJp_img() {
                return jp_img;
            }

            public void setJp_img(String jp_img) {
                this.jp_img = jp_img;
            }

            public String getJp_title() {
                return jp_title;
            }

            public void setJp_title(String jp_title) {
                this.jp_title = jp_title;
            }

            public Object getJp_is_open() {
                return jp_is_open;
            }

            public void setJp_is_open(Object jp_is_open) {
                this.jp_is_open = jp_is_open;
            }

            public int getCommnum() {
                return commnum;
            }

            public void setCommnum(int commnum) {
                this.commnum = commnum;
            }

            public int getAnswerCount() {
                return answerCount;
            }

            public void setAnswerCount(int answerCount) {
                this.answerCount = answerCount;
            }

            public int getJp_check() {
                return jp_check;
            }

            public void setJp_check(int jp_check) {
                this.jp_check = jp_check;
            }

            public String getJmi_username() {
                return jmi_username;
            }

            public void setJmi_username(String jmi_username) {
                this.jmi_username = jmi_username;
            }

            public Object getIs_focus() {
                return is_focus;
            }

            public void setIs_focus(Object is_focus) {
                this.is_focus = is_focus;
            }

            public String getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(String isCollection) {
                this.isCollection = isCollection;
            }

            public Object getClickNum() {
                return clickNum;
            }

            public void setClickNum(Object clickNum) {
                this.clickNum = clickNum;
            }

            public int getJac_browse() {
                return jac_browse;
            }

            public void setJac_browse(int jac_browse) {
                this.jac_browse = jac_browse;
            }

            public String getJp_code() {
                return jp_code;
            }

            public void setJp_code(String jp_code) {
                this.jp_code = jp_code;
            }

            public String getJp_content() {
                return jp_content;
            }

            public void setJp_content(String jp_content) {
                this.jp_content = jp_content;
            }

            public String getJp_atime() {
                return jp_atime;
            }

            public void setJp_atime(String jp_atime) {
                this.jp_atime = jp_atime;
            }

            public String getIsClick() {
                return isClick;
            }

            public void setIsClick(String isClick) {
                this.isClick = isClick;
            }

            public String getJmi_img() {
                return jmi_img;
            }

            public void setJmi_img(String jmi_img) {
                this.jmi_img = jmi_img;
            }

            public String getIs_del() {
                return is_del;
            }

            public void setIs_del(String is_del) {
                this.is_del = is_del;
            }

            public int getJp_type_id() {
                return jp_type_id;
            }

            public void setJp_type_id(int jp_type_id) {
                this.jp_type_id = jp_type_id;
            }

            public String getJp_strand_id() {
                return jp_strand_id;
            }

            public void setJp_strand_id(String jp_strand_id) {
                this.jp_strand_id = jp_strand_id;
            }

            public String getJp_recommend() {
                return jp_recommend;
            }

            public void setJp_recommend(String jp_recommend) {
                this.jp_recommend = jp_recommend;
            }

            public int getJp_id() {
                return jp_id;
            }

            public void setJp_id(int jp_id) {
                this.jp_id = jp_id;
            }
        }
    }
}
