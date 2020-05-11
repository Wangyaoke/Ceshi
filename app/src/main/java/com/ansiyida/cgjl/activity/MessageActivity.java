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
import com.ansiyida.cgjl.adapter.MessageAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.MessageBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.tv_txt)
    TextView tv_txt;
    @Bind(R.id.recycleView_messageActivity)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_messageActivity)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.text_putOut)
    ImageView text_putOut;
    private ArrayList<MessageBean.DataBean.ListBean> list;
    private int pageNum=1;
    private MessageAdapter messageAdapter;
    private LoadingDialog loadingDialog;
    private ArrayList<String> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        setStateColor(this,true);
        title.setText("推送记录");

        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        idList=new ArrayList<>();
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        list = new ArrayList<>();
        messageAdapter = new MessageAdapter(this,list);
        recyclerView.setAdapter(messageAdapter);
        loadingDialog=new LoadingDialog();
        loadingDialog.showDialog(this,"");
    }

    @Override
    protected void httpData() {
        pageNum=1;
        Call<MessageBean> call= MyApplication.getNetApi().getMessage((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"");
        call.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                if (response.isSuccessful()){
                    MessageBean body = response.body();
                    if (body!=null){
                        MessageBean.DataBean data = body.getData();
                        if (data!=null){
                            List<MessageBean.DataBean.ListBean> beanList = data.getList();
                            if (beanList!=null&&beanList.size()>0){
                                list.clear();
                                idList.clear();
                                list.addAll(beanList);
                                for (MessageBean.DataBean.ListBean bean:beanList){
                                    int jmm_id = bean.getJmm_id();
                                    idList.add(jmm_id+"");
                                }
                                messageAdapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt.setVisibility(View.GONE);

                            }else {
                                iv_empty.setVisibility(View.VISIBLE);
                                tv_txt.setVisibility(View.VISIBLE);
                            }

                        }else {
                            iv_empty.setVisibility(View.VISIBLE);
                            tv_txt.setVisibility(View.VISIBLE);
                        }
                    }else {
                        iv_empty.setVisibility(View.VISIBLE);
                        tv_txt.setVisibility(View.VISIBLE);
                    }


                }else {
                    iv_empty.setVisibility(View.VISIBLE);
                    tv_txt.setVisibility(View.VISIBLE);

                }
                refreshView.stopRefresh();

                call.cancel();
                if (loadingDialog!=null&&loadingDialog.isDialogShow()){
                    loadingDialog.disMissDialog();
                }
            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {
                call.cancel();
                iv_empty.setVisibility(View.VISIBLE);
                tv_txt.setVisibility(View.VISIBLE);
                refreshView.stopRefresh();
                if (loadingDialog!=null&&loadingDialog.isDialogShow()){
                    loadingDialog.disMissDialog();
                }
            }
        });
    }


    @Override
    protected void setClickListener() {

        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                LogUtil.i("xfre", "onRefresh");
            }

            @Override
            public void onRefresh(boolean isPullDown) {
                LogUtil.i("xfre","onRefresh(boolean isPullDown)");
                httpData();

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageNum++;
                Call<MessageBean> call= MyApplication.getNetApi().getMessage((String) SharedPreferenceUtils.get(MessageActivity.this,"app_token",""), pageNum + "");
                call.enqueue(new Callback<MessageBean>() {
                    @Override
                    public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                        if (response.isSuccessful()){
                            List<MessageBean.DataBean.ListBean> beanList = response.body().getData().getList();
                            if (beanList!=null){
                                list.addAll(beanList);
                                beanList.clear();
                                for (MessageBean.DataBean.ListBean bean:beanList){
                                    int jmm_id = bean.getJmm_id();
                                    idList.add(jmm_id+"");
                                }
                                messageAdapter.notifyDataSetChanged();
                            }else{
                                ToastUtils.showMessage(MessageActivity.this,"已加载到底部");
                            }

                        }

                        call.cancel();
                        refreshView.stopLoadMore();
                    }

                    @Override
                    public void onFailure(Call<MessageBean> call, Throwable t) {
                        call.cancel();
                        refreshView.stopLoadMore();

                    }
                });

            }

            @Override
            public void onRelease(float direction) {
                LogUtil.i("xfre","onRelease");

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {
                LogUtil.i("xfre","onHeaderMove");

            }
        });


    }

    @OnClick({R.id.image_back,R.id.text_putOut})
    public void click(View view){
        switch (view.getId()){
            case R.id.image_back:           //1.返回上一级
                this.finish();
                break;
            case R.id.text_putOut:          //2.清空按钮
                StringBuffer sb=new StringBuffer();
                for (MessageBean.DataBean.ListBean listBean:list){
                    String str=listBean.getJmm_id()+"";
                    sb.append(str+",");
                }
                if (loadingDialog==null){
                    loadingDialog=new LoadingDialog();
                }
                LogUtil.i("clear","data:"+sb.toString()+"|||");
                if (!"".equals(sb.toString())){
                    loadingDialog.showDialog(this, "");
                    Call<DefaultBean> call= MyApplication.getNetApi().deleteMessage((String) SharedPreferenceUtils.get(this,"app_token",""),sb.toString()+"");
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()){
                                DefaultBean body = response.body();
                                if ("0001".equals(body.getStatus())){
                                    list.clear();
                                    httpData();
                                    messageAdapter.notifyDataSetChanged();

                                }else {
                                    ToastUtils.showMessage(MessageActivity.this,body.getMessage());
                                }


                            }else {
                                ToastUtils.showMessage(MessageActivity.this,"数据解析错误1");
                            }

                            call.cancel();
                            if (loadingDialog!=null&&loadingDialog.isDialogShow()){
                                loadingDialog.disMissDialog();
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            call.cancel();
                            ToastUtils.showMessage(MessageActivity.this,"数据解析错误2");
                            if (loadingDialog!=null&&loadingDialog.isDialogShow()){
                                loadingDialog.disMissDialog();
                            }
                        }
                    });
                }else {
                    ToastUtils.showMessage(MessageActivity.this,"无消息可清空");
                }

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
        if (loadingDialog!=null){
            loadingDialog.disMissDialog();
            loadingDialog=null;
        }
    }
}
