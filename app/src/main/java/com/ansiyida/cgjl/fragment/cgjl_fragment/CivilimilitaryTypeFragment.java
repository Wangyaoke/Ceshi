package com.ansiyida.cgjl.fragment.cgjl_fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.CivilimitaryTypeAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.cgjl_bean.CiViMilitrayTypeBean;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class CivilimilitaryTypeFragment extends BaseFragment {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
    @Bind(R.id.tv_txt_no)
    TextView tvTxtNo;
    @Bind(R.id.iv_repeat)
    ImageView ivRepeat;
    @Bind(R.id.text_putOut)
    TextView textPutOut;

    private List<CiViMilitrayTypeBean.DataBean.ListBean> CiViTypeList = new ArrayList<>();
    private CivilimitaryTypeAdapter civilimitaryTypeAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_civilimilitarytype;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        textTitle.setText("军民融合");
        civilimitaryTypeAdapter = new CivilimitaryTypeAdapter(CiViTypeList, getContext());
        recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        recycler.setAdapter(civilimitaryTypeAdapter);
        imageBack.setVisibility(View.INVISIBLE);
        textPutOut.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        httpdata();
        setonclick();
    }

    private void setonclick() {

    }

    private void httpdata() {
        if (NetWorkUtils.isNetworkConnected(getContext())) {
            String apptoken = (String) SharedPreferenceUtils.get(getContext(), "app_token", "");
            Call<CiViMilitrayTypeBean> ciViMilitaryBeanCall = MyApplication.getNetApi().getCivilMilitaryType(apptoken, 1, 20);
            ciViMilitaryBeanCall.enqueue(new Callback<CiViMilitrayTypeBean>() {
                @Override
                public void onResponse(Call<CiViMilitrayTypeBean> call, Response<CiViMilitrayTypeBean> response) {
                    if (response.isSuccessful()) {
                        CiViMilitrayTypeBean body = response.body();
                        if (body.getStatus() == 200) {
                            List<CiViMilitrayTypeBean.DataBean.ListBean> list = body.getData().getList();
                            CiViTypeList.addAll(list);
                            civilimitaryTypeAdapter.notifyDataSetChanged();
                        } else {
                            ivEmpty.setVisibility(View.VISIBLE);
                            tvTxtNo.setVisibility(View.VISIBLE);
                        }
                    } else {
                        ivEmpty.setVisibility(View.VISIBLE);
                        tvTxtNo.setVisibility(View.VISIBLE);
                    }

                    call.cancel();
                }

                @Override
                public void onFailure(Call<CiViMilitrayTypeBean> call, Throwable t) {
                    Log.e("CiviFragment", "onFailure: " + t.getMessage());
                }
            });


        } else {
            recycler.setVisibility(View.GONE);
            ivRepeat.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
