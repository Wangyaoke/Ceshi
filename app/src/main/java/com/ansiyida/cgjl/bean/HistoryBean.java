package com.ansiyida.cgjl.bean;
import java.util.List;
public class HistoryBean {
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

        private String nextPage;
        private String pageNum;
        private String pageSize;
        private String pages;
        private String prePage;
        private String size;
        private String startRow;
        private String total;
        private List<list_bean> list;
        private String navigateFirstPage;
        private String navigateLastPage;
        private String navigatePages;
        private List<String> navigatepageNums;
        private String endRow;
        private String hasNextPage;
        private String hasPreviousPage;
        private String isFirstPage;
        private String isLastPage;
        public List<list_bean> getlist_bean() {
            return list;
        }

        public void setlist_bean(List<list_bean> list) {
            this.list = list;
        }

        public static class list_bean {




                private String id;

                private String createTime;
                private String source;
            private String genre;
            private boolean isCollection;
                private String province;
                private String infoId;
                private String type;
                private String title;
                private String userId;

                private String publishTime;
            public String getgenre () {
                return genre;
            }

            public void setgenre (String genre){
                this.genre = genre;
            }
            public boolean getisCollection () {
                return isCollection;
            }

            public void setisCollection (boolean isCollection){
                this.isCollection = isCollection;
            }
                public String getsource () {
                return source;
            }

                public void setsource (String source){
                this.source = source;
            }

                public String getprovince() {
                return province;
            }

                public void setprovince (String province){
                this.province = province;
            }

                public String getid () {
                return id;
            }

                public void setid (String id){
                this.id = id;
            }

                public String getinfoId () {
                return infoId;
            }

                public void setinfoId (String infoId){
                this.infoId = infoId;
            }

                public String getcreateTime () {
                return createTime;
            }

                public void setcreateTime (String createTime){
                this.createTime = createTime;
            }

                public String gettype () {
                return type;
            }

                public void settype ( String type){
                this.type = type;
            }

                public String getuserId () {
                return userId;
            }

                public void setuserId(String userId){
                this.userId = userId;
            }



                public String gettitle () {
                return title;
            }

                public void settitle (String jai_tpo){
                this.title = title;
            }




                public String getpublishTime () {
                return publishTime;
            }

                public void setpublishTime (String publishTime){
                this.publishTime = publishTime;
            }

            @Override
            public String toString() {
                return "list_bean{" +
                        "id='" + id + '\'' +
                        ", createTime='" + createTime + '\'' +
                        ", source='" + source + '\'' +
                        ", genre='" + genre + '\'' +
                        ", isCollection=" + isCollection +
                        ", province='" + province + '\'' +
                        ", infoId='" + infoId + '\'' +
                        ", type='" + type + '\'' +
                        ", title='" + title + '\'' +
                        ", userId='" + userId + '\'' +
                        ", publishTime='" + publishTime + '\'' +
                        '}';
            }
        }
    }
}
