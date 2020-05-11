package com.ansiyida.cgjl.bean;

import java.util.List;

/**
 * Created by ansiyida on 2018/3/13.
 */
public class PersonalDynamicBean {


    /**
     * status : 0001
     * message : 成功
     * data : {"haveMore":0,"pager":{"total":4,"pageSize":10,"pageCount":1,"page":1,"begin":0,"haveMore":0},"list":[{"art_id":35,"jmi_id":1,"art_time":"2018-03-14 15:41:58","jmi_username":"明日","art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b69c357669ea4212bb1056b2a8e3ece4.jpg?Expires=4674612925&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pdpu0WG0lU3Yonqv6GckJhbvsYY%3D","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_type":"C","art_des":"测试动态","art_type_id":"","art_title":"测试动态"},{"art_id":16,"jmi_id":1,"art_time":"2018-03-13 19:46:27","jmi_username":"明日","art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cb2f706624c24f59a5ba2fb611b17903.jpg?Expires=4674540715&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=bJGv%2FogOQYdGcPUG4V0K%2BdJiOJE%3D","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_type":"D","art_des":"1111","art_type_id":"3","art_title":"11111"},{"art_id":19,"jmi_id":1,"art_time":"2018-03-01 16:41:01","jmi_username":"明日","art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b3276105742a4e3b85d7df6413deaa05.jpg?Expires=1521720999&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=jB06I0wUail5RWI8NiKzG3j9XZs%3D","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_type":"C","art_des":"测试动态","art_type_id":"","art_title":"测试动态"},{"art_id":17,"jmi_id":1,"art_time":"2018-03-01 11:43:26","jmi_username":"明日","art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fb0762726a8a4cd3ba7f1038f41e12ca.jpg?Expires=1521702691&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=B80taJT%2B3wn8w%2FLxfA1%2FA7mwxf0%3D","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_type":"C","art_des":"测试动态","art_type_id":"","art_title":"测试动态"}]}
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
         * haveMore : 0
         * pager : {"total":4,"pageSize":10,"pageCount":1,"page":1,"begin":0,"haveMore":0}
         * list : [{"art_id":35,"jmi_id":1,"art_time":"2018-03-14 15:41:58","jmi_username":"明日","art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b69c357669ea4212bb1056b2a8e3ece4.jpg?Expires=4674612925&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pdpu0WG0lU3Yonqv6GckJhbvsYY%3D","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_type":"C","art_des":"测试动态","art_type_id":"","art_title":"测试动态"},{"art_id":16,"jmi_id":1,"art_time":"2018-03-13 19:46:27","jmi_username":"明日","art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cb2f706624c24f59a5ba2fb611b17903.jpg?Expires=4674540715&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=bJGv%2FogOQYdGcPUG4V0K%2BdJiOJE%3D","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_type":"D","art_des":"1111","art_type_id":"3","art_title":"11111"},{"art_id":19,"jmi_id":1,"art_time":"2018-03-01 16:41:01","jmi_username":"明日","art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b3276105742a4e3b85d7df6413deaa05.jpg?Expires=1521720999&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=jB06I0wUail5RWI8NiKzG3j9XZs%3D","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_type":"C","art_des":"测试动态","art_type_id":"","art_title":"测试动态"},{"art_id":17,"jmi_id":1,"art_time":"2018-03-01 11:43:26","jmi_username":"明日","art_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fb0762726a8a4cd3ba7f1038f41e12ca.jpg?Expires=1521702691&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=B80taJT%2B3wn8w%2FLxfA1%2FA7mwxf0%3D","jmi_img":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=","art_type":"C","art_des":"测试动态","art_type_id":"","art_title":"测试动态"}]
         */

        private int haveMore;
        private PagerBean pager;
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

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class PagerBean {
            /**
             * total : 4
             * pageSize : 10
             * pageCount : 1
             * page : 1
             * begin : 0
             * haveMore : 0
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
             * art_id : 35
             * jmi_id : 1
             * art_time : 2018-03-14 15:41:58
             * jmi_username : 明日
             * art_img : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/b69c357669ea4212bb1056b2a8e3ece4.jpg?Expires=4674612925&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=pdpu0WG0lU3Yonqv6GckJhbvsYY%3D
             * jmi_img : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/cfb6225fdb00441483bb49aabe0d0ab3.png?Expires=4674186406&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=oFMZRMsLQfedXpuKbtgjo/oXWn8=
             * art_type : C
             * art_des : 测试动态
             * art_type_id :
             * art_title : 测试动态
             */

            private int art_id;
            private int jmi_id;
            private String art_time;
            private String jmi_username;
            private String art_img;
            private String jmi_img;
            private String art_type;
            private String art_des;
            private String art_type_id;
            private String art_title;

            public int getArt_id() {
                return art_id;
            }

            public void setArt_id(int art_id) {
                this.art_id = art_id;
            }

            public int getJmi_id() {
                return jmi_id;
            }

            public void setJmi_id(int jmi_id) {
                this.jmi_id = jmi_id;
            }

            public String getArt_time() {
                return art_time;
            }

            public void setArt_time(String art_time) {
                this.art_time = art_time;
            }

            public String getJmi_username() {
                return jmi_username;
            }

            public void setJmi_username(String jmi_username) {
                this.jmi_username = jmi_username;
            }

            public String getArt_img() {
                return art_img;
            }

            public void setArt_img(String art_img) {
                this.art_img = art_img;
            }

            public String getJmi_img() {
                return jmi_img;
            }

            public void setJmi_img(String jmi_img) {
                this.jmi_img = jmi_img;
            }

            public String getArt_type() {
                return art_type;
            }

            public void setArt_type(String art_type) {
                this.art_type = art_type;
            }

            public String getArt_des() {
                return art_des;
            }

            public void setArt_des(String art_des) {
                this.art_des = art_des;
            }

            public String getArt_type_id() {
                return art_type_id;
            }

            public void setArt_type_id(String art_type_id) {
                this.art_type_id = art_type_id;
            }

            public String getArt_title() {
                return art_title;
            }

            public void setArt_title(String art_title) {
                this.art_title = art_title;
            }
        }
    }
}
