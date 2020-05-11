package com.ansiyida.cgjl.http;

import com.ansiyida.cgjl.bean.BannerBean;
import com.ansiyida.cgjl.bean.BuyListDetail;
import com.ansiyida.cgjl.bean.ChannelBean;
import com.ansiyida.cgjl.bean.CollegeListBean;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.DYListBean;
import com.ansiyida.cgjl.bean.DYMainBean;
import com.ansiyida.cgjl.bean.DYTypeBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.DefaultBean3;
import com.ansiyida.cgjl.bean.DefaultBean4;
import com.ansiyida.cgjl.bean.DefaultBean5;
import com.ansiyida.cgjl.bean.FansCareBean;
import com.ansiyida.cgjl.bean.FriendInfoBean;
import com.ansiyida.cgjl.bean.FriendYantaoBean;
import com.ansiyida.cgjl.bean.HistoryBean;
import com.ansiyida.cgjl.bean.HotSearchBean;
import com.ansiyida.cgjl.bean.InterestBean;
import com.ansiyida.cgjl.bean.JuBaoBean;
import com.ansiyida.cgjl.bean.LoginBean;
import com.ansiyida.cgjl.bean.MessageBean;
import com.ansiyida.cgjl.bean.MyDYItemBean;
import com.ansiyida.cgjl.bean.NewDetailBean;
import com.ansiyida.cgjl.bean.NewNewsListBean;
import com.ansiyida.cgjl.bean.NewsListBean;
import com.ansiyida.cgjl.bean.PersonalDynamicBean;
import com.ansiyida.cgjl.bean.SearchBean;
import com.ansiyida.cgjl.bean.SearchHistoryBean;
import com.ansiyida.cgjl.bean.SendYanTaoTypeBean;
import com.ansiyida.cgjl.bean.UpdateUserMsgBean;
import com.ansiyida.cgjl.bean.UploadPhotoBean;
import com.ansiyida.cgjl.bean.UserBean;
import com.ansiyida.cgjl.bean.VersionBean;
import com.ansiyida.cgjl.bean.VisitedDYBean;
import com.ansiyida.cgjl.bean.YanTaoBean;
import com.ansiyida.cgjl.bean.YanTaoDetailBean;
import com.ansiyida.cgjl.bean.YanTaoOptionsBean;
import com.ansiyida.cgjl.bean.caigoulist;
import com.ansiyida.cgjl.bean.infoTypeBeanBean;
import com.ansiyida.cgjl.bean.law_detail_listbean;
import com.ansiyida.cgjl.bean.lawlist;
import com.ansiyida.cgjl.bean.locationbean;
import com.ansiyida.cgjl.bean.policyTypeBean;
import com.ansiyida.cgjl.bean.sourceTypeBean;
import com.ansiyida.cgjl.bean.todayUpdateCountBean;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ansiyida on 2017/11/8.
 */
public interface NetctiyApi {
//    @GET("banner/banner.php")
//    Call<ResponseBody> getBanner(@Query("os") String os,@Query("index") String index,@Query("token") String token);
//
//    @FormUrlEncoded
//    @POST("login/login.php")
//    Call<ResponseBody> postLogin(@Field("os") String os, @Field("phone") String phone, @Field("password") String password, @Field("type") String type, @Field("token") String token,@Query("study_section") int study_section);

    //上传图片
//    @Multipart
//    @POST("app/editByToken")
//    Call<String> updateImage(@Part MultipartBody.Part  uploadfile);
//
//    @GET("ImageTest/uploadimage")
//    Call<ResponseBody> getBanner();

    /**
     * ----1.频道获取----
     * <p>
     * 请求参数
     * app_token   String	用户app_token
     */
    @GET("geocoder")
    Call<locationbean> getcity(@Query("output") String output, @Query("location") String location, @Query("ak") String ak);
   }
