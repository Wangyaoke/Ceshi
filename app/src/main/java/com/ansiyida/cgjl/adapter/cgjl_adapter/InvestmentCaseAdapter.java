package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ansiyida.cgjl.R;
import com.bumptech.glide.Glide;

import butterknife.ButterKnife;

public class InvestmentCaseAdapter extends RecyclerView.Adapter<InvestmentCaseAdapter.ViewHolder> {
    private String [] imageArray = new String[]{};
    private Context context;

    public InvestmentCaseAdapter(Context context) {
        this.context = context;
    }

    public void setImageArray(String[] imageArray) {
        this.imageArray = imageArray;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.investmentcase_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(imageArray[position]).into(holder.investmentCaseImage);
    }

    @Override
    public int getItemCount() {
        return imageArray.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView investmentCaseImage;
        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            investmentCaseImage = itemView.findViewById(R.id.investment_case_IMAGE);
        }
    }
}
