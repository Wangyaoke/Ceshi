package com.ansiyida.cgjl.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.adapter.cgjl_adapter.ColletShowAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.CollegeBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.college_bean;
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
public class CollectFragment extends BaseFragment{
    @Bind(R.id.tv_txt_no)
    TextView tv_txt_no;
    @Bind(R.id.recycleView_college)
    RecyclerView recyclerView;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.xrefreshView_historyFragment)
    XRefreshView refreshView;
    public boolean flag = false;
    private ArrayList<CollegeBean> list;
    private boolean isVisible = false;
    private NewsOneAdapter adapter;
    private ArrayList<NewBean2> lists;
    private int selectCount;
    private int localPosition = 1;
    private int pageCount = 20;
    public String mode="";
    private String type="";
    private Call<college_bean> call;

    private List<college_bean.DataBean.list_law_bean> collegelist = new ArrayList<>();
    private ColletShowAdapter colletShowAdapter;

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_collect;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        list = new ArrayList<>();
       Bundle arguments = getArguments();
      //  mode = arguments.getString("model","");
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
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
            colletShowAdapter = new ColletShowAdapter(collegelist,getActivity(),mode);
            http();
            onclick();
        }
        catch (Exception e)
        {
            e.toString();
        }

      refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                http();
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

    private void onclick() {
        adapter.setColletlistnear(new NewsOneAdapter.Colletlistnear() {
            @Override
            public void collet() {
                http();
            }
        });
        colletShowAdapter.setColletShowAdapterListnear(new ColletShowAdapter.ColletShowAdapterListnear() {
            @Override
            public void noCollet(String id, int Position) {

                CancelCollet(id,Position);
            }
        });

        adapter.setCgxqListnear(new NewsOneAdapter.CGXQListnear() {
            @Override
            public void CGXQ(int position,String bj) {
                if(bj.equals("取消收藏")){
                    lists.remove(position);
                    if(lists.size()==0) {
                        http();
                    }else {
                        adapter.notifyDataSetChanged();
                    }
                }else{
                    http();
                }
            }
        });
    }

    public void refreshed () {
       http();
   }

    @Override
    public void onResume() {
        super.onResume();
        collegelist.clear();
        http();
    }
    public void refresh(){
        http();
    }
    protected void http() {
        localPosition = 1;
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            if(!flag)
             call = MyApplication.getNetApi().collegeNews_get_all((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""),localPosition+"",pageCount + "",mode);
           else
                call = MyApplication.getNetApi().collegeNews_get((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""),localPosition+"",pageCount + "",flag,mode);

            // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
            call.enqueue(new Callback<college_bean>() {
                @Override
                public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                    if (response.isSuccessful()) {
                        college_bean body = response.body();
                        Log.e("hhh", "==== "+response.body().toString() );
                        if (body.getData() != null) {
                            collegelist.clear();
                            collegelist.addAll(body.getData().getlist_law_bean());
                            if (collegelist != null && collegelist.size() > 0) {
                                lists.clear();
                                for (college_bean.DataBean.list_law_bean listBean : collegelist) {
                                    Log.e("xxx", "Collet: "+listBean.toString() );
                                    NewBean2 newBean = new NewBean2();
                                    if("purchaseInfo".equals(mode)) {
                                        newBean.setArtype("P");
                                        if ("null".equals(listBean.getprovince() + "") | "".equals(listBean.getprovince()))
                                            newBean.setAuthor("全国");
                                        else
                                            newBean.setAuthor(listBean.getprovince() + "");
                                        newBean.setIsCollect(true);
                                        newBean.setId(listBean.getinfoId());
                                        newBean.settype(listBean.getuserId());
                                        newBean.setLable(listBean.gettype());
                                        newBean.setImg("");
                                        newBean.setTitle(listBean.gettitle());
                                        newBean.setIsVideo("否");
                                        newBean.setIsVideo("COL");
                                        if (listBean.getpublishTime() != null)
                                            newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                        else {
                                            newBean.setVtime("");
                                        }

                                        if (listBean.getsource() != null) {
                                            newBean.setSort(listBean.getsource());
                                            newBean.setgenre("");
                                        } else {
                                            newBean.setSort("");

                                        }
                                        newBean.setgenre("purchaseInfo");
                                    }else if("purchaseSecret".equals(mode))
                                    {
                                        newBean.setArtype("SM");

                                        newBean.setIsCollect(true);

                                        newBean.setId(listBean.getinfoId());

                                        newBean.setgenre("");
                                        newBean.setTitle(listBean.gettitle());
                                       newBean.setLable(listBean.getsecretDegree());
                                        newBean.setJca_time( "发布日期 "+TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                        newBean.setVtime( "有效时限 "+TimeUtils.mmtime_Time(listBean.getendTime()+""));
                                    }
                                    else if("purchaseDemand".equals(mode))
                                    {
                                        newBean.setwarn(listBean.getisWarn());
                                        newBean.setArtype("CX");
                                        newBean.setcx_collec("CX_TRUE");
                                        newBean.setendtime(listBean.getendTime());
                                        // newBean.setComnum("");
                                        newBean.setIsCollect(true);
                                        //  newBean.setClickNum("");

                                        //  newBean.setDateformat("");
                                        newBean.setId(listBean.getinfoId());
                                        newBean.setgenre("");
                                        newBean.setTitle(listBean.gettitle());

                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    }else if(mode.equals("viewpointInfo") || mode.equals("knowledge")){

                                    }

                                    lists.add(newBean);
                                }
                                if(mode.equals("viewpointInfo") || mode.equals("knowledge")){
                                    recyclerView.setAdapter(colletShowAdapter);
                                    colletShowAdapter.notifyDataSetChanged();
                                }else {
                                    recyclerView.setAdapter(adapter);
                                    adapter.notifyDataSetChanged();
                                    iv_empty.setVisibility(View.GONE);
                                    tv_txt_no.setVisibility(View.GONE);
                                }
                            } else {
                                lists.clear();
                                adapter.notifyDataSetChanged();
                                colletShowAdapter.notifyDataSetChanged();
                                iv_empty.setVisibility(View.VISIBLE);
                                tv_txt_no.setVisibility(View.VISIBLE);
                            }

                        } else {
                            iv_empty.setVisibility(View.VISIBLE);
                            tv_txt_no.setVisibility(View.VISIBLE);
                            lists.clear();
                            adapter.notifyDataSetChanged();
                            colletShowAdapter.notifyDataSetChanged();
                        }

                    } else {
                        iv_empty.setVisibility(View.VISIBLE);
                        tv_txt_no.setVisibility(View.VISIBLE);
                        lists.clear();
                        adapter.notifyDataSetChanged();
                        colletShowAdapter.notifyDataSetChanged();
                    }
                    refreshView.stopRefresh();
                    LogUtil.i("yantao", "Gone--1");
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                    call.cancel();
                }

                @Override
                public void onFailure(Call<college_bean> call, Throwable t) {
                    call.cancel();
                    refreshView.stopRefresh();
                    iv_repeat.setVisibility(View.GONE);
                    iv_repeat.setClickable(false);
                }
            });


        }
        else {
            lists.clear();
            adapter.notifyDataSetChanged();
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();

        }

    }

    private void httpLoadMore() {
        localPosition++;
        if(!flag)
            call = MyApplication.getNetApi().collegeNews_get_all((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""),localPosition+"",pageCount + "",mode);
        else
            call = MyApplication.getNetApi().collegeNews_get((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""),localPosition+"",pageCount + "",flag,mode);
        call.enqueue(new Callback<college_bean>() {
            @Override
            public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                if (response.isSuccessful()) {
                    college_bean body = response.body();
                    if (body.getData() != null) {
                        List<college_bean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();

                        if (list != null && list.size() > 0) {
                            for (college_bean.DataBean.list_law_bean listBean : list) {
                                NewBean2 newBean = new NewBean2();
                                if("purchaseInfo".equals(mode))
                                {   newBean.setArtype("P");
                                    if("null".equals(listBean.getprovince()+"")|"".equals(listBean.getprovince()))
                                        newBean.setAuthor("全国");
                                    else
                                        newBean.setAuthor(listBean.getprovince()+"");
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
                                    newBean.setIsVideo("COL");
                                    if(listBean.getpublishTime()!=null)
                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    else
                                        newBean.setVtime( "");
                                    if(listBean.getsource()!=null)
                                        newBean.setSort(listBean.getsource());
                                    else
                                        newBean.setSort("");}
                                else if("policyInfo".equals(mode))
                                {
                                    newBean.setArtype("L");
                                    //    newBean.setAuthor("["+listBean.getprovince()+"]");
                                    // newBean.setComnum("");
                                    newBean.setIsCollect(true);
                                    //  newBean.setDateformat("");
                                    //  newBean.setId("");
                                    //    newBean.setLable("["+listBean.gettype()+"]");
                                    //  newBean.setImg("");
                                    newBean.setId(listBean.getinfoId());
                                    newBean.setTitle(listBean.gettitle());
                                    newBean.setIsVideo("否");
                                    if(listBean.getcreateTime()!=null)
                                        newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    else
                                        newBean.setVtime("");
                                }
                                else if("purchaseSecret".equals(mode))
                                {
                                    newBean.setArtype("SM");

                                    newBean.setIsCollect(true);
                                    //  newBean.setClickNum("");

                                    //  newBean.setDateformat("");
                                    newBean.setId(listBean.getinfoId());


                                    newBean.setTitle(listBean.gettitle());
                                    newBean.setLable(listBean.getsecretDegree());
                                    newBean.setJca_time( "发布日期 "+TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                    newBean.setVtime( "有效时限 "+TimeUtils.mmtime_Time(listBean.getendTime()+""));
                                }
                                else if("purchaseDemand".equals(mode))
                                {
                                    newBean.setwarn(listBean.getisWarn());
                                    newBean.setArtype("CX");
                                    newBean.setcx_collec("CX_TRUE");
                                    newBean.setendtime(listBean.getendTime());
                                    // newBean.setComnum("");
                                    newBean.setIsCollect(true);
                                    //  newBean.setClickNum("");

                                    //  newBean.setDateformat("");
                                    newBean.setId(listBean.getinfoId());


                                    newBean.setTitle(listBean.gettitle());

                                    newBean.setVtime( TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                }
                                lists.add(newBean);
                            }
                            adapter.notifyDataSetChanged();
                            iv_empty.setVisibility(View.GONE);
                            tv_txt_no.setVisibility(View.GONE);

                        }

                    }
                        else {
                         //   ToastUtils.showMessage(getActivity(), "已经加载到底部");

                        }




                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<college_bean> call, Throwable t) {
                refreshView.stopLoadMore();
                call.cancel();
            }
        });
    }
    private void CancelCollet(String id, final int Position) {
        //取消收藏
        Log.e("CancelCollet",(String)SharedPreferenceUtils.get(getActivity(),"app_token","")+mode+id );
        Call<college_bean> call = MyApplication.getNetApi().DELETECollectionRecord((String)SharedPreferenceUtils.get(getActivity(),"app_token",""),mode,id);
        call.enqueue(new Callback<college_bean>() {
            @Override
            public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                if(response.isSuccessful()){
                    college_bean body = response.body();
                    if(body.getStatus().equals("200")) {
                        Toast.makeText(getActivity(), "取消收藏成功", Toast.LENGTH_SHORT).show();
                        collegelist.remove(Position);
                        if(collegelist.size()==0){
                            colletShowAdapter.notifyDataSetChanged();
                            iv_empty.setVisibility(View.VISIBLE);
                            tv_txt_no.setVisibility(View.VISIBLE);

                        }else{
                            colletShowAdapter.notifyDataSetChanged();
                        }

                    }else{
                        ToastUtils.showMessage(getContext(),body.getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<college_bean> call, Throwable t) {
                Log.e("StudyOnActivity", "ColletonFailure: "+t.getMessage() );
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.clear();
            list = null;
        }
    }


}
