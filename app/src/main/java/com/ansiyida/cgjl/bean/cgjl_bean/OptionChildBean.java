package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class OptionChildBean {

    /**
     * data : {"children":[{"children":[{"children":[],"createTime":1557730815000,"id":31,"isDelete":false,"name":"航空总体技术","parentId":30,"sort":31},{"children":[],"createTime":1557890842000,"id":36,"isDelete":false,"name":"航空飞行技术","parentId":30,"sort":36},{"children":[],"createTime":1557890847000,"id":37,"isDelete":false,"name":"飞行试验与设备","parentId":30,"sort":37},{"children":[],"createTime":1557890850000,"id":38,"isDelete":false,"name":"航空发动机","parentId":30,"sort":38}],"createTime":1557730789000,"id":30,"isDelete":false,"name":"航空","parentId":29,"sort":30},{"children":[{"children":[],"createTime":1557890856000,"id":40,"isDelete":false,"name":"航天总体技术","parentId":39,"sort":40},{"children":[],"createTime":1557890859000,"id":41,"isDelete":false,"name":"航天平台技术","parentId":39,"sort":41},{"children":[],"createTime":1557890867000,"id":42,"isDelete":false,"name":"航天载荷技术","parentId":39,"sort":42}],"createTime":1557890853000,"id":39,"isDelete":false,"name":"航天","parentId":29,"sort":39},{"children":[{"children":[],"createTime":1557890873000,"id":44,"isDelete":false,"name":"通信技术技术","parentId":43,"sort":44},{"children":[],"createTime":1557890877000,"id":45,"isDelete":false,"name":"有线电通信","parentId":43,"sort":45},{"children":[],"createTime":1557890881000,"id":46,"isDelete":false,"name":"无线电通信","parentId":43,"sort":46}],"createTime":1557890870000,"id":43,"isDelete":false,"name":"通讯","parentId":29,"sort":43},{"children":[{"children":[],"createTime":1557890891000,"id":48,"isDelete":false,"name":"船舶总体技术","parentId":47,"sort":48},{"children":[],"createTime":1557890894000,"id":49,"isDelete":false,"name":"船舶动力技术","parentId":47,"sort":49},{"children":[],"createTime":1557890898000,"id":50,"isDelete":false,"name":"船舶建造技术","parentId":47,"sort":50}],"createTime":1557890887000,"id":47,"isDelete":false,"name":"船舶、海洋","parentId":29,"sort":47}],"createTime":1557730766000,"id":29,"isDelete":false,"name":"产品自荐-专业领域","parentId":0,"sort":29}
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

    public static class DataBean {
        /**
         * children : [{"children":[{"children":[],"createTime":1557730815000,"id":31,"isDelete":false,"name":"航空总体技术","parentId":30,"sort":31},{"children":[],"createTime":1557890842000,"id":36,"isDelete":false,"name":"航空飞行技术","parentId":30,"sort":36},{"children":[],"createTime":1557890847000,"id":37,"isDelete":false,"name":"飞行试验与设备","parentId":30,"sort":37},{"children":[],"createTime":1557890850000,"id":38,"isDelete":false,"name":"航空发动机","parentId":30,"sort":38}],"createTime":1557730789000,"id":30,"isDelete":false,"name":"航空","parentId":29,"sort":30},{"children":[{"children":[],"createTime":1557890856000,"id":40,"isDelete":false,"name":"航天总体技术","parentId":39,"sort":40},{"children":[],"createTime":1557890859000,"id":41,"isDelete":false,"name":"航天平台技术","parentId":39,"sort":41},{"children":[],"createTime":1557890867000,"id":42,"isDelete":false,"name":"航天载荷技术","parentId":39,"sort":42}],"createTime":1557890853000,"id":39,"isDelete":false,"name":"航天","parentId":29,"sort":39},{"children":[{"children":[],"createTime":1557890873000,"id":44,"isDelete":false,"name":"通信技术技术","parentId":43,"sort":44},{"children":[],"createTime":1557890877000,"id":45,"isDelete":false,"name":"有线电通信","parentId":43,"sort":45},{"children":[],"createTime":1557890881000,"id":46,"isDelete":false,"name":"无线电通信","parentId":43,"sort":46}],"createTime":1557890870000,"id":43,"isDelete":false,"name":"通讯","parentId":29,"sort":43},{"children":[{"children":[],"createTime":1557890891000,"id":48,"isDelete":false,"name":"船舶总体技术","parentId":47,"sort":48},{"children":[],"createTime":1557890894000,"id":49,"isDelete":false,"name":"船舶动力技术","parentId":47,"sort":49},{"children":[],"createTime":1557890898000,"id":50,"isDelete":false,"name":"船舶建造技术","parentId":47,"sort":50}],"createTime":1557890887000,"id":47,"isDelete":false,"name":"船舶、海洋","parentId":29,"sort":47}]
         * createTime : 1557730766000
         * id : 29
         * isDelete : false
         * name : 产品自荐-专业领域
         * parentId : 0
         * sort : 29
         */

        private long createTime;
        private int id;
        private boolean isDelete;
        private String name;
        private int parentId;
        private int sort;
        private List<ChildrenBeanX> children;

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

        public boolean isIsDelete() {
            return isDelete;
        }

        public void setIsDelete(boolean isDelete) {
            this.isDelete = isDelete;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public List<ChildrenBeanX> getChildren() {
            return children;
        }

        public void setChildren(List<ChildrenBeanX> children) {
            this.children = children;
        }

        public static class ChildrenBeanX {
            /**
             * children : [{"children":[],"createTime":1557730815000,"id":31,"isDelete":false,"name":"航空总体技术","parentId":30,"sort":31},{"children":[],"createTime":1557890842000,"id":36,"isDelete":false,"name":"航空飞行技术","parentId":30,"sort":36},{"children":[],"createTime":1557890847000,"id":37,"isDelete":false,"name":"飞行试验与设备","parentId":30,"sort":37},{"children":[],"createTime":1557890850000,"id":38,"isDelete":false,"name":"航空发动机","parentId":30,"sort":38}]
             * createTime : 1557730789000
             * id : 30
             * isDelete : false
             * name : 航空
             * parentId : 29
             * sort : 30
             */

            private long createTime;
            private int id;
            private boolean isDelete;
            private String name;
            private int parentId;
            private int sort;
            private List<ChildrenBean> children;

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

            public boolean isIsDelete() {
                return isDelete;
            }

            public void setIsDelete(boolean isDelete) {
                this.isDelete = isDelete;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getParentId() {
                return parentId;
            }

            public void setParentId(int parentId) {
                this.parentId = parentId;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public List<ChildrenBean> getChildren() {
                return children;
            }

            public void setChildren(List<ChildrenBean> children) {
                this.children = children;
            }

            public static class ChildrenBean {
                /**
                 * children : []
                 * createTime : 1557730815000
                 * id : 31
                 * isDelete : false
                 * name : 航空总体技术
                 * parentId : 30
                 * sort : 31
                 */

                private long createTime;
                private int id;
                private boolean isDelete;
                private String name;
                private int parentId;
                private int sort;
                private List<?> children;

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

                public boolean isIsDelete() {
                    return isDelete;
                }

                public void setIsDelete(boolean isDelete) {
                    this.isDelete = isDelete;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public int getParentId() {
                    return parentId;
                }

                public void setParentId(int parentId) {
                    this.parentId = parentId;
                }

                public int getSort() {
                    return sort;
                }

                public void setSort(int sort) {
                    this.sort = sort;
                }

                public List<?> getChildren() {
                    return children;
                }

                public void setChildren(List<?> children) {
                    this.children = children;
                }
            }
        }
    }
}
