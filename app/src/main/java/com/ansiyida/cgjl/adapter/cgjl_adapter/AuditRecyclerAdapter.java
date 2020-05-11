package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.cgjl_bean.AuditCenterBean;
import com.ansiyida.cgjl.bean.cgjl_bean.CheckRecordDetailBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.view.cgjl_view.VerticalStepView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuditRecyclerAdapter  extends RecyclerView.Adapter<AuditRecyclerAdapter.ViewHolder> {
    private List<AuditCenterBean.DataBean.ListBean> list;
    private Context context;
    private List<VerticalStepView.Item> items = new ArrayList<>();
    private boolean type= false;
    private String mode;
    private CheckRecordDetailBean.DataBean data;
    public AuditRecyclerAdapter(List<AuditCenterBean.DataBean.ListBean> list, Context context,String mode) {
        this.list = list;
        this.context = context;
        this.mode = mode;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.audit_recycler_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) holder.auditcheckbox.getLayoutParams(); //取控件textView当前的布局参数 linearParams.height = 20;// 控件的高强制设成20
        type = false;
        items.clear();
        holder.textView.setText(list.get(position).getContent());
        RequestListener mRequestListener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                Log.e("Glide", "onException: "+e.getMessage() );
                holder.auditheadimg.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                Log.e("Glide", "onException: "+mode+"--"+isFirstResource );
                holder.auditheadimg.setVisibility(View.VISIBLE);
                return false;
            }
        };

        if(mode.equals("product")){
            if(!list.get(position).getImg().equals("")) {
                String[] split = list.get(position).getImg().split(",");
                if(split.length ==0){
                    Glide.with(context).load(list.get(position).getImg()).listener(mRequestListener).centerCrop().into(holder.auditheadimg);
                }else{
                    Glide.with(context).load(split[0]).listener(mRequestListener).centerCrop().into(holder.auditheadimg);
                }

            }else{
                holder.auditheadimg.setVisibility(View.GONE);
            }
        }
        //初始化大小
        linearParams.width = 17;
        linearParams.height= 27;
        holder.auditcheckbox.setBackgroundResource(R.mipmap.audit_right);
        //根据方向改变大小
        holder.audit_checkbox_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(type){
                    type = false;
                    //关闭
                    holder.auditdetailRecy.setVisibility(View.GONE);
                    linearParams.width = 17;
                    linearParams.height= 27;
                    holder.auditcheckbox.setBackgroundResource(R.mipmap.audit_right);
                }else{
                    //展开
                    type = true;
                    holder.auditdetailRecy.setVisibility(View.VISIBLE);
                    linearParams.width = 27;
                    linearParams.height= 17;
                    holder.auditcheckbox.setBackgroundResource(R.mipmap.audit_bottom);
                    http(list.get(position).getId(),holder.auditdetailRecy);
                }
                holder.auditcheckbox.setLayoutParams(linearParams); //使设置好的布局参数应用到控件
            }
        });
   }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView auditheadimg;
        TextView textView;
        ImageView auditcheckbox;
        RecyclerView auditdetailRecy;
        RelativeLayout audit_checkbox_rel;
        public ViewHolder(View itemView) {
            super(itemView);
            auditheadimg = itemView.findViewById(R.id.audit_headimg);
            textView = itemView.findViewById(R.id.audit_title);
            auditcheckbox = itemView.findViewById(R.id.audit_checkbox);
            auditdetailRecy = itemView .findViewById(R.id.audit_detail_recycler);
            audit_checkbox_rel =itemView.findViewById(R.id.audit_checkbox_rel);
        }
    }
    public void http(int id, final RecyclerView recyclerView){
        Call<CheckRecordDetailBean>call = MyApplication.getNetApi().getcheckRecordDetail((String)SharedPreferenceUtils.get(context,"app_token",""),id);
        call.enqueue(new Callback<CheckRecordDetailBean>() {
            @Override
            public void onResponse(Call<CheckRecordDetailBean> call, Response<CheckRecordDetailBean> response) {
                if(response.isSuccessful()){
                    CheckRecordDetailBean body = response.body();
                    if(body!=null){
                        data = body.getData();
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        Log.e("审核页面", "Audit: "+data.toString());
                        AuditDetailAdapter auditDetailAdapter = new AuditDetailAdapter(data,context,mode);
                        recyclerView.setAdapter(auditDetailAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<CheckRecordDetailBean> call, Throwable t) {
                Log.e("AuditRecyclerAdapter", "onFailure: "+t.getMessage() );
            }
        });
    }

}
