package com.ansiyida.cgjl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.KnowledgeDetailActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.KnowledgeSearchActivity;
import com.ansiyida.cgjl.adapter.cgjl_adapter.StudyRecyclerAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.cgjl_bean.StudyTypeBean;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
 * 学院模块
 * */
public class Fragment2 extends BaseFragment implements View.OnClickListener {
    @Bind(R.id.iv_search)
    ImageView iv_search;
    @Bind(R.id.iv_delete)
    ImageView edit_delete;
    @Bind(R.id.edittext_search)
    TextView editText;
    @Bind(R.id.recyclerView_fragment2)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_fragment2)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.linelayout)
    LinearLayout linelayout;
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.text_putOut)
    TextView textPutOut;
    private FragmentActivity activity;
    private ArrayList<StudyTypeBean.DataBean> lists;
    private int pageNum = 1;
    private int pageSize = 10;
    private String type = "全部法规";
    private StudyRecyclerAdapter studyRecyclerAdapter;

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment2;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        activity = getActivity();
        lists = new ArrayList<>();
        studyRecyclerAdapter = new StudyRecyclerAdapter(lists, getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(studyRecyclerAdapter);
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        imageBack.setVisibility(View.INVISIBLE);
        textTitle.setText("学院");
        textPutOut.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        refresh();
        httpData();
    }

    private void refresh() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                pageNum = 1;
                httpData();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                pageNum++;
                httpData();
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
        if (pageNum == 1) {
            lists.clear();
        }
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            Call<StudyTypeBean> call1 = MyApplication.getNetApi().getknowledgeType((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageSize + "");
            call1.enqueue(new Callback<StudyTypeBean>() {
                @Override
                public void onResponse(Call<StudyTypeBean> call1, Response<StudyTypeBean> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200) {
                            lists.addAll(response.body().getData().getList());
                            studyRecyclerAdapter.notifyDataSetChanged();
                        }
                    }
                    call1.cancel();
                    refreshView.stopRefresh();
                    refreshView.stopLoadMore();
                }

                @Override
                public void onFailure(Call<StudyTypeBean> call1, Throwable t) {
                    call1.cancel();
                    refreshView.stopRefresh();
                    refreshView.stopLoadMore();
                }
            });

        } else {
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
        }

    }

    public void refresh_date() {
        httpData();
    }

    @OnClick({R.id.iv_repeat, R.id.iv_delete, R.id.edittext_search})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_delete:                //1.分类
                editText.setText("");
                break;
            case R.id.edittext_search:                        //3.重新加载
                Intent intent_law = new Intent(getActivity(), KnowledgeSearchActivity.class);
                intent_law.putExtra("type", type);
                this.startActivityForResult(intent_law, 0);
                break;
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

    @Override
    public void onClick(View view) {
        String app_token1 = (String) SharedPreferenceUtils.get(getActivity(), "app_token", "");
        switch (view.getId()) {
            case R.id.image_qyml:
                if (app_token1.length() > 11) {
                    Intent intent = new Intent(getActivity(), KnowledgeDetailActivity.class);
                    intent.putExtra("resoure", "fragment2");
                    intent.putExtra("studyTypeId", 1 + "");
                    startActivity(intent);
                } else {
                    ToastUtils.showMessage(getActivity(), "请先登录");
                }
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
