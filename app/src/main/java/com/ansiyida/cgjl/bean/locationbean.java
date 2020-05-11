package com.ansiyida.cgjl.bean;

import java.util.List;

public class locationbean {

    /**
     * status : 0001
     * message : 成功
     * data : [{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fdf3b970a1a845e0a5e39425fe256ac3.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=8Kd%2Fi57%2Ff%2BwCGOAVVKNnUlwHxxQ%3D","jai_title":"社会","jai_tpo":null,"jai_id":85,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:51:43"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-31 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/fd250677148f4d19b7fc1aadf9647c6b.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=QR6wqZptYwOSOC6ZUV29P7lNSFA%3D","jai_title":"身边","jai_tpo":null,"jai_id":86,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:16"},{"jai_status":"Y","jal_id":1,"jal_end_time":"2018-03-30 00:00:00","jal_desc":"http://47.74.147.41:8081/back/advertising-readone","jal_recomment":"Y","jal_images":"http://lifangmi-image.oss-cn-qingdao.aliyuncs.com/test/e06798e6728640a2abde3da0a56cd744.jpg?Expires=1521697790&OSSAccessKeyId=LTAIQlz7GeprBLh4&Signature=gqKDcco%2BituQEaS2tP5Zprs3ATI%3D","jai_title":"前沿","jai_tpo":null,"jai_id":87,"isdel":"Y","jai_tupe":"A","jai_path":null,"jai_sort":1,"jai_url":"http://47.74.147.41:8081/back/advertising-readone","jal_start_time":"2018-03-01 14:52:45"}]
     */
  /*  "status":"OK",
            "result":{
        "location":{
            "lng":113.379763,
                    "lat":23.131427
        },
        "formatted_address":"广东省广州市天河区科韵路16号-104",
                "business":"天园,天河公园,棠下",
                "addressComponent":{
            "city":"广州市",
                    "direction":"西北",
                    "distance":"57",
                    "district":"天河区",
                    "province":"广东省",
                    "street":"科韵路",
                    "street_number":"16号-104"
        },
        "cityCode":257*/
    private String status;
    private resultbean result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public void setresult(resultbean result) {
        this.result = result;
    }
    public resultbean getresult() {
        return result;
    }


     public static class resultbean {
         private String formatted_address;
         private String business;
         private addressComponentbean addressComponent;
         private String cityCode;
         public lng_latbean location;

         public void setaddressComponentbean(addressComponentbean addressComponent) {
             this.addressComponent = addressComponent;
         }
         public addressComponentbean getaddressComponentbean() {
             return addressComponent;
         }
        public static class lng_latbean
        {
            private String lng;
            private String lat;
        }

         public static class addressComponentbean
         {
             private String city;
             private String direction;
             private String distance;
             private String province;
             private String street_number;
             private String street;
             private String district;
             public String getcity() {
                 return city;
             }

             public void setcity(String city) {
                 this.city = city;
             }
         }

    }
}
