package com.ansiyida.cgjl.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.ChildAdapter;
import com.ansiyida.cgjl.adapter.GroupAdapter;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DropBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.caigoulist;
import com.ansiyida.cgjl.bean.infoTypeBeanBean;
import com.ansiyida.cgjl.bean.policyTypeBean;
import com.ansiyida.cgjl.bean.sourceTypeBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.tab.DropdownButton;
import com.ansiyida.cgjl.util.GetCityUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static fm.jiecao.jcvideoplayer_lib.JCMediaManager.TAG;

public class bidding_class_activity1 extends BaseActivity {

    private DropdownButton dropdownButton2;
    private LinearLayout line;
    private DropdownButton dropdownButton3;
    private List<infoTypeBeanBean.DataBean> infoType;
    private List<sourceTypeBean.DataBean> sourceType;
    private List<policyTypeBean.DataBean> policyType;

    private List<DropBean> types;
    private List<DropBean> names;
    private String all_county="";
    View showPupWindow = null; // 选择区域的view
    ListView groupListView = null;
    ListView childListView = null;
    GroupAdapter groupAdapter = null;
    ChildAdapter childAdapter = null;

    TranslateAnimation animation;// 出现的动画效果
    // 屏幕的宽高
    public static int screen_width = 0;
    public static int screen_height = 0;
    PopupWindow mPopupWindow = null;
    private LinearLayout areaLayout;
    private boolean tabStateArr =true;// 标记tab的选中状态，方便设置
    private ImageView image_back;
    private TextView areaText;
    private TextView text_title;

    private NewsOneAdapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int pageCount = 10;
    private LoadingDialog loadingDialog;

    private TextView tv_txt_no;

