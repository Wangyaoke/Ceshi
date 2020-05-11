package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.List;

public class CiViMilitaryBean {


    /**
     * data : {"endRow":4,"hasNextPage":false,"hasPreviousPage":false,"isFirstPage":true,"isLastPage":true,"list":[{"address":"杭州","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":1559726650000,"id":1,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","isShow":true,"label":"科技,研发,生产","mode":"自营","name":"杭州产业园","sort":1,"typeId":1,"unitPrice":4},{"address":"合肥","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":1559726650000,"id":2,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/a2151afb-4dfa-4a87-9ead-7196d0d3e7ed.png","isShow":true,"label":"科技,研发,生产","mode":"加盟","name":"合肥产业园","sort":1,"typeId":1,"unitPrice":4},{"address":"海南","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":1559726650000,"id":3,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/947146c7-dd6f-4a0a-b23f-6f21cac991fa.png","isShow":true,"label":"科技,研发,生产","mode":"自营","name":"海南产业园","sort":1,"typeId":1,"unitPrice":4},{"address":"北京","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":1559726650000,"id":4,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/434fc514-1de5-4b8f-ade5-c5a1281a9721.png","isShow":true,"label":"科技,研发,生产","mode":"加盟","name":"北京产业园","sort":1,"typeId":1,"unitPrice":4}],"navigateFirstPage":1,"navigateLastPage":1,"navigatePages":8,"navigatepageNums":[1],"nextPage":0,"pageNum":1,"pageSize":20,"pages":1,"prePage":0,"size":4,"startRow":1,"total":4}
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
        return "CiViMilitaryBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public static class DataBean {
        /**
         * endRow : 4
         * hasNextPage : false
         * hasPreviousPage : false
         * isFirstPage : true
         * isLastPage : true
         * list : [{"address":"杭州","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":1559726650000,"id":1,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png","isShow":true,"label":"科技,研发,生产","mode":"自营","name":"杭州产业园","sort":1,"typeId":1,"unitPrice":4},{"address":"合肥","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":1559726650000,"id":2,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/a2151afb-4dfa-4a87-9ead-7196d0d3e7ed.png","isShow":true,"label":"科技,研发,生产","mode":"加盟","name":"合肥产业园","sort":1,"typeId":1,"unitPrice":4},{"address":"海南","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":1559726650000,"id":3,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/947146c7-dd6f-4a0a-b23f-6f21cac991fa.png","isShow":true,"label":"科技,研发,生产","mode":"自营","name":"海南产业园","sort":1,"typeId":1,"unitPrice":4},{"address":"北京","banner":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg","createTime":1559726650000,"id":4,"img":"https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/434fc514-1de5-4b8f-ade5-c5a1281a9721.png","isShow":true,"label":"科技,研发,生产","mode":"加盟","name":"北京产业园","sort":1,"typeId":1,"unitPrice":4}]
         * navigateFirstPage : 1
         * navigateLastPage : 1
         * navigatePages : 8
         * navigatepageNums : [1]
         * nextPage : 0
         * pageNum : 1
         * pageSize : 20
         * pages : 1
         * prePage : 0
         * size : 4
         * startRow : 1
         * total : 4
         */

        private int endRow;
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private int navigateFirstPage;
        private int navigateLastPage;
        private int navigatePages;
        private int nextPage;
        private int pageNum;
        private int pageSize;
        private int pages;
        private int prePage;
        private int size;
        private int startRow;
        private int total;
        private List<ListBean> list;
        private List<Integer> navigatepageNums;

        public int getEndRow() {
            return endRow;
        }

        public void setEndRow(int endRow) {
            this.endRow = endRow;
        }

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public boolean isIsFirstPage() {
            return isFirstPage;
        }

        public void setIsFirstPage(boolean isFirstPage) {
            this.isFirstPage = isFirstPage;
        }

        public boolean isIsLastPage() {
            return isLastPage;
        }

        public void setIsLastPage(boolean isLastPage) {
            this.isLastPage = isLastPage;
        }

