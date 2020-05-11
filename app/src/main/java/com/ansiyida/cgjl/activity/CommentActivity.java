package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.CommentAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.CommentBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.dialog.CommentDialog2;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentActivity extends BaseActivity {
    @Bind(R.id.recyclerView_commentActivity)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_commentActivity)
    XRefreshView refreshView;
    @Bind(R.id.text_title)
    TextView text_title;
    private ArrayList<CommentBean.DataBean.ContentBean> lists;
    private ArrayList<Integer> zanFlag;
    private ArrayList<Integer> commentCountList;
    private CommentAdapter adapter;
    private String id;
    private String type;
    private String jc_id;
    private int pageNum=1;
    private int pageCount=20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_comment;
    }

    @Override
    protected void initView() {
        setStateColor(this,true);
        lists=new ArrayList<>();
        zanFlag=new ArrayList<>();
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        type = intent.getStringExtra("type");
        jc_id = intent.getStringExtra("jc_id");
        commentCountList=new ArrayList<>();
       int commentCount = intent.getIntExtra("commentCount",0);
        boolean isFirst=false;
        if (commentCount >0){
            text_title.setText(commentCount +"条回复");
        }else {
            text_title.setText("评论");
            isFirst=true;
        }
        adapter=new CommentAdapter(this,lists,zanFlag,isFirst);

        LogUtil.i("intent","id:"+id+",type:"+type+",jc_id:"+jc_id);
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
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void httpData() {
        pageNum=1;
        Call<CommentBean> call2 = MyApplication.getNetApi().getComment((String)SharedPreferenceUtils.get(this,"app_token",""),type, id, pageNum+"", pageCount+"", jc_id);
        call2.enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                if (response.isSuccessful()) {
                    LogUtil.i("kj", "1");
                    adapter.setCommentBeans(response.body());

                } else {
                    LogUtil.i("kj", "2");
                }
                call.cancel();
                refreshView.stopRefresh();
            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable t) {
                LogUtil.i("kj", "3");
                refreshView.stopRefresh();
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
    @OnClick({ R.id.iv_editPingLun, R.id.picture_iv_back, R.id.iv_college, R.id.iv_share})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_editPingLun:               //1.发表评论按钮
                CommentDialog2 commentDialog2 = new CommentDialog2("优质评论将会优先展示", "", 300, new CommentDialog2.SendListener() {
                    @Override
                    public void sendComment(String inputText) {
                        Call<DefaultBean> call = MyApplication.getNetApi().saveComment(id, type, inputText, (String) SharedPreferenceUtils.get(CommentActivity.this, "app_token", ""),jc_id);
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()) {
                                    ToastUtils.showMessage(CommentActivity.this, response.body().getMessage());
                                    httpData();
                                }
                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<DefaultBean> call, Throwable t) {

                                call.cancel();
                            }
                        });

                    }
                }, CommentActivity.this, "发布");
                commentDialog2.show(CommentActivity.this.getSupportFragmentManager(), "comment");

                break;
            case R.id.picture_iv_back:              //2.返回上一级
                this.finish();
                break;
            case R.id.iv_college:                   //3.收藏按钮
                Call<DefaultBean> call = MyApplication.getNetApi().collegeNews(type, id, (String) SharedPreferenceUtils.get(this,"app_token",""));
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            ToastUtils.showMessage(CommentActivity.this, response.body().getMessage());
                        }
                        call.cancel();
                    }
                    @Override
                    public void onFailure(Call<DefaultBean> call, Throwable t) {

                        call.cancel();
                    }
                });
                break;
            case R.id.iv_share:                     //4.分享按钮

                ToastUtils.showMessage(this, "分享成功");
                break;

        }
    }
    private void httpLoadMore(){
        pageNum++;
        Call<CommentBean> call2 = MyApplication.getNetApi().getComment((String)SharedPreferenceUtils.get(this,"app_token",""),type, id, pageNum+"", pageCount+"", jc_id);
        call2.enqueue(new Callback<CommentBean>() {
            @Override
            public void onResponse(Call<CommentBean> call, Response<CommentBean> response) {
                if (response.isSuccessful()) {
                    LogUtil.i("kj", "1");
                    adapter.loadCommentBeans(response.body());
                } else {
                    LogUtil.i("kj", "2");
                }
                call.cancel();
                refreshView.stopLoadMore();
            }

            @Override
            public void onFailure(Call<CommentBean> call, Throwable t) {
                LogUtil.i("kj", "3");
                refreshView.stopLoadMore();
                call.cancel();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lists!=null){
            lists.clear();
            lists=null;
        }
        if (zanFlag!=null){
            zanFlag.clear();
            zanFlag=null;
        }
        if (commentCountList!=null){
            commentCountList.clear();
            commentCountList=null;
        }
    }
}
