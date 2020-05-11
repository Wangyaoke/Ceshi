package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.caigoulist;
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

public class bidding_class_activity extends BaseActivity {


    private LinearLayout line;



    private ImageView image_back;

    private TextView text_title;

    private NewsOneAdapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int pageCount = 10;

    private TextView tv_txt_no;

    private ImageView iv_empty;

    private ImageView iv_repeat;
    private XRefreshView refreshView;
    private RecyclerView recyclerView;
    private String citytext_drop;
    private String typetext_drop;
    private String sourecetext_drop;
    private String dy_keyword;
    private String genre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        }
        catch (Exception e) {
            LogUtil.i("start:", e.toString());
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_spinner_zb;
    }

    @Override
    protected void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recyclerView_spinner_zb);
        iv_repeat= (ImageView) findViewById(R.id.iv_repeat);
        iv_empty= (ImageView) findViewById(R.id.iv_empty);
        refreshView= (XRefreshView) findViewById(R.id.xrefreshView_spinner_zb);
         image_back= (ImageView) findViewById(R.id.image_back);
       text_title=(TextView) findViewById(R.id.text_title);
        tv_txt_no=(TextView)findViewById(R.id.tv_txt_no);
        Intent intent =getIntent();
        line=(LinearLayout) findViewById(R.id.line);

        text_title.setText(intent.getStringExtra("title"));
        typetext_drop=intent.getStringExtra("type");
         citytext_drop=intent.getStringExtra("address");;
      //  province_string=intent.getStringExtra("province");;
       sourecetext_drop=intent.getStringExtra("souces");;
         dy_keyword=intent.getStringExtra("keyword");;
        genre = intent.getStringExtra("genre");
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

    }

    @Override
    protected void initData() {

        httpData_RecyclerView("",dy_keyword,sourecetext_drop,citytext_drop,"",typetext_drop);

        refresh();
    }
    private void refresh() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                httpData_RecyclerView("",dy_keyword,sourecetext_drop,citytext_drop,"",typetext_drop);
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                httpLoadMore("",dy_keyword,sourecetext_drop,citytext_drop,"",typetext_drop);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }
    private void httpData_RecyclerView(String apptken,String keyword,String source,String citylocation,String province,String typetextdrop) {
        pageNum = 1;
        if (NetWorkUtils.isNetworkConnected(this)) {
            Log.e("地址", "httpData_RecyclerView: "+citylocation );
            Call<caigoulist> call = MyApplication.getNetApi().getcaigouseach((String) SharedPreferenceUtils.get(bidding_class_activity.this, "app_token", ""),pageNum+"","20",keyword,source,citylocation,province,typetextdrop,false);
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
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

                                    if("null".equals(listBean.getprovince()+"")|"".equals(listBean.getprovince()))
                                        newBean.setAuthor("全国");
                                    else
                                        newBean.setAuthor(listBean.getprovince()+"");
                                    // newBean.setComnum("");
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
                                    newBean.setgenre(genre);
                                    lists.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt_no.setVisibility(View.GONE);

                            }
                            else {
                                LogUtil.i("yantao", "else");
                                iv_empty.setVisibility(View.VISIBLE);
                                tv_txt_no.setVisibility(View.VISIBLE);
                                lists.clear();
                                adapter.notifyDataSetChanged();
                            }

                        }
                        else {
                            iv_empty.setVisibility(View.VISIBLE);
                            tv_txt_no.setVisibility(View.VISIBLE);
                            lists.clear();
                            adapter.notifyDataSetChanged();
                        }

                    }
                    else {
                        iv_empty.setVisibility(View.VISIBLE);
                        tv_txt_no.setVisibility(View.VISIBLE);
                        lists.clear();
                        adapter.notifyDataSetChanged();
                    }
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--1");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                  /*  if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
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
    private void httpLoadMore(String apptken,String keyword,String source,String citylocation,String province,String typetextdrop) {
        // String path = "http://api.map.baidu.com/geocoder?output=json&location=23.131427,113.379763&ak=esNPFDwwsXWtsQfw4NMNmur1";
        pageNum++;
        Call<caigoulist> call = MyApplication.getNetApi().getcaigouseach((String) SharedPreferenceUtils.get(bidding_class_activity.this, "app_token", ""),pageNum+"","20",keyword,source,citylocation,province,typetextdrop,false);

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
                            if("null".equals(listBean.getprovince()+"")|"".equals(listBean.getprovince()))
                                newBean.setAuthor("全国");
                            else
                                newBean.setAuthor(listBean.getprovince()+"");
                         //   newBean.setAuthor(listBean.getprovince()+"");
                            // newBean.setComnum("");
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

                            newBean.setgenre(genre);
                            lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);


                    } else {
                        ToastUtils.showMessage(bidding_class_activity.this, "已加载到底部");
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
    }

    @Override
    protected void setClickListener() {
        adapter.setColletlistnear(new NewsOneAdapter.Colletlistnear() {
            @Override
            public void collet() {

            }
        });
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bidding_class_activity.this.finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // MobclickAgent.onResume(this);
        int zcxxPosition = (int) SharedPreferenceUtils.get(bidding_class_activity.this, "ClickPosition", -1);
        Object zcxxCollege = SharedPreferenceUtils.get(bidding_class_activity.this, "ClickCollege", "");
        if(lists.size()>0){
            if(zcxxPosition!=-1 && !zcxxCollege.equals("")){
                if(zcxxCollege.equals("true")){
                    lists.get(zcxxPosition).setIsCollect(true);
                }else{
                    lists.get(zcxxPosition).setIsCollect(false);
                }
                adapter.notifyItemChanged(zcxxPosition);
            }
        }
    }
}

