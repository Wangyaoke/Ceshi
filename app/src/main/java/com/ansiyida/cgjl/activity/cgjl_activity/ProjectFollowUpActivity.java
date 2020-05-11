package com.ansiyida.cgjl.activity.cgjl_activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.ProjectFollowUpRecyclerViewAdapyer;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.ProjectListBean;
import com.ansiyida.cgjl.bean.cgjl_bean.ProjectTailAfterBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectFollowUpActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.del_image)
    ImageView delImage;
    @Bind(R.id.ok_text)
    TextView okText;
    @Bind(R.id.xRecyclerView)
    XRefreshView XRefreshView;
    @Bind(R.id.All_Election)
    CheckBox AllElection;
    @Bind(R.id.btn_del)
    Button btnDel;
    @Bind(R.id.del_rel)
    RelativeLayout delRel;
    @Bind(R.id.recyclerview)
    RecyclerView recyclerview;
    @Bind(R.id.no_data_rel)
    RelativeLayout noDataRel;
    private ProjectFollowUpRecyclerViewAdapyer projectFollowUpRecyclerViewAdapyer;
    private List<ProjectListBean.DataBean> ProjectList = new ArrayList<>();
    private String app_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(ProjectFollowUpActivity.this, "Android项目跟进", "ProjectFollowUpActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_project_follow_up;
    }

    @Override
    protected void initView() {
        projectFollowUpRecyclerViewAdapyer = new ProjectFollowUpRecyclerViewAdapyer();
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(projectFollowUpRecyclerViewAdapyer);
        XRefreshView.setPullRefreshEnable(false);
        XRefreshView.setPullLoadEnable(false);
    }

    @Override
    protected void initData() {
        app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //准备删除数据
        delImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ProjectList.size()>0) {
                    delImage.setVisibility(View.GONE);
                    okText.setVisibility(View.VISIBLE);
                    delRel.setVisibility(View.VISIBLE);
                    AllElection.setChecked(false);
                    projectFollowUpRecyclerViewAdapyer.setChecked(true, false,ProjectList);
                }else{
                    ToastUtils.showMessage(ProjectFollowUpActivity.this,"无数据可删除");
                }
            }
        });
        //完成
        okText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delImage.setVisibility(View.VISIBLE);
                okText.setVisibility(View.GONE);
                delRel.setVisibility(View.GONE);
                projectFollowUpRecyclerViewAdapyer.setChecked(false, false,ProjectList);
            }
        });

        //点击全选按钮
        AllElection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                projectFollowUpRecyclerViewAdapyer.setChecked(true, AllElection.isChecked(),ProjectList);
            }
        });

        //监听全选按钮的状态
        projectFollowUpRecyclerViewAdapyer.setProjectFollowListnear(new ProjectFollowUpRecyclerViewAdapyer.ProjectFollowListnear() {
            @Override
            public void Allcheck() {
                AllElection.setChecked(true);
            }
            @Override
            public void NoAllCheck() {
                AllElection.setChecked(false);
            }
        });

        //点击按钮获取删除的数据集合
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
                backgroundAlpha(ProjectFollowUpActivity.this, (float) 0.5);
            }
        });
    }

    private void HttpDeleteList(String deleteList) {
        Log.e("删除id", "HttpDeleteList: "+deleteList );
        Call<ProjectTailAfterBean> projectDelete = MyApplication.getNetApi().getProjectDelete(app_token, deleteList);
        projectDelete.enqueue(new Callback<ProjectTailAfterBean>() {
            @Override
            public void onResponse(Call<ProjectTailAfterBean> call, Response<ProjectTailAfterBean> response) {
                if (response.isSuccessful()) {
                    ProjectTailAfterBean body = response.body();
                    Log.e("删除回调", "onResponse: " + body.getData());
                    if (body.getData().contains("删除成功")) {
                        projectFollowUpRecyclerViewAdapyer.notifyDataSetChanged();
                        projectFollowUpRecyclerViewAdapyer.jlList.clear();
                    } else {
                        ToastUtils.showMessage(ProjectFollowUpActivity.this, body.getData());
                    }
                    getData();

                }
            }
            @Override
            public void onFailure(Call<ProjectTailAfterBean> call, Throwable t) {
                Log.e("删除失败", "onResponse: " + t.getMessage());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }
    private void getData(){
        Call<ProjectListBean> projectListCall = MyApplication.getNetApi().getProjectList(app_token);
        projectListCall.enqueue(new Callback<ProjectListBean>() {
            @Override
            public void onResponse(Call<ProjectListBean> call, Response<ProjectListBean> response) {
                ProjectList.clear();
                if (response.isSuccessful()) {
                    ProjectList.addAll(response.body().getData());
                    if (ProjectList.size() == 0) {
                        AllElection.setChecked(false);
                        delRel.setVisibility(View.GONE);
                        okText.setVisibility(View.GONE);
                        delImage.setVisibility(View.VISIBLE);
                        noDataRel.setVisibility(View.VISIBLE);
                    }else{
                        noDataRel.setVisibility(View.GONE);
                    }
                    if(delImage.getVisibility()==View.VISIBLE) {
                        projectFollowUpRecyclerViewAdapyer.setChecked(false, AllElection.isChecked(), ProjectList);
                    }else{
                        projectFollowUpRecyclerViewAdapyer.setChecked(true, AllElection.isChecked(), ProjectList);
                    }
                }
            }

            @Override
            public void onFailure(Call<ProjectListBean> call, Throwable t) {

            }
        });
    }

    private void deleteData() {
        View view = View.inflate(this,R.layout.delete_alert_layout,null);
        final PopupWindow popupWindow = new PopupWindow(view,this.getWindowManager().getDefaultDisplay().getWidth()/4*3, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        TextView title = view.findViewById(R.id.delete_title);
        Button dismiss =view.findViewById(R.id.dismiss_btn);
        Button sure =view.findViewById(R.id.sure_btn);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

        title.setText("确定删除跟进数据吗？");
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> delList = projectFollowUpRecyclerViewAdapyer.getDelList();
               String datastr="";
                if (delList.size() == 0) {
                    ToastUtils.showMessage(ProjectFollowUpActivity.this, "请选择要删除的数据");
                } else {
                    for (int i = 0; i < delList.size(); i++) {
                        String id = String.valueOf(ProjectList.get(i).getId());
                        if(i==0){
                            datastr = id;
                        }else{
                            datastr = datastr+","+id;
                        }
                    }
                    //请求接口删除
                    HttpDeleteList(datastr);
                }
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(ProjectFollowUpActivity.this,1);
            }
        });
    }
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
}
