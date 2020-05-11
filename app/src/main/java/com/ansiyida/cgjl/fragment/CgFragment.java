package com.ansiyida.cgjl.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.CG_dy_Activity;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.purchaseDemandBean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2018/1/3.
 */
public class CgFragment extends BaseFragment {
    @Bind(R.id.line)
    LinearLayout line;

    @Bind(R.id.tv_txt_no)
    TextView tv_txt_no;
    @Bind(R.id.recyclerView_spinner_zb)
    RecyclerView recyclerView;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.xrefreshView_spinner_zb)
    XRefreshView refreshView;
    private Call<purchaseDemandBean> call;
    private NewsOneAdapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int pageCount = 20;
   // private LoadingDialog loadingDialog;


    public String cg_keyword = "";
    private boolean isfinish;
    public String mode = "";

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_spinner_zb2;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        lists = new ArrayList<>();
        adapter = new NewsOneAdapter(lists, this.getActivity(), this.getActivity().getWindow());
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
       // loadingDialog = new LoadingDialog();

    }


    @Override
    protected void initData() {
        try {


            // if (mode == 2) {
            lists = new ArrayList<>();
            LinearLayoutManager manager = new LinearLayoutManager(getActivity());
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            adapter = new NewsOneAdapter(lists, getActivity(), this.getActivity().getWindow());
            recyclerView.setAdapter(adapter);
            httpData_RecyclerView(cg_keyword);
            //  } else {
            //       iv_empty.setVisibility(View.VISIBLE);
            //   }
        } catch (Exception e) {
            e.toString();
        }
        adapter.setCgxqListnear(new NewsOneAdapter.CGXQListnear() {
            @Override
            public void CGXQ(int position, String bj) {

            }
        });
        adapter.setColletlistnear(new NewsOneAdapter.Colletlistnear() {
            @Override
            public void collet() {

            }
        });
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                httpData_RecyclerView(cg_keyword);
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                httpLoadMore(cg_keyword);
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });


    }


    public void refreshed() {
        httpData_RecyclerView(cg_keyword);
    }

    private void  httpData_RecyclerView(String keyword) {
        pageNum = 1;
      /*  if (loadingDialog != null) {
            loadingDialog.showDialog(getActivity(), "");
        }*/
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            if ("all".equals(mode))
                call = MyApplication.getNetApi().getpurchaseDemand1((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", keyword);
            else if ("true".equals(mode))
                call = MyApplication.getNetApi().getpurchaseDemand((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", true, keyword);
            else if ("false".equals(mode))
                call = MyApplication.getNetApi().getpurchaseDemand((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", false, keyword);
            else
                call = MyApplication.getNetApi().getpurchaseDemand1((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", keyword);

            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<purchaseDemandBean>() {
                @Override
                public void onResponse(Call<purchaseDemandBean> call, Response<purchaseDemandBean> response) {
                    if (response.isSuccessful()) {
                        purchaseDemandBean body = response.body();
                        //  caigoulist body = response.body();

                        if (body != null) {
                            List<purchaseDemandBean.DataBean> list = body.getData();

                            if (list != null && list.size() > 0) {
                                LogUtil.i("yantao", "length:" + list.size());
                                lists.clear();
                                for (purchaseDemandBean.DataBean listBean : list) {
                                    NewBean2 newBean = new NewBean2();
                                    newBean.setArtype("CX_dy");

                                    // newBean.setComnum("");
                                    newBean.setIsCollect(listBean.getisCollection());
                                    //  newBean.setClickNum("");
if(listBean.getisFinish())
    newBean.setLable("已对接");
else
    newBean.setLable("未对接");
                                    //  newBean.setDateformat("");
                                    newBean.setId(listBean.getid());


                                    newBean.setTitle(listBean.gettitle());

                                    newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
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
                /*    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
                    call.cancel();
                }

                @Override
                public void onFailure(Call<purchaseDemandBean> call, Throwable t) {
                    call.cancel();
                    LogUtil.i("yantao", "onFailure");
                    refreshView.stopRefresh();
                    ToastUtils.showMessage(getActivity(), t.toString());

                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    /*if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }*/
                }
            });


        } else {
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
       /*     if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }*/
        }

    }

    private void httpLoadMore(String keyword) {
        // String path = "http://api.map.baidu.com/geocoder?output=json&location=23.131427,113.379763&ak=esNPFDwwsXWtsQfw4NMNmur1";
        pageNum++;
        if ("all".equals(mode))
            call = MyApplication.getNetApi().getpurchaseDemand1((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", keyword);
        else if ("true".equals(mode))
            call = MyApplication.getNetApi().getpurchaseDemand((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", true, keyword);
        else if ("false".equals(mode))
            call = MyApplication.getNetApi().getpurchaseDemand((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", false, keyword);
        else
            call = MyApplication.getNetApi().getpurchaseDemand1((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", keyword);

        //  Call<caigoulist> call = MyApplication.getNetApi().getcaigou("",pageNum+"","10");
        call.enqueue(new Callback<purchaseDemandBean>() {
            @Override
            public void onResponse(Call<purchaseDemandBean> call, Response<purchaseDemandBean> response) {
                if (response.isSuccessful()) {
                    purchaseDemandBean body = response.body();
                    List<purchaseDemandBean.DataBean> list = body.getData();
                    if (list != null && list.size() > 0) {
                        LogUtil.i("yantao", "length:" + list.size());
                     //   lists.clear();
                        for (purchaseDemandBean.DataBean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("CX_dy");
                            if(listBean.getisFinish())
                                newBean.setLable("已对接");
                            else
                                newBean.setLable("未对接");
                            // newBean.setComnum("");
                            newBean.setIsCollect(listBean.getisCollection());
                            //  newBean.setClickNum("");

                            //  newBean.setDateformat("");
                            newBean.setId(listBean.getid());


                            newBean.setTitle(listBean.gettitle());

                            newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
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
            public void onFailure(Call<purchaseDemandBean> call, Throwable t) {
                call.cancel();
                ToastUtils.showMessage(getActivity(), t.toString());

                refreshView.stopLoadMore();


            }
        });
    }

    private void httpDataresume(String keyword) {
        int size;
        if(lists!=null)
        {if(lists.size()!=0)
            size=lists.size();
        else
            size=20;}
        else
            size=20;
        // String path = "http://api.map.baidu.com/geocoder?output=json&location=23.131427,113.379763&ak=esNPFDwwsXWtsQfw4NMNmur1";
     //   pageNum++;
        if ("all".equals(mode))
            call = MyApplication.getNetApi().getpurchaseDemand1((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), "1", size+"", keyword);
        else if ("true".equals(mode))
            call = MyApplication.getNetApi().getpurchaseDemand((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", true, keyword);
        else if ("false".equals(mode))
            call = MyApplication.getNetApi().getpurchaseDemand((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", false, keyword);
        else
            call = MyApplication.getNetApi().getpurchaseDemand1((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", "20", keyword);

        //  Call<caigoulist> call = MyApplication.getNetApi().getcaigou("",pageNum+"","10");
        call.enqueue(new Callback<purchaseDemandBean>() {
            @Override
            public void onResponse(Call<purchaseDemandBean> call, Response<purchaseDemandBean> response) {
                if (response.isSuccessful()) {
                    purchaseDemandBean body = response.body();
                    List<purchaseDemandBean.DataBean> list = body.getData();
                    if (list != null && list.size() > 0) {
                        LogUtil.i("yantao", "length:" + list.size());
                          lists.clear();
                        for (purchaseDemandBean.DataBean listBean : list) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setArtype("CX_dy");
                            if(listBean.getisFinish())
                                newBean.setLable("已对接");
                            else
                                newBean.setLable("未对接");
                            // newBean.setComnum("");
                            newBean.setIsCollect(listBean.getisCollection());
                            //  newBean.setClickNum("");

                            //  newBean.setDateformat("");
                            newBean.setId(listBean.getid());


                            newBean.setTitle(listBean.gettitle());

                            newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                            lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                        iv_empty.setVisibility(View.GONE);


                    } else {
                     //   ToastUtils.showMessage(getActivity(), "已加载到底部");
                        pageNum--;
                    }

                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<purchaseDemandBean> call, Throwable t) {
                call.cancel();
                ToastUtils.showMessage(getActivity(), t.toString());
                refreshView.stopLoadMore();
            }
        });
    }


    @Override
    public void onResume() {
        httpDataresume(cg_keyword);
        super.onResume();
        // MobclickAgent.onResume(this);
        CG_dy_Activity activity = (CG_dy_Activity) getActivity();
        activity.refreshed();

        /*int clickPosition = (int) SharedPreferenceUtils.get(getContext(), "CXClickPosition", -1);
        Object clickCollege  = SharedPreferenceUtils.get(getContext(),"CXClickCollege","");
        Log.e("onResume", "onResume: "+clickPosition+clickCollege );
        if(clickPosition!=-1){
            if(!clickCollege.equals("")){
                if(clickCollege.equals("true")){
                    lists.get((Integer) clickPosition).setIsCollect(true);
                }else{
                    lists.get((Integer) clickPosition).setIsCollect(false);
                }
            }
            adapter.notifyItemChanged((Integer) clickPosition);
        }
        SharedPreferenceUtils.remove(getContext(),"CXClickCollege");
        SharedPreferenceUtils.remove(getContext(),"CXClickCollege");*/
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }


}
