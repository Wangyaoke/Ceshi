package com.ansiyida.cgjl.bean;

import java.util.List;

public class invoice_detail_listbean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fdf3b970a1a845e0a5e39425fe256ac3.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=8Kd%2Fi57%2Ff%2BwCGOAVVKNnUlwHxxQ%3D","jai_title":"社会","jai_tpo":null,"jai_id":85,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:51:43"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-31 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fd250677148f4d19b7fc1aadf9647c6b.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=QR6wqZptYwOSOC6ZUV29P7lNSFA%3D","jai_title":"身边","jai_tpo":null,"jai_id":86,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:16"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/e06798e6728640a2abde3da0a56cd744.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=gqKDcco%2BituQEaS2tP5Zprs3ATI%3D","jai_title":"前沿","jai_tpo":null,"jai_id":87,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:45"}]
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
         * jai_status : Y
         * jal_id : 1
         * jal_end_time : 2018-03-30 00:00:00
         * jal_desc : http://47.74.147.41:8081/back/advertising-readone
         * jal_recomment : Y
         * jal_images : http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fdf3b970a1a845e0a5e39425fe256ac3.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=8Kd%2Fi57%2Ff%2BwCGOAVVKNnUlwHxxQ%3D
         * jai_title : 社会
         * jai_tpo : null
         * jai_id : 85
         * isdel : Y
         * jai_tupe : A
         * jai_path : null
         * jai_sort : 1
         * jai_url : http://47.74.147.41:8081/back/advertising-readone
         * jal_start_time : 2018-03-01 14:51:43
         */

        /*"address":"厦门市湖滨南路81号光大银行大厦21层，电话：0592-230888"
       "content":""
       "createTime":1539038831000
       "id":"35"
       "isCollection":false
       "link":"http://www.ccgp.gov.cn/cggg/dfgg/jzxtpgg/201711/t20171129_9251258.htm"
       "province":"福建"
       "publishTime":1511967300000
       "source":"中国政府采购网"
       "title":"厦门公物-竞争性谈判-GW2017-SH668-智慧图书馆系统-采购公告"
       "type":"竞争性谈判公告"*/

        private invoice_bean invoice;
        private List<order_bean> order;
        public List<order_bean> getorder_bean() {
            return order;
        }

        public void setorder_bean(List<order_bean> list) {
            this.order = order;
        }


        public invoice_bean getinvoice() {
            return invoice;
        }
        public void setinvoice(invoice_bean invoice) {
            this.invoice = invoice;
        }
    public static class invoice_bean
    { private String userId;
        private String address;
        private String id;
        private String isDelete;
        private String createTime;
        private String orderNumber;
        private String payMethod;
        private String payMonth;
        private String payStatus;
        private String price;
        private String payTime;
        private String showDate;
        private String isPay;
        private String status;
        private String type;
        private String dutyNo;
        private String email;
        private String invoiceTitle;
        private String money;
        private String phone;
        private String content;
        private String recipients;
        public String getaddress() {
            return address;
        }

        public void setaddress(String address) {
            this.address = address;
        }
        public String getcontent() {
            return content;
        }

        public void setcontent(String content) {
            this.content = content;
        }
        public String getstatus() {
            return status;
        }

        public void setstatus(String status) {
            this.status = status;
        }
        public String gettype() {
            return type;
        }

        public void settype(String type) {
            this.type = type;
        }
        public String getdutyNo() {
            return dutyNo;
        }

        public void setdutyNo(String dutyNo) {
            this.dutyNo = dutyNo;
        }
        public String getemail() {
            return email;
        }

        public void setemaile(String email) {
            this.email = email;
        }
        public String getinvoiceTitle() {
            return invoiceTitle;
        }

        public void setinvoiceTitle(String invoiceTitle) {
            this.invoiceTitle = invoiceTitle;
        }
        public String getmoney() {
            return money;
        }

        public void setmoney(String money) {
            this.money = money;
        }
        public String getphone() {
            return phone;
        }

        public void setphone(String phone) {
            this.phone = phone;
        }
        public String getrecipients() {
            return recipients;
        }

        public void setrecipients(String recipients) {
            this.recipients = recipients;
        }
        public String getisDelete() {
            return isDelete;
        }

        public void setisDelete(String isDelete) {
            this.isDelete = isDelete;
        }
        public String getorderNumber() {
            return orderNumber;
        }

        public void setorderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }



        public String getpayMethode() {
            return payMethod;
        }

        public void setpayMethod(String payMethod) {
            this.payMethod = payMethod;
        }

        public String getpayMonth() {
            return payMonth;
        }

        public void setpayMonth(String payMonth) {
            this.payMonth = payMonth;
        }

        public String getpayStatus() {
            return payStatus;
        }

        public void setpayStatus(String payStatus) {
            this.payStatus = payStatus;
        }

        public String getprice() {
            return price;
        }

        public void setprice(String price) {
            this.price = price;
        }

        public String getcreateTime() {
            return createTime;
        }

        public void setcreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getpayTime() {
            return payTime;
        }

        public void setpayTime(String payTime) {
            this.payTime = payTime;
        }

        public String getuserId() {
            return userId;
        }

        public void setuserId(String userId) {
            this.userId = userId;
        }

        public String getshowDate() {
            return showDate;
        }

        public void setshowDate(String showDate) {
            this.showDate = showDate;
        }

        public String getisPay() {
            return isPay;
        }

        public void setisPay(String isPay) {
            this.isPay = isPay;
        }


        public String getid() {
            return id;
        }

        public void setid(String id) {
            this.id = id;
        }
    }
        public static class order_bean
        {
            private String userId;
            private String id;
            private String isDelete;
            private String createTime;
            private String orderNumber;
            private String payMethod;
            private String payMonth;
            private String payStatus;
            private String price;
            private String payTime;
            private String showDate;
            private String isPay;
            private String status;
            private String type;
            private String dutyNo;
            private String email;
            private String invoiceTitle;
            private String money;
            private String phone;
            private String recipients;
            public String getstatus() {
                return status;
            }

            public void setstatus(String status) {
                this.status = status;
            }
            public String gettype() {
                return type;
            }

            public void settype(String type) {
                this.type = type;
            }
            public String getdutyNo() {
                return dutyNo;
            }

            public void setdutyNo(String dutyNo) {
                this.dutyNo = dutyNo;
            }
            public String getemail() {
                return email;
            }

            public void setemaile(String email) {
                this.email = email;
            }
            public String getinvoiceTitle() {
                return invoiceTitle;
            }

            public void setinvoiceTitle(String invoiceTitle) {
                this.invoiceTitle = invoiceTitle;
            }
            public String getmoney() {
                return money;
            }

            public void setmoney(String money) {
                this.money = money;
            }
            public String getphone() {
                return phone;
            }

            public void setphone(String phone) {
                this.phone = phone;
            }
            public String getrecipients() {
                return recipients;
            }

            public void setrecipients(String recipients) {
                this.recipients = recipients;
            }
            public String getisDelete() {
                return isDelete;
            }

            public void setisDelete(String isDelete) {
                this.isDelete = isDelete;
            }
            public String getorderNumber() {
                return orderNumber;
            }

            public void setorderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }



            public String getpayMethode() {
                return payMethod;
            }

            public void setpayMethod(String payMethod) {
                this.payMethod = payMethod;
            }

            public String getpayMonth() {
                return payMonth;
            }

            public void setpayMonth(String payMonth) {
                this.payMonth = payMonth;
            }

            public String getpayStatus() {
                return payStatus;
            }

            public void setpayStatus(String payStatus) {
                this.payStatus = payStatus;
            }

            public String getprice() {
                return price;
            }

            public void setprice(String price) {
                this.price = price;
            }

            public String getcreateTime() {
                return createTime;
            }

            public void setcreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getpayTime() {
                return payTime;
            }

            public void setpayTime(String payTime) {
                this.payTime = payTime;
            }

            public String getuserId() {
                return userId;
            }

            public void setuserId(String userId) {
                this.userId = userId;
            }

            public String getshowDate() {
                return showDate;
            }

            public void setshowDate(String showDate) {
                this.showDate = showDate;
            }

            public String getisPay() {
                return isPay;
            }

            public void setisPay(String isPay) {
                this.isPay = isPay;
            }


            public String getid() {
                return id;
            }

            public void setid(String id) {
                this.id = id;
            }
        }

    }
}
