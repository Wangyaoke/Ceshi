package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.EnterprisesRecommendoneselfActivity;
import com.ansiyida.cgjl.activity.recom_self_activity;
import com.ansiyida.cgjl.bean.cgjl_bean.CheckRecordDetailBean;
import com.ansiyida.cgjl.view.cgjl_view.VerticalProgress;
import com.ansiyida.cgjl.view.cgjl_view.VerticalStepView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuditDetailAdapter extends RecyclerView.Adapter<AuditDetailAdapter.ViewHolder> {

    private CheckRecordDetailBean.DataBean checkRecordDetailBean;
    private Context context;
    private String mode;
    private List<VerticalStepView.Item> items = new ArrayList<>();

    public AuditDetailAdapter(CheckRecordDetailBean.DataBean checkRecordDetailBean, Context context, String mode) {
        this.checkRecordDetailBean = checkRecordDetailBean;
        this.context = context;
        this.mode = mode;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.audit_detail_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final List<CheckRecordDetailBean.DataBean.CheckRecordBean> checkRecord = checkRecordDetailBean.getCheckRecord();
        if(checkRecord.size()>0){
            holder.verticalProgress.setCurrentPoint(checkRecord.size());
        }
        holder.verticalProgress.setMaxPointCount(checkRecord.size());
        holder.auditRestart.setOnClickListener(new View.OnClickListener() {
            private Intent intent;
            @Override
            public void onClick(View view) {
                if (mode.equals("company")) {
                    intent = new Intent(context, EnterprisesRecommendoneselfActivity.class);
                } else if (mode.equals("product")) {
                    intent = new Intent(context, recom_self_activity.class);
                }
                context.startActivity(intent);
            }
        });
        holder.auditFailure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popwindow(checkRecord.get(2).getContent());
            }
        });

        if(checkRecord.size() == 3){
            if(checkRecord.get(2).getTitle().equals("通过")){
                holder.AuditResult.setText("审核成功");
            }else if(checkRecord.get(2).getTitle().equals("未通过")){
                //失败
                holder.AuditResult.setText("审核失败");
                holder.auditRestart.setVisibility(View.VISIBLE);
                holder.auditFailure.setVisibility(View.VISIBLE);
            }
        }
        for (int i = 0; i < checkRecord.size(); i++) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //如果它本来就是long类型的,则不用写这一步
            long lt = new Long(checkRecord.get(i).getCreateTime());
            Date date = new Date(lt);
            if(checkRecord.size()==2){
                if (i == 0) {
                    holder.AduitSubmissionTime.setText(simpleDateFormat.format(date));
                } else if (i == 1) {
                    holder.AuditResult.setText("正在审核");
                    holder.ZZAudit.setText("");
                    holder.ZZAuditTime.setText("");
                    holder.auditResultTime.setText(simpleDateFormat.format(date));
                }
            }else if (checkRecord.size() ==3){
                if (i == 0) {
                    holder.AduitSubmissionTime.setText(simpleDateFormat.format(date));
                } else if (i == 1) {
                    holder.ZZAuditTime.setText(simpleDateFormat.format(date));
                } else {
                    holder.auditResultTime.setText(simpleDateFormat.format(date));
                }
            }
        }
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        VerticalProgress verticalProgress;
        TextView AduitSubmission;
        TextView AduitSubmissionTime;
        TextView ZZAudit;
        TextView ZZAuditTime;
        TextView AuditResult;
        TextView auditFailure;
        TextView auditRestart;
        TextView auditResultTime;
        public ViewHolder(View itemView) {
            super(itemView);
            verticalProgress  = itemView.findViewById(R.id.verticalProgress);
            AduitSubmission = itemView.findViewById(R.id.Aduit_Submission);
            AduitSubmissionTime = itemView.findViewById(R.id.Aduit_Submission_time);
            ZZAudit = itemView.findViewById(R.id.ZZ_Audit);
            ZZAuditTime = itemView.findViewById(R.id.ZZ_Audit_time);
            AuditResult = itemView.findViewById(R.id.AuditResult);
            auditResultTime = itemView.findViewById(R.id.auditResult_time);
            auditFailure = itemView.findViewById(R.id.audit_failure);
            auditRestart = itemView.findViewById(R.id.audit_restart);
        }
    }
    public void popwindow(String content){
        View view = View.inflate(context,R.layout.auditalert,null);
        DisplayMetrics dm =context.getResources().getDisplayMetrics();

        double  width = (double) dm.widthPixels/1.5;
        final PopupWindow popupWindow  = new PopupWindow(view, (int) width, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setOutsideTouchable(true);
        //关闭事件
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha((Activity) context,(float)1);
            }
        });
        TextView Alert_Reason = view.findViewById(R.id.Alert_Reason);
        Alert_Reason.setText(content);
        Button Cancel_btn = view.findViewById(R.id.Cancel_btn);
        Button Determine_btn = view.findViewById(R.id.Determine_btn);
       Cancel_btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               popupWindow.dismiss();
               backgroundAlpha((Activity) context,(float) 1);
           }
       });
        Determine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                backgroundAlpha((Activity) context,(float) 1);
            }
        });
        backgroundAlpha((Activity) context,(float) 0.5);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);
    }
    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
