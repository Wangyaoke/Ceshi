package com.ansiyida.cgjl.http;

import com.ansiyida.cgjl.bean.BannerBean;
import com.ansiyida.cgjl.bean.BuyListDetail;
import com.ansiyida.cgjl.bean.ChannelBean;
import com.ansiyida.cgjl.bean.CollegeListBean;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.CompanyBean;
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
import com.ansiyida.cgjl.bean.ProductBean;
import com.ansiyida.cgjl.bean.ProprietorBean;
import com.ansiyida.cgjl.bean.SearchBean;
import com.ansiyida.cgjl.bean.SearchHistoryBean;
import com.ansiyida.cgjl.bean.SendYanTaoTypeBean;
import com.ansiyida.cgjl.bean.UpdateUserMsgBean;
import com.ansiyida.cgjl.bean.UploadPhotoBean;
import com.ansiyida.cgjl.bean.UserBean;
import com.ansiyida.cgjl.bean.VersionBean;
import com.ansiyida.cgjl.bean.ViewpointBean;
import com.ansiyida.cgjl.bean.ViewpointDetail;
import com.ansiyida.cgjl.bean.VisitedDYBean;
import com.ansiyida.cgjl.bean.YanTaoBean;
import com.ansiyida.cgjl.bean.YanTaoDetailBean;
import com.ansiyida.cgjl.bean.YanTaoOptionsBean;
import com.ansiyida.cgjl.bean.buyvipaliPaybean;
import com.ansiyida.cgjl.bean.buyvipbean;
import com.ansiyida.cgjl.bean.caigoulist;
import com.ansiyida.cgjl.bean.cgjl_bean.AdvertisementBean;
import com.ansiyida.cgjl.bean.cgjl_bean.AuditCenterBean;
import com.ansiyida.cgjl.bean.cgjl_bean.BigDataListBean;
import com.ansiyida.cgjl.bean.cgjl_bean.CheckRecordDetailBean;
import com.ansiyida.cgjl.bean.cgjl_bean.CiViMilitaryBean;
import com.ansiyida.cgjl.bean.cgjl_bean.CiViMilitrayTypeBean;
import com.ansiyida.cgjl.bean.cgjl_bean.EnterpriseBusinessBean;
import com.ansiyida.cgjl.bean.cgjl_bean.EnterpriseDataBean;
import com.ansiyida.cgjl.bean.cgjl_bean.FinancialServeBean;
import com.ansiyida.cgjl.bean.cgjl_bean.FinancialServeIntroduceBean;
import com.ansiyida.cgjl.bean.cgjl_bean.GetProjectBean;
import com.ansiyida.cgjl.bean.cgjl_bean.IndustrialPackDetailBean;
import com.ansiyida.cgjl.bean.cgjl_bean.IndustrialParkDetailBean;
import com.ansiyida.cgjl.bean.cgjl_bean.IntrodusiralAuditCenterBean;
import com.ansiyida.cgjl.bean.cgjl_bean.MenuBean;
import com.ansiyida.cgjl.bean.cgjl_bean.OptionBean;
import com.ansiyida.cgjl.bean.cgjl_bean.OptionChildBean;
import com.ansiyida.cgjl.bean.cgjl_bean.ParkDetailsListContentBean;
import com.ansiyida.cgjl.bean.cgjl_bean.ProjectListBean;
import com.ansiyida.cgjl.bean.cgjl_bean.ProjectTailAfterBean;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseBigDataTrendBean;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseInfoSearchBean;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseWordCloudBean;
import com.ansiyida.cgjl.bean.cgjl_bean.QyZjBean;
import com.ansiyida.cgjl.bean.cgjl_bean.ShouYeBannerBean;
import com.ansiyida.cgjl.bean.cgjl_bean.StudyRecyclerBean;
import com.ansiyida.cgjl.bean.cgjl_bean.StudySearchBean;
import com.ansiyida.cgjl.bean.cgjl_bean.StudyTypeBean;
import com.ansiyida.cgjl.bean.cgjl_bean.UpdateUserInfo;
import com.ansiyida.cgjl.bean.college_bean;
import com.ansiyida.cgjl.bean.cpml_detail_listbean;
import com.ansiyida.cgjl.bean.dydetail_bean;
import com.ansiyida.cgjl.bean.history_buyvip;
import com.ansiyida.cgjl.bean.infoTypeBeanBean;
import com.ansiyida.cgjl.bean.invoice_detail_listbean;
import com.ansiyida.cgjl.bean.law_detail_listbean;
import com.ansiyida.cgjl.bean.law_seachbean;
import com.ansiyida.cgjl.bean.lawlist;
import com.ansiyida.cgjl.bean.minedy_bean;
import com.ansiyida.cgjl.bean.policyTypeBean;
import com.ansiyida.cgjl.bean.purchaseDemandBean;
import com.ansiyida.cgjl.bean.purchaseDemand_detailn;
import com.ansiyida.cgjl.bean.purchaseSecretBean;
import com.ansiyida.cgjl.bean.qyml_detail_listbean;
import com.ansiyida.cgjl.bean.qyml_winTender_listbean;
import com.ansiyida.cgjl.bean.sourceTypeBean;
import com.ansiyida.cgjl.bean.todayUpdateCountBean;
import com.ansiyida.cgjl.bean.wintenderbean;
import com.ansiyida.cgjl.bean.yzml_detail_listbean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by ansiyida on 2017/11/8.
 */