        public int getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public void setNavigateFirstPage(int navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public int getNavigateLastPage() {
            return navigateLastPage;
        }

        public void setNavigateLastPage(int navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public int getNavigatePages() {
            return navigatePages;
        }

        public void setNavigatePages(int navigatePages) {
            this.navigatePages = navigatePages;
        }

        public int getNextPage() {
            return nextPage;
        }

        public void setNextPage(int nextPage) {
            this.nextPage = nextPage;
        }

        public int getPageNum() {
            return pageNum;
        }

        public void setPageNum(int pageNum) {
            this.pageNum = pageNum;
        }

        public int getPageSize() {
            return pageSize;
        }

        public void setPageSize(int pageSize) {
            this.pageSize = pageSize;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public int getPrePage() {
            return prePage;
        }

        public void setPrePage(int prePage) {
            this.prePage = prePage;
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getStartRow() {
            return startRow;
        }

        public void setStartRow(int startRow) {
            this.startRow = startRow;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public List<Integer> getNavigatepageNums() {
            return navigatepageNums;
        }

        public void setNavigatepageNums(List<Integer> navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "endRow=" + endRow +
                    ", hasNextPage=" + hasNextPage +
                    ", hasPreviousPage=" + hasPreviousPage +
                    ", isFirstPage=" + isFirstPage +
                    ", isLastPage=" + isLastPage +
                    ", navigateFirstPage=" + navigateFirstPage +
                    ", navigateLastPage=" + navigateLastPage +
                    ", navigatePages=" + navigatePages +
                    ", nextPage=" + nextPage +
                    ", pageNum=" + pageNum +
                    ", pageSize=" + pageSize +
                    ", pages=" + pages +
                    ", prePage=" + prePage +
                    ", size=" + size +
                    ", startRow=" + startRow +
                    ", total=" + total +
                    ", list=" + list +
                    ", navigatepageNums=" + navigatepageNums +
                    '}';
        }

        public static class ListBean {
            /**
             * address : 杭州
             * banner : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/7a88b37f-3340-4525-92a0-0b207c60dffc.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/dc6931d5-45ab-4b87-b226-a7b255aa0f85.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/ab485ee1-52e3-40f7-bffb-03239a9847c4.jpg,https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/1a056359-438f-4066-9470-2ba23d701580.jpg
             * createTime : 1559726650000
             * id : 1
             * img : https://lifangmi-image.oss-cn-qingdao.aliyuncs.com/purchase/images/0a7c1d03-ad50-483b-acfc-cce1733cc8ab.png
             * isShow : true
             * label : 科技,研发,生产
             * mode : 自营
             * name : 杭州产业园
             * sort : 1
             * typeId : 1
             * unitPrice : 4
             */

            private String address;
            private String banner;
            private long createTime;
            private int id;
            private String img;
            private boolean isShow;
            private String label;
            private String mode;
            private String name;
            private int sort;
            private int typeId;
            private int unitPrice;

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getBanner() {
                return banner;
            }

            public void setBanner(String banner) {
                this.banner = banner;
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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public boolean isIsShow() {
                return isShow;
            }

            public void setIsShow(boolean isShow) {
                this.isShow = isShow;
            }

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getMode() {
                return mode;
            }

            public void setMode(String mode) {
                this.mode = mode;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public int getTypeId() {
                return typeId;
            }

            public void setTypeId(int typeId) {
                this.typeId = typeId;
            }

            public int getUnitPrice() {
                return unitPrice;
            }

            public void setUnitPrice(int unitPrice) {
                this.unitPrice = unitPrice;
            }

            @Override
            public String toString() {
                return "ListBean{" +
                        "address='" + address + '\'' +
                        ", banner='" + banner + '\'' +
                        ", createTime=" + createTime +
                        ", id=" + id +
                        ", img='" + img + '\'' +
                        ", isShow=" + isShow +
                        ", label='" + label + '\'' +
                        ", mode='" + mode + '\'' +
                        ", name='" + name + '\'' +
                        ", sort=" + sort +
                        ", typeId=" + typeId +
                        ", unitPrice=" + unitPrice +
                        '}';
            }
        }
    }
}
