package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.ansiyida.cgjl.R;

public class SecretProcurementRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] LineChartOneArray  =new String[]{"公开","秘密" ,"机密"};
    private String mode;
    public void setData(String mode){
        this.mode = mode;
        notifyDataSetChanged();
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(View.inflate(parent.getContext(), R.layout.secretprocurement_recycler_layout,null));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        if(mode.equals("LineChartOne")) {
            return LineChartOneArray.length;
        }else if(mode.equals("LineChartTwo")){
            return 1;
        }else if(mode.equals("LineChartThree")){
            return 1;
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

}
