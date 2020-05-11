package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/13.
 */
public class YanTaoBean {

    /**
     * status : 0001
     * message : 成功
     * data : {"pager":{"total":15,"pageSize":20,"pageCount":2,"page":1,"begin":0,"haveMore":1},"pagesize":20,"userId":1,"list":[{"jmi_id":"","isThumbsUp":"N","jp_title":"asdfasdf","answerCount":"","jmi_username":"明日情报","isCollection":"Y","jp_content":"asdfsdfasf","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"15"},{"jmi_id":"","isThumbsUp":"N","jp_title":"aasdfasa","answerCount":"","jmi_username":"明日情报","isCollection":"Y","jp_content":"asdfdasfsadf","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"14"},{"jmi_id":"","isThumbsUp":"N","jp_title":"ceshitiwen","answerCount":"","jmi_username":"明日情报","isCollection":"Y","jp_content":"miaoshu","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"13"},{"jmi_id":"","isThumbsUp":"N","jp_title":"测试提问title","answerCount":"1","jmi_username":"明日情报","isCollection":"N","jp_content":"测试提问des","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"12"},{"jmi_id":"8","isThumbsUp":"N","jp_title":"达到精通到底有多难","answerCount":"1","jmi_username":"zhao","isCollection":"N","jp_content":"很难","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/9e34e8ebb93946a19361414fe0905cc0.png?Expires=1522385208&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=wQzAc9KtlL0rvzGsFFZO%2FbeGUak%3D","thumbus_count":0,"jp_type_id":9,"jp_id":"11"},{"jmi_id":"8","isThumbsUp":"N","jp_title":"1+1到底等于几","answerCount":"","jmi_username":"zhao","isCollection":"N","jp_content":"1+1到底等于几","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/9e34e8ebb93946a19361414fe0905cc0.png?Expires=1522385208&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=wQzAc9KtlL0rvzGsFFZO%2FbeGUak%3D","thumbus_count":0,"jp_type_id":20,"jp_id":"10"},{"jmi_id":"10","isThumbsUp":"N","jp_title":"请例举仓央嘉措的诗","answerCount":"1","jmi_username":"测试用户1","isCollection":"N","jp_content":"最好是流传最广的","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"9"},{"jmi_id":"9","isThumbsUp":"N","jp_title":"今年你有什么计划梦想要实现吗","answerCount":"1","jmi_username":"顺晓媒体","isCollection":"N","jp_content":"是不是想年薪百万\n我知道怎么能实现\n那就是做梦","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/ee638fec3e784b31ab518938844169e8.jpg?Expires=4674517829&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=ykjJPUzpX8cgb0jWLOJ2UE2UgOc%3D","thumbus_count":0,"jp_type_id":10,"jp_id":"8"},{"jmi_id":"9","isThumbsUp":"N","jp_title":"心情不美丽","answerCount":"3","jmi_username":"顺晓媒体","isCollection":"N","jp_content":"心情不美丽","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/ee638fec3e784b31ab518938844169e8.jpg?Expires=4674517829&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=ykjJPUzpX8cgb0jWLOJ2UE2UgOc%3D","thumbus_count":1,"jp_type_id":21,"jp_id":"7"},{"jmi_id":"9","isThumbsUp":"N","jp_title":"我的提问 2018","answerCount":"11","jmi_username":"顺晓媒体","isCollection":"N","jp_content":"123123123","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/ee638fec3e784b31ab518938844169e8.jpg?Expires=4674517829&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=ykjJPUzpX8cgb0jWLOJ2UE2UgOc%3D","thumbus_count":1,"jp_type_id":3,"jp_id":"6"},{"jmi_id":"","isThumbsUp":"N","jp_title":"明日研讨厅的提问","answerCount":"3","jmi_username":"明日情报","isCollection":"N","jp_content":"明日研讨厅的描述","jmi_img":"","thumbus_count":1,"jp_type_id":3,"jp_id":"5"},{"jmi_id":"","isThumbsUp":"N","jp_title":"天空为什么是蓝的，草为什么是绿的","answerCount":"3","jmi_username":"明日情报","isCollection":"N","jp_content":"因为光的折射原理一级植物色素吸收","jmi_img":"","thumbus_count":2,"jp_type_id":3,"jp_id":"4"},{"jmi_id":"","isThumbsUp":"N","jp_title":"测试","answerCount":"1","jmi_username":"明日情报","isCollection":"N","jp_content":"测试","jmi_img":"","thumbus_count":3,"jp_type_id":12,"jp_id":"3"},{"jmi_id":"","isThumbsUp":"N","jp_title":"提问","answerCount":"1","jmi_username":"明日情报","isCollection":"N","jp_content":"123","jmi_img":"","thumbus_count":2,"jp_type_id":3,"jp_id":"2"},{"jmi_id":"","isThumbsUp":"N","jp_title":"为什么人这么聪明","answerCount":"3","jmi_username":"明日情报","isCollection":"N","jp_content":"描述不能为空","jmi_img":"","thumbus_count":2,"jp_type_id":3,"jp_id":"1"}],"currentpage":1,"typeId":-1,"keyWord":null}
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
         * pager : {"total":15,"pageSize":20,"pageCount":2,"page":1,"begin":0,"haveMore":1}
         * pagesize : 20
         * userId : 1
         * list : [{"jmi_id":"","isThumbsUp":"N","jp_title":"asdfasdf","answerCount":"","jmi_username":"明日情报","isCollection":"Y","jp_content":"asdfsdfasf","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"15"},{"jmi_id":"","isThumbsUp":"N","jp_title":"aasdfasa","answerCount":"","jmi_username":"明日情报","isCollection":"Y","jp_content":"asdfdasfsadf","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"14"},{"jmi_id":"","isThumbsUp":"N","jp_title":"ceshitiwen","answerCount":"","jmi_username":"明日情报","isCollection":"Y","jp_content":"miaoshu","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"13"},{"jmi_id":"","isThumbsUp":"N","jp_title":"测试提问title","answerCount":"1","jmi_username":"明日情报","isCollection":"N","jp_content":"测试提问des","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"12"},{"jmi_id":"8","isThumbsUp":"N","jp_title":"达到精通到底有多难","answerCount":"1","jmi_username":"zhao","isCollection":"N","jp_content":"很难","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/9e34e8ebb93946a19361414fe0905cc0.png?Expires=1522385208&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=wQzAc9KtlL0rvzGsFFZO%2FbeGUak%3D","thumbus_count":0,"jp_type_id":9,"jp_id":"11"},{"jmi_id":"8","isThumbsUp":"N","jp_title":"1+1到底等于几","answerCount":"","jmi_username":"zhao","isCollection":"N","jp_content":"1+1到底等于几","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/9e34e8ebb93946a19361414fe0905cc0.png?Expires=1522385208&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=wQzAc9KtlL0rvzGsFFZO%2FbeGUak%3D","thumbus_count":0,"jp_type_id":20,"jp_id":"10"},{"jmi_id":"10","isThumbsUp":"N","jp_title":"请例举仓央嘉措的诗","answerCount":"1","jmi_username":"测试用户1","isCollection":"N","jp_content":"最好是流传最广的","jmi_img":"","thumbus_count":0,"jp_type_id":3,"jp_id":"9"},{"jmi_id":"9","isThumbsUp":"N","jp_title":"今年你有什么计划梦想要实现吗","answerCount":"1","jmi_username":"顺晓媒体","isCollection":"N","jp_content":"是不是想年薪百万\n我知道怎么能实现\n那就是做梦","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/ee638fec3e784b31ab518938844169e8.jpg?Expires=4674517829&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=ykjJPUzpX8cgb0jWLOJ2UE2UgOc%3D","thumbus_count":0,"jp_type_id":10,"jp_id":"8"},{"jmi_id":"9","isThumbsUp":"N","jp_title":"心情不美丽","answerCount":"3","jmi_username":"顺晓媒体","isCollection":"N","jp_content":"心情不美丽","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/ee638fec3e784b31ab518938844169e8.jpg?Expires=4674517829&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=ykjJPUzpX8cgb0jWLOJ2UE2UgOc%3D","thumbus_count":1,"jp_type_id":21,"jp_id":"7"},{"jmi_id":"9","isThumbsUp":"N","jp_title":"我的提问 2018","answerCount":"11","jmi_username":"顺晓媒体","isCollection":"N","jp_content":"123123123","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/ee638fec3e784b31ab518938844169e8.jpg?Expires=4674517829&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=ykjJPUzpX8cgb0jWLOJ2UE2UgOc%3D","thumbus_count":1,"jp_type_id":3,"jp_id":"6"},{"jmi_id":"","isThumbsUp":"N","jp_title":"明日研讨厅的提问","answerCount":"3","jmi_username":"明日情报","isCollection":"N","jp_content":"明日研讨厅的描述","jmi_img":"","thumbus_count":1,"jp_type_id":3,"jp_id":"5"},{"jmi_id":"","isThumbsUp":"N","jp_title":"天空为什么是蓝的，草为什么是绿的","answerCount":"3","jmi_username":"明日情报","isCollection":"N","jp_content":"因为光的折射原理一级植物色素吸收","jmi_img":"","thumbus_count":2,"jp_type_id":3,"jp_id":"4"},{"jmi_id":"","isThumbsUp":"N","jp_title":"测试","answerCount":"1","jmi_username":"明日情报","isCollection":"N","jp_content":"测试","jmi_img":"","thumbus_count":3,"jp_type_id":12,"jp_id":"3"},{"jmi_id":"","isThumbsUp":"N","jp_title":"提问","answerCount":"1","jmi_username":"明日情报","isCollection":"N","jp_content":"123","jmi_img":"","thumbus_count":2,"jp_type_id":3,"jp_id":"2"},{"jmi_id":"","isThumbsUp":"N","jp_title":"为什么人这么聪明","answerCount":"3","jmi_username":"明日情报","isCollection":"N","jp_content":"描述不能为空","jmi_img":"","thumbus_count":2,"jp_type_id":3,"jp_id":"1"}]
         * currentpage : 1
         * typeId : -1
         * keyWord : null
         */

