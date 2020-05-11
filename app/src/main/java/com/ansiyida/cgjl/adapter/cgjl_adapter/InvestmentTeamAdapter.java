package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.cgjl_bean.FinancialServeIntroduceBean;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class InvestmentTeamAdapter extends RecyclerView.Adapter<InvestmentTeamAdapter.ViewHolder> {

    private Context context;
    private List<FinancialServeIntroduceBean.DataBean.FinancialServeModelListsBean> listsBeans = new ArrayList<>();
    public InvestmentTeamAdapter(Context context) {
        this.context = context;
    }

    public void setListsBeans(List<FinancialServeIntroduceBean.DataBean.FinancialServeModelListsBean> listsBeans) {
        this.listsBeans = listsBeans;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.investmentteam_layouy, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.investmentName.setText(listsBeans.get(position).getName());
        holder.investmentTeamIntroduce.setText(listsBeans.get(position).getFigureIntroduce());
        Glide.with(context).load(listsBeans.get(position).getHeadPortrait()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.investmentTeamImage) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.investmentTeamImage.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listsBeans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.investment_team_image)
        ImageView investmentTeamImage;
        @Bind(R.id.investment_name)
        TextView investmentName;
        @Bind(R.id.investment_team_introduce)
        TextView investmentTeamIntroduce;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
