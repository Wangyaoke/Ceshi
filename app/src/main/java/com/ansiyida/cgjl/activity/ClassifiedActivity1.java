package com.ansiyida.cgjl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.purchaseSecretBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.util.EditTextUtil;
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

public class ClassifiedActivity1 extends BaseActivity {
    @Bind(R.id.tv_txt_no)
    TextView tv_txt_no;
    @Bind(R.id.point_1)
    ImageView point_1;
    @Bind(R.id.relative_serch_top)
    RelativeLayout relative_serch_top;
    @Bind(R.id.relative_top)
    RelativeLayout relative_top;
    @Bind(R.id.iv_delete)
    ImageView edit_delete;
    @Bind(R.id.edittext_search)
    EditText editText;
    @Bind(R.id.iv_search)
    ImageView iv_search;
    @Bind(R.id.relative)
    RelativeLayout relative;
    private LinearLayout areaLayout;
    private boolean tabStateArr =true;// 标记tab的选中状态，方便设置
    private ImageView image_back;
    private String serch_keyword="";
    private TextView text_title;

    private NewsOneAdapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int pageCount = 20;
    private LoadingDialog loadingDialog;
    private boolean  isfinish=false;

    private ImageView iv_empty;
    private ImageView iv_repeat;
    private XRefreshView refreshView;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
        }
        catch (Exception e) {
            LogUtil.i("start:", e.toString());
        }
        GoogleAssistant(ClassifiedActivity1.this,"Android涉密采购","ClassifiedActivity1");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cgxq;
    }

    @Override
    protected void initView() {

        recyclerView= (RecyclerView) findViewById(R.id.recyclerView_spinner_zb);
        iv_repeat= (ImageView) findViewById(R.id.iv_repeat);
        iv_empty= (ImageView) findViewById(R.id.iv_empty);
        refreshView= (XRefreshView) findViewById(R.id.xrefreshView_spinner_zb);

        image_back= (ImageView) findViewById(R.id.image_back);
        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ClassifiedActivity1.this.finish();
            }
        });

        areaLayout = (LinearLayout) findViewById(R.id.area_layout);
        text_title=(TextView) findViewById(R.id.text_title);
        Intent intent =getIntent();
        text_title.setText(intent.getStringExtra("title"));
        serch_keyword = intent.getStringExtra("keyword");
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
        loadingDialog = new LoadingDialog();

    }

    @Override
    protected void initData() {
        httpData_RecyclerView(isfinish,serch_keyword);
        refresh();
    }
    private void refresh() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                httpData_RecyclerView(isfinish,serch_keyword);
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                httpLoadMore(isfinish,serch_keyword);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }
    private void httpData_RecyclerView(Boolean isFinish,String keyword) {
        pageNum = 1;
        if (loadingDialog != null) {
            loadingDialog.showDialog(this, "");
        }
        if (NetWorkUtils.isNetworkConnected(this)) {
            Call<purchaseSecretBean> call = MyApplication.getNetApi().getpurchaseSecret((String) SharedPreferenceUtils.get(ClassifiedActivity1.this, "app_token", ""),pageNum+"","20",isFinish,keyword);
            call.enqueue(new Callback<purchaseSecretBean>() {
                @Override
                public void onResponse(Call<purchaseSecretBean> call, Response<purchaseSecretBean> response) {
                    if (response.isSuccessful()) {
                        purchaseSecretBean body = response.body();
                        if (body.getData() != null) {
                            List<purchaseSecretBean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                            if (list != null && list.size() > 0) {
                                LogUtil.i("yantao", "length:" + list.size());
                                lists.clear();
                                for (purchaseSecretBean.DataBean.list_law_bean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("SM");
                                    newBean.setIsCollect(listBean.getisCollection());
                                    newBean.setId(listBean.getid());
                                    newBean.setTitle(listBean.gettitle());
                                    newBean.setLable(listBean.getsecretDegree());
                                    if(listBean.getpublishTime()!=null) {
                                        newBean.setJca_time("发布日期 " + TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    }else {
                                        newBean.setJca_time("发布日期                      ");
                                    }
                                    if(listBean.getendTime()!=null) {
                                        newBean.setVtime("有效时限 " + TimeUtils.mmtime_Time(listBean.getendTime()));
                                    }else {
                                        newBean.setVtime("有效时限                      ");
                                    }
                                    lists.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt_no.setVisibility(View.GONE);

                            } else {
                                LogUtil.i("yantao", "else");
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
                    LogUtil.i("yantao", "Gone--1");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<purchaseSecretBean> call, Throwable t) {
                    call.cancel();
                    LogUtil.i("yantao", "onFailure");
                    refreshView.stopRefresh();
                    ToastUtils.showMessage(ClassifiedActivity1.this, t.toString());

                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                }
            });


        } else {
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(this, "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
            if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
        }

    }
    private void httpLoadMore(Boolean isFinish,String keyword) {
        pageNum++;
        Call<purchaseSecretBean> call = MyApplication.getNetApi().getpurchaseSecret((String) SharedPreferenceUtils.get(ClassifiedActivity1.this, "app_token", ""),pageNum+"","20",isFinish,keyword);
        call.enqueue(new Callback<purchaseSecretBean>() {
            @Override
            public void onResponse(Call<purchaseSecretBean> call, Response<purchaseSecretBean> response) {
                if (response.isSuccessful()) {
                    purchaseSecretBean body = response.body();
                    if (body.getData() != null) {
                        List<purchaseSecretBean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                        if (list != null && list.size() > 0) {
                            LogUtil.i("yantao", "length:" + list.size());
                            for (purchaseSecretBean.DataBean.list_law_bean listBean : list) {
                                NewBean2 newBean = new NewBean2();
                                newBean.setArtype("SM");
                                newBean.setIsCollect(listBean.getisCollection());
                                newBean.setId(listBean.getid());
                                newBean.setTitle(listBean.gettitle());
                                newBean.setLable(listBean.getsecretDegree());
                                if (listBean.getpublishTime() != null)
                                    newBean.setJca_time("发布日期 " + TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                else
                                newBean.setJca_time( "发布日期                      ");
                                if (listBean.getendTime() != null)
                                    newBean.setVtime("有效时限 " + TimeUtils.mmtime_Time(listBean.getendTime()));
                                else
                                newBean.setVtime( "有效时限                      ");
                                lists.add(newBean);
                            }
                            adapter.notifyDataSetChanged();
                            iv_empty.setVisibility(View.GONE);


                        } else {
                            ToastUtils.showMessage(ClassifiedActivity1.this, "已加载到底部");
                            pageNum--;
                        }
                    } else {
                        ToastUtils.showMessage(ClassifiedActivity1.this, "已加载到底部");
                        pageNum--;
                    }
                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<purchaseSecretBean> call, Throwable t) {
                call.cancel();
                ToastUtils.showMessage(ClassifiedActivity1.this, t.toString());

                refreshView.stopLoadMore();


            }
        });
    }
    @Override
    protected void httpData() {


    }
    @OnClick({R.id.image_back,R.id.point_1,R.id.edittext_search})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.point_1:               //1.返回上一级
                relative_top.setVisibility(View.VISIBLE);
                point_1.setVisibility(View.GONE);
                relative_serch_top.setVisibility(View.GONE);
                editText.setText("");
                serch_keyword="";
                httpData_RecyclerView(isfinish,serch_keyword);
                break;
            case R.id.edittext_search:               //1.返回上一级
                relative_top.setVisibility(View.GONE);
                point_1.setVisibility(View.VISIBLE);
                relative_serch_top.setVisibility(View.VISIBLE);

                break;
            case R.id.image_back:               //1.返回上一级

                this.finish();
                break;
            default:
                break;

        }
    }

    @Override
    protected void setClickListener() {
        adapter.setCgxqListnear(new NewsOneAdapter.CGXQListnear() {
            @Override
            public void CGXQ(int position, String bj) {
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点

                    if (!"".equals(EditTextUtil.getEditTextStr(editText))){
                        edit_delete.setVisibility(View.VISIBLE);
                    }
                    LogUtil.i("jiao", "获取焦点");
                    iv_search.setClickable(true);
                    relative_top.setVisibility(View.GONE);
                    point_1.setVisibility(View.VISIBLE);
                    relative_serch_top.setVisibility(View.VISIBLE);

                } else {
                    // 失去焦点
                    LogUtil.i("jiao", "失去焦点");
                    iv_search.setClickable(false);
                    edit_delete.setVisibility(View.GONE);

                }

            }


        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //这里是我要做的操作！
                    searchNext();
                    return true;
                }
                return false;
            }
        });



        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNext();
            }
        });
        //编辑框输入监听
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (!"".equals(s1)){
                    edit_delete.setVisibility(View.VISIBLE);
                }else {
                    edit_delete.setVisibility(View.GONE);
                }
            }
        });

        //编辑器清空按钮监听
        edit_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
            }
        });


    }
    public void setFocus() {

        relative.setFocusable(true);
        relative.setFocusableInTouchMode(true);

        relative.requestFocus();
        relative.requestFocusFromTouch();
    }
    private void searchNext(){
        relative_top.setVisibility(View.GONE);
        point_1.setVisibility(View.VISIBLE);
        relative_serch_top.setVisibility(View.VISIBLE);
        String editTextStr = EditTextUtil.getEditTextStr(editText);
        if (!"".equals(editTextStr)) {
            SharedPreferenceUtils.put(this, "searchString", editTextStr);
            setFocus();
            visibleKeyBoard();
            iv_search.setClickable(false);
            serch_keyword=editTextStr;
            httpData_RecyclerView(isfinish,serch_keyword);
        } else {
            ToastUtils.showMessage(this, "请输入有效内容");
        }
    }
    private void visibleKeyBoard() {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }


    public void onBackPressed() {

        if(point_1.getVisibility()>0 )
            this.finish();
        else
        {
            relative_top.setVisibility(View.VISIBLE);
            point_1.setVisibility(View.GONE);
            relative_serch_top.setVisibility(View.GONE);
            editText.setText("");
            serch_keyword="";
            httpData_RecyclerView(isfinish,serch_keyword);}

    }

}


