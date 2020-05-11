package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.DYDetailAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DYMainBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.DpPxTools;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.glide_circle_photo.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DYDetailsActivity extends BaseActivity {
    @Bind(R.id.recyclerView_dyDetails)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_dyDetails)
    XRefreshView refreshView;
    @Bind(R.id.relative_top)
    RelativeLayout relative_top;
    @Bind(R.id.tv_userName)
    TextView tv_userName;
    @Bind(R.id.iv_touXiang)
    ImageView iv_touXiang;
    private int instance;
    private LinearLayoutManager mLayoutManager;
    private int pageNum;
    private String js_id;
    private DYDetailAdapter dyDetailAdapter;
    private int type;
    private List<DYMainBean.DataBean.PageInfoBean> contentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_dydetails;
    }

    @Override
    protected void initView() {
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(false);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        contentList = new ArrayList<>();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        js_id = intent.getStringExtra("js_id");
        type = intent.getIntExtra("type", 0);
        instance = DpPxTools.dip2px(this, 105);
    }

    @Override
    protected void httpData() {
        pageNum = 1;
        LogUtil.i("param", "js_id:" + js_id);
        Call<DYMainBean> call = null;
        if (type == 0) {
            call = MyApplication.getNetApi().getDYMain(pageNum + "", js_id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
        } else {
            call = MyApplication.getNetApi().getMyDYMain(pageNum + "", js_id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
        }
        call.enqueue(new Callback<DYMainBean>() {
            @Override
            public void onResponse(Call<DYMainBean> call, Response<DYMainBean> response) {
                if (response.isSuccessful()) {
                    DYMainBean.DataBean data = response.body().getData();
                    List<DYMainBean.DataBean.PageInfoBean> pageInfo = data.getPageInfo();
                    List<DYMainBean.DataBean.PageInfoBean> content;
                    if (pageInfo != null) {
                        content = pageInfo;
                        content.add(0, null);
                    } else {
                        content = new ArrayList<DYMainBean.DataBean.PageInfoBean>();
                        content.add(null);
                    }
                    contentList.addAll(content);
                    dyDetailAdapter = new DYDetailAdapter(DYDetailsActivity.this, contentList, data);
                    tv_userName.setText(data.getSource().getJs_name());
                    Glide.with(DYDetailsActivity.this).load(data.getSource().getJs_logo()).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(DYDetailsActivity.this)).into(iv_touXiang);
                    recyclerView.setAdapter(dyDetailAdapter);
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<DYMainBean> call, Throwable t) {
                call.cancel();

            }
        });
    }

    @Override
    protected void setClickListener() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int scollYDistance = getScollYDistance();
                if (scollYDistance > instance) {
                    relative_top.setVisibility(View.VISIBLE);
                } else {
                    relative_top.setVisibility(View.GONE);

                }
            }
        });

        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                refresh();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                loadMore();
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
            case R.id.image_back:                       //1.返回上一级
                this.finish();
                break;
        }
    }


    private int getScollYDistance() {
        int position = mLayoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = mLayoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    private void loadMore() {
        pageNum++;
        LogUtil.i("param", "js_id:" + js_id);
        Call<DYMainBean> call = null;
        if (type == 0) {
            call = MyApplication.getNetApi().getDYMain(pageNum + "", js_id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
        } else {
            call = MyApplication.getNetApi().getMyDYMain(pageNum + "", js_id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
        }
        call.enqueue(new Callback<DYMainBean>() {
            @Override
            public void onResponse(Call<DYMainBean> call, Response<DYMainBean> response) {
                if (response.isSuccessful()) {
                    DYMainBean.DataBean data = response.body().getData();
                    List<DYMainBean.DataBean.PageInfoBean> pageInfo = data.getPageInfo();
                    List<DYMainBean.DataBean.PageInfoBean> content;
                    if (pageInfo != null && pageInfo.size() > 0) {
                        content = pageInfo;
                        contentList.addAll(content);
                        dyDetailAdapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showMessage(DYDetailsActivity.this, "无数据可加载");
                    }

                } else {
                    ToastUtils.showMessage(DYDetailsActivity.this, "数据加载异常");

                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<DYMainBean> call, Throwable t) {
                refreshView.stopLoadMore();
                ToastUtils.showMessage(DYDetailsActivity.this, "数据加载异常");
                call.cancel();
            }
        });
    }

    private void refresh() {
        pageNum = 1;
        LogUtil.i("param", "js_id:" + js_id);
        Call<DYMainBean> call = null;
        if (type == 0) {
            call = MyApplication.getNetApi().getDYMain(pageNum + "", js_id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
        } else {
            call = MyApplication.getNetApi().getMyDYMain(pageNum + "", js_id, (String) SharedPreferenceUtils.get(this, "app_token", ""));
        }
        call.enqueue(new Callback<DYMainBean>() {
            @Override
            public void onResponse(Call<DYMainBean> call, Response<DYMainBean> response) {
                if (response.isSuccessful()) {
                    DYMainBean.DataBean data = response.body().getData();
                    List<DYMainBean.DataBean.PageInfoBean> pageInfo = data.getPageInfo();
                    List<DYMainBean.DataBean.PageInfoBean> content;
                    if (pageInfo != null) {
                        content = pageInfo;
                        content.add(0, null);
                    } else {
                        content = new ArrayList<DYMainBean.DataBean.PageInfoBean>();
                        content.add(null);
                    }
                    contentList.clear();
                    contentList.addAll(content);
                    dyDetailAdapter.notifyDataSetChanged();
                } else {
                    ToastUtils.showMessage(DYDetailsActivity.this, "数据加载异常");

                }
                refreshView.stopRefresh();
                call.cancel();
            }

            @Override
            public void onFailure(Call<DYMainBean> call, Throwable t) {
                refreshView.stopRefresh();
                ToastUtils.showMessage(DYDetailsActivity.this, "数据加载异常");
                call.cancel();

            }
        });
    }

}
