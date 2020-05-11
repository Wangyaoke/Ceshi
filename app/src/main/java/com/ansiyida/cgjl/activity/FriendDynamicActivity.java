package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.Fragment1ViewPagerAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.FriendInfoBean;
import com.ansiyida.cgjl.fragment.FriendDynamicFragment;
import com.ansiyida.cgjl.fragment.FriendYanTaoFragment;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.glide_circle_photo.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendDynamicActivity extends BaseActivity {
    @Bind(R.id.viewPager_friendDynamicActivity)
    ViewPager viewPager;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.rb1_friendDynamicActivity)
    RadioButton rb1;
    @Bind(R.id.rb2_friendDynamicActivity)
    RadioButton rb2;
    @Bind(R.id.iv_touXiang)
    ImageView iv_touXiang;
    @Bind(R.id.iv_care)
    ImageView iv_care;
    @Bind(R.id.tv_userName)
    TextView tv_userName;
    @Bind(R.id.tv_talk)
    TextView tv_talk;
    @Bind(R.id.tv_guanZhuCount)
    TextView tv_guanZhuCount;
    @Bind(R.id.tv_fansCount)
    TextView tv_fansCount;
    private ArrayList<Fragment> list;
    private String jm_id;
    private boolean isCare=false;
    private String userImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_friend_dynamic;
    }

    @Override
    protected void initView() {
        Intent intent = getIntent();
        jm_id = intent.getStringExtra("jm_id");
        if (jm_id == null || "".equals(jm_id)) {
            jm_id = "1";
        }
        list = new ArrayList<>();
        //动态
        FriendDynamicFragment dynamicFragment = new FriendDynamicFragment();
        Bundle bundle = new Bundle();
        bundle.putString("id", jm_id);
        dynamicFragment.setArguments(bundle);
        list.add(dynamicFragment);
        //研讨
        FriendYanTaoFragment yanTaoFragment = new FriendYanTaoFragment();
        Bundle bundle2 = new Bundle();
        bundle2.putString("id", jm_id);
        yanTaoFragment.setArguments(bundle2);
        list.add(yanTaoFragment);
        viewPager.setAdapter(new Fragment1ViewPagerAdapter(getSupportFragmentManager(), list));
        rb1.setChecked(true);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {
        Call<FriendInfoBean> call = MyApplication.getNetApi().friendInfo((String) SharedPreferenceUtils.get(this, "app_token", ""), jm_id);
        call.enqueue(new Callback<FriendInfoBean>() {
            @Override
            public void onResponse(Call<FriendInfoBean> call, Response<FriendInfoBean> response) {
                if (response.isSuccessful()) {
                    FriendInfoBean body = response.body();
                    if ("0001".equals(body.getStatus())) {
                        FriendInfoBean.DataBean data = body.getData();
                        userImg = data.getUserImg();
                        if (userImg == null) {
                            userImg = "";
                        }
                        Glide.with(FriendDynamicActivity.this).load(userImg).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(FriendDynamicActivity.this)).into(iv_touXiang);
                       LogUtil.i("isLike","islike:"+data.isIsLike());
                        if (data.isIsLike()!=null&&!"".equals(data.isIsLike())&&(boolean)data.isIsLike()) {
                            iv_care.setImageResource(R.mipmap.icon_guanzhu_yes);
                            isCare=true;
                        } else {
                            iv_care.setImageResource(R.mipmap.icon_guanzhu_none);
                            isCare=false;
                        }
                        tv_userName.setText(data.getUserName());
                        tv_talk.setText(data.getUserDes());
                        tv_guanZhuCount.setText(data.getLikeNum() + "");
                        tv_fansCount.setText(data.getFansNum() + "");
                    } else {
                        ToastUtils.showMessage(FriendDynamicActivity.this, body.getMessage());
                    }


                }

                call.cancel();
            }

            @Override
            public void onFailure(Call<FriendInfoBean> call, Throwable t) {
                call.cancel();
                ToastUtils.showMessage(FriendDynamicActivity.this, "未知错误");
                LogUtil.i("error","erroId:"+jm_id);
            }
        });
    }

    @Override
    protected void setClickListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    rb1.setChecked(true);
                } else {
                    rb2.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rb1_friendDynamicActivity) {
                    viewPager.setCurrentItem(0);
                } else if (checkedId == R.id.rb2_friendDynamicActivity) {
                    viewPager.setCurrentItem(1);
                }
            }
        });
    }

    @OnClick({R.id.image_back,R.id.iv_care,R.id.iv_touXiang})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:               //1.返回上一级
                this.finish();
                break;
            case R.id.iv_care:                  //2.关注按钮
                if (!isCare) {
                    Call<DefaultBean2> call = MyApplication.getNetApi().guanZhu(jm_id + "", "F", "P", (String) SharedPreferenceUtils.get(this,"app_token",""));
                    call.enqueue(new Callback<DefaultBean2>() {
                        @Override
                        public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                            if (response.isSuccessful()) {
                                DefaultBean2 body = response.body();
                                if ("200".equals( response.body().getStatus())) {
                                    iv_care.setImageResource(R.mipmap.icon_guanzhu_yes);
                                    isCare=true;
                                }else {
                                    ToastUtils.showMessage(FriendDynamicActivity.this, body.getMsg());
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultBean2> call, Throwable t) {
                            ToastUtils.showMessage(FriendDynamicActivity.this, "解析失败");
                        }
                    });

                } else {
                    Call<DefaultBean2> call = MyApplication.getNetApi().guanZhu(jm_id+"", "G", "P", (String) SharedPreferenceUtils.get(this,"app_token",""));
                    call.enqueue(new Callback<DefaultBean2>() {
                        @Override
                        public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                            if (response.isSuccessful()) {
                                DefaultBean2 body = response.body();
                                if ("200".equals( response.body().getStatus())) {
                                    ToastUtils.showMessage(FriendDynamicActivity.this, "取消成功");
                                    iv_care.setImageResource(R.mipmap.icon_guanzhu_none);
                                    isCare=false;
                                }else {
                                    ToastUtils.showMessage(FriendDynamicActivity.this, "取消失败");
                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultBean2> call, Throwable t) {
                            ToastUtils.showMessage(FriendDynamicActivity.this, "解析失败");

                        }
                    });

                }

                break;
            case R.id.iv_touXiang:          //3.点击头像
                Intent intent = new Intent(this, PhotoBigActivity.class);
                intent.putExtra("picString", userImg);
                intent.putExtra("fromPosition", 0);
                this.startActivity(intent);

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.clear();
            list = null;
        }
    }
}