    private ImageView iv_empty;
    private EditText edit_view_source;
    private EditText  edit_view_type;
    private ImageView iv_repeat;
    private XRefreshView refreshView;
    private RecyclerView recyclerView;
    private int position;
    private String citytext_drop;
    private String citytext_drop_1;
    private String typetext_drop;
    private String sourecetext_drop;
    private boolean loadBol=true;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 20:
                    childAdapter.setSelectedPosition(-1);
                    childAdapter.setChildData(GetCityUtil.childNameArray[msg.arg1]);
                    childAdapter.notifyDataSetChanged();
                    groupAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }

        };
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            DisplayMetrics dm = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(dm); // 获取手机屏幕的大小
            screen_width = dm.widthPixels;
            screen_height = dm.heightPixels;
        }
        catch (Exception e) {
            LogUtil.i("start:", e.toString());
        }
        SharedPreferenceUtils.remove(bidding_class_activity1.this,"ClickPosition");
        SharedPreferenceUtils.remove(bidding_class_activity1.this,"ClickCollege");

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_spinner_zb1;
    }

    @Override
    protected void initView() {
        types = new ArrayList<>();
        names = new ArrayList<>();

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView_spinner_zb);
        iv_repeat= (ImageView) findViewById(R.id.iv_repeat);
        iv_empty= (ImageView) findViewById(R.id.iv_empty);
        refreshView= (XRefreshView) findViewById(R.id.xrefreshView_spinner_zb);
        dropdownButton2 = (DropdownButton) findViewById(R.id.time2);

        image_back= (ImageView) findViewById(R.id.image_back);
        dropdownButton3 = (DropdownButton) findViewById(R.id.time3);
        edit_view_source=(EditText) findViewById(R.id.edit_souce);
        edit_view_type=(EditText) findViewById(R.id.edit_type);
        tv_txt_no=(TextView)findViewById(R.id.tv_txt_no);
        areaLayout = (LinearLayout) findViewById(R.id.area_layout);
        text_title=(TextView) findViewById(R.id.text_title);
        Intent intent =getIntent();
        line=(LinearLayout) findViewById(R.id.line);
        if("全部推荐".equals(intent.getStringExtra("title")))
        typetext_drop="";
        else
            typetext_drop=intent.getStringExtra("title");
        citytext_drop="全国";
        citytext_drop_1=citytext_drop;
        sourecetext_drop="";
        areaText = (TextView) findViewById(R.id.area_textView);
        text_title.setText(intent.getStringExtra("title"));
        if(citytext_drop.equals("全国"))
            citytext_drop="";
        int[] location = new int[2];
        animation = new TranslateAnimation(0, 0, -700, location[1]);
        animation.setDuration(500);
        activity = this;
        lists = new ArrayList<>();
        adapter = new NewsOneAdapter(lists, this, this.getWindow());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapter);
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        loadingDialog = new LoadingDialog();
        edit_view_source.addTextChangedListener(editview_source);
        edit_view_type.addTextChangedListener(editview_type);
        dropdownButton2.recyclerView=recyclerView;
        dropdownButton2.line=line;
        dropdownButton2.lable=false;
        dropdownButton3.recyclerView=recyclerView;
        dropdownButton3.line=line;
        dropdownButton3.lable=true;

        adapter.setColletlistnear(new NewsOneAdapter.Colletlistnear() {
            @Override
            public void collet() {

            }
        });
        GoogleAssistant(bidding_class_activity1.this,"Android"+typetext_drop,"bidding_class_activity1");
    }
    private TextWatcher editview_source = new TextWatcher() {
        String ll;
        @Override
        public void onTextChanged(CharSequence s, int start, int before,int count) {
        try
        {
            ll = sourceType.get(dropdownButton3.getselectPosition()).getcode();
            if (ll != sourecetext_drop) {
                sourecetext_drop = ll;
                if (sourecetext_drop.equals("武警物资采购网") | sourecetext_drop.equals("军队采购网") | sourecetext_drop.equals("全军武器装备采购网")) {
                   // citytext_drop_1 = citytext_drop;
                    citytext_drop = "";
                    areaText.setText("全国");
                } else {
                    if("全国".equals(citytext_drop_1))
                    {   citytext_drop = "";
                        areaText.setText("全国");}
                    else {
                        citytext_drop = citytext_drop_1;
                        areaText.setText(citytext_drop_1);
                    }
                }
                httpData_RecyclerView("", "", sourecetext_drop, citytext_drop, typetext_drop);

            }
            Log.i(TAG, "onTextChanged: ");
        }
            catch (NullPointerException ex)
            {
                Log.i(TAG, ex.toString());

            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) {


        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    private TextWatcher editview_type = new TextWatcher() {
        String ll;
        @Override
        public void onTextChanged(CharSequence s, int start, int before,int count) {
            if(dropdownButton2.getselectPosition()==0)
                ll=infoType.get(dropdownButton2.getselectPosition()).getcode();
            else
            {
                if(!"废标流标".equals(infoType.get(dropdownButton2.getselectPosition()+2).getname()))
                ll=infoType.get(dropdownButton2.getselectPosition()+2).getcode();
                else
                    ll="废标流标";
            }


            if(ll!=typetext_drop)
            {
                typetext_drop=ll;
                httpData_RecyclerView("","",sourecetext_drop,citytext_drop,typetext_drop);

            }
            Log.i(TAG, "onTextChanged: ");
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) {


        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    @Override
    protected void initData() {

       refresh();
    }
    private void refresh() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {

                httpData_RecyclerView("","",sourecetext_drop,citytext_drop,typetext_drop);
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                httpLoadMore("","",sourecetext_drop,citytext_drop,typetext_drop);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }
    private void httpData_RecyclerView(String apptken,String keyword,String source,String citylocation,String typetextdrop) {
        if(!source.equals("中国政府采购网")){
            citylocation="";
        }
        if(loadingDialog!=null && loadBol){
            loadingDialog.showDialog(this);
        }
        pageNum = 1;

        if (NetWorkUtils.isNetworkConnected(this)) {
            Log.e("公告", "httpData_RecyclerView: "+ keyword+source+citylocation+all_county+typetextdrop);
            Call<caigoulist> call = MyApplication.getNetApi().getcaigou((String) SharedPreferenceUtils.get(bidding_class_activity1.this, "app_token", ""),pageNum+"","20",keyword,source,citylocation,all_county,typetextdrop,"sort");
            call.enqueue(new Callback<caigoulist>() {
                @Override
                public void onResponse(Call<caigoulist> call, Response<caigoulist> response) {
                    if (response.isSuccessful()) {
                        caigoulist body = response.body();
                        //  caigoulist body = response.body();

                        if (body != null) {
                            List<caigoulist.DataBean> list = body.getData();
                            if (list != null && list.size() > 0) {
                                LogUtil.i("yantao", "length:" + list.size());
                                   lists.clear();
                                for (caigoulist.DataBean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("P");
                                    if("null".equals(listBean.getprovince()+"")|"".equals(listBean.getprovince())) {
                                        newBean.setAuthor("全国");
                                        newBean.setgenre("purchaseInfo");
                                    } else {
                                        newBean.setAuthor(listBean.getprovince() + "");
                                        newBean.setgenre("purchaseInfo");
                                    }
                                    newBean.setIsCollect(listBean.getisCollection());
                                    //  newBean.setClickNum("");
                                    newBean.setContent(listBean.getcontent());
                                    //  newBean.setDateformat("");
                                    newBean.setId(listBean.getid());
                                    newBean.setLable(listBean.gettype());
                                    newBean.setImg("");
                                    newBean.setTitle(listBean.gettitle());
                                    newBean.setIsVideo("否");
                                    newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    newBean.setSort(listBean.getsource());

                                    lists.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt_no.setVisibility(View.GONE);

                            } else {
                                LogUtil.i("yantao", "else");
                                iv_empty.setVisibility(View.VISIBLE);
                                tv_txt_no.setVisibility(View.VISIBLE);
                                lists.clear();
                                adapter.notifyDataSetChanged();
                            }
                        } else {
                            iv_empty.setVisibility(View.VISIBLE);
                            tv_txt_no.setVisibility(View.VISIBLE);
                            lists.clear();
                            adapter.notifyDataSetChanged();
                        }

                    } else {
                        iv_empty.setVisibility(View.VISIBLE);
                        tv_txt_no.setVisibility(View.VISIBLE);
                        lists.clear();
                        adapter.notifyDataSetChanged();
                    }
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--1");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    if(loadingDialog!=null && loadingDialog.isDialogShow() &&loadBol){
                        loadBol=false;
                        loadingDialog.disMissDialog();
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<caigoulist> call, Throwable t) {
                    call.cancel();
                    LogUtil.i("yantao", "onFailure");
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--2");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                }
            });


        } else {
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(this, "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
        }

    }
    private void httpLoadMore(String apptken,String keyword,String source,String citylocation,String typetextdrop) {
        if(!source.equals("中国政府采购网")){
            citylocation="";
        }
        // String path = "http://api.map.baidu.com/geocoder?output=json&location=23.131427,113.379763&ak=esNPFDwwsXWtsQfw4NMNmur1";
        pageNum++;
        Call<caigoulist> call = MyApplication.getNetApi().getcaigou((String) SharedPreferenceUtils.get(bidding_class_activity1.this, "app_token", ""),pageNum+"","20",keyword,source,citylocation,all_county,typetextdrop,"sort");

        //  Call<caigoulist> call = MyApplication.getNetApi().getcaigou("",pageNum+"","10");
        call.enqueue(new Callback<caigoulist>() {
            @Override
            public void onResponse(Call<caigoulist> call, Response<caigoulist> response) {
                if (response.isSuccessful()) {
                    caigoulist body = response.body();
                    List<caigoulist.DataBean> list = body.getData();
                    if (list != null && list.size() > 0) {
                        LogUtil.i("yantao", "length:" + list.size());
                      //  lists.clear();
                        for (caigoulist.DataBean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("P");
                            if ("null".equals(listBean.getprovince() + "") | "".equals(listBean.getprovince())){
                                newBean.setAuthor("全国");
                            newBean.setgenre("");
                        }else {
                                newBean.setAuthor(listBean.getprovince() + "");  // newBean.setComnum("");
                                newBean.setgenre("");
                            }
                            newBean.setIsCollect(listBean.getisCollection());
                            //  newBean.setClickNum("");
                            newBean.setContent(listBean.getcontent());
                            //  newBean.setDateformat("");
                            newBean.setId(listBean.getid());
                            newBean.setLable(listBean.gettype());
                            newBean.setImg("");
                            newBean.setTitle(listBean.gettitle());
                            newBean.setIsVideo("否");
                            newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                            newBean.setSort(listBean.getsource());
                            lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);


                    } else {
                        ToastUtils.showMessage(bidding_class_activity1.this, "已加载到底部");
                        pageNum--;
                    }

                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<caigoulist> call, Throwable t) {
                call.cancel();
                LogUtil.i("yantao", "onFailure");
                refreshView.stopLoadMore();

            }
        });
    }
    @Override
    protected void httpData() {

            Call<infoTypeBeanBean> call = MyApplication.getNetApi().getinfoType((String) SharedPreferenceUtils.get(bidding_class_activity1.this, "app_token", ""));
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<infoTypeBeanBean>() {
                @Override
                public void onResponse(Call<infoTypeBeanBean> call, Response<infoTypeBeanBean> response) {
                    if (response.isSuccessful()) {
                        infoTypeBeanBean body = response.body();
                        //  caigoulist body = response.body();

                        if (body != null) {
                            infoType = body.getData();

                            if (infoType != null && infoType.size() > 0) {
                               // types.add(new DropBean("不限"));
                                for (infoTypeBeanBean.DataBean listBean : infoType) {
                                    if(!"采购需求".equals(listBean.getname())&&!"涉密采购".equals(listBean.getname()))
                                    types.add(new DropBean(listBean.getname()));

                                }
                                dropdownButton2.setData(types,"类型",text_title,edit_view_type);
                               // dropdownButton_policyType.setData(names,"全部法规",null,edit_view_class);
                                for(int i=0;i<infoType.size();i++)
                                {

                                    if(typetext_drop.equals(infoType.get(i).getname()))
                                    {
                                        position=0;
                                        dropdownButton2.selectPosition=0;
                                        if(!"废标流标".equals(typetext_drop))
                                        typetext_drop=infoType.get(i).getcode();
                                    break;
                                    }
                                }
                            }

                        }

                    }

                    call.cancel();
                    Call<sourceTypeBean> call1 = MyApplication.getNetApi().getsource((String) SharedPreferenceUtils.get(bidding_class_activity1.this, "app_token", ""));
                    // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                    call1.enqueue(new Callback<sourceTypeBean>() {
                        @Override
                        public void onResponse(Call<sourceTypeBean> call, Response<sourceTypeBean> response) {
                            if (response.isSuccessful()) {
                                sourceTypeBean body = response.body();
                                //  caigoulist body = response.body();

                                if (body != null) {
                                    //  sourceTypeBean.DataBean.listBean list = body.getData();
                                    sourceType = body.getData();
                                    if (infoType != null && infoType.size() > 0) {
                                      //  names.add(new DropBean("不限"));
                                        for (sourceTypeBean.DataBean listbean: sourceType) {
                                            names.add(new DropBean(listbean.getname()));

                                        }
                                        try {
                                            dropdownButton3.setData(names, "来源", null, edit_view_source);
                                        }catch (Exception e){

                                        }
                                        // dropdownButton_policyType.setData(names,"全部法规",null,edit_view_class);
                                    }

                                }

                            }

                            call.cancel();
                            httpData_RecyclerView("","",sourecetext_drop,citytext_drop,typetext_drop);
                        }

                        @Override
                        public void onFailure(Call<sourceTypeBean> call, Throwable t) {

                            call.cancel();
                        }
                    });
                }

                @Override
                public void onFailure(Call<infoTypeBeanBean> call, Throwable t) {

                    call.cancel();
                }
            });



    }

    @Override
    protected void setClickListener() {
        areaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //tabStateArr = !tabStateArr;
                setTabState(areaText, tabStateArr);

            }
        });
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bidding_class_activity1.this.finish();
            }
        });
    }

    private void setTabState(TextView textView, boolean state) {
        Drawable icon;
        tabStateArr = !state;
        if (state) {// 选中状态
            recyclerView.setAlpha(0.1f);
            line.setVisibility(View.VISIBLE);
            icon = getResources().getDrawable(R.mipmap.ic_dropdown_actived);
            textView.setTextColor(getResources().getColor(R.color.text_blue));
            showPupupWindow();
        } else {
            recyclerView.setAlpha(1f);
            line.setVisibility(View.GONE);
            icon = getResources().getDrawable(R.mipmap.ic_dropdown_normal);
            textView.setTextColor(getResources().getColor(R.color.text_black));
            mPopupWindow.dismiss();
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    /**
     * 初始化 PopupWindow
     *
     * @param view
     */
    public void initPopuWindow(View view) {
        /* 第一个参数弹出显示view 后两个是窗口大小 */
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
       // mPopupWindow = new PopupWindow(view, screen_width, screen_height);
        /* 设置背景显示 */
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        mPopupWindow.setBackgroundDrawable(dw);
        //mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.jc_title_bg));
        /* 设置触摸外面时消失 */
         mPopupWindow.setOutsideTouchable(true);

        mPopupWindow.setFocusable(true);

        /**
         * 1.解决再次点击MENU键无反应问题 2.sub_view是PopupWindow的子View
         */
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTabState(areaText, false);
            }
        });
    }

    /**
     * 展示区域选择的对话框
     */
    private void showPupupWindow() {
        showPupWindow = LayoutInflater.from(this).inflate(
                R.layout.dropdown_dropdown_item, null);
        initPopuWindow(showPupWindow);

        groupListView = (ListView) showPupWindow
                .findViewById(R.id.listView1);
        childListView = (ListView) showPupWindow
                .findViewById(R.id.listView2);

        groupAdapter = new GroupAdapter(this, GetCityUtil.GroupNameArray);
        groupListView.setAdapter(groupAdapter);

        groupListView.setOnItemClickListener(new MyItemClick());

        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                areaText.setText(childListView.getItemAtPosition(position).toString());
                mPopupWindow.dismiss();
                if("全国".equals(childListView.getItemAtPosition(position).toString())) {
                    citytext_drop_1 = "全国";
                    citytext_drop="";
                    all_county="";
                } else {
                    citytext_drop= childListView.getItemAtPosition(position).toString();
                    citytext_drop_1 = citytext_drop;
                    all_county="";
                }
                httpData_RecyclerView("","",sourecetext_drop,citytext_drop,typetext_drop);
                if(!sourecetext_drop.equals("中国政府采购网")){
                    ToastUtils.showMessage(bidding_class_activity1.this,"来源为中国政府采购网时地址生效");
                }
            }
        });
        groupAdapter.setSelectedPosition(0);
        childAdapter = new ChildAdapter(bidding_class_activity1.this);
        childListView.setAdapter(childAdapter);
        childAdapter.setSelectedPosition(0);
        childAdapter.setChildData(GetCityUtil.childNameArray[0]);
        childAdapter.notifyDataSetChanged();
        groupAdapter.notifyDataSetChanged();
        mPopupWindow.showAsDropDown(areaLayout, -5, 10);
    }

    class MyItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            groupAdapter.setSelectedPosition(position);
            childAdapter = new ChildAdapter(bidding_class_activity1.this);
            childListView.setAdapter(childAdapter);

            Message msg = new Message();
            msg.what = 20;
            msg.arg1 = position;
            handler.sendMessage(msg);
        }

    }





    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    public void onBackPressed() {
        try
            {

        if(mPopupWindow!=null)
            mPopupWindow=null;
        if(dropdownButton2!=null)
            dropdownButton2=null;
        if(dropdownButton3!=null)
            dropdownButton3=null;
        this.finish();}
       catch (Exception e) {
            String KK=e.toString();
            }
    }


    @Override
    protected void onResume() {
        super.onResume();
       // MobclickAgent.onResume(this);
        Object clickPosition = SharedPreferenceUtils.get(bidding_class_activity1.this, "ClickPosition", -1);
        Log.e(TAG, "onResume: "+clickPosition );
        if(clickPosition!=null){
            Object clickCollege = SharedPreferenceUtils.get(bidding_class_activity1.this, "ClickCollege", "");
            if(!clickCollege.equals("")){
                if(clickCollege.equals("true")){
                    lists.get((Integer) clickPosition).setIsCollect(true);
                }else{
                    lists.get((Integer) clickPosition).setIsCollect(false);
                }
            }
            adapter.notifyItemChanged((Integer) clickPosition);
        }
    }
}

