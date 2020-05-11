package com.ansiyida.cgjl.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.CollegeAdapter;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.CollegeBean;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.HistoryBean;
import com.ansiyida.cgjl.bean.NewBean2;
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

public class HistoryActivity extends BaseActivity {
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.tv_txt)
    TextView tv_txt;
    @Bind(R.id.text_putOut)
    ImageView text_putOut;


    @Bind(R.id.recycleView_college)
    RecyclerView recyclerView;


    @Bind(R.id.xrefreshView_collegeActivity)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    private boolean flag = true;
    private ArrayList<CollegeBean> list;
    private HashMap count=new HashMap();
    private boolean loadmore=true;
    private boolean isVisible = false;
    private CollegeAdapter collegeAdapter;
    private NewsOneAdapter adapter;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int selectCount;
    private int history_count=0;
    private int localPosition = 1;
    private int pageCount = 10;
   // private LoadingDialog loadingDialog;
    private String history_time="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(HistoryActivity.this,"Android浏览历史","HistoryActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_college;
    }

    @Override
    protected void initView() {
        String titleName = getIntent().getStringExtra("titleName");
        if (TextUtils.isEmpty(titleName)) {
            titleName = "浏览历史";
        }
        setStateColor(this, true);
        title.setText(titleName);
      //  relative_delete.setVisibility(View.GONE);
        list = new ArrayList<>();

      //  loadingDialog = new LoadingDialog();
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

        adapter.setColletlistnear(new NewsOneAdapter.Colletlistnear() {
            @Override
            public void collet() {

            }
        });
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

            if (NetWorkUtils.isNetworkConnected(this)) {
                Call<HistoryBean> call = MyApplication.getNetApi().getReadList((String) SharedPreferenceUtils.get(this, "app_token", ""), pageNum + "", "100");
                // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                call.enqueue(new Callback<HistoryBean>() {
                    @Override
                    public void onResponse(Call<HistoryBean> call, Response<HistoryBean> response) {
                        if (response.isSuccessful()) {
                            HistoryBean body = response.body();

                            if (body.getData() != null) {
                                List<HistoryBean.DataBean.list_bean> list = body.getData().getlist_bean();
                                if (list != null && list.size() > 0) {
                                    lists.clear();
                                    count.clear();
                                    history_time="";
                                    history_count=0;
                                    loadmore=true;
                                    int size=0;
                                    for (HistoryBean.DataBean.list_bean listBean : list)
                                    {
                                        size++;
                                            if(size==list.size())
                                            {
                                                if (!(TimeUtils.mmtime_Time(listBean.getcreateTime())).equals(history_time)) {
                                                    if("".equals(history_time))
                                                    {
                                                        history_count = 1;
                                                        history_time= TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                        count.put(history_time, history_count);
                                                    }
                                                    else {
                                                        count.put(history_time, history_count);
                                                        history_count = 1;

                                                        history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                        count.put(history_time, history_count);
                                                    }
                                                } else {
                                                    history_count++;
                                                    count.put(history_time, history_count);
                                                }
                                            }
                                            else
                                            {
                                                if (!(TimeUtils.mmtime_Time(listBean.getcreateTime())).equals(history_time)) {
                                                    if("".equals(history_time))
                                                    {
                                                        history_count = 1;
                                                        history_time= TimeUtils.mmtime_Time(listBean.getcreateTime());

                                                    }
                                                    else {
                                                        count.put(history_time, history_count);
                                                        history_count = 1;
                                                        history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                    }
                                                } else {
                                                    history_count++;
                                                }
                                            }
                                        }
                                    try {
                                       history_time="";
                                        for (HistoryBean.DataBean.list_bean listBean : list) {
                                            NewBean2 newBean = new NewBean2();
                                            if ("purchaseInfo".equals(listBean.getgenre())) {
                                                newBean.setArtype("P");
                                                newBean.setgenre("purchaseInfo");
                                            } else if ("policyInfo".equals(listBean.getgenre())) {
                                                newBean.setArtype("L");
                                                newBean.setgenre("policyInfo");
                                            } else if ("purchaseDemand".equals(listBean.getgenre())){
                                                newBean.setArtype("CX");
                                                 newBean.setgenre("purchaseDemand");
                                        }else if("viewpointInfo".equals(listBean.getgenre())) {
                                                newBean.setArtype("P");
                                                newBean.setgenre("viewpointInfo");
                                        }else{
                                                newBean.setArtype("P");
                                                newBean.setgenre("purchaseInfo");
                                            }

                                                if((listBean.getprovince()+"").equals("")|(listBean.getprovince()+"").equals("null"))
                                                    newBean.setAuthor("全国");
                                                else
                                                    newBean.setAuthor(listBean.getprovince());
                                                // newBean.setComnum("");
                                                newBean.setIsCollect(listBean.getisCollection());
                                                newBean.setread_collect("false");
                                                //  newBean.setClickNum("");
                                                //  newBean.setContent(listBean.getcontent());
                                                //  newBean.setDateformat("");
                                                newBean.setId(listBean.getinfoId());
                                                newBean.setLable(listBean.gettype());
                                                newBean.setImg("");
                                                newBean.setTitle(listBean.gettitle());
                                                newBean.setIsVideo("否");
                                                if (listBean.getpublishTime() != null)
                                                    newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                                else
                                                    newBean.setVtime("");
                                                if (listBean.getsource() != null)
                                                    newBean.setSort(listBean.getsource());
                                                else
                                                    newBean.setSort("");

                                            if (!(TimeUtils.mmtime_Time(listBean.getcreateTime())).equals(history_time)) {
                                                    history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                    newBean.sethistory_time(TimeUtils.mmtime_Time(listBean.getcreateTime()));
                                                    newBean.sethistory_count("(阅读" + count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())) + "条信息)");
                                                } else {
                                                    newBean.sethistory_time("");
                                                    newBean.sethistory_count("");
                                                }

                                                lists.add(newBean);

                                        }
                                    }
                                    catch(Exception e)
                                    {
                                        e.toString();
                                    }
                                 //   lists.clear();
                                    adapter.notifyDataSetChanged();
                                    iv_empty.setVisibility(View.GONE);
                                    tv_txt.setVisibility(View.GONE);

                                } else {
                                    //  LogUtil.i("yantao", "else");
                                    iv_empty.setVisibility(View.VISIBLE);
                                    tv_txt.setVisibility(View.VISIBLE);
                                }

                            } else {
                                iv_empty.setVisibility(View.VISIBLE);
                                tv_txt.setVisibility(View.VISIBLE);
                            }

                        } else {
                            iv_empty.setVisibility(View.VISIBLE);
                            tv_txt.setVisibility(View.VISIBLE);
                        }
                        refreshView.stopRefresh();
                        LogUtil.i("yantao", "Gone--1");
                        iv_repeat.setVisibility(View.GONE);
                        iv_repeat.setClickable(false);
                    /*    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                            loadingDialog.disMissDialog();
                        }*/
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<HistoryBean> call, Throwable t) {
                        call.cancel();
                        LogUtil.i("yantao", "onFailure");
                        refreshView.stopRefresh();
                        LogUtil.i("yantao", "Gone--2");
                        iv_repeat.setVisibility(View.GONE);
                        iv_repeat.setClickable(false);

                    }
                });


            } else {

                iv_repeat.setVisibility(View.VISIBLE);
                ToastUtils.showMessage(this, "当前网络不可用");
                iv_repeat.setClickable(true);
                refreshView.stopRefresh();

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

    @OnClick({R.id.image_back, R.id.text_putOut})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:               //1.返回上一级
                this.finish();
                break;
            case R.id.text_putOut:              //2.“编辑”按钮
                initPopuWindow_cache();



                break;

            default:
                break;
        }
    }



    private void httpLoadMore() {
        pageNum++;
        Call<HistoryBean> call = MyApplication.getNetApi().getReadList((String) SharedPreferenceUtils.get(this, "app_token", ""), pageNum + "", "20");
        // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
        call.enqueue(new Callback<HistoryBean>() {
                 @Override
                public void onResponse(Call<HistoryBean> call, Response<HistoryBean> response) {
                    if (response.isSuccessful()) {
                        HistoryBean body = response.body();
                        //  caigoulist body = response.body();

                        if (body.getData() != null) {
                            List<HistoryBean.DataBean.list_bean> list = body.getData().getlist_bean();

                            if (list != null && list.size() > 0) {
                                int size=0;
                                for (HistoryBean.DataBean.list_bean listBean : list)
                                {
                                    size++;
                                     if(size==list.size())
                                    {
                                        if(size==1) {
                                            if (count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())) != null) {

                                                history_count = (int) count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())) + 1;
                                                count.remove(count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())));
                                                history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                count.put(history_time, history_count);
                                            } else {

                                                history_count = 1;

                                                history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                count.put(history_time, history_count);

                                            }
                                        }
                                        else
                                            {
                                                if (!(TimeUtils.mmtime_Time(listBean.getcreateTime())).equals(history_time))
                                                {

                                                    count.put(history_time, history_count);
                                                    history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                    history_count=1;
                                                    count.put(history_time, history_count);
                                                }
                                                else
                                                    history_count++;
                                                count.put(history_time, history_count);
                                            }
                                        }

                                    else
                                    {
                                        if (size==1) {
                                            if (count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())) != null) {

                                                history_count = (int) count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())) + 1;
                                                count.remove(count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())));
                                                history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());

                                            } else {

                                                history_count = 1;

                                                history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());


                                            }
                                        } else {
                                            if (!(TimeUtils.mmtime_Time(listBean.getcreateTime())).equals(history_time))
                                            {

                                                count.put(history_time, history_count);
                                                history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                history_count=1;
                                            }
                                            else
                                                history_count++;
                                        }
                                    }
                                }

                                //    NewBean2 newBean1 = new NewBean2();
                                //      newBean1.setArtype("PP");
                                //    lists.add(newBean1);
                                try {
                                    history_time="";
                                    for (HistoryBean.DataBean.list_bean listBean : list)
                                    {
                                        if (loadmore)
                                        {
                                            for (int i = 0; i < lists.size(); i++)
                                            {
                                            if (lists.get(i).gethistory_time().equals(TimeUtils.mmtime_Time(listBean.getcreateTime()))) {
                                                loadmore=false;
                                                history_time=TimeUtils.mmtime_Time(listBean.getcreateTime());
                                                NewBean2 newBean1 = new NewBean2();
                                                newBean1.setArtype(lists.get(i).getArtype());
                                                newBean1.setIsCollect(lists.get(i).getIsCollect());
                                                newBean1.setId(lists.get(i).getId());
                                                newBean1.setImg("");
                                                newBean1.setIsVideo("否");
                                                newBean1.setTitle(lists.get(i).getTitle());
                                                newBean1.setVtime(lists.get(i).getVtime());
                                                newBean1.setSort(lists.get(i).getSort());
                                                newBean1.sethistory_time(lists.get(i).gethistory_time());
                                                newBean1.sethistory_count("(阅读" + count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())) + "条信息)");
                                                lists.set(i, newBean1);
                                               break;
                                            }
                                                loadmore=false;
                                        }
                                        }
                                        NewBean2 newBean = new NewBean2();
                                        if ("purchaseInfo".equals(listBean.getgenre())) {
                                            newBean.setArtype("P");
                                            newBean.setgenre("purchaseInfo");
                                        } else if ("policyInfo".equals(listBean.getgenre())) {
                                            newBean.setArtype("L");
                                            newBean.setgenre("policyInfo");
                                        } else if ("purchaseDemand".equals(listBean.getgenre())){
                                            newBean.setArtype("CX");
                                            newBean.setgenre("purchaseDemand");
                                        }else if("viewpointInfo".equals(listBean.getgenre())) {
                                            newBean.setArtype("P");
                                            newBean.setgenre("viewpointInfo");
                                        }else{
                                            newBean.setArtype("P");
                                            newBean.setgenre("purchaseInfo");
                                        }
                                        if (listBean.getprovince() != null)
                                            newBean.setAuthor(listBean.getprovince());
                                        else
                                            newBean.setAuthor("");
                                        // newBean.setComnum("");
                                        newBean.setIsCollect(listBean.getisCollection());
                                        newBean.setread_collect("false");
                                        //  newBean.setClickNum("");
                                        //  newBean.setContent(listBean.getcontent());
                                        //  newBean.setDateformat("");
                                        newBean.setId(listBean.getinfoId());
                                        newBean.setLable(listBean.gettype());
                                        newBean.setImg("");
                                        newBean.setTitle(listBean.gettitle());
                                        newBean.setIsVideo("否");
                                        if (listBean.getpublishTime() != null)
                                            newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                        else
                                            newBean.setVtime("");
                                        if (listBean.getsource() != null)
                                            newBean.setSort(listBean.getsource());
                                        else
                                            newBean.setSort("");

                                        if (!(TimeUtils.mmtime_Time(listBean.getcreateTime())).equals(history_time)) {
                                            history_time = TimeUtils.mmtime_Time(listBean.getcreateTime());
                                            newBean.sethistory_time(TimeUtils.mmtime_Time(listBean.getcreateTime()));
                                            newBean.sethistory_count("(阅读" + count.get(TimeUtils.mmtime_Time(listBean.getcreateTime())) + "条信息)");
                                        }
                                        else {
                                            newBean.sethistory_time("");
                                            newBean.sethistory_count("");
                                        }
                                        lists.add(newBean);

                                    }
                                }
                                catch(Exception e)
                                {
                                    e.toString();
                                }
                                //   lists.clear();
                                adapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.GONE);
                                tv_txt.setVisibility(View.GONE);

                            } else {
                            pageNum--;
                           // ToastUtils.showMessage(HistoryActivity.this, "已加载到底部");
                        }
                    }else{
                     //   ToastUtils.showMessage(HistoryActivity.this, "已加载到底部");

                    }
                } else {
                    pageNum--;
                    ToastUtils.showMessage(HistoryActivity.this, "未知错误");
                }

                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<HistoryBean> call, Throwable t) {
                localPosition--;
                ToastUtils.showMessage(HistoryActivity.this, "未知错误");
                refreshView.stopLoadMore();
                call.cancel();
            }
        });
    }
    /**
     * 删除弹窗
     */
    private PopupWindow popupWindow_cache;
    private View contentView_cache;
    public void initPopuWindow_cache() {
        if (popupWindow_cache == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_cache = mLayoutInflater.inflate(R.layout.popwindow_del_sure1, null);
            popupWindow_cache = new PopupWindow(contentView_cache, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relative_out= (RelativeLayout) contentView_cache.findViewById(R.id.relative_out);
        RelativeLayout relative_in= (RelativeLayout) contentView_cache.findViewById(R.id.relative_in);
      //  ImageView relative_view= (ImageView) contentView_cache.findViewById(R.id.obtain_yanZheng_tuxing);

      //  relative_view.setBackgroundResource(R.drawable.anim_rocket_thrust);


     //   AnimationDrawable rocketAnimation  = (AnimationDrawable) relative_view.getBackground();
     //   rocketAnimation.start();
        TextView tv_sure= (TextView) contentView_cache.findViewById(R.id.tv_sure);
        TextView tv_cancel= (TextView) contentView_cache.findViewById(R.id.tv_cancel);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_cache.dismiss();
            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tv_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lists.clear();
                adapter.notifyDataSetChanged();
                Call<DefaultBean> call = MyApplication.getNetApi().delReadList((String) SharedPreferenceUtils.get(HistoryActivity.this, "app_token", ""));
                call.enqueue(new Callback<DefaultBean>() {
                    @Override
                    public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                        if (response.isSuccessful()) {
                            ToastUtils.showMessage(HistoryActivity.this, response.body().getMessage());
                            if ("200".equals(response.body().getStatus())) {

                                // iv.setImageResource(R.mipmap.icon_subscription_default3x);

                                ToastUtils.showMessage(HistoryActivity.this, "浏览记录已清空！");
                                httpData();
                                //    newBean2.setIsCollect(false);
                            }
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<DefaultBean> call, Throwable t) {

                        call.cancel();
                    }
                });
                popupWindow_cache.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_cache.dismiss();

            }
        });

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = 0.4f;
        getWindow().setAttributes(lp);
        popupWindow_cache.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_cache.setOutsideTouchable(true);
        popupWindow_cache.setFocusable(true);
        popupWindow_cache.showAtLocation(contentView_cache, Gravity.CENTER, 0, 0);
        popupWindow_cache.update();
//        popupWindow_cache.getContentView().startAnimation(AnimationUtil.createInAnimation(this, 300));
        popupWindow_cache.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }
    @Override
    protected void onResume() {
httpData();
        super.onResume();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.clear();
            list = null;
        }

    }
}
