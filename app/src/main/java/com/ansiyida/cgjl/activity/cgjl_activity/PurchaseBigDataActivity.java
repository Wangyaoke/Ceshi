package com.ansiyida.cgjl.activity.cgjl_activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.TextView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.BigDataRecyclerViewAdapter;
import com.ansiyida.cgjl.adapter.cgjl_adapter.ListPopWindowAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseBigDataTrendBean;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseWordCloudBean;
import com.ansiyida.cgjl.fragment.cgjl_fragment.ChartFragment;
import com.ansiyida.cgjl.util.ScreenDiscolorationUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PurchaseBigDataActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    RadioButton textTitle;
    @Bind(R.id.ProcurementData_ChooseTime)
    RadioButton ProcurementDataChooseTime;
    @Bind(R.id.ProcurementData_DayNum)
    TextView ProcurementDataDayNum;
    @Bind(R.id.WordCloud_ChooseTime)
    RadioButton WordCloudChooseTime;
    @Bind(R.id.WordCloud)
    ImageView WordCloud;
    @Bind(R.id.WordCloud_recycler)
    RecyclerView WordCloudRecycler;
    @Bind(R.id.ChartFrame)
    FrameLayout ChartFrame;

    private ListPopupWindow listPopupWindow;
    private List<String> poplist = new ArrayList<>();
    private ListPopWindowAdapter listPopWindowAdapter;
    private BigDataRecyclerViewAdapter recyclerViewAdapter;
    private String ChooseType;
    private String KeyWord;
    private List<String> purchasePopwindowList = new ArrayList<>();
    private List<PurchaseBigDataTrendBean.DataBean> data;
    private String time = "15";
    private String app_token;
    private String WordCloudtime = "30";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        GoogleAssistant(PurchaseBigDataActivity.this,"Android采购大数据","PurchaseBigDataActivity");
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_big_purchase_data;
    }

    @Override
    protected void initView() {
        app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
        SharedPreferenceUtils.put(this, "SetPopItemSize", 12);
        SharedPreferenceUtils.put(this, "ChartViewJudge", "PurchaseBigData");

        //初始化View
        listPopupWindow = new ListPopupWindow(this);
        listPopWindowAdapter = new ListPopWindowAdapter(poplist, this);
        recyclerViewAdapter = new BigDataRecyclerViewAdapter(this, "PurChaseData");

        //初始化设置
        WordCloudRecycler.setLayoutManager(new LinearLayoutManager(this));
        WordCloudRecycler.setAdapter(recyclerViewAdapter);
        WordCloudRecycler.setFocusable(false);

        listPopupWindow.setAdapter(listPopWindowAdapter);
        listPopupWindow.setModal(true);
        listPopupWindow.setHeight(200);
        WordCloudRecycler.setFocusable(false);
    }

    @Override
    protected void initData() {
        ProcurementDataChooseTime.setText("近" + time + "天");
        WordCloudChooseTime.setText("近" + WordCloudtime + "天");
    }

    @Override
    protected void httpData() {
        SelctionWordCloud();
        changeChartData();
    }

    private void SelctionWordCloud() {
        //查询词云部分数据
        Call<PurchaseWordCloudBean> purchaseWordCloudDataCall = MyApplication.getNetApi().getPurchaseWordCloudData(app_token, WordCloudtime);
        purchaseWordCloudDataCall.enqueue(new Callback<PurchaseWordCloudBean>() {
            @Override
            public void onResponse(Call<PurchaseWordCloudBean> call, Response<PurchaseWordCloudBean> response) {
                if (response.isSuccessful()) {
                    PurchaseWordCloudBean body = response.body();
                    if (body.getStatus() == 200) {
                        List<PurchaseWordCloudBean.DataBean> data = body.getData();
                        int[] color = new int[]{0xffF6EA2A, 0xffE66166, 0xff00BAEB, 0xffBEB1EA, 0xffF9C719, 0xffED6894, 0xffFAEF21, 0xff404BE6
                                , 0xff236DC0, 0xffB6B2B2, 0xff2FB3CB, 0xff56BB13, 0xffDCF247, 0xffEB030E, 0xffECA72F, 0xff7919C3, 0xffF62A6E, 0xffF62AEA, 0xff2AD2F6, 0xff2AF672};
                        if (data.size() >= color.length) {
                            for (int i = 0; i < color.length; i++) {
                                data.get(i).setColor(color[i]);
                            }
                        }
                        //Log.e("数据", "onResponse: "+data.size()+WordCloudtime );
                        if (data.size() > 0) {
                            Glide.with(PurchaseBigDataActivity.this).load(data.get(0).getCloud_img()).into(WordCloud);
                            data.add(0, new PurchaseWordCloudBean.DataBean());
                            recyclerViewAdapter.setPurchaseWordCloudList(data);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PurchaseWordCloudBean> call, Throwable t) {

            }
        });
    }

    @Override
    protected void setClickListener() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ProcurementDataChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseType = "ProcurementDataChooseTime";
                listPopupWindow.setAnchorView(ProcurementDataChooseTime);
                poplist.clear();
                poplist.add("近15天");
                poplist.add("近30天");
                poplist.add("近90天");
                poplist.add("近半年");
                poplist.add("近一年");
                listPopupWindow.show();
                listPopWindowAdapter.notifyDataSetChanged();
            }
        });

        WordCloudChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChooseType = "WordCloudChooseTime";
                listPopupWindow.setAnchorView(WordCloudChooseTime);
                poplist.clear();
                poplist.add("近30天");
                poplist.add("近60天");
                poplist.add("近90天");
                listPopupWindow.show();
                listPopWindowAdapter.notifyDataSetChanged();
            }
        });

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ChooseType.equals("ProcurementDataChooseTime")) {
                    ProcurementDataChooseTime.setText(poplist.get(position));
                    if (poplist.get(position).contains("半年")) {
                        time = "180";
                    } else if (poplist.get(position).contains("一年")) {
                        time = "365";
                    } else {
                        String replace = poplist.get(position).replace("近", "").replace("天", "");
                        time = replace;
                    }
                    changeChartData();
                } else if (ChooseType.equals("WordCloudChooseTime")) {
                    WordCloudChooseTime.setText(poplist.get(position));
                    String replace = poplist.get(position).replace("近", "").replace("天", "");
                    WordCloudtime = replace;
                    SelctionWordCloud();
                }
                listPopupWindow.dismiss();
            }
        });

        listPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (ChooseType.equals("ProcurementDataChooseTime")) {

                } else if (ChooseType.equals("WordCloudChooseTime")) {

                }
            }
        });

        textTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ChooseKeyWord();
            }
        });
    }

    public void changeChartData() {
        //采购大数据趋势图
        Call<PurchaseBigDataTrendBean> purchaseBigDataTrendDataCall = MyApplication.getNetApi().getPurchaseBigDataTrendData(app_token, time);
        purchaseBigDataTrendDataCall.enqueue(new Callback<PurchaseBigDataTrendBean>() {
            @Override
            public void onResponse(Call<PurchaseBigDataTrendBean> call, Response<PurchaseBigDataTrendBean> response) {
                if (response.isSuccessful()) {
                    PurchaseBigDataTrendBean body = response.body();
                    if (body != null && body.getStatus() == 200) {
                        data = body.getData();

                        ChartFragment chartFragment = new ChartFragment();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", (Serializable) data);
                        chartFragment.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.ChartFrame, chartFragment).commit();
                    }
                }
            }

            @Override
            public void onFailure(Call<PurchaseBigDataTrendBean> call, Throwable t) {

            }
        });
    }



    private void ChooseKeyWord() {
        View view = View.inflate(this, R.layout.purchase_popwindow, null);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ;
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                ScreenDiscolorationUtil.ChangeDiscoloration(PurchaseBigDataActivity.this, 1);
            }
        });
        ScreenDiscolorationUtil.ChangeDiscoloration(PurchaseBigDataActivity.this, (float) 0.5);
        RecyclerView purchase_popwindow = view.findViewById(R.id.purchase_popwindow);
        purchase_popwindow.setLayoutManager(new LinearLayoutManager(PurchaseBigDataActivity.this));
        BigDataRecyclerViewAdapter bigDataRecyclerViewAdapter = new BigDataRecyclerViewAdapter(PurchaseBigDataActivity.this, "PurchaseDataPopwindow");
        purchase_popwindow.setAdapter(bigDataRecyclerViewAdapter);
        purchasePopwindowList.clear();
        purchasePopwindowList.add("涉密采购");
        purchasePopwindowList.add("公开招标");
        purchasePopwindowList.add("单一来源");
        bigDataRecyclerViewAdapter.setPurchasePopwindow(purchasePopwindowList);

        bigDataRecyclerViewAdapter.setPurchasePopwindowListnear(new BigDataRecyclerViewAdapter.PurchasePopwindowListnear() {
            @Override
            public void Onclick(int position) {
                textTitle.setText(purchasePopwindowList.get(position));
                popupWindow.dismiss();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferenceUtils.remove(this, "SetPopItemSize");
        SharedPreferenceUtils.remove(this, "ChartViewJudge");
        SharedPreferenceUtils.remove(this, "invalidata");
    }
}
