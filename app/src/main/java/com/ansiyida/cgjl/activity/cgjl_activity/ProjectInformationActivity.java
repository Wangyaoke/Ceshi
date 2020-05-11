package com.ansiyida.cgjl.activity.cgjl_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.ProjectInformationAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.GetProjectBean;
import com.ansiyida.cgjl.bean.cgjl_bean.ProjectTailAfterBean;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectInformationActivity extends BaseActivity {

    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView Title;
    @Bind(R.id.textTitle)
    TextView textTitle;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.FollowUp)
    ImageView FollowUp;

    private boolean JudgeCP = false;
    private ProjectInformationAdapter projectInformationAdapter;
    private List<GetProjectBean.DataBean> list = new ArrayList<>();
    private String title;
    private String source;
    private String app_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        GoogleAssistant(ProjectInformationActivity.this, "Android项目跟进详情", "ProjectInformationActivity");
    }
    @Override
    protected int getContentView() {
        return R.layout.activity_project_information;
    }

    @Override
    protected void initView() {
        projectInformationAdapter = new ProjectInformationAdapter(this,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(projectInformationAdapter);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        source = intent.getStringExtra("source");
        Log.e("项目跟进页面", "initData: "+ title +"++++++"+ source);
        Title.setText("项目信息");
        textTitle.setText(title);
    }

    @Override
    protected void httpData() {
        try {
            app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
            Call<GetProjectBean> getProjectCall = MyApplication.getNetApi().getProject(app_token, source, title);
            getProjectCall.enqueue(new Callback<GetProjectBean>() {
                @Override
                public void onResponse(Call<GetProjectBean> call, Response<GetProjectBean> response) {
                    list.clear();
                    if (response.isSuccessful()) {
                        GetProjectBean body = response.body();
                        List<GetProjectBean.DataBean> data = body.getData();
                        if(data!=null) {
                            list.addAll(body.getData());
                        }
                        list.remove(list.size()-1);
                        Log.e("信息", "onResponse: "+list.toString() );
                        projectInformationAdapter.notifyDataSetChanged();
                        JudgeCP = data.get(data.size() - 1).isFocus();
                        if (JudgeCP) {
                            FollowUp.setImageResource(R.mipmap.button_open);
                        } else {
                            FollowUp.setImageResource(R.mipmap.button_close);
                        }
                    }
                }
                @Override
                public void onFailure(Call<GetProjectBean> call, Throwable t) {

                }
            });
        }catch (Exception e){
            Log.e("项目跟进页面", "httpData: "+e.getMessage() );
        }
    }

    @Override
    protected void setClickListener() {
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        FollowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ("true".equals(SharedPreferenceUtils.get(ProjectInformationActivity.this, "vistor", ""))) {
                    changeType();
                }else{
                    ToastUtils.showMessage(ProjectInformationActivity.this, "请先登录");
                }
            }
        });
    }
    private void changeType(){
        //是否跟进项目状态
        Call<ProjectTailAfterBean> projectTailAfter = MyApplication.getNetApi().getProjectTailAfter(app_token, source, title);
        projectTailAfter.enqueue(new Callback<ProjectTailAfterBean>() {
            @Override
            public void onResponse(Call<ProjectTailAfterBean> call, Response<ProjectTailAfterBean> response) {
                if(response.isSuccessful()){
                    ProjectTailAfterBean body = response.body();
                    if(body.getData().contains("成功")){
                        JudgeCP=!JudgeCP;
                        if(JudgeCP){
                            FollowUp.setImageResource(R.mipmap.button_open);
                        }else{
                            FollowUp.setImageResource(R.mipmap.button_close);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ProjectTailAfterBean> call, Throwable t) {
                Log.e("项目跟进页面", "httpData: "+t.getMessage() );
            }
        });
    }

}
