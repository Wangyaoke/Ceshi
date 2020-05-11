package com.ansiyida.cgjl.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.CollegeAdapter;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.CollegeBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.college_bean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.listener.ICollegeActivity;
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

public class CollegeActivity extends BaseActivity implements ICollegeActivity {
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;
    @Bind(R.id.deleteAll)
    TextView deleteAll;
    @Bind(R.id.edit_cancel)
    TextView edit_cancel;
    @Bind(R.id.recycleView_college)
    RecyclerView recyclerView;
    @Bind(R.id.relative_delete)
    RelativeLayout relative_delete;
    @Bind(R.id.linear_delete)
    LinearLayout linear_delete;
    @Bind(R.id.deleteCount_college)
    TextView deleteCount;
    @Bind(R.id.xrefreshView_collegeActivity)
    XRefreshView refreshView;

    private boolean flag = true;
    private ArrayList<CollegeBean> list;
    private boolean isVisible = false;
    private CollegeAdapter collegeAdapter;
    private NewsOneAdapter adapter;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int selectCount;
    private int localPosition = 1;
    private int pageCount = 10;
    private LoadingDialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_college;
    }

    @Override
    protected void initView() {

        String titleName = getIntent().getStringExtra("titleName");
        if (TextUtils.isEmpty(titleName)) {
            titleName = "标题";
        }
        setStateColor(this, true);
        title.setText(titleName);
        relative_delete.setVisibility(View.GONE);
        list = new ArrayList<>();
        loadingDialog = new LoadingDialog();
       // loadingDialog.showDialog(this, "");
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
    private void refresh() {
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
    @Override
    protected void httpData() {
        pageNum = 1;
        if (loadingDialog != null) {
            loadingDialog.showDialog(this, "");
        }
        if (NetWorkUtils.isNetworkConnected(this)) {
            Call<college_bean> call = MyApplication.getNetApi().collegeNews_get((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"","10",false,"");
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<college_bean>() {
                @Override
                public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                    if (response.isSuccessful()) {
                        college_bean body = response.body();
                        //  caigoulist body = response.body();

                        if (body != null) {
                            List<college_bean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();

                            if (list != null && list.size() > 0) {
                            //  LogUtil.i("yantao", "length:" + list.size());
                                lists.clear();
                                for (college_bean.DataBean.list_law_bean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("P");
                                    if(listBean.getprovince()!=null) {
                                        newBean.setAuthor(listBean.getprovince());
                                    }else{
                                        newBean.setAuthor("");
                                    }
                                    // newBean.setComnum("");
                                    newBean.setIsCollect(true);
                                    //  newBean.setClickNum("");
                                  //  newBean.setContent(listBean.getcontent());
                                    //  newBean.setDateformat("");
                                    newBean.setId(listBean.getinfoId());
                                    newBean.setLable(listBean.gettype());
                                    newBean.setImg("");
                                    newBean.setTitle(listBean.gettitle());
                                    newBean.setIsVideo("否");
                                    if(listBean.getpublishTime()!=null) {
                                        newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    } else {
                                        newBean.setVtime("");
                                    }
                                    if(listBean.getsource()!=null) {
                                        newBean.setSort(listBean.getsource());
                                    }else {
                                        newBean.setSort("");
                                    }

                                    lists.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                            }

                    else {
                      //  LogUtil.i("yantao", "else");
                        iv_empty.setVisibility(View.VISIBLE);

                    }

                } else {
                    iv_empty.setVisibility(View.VISIBLE);

                }

            } else {
                iv_empty.setVisibility(View.VISIBLE);
            }
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--1");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<college_bean> call, Throwable t) {
                    call.cancel();
                    LogUtil.i("yantao", "onFailure");
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--2");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                }
            });


        }
        else {

            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(this, "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
            if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
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

    @OnClick({R.id.image_back, R.id.text_putOut, R.id.deleteAll, R.id.linear_delete, R.id.edit_cancel})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:               //1.返回上一级
                this.finish();
                break;
            case R.id.text_putOut:              //2.“编辑”按钮
                clickTab();
                isVisible = !isVisible;
                if (list.size() > 0) {
                    list.get(0).setEdit(isVisible);
                    collegeAdapter.notifyDataSetChanged();
                }


                break;
            case R.id.deleteAll:                //3.左上角“全部删除”按钮
                collegeAdapter.deleteAll();

                break;
            case R.id.linear_delete:            //4.右下角“删除”按钮
                collegeAdapter.deleteItem();
                break;
            case R.id.edit_cancel:              //5.“取消”按钮
                clickTab();
                isVisible = !isVisible;
                if (list.size() > 0) {
                    list.get(0).setEdit(isVisible);
                    collegeAdapter.cancelAll();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void addDeleteCount(int num) {
        if (num == 100) {
            selectCount = 0;
            clickTab();
            isVisible = !isVisible;
            if (list.size() > 0) {
                list.get(0).setEdit(isVisible);
            }
        } else {
            selectCount += num;
            deleteCount.setText(selectCount + "");
            LogUtil.i("kl", "count:" + selectCount);
        }
    }

    private void httpLoadMore() {
        pageNum++;
        Call<college_bean> call = MyApplication.getNetApi().collegeNews_get((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"","10",false,"");
        call.enqueue(new Callback<college_bean>() {
            @Override
            public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                if (response.isSuccessful()) {
                    college_bean body = response.body();
                    if (body != null) {
                        List<college_bean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();

                        if (list != null && list.size() > 0) {
                            lists.clear();
                            for (college_bean.DataBean.list_law_bean listBean : list) {
                                NewBean2 newBean = new NewBean2();
                                newBean.setArtype("P");
                                if(listBean.getprovince()!=null) {
                                    newBean.setAuthor(listBean.getprovince());
                                }else {
                                    newBean.setAuthor("");
                                }
                                newBean.setIsCollect(true);
                                newBean.setId(listBean.getinfoId());
                                newBean.setLable(listBean.gettype());
                                newBean.setImg("");
                                newBean.setTitle(listBean.gettitle());
                                newBean.setIsVideo("否");
                                if(listBean.getpublishTime()!=null)
                                    newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                else
                                    newBean.setVtime( "");
                                if(listBean.getsource()!=null)
                                    newBean.setSort(listBean.getsource());
                                else
                                    newBean.setSort("");
                                lists.add(newBean);
                            }
                            adapter.notifyDataSetChanged();
                            iv_empty.setVisibility(View.GONE);


                        } else {
                            pageNum--;
                            ToastUtils.showMessage(CollegeActivity.this, "已加载到底部");
                        }
                    }else{
                        ToastUtils.showMessage(CollegeActivity.this, "已加载到底部");

                    }
                } else {
                    pageNum--;
                    ToastUtils.showMessage(CollegeActivity.this, "未知错误");
                }

                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<college_bean> call, Throwable t) {
                localPosition--;
                ToastUtils.showMessage(CollegeActivity.this, "未知错误");
                refreshView.stopLoadMore();
                call.cancel();
            }
        });
    }


    private void clickTab() {
        if (isVisible) {
            text_putOut.setVisibility(View.VISIBLE);
            image_back.setVisibility(View.VISIBLE);
            edit_cancel.setVisibility(View.GONE);
            deleteAll.setVisibility(View.GONE);
            relative_delete.setVisibility(View.GONE);


        } else {
            text_putOut.setVisibility(View.GONE);
            image_back.setVisibility(View.GONE);
            edit_cancel.setVisibility(View.VISIBLE);
            deleteAll.setVisibility(View.VISIBLE);
            relative_delete.setVisibility(View.VISIBLE);
            deleteCount.setText(selectCount + "");

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.clear();
            list = null;
        }

        if (loadingDialog!=null&&loadingDialog.isDialogShow()){
            loadingDialog.disMissDialog();
            loadingDialog=null;
        }
    }
}
