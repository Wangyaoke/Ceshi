package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/9.
 */
public class MessageBean {


    /**
     * status : 0001
     * message : 成功
     * data : {"haveMore":1,"pager":{"total":19,"pageSize":10,"pageCount":2,"page":1,"begin":0,"haveMore":1},"id":156,"jmm_receive":156,"page":1,"list":[{"jmm_id":768,"jmm_receive":"156","jmi_datetime":"2018-04-08","jmm_content":"我们会及时添加网站链接","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":754,"jmm_receive":"156","jmi_datetime":"2018-04-08","jmm_content":"邀请您回答问题","jp_title":"什么是单独审查？什么是联合审查？","jp_id":182,"jmi_id":197,"jmi_username":"指尖逝去青春","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b3f25caf3fa04029945c42c86a5799d0.jpeg?Expires=4675717817&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=LV0GERcTTHK%2FGq2fdT8qbTe2AbQ%3D","answerNum":1},{"jmm_id":729,"jmm_receive":"156","jmi_datetime":"2018-04-03","jmm_content":"我们会及时添加网站链接","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":728,"jmm_receive":"156","jmi_datetime":"2018-04-03","jmm_content":"邀请您回答问题","jp_title":"文件审查的程序是什么？","jp_id":183,"jmi_id":197,"jmi_username":"指尖逝去青春","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b3f25caf3fa04029945c42c86a5799d0.jpeg?Expires=4675717817&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=LV0GERcTTHK%2FGq2fdT8qbTe2AbQ%3D","answerNum":2},{"jmm_id":691,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":689,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":688,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":687,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":686,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":685,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null}]}
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
         * haveMore : 1
         * pager : {"total":19,"pageSize":10,"pageCount":2,"page":1,"begin":0,"haveMore":1}
         * id : 156
         * jmm_receive : 156
         * page : 1
         * list : [{"jmm_id":768,"jmm_receive":"156","jmi_datetime":"2018-04-08","jmm_content":"我们会及时添加网站链接","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":754,"jmm_receive":"156","jmi_datetime":"2018-04-08","jmm_content":"邀请您回答问题","jp_title":"什么是单独审查？什么是联合审查？","jp_id":182,"jmi_id":197,"jmi_username":"指尖逝去青春","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b3f25caf3fa04029945c42c86a5799d0.jpeg?Expires=4675717817&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=LV0GERcTTHK%2FGq2fdT8qbTe2AbQ%3D","answerNum":1},{"jmm_id":729,"jmm_receive":"156","jmi_datetime":"2018-04-03","jmm_content":"我们会及时添加网站链接","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":728,"jmm_receive":"156","jmi_datetime":"2018-04-03","jmm_content":"邀请您回答问题","jp_title":"文件审查的程序是什么？","jp_id":183,"jmi_id":197,"jmi_username":"指尖逝去青春","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b3f25caf3fa04029945c42c86a5799d0.jpeg?Expires=4675717817&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=LV0GERcTTHK%2FGq2fdT8qbTe2AbQ%3D","answerNum":2},{"jmm_id":691,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":689,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":688,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":687,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":686,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null},{"jmm_id":685,"jmm_receive":"156","jmi_datetime":"2018-03-29","jmm_content":"感谢您的举报","jp_title":null,"jp_id":null,"jmi_id":null,"jmi_username":null,"jmi_img":null,"answerNum":null}]
         */

        private int haveMore;
        private PagerBean pager;
        private int id;
        private int jmm_receive;
        private int page;
        private List<ListBean> list;

        public int getHaveMore() {
            return haveMore;
        }

        public void setHaveMore(int haveMore) {
            this.haveMore = haveMore;
        }

        public PagerBean getPager() {
            return pager;
        }

        public void setPager(PagerBean pager) {
            this.pager = pager;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getJmm_receive() {
            return jmm_receive;
        }

        public void setJmm_receive(int jmm_receive) {
            this.jmm_receive = jmm_receive;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagerBean {
            /**
             * total : 19
             * pageSize : 10
             * pageCount : 2
             * page : 1
             * begin : 0
             * haveMore : 1
             */

            private int total;
            private int pageSize;
            private int pageCount;
            private int page;
            private int begin;
            private int haveMore;

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getPageCount() {
                return pageCount;
            }

            public void setPageCount(int pageCount) {
                this.pageCount = pageCount;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public int getBegin() {
                return begin;
            }

            public void setBegin(int begin) {
                this.begin = begin;
            }

            public int getHaveMore() {
                return haveMore;
            }

            public void setHaveMore(int haveMore) {
                this.haveMore = haveMore;
            }
        }

        public static class ListBean {
            /**
             * jmm_id : 768
             * jmm_receive : 156
             * jmi_datetime : 2018-04-08
             * jmm_content : 我们会及时添加网站链接
             * jp_title : null
             * jp_id : null
             * jmi_id : null
             * jmi_username : null
             * jmi_img : null
             * answerNum : null
             */

            private int jmm_id;
            private String jmm_receive;
            private String jmi_datetime;
            private String jmm_content;
            private Object jp_title;
            private Object jp_id;
            private Object jmi_id;
            private Object jmi_username;
            private Object jmi_img;
            private Object answerNum;

            public int getJmm_id() {
                return jmm_id;
            }

            public void setJmm_id(int jmm_id) {
                this.jmm_id = jmm_id;
            }

            public String getJmm_receive() {
                return jmm_receive;
            }

            public void setJmm_receive(String jmm_receive) {
                this.jmm_receive = jmm_receive;
            }

            public String getJmi_datetime() {
                return jmi_datetime;
            }

            public void setJmi_datetime(String jmi_datetime) {
                this.jmi_datetime = jmi_datetime;
            }

            public String getJmm_content() {
                return jmm_content;
            }

            public void setJmm_content(String jmm_content) {
                this.jmm_content = jmm_content;
            }

            public Object getJp_title() {
                return jp_title;
            }

            public void setJp_title(Object jp_title) {
                this.jp_title = jp_title;
            }

            public Object getJp_id() {
                return jp_id;
            }

            public void setJp_id(Object jp_id) {
                this.jp_id = jp_id;
            }

            public Object getJmi_id() {
                return jmi_id;
            }

            public void setJmi_id(Object jmi_id) {
                this.jmi_id = jmi_id;
            }

            public Object getJmi_username() {
                return jmi_username;
            }

            public void setJmi_username(Object jmi_username) {
                this.jmi_username = jmi_username;
            }

            public Object getJmi_img() {
                return jmi_img;
            }

            public void setJmi_img(Object jmi_img) {
                this.jmi_img = jmi_img;
            }

            public Object getAnswerNum() {
                return answerNum;
            }

            public void setAnswerNum(Object answerNum) {
                this.answerNum = answerNum;
            }
        }
    }
}
