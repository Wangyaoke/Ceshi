package com.ansiyida.cgjl.activity;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.CollegeAdapter;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.history_buyvip;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuyInvoiveApllyAvtivity extends BaseActivity{
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;

    @Bind(R.id.recycleView_college)
    RecyclerView recyclerView;

    @Bind(R.id.xrefreshView_collegeActivity)
    XRefreshView refreshView;
    @Bind(R.id.tv_txt_no)
    TextView tv_txt_no;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    private boolean flag = true;
    //private ArrayList<CollegeBean> list;
    private boolean isVisible = false;
    private CollegeAdapter collegeAdapter;
    private NewsOneAdapter adapter;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int selectCount;
    private int localPosition = 1;
    private int pageCount = 10;
   // private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_buyhistory;
    }

    @Override
    protected void initView() {
        String titleName = getIntent().getStringExtra("titleName");
        if (TextUtils.isEmpty(titleName)) {
            titleName = "申请开票";
        }
        setStateColor(this, true);
        title.setText(titleName);
        text_putOut.setText("继续");

    }

    @Override
    protected void initData() {
        lists = new ArrayList<>();
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        // collegeAdapter = new CollegeAdapter(this, list, this);
        adapter = new NewsOneAdapter(lists, this, this.getWindow());
        recyclerView.setAdapter(adapter);
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);

    }

    @Override
    protected void httpData() {
        pageNum = 1;

        if (NetWorkUtils.isNetworkConnected(this)) {
            Call<history_buyvip> call = MyApplication.getNetApi().invoice_apply((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"","20");
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<history_buyvip>() {
                @Override
                public void onResponse(Call<history_buyvip> call, Response<history_buyvip> response) {
                    if (response.isSuccessful()) {
                        history_buyvip body = response.body();
                        //  caigoulist body = response.body();

                        if (body.getData() != null) {
                            List<history_buyvip.DataBean.list_law_bean> list = body.getData().getlist_law_bean();

                            if (list != null && list.size() > 0) {
                                lists.clear();
                                for (history_buyvip.DataBean.list_law_bean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("APPLY");
                                    newBean.setIsCollect(false);
                                    newBean.setId(listBean.getid());
                                    newBean.setprovince(listBean.getprice());
                                    if(listBean.getpayMethode()!=null)
                                        newBean.setAuthor("采购精灵会员"+listBean.getpayMonth()+"个月，金额"+listBean.getprice()+"元");
                                    else
                                        newBean.setAuthor("");
                                    newBean.setTitle(listBean.getpayMethode());
                                    // newBean.setComnum("");
                                    if(listBean.getcreateTime()!=null)
                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getcreateTime()));
                                    else
                                        newBean.setVtime( "");

                                    lists.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt_no.setVisibility(View.GONE);

                            }
                            else {
                                iv_empty.setVisibility(View.VISIBLE);
                                tv_txt_no.setVisibility(View.VISIBLE);

                                lists.clear();
                                adapter.notifyDataSetChanged();

                            }

                        } else {
                            ToastUtils.showMessage(BuyInvoiveApllyAvtivity.this,body.getMessage());
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
                    call.cancel();
                }

                @Override
                public void onFailure(Call<history_buyvip> call, Throwable t) {
                    call.cancel();
                    LogUtil.i("yantao", "onFailure");
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--2");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                }
            });


        }
        else {

            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(this, "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();

        }

    }

    @Override
    protected void setClickListener() {

        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                httpData();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                httpLoadMore();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });


    }

    @OnClick({R.id.image_back, R.id.text_putOut})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:               //1.返回上一级
                this.finish();
                break;
            case R.id.text_putOut:              //2.“编辑”按钮
                String id="";
                double price=0;
                for(int i=0;i<lists.size();i++)
                {
                    if(lists.get(i).getIsCollect())
                    {
                        id=id+lists.get(i).getId()+",";

                        double v = Double.parseDouble(lists.get(i).getprovince());
                        price = price+v;
                    }


                }
                if(!"".equals(id))
                {
                    Intent intent_read = new Intent(this, ApplyInvoiveAvtivity.class);
                    intent_read.putExtra("invoicenub", id);
                    intent_read.putExtra("price", price+"");
                    startActivityForResult(intent_read, 0);
                }
                    else
                ToastUtils.showMessage(this, "请选择需要开票的记录");

                break;

            default:
                break;
        }
    }



    private void httpLoadMore() {
        pageNum++;
        Call<history_buyvip> call = MyApplication.getNetApi().invoice_apply((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"","20");
        // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
        call.enqueue(new Callback<history_buyvip>() {
            @Override
            public void onResponse(Call<history_buyvip> call, Response<history_buyvip> response) {
                if (response.isSuccessful()) {
                    history_buyvip body = response.body();
                    //  caigoulist body = response.body();

                    if (body != null) {
                        List<history_buyvip.DataBean.list_law_bean> list = body.getData().getlist_law_bean();

                        if (list != null && list.size() > 0) {
                            for (history_buyvip.DataBean.list_law_bean listBean : list) {
                                NewBean2 newBean = new NewBean2();
                                newBean.setArtype("APPLY");
                                newBean.setIsCollect(false);

                                newBean.setprovince(listBean.getprice());
                                newBean.setId(listBean.getid());
                                if(listBean.getpayMethode()!=null)
                                    newBean.setAuthor("采购精灵会员"+listBean.getpayMonth()+"个月，金额"+listBean.getprice()+"元");
                                else
                                    newBean.setAuthor("");
                                newBean.setTitle(listBean.getpayMethode());
                                // newBean.setComnum("");
                                if(listBean.getcreateTime()!=null)
                                    newBean.setVtime( TimeUtils.mmtime_Time(listBean.getcreateTime()));
                                else
                                    newBean.setVtime( "");
                                lists.add(newBean);
                            }
                            adapter.notifyDataSetChanged();
                            iv_empty.setVisibility(View.GONE);


                        } else {
                            pageNum--;
                            ToastUtils.showMessage(BuyInvoiveApllyAvtivity.this, "已加载到底部");
                        }
                    }else{
                        ToastUtils.showMessage(BuyInvoiveApllyAvtivity.this, "已加载到底部");

                    }
                } else {
                    pageNum--;
                    ToastUtils.showMessage(BuyInvoiveApllyAvtivity.this, "已加载到底部");
                }

                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<history_buyvip> call, Throwable t) {
                localPosition--;
                ToastUtils.showMessage(BuyInvoiveApllyAvtivity.this, "未知错误");
                refreshView.stopLoadMore();
                call.cancel();
            }
        });
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}