        private PagerBean pager;
        private int pagesize;
        private int userId;
        private int currentpage;
        private int typeId;
        private Object keyWord;
        private List<ListBean> list;

        public PagerBean getPager() {
            return pager;
        }

        public void setPager(PagerBean pager) {
            this.pager = pager;
        }

        public int getPagesize() {
            return pagesize;
        }

        public void setPagesize(int pagesize) {
            this.pagesize = pagesize;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getCurrentpage() {
            return currentpage;
        }

        public void setCurrentpage(int currentpage) {
            this.currentpage = currentpage;
        }

        public int getTypeId() {
            return typeId;
        }

        public void setTypeId(int typeId) {
            this.typeId = typeId;
        }

        public Object getKeyWord() {
            return keyWord;
        }

        public void setKeyWord(Object keyWord) {
            this.keyWord = keyWord;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagerBean {
            /**
             * total : 15
             * pageSize : 20
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
             * jmi_id :
             * isThumbsUp : N
             * jp_title : asdfasdf
             * answerCount :
             * jmi_username : 明日情报
             * isCollection : Y
             * jp_content : asdfsdfasf
             * jmi_img :
             * thumbus_count : 0
             * jp_type_id : 3
             * jp_id : 15
             */

            private String jmi_id;
            private String isThumbsUp;
            private String jp_title;
            private String answerCount;
            private String jmi_username;
            private String isCollection;
            private String jp_content;
            private String jmi_img;
            private int thumbus_count;
            private int jp_type_id;
            private String jp_id;

            public String getJmi_id() {
                return jmi_id;
            }

            public void setJmi_id(String jmi_id) {
                this.jmi_id = jmi_id;
            }

            public String getIsThumbsUp() {
                return isThumbsUp;
            }

            public void setIsThumbsUp(String isThumbsUp) {
                this.isThumbsUp = isThumbsUp;
            }

            public String getJp_title() {
                return jp_title;
            }

            public void setJp_title(String jp_title) {
                this.jp_title = jp_title;
            }

            public String getAnswerCount() {
                return answerCount;
            }

            public void setAnswerCount(String answerCount) {
                this.answerCount = answerCount;
            }

            public String getJmi_username() {
                return jmi_username;
            }

            public void setJmi_username(String jmi_username) {
                this.jmi_username = jmi_username;
            }

            public String getIsCollection() {
                return isCollection;
            }

            public void setIsCollection(String isCollection) {
                this.isCollection = isCollection;
            }

            public String getJp_content() {
                return jp_content;
            }

            public void setJp_content(String jp_content) {
                this.jp_content = jp_content;
            }

            public String getJmi_img() {
                return jmi_img;
            }

            public void setJmi_img(String jmi_img) {
                this.jmi_img = jmi_img;
            }

            public int getThumbus_count() {
                return thumbus_count;
            }

            public void setThumbus_count(int thumbus_count) {
                this.thumbus_count = thumbus_count;
            }

            public int getJp_type_id() {
                return jp_type_id;
            }

            public void setJp_type_id(int jp_type_id) {
                this.jp_type_id = jp_type_id;
            }

            public String getJp_id() {
                return jp_id;
            }

            public void setJp_id(String jp_id) {
                this.jp_id = jp_id;
            }
        }
    }
}