public interface NetApi {
    //删除跟踪的项目
    @POST("project/delete")
    Call<ProjectTailAfterBean> getProjectDelete(@Header("token") String token, @Query("ids") String ids );
    //我的模块项目跟踪列表
    @GET("project/follow")
    Call<ProjectListBean> getProjectList(@Header("token") String token);
    //项目跟踪按钮
    @POST("project/tailAfter")
    Call<ProjectTailAfterBean> getProjectTailAfter(@Header("token") String token, @Query("source") String source, @Query("article") String article);
    //获取项目跟踪信息
    @GET("project")
    Call<GetProjectBean> getProject(@Header("token") String token,@Query("source") String source,@Query("article") String article);
    //大数据
    @GET("bigdata")
    Call<BigDataListBean> getBigDataListData(@Header("token") String token);
    //采购大数据词云
    @GET("procurement/tendency")
    Call<PurchaseBigDataTrendBean> getPurchaseBigDataTrendData(@Header("token") String token, @Query("time")String time);
    //采购大数据词云
    @GET("procurement/data")
    Call<PurchaseWordCloudBean> getPurchaseWordCloudData(@Header("token") String token,@Query("time")String time);
    //企业大数据行业比重
    @GET("enterprise/business")
    Call<EnterpriseBusinessBean> getEnterpriseBusinessData(@Header("token") String token);
    //企业大数据军民融合企业分布
    @GET("enterprise/distribution")
    Call<EnterpriseDataBean> getEnterpriseData(@Header("token") String token);
    //首页其他分类
    @GET("menu")
    Call<MenuBean> getMenu(@Header("token") String token,@Query("genre") String genre);
    //我的模块产业园审核列表
    @GET("industrialPack")
    Call<IntrodusiralAuditCenterBean> getAuditIntrodusiral(@Header("token") String token,@Query("pageNum") Integer pageNum,@Query("pageSize") Integer pageSize,@Query("mode")String mode,@Query("address") String address);
    //立即沟通提交
    @POST("financialServe/exchange")
    Call<QyZjBean> getFinancialServeExchange(@Header("token") String app_token,@Query("financialServeId")Integer financialServeId,@Query("companyName") String companyName ,@Query("projectIntroduce") String projectIntroduce,@Query("tradeKind") String tradeKind,@Query("financingDemand") String financingDemand,@Query("round") String round,@Query("yearIncome") String yearIncome,@Query("linkman") String linkman,@Query("phone") String phone);
    //金融商品详情
    @GET("financialServe/commodity/introduce/{id}")
    Call<FinancialServeIntroduceBean> getFinancialServeIntroduce(@Header("token") String app_token, @Path("id") Integer id);
    //金融服务列表查询
    @GET("financialServe")
    Call<FinancialServeBean> getfinancialServe (@Header("token") String app_token);
    //产业园详情政策新闻
    @GET("civilMilitary/ParkDetailsListContent/{id}")
    Call<ParkDetailsListContentBean> getParkDetailsListContent(@Header("token") String app_token, @Path("id") Integer id);
    //产业园入住申请详情
    @GET("industrialPack/{id}")
    Call<IndustrialPackDetailBean> GetindustrialPackDetail(@Header("token") String app_token, @Path("id") Integer id);
    @PUT("industrialPack")
    Call<QyZjBean> getHhH(@FieldMap Map<String,Object> map);
    //产业园入住
    @PUT("industrialPack")
    Call<QyZjBean> AddIndustrialPack(@Header("token") String app_token,@Query("companyName") String companyName,@Query("companyWebsite") String companyWebsite,@Query("companyLegal") String companyLegal,@Query("companyAddress") String companyAddress,@Query("tradeType") String tradeType,@Query("businessKeyword") String businessKeyword,@Query("companySummary") String companySummary,@Query("businessSummary") String businessSummary,@Query("degree") String degree,@Query("parkAddress") String parkAddress
            ,@Query("parkName") String parkName,@Query("parkDemand") String parkDemand,@Query("parkPurpose") String parkPurpose,@Query("otherDemand") String otherDemand,@Query("startTime") String startTime,@Query("duration") String duration,@Query("contacts") String contacts,@Query("contactsPhone") String contactsPhone,@Query("contactsEmail") String contactsEmail,@Query("civilMilitaryId") Integer id);
    //产业园详情查询
    @GET("civilMilitary/{id}")
    Call<IndustrialParkDetailBean> getIndustrialParkDetail(@Header("token") String app_token,@Path("id") Integer id);
    //军民融合分类
    @GET("civilMilitaryType")
    Call<CiViMilitrayTypeBean> getCivilMilitaryType(@Header("token") String app_token,@Query("pageNum") Integer pageNum,@Query("pageSize") Integer pageSize);

