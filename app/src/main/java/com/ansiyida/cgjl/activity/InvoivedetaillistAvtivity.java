package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.ansiyida.cgjl.bean.invoice_detail_listbean;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
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

public class InvoivedetaillistAvtivity extends BaseActivity {
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.text_title)
    TextView text_title;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected int getContentView() {

        return R.layout.activity_invoivedetailist;
    }

    @Override
    protected void initView() {
        text_title.setText("开票详情");
        text_putOut.setText("");

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
        refreshView.setPullLoadEnable(false);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(false);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);

    }


    @Override
    protected void httpData() {
        pageNum = 1;
        if (NetWorkUtils.isNetworkConnected(this)) {
            Intent intent = getIntent();
            Call<invoice_detail_listbean> call = MyApplication.getNetApi().get_invoice_detail(intent.getStringExtra("id"),((String) SharedPreferenceUtils.get(InvoivedetaillistAvtivity.this, "app_token", "")));
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<invoice_detail_listbean>() {
                @Override
                public void onResponse(Call<invoice_detail_listbean> call, Response<invoice_detail_listbean> response) {
                    if (response.isSuccessful()) {
                        invoice_detail_listbean body = response.body();
                        //  caigoulist body = response.body();

                        if (body != null) {
                            List<invoice_detail_listbean.DataBean.order_bean> list = body.getData().getorder_bean();

                            if (list != null && list.size() > 0) {
                                //      LogUtil.i("yantao", "length:" + list.size());
                                lists.clear();
                                //    NewBean2 newBean1 = new NewBean2();
                                //      newBean1.setArtype("PP");
                                //    lists.add(newBean1);
                                for (invoice_detail_listbean.DataBean.order_bean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("BUY_D");
                                    newBean.setAuthor("采购精灵会员"+listBean.getshowDate());
                                    newBean.setTitle(listBean.getpayMethode());
                                    // newBean.setComnum("");
                                    if(listBean.getcreateTime()!=null)
                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getcreateTime()));
                                    else
                                        newBean.setVtime( "");
                                    newBean.setprice(listBean.getprice()+"元");

                                    lists.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt_no.setVisibility(View.GONE);

                            }




                            else {
                                //  LogUtil.i("yantao", "else");
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

                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);

                    call.cancel();
                }

                @Override
                public void onFailure(Call<invoice_detail_listbean> call, Throwable t) {
                    call.cancel();

                    refreshView.stopRefresh();

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


    }

    @OnClick({R.id.image_back})
    public void click(View view) {
        switch (view.getId()) {

            case R.id.image_back:               //1.返回上一级

                this.finish();
                break;
            default:
                break;

        }
    }



    void returnResult(String sdata) {
        Intent result = new Intent();
        result.putExtra("keyword", sdata);
        setResult(ActivityCodeUtil.KEYWORD , result);
        finish();
    }

            @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

