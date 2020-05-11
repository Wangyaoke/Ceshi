package com.ansiyida.cgjl.activity.cgjl_activity;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.CiviMilitaryAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DropBean;
import com.ansiyida.cgjl.bean.cgjl_bean.CiViMilitaryBean;
import com.ansiyida.cgjl.bean.cgjl_bean.OptionBean;
import com.ansiyida.cgjl.dialog.LoadingDialog;
import com.ansiyida.cgjl.tab.DropdownButton;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class IndustrialParkActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.address_drop)
    DropdownButton addressDrop;
    @Bind(R.id.Type_drop)
    DropdownButton TypeDrop;
    @Bind(R.id.text)
    TextView text;
    @Bind(R.id.edit)
    EditText edit;
    @Bind(R.id.choose_rel)
    RelativeLayout chooseRel;
    @Bind(R.id.recycler)
    RecyclerView recycler;
    @Bind(R.id.line)
    LinearLayout line;
    //无数据
    @Bind(R.id.iv_empty)
    ImageView ivEmpty;
    @Bind(R.id.tv_txt_no)
    TextView tvTxtNo;
    //网络
    @Bind(R.id.iv_repeat)
    ImageView ivRepeat;
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.text_putOut)
    TextView textPutOut;
    @Bind(R.id.modetext)
    TextView modetext;
    @Bind(R.id.xrefreshView)
    XRefreshView xrefreshView;

    private Integer PageNum = 1;
    private Integer PageSize = 10;
    private String mode = "";
    private String address = "";
    private List<CiViMilitaryBean.DataBean.ListBean> CiViMilitaryList = new ArrayList<>();
    private CiviMilitaryAdapter civiMilitaryAdapter;
    private LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the FirebaseAnalytics instance.
        GoogleAssistant(IndustrialParkActivity.this,"Android产业园入驻","IndustrialParkActivity");
    }

    @Override
    protected int getContentView() {

        return R.layout.fragment_civilmilitary;
    }

    @Override
    protected void initView() {

        loadingDialog = new LoadingDialog();
        civiMilitaryAdapter = new CiviMilitaryAdapter(CiViMilitaryList,this);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(civiMilitaryAdapter);
        //加载更多
        xrefreshView.setPullLoadEnable(true);
        //允许下拉刷新
        xrefreshView.setPullRefreshEnable(true);
        xrefreshView.setAutoLoadMore(false);
    }

    @Override
    protected void initData() {
        getAddressAndType();
        textTitle.setText("军民融合");
        SetOnclick();
        if(loadingDialog!=null){
            loadingDialog.showDialog(this,"");
        }
        httpData();
    }

    private void getAddressAndType() {
        String app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
        Call<OptionBean> getAddress = MyApplication.getNetApi().getoption(app_token, 104);
        getAddress.enqueue(new Callback<OptionBean>() {
            @Override
            public void onResponse(Call<OptionBean> call, Response<OptionBean> response) {
                if(response.isSuccessful()){
                    List<OptionBean.DataBean> data = response.body().getData();
                    List<DropBean> list = new ArrayList<>();
                    for (int i =data.size()-1; i >=0; i--) {
                        list.add(new DropBean(data.get(i).getName()));
                    }
                    addressDrop.recyclerView = recycler;
                    addressDrop.line = line;
                    addressDrop.lable = false;
                    addressDrop.setData(list, data.get(data.size()-1).getName(), text, edit);
                    address= data.get(data.size()-1).getName();
                }
            }

            @Override
            public void onFailure(Call<OptionBean> call, Throwable t) {

            }
        });
        Call<OptionBean> getType = MyApplication.getNetApi().getoption(app_token, 110);
        getType.enqueue(new Callback<OptionBean>() {
            @Override
            public void onResponse(Call<OptionBean> call, Response<OptionBean> response) {
                if(response.isSuccessful()){
                    List<OptionBean.DataBean> data = response.body().getData();
                    List<DropBean> list = new ArrayList<>();
                    for (int i = data.size()-1; i >=0; i--) {
                        list.add(new DropBean(data.get(i).getName()));
                    }
                    TypeDrop.recyclerView = recycler;
                    TypeDrop.line = line;
                    TypeDrop.lable = false;
                    TypeDrop.setData(list, data.get(data.size()-1).getName(), modetext, edit);
                    mode=data.get(data.size()-1).getName();
                }
            }

            @Override
            public void onFailure(Call<OptionBean> call, Throwable t) {

            }
        });
    }

    public void SetOnclick() {
        imageBack.setOnClickListener(this);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                address = editable.toString();
                addressDrop.setText(editable.toString());
                PageNum = 1;
                httpData();
            }
        });
        modetext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mode = editable.toString();
                TypeDrop.setText(editable.toString());
                PageNum = 1;
                httpData();
            }
        });
        xrefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                PageNum = 1;
                CiViMilitaryList.clear();
                httpData();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                PageNum++;
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


    public void httpData() {
        if(PageNum==1){
            CiViMilitaryList.clear();
        }
        //请求数据
        if (NetWorkUtils.isNetworkConnected(this)) {
            String apptoken = (String) SharedPreferenceUtils.get(this, "app_token", "");
            Log.e("产业园", "httpData: "+ mode + address);
            Call<CiViMilitaryBean> ciViMilitaryBeanCall = MyApplication.getNetApi().getcivilMilitary(apptoken, PageNum, PageSize, mode, address);
            ciViMilitaryBeanCall.enqueue(new Callback<CiViMilitaryBean>() {
                @Override
                public void onResponse(Call<CiViMilitaryBean> call, Response<CiViMilitaryBean> response) {
                    if (response.isSuccessful()) {

                        CiViMilitaryBean body = response.body();
                        if (body.getStatus() == 200) {
                            List<CiViMilitaryBean.DataBean.ListBean> list = body.getData().getList();
                            if(list.size()>0) {
                                ivEmpty.setVisibility(View.GONE);
                                tvTxtNo.setVisibility(View.GONE);
                                if (PageNum == 1) {
                                    CiViMilitaryList.addAll(list);
                                    civiMilitaryAdapter.setCiviList(list);
                                } else {
                                    CiViMilitaryList.addAll(list);
                                    civiMilitaryAdapter.setCiviList(CiViMilitaryList);
                                }

                            }else{
                                if (PageNum == 1) {
                                    CiViMilitaryList.clear();
                                    civiMilitaryAdapter.setCiviList(CiViMilitaryList);
                                    ivEmpty.setVisibility(View.VISIBLE);
                                    tvTxtNo.setVisibility(View.VISIBLE);
                                }
                            }
                        } else {
                            if (PageNum == 1) {
                                ivEmpty.setVisibility(View.VISIBLE);
                                tvTxtNo.setVisibility(View.VISIBLE);
                            }
                        }
                        if (loadingDialog != null && loadingDialog.isDialogShow()) {
                            loadingDialog.disMissDialog();
                        }
                    } else {
                        if (loadingDialog != null && loadingDialog.isDialogShow()) {
                            loadingDialog.disMissDialog();
                        }
                        if (PageNum == 1) {
                            ivEmpty.setVisibility(View.VISIBLE);
                            tvTxtNo.setVisibility(View.VISIBLE);
                        }
                    }
                    xrefreshView.stopLoadMore();
                    xrefreshView.stopRefresh();


                }

                @Override
                public void onFailure(Call<CiViMilitaryBean> call, Throwable t) {
                    Log.e("CiviFragment", "onFailure: "+t.getMessage() );
                    if (loadingDialog != null && loadingDialog.isDialogShow()) {
                        loadingDialog.disMissDialog();
                    }
                }
            });


        } else {
            recycler.setVisibility(View.GONE);
            ivRepeat.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void setClickListener() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image_back:
                this.finish();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        CiViMilitaryList=null;
    }
}