    @POST("taobao/ad")
    Call<AdvertisementBean> getAdvertise(@Query("keyword") String keyword,@Query("pageSize") Integer pageSize,@Query("pageNum") Integer pageNum);
    //军民融合产业园展示
    @GET("civilMilitary")
    Call<CiViMilitaryBean> getcivilMilitary (@Header("token") String app_token, @Query("pageNum") Integer pageNum, @Query("pageSize")Integer pageSize, @Query("mode") String mode,@Query("address") String address);
    //关键字
    @GET("option/all")
    Call <OptionChildBean> getoptioAll(@Header("token") String app_token, @Query("id") Integer id);
    @GET("option")
    Call <OptionBean> getoption(@Header("token") String app_token, @Query("parentId") Integer parentId);
    //修改用户信息
    @POST("userInfo")
    Call<UpdateUserInfo> updateUserInfo(@Header("token") String app_token, @Query("headImg") String headImg,@Query("phone") String phone);
    //首页搜索
    @GET("purchaseInfo/search")
    Call<PurchaseInfoSearchBean> getPurchaseInfoSearch (@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize")String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("type") String type,@Query("startTime") String startTime,@Query("endTime") String endTime,@Query("searchType") String searchType);
    //审核详情
    @GET("checkRecord/{id}")
    Call<CheckRecordDetailBean>getcheckRecordDetail(@Header("token") String app_token, @Path("id") Integer id);
    //审核列表
    @GET("checkRecord")
    Call<AuditCenterBean>getcheckRecord(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize")String pageSize, @Query("infoType") String infoType);
    //产品自荐
    @POST("product")
    Call<QyZjBean>CPSelfRecommendation(@Header("token") String token, @Query("title") String title, @Query("img") String img, @Query("companyName") String companyName, @Query("majorField")String majorField, @Query("degree") String degree, @Query("productKeyword")String productKeyword, @Query("productSummary") String productSummary, @Query("contact")String contact, @Query("parentId")String parentId);
    //企业自荐
    @POST("company")
    Call<QyZjBean>QYSelfRecommendation(@Header("token") String token, @Query("corporation") String corporation, @Query("companyName") String companyName, @Query("website") String website, @Query("tradeType")String tradeType, @Query("majorField") String majorField, @Query("businessKeyword")String businessKeyword, @Query("companySummary") String companySummary, @Query("businessSummary")String businessSummary, @Query("businessLicense") String businessLicense, @Query("parentId")String parentId);
    //查询收藏
    @GET("collection/record")
    Call<college_bean>SelCollectionRecord(@Header("token") String token,@Query("pageNum") String pageNum, @Query("pageSize") String pageSize,@Query("isWarn") Boolean isWarn, @Query("genre")String genre);
    //收藏
    @POST("collection/record")
    Call<college_bean>getCollectionRecord(@Header("token") String app_token, @Query("isWarn") Boolean isWarn, @Query("genre")String genre,@Query("infoId") String infoId);
    //取消收藏
    @DELETE("collection/record")
    Call<college_bean>DELETECollectionRecord(@Header("token") String app_token, @Query("genre") String genre,@Query("infoIds") String infoIds);
    //根据学习类型查询资料
    @GET("knowledge")
    Call<StudyRecyclerBean>getKnowledge(@Header("token") String app_token, @Query("typeId") Integer typeId, @Query("id")Integer id);
    //广告查询
    @GET("adInfo")
    Call<ShouYeBannerBean>getadInfo(@Header("token") Object app_token, @Query("position") String position);
    //学院类型查询
    @GET("knowledgeType")
    Call<StudyTypeBean> getknowledgeType(@Header("token") String app_token, @Query("pageNum") String pageNum , @Query("pageSize") String pagesize);
    //学院搜索
    @GET("knowledge/search")
    Call<StudySearchBean> getKnowledgeSearch(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword);
    /**
     * ----1.频道获取----
     * <p>
     * 请求参数
     * app_token   String	用户app_token
     */
    @GET("app/channel")
    Call<ChannelBean> getChannelList(@Query("app_token") String app_token);
    @GET("picture/code")
    Call<ResponseBody> picture(@Header("token") String app_token,@Query("key") String key,@Query("type") String type);
    @POST("visitor/login")
    Call<DefaultBean2> visitor(@Query("visitorId") String visitorId);
    @GET("policyInfo/{id}")
    Call<law_detail_listbean> get_law_detail( @Path("id") String id,@Header("token") String app_token);
    @GET("invoice/{id}")
    Call<invoice_detail_listbean> get_invoice_detail(@Path("id") String id, @Header("token") String app_token);
    @GET("product/{id}")
    Call<cpml_detail_listbean> get_product_detail(@Path("id") String id, @Header("token") String app_token);

    @GET("company/{id}")
    Call<qyml_detail_listbean> get_company_detail(@Path("id") String id, @Header("token") String app_token);
    @GET("company/winTender")
    Call<qyml_winTender_listbean> get_company_winTender(@Header("token") String app_token, @Query("companyName") String companyName);
    @GET("proprietor/detail/{id}")
    Call<yzml_detail_listbean> get_proprietor_detail(@Path("id") String id, @Header("token") String app_token);
    @GET("purchaseInfo/{id}")
    Call<BuyListDetail> get_buy_detail(@Path("id") String id, @Header("token") String app_token);
    @GET("viewpoint/{id}")
    Call<ViewpointDetail> get_viewpoint_detail(@Path("id") String id, @Header("token") String app_token);
    @GET("purchaseDemand/v2")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<purchaseDemandBean> getpurchaseDemand(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("isFinish") Boolean isFinish, @Query("keyword") String keyword);
    @GET("purchaseDemand/v2")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<purchaseDemandBean> getpurchaseDemand1(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword);

    @GET("purchaseSecret/v2")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<purchaseSecretBean> getpurchaseSecret(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("isFinish") Boolean isFinish, @Query("keyword") String keyword);

    @GET("purchaseDemand/{id}")
    Call<purchaseDemand_detailn> get_purchaseDemand_detail(@Path("id") String id, @Header("token") String app_token);
    @GET("purchaseInfo")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<caigoulist> getcaigou(@Header("token") String app_token,@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword, @Query("source") String source, @Query("address") String address, @Query("province") String province, @Query("type") String type,@Query("sort") String sort);
    @GET("viewpoint")//精灵智库
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<ViewpointBean> getviewpoint(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("types") String types);

    @GET("purchaseInfo")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<caigoulist> getcaigou1(@Header("token") String app_token,@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword, @Query("source") String source, @Query("address") String address,@Query("province") String province, @Query("type") String type);
    @GET("purchaseInfo")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<caigoulist> getcaigouseach(@Header("token") String app_token,@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword, @Query("source") String source, @Query("address") String address,@Query("province") String province, @Query("type") String type, @Query("isRecord") Boolean isRecord);
    @GET("company/winTender")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<wintenderbean> getwinTender(@Header("token") String app_token, @Query("companyName") String companyName);

    @GET("search")
    Call<SearchBean> getSearchList(@Header("token") String app_token,@Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("address") String address);
    @GET("policyInfo/search")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<law_seachbean> getlaw_search(@Header("token") String token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("type") String type, @Query("keyword") String keyword);

    @GET("policyInfo")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<lawlist> getlawlist(@Header("token") String token,@Query("pageNum") String pageNum,@Query("pageSize") String pageSize,@Query("typeId") int typeid);
    @GET("policyInfo")
        //   Call<ChannelBean> getcaigou(@Query("app_token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize, @Query("keyword") String keyword,@Query("source") String source,@Query("address") String address,@Query("province") String province,@Field("type") String type);
    Call<lawlist> getlawlist1(@Header("token") String token,@Query("pageNum") String pageNum, @Query("pageSize") String pageSize);
    @GET("hot/search")
    Call<HotSearchBean> hotSearchList(@Header("token") String token, @Query("pageNum")String pageNum,@Query("pageSize")String pageSize);
    @GET("proprietor")
    Call<ProprietorBean> getproprietor(@Header("token") String token, @Query("pageNum")String pageNum, @Query("pageSize")String pageSize, @Query("proprietorName")String proprietorName);
    @GET("company")
    Call<CompanyBean> getcompany(@Header("token") String token, @Query("pageNum")String pageNum, @Query("pageSize")String pageSize, @Query("companyName")String companyName, @Query("tradeType")String tradeType);
    @GET("product")
    Call<ProductBean> getproduct(@Header("token") String token, @Query("pageNum")String pageNum, @Query("pageSize")String pageSize, @Query("keyword")String keyword);
    @GET("purchaseInfo/todayUpdateCount")
    Call<todayUpdateCountBean> gettodayUpdateCount();
    @GET("source")
    Call<sourceTypeBean> getsource(@Header("token") String app_token);
    @GET("policyType")
    Call<policyTypeBean> getpolicyType(@Header("token") String app_token);
    @GET("infoType")
    Call<infoTypeBeanBean> getinfoType(@Header("token") String app_token);
    @GET("userInfo")
    Call<UserBean> getUserInfo(@Header("token") String app_token);
    @POST("reportErrors")
    Call<DefaultBean> postbug(@Header("token") String token, @Query("infoId")String infoId,@Query("genre")String genre,@Query("errorInfo")String errorInfo);

    /**
     * ----2.根据类型分频道(Bean)----
     * <p>
     * 请求参数
     * pid	int	一级分类id
     * cid	int	二级分类id
     * currentpage	int	当前页数（默认1）
     * pagesize	int	每页条数（默认20）
     */
    @FormUrlEncoded
    @POST("app/web/bytype")
    Call<NewsListBean> getNewListByType(@Field("pid") String pid, @Field("cid") String cid, @Field("currentpage") String currentpage, @Field("pagesize") String pagesize,@Field("app_token") String app_token,@Field("jct_user_cooike") String jct_user_cooike);

    /**
     * ----3.提交频道更改----
     * <p>
     * 请求参数
     * channel	String	频道id串”,”号隔开
     * app_token String	用户app_token
     */
    @FormUrlEncoded
    @POST("app/channel/modify")
    Call<ResponseBody> uploadChannel(@Field("channel") String channel, @Field("app_token") String app_token);

    /**
     * ----4.新闻详情----
     * <p>
     * 请求参数
     * type	String	文章类型
     * id	int	文章id
     * app_token String	用户app_token
     */
    @FormUrlEncoded
    @POST("app/forword")
    Call<NewDetailBean> getNewDetail(@Field("type") String type, @Field("id") String id, @Field("app_token") String app_token);

    /**
     * ----5.评论列表----
     * <p>
     * 请求参数
     * app_token        用户token
     * currentpage	 	当前页数（默认1）
     * pagesize	     	每页条数（默认20）
     * id	        	文章id
     * artype	    	文章类型
     * jc_id	    	评论id（二级评论使用）
     */
    @FormUrlEncoded
    @POST("app/comment")
    Call<CommentBean> getComment(@Field("app_token") String app_token, @Field("artype") String artype, @Field("id") String id, @Field("currentpage") String currentpage, @Field("pagesize") String pagesize, @Field("jc_id") String jc_id);

    /**
     * ----6.首页Banner----
     * <p>
     * id	int	1(固定)
     */
    @FormUrlEncoded
    @POST("app/web/homebanner")
    Call<BannerBean> getBanner(@Field("id") String id);

    /**
     * ----7.发表评论----
     * <p>
     * 请求参数
     * comment_id	    int	    文章id	          Y
     * article_type	    String	文章类型
     * jc_cont	        String	评论内容
     * app_token	    String	登录用户令牌
     * jc_p_id		二级评论时传值，（评论的一级id）
     */
    @FormUrlEncoded
    @POST("app/savecomment")
    Call<DefaultBean> saveComment(@Field("comment_id") String comment_id, @Field("article_type") String article_type, @Field("jc_cont") String jc_cont, @Field("app_token") String app_token, @Field("jc_p_id") String jc_p_id);

    /**
     * ----8.收藏文章----
     * <p>
     * 请求参数
     * jc_type	String	a/b/c/d/e/f(视频文章/普通文章/图片文章/问答/产品/采集文章)
     * concer_id	int	被收藏id
     * app_token	String	登录用户令牌
     */

    @DELETE("collection/record")
    Call<DefaultBean> collegeNews_del(@Header("token") String app_token,@Query("genre") String genre, @Query("infoIds") String infoIds);
    @POST("collection/record")
    Call<DefaultBean> collegeNews(@Header("token") String app_token, @Query("genre") String genre, @Query("infoId") String infoIds);
    @POST("collection/record/warn")
    Call<DefaultBean> collegewarn(@Header("token") String app_token, @Query("id") String id, @Query("isWarn") String isWarn);
    @GET("collection/record")
    Call<college_bean> collegeNews_get_all(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize,@Query("genre") String genre);
    @GET("collection/record")
    Call<college_bean> collegeNews_get(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize,@Query("isWarn") boolean isWarn,@Query("genre") String genre);

    @GET("order")
    Call<history_buyvip> history_buyvip(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);
    @GET("invoice/apply")
    Call<history_buyvip> invoice_apply(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);
    @GET("invoice")
    Call<history_buyvip> invoice(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);

    @GET("buyhistory")
    Call<college_bean> buy_history(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize,@Query("genre") String genre);

    /**
     * ----9.精彩/不喜欢||用户点赞----
     * <p>
     * 请求参数
     * thumbus_id		id	Y
     * ltu_type	    	A/B/Z/(P、T、S)（回答，评论,问题.文章类型）
     * datatype	    	up/down(精彩/不喜欢)
     * app_token		登录用户令牌
     */
    @FormUrlEncoded
    @POST("app/addgread")
    Call<DefaultBean> addgread(@Field("thumbus_id") String thumbus_id, @Field("ltu_type") String ltu_type, @Field("datatype") String datatype, @Field("app_token") String app_token);

    /**
     * ----10.举报、反馈列表----
     * <p>
     * 请求参数
     * id	int	20（文章举报）
     * *********10（文章反馈）
     * *********24（评论举报）
     * *********2 （文章不喜欢）
     * *********5  (兴趣选择)
     */
    @FormUrlEncoded
    @POST("app/web/lable")
    Call<JuBaoBean> getJuBao(@Field("id") String id);

    /**
     * ----11.关注/取消关注----
     * concer_id		被关注id
     * sign		        F/G 关注/取消
     * jmi_type		    关注类型 会员/智库号P/Z
     * app_token		登录            用户令牌
     */
    @FormUrlEncoded
    @POST("app/attentionAdd")
    Call<DefaultBean2> guanZhu(@Field("concer_id") String concer_id, @Field("sign") String sign, @Field("jmi_type") String jmi_type, @Field("app_token") String app_token);

    /**
     * ----12.订阅---
     * app_token	String	登录用户令牌
     */
    @POST("subscribe")
    Call<DefaultBean2> add_dy(@Header("token") String app_token,@Query("name") String name,@Query("keyword") String keyword,@Query("genre") String genre,@Query("province") String province,@Query("address") String address,@Query("typeId") Object typeId,@Query("sourceId") Object sourceId,@Query("status") Boolean status);
    @POST("subscribe")
    Call<DefaultBean2> add_dy_cg(@Header("token") String app_token,@Query("name") String name,@Query("keyword") String keyword,@Query("genre") String genre,@Query("status") Boolean status);
    @POST("subscribe")
    Call<DefaultBean2> add_dy_sm(@Header("token") String app_token,@Query("keyword") String keyword, @Query("genre") String genre,@Query("status") Boolean status);

    @POST("subscribe/read/{id}")
    Call<DefaultBean2> read_dy(@Path("id") String id,@Header("token") String app_token);
    @POST("subscribe/{id}")
    Call<DefaultBean2> post_dy(@Path("id") String id,@Header("token") String app_token,@Query("name") String name,@Query("keyword") String keyword,@Query("genre") String genre,@Query("province") String province,@Query("address") String address,@Query("typeId") Object typeId,@Query("sourceId") Object sourceId,@Query("status") Boolean status);
    @POST("subscribe/{id}")
    Call<DefaultBean2> post_dy_cx(@Path("id") String id,@Header("token") String app_token,@Query("name") String name,@Query("keyword") String keyword,@Query("genre") String genre,@Query("status") Boolean status);
    @POST("subscribe/{id}")
    Call<DefaultBean2> post_dy_sm(@Path("id") String id,@Header("token") String app_token,@Query("name") String name,@Query("keyword") String keyword,@Query("genre") String genre,@Query("status") Boolean status);

    @GET("subscribe")
    Call<minedy_bean> get_dy(@Header("token") String app_token, @Query("pageNum") String pageNum, @Query("pageSize") String pageSize);
    @DELETE("subscribe/{id}")
    Call<DefaultBean2> del_dy( @Path("id") String id,@Header("token") String app_token);
    @GET("subscribe/{id}")
    Call<dydetail_bean> getID_dy(@Path("id") String id, @Header("token") String app_token);
    /**
     * ----12.个人中心用户信息----
     * app_token	String	登录用户令牌
     */
    @FormUrlEncoded
    @GET("userInfo")
    Call<UserBean> getUserInfo1(@Header("token") String app_token);

    /**
     * ----13.首页网址提交----
     * name		    网址名称
     * url		    网址
     * email		邮箱
     * app_token	登录用户令牌
     */
    @FormUrlEncoded
    @POST("app/web/urlsubmit")
    Call<DefaultBean2> uploadUrl(@Field("name") String name, @Field("url") String url, @Field("email") String email, @Field("app_token") String app_token);

    /**
     * ----14.获取我的收藏----
     * app_token		登录用户令牌
     * currentpage		当前页数（默认1）
     * pagesize		    每页显示条数（默认20）
     */
    @FormUrlEncoded
    @POST("app/memberCenterContributeCollect")
    Call<CollegeListBean> getCollegeList(@Field("app_token") String app_token, @Field("currentpage") String currentpage, @Field("pagesize") String pagesize);

    /**
     * ----15.获取阅读历史----
     * app_token		登录用户令牌
     * currentpage		当前页数（默认1）
     * pagesize		    每页显示条数（默认20）
     */
   // @FormUrlEncoded
    @GET("browse/history/v2")
    Call<HistoryBean> getReadList(@Header("token") String token, @Query("pageNum")String pageNum,@Query("pageSize")String pageSize);
    @DELETE("browse/history")
    Call<DefaultBean> delReadList(@Header("token") String token);


    /**
     * ----16.搜索----
     * keyword		    搜索关键词
     * type		        搜索类型（N/S/T/C/D）（新闻/视频/图集/智库/问答）
     * currentpage		当前页（默认1）
     * pagesize		    每页显示条数（默认20）
     * app_token		登录token
     */


    /**
     * ----17.查询搜索记录----
     * currentpage		当前页（默认1）
     * pagesize		    每页显示条数（默认20）
     * app_token		登录token
     */
 //   @FormUrlEncoded
    @GET("search/record")
    Call<SearchHistoryBean> getSearchHistoryList(@Header("token") String token, @Query("pageNum")String pageNum,@Query("pageSize")String pageSize);

    /**
     * ----18.删除搜索记录----
     * ids		需要删除的id串逗号隔开
     * app_token		登录token
     */

    @DELETE("search/record")
    Call<DefaultBean> deleteSearchHistory(@Header("token") String token);

    /**
     * ----19.热搜排行榜----
     * limit		获取多少个（默认5）
     */



    /**
     * ----20.粉丝或关注列表----
     * app_token	String	登录用户令牌
     * sign		    G/F 关注/粉丝
     * page		    当前页数（默认1，每页显示10）
     */
    @FormUrlEncoded
    @POST("app/attentionreadlistajax")
    Call<FansCareBean> fansCareList(@Field("app_token") String app_token, @Field("sign") String sign, @Field("page") String page);

    /**
     * ----21.获取消息列表----
     * app_token	String	登录用户令牌
     * page		当前页数（默认1，每页显示10）
     */
    @FormUrlEncoded
    @POST("app/memberCenterMsg")
    Call<MessageBean> getMessage(@Field("app_token") String app_token, @Field("page") String page);

    /**
     * ----22.发送手机验证码----
     * phone	String	手机号码
     */
    /*,@Query("code")String code*/
    @GET("phone/code")
    Call<DefaultBean2> sendPhoneYanZheng(@Header("token") String app_token,@Query("key") String key,@Query("type") String type,@Query("phone") String phone);
    @POST("order/wx")
    Call<buyvipbean>buyvip(@Header("token") String app_token, @Query("payMonth") String payMonth, @Query("price") String price);
    @POST("order/aliPay")
    Call<buyvipaliPaybean>buyvip_aliPay(@Header("token") String app_token, @Query("payMonth") String payMonth, @Query("price") String price);


    /**
     * ----23.手机号绑定----
     * phone	    	手机号码
     * code		        验证码
     * app_token		登录用户令牌
     */
    @FormUrlEncoded
    @POST("app/phonebinding")
    Call<DefaultBean> bindPhone(@Field("phone") String phone, @Field("code") String code, @Field("app_token") String app_token);

    /**
     * ----24.修改密码----
     * old_jmi_pwd		旧密码
     * app_token		登录用户令牌
     * jmi_pwd		新密码
     * vcode		验证码
     * phone		发送验证码的手机号
     */
    @FormUrlEncoded
    @POST("app/pwdeditor")
    Call<DefaultBean4> changePassword(@Field("app_token") String app_token, @Field("vcode") String vcode, @Field("phone") String phone, @Field("old_jmi_pwd") String old_jmi_pwd, @Field("jmi_pwd") String jmi_pwd);

    /**
     * ----25.校验手机号验证码----
     * phone	String	手机号码
     * code		验证码
     */
    @FormUrlEncoded
    @POST("app/checkphonecode")
    Call<DefaultBean> yanZhengMa(@Field("phone") String phone, @Field("code") String code);

    /**
     * ----26.会员注册----
     * jmi_acc		用户名
     * jmi_pwd		密码
     */

    @POST("register")
    Call<DefaultBean3> registerUser(@Header("token") String app_token,@Query("phone") String phone,@Query("password") String password,@Query("code") String code,@Query("type") String type);
    @POST("invoice")
    Call<DefaultBean3> invoice_apply(@Header("token") String app_token,@Query("type") String type,@Query("invoiceTitle") String invoiceTitle,@Query("dutyNo") String dutyNo,@Query("content") String content,@Query("money") String money,@Query("recipients") String recipients,@Query("phone") String phone,@Query("address") String address,@Query("email") String email,@Query("orderIds") String orderIds);
    @POST("change/info")
    Call<DefaultBean3> changeUser(@Header("token") String app_token,@Query("phone") String phone,@Query("password") String password,@Query("code") String code,@Query("type") String type);
    @POST("retrievePassword")
    Call<DefaultBean3> retrievePassword(@Header("token") String app_token,@Query("phone") String phone,@Query("password") String password,@Query("code") String code,@Query("type") String type);

    /**
     * ----27.会员登录----
     * jmi_acc		用户名
     * jmi_pwd		密码
     * app_id		手机唯一标识
     */
   // @FormUrlEncoded
    @POST("account/login")
    Call<LoginBean> login(@Header("jmi_acc") String app_token, @Query("phone") String phone, @Query("password") String password);

    /**
     * ----28.会员退出----
     * app_token		登录token
     */
    @FormUrlEncoded
    @POST("app/goout")
    Call<DefaultBean4> goOut(@Field("app_token") String app_token);

    /**
     * ----29.上传头像(1)----
     * jmi_img		头像
     */
    @Multipart
    @POST("uploadfile/uploadForApp")
    Call<UploadPhotoBean> updateImage(@Part MultipartBody.Part file);

    /**
     * ----30.上传头像(2)----
     * jmi_img		图片链接
     * app_token		登录令牌
     */
   // @FormUrlEncoded
    @Multipart
    @POST("upload/images")
    Call<UpdateUserMsgBean> updateTouXiang(@Header("token") String app_token,@Part MultipartBody.Part file);

    /**
     * ----31.修改用户名称----
     * jmi_username		用户名称
     * app_token		登录令牌
     */
    @FormUrlEncoded
    @POST("app/editByToken")
    Call<UpdateUserMsgBean> updateName(@Field("jmi_username") String jmi_username, @Field("app_token") String app_token);

    /**
     * ----32.修改描述----
     * jmi_des		    描述
     * app_token		登录令牌
     */
    @FormUrlEncoded
    @POST("app/editByToken")
    Call<UpdateUserMsgBean> updateMiaoShu(@Field("jmi_des") String jmi_des, @Field("app_token") String app_token);

    /**
     * ----33.兴趣标签----
     * app_token		登录令牌
     * strand_id		兴趣标签
     * strand_user		用户编辑的字符串（逗号隔开）
     */
    @FormUrlEncoded
    @POST("app/editByToken")
    Call<UpdateUserMsgBean> updatePersonalChannel(@Field("app_token") String app_token, @Field("strand_id") String strand_id, @Field("strand_user") String strand_user);

    /**
     * ----34.获取动态----
     * app_token	登录用户令牌
     * page		    当前页数（默认1，每页显示10）
     */
    @FormUrlEncoded
    @POST("app/membertrend")
    Call<PersonalDynamicBean> getDynamic(@Field("app_token") String app_token, @Field("page") String page);

    /**
     * ----35.根据账号换密码----
     * jmi_pwd		密码
     * jmi_acc		账户
     */
    @FormUrlEncoded
    @POST("app/modifyPasswordByAcc")
    Call<DefaultBean4> forgetPassword(@Field("jmi_acc") String jmi_acc, @Field("jmi_pwd") String jmi_pwd);

    /**
     * ----36.研讨列表----
     * app_token		用户标识
     * currentpage		当前页（默认1）
     * pagesize		    每页显示条数（默认20）
     */
    @FormUrlEncoded
    @POST("app/getProblem")
    Call<YanTaoBean> yanTaoList(@Field("app_token") String app_token, @Field("currentpage") String currentpage, @Field("pagesize") String pagesize);

    /**
     * ----37.研讨详情（问题部分）----
     * app_token		token
     * id		        问题id（jp_id）
     */
    @FormUrlEncoded
    @POST("app/questions")
    Call<YanTaoDetailBean> yanTaoDetailQuestion(@Field("app_token") String app_token, @Field("id") String id);

    /**
     * ----38.研讨详情（观点部分）----
     * app_token		头像
     * jp_id		    问题id
     * currentpage		当前页（默认1）
     * pagesize		    页数大小（默认20）
     */
    @FormUrlEncoded
    @POST("app/answer")
    Call<YanTaoOptionsBean> yanTaoOptions(@Field("app_token") String app_token, @Field("jp_id") String jp_id, @Field("currentpage") String currentpage, @Field("pagesize") String pagesize);

    /**
     * ----39.研讨（发表观点）----
     * app_token		登录令牌
     * ja_ask_id		回答问题的id  id(提问问题)
     * ja_content		内容
     * ja_quote		    引用回答的id  回答的id
     * user_quote		引用回答的用户id 用户id
     */
    @FormUrlEncoded
    @POST("app/addAnswer")
    Call<DefaultBean> sendOptions(@Field("app_token") String app_token, @Field("ja_ask_id") String ja_ask_id, @Field("ja_content") String ja_content, @Field("ja_quote") String ja_quote, @Field("user_quote") String user_quote);

    /**
     * ----40.获取好友主页信息----
     * app_token		登录令牌
     * jm_id		    用户id
     */
    @FormUrlEncoded
    @POST("app/tohomepage")
    Call<FriendInfoBean> friendInfo(@Field("app_token") String app_token, @Field("jm_id") String jm_id);

    /**
     * ----41.获取好友主页动态----
     * id		用户id
     * page		页数（每页10条，默认1页）
     */
    @FormUrlEncoded
    @POST("app/personalMembertrend")
    Call<PersonalDynamicBean> getFriendDynamic(@Field("id") String id, @Field("page") String page);

    /**
     * ----42.获取好友主页研讨----
     * id		用户id
     * page		页数（每页10条，默认1页）
     */
    @FormUrlEncoded
    @POST("app/personalDiscuss")
    Call<FriendYantaoBean> getFriendYantao(@Field("id") String id, @Field("page") String page);

    /**
     * ----43.取消收藏----
     * app_token	登录用户令牌
     * id		    取消收藏对象的id
     */
    @FormUrlEncoded
    @POST("app/memberCenterCollect")
    Call<DefaultBean> cancelCollege(@Field("app_token") String app_token, @Field("id") String id);

    /**
     * ----44.删除浏览历史----
     * app_token	登录用户令牌
     * ids		    浏览历史对象的id
     */
    @FormUrlEncoded
    @POST("app/hitorydel")
    Call<DefaultBean> deleteReadHistory(@Field("app_token") String app_token, @Field("ids") String ids);

    /**
     * ----45.提交反馈----
     * feedback_id		反馈文章id
     * jf_type		    (A/B/C/D/E/F)视频，文章，图片，会员，网站,App
     * jf_strand_id		反馈id串（逗号隔开）
     * jf_content       用户对app的反馈内容
     */
    @FormUrlEncoded
    @POST("app/web/feedback")
    Call<DefaultBean> userFeedBack(@Field("feedback_id") String feedback_id, @Field("jf_type") String jf_type, @Field("jf_strand_id") String jf_strand_id, @Field("jf_content") String jf_content,@Field("app_token") String app_token);

    /**
     * ----46.获取研讨分类----
     */
    @GET("app/getDiscussNav")
    Call<SendYanTaoTypeBean> getYanTaoType();

    /**
     * ----47.发布研讨----
     * app_token	    登录用户令牌(必填)
     * jp_type_id		分类id(必填)
     * jp_title		    标题(必填)
     * jp_content		内容（选填）
     * jp_strand_id		标签串（用逗号隔开）（选填）
     */
    @FormUrlEncoded
    @POST("app/discuss")
    Call<DefaultBean5> sendYanTao(@Field("app_token") String app_token, @Field("jp_type_id") String jp_type_id, @Field("jp_title") String jp_title, @Field("jp_content") String jp_content, @Field("jp_strand_id") String jp_strand_id);

    /**
     * ----48.个人兴趣----
     * app_token	    登录用户令牌(必填)
     */
    @FormUrlEncoded
    @POST("app/getMyInterestLabel")
    Call<InterestBean> getMyInterest(@Field("app_token") String app_token);

    /**
     * ----49.举报文章----
     * report_id		    文章id
     * jr_type		        举报类型  文章/会员(A/B)
     * jr_article_type		文章类型
     * jr_strand_id		    选择举报内容的id串,”,”号隔开
     * app_token		    登录用户令牌
     */
    @FormUrlEncoded
    @POST("app/articlereport")
    Call<DefaultBean> http_report(@Field("app_token") String app_token, @Field("jr_type") String jr_type, @Field("jr_article_type") String jr_article_type, @Field("jr_strand_id") String jr_strand_id, @Field("report_id") String report_id);

    /**
     * ----50.邀请回答----
     * app_token		登录令牌
     * jp_id		    回答问题的id  id(提问问题)
     * jmm_receive		邀请的id
     */
    @FormUrlEncoded
    @POST("app/invitation")
    Call<DefaultBean> http_invitation(@Field("app_token") String app_token, @Field("jp_id") String jp_id, @Field("jmm_receive") String jmm_receive);

    /**
     * ----51.第三方登录----
     * jmi_username		昵称
     * jmi_img		    头像
     * jmi_open_id		唯一标识
     * jmi_soure        来源 注册/微信/QQ/微博/军采通（A/B/C/D/E）
     */
    @FormUrlEncoded
    @POST("other/login")
    Call<DefaultBean4> thirdLogin(@Field("app_token")String app_token, @Field("loginType") String loginType,@Field("accessToken") String accessToken,@Field("openId") String jmi_open_id);
    /**
     * ----52.删除消息----
     * app_token	登录用户令牌
     * jmm_id		消息ID
     */
    @FormUrlEncoded
    @POST("app/memberCenterMsgDel")
    Call<DefaultBean> deleteMessage(@Field("app_token") String app_token, @Field("jmm_id") String jmm_id);

    /**
     * ----53.设为最佳观点----
     * ja_id		观点id
     */
    @FormUrlEncoded
    @POST("app/setPre")
    Call<DefaultBean> setBestOption(@Field("ja_id") String ja_id);
    /**
     * ----54.获取最新版本号----
     */
    @GET("app/version/record/newest")
    Call<VersionBean> getVersion();
    /**
     * ----55.获取订阅分类----
     */
    @GET("app/source/type/list")
    Call<DYTypeBean> getDingYueTypes();
    /**
     * ----56.订阅分类详情列表----
     * pageNum		当前页，默认1
       pageSize		每页大小，默认20
       jst_id		信息源分类id
       app_token	登录令牌
     */
    @FormUrlEncoded
    @POST("app/type/source/list")
    Call<DYListBean> getDYList(@Field("pageNum") String pageNum,@Field("jst_id")String jst_id,@Field("app_token")String app_token);
    /**
     * ----57.添加订阅----
     * js_id		信息源ID（添加自定义信息源时不使用）
       jms_name		自定义信息源名称（添加非自定义信息源时不使用）
       app_token	登录令牌
     */
    @FormUrlEncoded
    @POST("app/source/add")
    Call<DefaultBean> addDingYue(@Field("js_id") String js_id,@Field("jms_name")String jms_name,@Field("app_token")String app_token);
    /**
     * ----58.删除订阅（非自定义）----
     * js_ids		要删除的id串，用逗号隔开
       app_token	登录令牌
     */
    @FormUrlEncoded
    @POST("app/source/delete/follow")
    Call<DefaultBean> deleteDingYueUnZDY(@Field("js_ids") String js_ids,@Field("app_token")String app_token);
    /**
     * ----59.获取信息源主页（非自定义）----
     * pageNum		    当前页，默认1
       js_id	    	信息源id
       app_token		登录令牌
     */
    @FormUrlEncoded
    @POST("app/source/home")
    Call<DYMainBean> getDYMain(@Field("pageNum") String pageNum,@Field("js_id")String js_id,@Field("app_token")String app_token);
    /**
     * ----60.获取订阅Item（自定义）----
     * app_token	登录令牌
     */
    @FormUrlEncoded
    @POST("app/source/my/custom")
    Call<MyDYItemBean> getMyDYItem(@Field("app_token")String app_token);
    /**
     * ----61.删除订阅（自定义）----
     * jms_ids		要删除的id串，用逗号隔开
     * app_token	登录令牌
     */
    @FormUrlEncoded
    @POST("app/source/delete/custom")
    Call<DefaultBean> deleteMyDYItem(@Field("jms_ids")String jms_ids,@Field("app_token")String app_token);
    /**
     * ----62.浏览关键词（自定义）----
     * pageNum		当前页，默认1
       keyword		自定义词
     */
    @FormUrlEncoded
    @POST("app/member/source/preview")
    Call<VisitedDYBean> visitedMyDYItem(@Field("pageNum")String pageNum,@Field("keyword")String keyword);
    /**
     * ----63.获取信息源主页（自定义）----
     * pageNum	 	当前页，默认1
       jms_id	 	信息源id
       app_token	登录令牌
     */
    @FormUrlEncoded
    @POST("app/member/source/home")
    Call<DYMainBean> getMyDYMain(@Field("pageNum") String pageNum,@Field("jms_id")String jms_id,@Field("app_token")String app_token);
    /**
     * ----64.获取订阅频道列表----
     * pageNum		当前页，默认1
     * app_token	登录令牌
     */
    @FormUrlEncoded
    @POST("app/subscribe")
    Call<VisitedDYBean> getDYListMain(@Field("pageNum") String pageNum,@Field("app_token")String app_token);
    /**
     * ----65.获取新闻列表（New）----
     * pageNum		      当前页，默认1
     * pageSize		      每页大小，默认20
     * pid		          分类父ID :(1 热点)/(2 推荐)/(5 视频)/(6 图片)/(170 订阅)
     * cid	     	      分类ID（频道id）
     * app_token	 	  登录令牌
     * jct_user_cooike	  Cookie值（推荐时传值）
     */
    @FormUrlEncoded
    @POST("app/article/list")
    Call<NewNewsListBean> getNewNewsList(@Field("pageNum") String pageNum,@Field("pid") String pid,@Field("cid") String cid,@Field("app_token")String app_token,@Field("jct_user_cooike")String jct_user_cooike);
}
