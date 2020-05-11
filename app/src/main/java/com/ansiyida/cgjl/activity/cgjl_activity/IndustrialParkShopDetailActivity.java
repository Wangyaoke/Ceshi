package com.ansiyida.cgjl.activity.cgjl_activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.InvestmentCaseAdapter;
import com.ansiyida.cgjl.adapter.cgjl_adapter.InvestmentTeamAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.FinancialServeIntroduceBean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndustrialParkShopDetailActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.Top_back)
    ImageView TopBack;
    @Bind(R.id.shop_name)
    TextView shopName;
    @Bind(R.id.shop_introduce_back)
    RelativeLayout shopIntroduceBack;
    @Bind(R.id.shop_introduce_share)
    RelativeLayout shopIntroduceShare;
    @Bind(R.id.shop_introduce_Communicate)
    Button shopIntroduceCommunicate;
    @Bind(R.id.shop_introduce_keywordOne)
    TextView shopIntroduceKeywordOne;
    @Bind(R.id.shop_introduce_keywordTwo)
    TextView shopIntroduceKeywordTwo;
    @Bind(R.id.shop_introduce_keywordThree)
    TextView shopIntroduceKeywordThree;
    @Bind(R.id.shop_introduce_CommunicationNumber)
    TextView shopIntroduceCommunicationNumber;
    @Bind(R.id.shop_introduce_enterprise_introduction_webview)
    WebView shopIntroduceEnterpriseIntroductionWebview;
    @Bind(R.id.Investment_Team_recycler)
    RecyclerView InvestmentTeamRecycler;
    @Bind(R.id.InvestmentCase_gridview)
    RecyclerView InvestmentCaseGridview;
    @Bind(R.id.view5)
    View view5;
    @Bind(R.id.shop_introduce_keywordFour)
    TextView shopIntroduceKeywordFour;
    @Bind(R.id.veiw1)
    View veiw1;
    @Bind(R.id.veiw2)
    View veiw2;
    private List<String> ImageList = new ArrayList<>();
    private InvestmentTeamAdapter investmentTeamAdapter;
    private InvestmentCaseAdapter investmentCaseAdapter;
    private int id;
    private Bitmap myBitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        GoogleAssistant(IndustrialParkShopDetailActivity.this,"Android"+shopName.getText()+"详情","IndustrialShopActivity");
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_industrial_park_shop_introduce;
    }

    @Override
    protected void initView() {
        investmentTeamAdapter = new InvestmentTeamAdapter(this);
        investmentCaseAdapter = new InvestmentCaseAdapter(this);
        InvestmentTeamRecycler.setLayoutManager(new LinearLayoutManager(this));

        InvestmentTeamRecycler.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        InvestmentCaseGridview.setLayoutManager(new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        InvestmentTeamRecycler.setAdapter(investmentTeamAdapter);
        InvestmentCaseGridview.setAdapter(investmentCaseAdapter);
        setWebview(shopIntroduceEnterpriseIntroductionWebview);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);
    }

    @Override
    protected void httpData() {
        String app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
        try {
            Call<FinancialServeIntroduceBean> financialServeIntroduceCall = MyApplication.getNetApi().getFinancialServeIntroduce(app_token, id);
            financialServeIntroduceCall.enqueue(new Callback<FinancialServeIntroduceBean>() {
                @Override
                public void onResponse(Call<FinancialServeIntroduceBean> call, Response<FinancialServeIntroduceBean> response) {
                    if (response.isSuccessful()) {
                        FinancialServeIntroduceBean body = response.body();
                        if (body.getStatus() == 200) {
                            FinancialServeIntroduceBean.DataBean data = body.getData();
                            if (data.getFinancialServeModelLists().size() > 0) {
                                investmentTeamAdapter.setListsBeans(data.getFinancialServeModelLists());
                            }
                            if (data.getFinancialServeModelTexts().size() > 0) {

                            }
                            String investCaseImg = data.getInvestCaseImg();
                            String[] split = investCaseImg.split(",");
                            if (split.length > 0) {
                                investmentCaseAdapter.setImageArray(split);
                            }
                            shopIntroduceCommunicationNumber.setText(data.getApplyForNumber() + "人沟通");
                            shopName.setText(data.getName());
                            String[] keyword = data.getLabel().split(",");
                            for (int i = 0; i < keyword.length; i++) {
                                if (i == 0) {
                                    shopIntroduceKeywordOne.setVisibility(View.VISIBLE);
                                    shopIntroduceKeywordOne.setText(keyword[i]);
                                } else if (i == 1) {
                                    veiw1.setVisibility(View.VISIBLE);
                                    shopIntroduceKeywordTwo.setVisibility(View.VISIBLE);
                                    shopIntroduceKeywordTwo.setText(keyword[i]);
                                } else if (i == 2) {
                                    veiw2.setVisibility(View.VISIBLE);
                                    shopIntroduceKeywordThree.setVisibility(View.VISIBLE);
                                    shopIntroduceKeywordThree.setText(keyword[i]);
                                } else if (i == 3) {
                                    view5.setVisibility(View.VISIBLE);
                                    shopIntroduceKeywordFour.setVisibility(View.VISIBLE);
                                    shopIntroduceKeywordFour.setText(keyword[i]);
                                }
                            }
                            String[] banner = data.getBanner().split(",");
                            if (banner.length > 0) {
                                Glide.with(IndustrialParkShopDetailActivity.this).load(banner[0]).centerCrop().into(TopBack);
                            }
                            if (data.getFinancialServeModelTexts().size() > 0) {
                                shopIntroduceEnterpriseIntroductionWebview.loadDataWithBaseURL(null, getHtml(data.getFinancialServeModelTexts().get(0).getContent()), "text/html", "utf-8", null);
                            }

                        } else {
                            Log.e("金融商品详情失败", "onResponse: " + response.toString());
                        }
                    } else {
                        Log.e("金融商品详情失败", "onResponse: " + response.toString());
                    }
                }

                @Override
                public void onFailure(Call<FinancialServeIntroduceBean> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Log.e("Exception", "httpData: " + e.getMessage());
        }


    }

    @Override
    protected void setClickListener() {
        shopIntroduceBack.setOnClickListener(this);
        shopIntroduceShare.setOnClickListener(this);
        shopIntroduceCommunicate.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shop_introduce_Communicate:
                if ("true".equals(((String) SharedPreferenceUtils.get(this, "vistor", "")))) {
                    Intent intent = new Intent(IndustrialParkShopDetailActivity.this, IndustrialShopCommunicateActivity.class);
                    intent.putExtra("id", id);
                    this.startActivity(intent);
                } else {
                    ToastUtils.showMessage(this, "请先登录");
                }
                break;
            case R.id.shop_introduce_back:
                this.finish();
                break;
            case R.id.shop_introduce_share:
                WeChatShare(id + "", shopName.getText().toString());
                break;
        }
    }

    public void setWebview(WebView webview) {
        WebSettings webSettings = webview.getSettings();
//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
// 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
// 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即
//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
////其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);//
    }

    public String getHtml(String html_body) {
        //字符串html_body==精灵智库内容
        String html = "<!DOCTYPE  html>\n" +
                "<html><head><meta  charset=\"utf-8\">\n" +
                "<title></title>\n" +
                "<meta  content=\"width=device-width,  initial-scale=1.0,  minimum-scale=1.0,  maximum-scale=1.0,  user-scalable=no\"  name=\"viewport\"  />\n" +
                "<meta  http-equiv=\"Content-Type\"  content=\"text/html;  charset=UTF-8\"  />\n" +
                "<meta  http-equiv=\"Pragma\"  content=\"no-cache\"  />\n" +
                "<meta  http-equiv=\"Cache-Control\"  content=\"no-cache\"  />\n" +
                "<meta  http-equiv=\"Expires\"  content=\"0\"  />\n" +
                "<meta  http-equiv=\"Access-Control-Allow-Origin\"  content=\"*\"  />\n" +
                "<meta  name=\"format-detection\"  content=\"telephone=no\"  />\n" +
                "<style>  \n" +
                "body{  \n" +
                "\tpadding:0  0px;\n" +
                "\tfont-size:12px;\n" +
                "\tmargin:0;\n" +
                /*   "\tfont-family:\"Microsoft  YaHei\\\",\\\"Open  Sans\\\",\\\"Helvetica  Neue\\\",Helvetica,Arial,sans-serif\";\n" +*/
                "\tfont-weight:300;  word-break:  break-all;}\n" +
                "p{color:#FF616161;text-align:left;line-height:30px;font-size:12px !important; }\n" +
                "span{color:#FF616161;text-align:left;line-height:30px;font-size:12px !important; }\n" +
                "blockquote  p{margin-top:16px;}\n" +
                "blockquote{padding:16px;    background-color:#f6f6f6;  font-size:12px;  display:  block;  -webkit-margin-before:  1em;  -webkit-margin-after:  0em;-webkit-margin-start:  0px;-webkit-margin-end:  0px;}\n" +
                "img{max-width:100%;  height:auto;}\n" +
                "div{text-align:left;line-height:30px;font-size:12px !important;}\n" +
                "h1{font-size:24px;}\n" +
                "h2{font-size:21px;}\n" +
                "h3{font-size:19px;}\n" +
                "iframe,img  {max-width:  100%;  }\n" +
                "a{color:#4d8ae7;}\n" +
                "</style>\n" +
                "  <script>\n" +
                "\t\tfunction getImgArray(){\n" +
                "\t\t\tvar arr=document.getElementsByTagName('img');\n" +
                "\t\t\tvar imgArray=[];\n" +
                "\t\t\tfor(var x=0;x<arr.length;x++){\n" +
                "\t\t\t\timgArray[x]=arr[x].src;\n" +
                "\t\t\t}\n" +
                "\t\t\treturn imgArray;\n" +
                "\t\t}\t\n" +
                "  </script>" +
                " <style>\n" +
                "  img {\n" +
                "\twidth:100% !important;\n" +
                "\theight:auto !important;\n" +
                "  }\n" +
                "  </style>" +
                "  </style>" +
                "</head>  \n" +
                "<body>\n" +
                html_body +
                "</body>\n" +
                "</html>";
        html = html.replace("<img", "<img onclick='window.android.showToast(getImgArray(),this.src)'");
        html = html.replace("text-align:center;text-indent:2em;", "text-align:center;");
        return html;
    }

    public void WeChatShare(String id, String title) {
        if (myBitmap == null) {
            myBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.app_icon);
        }
        final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[]
                {
                        SHARE_MEDIA.WEIXIN,
                        SHARE_MEDIA.WEIXIN_CIRCLE,
                        SHARE_MEDIA.SINA,
                        SHARE_MEDIA.QQ,
                        SHARE_MEDIA.QZONE
                };
        UMImage image = new UMImage(IndustrialParkShopDetailActivity.this.getApplicationContext(), myBitmap);
        UMWeb web = new UMWeb("");
        web = new UMWeb("http://cg.calcnext.com/detail/#/financialCon?id=" + id);
        //web = new UMWeb("http://cg.calcnext.com/detail/#/?type=viewpoint&id=" + id);
        //标题
        web.setTitle(title);
        //缩略图
        web.setThumb(image);
        web.setDescription("新颖的商品 给人焕然一新的感觉！");//描述
        new ShareAction(IndustrialParkShopDetailActivity.this)
                .withMedia(web)
                .setDisplayList(displaylist)
                .setCallback(umShareListener)
                .open();
    }

    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }


        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showMessage(IndustrialParkShopDetailActivity.this, "分享成功");


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(IndustrialParkShopDetailActivity.this, "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("QZONE")) {

                ToastUtils.showMessage(IndustrialParkShopDetailActivity.this, "您未安装QQ客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN")) {

                ToastUtils.showMessage(IndustrialParkShopDetailActivity.this, "您未安装微信客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                ToastUtils.showMessage(IndustrialParkShopDetailActivity.this, "您未安装微信客户端，无法分享");
            } else {
                ToastUtils.showMessage(IndustrialParkShopDetailActivity.this, "分享失败,msg:" + t.getMessage());

            }
            LogUtil.i("share", "分享失败,msg:" + t.getMessage());
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {

        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

}
