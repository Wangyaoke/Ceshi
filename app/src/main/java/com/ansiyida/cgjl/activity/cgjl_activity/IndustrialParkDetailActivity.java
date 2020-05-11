package com.ansiyida.cgjl.activity.cgjl_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.IndustrialRecyclerAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.IndustrialParkDetailBean;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.GlideCircleTransform;
import com.bumptech.glide.Glide;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IndustrialParkDetailActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.rent_text)
    TextView rentText;
    @Bind(R.id.Xbanner)
    XBanner Xbanner;
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.Application_for_admission_btn)
    Button ApplicationForAdmissionBtn;
    @Bind(R.id.Detail_Recycler)
    RecyclerView Detail_Recycler;
    @Bind(R.id.XWDT_Recycler)
    RecyclerView XWDTRecycler;
    @Bind(R.id.webview)
    WebView webview;
    private int industriId;
    //轮播
    private String[] bannerSplit;
    private IndustrialRecyclerAdapter recyclerAdapter;
    private String address;
    @Override
    protected int getContentView() {
        return R.layout.activity_industrialpark;
    }

    @Override
    protected void initView() {
        recyclerAdapter = new IndustrialRecyclerAdapter(this);
        Detail_Recycler.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        XWDTRecycler.setLayoutManager(new LinearLayoutManager(this) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        });
        XWDTRecycler.setAdapter(recyclerAdapter);

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        industriId = intent.getIntExtra("id", 0);
    }


    @Override
    protected void httpData() {

        if (NetWorkUtils.isNetworkConnected(this)) {
            String vistor = (String) SharedPreferenceUtils.get(this, "vistor", "");
            String app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
            Call<IndustrialParkDetailBean> InDustrialCall = MyApplication.getNetApi().getIndustrialParkDetail(app_token, industriId);
            InDustrialCall.enqueue(new Callback<IndustrialParkDetailBean>() {
                @Override
                public void onResponse(Call<IndustrialParkDetailBean> call, Response<IndustrialParkDetailBean> response) {
                    if (response.isSuccessful()) {
                        IndustrialParkDetailBean body = response.body();
                        if (body.getStatus() == 200) {
                            IndustrialParkDetailBean.DataBean.CivilMilitaryBean civilMilitary = body.getData().getCivilMilitary();
                            address = civilMilitary.getAddress();
                            textTitle.setText(civilMilitary.getName());
                            rentText.setText("￥"+civilMilitary.getUnitPrice()+"元");
                            //轮播
                            String banner = civilMilitary.getBanner();
                            if (banner != null) {
                                bannerSplit = banner.split(",");
                                Banner();
                            }
                            if (body.getData().getListDetails().size() > 0) {
                                recyclerAdapter.setListDetailsBeans(body.getData().getListDetails());
                            }
                            if (body.getData().getTextDetails().size() > 0) {
                                recyclerAdapter = new IndustrialRecyclerAdapter(IndustrialParkDetailActivity.this);
                                Detail_Recycler.setAdapter(recyclerAdapter);
                                recyclerAdapter.setTextDetailsBean(body.getData().getTextDetails());
                            }
                        } else {
                            ToastUtils.showMessage(IndustrialParkDetailActivity.this, response.toString());
                        }
                    } else {
                        ToastUtils.showMessage(IndustrialParkDetailActivity.this, response.toString());
                    }
                }

                @Override
                public void onFailure(Call<IndustrialParkDetailBean> call, Throwable t) {
                    Log.e("IndustrialParkDetail", "onFailure: " + t.getMessage());
                }
            });
        }
        GoogleAssistant(IndustrialParkDetailActivity.this,"Android"+textTitle.getText()+"详情","IndustrialShopActivity");

    }

    private void Banner() {
        //给轮播图赋值
        if (bannerSplit.length > 0) {
            final List<String> bannerList = new ArrayList<>();
            for (int i = 0; i < bannerSplit.length; i++) {
                bannerList.add(bannerSplit[i]);
            }
            Xbanner.setData(bannerList, null);
            Xbanner.loadImage(new XBanner.XBannerAdapter() {
                @Override
                public void loadBanner(XBanner banner, Object model, View view, int position) {
                    Glide.with(IndustrialParkDetailActivity.this)
                            .load(bannerList.get(position))
                            .transform(new GlideCircleTransform(IndustrialParkDetailActivity.this))
                            .into((ImageView) view);
                }
            });
        }
    }

    @Override
    protected void setClickListener() {
       webview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN) {
                //按返回键操作并且能回退网页
                if (keyCode == KeyEvent.KEYCODE_BACK && webview.canGoBack()) {
                    //后退
                     webview.goBack();
                    return true;
                }
            }
            return false;
            }
        });
        webview.setWebViewClient(new MyWebViewClient());
        imageBack.setOnClickListener(this);
        ApplicationForAdmissionBtn.setOnClickListener(this);
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            view.getSettings().setJavaScriptEnabled(true);
            if(url.equals("http://192.168.32.207:8088/#/CivilMilitary")){
                finish();
            }
            String[] split = url.split("\\?");
            if(split[0].equals("http://192.168.32.207:8088/#/corporationFrom")){
                Intent intent = new Intent(IndustrialParkDetailActivity.this, IndustrialParkSettledInActivity.class);
                intent.putExtra("id",industriId);
                intent.putExtra("address",address);
                startActivity(intent);
                webview.goBack();
            }
            super.onPageFinished(view, url);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_back:
                this.finish();
                break;
            case R.id.Application_for_admission_btn:
                if ("true".equals(((String) SharedPreferenceUtils.get(this, "vistor", "")))) {
                    Intent intent = new Intent(IndustrialParkDetailActivity.this, IndustrialParkSettledInActivity.class);
                    intent.putExtra("id", industriId);
                    intent.putExtra("address",address);
                    this.startActivity(intent);
                }else{
                    ToastUtils.showMessage(this,"请先登录");
                }
                break;
        }
    }
    public void setWebview(WebView webview) {
        WebSettings webSettings = webview.getSettings();
       //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
    }

}
