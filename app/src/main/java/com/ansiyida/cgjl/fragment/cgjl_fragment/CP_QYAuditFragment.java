package com.ansiyida.cgjl.fragment.cgjl_fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.AuditRecyclerAdapter;
import com.ansiyida.cgjl.bean.cgjl_bean.AuditCenterBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CP_QYAuditFragment extends Fragment {

    public String Mode;
    //@Bind(R.id.cpaudit_recyclerview)
    RecyclerView cpauditRecyclerview;
   // @Bind(R.id.xrefreshView_collegeActivity)
    XRefreshView xrefreshViewCollegeActivity;
    //@Bind(R.id.iv_empty)
    ImageView ivEmpty;
    //@Bind(R.id.iv_repeat)
    ImageView ivRepeat;
    //@Bind(R.id.tv_txt)
    TextView tvTxt;
    private int pageNum = 1;
    private LoadingDialog loadingDialog;
    private List<AuditCenterBean.DataBean.ListBean> list = new ArrayList<>();
    private AuditRecyclerAdapter auditRecyclerAdapter;
    private RelativeLayout noDateRel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cpaudit,container, false);
        initView(view, savedInstanceState);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    protected void initView(View view, Bundle savedInstanceState) {
        noDateRel = view.findViewById(R.id.noDataRel);
        cpauditRecyclerview = view.findViewById(R.id.cpaudit_recyclerview);
        xrefreshViewCollegeActivity = view.findViewById(R.id.xrefreshView_collegeActivity);
        ivEmpty = view.findViewById(R.id.iv_empty);
        ivRepeat = view.findViewById(R.id.iv_repeat);
        tvTxt = view.findViewById(R.id.tv_txt);

        cpauditRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        auditRecyclerAdapter = new AuditRecyclerAdapter(list, getContext(), Mode);
        cpauditRecyclerview.setAdapter(auditRecyclerAdapter);
        //允许加载更多
        xrefreshViewCollegeActivity.setPullLoadEnable(true);
        //允许下拉刷新
        xrefreshViewCollegeActivity.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        xrefreshViewCollegeActivity.setAutoLoadMore(false);
    }

    protected void initData() {
        onclick();
        http();
    }

    private void onclick() {
        xrefreshViewCollegeActivity.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                pageNum = 1;
                list.clear();
                http();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                pageNum++;
                http();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
    }

    private void http() {
        if (NetWorkUtils.isNetworkConnected(getContext())) {
            Call<AuditCenterBean> call = MyApplication.getNetApi().getcheckRecord((String) SharedPreferenceUtils.get(getContext(), "app_token", ""), pageNum + "", "20", Mode);
            call.enqueue(new Callback<AuditCenterBean>() {
                @Override
                public void onResponse(Call<AuditCenterBean> call, Response<AuditCenterBean> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus() == 200) {
                            if(response.body().getData().getList().size()>0) {
                                list.addAll(response.body().getData().getList());
                                auditRecyclerAdapter.notifyDataSetChanged();
                            }else{
                                if(pageNum==1) {
                                    noDateRel.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    } else {
                        Log.e("onResponse", "onResponse: "+response.toString() );
                        ToastUtils.showMessage(getContext(), response.toString());
                    }
                    xrefreshViewCollegeActivity.stopRefresh();
                    xrefreshViewCollegeActivity.stopLoadMore();
                    call.cancel();
                }

                @Override
                public void onFailure(Call<AuditCenterBean> call, Throwable t) {
                    call.cancel();
                    LogUtil.i("yantao", "onFailure"+t.getMessage());
                    xrefreshViewCollegeActivity.stopRefresh();
                    xrefreshViewCollegeActivity.stopLoadMore();
                    LogUtil.i("yantao", "Gone--2");
                    ivRepeat.setVisibility(View.GONE);
                    ivRepeat.setClickable(false);
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                }
            });

        } else {
            ivRepeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(getContext(), "当前网络不可用");
            ivRepeat.setClickable(true);
            xrefreshViewCollegeActivity.stopRefresh();
            if (loadingDialog != null && loadingDialog.isDialogShow()) {
                loadingDialog.disMissDialog();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
