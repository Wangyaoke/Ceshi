package com.ansiyida.cgjl.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.PersonalDynamicAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.PersonalDynamicBean;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2018/1/31.
 */
public class FriendDynamicFragment extends BaseFragment {
    @Bind(R.id.recyclerView_newsFragment)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_newsFragment)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    private int pageNum;
    private PersonalDynamicAdapter personalDynamicAdapter;
    private ArrayList<PersonalDynamicBean.DataBean.ListBean> lists;
    private String id;
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
        LogUtil.i("id","id:"+id);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        lists = new ArrayList<>();
        personalDynamicAdapter = new PersonalDynamicAdapter(getActivity(), lists);
        recyclerView.setAdapter(personalDynamicAdapter);

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
        Call<PersonalDynamicBean> call= MyApplication.getNetApi().getFriendDynamic(id,pageNum+"");
        call.enqueue(new Callback<PersonalDynamicBean>() {
            @Override
            public void onResponse(Call<PersonalDynamicBean> call, Response<PersonalDynamicBean> response) {
                if (response.isSuccessful()){
                    PersonalDynamicBean body = response.body();
                    if (body!=null){
                        PersonalDynamicBean.DataBean data = body.getData();
                        if (data!=null){
                            List<PersonalDynamicBean.DataBean.ListBean> beanList = data.getList();
                            if (beanList!=null&&beanList.size()>0){
                                lists.clear();
                                lists.addAll(beanList);
                                personalDynamicAdapter.notifyDataSetChanged();
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

                }else {
                    iv_empty.setVisibility(View.VISIBLE);
                    ToastUtils.showMessage(getActivity(),"获取动态列表失败");
                }
                call.cancel();
                refreshView.stopRefresh();
            }

            @Override
            public void onFailure(Call<PersonalDynamicBean> call, Throwable t) {
                iv_empty.setVisibility(View.VISIBLE);
                ToastUtils.showMessage(getActivity(),"未知错误");
                refreshView.stopRefresh();

                call.cancel();
            }
        });
    }
    private void httpLoadMore(){
        //上拉加载
        pageNum++;
        Call<PersonalDynamicBean> call= MyApplication.getNetApi().getFriendDynamic(id, pageNum + "");
        call.enqueue(new Callback<PersonalDynamicBean>() {
            @Override
            public void onResponse(Call<PersonalDynamicBean> call, Response<PersonalDynamicBean> response) {
                if (response.isSuccessful()){
                    PersonalDynamicBean body = response.body();
                    if (body!=null){
                        PersonalDynamicBean.DataBean data = body.getData();
                        if (data!=null){
                            List<PersonalDynamicBean.DataBean.ListBean> beanList = data.getList();
                            if (beanList!=null&&beanList.size()>0){
                                lists.addAll(beanList);
                                personalDynamicAdapter.notifyDataSetChanged();
                            }else {
                                ToastUtils.showMessage(getActivity(), "已加载到底部");
                            }
                        }else {
                            ToastUtils.showMessage(getActivity(), "已加载到底部");
                        }
                    }else {
                        ToastUtils.showMessage(getActivity(), "已加载到底部");
                    }

                }else {
                    ToastUtils.showMessage(getActivity(),"获取动态列表失败");
                }
                call.cancel();
                refreshView.stopLoadMore();
            }

            @Override
            public void onFailure(Call<PersonalDynamicBean> call, Throwable t) {
                ToastUtils.showMessage(getActivity(), "未知错误");
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
