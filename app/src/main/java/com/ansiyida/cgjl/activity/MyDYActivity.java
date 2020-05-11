package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.MyDYItemBean;
import com.ansiyida.cgjl.util.AnimationUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.MyLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyDYActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView tv_title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;
    @Bind(R.id.myLinear)
    MyLinearLayout linearLayout;
    private int childCount = 1;
    private ArrayList<String> lists;
    private boolean isAdd = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_my_dy;
    }

    @Override
    protected void initView() {
        tv_title.setText("我的订阅");
        text_putOut.setVisibility(View.VISIBLE);
        lists = new ArrayList<>();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void httpData() {
        Call<MyDYItemBean> call = MyApplication.getNetApi().getMyDYItem((String) SharedPreferenceUtils.get(this, "app_token", ""));
        call.enqueue(new Callback<MyDYItemBean>() {
            @Override
            public void onResponse(Call<MyDYItemBean> call, Response<MyDYItemBean> response) {
                if (response.isSuccessful()) {
                    childCount=1;
                    lists.clear();
                    MyDYItemBean.DataBean data = response.body().getData();
                    if (data != null) {
                        List<MyDYItemBean.DataBean.ListBean> list = data.getList();
                        if (list != null && list.size() > 0) {
                            for (MyDYItemBean.DataBean.ListBean listBean : list) {
                                addTextView(listBean.getJms_name(), listBean.getJms_id(), childCount);
                            }
                        }
                    } else {
                        ToastUtils.showMessage(MyDYActivity.this, response.body().getMessage());
                    }
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<MyDYItemBean> call, Throwable t) {
                call.cancel();
            }
        });


    }

    @Override
    protected void setClickListener() {

    }

    @OnClick({R.id.image_back, R.id.text_putOut, R.id.iv_add})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:                       //1.返回上一级
                this.finish();
                break;
            case R.id.text_putOut:                      //2.编辑按钮
                isAdd = !isAdd;
                if (isAdd) {
                    cancleEdit();
                } else {
                    edit();
                }
                break;
            case R.id.iv_add:                          //3.添加
                initPopuWindow_addDY();
                break;
        }
    }

    private void addTextView(final String str, final int id, final int position) {
        lists.add(str + "," + id);
        TextView tv1 = (TextView) linearLayout.getChildAt(childCount);
        tv1.setText(str);
        tv1.setVisibility(View.VISIBLE);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAdd) {
                    Intent intent=new Intent(MyDYActivity.this, DYDetailsActivity.class);
                    intent.putExtra("js_id",id+"");
                    intent.putExtra("type",1);
                    MyDYActivity.this.startActivity(intent);
                } else {
                    Call<DefaultBean> call=MyApplication.getNetApi().deleteMyDYItem(id+"",(String)SharedPreferenceUtils.get(MyDYActivity.this,"app_token",""));
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                if ("0001".equals(response.body().getStatus())) {
                                    deleteTextView(position);
                                } else {
                                    ToastUtils.showMessage(MyDYActivity.this, response.body().getMessage());
                                }
                            }

                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable t) {
                            call.cancel();
                        }
                    });

                }
            }
        });
        childCount++;
    }

    private void deleteTextView(int position) {
        lists.remove(position - 1);
        childCount--;
        int length = lists.size();
        for (int x = 0; x < 10; x++) {
            TextView textView = (TextView) linearLayout.getChildAt(x + 1);
            if (x < length) {
                textView.setVisibility(View.VISIBLE);
                textView.setText(lists.get(x).split(",")[0]);
                textView.setVisibility(View.VISIBLE);
                final int s = x;
                textView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isAdd) {
                            Intent intent=new Intent(MyDYActivity.this, DYDetailsActivity.class);
                            intent.putExtra("js_id",lists.get(s).split(",")[1] + "");
                            intent.putExtra("type",1);
                            MyDYActivity.this.startActivity(intent);
                        } else {
                            Call<DefaultBean> call=MyApplication.getNetApi().deleteMyDYItem(lists.get(s).split(",")[1] + "",(String)SharedPreferenceUtils.get(MyDYActivity.this,"app_token",""));
                            call.enqueue(new Callback<DefaultBean>() {
                                @Override
                                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                    if (response.isSuccessful()) {
                                        if ("0001".equals(response.body().getStatus())) {
                                            deleteTextView(s + 1);
                                        } else {
                                            ToastUtils.showMessage(MyDYActivity.this, response.body().getMessage());
                                        }
                                    }

                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<DefaultBean> call, Throwable t) {
                                    call.cancel();
                                }
                            });

                        }


                    }
                });
            } else {
                textView.setVisibility(View.GONE);
            }
        }
    }
    private void edit(){
        for (int x = 0; x < 10; x++) {
            TextView textView = (TextView) linearLayout.getChildAt(x + 1);
            Drawable drawable= getResources().getDrawable(R.mipmap.bg_dy_two);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null,null,drawable,null);
        }

    }
    private void cancleEdit(){
        for (int x = 0; x < 10; x++) {
            TextView textView = (TextView) linearLayout.getChildAt(x + 1);
            Drawable drawable= getResources().getDrawable(R.mipmap.bg_dy_two_none);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null,null,drawable,null);
        }
    }
    private PopupWindow popupWindow_addDY;
    private View contentView_addDY;

    public void initPopuWindow_addDY() {
        if (popupWindow_addDY == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_addDY = mLayoutInflater.inflate(R.layout.popwindow_add_dy, null);
            popupWindow_addDY = new PopupWindow(contentView_addDY, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        RelativeLayout relative_out = (RelativeLayout) contentView_addDY.findViewById(R.id.relative_out);
        RelativeLayout relative_in = (RelativeLayout) contentView_addDY.findViewById(R.id.relative_in);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_addDY.dismiss();
            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        final EditText editText = (EditText) contentView_addDY.findViewById(R.id.edit_addDy);
        Button btn_show = (Button) contentView_addDY.findViewById(R.id.btn_show);
        Button btn_add = (Button) contentView_addDY.findViewById(R.id.btn_add);
        btn_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String trim = editText.getText().toString().trim();
                if (!"".equals(trim)) {
                    Intent intent = new Intent(MyDYActivity.this, VisistedActivity.class);
                    intent.putExtra("keyword", trim);
                    startActivity(intent);
                }else {
                    ToastUtils.showMessage(MyDYActivity.this, "请输入内容");
                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String trim = editText.getText().toString().trim();
                if (!"".equals(trim)){
                    if (childCount <= 10) {
                        Call<DefaultBean> call=MyApplication.getNetApi().addDingYue("",trim,(String)SharedPreferenceUtils.get(MyDYActivity.this,"app_token",""));
                        call.enqueue(new Callback<DefaultBean>() {
                            @Override
                            public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                                if (response.isSuccessful()) {
                                    if ("0001".equals(response.body().getStatus())) {
                                        httpData();
                                        editText.setText("");
                                    }else {
                                        ToastUtils.showMessage(MyDYActivity.this, response.body().getMessage());

                                    }
                                }

                                call.cancel();
                            }

                            @Override
                            public void onFailure(Call<DefaultBean> call, Throwable t) {
                                call.cancel();
                            }
                        });
                    } else {
                        ToastUtils.showMessage(MyDYActivity.this, "已经达到最大数量");
                    }
                    popupWindow_addDY.dismiss();
                }else {
                    ToastUtils.showMessage(MyDYActivity.this, "请输入内容");
                }

            }
        });
        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().setAttributes(lp);
        popupWindow_addDY.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_addDY.setOutsideTouchable(true);
        popupWindow_addDY.setFocusable(true);
//        popupWindow1.showAsDropDown(jiuCuo);
        popupWindow_addDY.showAtLocation(contentView_addDY, Gravity.CENTER, 0, 0);
        popupWindow_addDY.update();
        popupWindow_addDY.getContentView().startAnimation(AnimationUtil.createInAnimation(this, 300));
        popupWindow_addDY.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = MyDYActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                MyDYActivity.this.getWindow().setAttributes(lp);
            }
        });
    }
}
