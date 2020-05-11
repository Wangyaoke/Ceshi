package com.ansiyida.cgjl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.ansiyida.cgjl.bean.CollegeBean;
import com.ansiyida.cgjl.bean.CompanyBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
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

public class qyml1_activity extends BaseActivity implements View.OnClickListener {
    @Bind(R.id.tv_txt_no)
    TextView tv_txt_no;
    @Bind(R.id.text_cpzj)
    TextView text_cpzj;
    @Bind(R.id.line)
    LinearLayout line;
    @Bind(R.id.relative_serch_top)
    RelativeLayout relative_serch_top;
    @Bind(R.id.relative_top)
    RelativeLayout relative_top;
    @Bind(R.id.point_1)
    ImageView point_1;
    @Bind(R.id.iv_delete)
    ImageView edit_delete;
    @Bind(R.id.edittext_search)
    EditText editText;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.line_hy)
    TextView line_hy;
    @Bind(R.id.line_qy)
    TextView line_qy;
    @Bind(R.id.find_hy)
    TextView find_hy;
    @Bind(R.id.find_qy)
    TextView find_qy;
    @Bind(R.id.xrefreshView)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.iv_search)
    ImageView iv_search;
    @Bind(R.id.relative)
    RelativeLayout relative;
    private NewsOneAdapter adapter;
    private ArrayList<NewBean2> lists;
    private ArrayList<CollegeBean> list;
    private int pageNum = 1;
    private int selectCount;
    private int pageCount = 10;
    private LoadingDialog loadingDialog;
    private String serch_keyword="";
    private String tradetype="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(qyml1_activity.this,"Android企业名录","qyml1_activity");
    }

    @Override
    protected int getContentView() {

        return R.layout.qyml_layout;
    }

    @Override
    protected void initView() {
        title.setText("企业名录");
        text_cpzj.setText("");
        list = new ArrayList<>();
        loadingDialog = new LoadingDialog();

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
        // httpData();
        //   refresh();
        find_hy.setTextColor(getResources().getColor(R.color.gray_dan));
        line_hy.setVisibility(View.GONE);
        find_qy.setTextColor(getResources().getColor(R.color.color_blue));
        line_qy.setBackgroundResource(R.color.color_blue);
        tradetype="";
    }

    @Override
    protected void httpData() {
        httpData_RecyclerView((String) SharedPreferenceUtils.get(this, "app_token", ""),serch_keyword,tradetype);
    }


    @Override
    protected void setClickListener() {
        text_cpzj.setOnClickListener(this);
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                httpData_RecyclerView((String) SharedPreferenceUtils.get(qyml1_activity.this, "app_token", ""),serch_keyword,tradetype);

            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                httpLoadMore((String) SharedPreferenceUtils.get(qyml1_activity.this, "app_token", ""),serch_keyword,tradetype);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

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
                    line.setVisibility(View.GONE);
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
//                    // 先隐藏键盘
                  /*  ((InputMethodManager) editText.getContext()
                           .getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(qyml1_activity.this.getCurrentFocus().getWindowToken(),
                                   InputMethodManager.HIDE_NOT_ALWAYS);*/
                    // 搜索，进行自己要的操作...

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

    @OnClick({R.id.image_back,R.id.find_hy,R.id.find_qy,R.id.point_1,R.id.edittext_search,R.id.text_cpzj})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.point_1:               //1.返回上一级
                relative_top.setVisibility(View.VISIBLE);
                line.setVisibility(View.VISIBLE);
                point_1.setVisibility(View.GONE);
                relative_serch_top.setVisibility(View.GONE);
                editText.setText("");
                serch_keyword="";
                tradetype="";
                httpData();
              //  this.finish();
                break;
            case R.id.image_back:               //1.返回上一级

                this.finish();
                break;
            case R.id.edittext_search:               //1.返回上一级

                relative_top.setVisibility(View.GONE);
                line.setVisibility(View.GONE);
                point_1.setVisibility(View.VISIBLE);
                relative_serch_top.setVisibility(View.VISIBLE);
                break;
            case R.id.find_hy:               //1.返回上一级
                find_hy.setTextColor(getResources().getColor(R.color.color_blue));
                line_hy.setBackgroundResource(R.color.color_blue);
                line_hy.setVisibility(View.VISIBLE);
               find_qy.setTextColor(getResources().getColor(R.color.gray_dan));
                line_qy.setVisibility(View.GONE);
                line_qy.setBackgroundResource(R.color.gray_dan);
                tradetype="";
                httpData_RecyclerView((String) SharedPreferenceUtils.get(qyml1_activity.this, "app_token", ""),serch_keyword,tradetype);

             //   this.finish();
                break;
            case R.id.find_qy:               //1.返回上一级
                find_hy.setTextColor(getResources().getColor(R.color.gray_dan));
                line_hy.setBackgroundResource(R.color.gray_dan);
                line_hy.setVisibility(View.GONE);
                find_qy.setTextColor(getResources().getColor(R.color.color_blue));
                line_qy.setBackgroundResource(R.color.color_blue);
                line_qy.setVisibility(View.VISIBLE);
                tradetype="";
                httpData_RecyclerView((String) SharedPreferenceUtils.get(qyml1_activity.this, "app_token", ""),serch_keyword,tradetype);

             //   this.finish();
                break;
            default:
                break;

        }
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
     //   if (!"".equals(editTextStr)) {
            SharedPreferenceUtils.put(this, "searchString", editTextStr);

            setFocus();
            visibleKeyBoard();
            iv_search.setClickable(false);
         /*   if (localPosition == 0) {
                if (isFirst != 0) {
                    searchFragment1.httpData();
                } else {
                    isFirst++;
                }
            } else {
                viewpager.setCurrentItem(0);
            }*/
            serch_keyword=editTextStr;

            httpData_RecyclerView((String) SharedPreferenceUtils.get(qyml1_activity.this, "app_token", ""),serch_keyword,tradetype);

