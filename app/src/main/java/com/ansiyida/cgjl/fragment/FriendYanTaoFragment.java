package com.ansiyida.cgjl.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.FriendYantaoAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.FriendYantaoBean;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2018/3/16.
 */
public class FriendYanTaoFragment extends BaseFragment {
    @Bind(R.id.recyclerView_newsFragment)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_newsFragment)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    private String id;
    private int pageNum;
    private FriendYantaoAdapter yantaoAdapter;
    private ArrayList<FriendYantaoBean.DataBean.ListBean> lists;

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_news_fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Bundle bundle=getArguments();
        id=bundle.getString("id");
        if (id==null||"".equals(id)){
            id="1";
        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        lists=new ArrayList<>();
        yantaoAdapter = new FriendYantaoAdapter(getActivity(),lists);
        recyclerView.setAdapter(yantaoAdapter);
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        httpData();
    }
    private void httpData(){
        //初始
        pageNum=1;
        Call<FriendYantaoBean> call= MyApplication.getNetApi().getFriendYantao(id,pageNum+"");
        call.enqueue(new Callback<FriendYantaoBean>() {
            @Override
            public void onResponse(Call<FriendYantaoBean> call, Response<FriendYantaoBean> response) {
                if (response.isSuccessful()){
                    FriendYantaoBean.DataBean data = response.body().getData();
                    if (data!=null){
                        List<FriendYantaoBean.DataBean.ListBean> beanList = data.getList();
                        if (beanList!=null&&beanList.size()>0){
                            lists.clear();
                            lists.addAll(beanList);
                            yantaoAdapter.notifyDataSetChanged();
                            iv_empty.setVisibility(View.GONE);

                        }else {
                            iv_empty.setVisibility(View.VISIBLE);

                        }

                    }else {
                        iv_empty.setVisibility(View.VISIBLE);

                    }

                }else {
                    iv_empty.setVisibility(View.VISIBLE);

                }
                refreshView.stopRefresh();
                call.cancel();
            }

            @Override
            public void onFailure(Call<FriendYantaoBean> call, Throwable t) {
                refreshView.stopRefresh();
                iv_empty.setVisibility(View.VISIBLE);
                call.cancel();
            }
        });

    }
    private void httpLoadMore(){
        //上拉加载
        pageNum++;
        Call<FriendYantaoBean> call= MyApplication.getNetApi().getFriendYantao(id,pageNum+"");
        call.enqueue(new Callback<FriendYantaoBean>() {
            @Override
            public void onResponse(Call<FriendYantaoBean> call, Response<FriendYantaoBean> response) {
                if (response.isSuccessful()){
                    FriendYantaoBean.DataBean data = response.body().getData();
                    if (data!=null){
                        List<FriendYantaoBean.DataBean.ListBean> beanList = data.getList();
                        if (beanList!=null&&beanList.size()>0){
                            lists.addAll(beanList);
                            yantaoAdapter.notifyDataSetChanged();
                        }else {
                            ToastUtils.showMessage(getActivity(),"已加载到底部");
                            pageNum--;
                        }

                    }else {
                        ToastUtils.showMessage(getActivity(),"已加载到底部");
                        pageNum--;
                    }

                }else {
                    ToastUtils.showMessage(getActivity(),"未知错误");
                    pageNum--;

                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<FriendYantaoBean> call, Throwable t) {
                pageNum--;
                ToastUtils.showMessage(getActivity(),"未知错误");
                refreshView.stopLoadMore();
                call.cancel();
            }
        });

    }
    @Override
    protected void initData() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
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
    public void onDestroy() {
        super.onDestroy();
    }
}

