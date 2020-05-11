package com.ansiyida.cgjl.activity.cgjl_activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.StudyMenuAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.StudyRecyclerBean;
import com.ansiyida.cgjl.bean.college_bean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KnowledgeDetailActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.study_menu_setting)
    ImageView studyMenuSetting;
    @Bind(R.id.iv_back)
    ImageView ivBack;
    @Bind(R.id.iv_college_top)
    ImageView ivCollegeTop;
    @Bind(R.id.iv_share)
    ImageView ivShare;
    @Bind(R.id.study_pdf)
    PDFView showPDF;
    @Bind(R.id.page_percentage)
    TextView page_Percentage;
    @Bind(R.id.page_Count)
    TextView page_Count;

    private String studyTypeIds;

    private ProgressDialog mProgressDialog;
    // 下载失败
    public static final int DOWNLOAD_ERROR = 2;
    // 下载成功
    public static final int DOWNLOAD_SUCCESS = 1;
    private File file1;
    private List<StudyRecyclerBean.DataBean.AllBean> list = new ArrayList();
    //计数
    private int ListNum ;
    private int colletType = 0;
    /**
     * 下载完成后  直接打开文件
     */
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DOWNLOAD_SUCCESS:
                    File file = (File) msg.obj;
                    //加载pdf图片
                    displayFromFile(file);
                    break;
                case DOWNLOAD_ERROR:
                    ToastUtils.showMessage(KnowledgeDetailActivity.this, "文件加载失败");
                    break;
            }
        }
    };
    private SlidingMenu menu;
    private ImageView menu_image;
    private RecyclerView study_menu_recyclerView;
    private TextView no_catalog;
    private StudyMenuAdapter studyMenuAdapter;
    private String resoure;
    private String searchUri;
    private String searchid;
    private Bitmap myBitmap;
    private String searchTitle;
    private List<college_bean.DataBean.list_law_bean> colletList;
    private String colletid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        GoogleAssistant(KnowledgeDetailActivity.this,"Android学院详情","KnowledgeDetailActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_study_one;
    }
    @Override
    protected void initData() {

        //获取上个页面传递的值
        Intent intent = getIntent();
        resoure = intent.getStringExtra("resoure");
        if(resoure.equals("fragment2")) {
            studyTypeIds = intent.getStringExtra("studyTypeId");
        }else if(resoure.equals("knowledgeSearch")){
            searchUri = intent.getStringExtra("content");
            searchid = intent.getStringExtra("id");
            studyTypeIds = intent.getStringExtra("studyTypeId");
            searchTitle = intent.getStringExtra("title");
        }else if(resoure.equals("Collet")){
            studyTypeIds = intent.getStringExtra("studyTypeId");
            colletid = intent.getStringExtra("id");
            searchTitle = intent.getStringExtra("title");
        }
    }

    @Override
    protected void httpData() {
        if(resoure.equals("fragment2") || resoure.equals("Collet")) {
            try {
                    if (NetWorkUtils.isNetworkConnected(this)) {
                        Call<StudyRecyclerBean> call = MyApplication.getNetApi().getKnowledge((String) SharedPreferenceUtils.get(this, "app_token", ""), Integer.parseInt(studyTypeIds), 0);
                        call.enqueue(new Callback<StudyRecyclerBean>() {
                            @Override
                            public void onResponse(Call<StudyRecyclerBean> call, Response<StudyRecyclerBean> response) {
                                StudyRecyclerBean body = response.body();
                                if (response.isSuccessful()) {
                                    Log.e("onResponse", "onResponse: "+response.body().toString() );
                                    list.addAll(body.getData().getAll());
                                    if (list.size() != 0) {
                                        if(resoure.equals("fragment2") ) {
                                            ListNum = 0;
                                            loadPdf(list.get(0).getContent());
                                        }else if( resoure.equals("Collet")){
                                            for (int i = 0; i < list.size(); i++) {

                                                if(colletid.equals(list.get(i).getId()+"") ){
                                                    loadPdf(list.get(i).getContent());
                                                    ListNum = i;
                                                }
                                            }
                                            ivCollegeTop.setImageResource(R.mipmap.yantao_college_yes);
                                            colletType = DOWNLOAD_SUCCESS;
                                        }
                                        studyMenuAdapter.notifyDataSetChanged();

                                    }
                                    getColletTake();
                                } else {
                                    Log.e("KnowledgeDetailActivity", "onResponse: body好像为空");
                                }
                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<StudyRecyclerBean> call, Throwable t) {
                                Log.e("KnowledgeDetailActivity", "onFailure: " + t.getMessage());
                            }
                        });
                    }else{
                        ToastUtils.showMessage(this,"请先检查一下您的网络状态！");
                    }
            } catch (Exception e) {
                Log.e("Study_Exception", e.getMessage() );
            }
        }else if(resoure.equals("knowledgeSearch")){
            loadPdf(searchUri);
            getColletTake();
        }

    }

    @Override
    protected void setClickListener() {
        ivBack.setOnClickListener(this);
        studyMenuSetting.setOnClickListener(this);
        ivCollegeTop.setOnClickListener(this);
        ivShare.setOnClickListener(this);
        menu.setOnClosedListener(new SlidingMenu.OnClosedListener() {
            @Override
            public void onClosed() {
                studyMenuSetting.setVisibility(View.VISIBLE);
            }
        });
        //接口回调
        studyMenuAdapter.setStudyMenuAdapterClick(new StudyMenuAdapter.StudyMenuAdapterClick() {
            @Override
            public void clickItem(int position) {
                ListNum = position;
                if(menu.isMenuShowing()) {
                    menu.toggle(false);
                }
                studyMenuSetting.setVisibility(View.VISIBLE);
                ivCollegeTop.setImageResource(R.mipmap.icon_subscription);
                getColletTake();
                loadPdf(list.get(position).getContent());
                resoure = "fragment2";
            }
        });
    }
    //加载pdf
    private void displayFromFile(File file) {
        if (file.getName().isEmpty()) {
            Toast.makeText(this, "名字为空", Toast.LENGTH_SHORT).show();
        } else {
            showPDF.setVisibility(View.VISIBLE);
            showPDF.fromFile(file)
                    //是否允许翻页，默认是允许翻页
                    .enableSwipe(true)
                    //pdf文档翻页是否是垂直翻页，默认是左右滑动翻页
                    .swipeHorizontal(false)
                    //
                    .enableDoubletap(false)
                    //设置默认显示第0页
                    .defaultPage(0)
                    //允许在当前页面上绘制一些内容，通常在屏幕中间可见。
//                .onDraw(onDrawListener)
//                // 允许在每一页上单独绘制一个页面。只调用可见页面
//                .onDrawAll(onDrawListener)
                    //设置加载监听
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {

                        }
                    })
                    //设置翻页监听
                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            if ((page + 1) > 0 && pageCount > 0) {
                                page_Count.setText(page + 1 + "/" + pageCount);
                                Float ff = (Float.valueOf(page) + 1) / Float.valueOf(pageCount);
                                float f = Float.parseFloat(ff * 100 + "");
                                DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                                String p = decimalFormat.format(f);//format 返回的是字符串
                                page_Percentage.setText(p + "%");
                            }
                        }
                    })
                    // 渲染风格（就像注释，颜色或表单）
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    // 改善低分辨率屏幕上的渲染
                    .enableAntialiasing(true)
                    // 页面间的间距。定义间距颜色，设置背景视图
                    .spacing(1)
                    .load();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private void getColletTake() {
        if (NetWorkUtils.isNetworkConnected(this)) {
            Call<college_bean> college_beanCall = MyApplication.getNetApi().SelCollectionRecord((String) SharedPreferenceUtils.get(KnowledgeDetailActivity.this, "app_token", ""), 1 + "", 100 + "", true, "knowledge");
            college_beanCall.enqueue(new Callback<college_bean>() {
                @Override
                public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                    college_bean body = response.body();
                    Log.e("getColletTake", response.body().toString());
                    if (response.isSuccessful()) {
                        try {
                            if (response.body().getData().getlist_law_bean().size() > 0) {
                                colletList = body.getData().getlist_law_bean();
                                if (resoure.equals("fragment2")) {
                                    boolean bol = false;
                                    for (int i = 0; i < colletList.size(); i++) {
                                        if (colletList.get(i).getinfoId().equals(list.get(ListNum).getId() + "")) {
                                            bol = true;
                                            ivCollegeTop.setImageResource(R.mipmap.yantao_college_yes);
                                        }
                                    }
                                    if (bol) {
                                        colletType = DOWNLOAD_SUCCESS;
                                    } else {
                                        colletType = DOWNLOAD_ERROR;
                                    }

                                } else if (resoure.equals("knowledgeSearch")) {
                                    boolean bol = false;
                                    for (int i = 0; i < colletList.size(); i++) {
                                        if (colletList.get(i).getinfoId().equals(searchid)) {
                                            bol = true;
                                            ivCollegeTop.setImageResource(R.mipmap.yantao_college_yes);
                                        }
                                    }
                                    if (bol) {
                                        colletType = DOWNLOAD_SUCCESS;
                                    } else {
                                        colletType = DOWNLOAD_ERROR;
                                    }
                                }
                            }
                        }catch (Exception e){

                        }
                    }
                }

                @Override
                public void onFailure(Call<college_bean> call, Throwable t) {
                    Log.e("StudyOneActivity_Collet", "onFailure: " + t.getMessage());
                }
            });
        }else{
            ToastUtils.showMessage(this,"请检查您的网络状态");
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.study_menu_setting:
                if(resoure.equals("knowledgeSearch")) {
                    no_catalog.setVisibility(View.VISIBLE);
                }
                studyMenuSetting.setVisibility(View.GONE);
                menu.showMenu();
                studyMenuAdapter.JudgeColor(ListNum);
                break;
            case R.id.iv_back:
                //菜单展示的时候先返回菜单
                if(menu.isMenuShowing()){
                    menu.toggle(false);
                }else if(!menu.isMenuShowing()){
                    finish();
                }
                break;
            case R.id.iv_college_top:
                if("true".equals(SharedPreferenceUtils.get(KnowledgeDetailActivity.this, "vistor", ""))) {
                    if (colletType != DOWNLOAD_SUCCESS) {
                        //收cang
                        if (resoure.equals("fragment2")) {
                            Collet(list.get(ListNum).getId() + "");
                        } else if (resoure.equals("knowledgeSearch")) {
                            Collet(searchid);
                        }
                    } else {
                        //取消收藏
                        if (resoure.equals("fragment2")) {
                            CancelCollet(list.get(ListNum).getId() + "");
                        } else if (resoure.equals("knowledgeSearch")) {
                            CancelCollet(searchid);
                        }
                    }
                    if (resoure.equals("Collet")) {
                        if (colletType == 1) {
                            CancelCollet(colletid);
                        } else {
                            Collet(colletid);
                        }
                    }
                }else{
                    ToastUtils.showMessage(KnowledgeDetailActivity.this,"请先登录");
                }
                break;
            case R.id.iv_share:
               if(resoure.equals("fragment2")){
                   //学习页面
                   WeChatShare(list.get(ListNum).getId()+"",list.get(ListNum).getTitle());
               }else if(resoure.equals("knowledgeSearch")){
                   //搜索页面
                   WeChatShare(searchid,searchTitle);
               }else if(resoure.equals("Collet")){
                   WeChatShare(colletid,searchTitle);
               }
                break;
        }
    }

    private void Collet(final String id) {
        //收藏
        if (NetWorkUtils.isNetworkConnected(this)) {
            Call<college_bean> call = MyApplication.getNetApi().getCollectionRecord((String) SharedPreferenceUtils.get(KnowledgeDetailActivity.this, "app_token", ""), true, "knowledge", id);
            call.enqueue(new Callback<college_bean>() {
                @Override
                public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                    if (response.isSuccessful()) {
                        Object body = response.body();
                        if (response.body().getStatus().equals("200")) {
                            ToastUtils.showMessage(KnowledgeDetailActivity.this,"收藏成功");
                            colletType = DOWNLOAD_SUCCESS;
                            ivCollegeTop.setImageResource(R.mipmap.yantao_college_yes);
                            if(resoure.equals("knowledgeSearch")) {
                                SharedPreferenceUtils.put(KnowledgeDetailActivity.this, "KnowSearchId", id);
                                SharedPreferenceUtils.put(KnowledgeDetailActivity.this, "KnowSearchCollege","true");
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<college_bean> call, Throwable t) {
                    Log.e("StudyOnActivity", "ColletonFailure: " + t.getMessage());
                }
            });
        }else{
            ToastUtils.showMessage(this,"请检查您的网络状况");
        }
    }
    private void CancelCollet(final String id) {
        //取消收藏
        if (NetWorkUtils.isNetworkConnected(this)) {
            Log.e("CancelCollet", (String) SharedPreferenceUtils.get(KnowledgeDetailActivity.this, "app_token", "") + "knowledge" + id);
            Call<college_bean> call = MyApplication.getNetApi().DELETECollectionRecord((String) SharedPreferenceUtils.get(KnowledgeDetailActivity.this, "app_token", ""), "knowledge", id);
            call.enqueue(new Callback<college_bean>() {
                @Override
                public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                    if (response.isSuccessful()) {
                        Object body = response.body();
                        if (response.body().getStatus().equals("200")) {
                            ToastUtils.showMessage(KnowledgeDetailActivity.this,"取消收藏成功");
                            colletType = DOWNLOAD_ERROR;
                            ivCollegeTop.setImageResource(R.mipmap.icon_subscription);
                            if(resoure.equals("knowledgeSearch")) {
                                SharedPreferenceUtils.put(KnowledgeDetailActivity.this, "KnowSearchId", id);
                                SharedPreferenceUtils.put(KnowledgeDetailActivity.this, "KnowSearchCollege","false");
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<college_bean> call, Throwable t) {
                    Log.e("StudyOnActivity", "ColletonFailure: " + t.getMessage());
                }
            });
        }else{
            ToastUtils.showMessage(this,"请检查您的网络状况");
        }
    }

    public void loadPdf(final String uri) {
        if (!uri.isEmpty()) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
            //截取最后14位 作为文件名
            String s = uri.substring(uri.length() - 14);
            //文件存储
            file1 = new File(Environment.getExternalStorageDirectory(), getFileName(s));
            new Thread() {
                public void run() {
                    File haha = new File(file1.getAbsolutePath());
                    //判断是否有此文件
                    if (haha.exists()) {
                        //有缓存文件,拿到路径 直接打开
                        Message msg = Message.obtain();
                        msg.obj = haha;
                        msg.what = DOWNLOAD_SUCCESS;
                        handler.sendMessage(msg);
                        mProgressDialog.dismiss();
                        return;
                    }
                    // 本地没有此文件 则从网上下载打开
                    File downloadfile = downLoad(uri, file1.getAbsolutePath(), mProgressDialog);
                    Message msg = Message.obtain();
                    if (downloadfile != null) {
                        // 下载成功,安装....
                        msg.obj = downloadfile;
                        msg.what = DOWNLOAD_SUCCESS;
                    } else {
                        // 提示用户下载失败.
                        msg.what = DOWNLOAD_ERROR;
                    }
                    handler.sendMessage(msg);
                    mProgressDialog.dismiss();
                }

                ;
            }.start();
        }
    }

    @Override
    public void initView() {
        menu = new SlidingMenu(this);
        //menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        menu.setBehindOffsetRes(R.dimen.StudySlidingMenu);   // 100dp
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.study_menu);
        //初始化菜单的三个控件
        menu_image = menu.findViewById(R.id.menu_image);
        study_menu_recyclerView = menu.findViewById(R.id.study_menu_recyclerView);
        no_catalog = menu.findViewById(R.id.no_Catalog);
        //适配器
        studyMenuAdapter = new StudyMenuAdapter(list,this);
        study_menu_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        study_menu_recyclerView.setAdapter(studyMenuAdapter);
    }

    /**
     * 传入文件 url  文件路径  和 弹出的dialog  进行 下载文档
     */
    public static File downLoad(String serverpath, String savedfilepath, ProgressDialog pd) {
        try {
            URL url = new URL(serverpath);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            if (conn.getResponseCode() == 200) {
                int max = conn.getContentLength();
                pd.setMax(max);
                InputStream is = conn.getInputStream();
                File file = new File(savedfilepath);
                FileOutputStream fos = new FileOutputStream(file);
                int len = 0;
                byte[] buffer = new byte[1024];
                int total = 0;
                while ((len = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, len);
                    total += len;
                    pd.setProgress(total);
                }
                fos.flush();
                fos.close();
                is.close();
                return file;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public static String getFileName(String serverurl) {
        return serverurl.substring(serverurl.lastIndexOf("/") + 1);
    }
    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }


        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showMessage(KnowledgeDetailActivity.this, "分享成功");


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(KnowledgeDetailActivity.this, "您未安装QQ客户端，无法分享");
            } else if (platform.toString().equals("QZONE")) {
                ToastUtils.showMessage(KnowledgeDetailActivity.this, "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("WEIXIN")) {
                ToastUtils.showMessage(KnowledgeDetailActivity.this, "您未安装微信客户端，无法分享");

            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {
                ToastUtils.showMessage(KnowledgeDetailActivity.this, "您未安装微信客户端，无法分享");
            } else {
                ToastUtils.showMessage(KnowledgeDetailActivity.this, "分享失败,msg:" + t.getMessage());

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
    public void WeChatShare(String id,String title){
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
        UMImage image = new UMImage(KnowledgeDetailActivity.this.getApplicationContext(), myBitmap);
        UMWeb web=new UMWeb("");
        //正常分享

        web = new UMWeb("http://cg.calcnext.com/detail/#/studyContent?id="+ id+"&typeId="+studyTypeIds);
        //web = new UMWeb("http://cg.calcnext.com/detail/#/?type=viewpoint&id=" + id);
        web.setTitle(title);//标题
        web.setThumb(image);  //缩略图
        web.setDescription("在知识的海洋中畅游！");//描述
        new ShareAction(KnowledgeDetailActivity.this)
                .withMedia(web)
                .setDisplayList(displaylist)
                .setCallback(umShareListener)
                .open();
    }

}
