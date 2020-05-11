package com.ansiyida.cgjl.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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
import com.ansiyida.cgjl.activity.InterstingTabActivity;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.DropBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.lawlist;
import com.ansiyida.cgjl.bean.policyTypeBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.tab.DropdownButton_law;
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

import static fm.jiecao.jcvideoplayer_lib.JCMediaManager.TAG;

public class Fragment2_beifen extends BaseFragment {
    @Bind(R.id.recyclerView_fragment2)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_fragment2)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.line)
    LinearLayout line;
    @Bind(R.id.tv_txt)
    TextView tv_txt;
  @Bind(R.id.time2)
  DropdownButton_law dropdownButton_policyType;
    @Bind(R.id.edit_view)
    EditText  edit_view_class;
    private NewsOneAdapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int pageSize = 10;
    private int pageCount = 20;
    private LoadingDialog loadingDialog;
    private List<policyTypeBean.DataBean> list_policyType;
    private String dropbutton_text;
    private List<DropBean> names;
    private int typeid=0;
    private String type="全部法规";
    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {

        return R.layout.layout_fragment2;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        names = new ArrayList<>();
        activity = getActivity();
        lists = new ArrayList<>();
     //  dropdownButton_policyType =  (DropdownButton) findViewById(R.id.time1);
        adapter = new NewsOneAdapter(lists, activity, getActivity().getWindow());
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



        edit_view_class.addTextChangedListener(textWatcher);
        dropdownButton_policyType.recyclerView=recyclerView;
        dropdownButton_policyType.line=line;
        dropdownButton_policyType.lable=true;
    }

    @Override
    protected void initData() {
       dropdownbuttonData();
        refresh();
    }


    private TextWatcher textWatcher = new TextWatcher() {
        String ll;
        @Override
        public void onTextChanged(CharSequence s, int start, int before,int count) {
             ll=list_policyType.get(dropdownButton_policyType.getselectPosition()).getcode();
            if(ll!=type)
            {
                type=ll;
                typeid=list_policyType.get(dropdownButton_policyType.getselectPosition()).getid();
                httpData();
            }
            Log.i(TAG, "onTextChanged: ");
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) {


        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
    public void refresh_date() {
        httpData();
    }
    private void refresh() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                httpData();
                //dropdownbuttonData();
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

    private void httpData() {
        Call<lawlist> call;
        pageNum = 1;
        if (loadingDialog != null) {
            loadingDialog.showDialog(getActivity(), "");
        }
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
        //    if(typeid==0)
             //  call = MyApplication.getNetApi().getlawlist1((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""),pageNum+"",pageCount + "");
           // call = MyApplication.getNetApi().getlawlist(pageNum+"",pageCount + "",4);
            //    else
            call = MyApplication.getNetApi().getlawlist((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""),pageNum+"",pageCount + "",typeid);
          //  Call<lawlist> call = MyApplication.getNetApi().getlawlist1(pageNum + "",10);;

            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<lawlist>() {
                @Override
                public void onResponse(Call<lawlist> call, Response<lawlist> response) {
                    if (response.isSuccessful()) {
                        lawlist body = response.body();
                      //  caigoulist body = response.body();

                        if (body != null) {
                            List<lawlist.DataBean.list_law_bean> list = body.getData().getlist_law_bean();

                                if (list != null && list.size() > 0) {
                                    LogUtil.i("yantao", "length:" + list.size());
                                      lists.clear();
                                    for (lawlist.DataBean.list_law_bean listBean : list) {
                                        NewBean2 newBean = new NewBean2();
                                        newBean.setArtype("L");
                                    //    newBean.setAuthor("["+listBean.getprovince()+"]");
                                       // newBean.setComnum("");
                                        newBean.setIsCollect(listBean.getisCollection());
                                      //  newBean.setClickNum("");
                                        newBean.setContent(listBean.getcontent());
                                      //  newBean.setDateformat("");
                                      //  newBean.setId("");
                                    //    newBean.setLable("["+listBean.gettype()+"]");
                                     //  newBean.setImg("");
                                        newBean.setId(listBean.getid());
                                        newBean.setTitle(listBean.gettitle());
                                        newBean.setIsVideo("否");
                                        if(listBean.getcreateTime()!=null)
                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getcreateTime()));
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
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<lawlist> call, Throwable t) {
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
        } else {
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
            if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
        }

    }
   private void dropdownbuttonData() {

        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            Call<policyTypeBean> call1 = MyApplication.getNetApi().getpolicyType("");
            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call1.enqueue(new Callback<policyTypeBean>() {
                @Override
                public void onResponse(Call<policyTypeBean> call1, Response<policyTypeBean> response) {
                    if (response.isSuccessful()) {
                        policyTypeBean body = response.body();
                        //  caigoulist body = response.body();

                        if (body != null) {
                            list_policyType = body.getData();

                            if (list_policyType != null && list_policyType.size() > 0) {
                              //  names.add(new DropBean("全部法规"));
                                for (policyTypeBean.DataBean listBean : list_policyType) {
                                    names.add(new DropBean(listBean.getname()));

                                }
                                dropdownButton_policyType.setData(names,names.get(0).getName(),null,edit_view_class);
                                typeid=list_policyType.get(0).getid();
                               //
                                // \typeid=0;
                                type=list_policyType.get(0).getcode();
                                httpData();
                            }

                        }

                    }

                    call1.cancel();

                }

                @Override
                public void onFailure(Call<policyTypeBean> call1, Throwable t) {

                    call1.cancel();
                   }
            });

        } else {
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
            if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
        }

    }
    private void httpLoadMore() {
        Call<lawlist> call;
        pageNum++;
    //    if(typeid==0)
       //     call = MyApplication.getNetApi().getlawlist1((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""),pageNum+"",pageCount + ""+"");
            // call = MyApplication.getNetApi().getlawlist(pageNum+"",pageCount + "",4);
    //    else
            call = MyApplication.getNetApi().getlawlist((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""),pageNum+"",pageCount + "",typeid);

        call.enqueue(new Callback<lawlist>() {
            @Override
            public void onResponse(Call<lawlist> call, Response<lawlist> response) {
                if (response.isSuccessful()) {
                    lawlist body = response.body();
                    List<lawlist.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                    if (list != null && list.size() > 0) {
                        LogUtil.i("yantao", "length:" + list.size());
                      //  lists.clear();
                        for (lawlist.DataBean.list_law_bean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("L");
                            //    newBean.setAuthor("["+listBean.getprovince()+"]");
                            // newBean.setComnum("");
                            newBean.setIsCollect(listBean.getisCollection());
                            //  newBean.setClickNum("");
                            newBean.setContent(listBean.getcontent());
                            //  newBean.setDateformat("");
                            //  newBean.setId("");
                            //    newBean.setLable("["+listBean.gettype()+"]");
                            //  newBean.setImg("");
                            newBean.setId(listBean.getid());
                            newBean.setTitle(listBean.gettitle());
                            newBean.setIsVideo("否");
                            newBean.setVtime( TimeUtils.mmtime_Time(listBean.getcreateTime()));
                            lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);


                    } else {
                        ToastUtils.showMessage(getActivity(), "已加载到底部");
                        pageNum--;
                    }

                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<lawlist> call, Throwable t) {
                call.cancel();
                LogUtil.i("yantao", "onFailure");
                refreshView.stopLoadMore();


            }
        });
    }

   @OnClick({ R.id.iv_repeat})
    public void click(View view) {
        switch (view.getId()) {
         /*   case R.id.saoYiSao_relative:                //1.扫一扫
                initPopuWindow_dropMenu();

                break;*/
        /*    case R.id.iv_sendYantao:                    //2.发表研讨
                startActivity(new Intent(activity, YanTaoSendActivity.class));
                break;*/
            case R.id.iv_repeat:                        //3.重新加载
                httpData();
                break;
       /*     case R.id.relative_search_bg:               //4.搜索
                MainActivity activity1 = (MainActivity) getActivity();
                activity1.choiceSearch();
                break;*/
        }
    }

    /**
     * 网址提交
     */
    private PopupWindow popupWindow_net;
    private View contentView_net;

    private void initPopuWindow_net() {
        final Activity activity = getActivity();
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
                    Call<DefaultBean2> call = MyApplication.getNetApi().uploadUrl(str_name, str_url, str_email, (String) SharedPreferenceUtils.get(getActivity(), "app_token", ""));
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

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow_net.setBackgroundDrawable(cd);
//        cd.setCallback(null);
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
        final Activity activity = getActivity();
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
                ToastUtils.showMessage(getActivity(), "功能未开放");

            }
        });
        interest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //兴趣按钮
                activity.startActivity(new Intent(activity, InterstingTabActivity.class));
                popupWindow1.dismiss();
//                ToastUtils.showMessage(getActivity(), "功能未开放");


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

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
     //   popupWindow1.showAsDropDown(saoYiSao);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lists != null) {
            lists.clear();
            lists = null;
        }
        if (loadingDialog != null && loadingDialog.isDialogShow()) {
            loadingDialog.disMissDialog();
            loadingDialog = null;
        }
    }
}
