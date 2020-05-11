package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.EnterpriseBigDataActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.PurchaseBigDataActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.BigDataListBean;
import com.ansiyida.cgjl.bean.cgjl_bean.EnterpriseBusinessBean;
import com.ansiyida.cgjl.bean.cgjl_bean.EnterpriseDataBean;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseBigDataTrendBean;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseWordCloudBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BigDataRecyclerViewAdapter extends RecyclerView.Adapter {


    private Context context;
    private String mode;
    //Enter
    private List<Object> EnterList;
    private ArrayList<Integer> EnterColors;
    private List<String> purchasePopwindowList = new ArrayList<>();
    private List<EnterpriseDataBean.DataBean> data = new ArrayList<>();
    private List<BigDataListBean.DataBean> BigDataList = new ArrayList<>();
    private List<PurchaseBigDataTrendBean.DataBean> Caigoudata = new ArrayList<>();
    private List<EnterpriseBusinessBean.DataBean> EnterpriseBusinessList = new ArrayList<>();
    private List<PurchaseWordCloudBean.DataBean> PurchaseWordCloudList = new ArrayList<>();
    private float Num;
    public BigDataRecyclerViewAdapter(Context context, String mode) {
        this.context = context;
        this.mode = mode;
    }

    public void setEnterData(List<Object> list, ArrayList<Integer> colors) {
        this.EnterList = list;
        this.EnterColors = colors;
    }
    public void setEnterBusinessList(List<EnterpriseBusinessBean.DataBean> EnterpriseBusinessList,float  Num){
        this.EnterpriseBusinessList = EnterpriseBusinessList;
        this.Num = Num;
        notifyDataSetChanged();
    }
    public void setPurchasePopwindow(List<String> list) {
        this.purchasePopwindowList = list;
        notifyDataSetChanged();
    }
    public void setPurchaseWordCloudList(List<PurchaseWordCloudBean.DataBean> list){
        this.PurchaseWordCloudList=list;
        notifyDataSetChanged();
    }
    public void setBigDataList(List<BigDataListBean.DataBean> list){
        this.BigDataList = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new BigDataViewHolder(View.inflate(context, R.layout.bigdata_recyclerview_layout, null));
        } else if (viewType == 1) {
            return new EnterpriseViewHolder(View.inflate(context, R.layout.enterprise_recyclerview_layout, null));
        } else if (viewType == 2) {
            return new PurchaseViewHolder(View.inflate(context, R.layout.enterprise_recyclerview_layout, null));
        } else if (viewType == 3) {
            return new PurchasePopwindowViewHolder(View.inflate(context, R.layout.listpopwindow_layout, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BigDataViewHolder) {
            if(position==1) {
                SelectionData();
            }
            BigDataViewHolder bigDataViewHolder = (BigDataViewHolder) holder;
            bigDataViewHolder.bigdataRel.setOnClickListener(new View.OnClickListener() {
                private Intent intent =new Intent();
                @Override
                public void onClick(View v) {
                    String name = BigDataList.get(position).getName();
                    if(name.equals("企业大数据")){
                        intent.setClass(context, EnterpriseBigDataActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("data", (Serializable) data);
                        intent.putExtras(bundle);
                    }else if(name.equals("采购大数据")){
                        intent.setClass(context, PurchaseBigDataActivity.class);
                    }
                    context.startActivity(intent);
                }
            });
            Glide.with(context).load(BigDataList.get(position).getImage()).error(R.mipmap.icon_placeholder).into(bigDataViewHolder.bigdataImage);
        } else if (holder instanceof EnterpriseViewHolder) {
            EnterpriseViewHolder enterpriseViewHolder = (EnterpriseViewHolder) holder;
            if (position == 0) {
                enterpriseViewHolder.TitleLin.setVisibility(View.VISIBLE);
                enterpriseViewHolder.DataLin.setVisibility(View.GONE);
                enterpriseViewHolder.viewLine.setVisibility(View.GONE);
            } else {
                EnterpriseBigDataActivity enterpriseBigDataActivity = (EnterpriseBigDataActivity) context;
                EnterpriseBusinessBean.DataBean dataBean = EnterpriseBusinessList.get(position);
                enterpriseViewHolder.industryText.setText(dataBean.getBusinessName());
                DecimalFormat df = new DecimalFormat("0.00");
                enterpriseViewHolder.proportionText.setText(df.format((dataBean.getBusinessNum()/Num)*100)+"%");
                enterpriseViewHolder.numText.setText(dataBean.getBusinessNum()+"");
                GradientDrawable drawable = new GradientDrawable();
                drawable.setCornerRadius(7);
                drawable.setColor(dataBean.getColor());
                enterpriseViewHolder.DataColorView.setBackgroundDrawable(drawable);
                if (enterpriseBigDataActivity.pieChartPosition.equals(dataBean.getBusinessName())) {
                    enterpriseViewHolder.viewLine.setVisibility(View.GONE);
                    enterpriseViewHolder.DataLin.setBackgroundResource(R.drawable.tblr_shape);
                }else{
                    enterpriseViewHolder.viewLine.setVisibility(View.VISIBLE);
                    enterpriseViewHolder.DataLin.setBackgroundResource(0);
                }
            }

        } else if (holder instanceof PurchaseViewHolder) {
            PurchaseViewHolder purchaseViewHolder = (PurchaseViewHolder) holder;
            if (position == 0) {
                purchaseViewHolder.TitleLin.setVisibility(View.VISIBLE);
                purchaseViewHolder.DataLin.setVisibility(View.GONE);
                purchaseViewHolder.viewLine.setVisibility(View.GONE);
            } else {
                purchaseViewHolder.industryText.setText(PurchaseWordCloudList.get(position).getKey_word());
                DecimalFormat df = new DecimalFormat("0.00");
                purchaseViewHolder.proportionText.setText(df.format(PurchaseWordCloudList.get(position).getScore()*100)+"%");
                purchaseViewHolder.numText.setText(PurchaseWordCloudList.get(position).getNumbers()+"");
                GradientDrawable drawable = new GradientDrawable();
                drawable.setCornerRadius(7);
                drawable.setColor(PurchaseWordCloudList.get(position).getColor());
                purchaseViewHolder.DataColorView.setBackground(drawable);
            }
        } else if (holder instanceof PurchasePopwindowViewHolder) {
            PurchasePopwindowViewHolder purchasePopwindowViewHolder = (PurchasePopwindowViewHolder) holder;
            purchasePopwindowViewHolder.recyText.setText(purchasePopwindowList.get(position));
            purchasePopwindowViewHolder.recyText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    purchasePopwindowListnear.Onclick(position);
                }
            });
        }
    }

    private void SelectionData() {
        //查询企业分布
        String app_token = (String) SharedPreferenceUtils.get(context,"app_token","");
        MyApplication.getNetApi().getEnterpriseData(app_token).enqueue(new Callback<EnterpriseDataBean>() {
            @Override
            public void onResponse(Call<EnterpriseDataBean> call, Response<EnterpriseDataBean> response) {
                Log.e("查询", "onResponse: "+response.body().toString() );
                if(response.isSuccessful()){
                    EnterpriseDataBean body = response.body();
                    if(body!=null && body.getStatus()==200){
                        data = body.getData();
                    }
                }
            }

            @Override
            public void onFailure(Call<EnterpriseDataBean> call, Throwable t) {
                Log.e("onFailure", "BigDataRecyclerViewAdapter"+t.getMessage() );
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mode.equals("BigData")) {
            return BigDataList.size();
        } else if (mode.equals("Enterprise")) {
            if(EnterpriseBusinessList.size()>10) {
                return 11;
            }else{
                return EnterpriseBusinessList.size();
            }
        } else if (mode.equals("PurChaseData")) {
            return PurchaseWordCloudList.size();
        } else if (mode.equals("PurchaseDataPopwindow")) {
            return purchasePopwindowList.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        //判断类型
        if (mode.equals("BigData")) {
            return 0;
        } else if (mode.equals("Enterprise")) {
            return 1;
        } else if (mode.equals("PurChaseData")) {
            return 2;
        } else if (mode.equals("PurchaseDataPopwindow")) {
            return 3;
        }
        return -1;
    }

    public class BigDataViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.bigdata_image)
        ImageView bigdataImage;
        @Bind(R.id.bigdata_rel)
        RelativeLayout bigdataRel;

        public BigDataViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class EnterpriseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.Title_lin)
        LinearLayout TitleLin;
        @Bind(R.id.DataColorView)
        View DataColorView;
        @Bind(R.id.industry_text)
        TextView industryText;
        @Bind(R.id.proportion_text)
        TextView proportionText;
        @Bind(R.id.num_text)
        TextView numText;
        @Bind(R.id.Data_lin)
        LinearLayout DataLin;
        @Bind(R.id.viewLine)
        View viewLine;

        public EnterpriseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class PurchaseViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.Title_lin)
        LinearLayout TitleLin;
        @Bind(R.id.DataColorView)
        View DataColorView;
        @Bind(R.id.industry_text)
        TextView industryText;
        @Bind(R.id.proportion_text)
        TextView proportionText;
        @Bind(R.id.num_text)
        TextView numText;
        @Bind(R.id.Data_lin)
        LinearLayout DataLin;
        @Bind(R.id.viewLine)
        View viewLine;

        public PurchaseViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public class PurchasePopwindowViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recy_text)
        TextView recyText;
        public PurchasePopwindowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public interface PurchasePopwindowListnear{
        void Onclick(int position);
    }
    public PurchasePopwindowListnear purchasePopwindowListnear;
    public void  setPurchasePopwindowListnear(PurchasePopwindowListnear purchasePopwindowListnear){
        this.purchasePopwindowListnear=purchasePopwindowListnear;
    }
}
