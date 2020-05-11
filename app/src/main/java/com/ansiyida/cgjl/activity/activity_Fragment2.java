package com.ansiyida.cgjl.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.DropBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.law_seachbean;
import com.ansiyida.cgjl.bean.policyTypeBean;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class activity_Fragment2 extends BaseActivity {
    
    @Bind(R.id.iv_search)
    ImageView iv_search;
    @Bind(R.id.iv_delete)
    ImageView edit_delete;
    @Bind(R.id.edittext_search)
    EditText editText;
    @Bind(R.id.recyclerView_fragment2)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_fragment2)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.text_law)
    TextView text_law;
    @Bind(R.id.tv_txt)
    TextView tv_txt;
    @Bind(R.id.up_down)
    RelativeLayout up_down;
    @Bind(R.id.edit_view)
    EditText  edit_view_class;
    private HashMap type_id=new HashMap();
    private NewsOneAdapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int pageSize = 10;
    private int pageCount = 20;
    private List<policyTypeBean.DataBean> list_policyType;
    private String dropbutton_text;
    private List<DropBean> names;
    private int typeid=0;
    private String type="全部法规";
    private String serch_keyword="";
    private static final int REQUEST_CODE_law=111;

    @Override
    protected void initView() {
        names = new ArrayList<>();
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
        //loadingDialog = new LoadingDialog();
        editText.setFocusable(true);
        editText.requestFocus();
        visibleKeyBoard();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_fragment2;
    }



    @Override
    protected void initData() {
        Intent intent =getIntent();
        type = intent.getStringExtra("type");
        refresh();

    }
    private void visibleKeyBoard() {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public void refresh_date() {
       // httpData();
        httpseach();
    }
    private void refresh() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                httpseach();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                httpseachMore();
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

    }

    @Override
    protected void setClickListener() {
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
                    serch_keyword=EditTextUtil.getEditTextStr(editText);
                    //这里是我要做的操作！
                    httpseach();
                    return true;
                }
                return false;
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


    }

    private void httpseach() {
        Call<law_seachbean> call1;
        pageNum = 1;
        if (NetWorkUtils.isNetworkConnected(this)) {
            call1 = MyApplication.getNetApi().getlaw_search((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"",pageCount + "",type,serch_keyword);
            call1.enqueue(new Callback<law_seachbean>() {
                @Override
                public void onResponse(Call<law_seachbean> call1, Response<law_seachbean> response) {
                    if (response.isSuccessful()) {
                        law_seachbean body = response.body();

                        if (body != null) {
                            List<law_seachbean.DataBean> list = body.getData();

                            if (list != null && list.size() > 0) {
                                LogUtil.i("yantao", "length:" + list.size());
                                lists.clear();
                                for (law_seachbean.DataBean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("L");
                                    newBean.setIsCollect(listBean.getisCollection());
                                    newBean.setContent(listBean.getcontent());
                                    newBean.setId(listBean.getid());
                                    newBean.setTitle(listBean.gettitle());
                                    newBean.setIsVideo("否");
                                    if(listBean.getpublishTime()!=null)
                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    else
                                        newBean.setVtime("");
                                    lists.add(newBean);
                                }
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt.setVisibility(View.GONE);

                            } else {
                                lists.clear();
                                adapter.notifyDataSetChanged();
                                tv_txt.setVisibility(View.VISIBLE);
                                iv_empty.setVisibility(View.VISIBLE);

                            }

                        } else {
                            lists.clear();
                            adapter.notifyDataSetChanged();
                            iv_empty.setVisibility(View.VISIBLE);

                        }

                    } else {
                        lists.clear();
                        adapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.VISIBLE);
                    }
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--1");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    call1.cancel();
                }

                @Override
                public void onFailure(Call<law_seachbean> call1, Throwable t) {
                    call1.cancel();
                    LogUtil.i("yantao", "onFailure");
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--2");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                }
            });
        } else {
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(activity_Fragment2.this, "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
        }

    }
    private void httpseachMore() {
        Call<law_seachbean> call1;
        pageNum++;
        call1 = MyApplication.getNetApi().getlaw_search((String) SharedPreferenceUtils.get(this, "app_token", ""),pageNum+"",pageCount + "",type,serch_keyword);
        call1.enqueue(new Callback<law_seachbean>() {
            @Override
            public void onResponse(Call<law_seachbean> call1, Response<law_seachbean> response) {
                if (response.isSuccessful()) {
                    law_seachbean body = response.body();
                    List<law_seachbean.DataBean> list = body.getData();
                    if (list != null && list.size() > 0) {
                        LogUtil.i("yantao", "length:" + list.size());
                        for (law_seachbean.DataBean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("L");
                            newBean.setIsCollect(listBean.getisCollection());
                            newBean.setContent(listBean.getcontent());
                            newBean.setId(listBean.getid());
                            newBean.setTitle(listBean.gettitle());
                            newBean.setIsVideo("否");
                            if(listBean.getpublishTime()!=null)
                                newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                            else
                            lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);


                    } else {
                      //  ToastUtils.showMessage(activity_Fragment2.this, "已加载到底部");
                        pageNum--;
                    }

                }
                refreshView.stopLoadMore();
                call1.cancel();
            }

            @Override
            public void onFailure(Call<law_seachbean> call1, Throwable t) {
                call1.cancel();
                LogUtil.i("yantao", "onFailure");
                refreshView.stopLoadMore();


            }
        });
    }
   @OnClick({ R.id.iv_repeat,R.id.up_down,R.id.iv_delete,R.id.point})
    public void click(View view) {
        switch (view.getId()) {
           case R.id.up_down:
               this.startActivityForResult(new Intent(this, Activity_law.class), REQUEST_CODE_law);
                break;
            case R.id.iv_delete:
                editText.setText("");
                  break;
            case R.id.point:
                finish();
                break;
            case R.id.iv_repeat:                        //3.重新加载
                httpseach();
                break;
        }
    }

    /**
     * 网址提交
     */
    private PopupWindow popupWindow_net;
    private View contentView_net;

    private void initPopuWindow_net() {
        final Activity activity =this;
        if (popupWindow_net == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
            contentView_net = mLayoutInflater.inflate(R.layout.popwindow_net, null);
            popupWindow_net = new PopupWindow(contentView_net, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        Button btn_cancel = (Button) contentView_net.findViewById(R.id.btn_cancel);
        Button btn_submit = (Button) contentView_net.findViewById(R.id.btn_submit);
        RelativeLayout relativeLayout = (RelativeLayout) contentView_net.findViewById(R.id.relative);
        LinearLayout linear = (LinearLayout) contentView_net.findViewById(R.id.linear);
        final EditText edit_1 = (EditText) contentView_net.findViewById(R.id.edit_1);
        final EditText edit_2 = (EditText) contentView_net.findViewById(R.id.edit_2);
        final EditText edit_3 = (EditText) contentView_net.findViewById(R.id.edit_3);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_net.dismiss();
            }
        });
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_net.dismiss();
            }
        });
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str_name = EditTextUtil.getEditTextStr(edit_1);
                String str_url = EditTextUtil.getEditTextStr(edit_2);
                String str_email = EditTextUtil.getEditTextStr(edit_3);
                if (!"".equals(str_name) || !"".equals(str_url)) {
                    Call<DefaultBean2> call = MyApplication.getNetApi().uploadUrl(str_name, str_url, str_email, (String) SharedPreferenceUtils.get(activity_Fragment2.this, "app_token", ""));
                    call.enqueue(new Callback<DefaultBean2>() {
                        @Override
                        public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                            if (response.isSuccessful()) {
                                ToastUtils.showMessage(activity, "提交成功");
                            }
                            popupWindow_net.dismiss();
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean2> call, Throwable t) {
                            ToastUtils.showMessage(activity, "提交失败");
                            popupWindow_net.dismiss();
                            call.cancel();
                        }
                    });
                } else {
                    ToastUtils.showMessage(activity, "请填写网站名称或网址");
                }
            }
        });
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow_net.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_net.setOutsideTouchable(true);
        popupWindow_net.setFocusable(true);
        popupWindow_net.showAtLocation(contentView_net, Gravity.CENTER, 0, 0);
        popupWindow_net.update();
        popupWindow_net.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 下拉菜单
     */
    private PopupWindow popupWindow1;
    private View contentView1;

    private void initPopuWindow_dropMenu() {
        final Activity activity = this;
        if (popupWindow1 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
            contentView1 = mLayoutInflater.inflate(R.layout.saoyisao_layout, null);
            popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        LinearLayout sao = (LinearLayout) contentView1.findViewById(R.id.linear_sao);
        LinearLayout interest = (LinearLayout) contentView1.findViewById(R.id.linear_interest);
        LinearLayout net = (LinearLayout) contentView1.findViewById(R.id.linear_net);
        sao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //扫一扫按钮
                ToastUtils.showMessage(activity_Fragment2.this, "功能未开放");

            }
        });
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //兴趣按钮
                activity.startActivity(new Intent(activity, InterstingTabActivity.class));
                popupWindow1.dismiss();
            }
        });
        net.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //网址提交按钮
                popupWindow1.dismiss();
                initPopuWindow_net();
            }
        });

        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
        popupWindow1.showAtLocation(contentView1, Gravity.CENTER, 0, 0);
        popupWindow1.update();
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        String ll;
        super.onActivityResult(requestCode, resultCode, data);

         if (requestCode ==REQUEST_CODE_law) {
             if(data!=null)
             {  ll = data.getStringExtra("option");
             if (ll != type) {
                 type = ll;
                 text_law.setText(type);
                 editText.setText("");
                 serch_keyword=EditTextUtil.getEditTextStr(editText);
                 httpseach();}
             }
         }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lists != null) {
            lists.clear();
            lists = null;
        }
    }
}