//httpData_RecyclerView("",editTextStr,"","","");
      /*  } else {
            ToastUtils.showMessage(this, "请输入有效内容");
        }*/
    }
    private void visibleKeyBoard() {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0) ;
    }
    void returnResult(String sdata) {
        Intent result = new Intent();
        result.putExtra("keyword", sdata);
        setResult(ActivityCodeUtil.KEYWORD , result);
        finish();
    }
    private void httpData_RecyclerView(String apptken,String keyword,String tradetype1) {
        pageNum = 1;
        if (loadingDialog != null) {
            loadingDialog.showDialog(qyml1_activity.this, "");
        }
        if (NetWorkUtils.isNetworkConnected(qyml1_activity.this)) {
            Call<CompanyBean> call = MyApplication.getNetApi().getcompany(apptken,pageNum+"","20",keyword,tradetype1);
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<CompanyBean>() {
                @Override
                public void onResponse(Call<CompanyBean> call, Response<CompanyBean> response) {
                    if (response.isSuccessful()) {
                        CompanyBean body = response.body();
                        //  caigoulist body = response.body();

                        if (body != null) {
                            List<CompanyBean.DataBean> list = body.getData();

                            if (list != null && list.size() > 0) {
                                LogUtil.i("yantao", "length:" + list.size());
                                lists.clear();
                                for (CompanyBean.DataBean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("QY");
                                    //   newBean.setImg(listBean.getimg());
                                    //   newBean.setAuthor("["+listBean.getprovince()+"]");
                                    // newBean.setComnum("");
                                    //  newBean.setIsCollect(listBean.getisCollection());
                                    //  newBean.setClickNum("");
                                    //  newBean.setContent(listBean.getproductSummary());
                                    //  newBean.setDateformat("");
                                    newBean.setId(listBean.getId());
                                    newBean.setContent(listBean.getcompanySummary());
                                    //    newBean.setImg("1");
                                    newBean.setTitle(listBean.getcompanyName());
                                    //  newBean.setIsVideo("否");
                                    //   newBean.setgenre(listBean.getgenre());
                                    newBean.setTime( TimeUtils.mmtime_Time(listBean.getcreateTime()));
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
                    //   LogUtil.i("yantao", "Gone--1");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<CompanyBean> call, Throwable t) {
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
            ToastUtils.showMessage(qyml1_activity.this, "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
            if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
        }

    }
    private void httpLoadMore(String apptken,String keyword,String tradetype1) {
        // String path = "http://api.map.baidu.com/geocoder?output=json&location=23.131427,113.379763&ak=esNPFDwwsXWtsQfw4NMNmur1";
        pageNum++;
        Call<CompanyBean> call = MyApplication.getNetApi().getcompany(apptken, pageNum + "", "20", keyword,tradetype1);

        //  Call<caigoulist> call = MyApplication.getNetApi().getcaigou("",pageNum+"","10");
        call.enqueue(new Callback<CompanyBean>() {
            @Override
            public void onResponse(Call<CompanyBean> call, Response<CompanyBean> response) {
                if (response.isSuccessful()) {
                    CompanyBean body = response.body();
                    List<CompanyBean.DataBean> list = body.getData();
                    if (list != null && list.size() > 0) {
                        LogUtil.i("yantao", "length:" + list.size());
                        //       lists.clear();
                        for (CompanyBean.DataBean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("QY");
                            //   newBean.setImg(listBean.getimg());
                            //   newBean.setAuthor("["+listBean.getprovince()+"]");
                            // newBean.setComnum("");
                            //  newBean.setIsCollect(listBean.getisCollection());
                            //  newBean.setClickNum("");
                            //  newBean.setContent(listBean.getproductSummary());
                            //  newBean.setDateformat("");
                            newBean.setId(listBean.getId());
                            newBean.setContent(listBean.getcompanySummary());
                            //    newBean.setImg("1");
                            newBean.setTitle(listBean.getcompanyName());
                            //  newBean.setIsVideo("否");
                            //   newBean.setgenre(listBean.getgenre());
                            newBean.setTime( TimeUtils.mmtime_Time(listBean.getcreateTime()));
                            lists.add(newBean);
                            //  lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);


                    } else {
                        ToastUtils.showMessage(qyml1_activity.this, "已加载到底部");
                        pageNum--;
                    }

                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<CompanyBean> call, Throwable t) {
                call.cancel();
                LogUtil.i("yantao", "onFailure");
                refreshView.stopLoadMore();


            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
    public void onBackPressed() {
    //   super.onBackPressed();
       // if(point_1.getVisibility())
        if(point_1.getVisibility()>0 )
            this.finish();
        else
        {
            relative_top.setVisibility(View.VISIBLE);
            line.setVisibility(View.VISIBLE);
            point_1.setVisibility(View.GONE);
            relative_serch_top.setVisibility(View.GONE);}
        editText.setText("");
        serch_keyword="";
        tradetype="";
        httpData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
        }
    }
}



