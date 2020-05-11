package com.ansiyida.cgjl.bean.cgjl_bean;

import java.util.Arrays;
import java.util.List;

public class StudyTypeBean {
    private Data data;
    private String message;
    private double status;

    public Data getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public double getStatus() {
        return status;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StudyTypeBean{" +
                "data=" + data +
                ", message='" + message + '\'' +
                ", status=" + status +
                '}';
    }

    public class Data{
        private boolean hasNextPage;
        private boolean hasPreviousPage;
        private boolean isFirstPage;
        private boolean isLastPage;
        private List<DataBean> list;
        private double navigateFirstPage ;
        private double navigateLastPage ;
        private double navigatePages ;
        private double[] navigatepageNums ;
        private double nextPage ;
        private double pageNum ;
        private double pageSize ;
        private double pages ;
        private double prePage ;
        private double size ;
        private double startRow ;
        private double total ;

        public boolean isHasNextPage() {
            return hasNextPage;
        }

        public boolean isHasPreviousPage() {
            return hasPreviousPage;
        }

        public boolean isFirstPage() {
            return isFirstPage;
        }

        public boolean isLastPage() {
            return isLastPage;
        }

        public List<DataBean> getList() {
            return list;
        }

        public double getNavigateFirstPage() {
            return navigateFirstPage;
        }

        public double getNavigateLastPage() {
            return navigateLastPage;
        }

        public double getNavigatePages() {
            return navigatePages;
        }

        public double[] getNavigatepageNums() {
            return navigatepageNums;
        }

        public double getNextPage() {
            return nextPage;
        }

        public double getPageNum() {
            return pageNum;
        }

        public double getPageSize() {
            return pageSize;
        }

        public double getPages() {
            return pages;
        }

        public double getPrePage() {
            return prePage;
        }

        public double getSize() {
            return size;
        }

        public double getStartRow() {
            return startRow;
        }

        public double getTotal() {
            return total;
        }

        public void setHasNextPage(boolean hasNextPage) {
            this.hasNextPage = hasNextPage;
        }

        public void setHasPreviousPage(boolean hasPreviousPage) {
            this.hasPreviousPage = hasPreviousPage;
        }

        public void setFirstPage(boolean firstPage) {
            isFirstPage = firstPage;
        }

        public void setLastPage(boolean lastPage) {
            isLastPage = lastPage;
        }

        public void setList(List<DataBean> list) {
            this.list = list;
        }

        public void setNavigateFirstPage(double navigateFirstPage) {
            this.navigateFirstPage = navigateFirstPage;
        }

        public void setNavigateLastPage(double navigateLastPage) {
            this.navigateLastPage = navigateLastPage;
        }

        public void setNavigatePages(double navigatePages) {
            this.navigatePages = navigatePages;
        }

        public void setNavigatepageNums(double[] navigatepageNums) {
            this.navigatepageNums = navigatepageNums;
        }

        public void setNextPage(double nextPage) {
            this.nextPage = nextPage;
        }

        public void setPageNum(double pageNum) {
            this.pageNum = pageNum;
        }

        public void setPageSize(double pageSize) {
            this.pageSize = pageSize;
        }

        public void setPages(double pages) {
            this.pages = pages;
        }

        public void setPrePage(double prePage) {
            this.prePage = prePage;
        }

        public void setSize(double size) {
            this.size = size;
        }

        public void setStartRow(double startRow) {
            this.startRow = startRow;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "hasNextPage=" + hasNextPage +
                    ", hasPreviousPage=" + hasPreviousPage +
                    ", isFirstPage=" + isFirstPage +
                    ", isLastPage=" + isLastPage +
                    ", list=" + list +
                    ", navigateFirstPage=" + navigateFirstPage +
                    ", navigateLastPage=" + navigateLastPage +
                    ", navigatePages=" + navigatePages +
                    ", navigatepageNums=" + Arrays.toString(navigatepageNums) +
                    ", nextPage=" + nextPage +
                    ", pageNum=" + pageNum +
                    ", pageSize=" + pageSize +
                    ", pages=" + pages +
                    ", prePage=" + prePage +
                    ", size=" + size +
                    ", startRow=" + startRow +
                    ", total=" + total +
                    '}';
        }
    }
    public class DataBean{
        private String createTime;
        private int id;
        private String img;
        private Object isDelete ;
        private double sort ;
        private String title;

        public String getCreateTime() {
            return createTime;
        }

        public int getId() {
            return id;
        }

        public String getImg() {
            return img;
        }

        public Object getIsDelete() {
            return isDelete;
        }

        public double getSort() {
            return sort;
        }

        public String getTitle() {
            return title;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public void setIsDelete(double isDelete) {
            this.isDelete = isDelete;
        }

        public void setSort(double sort) {
            this.sort = sort;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "createTime='" + createTime + '\'' +
                    ", id=" + id +
                    ", img='" + img + '\'' +
                    ", isDelete=" + isDelete +
                    ", sort=" + sort +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
