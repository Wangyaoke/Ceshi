package com.ansiyida.cgjl.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.CareAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.FansCareBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FansActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.recycleView_fansActivity)
    RecyclerView recyclerView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.xrefreshView_fansActivity)
    XRefreshView refreshView;
    private ArrayList<FansCareBean.ListBean> list;
    private int pageNum=1;
    private CareAdapter careAdapter;
    private LoadingDialog loadingDialog;
    private ArrayList<Integer> gzList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_fans;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        title.setText("粉丝");
        loadingDialog = new LoadingDialog();
        loadingDialog.showDialog(this, "");
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        gzList=new ArrayList<>();
        careAdapter = new CareAdapter(list,this,gzList);
        recyclerView.setAdapter(careAdapter);
    }

    @Override
    protected void httpData() {
        pageNum=1;
        Call<FansCareBean> call= MyApplication.getNetApi().fansCareList((String) SharedPreferenceUtils.get(this, "app_token", ""),"F",pageNum+"");
        call.enqueue(new Callback<FansCareBean>() {
            @Override
            public void onResponse(Call<FansCareBean> call, Response<FansCareBean> response) {
                if (response.isSuccessful()) {
                    list.clear();
                    List<FansCareBean.ListBean> beanList = response.body().getList();
                    if (beanList != null && beanList.size() > 0) {
                        list.addAll(beanList);
                        gzList.clear();
                        for (FansCareBean.ListBean bean : beanList) {
                            String is_g_z = bean.getIs_g_z();
                            if ("Y".equals(is_g_z)) {
                                gzList.add(1);
                            } else {
                                gzList.add(0);
                            }
                        }
                        careAdapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);
                    } else {
                        iv_empty.setVisibility(View.VISIBLE);

                    }

                } else {
                    iv_empty.setVisibility(View.VISIBLE);
                }

                call.cancel();
                if (loadingDialog != null && loadingDialog.isDialogShow()) {
                    loadingDialog.disMissDialog();
                }
                refreshView.stopRefresh();
            }

            @Override
            public void onFailure(Call<FansCareBean> call, Throwable throwable) {
                call.cancel();
                iv_empty.setVisibility(View.VISIBLE);
                if (loadingDialog != null && loadingDialog.isDialogShow()) {
                    loadingDialog.disMissDialog();
                }
                refreshView.stopRefresh();
            }
        });
    }
    private void httpLoadMore(){

        pageNum++;
        Call<FansCareBean> call= MyApplication.getNetApi().fansCareList((String) SharedPreferenceUtils.get(this, "app_token", ""),"F",pageNum+"");
        call.enqueue(new Callback<FansCareBean>() {
            @Override
            public void onResponse(Call<FansCareBean> call, Response<FansCareBean> response) {
                if (response.isSuccessful()){
                    List<FansCareBean.ListBean> beanList = response.body().getList();
                    if (beanList!=null&&beanList.size()>0){
                        list.addAll(beanList);
                        for (FansCareBean.ListBean bean:beanList){
                            String is_g_z = bean.getIs_g_z();
                            if ("Y".equals(is_g_z)){
                                gzList.add(1);
                            }else {
                                gzList.add(0);
                            }
                        }
                        careAdapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);
                    }else {
                        ToastUtils.showMessage(FansActivity.this, "已加载到底部");

                    }

                }else {
                    ToastUtils.showMessage(FansActivity.this,"已加载到底部");
                }

                call.cancel();
                if (loadingDialog!=null&&loadingDialog.isDialogShow()){
                    loadingDialog.disMissDialog();
                }
                refreshView.stopLoadMore();
            }

            @Override
            public void onFailure(Call<FansCareBean> call, Throwable throwable) {
                call.cancel();
                if (loadingDialog!=null&&loadingDialog.isDialogShow()){
                    loadingDialog.disMissDialog();
                }
                refreshView.stopLoadMore();
                ToastUtils.showMessage(FansActivity.this, "已加载到底部");
            }
        });

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list!=null){
            list.clear();
            list=null;
        }

        if (loadingDialog!=null&&loadingDialog.isDialogShow()){
            loadingDialog.disMissDialog();
            loadingDialog=null;
        }
    }
}
