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
import com.ansiyida.cgjl.adapter.PersonalDynamicAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.PersonalDynamicBean;
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

public class PersonalDynamicActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.recycleView_personalDynamicActivity)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_personalDynamicActivity)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    private int pageNum;
    private PersonalDynamicAdapter personalDynamicAdapter;
    private ArrayList<PersonalDynamicBean.DataBean.ListBean> lists;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_personal_dynamic;
    }

    @Override
    protected void initView() {
        title.setText("动态");
        setStateColor(this, true);
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        loadingDialog = new LoadingDialog();
        loadingDialog.showDialog(this, "");
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        lists = new ArrayList<>();
        personalDynamicAdapter = new PersonalDynamicAdapter(this, lists);
        recyclerView.setAdapter(personalDynamicAdapter);
    }

    @Override
    protected void httpData() {
        pageNum=1;
        Call<PersonalDynamicBean> call= MyApplication.getNetApi().getDynamic((String) SharedPreferenceUtils.get(this,"app_token",""),pageNum+"");
        call.enqueue(new Callback<PersonalDynamicBean>() {
            @Override
            public void onResponse(Call<PersonalDynamicBean> call, Response<PersonalDynamicBean> response) {
                if (response.isSuccessful()){
                    PersonalDynamicBean body = response.body();
                    if (body!=null){
                        PersonalDynamicBean.DataBean data = body.getData();
                        if (data!=null){
                            lists.clear();
                            List<PersonalDynamicBean.DataBean.ListBean> beanList = data.getList();
                            if (beanList!=null&&beanList.size()>0){
                                lists.addAll(beanList);
                                personalDynamicAdapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                            }else {
                                iv_empty.setVisibility(View.VISIBLE);
                            }
                        }else {
                            iv_empty.setVisibility(View.VISIBLE);
                        }
                    }else {
                        iv_empty.setVisibility(View.VISIBLE);
                    }

                }else {
                    ToastUtils.showMessage(PersonalDynamicActivity.this,"获取动态列表失败");
                }

                refreshView.stopRefresh();
                call.cancel();
                if (loadingDialog!=null&&loadingDialog.isDialogShow()){
                    loadingDialog.disMissDialog();
                }
            }

            @Override
            public void onFailure(Call<PersonalDynamicBean> call, Throwable t) {
                ToastUtils.showMessage(PersonalDynamicActivity.this,"未知错误");

                refreshView.stopRefresh();
                call.cancel();
                if (loadingDialog!=null&&loadingDialog.isDialogShow()){
                    loadingDialog.disMissDialog();
                }
            }
        });
    }

    private void httpLoadMore() {
        //上拉加载
        //初始
        pageNum++;
        Call<PersonalDynamicBean> call = MyApplication.getNetApi().getDynamic((String) SharedPreferenceUtils.get(PersonalDynamicActivity.this, "app_token", ""), pageNum + "");
        call.enqueue(new Callback<PersonalDynamicBean>() {
            @Override
            public void onResponse(Call<PersonalDynamicBean> call, Response<PersonalDynamicBean> response) {
                if (response.isSuccessful()) {
                    PersonalDynamicBean body = response.body();
                    if (body != null) {
                        PersonalDynamicBean.DataBean data = body.getData();
                        if (data != null) {
                            List<PersonalDynamicBean.DataBean.ListBean> beanList = data.getList();
                            if (beanList != null && beanList.size() > 0) {
                                lists.addAll(beanList);
                                personalDynamicAdapter.notifyDataSetChanged();
                            } else {
                                pageNum--;
                                ToastUtils.showMessage(PersonalDynamicActivity.this, "已加载到底部");
                            }
                        }
                    } else {
                        pageNum--;

                    }

                } else {
                    pageNum--;
                    ToastUtils.showMessage(PersonalDynamicActivity.this, "获取动态列表失败");
                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<PersonalDynamicBean> call, Throwable t) {
                ToastUtils.showMessage(PersonalDynamicActivity.this, "未知错误");
                refreshView.stopLoadMore();
                pageNum--;
                call.cancel();
            }
        });
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

    @OnClick({R.id.image_back})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:                   //1.返回上一级

                this.finish();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lists!=null){
            lists.clear();
            lists=null;
        }

        if (loadingDialog!=null&&loadingDialog.isDialogShow()){
            loadingDialog.disMissDialog();
            loadingDialog=null;
        }
    }
}
