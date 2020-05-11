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
import com.ansiyida.cgjl.bean.history_buyvip;
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

public class BuyInvoiveHistoryAvtivity extends BaseActivity {

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
        return R.layout.activity_buyhistory;
    }

    @Override
    protected void initView() {
        text_title.setText("开票历史");
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
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);

    }
    private void httpLoadMore() {
        pageNum++;
        Call<history_buyvip> call = MyApplication.getNetApi().invoice((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"","20");
        //Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
        call.enqueue(new Callback<history_buyvip>() {
            @Override
            public void onResponse(Call<history_buyvip> call, Response<history_buyvip> response) {
                if (response.isSuccessful()) {
                    history_buyvip body = response.body();
                    //caigoulist body = response.body();
                    if (body.getData() != null) {
                        List<history_buyvip.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                        if (list != null && list.size() > 0) {
                            for (history_buyvip.DataBean.list_law_bean listBean : list) {
                                NewBean2 newBean = new NewBean2();
                                newBean.setArtype("VIP_H");
                                newBean.setAuthor("采购精灵会员");
                                newBean.setId(listBean.getid());
                                newBean.setTitle(listBean.getmoney()+".00元");
                                newBean.setSort(listBean.getstatus());
                                if(listBean.getcreateTime()!=null)
                                    newBean.setVtime( TimeUtils.mmtime_Time(listBean.getcreateTime()));
                                else
                                    newBean.setVtime("");
                                lists.add(newBean);
                            }
                            adapter.notifyDataSetChanged();
                            iv_empty.setVisibility(View.GONE);
                        } else {
                            pageNum--;
                            ToastUtils.showMessage(BuyInvoiveHistoryAvtivity.this, "已加载到底部");
                        }
                    }else{
                       // ToastUtils.showMessage(BuyInvoiveHistoryAvtivity.this, "已加载到底部");
                        ToastUtils.showMessage(BuyInvoiveHistoryAvtivity.this,body.getMessage());
                    }
                } else {
                    pageNum--;
                    ToastUtils.showMessage(BuyInvoiveHistoryAvtivity.this, "已加载到底部");
                }

                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<history_buyvip> call, Throwable t) {
                localPosition--;
                ToastUtils.showMessage(BuyInvoiveHistoryAvtivity.this, "未知错误");
                refreshView.stopLoadMore();
                call.cancel();
            }
        });
    }
    @Override
    protected void httpData() {
        pageNum = 1;
        if (NetWorkUtils.isNetworkConnected(this)) {
            Call<history_buyvip> call = MyApplication.getNetApi().invoice((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"","20");
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
                                //      LogUtil.i("yantao", "length:" + list.size());
                                lists.clear();
                                for (history_buyvip.DataBean.list_law_bean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("VIP_H");
                                    newBean.setAuthor("采购精灵会员");
                                    newBean.setId(listBean.getid());
                                    newBean.setTitle(listBean.getmoney()+".00元");
                                    newBean.setSort(listBean.getstatus());
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
                                //  LogUtil.i("yantao", "else");
                                iv_empty.setVisibility(View.VISIBLE);
                                tv_txt_no.setVisibility(View.VISIBLE);

                                lists.clear();
                                adapter.notifyDataSetChanged();

                            }

                        } else {
                            iv_empty.setVisibility(View.VISIBLE);
                            tv_txt_no.setVisibility(View.VISIBLE);
                            ToastUtils.showMessage(BuyInvoiveHistoryAvtivity.this,body.getMessage());
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
                public void onFailure(Call<history_buyvip> call, Throwable t) {
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

        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
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

