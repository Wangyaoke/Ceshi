package com.ansiyida.cgjl.city;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.ClassifiedActivity;
import com.ansiyida.cgjl.activity.ClassifiedActivity1;
import com.ansiyida.cgjl.activity.bidding_class_activity1;
import com.ansiyida.cgjl.adapter.cgjl_adapter.MenuAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.MenuBean;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.zaaach.citypicker.CityPickerActivity;

public class provinceList extends BaseActivity {
    @Bind(R.id.icon1_mainAcitivity)
    RadioButton rb1;
    @Bind(R.id.icon2_mainAcitivity)
    RadioButton rb2;
    @Bind(R.id.icon3_mainAcitivity)
    RadioButton rb3;
    @Bind(R.id.icon4_mainAcitivity)
    RadioButton rb4;
    @Bind(R.id.icon44_mainAcitivity)
    RadioButton rb5;
    @Bind(R.id.icon5_mainAcitivity)
    RadioButton rb6;
    @Bind(R.id.icon6_mainAcitivity)
    RadioButton rb7;
    @Bind(R.id.icon7_mainAcitivity)
    RadioButton rb8;
    @Bind(R.id.icon8_mainAcitivity)
    RadioButton rb9;
    @Bind(R.id.icon9_mainAcitivity)
    RadioButton rb10;
    @Bind(R.id.icon10_mainAcitivity)
    RadioButton rb11;
    @Bind(R.id.icon11_mainAcitivity)
    RadioButton rb12;
    @Bind(R.id.icon12_mainAcitivity)
    RadioButton rb13;
    @Bind(R.id.icon13_mainAcitivity)
    RadioButton rb14;

    ImageView bt1;//代替首页tab，点击刷新页面
    @Bind(R.id.icon16_mainAcitivity)
    RadioButton rb16;
    @Bind(R.id.icon17_mainAcitivity)
    RadioButton rb17;
    @Bind(R.id.type_Recycler)
    RecyclerView typeRecycler;
    private RadioGroup rg;
    private RadioButton fame = null;
    private RadioButton fame2 = null;
    private RadioButton fame3 = null;
    public String option = "";
    private List<MenuBean.DataBean> MenuList = new ArrayList<>();
    private MenuAdapter menuAdapter;

    @SuppressLint("ClickableViewAccessibility")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //	super.onCreate(savedInstanceState);
        //	setContentView(R.layout.activity_provincelist);
        GoogleAssistant(provinceList.this,"Android其他公告","provinceList");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_provincelist;
    }

    @Override
    protected void initView() {

        rg = (RadioGroup) this.findViewById(R.id.icon_mainAcitivity);
        bt1 = (ImageView) findViewById(R.id.point_1);
        menuAdapter = new MenuAdapter(this,MenuList);
        typeRecycler.setLayoutManager(new GridLayoutManager(this,4));
        typeRecycler.setAdapter(menuAdapter);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {
        if(NetWorkUtils.isNetworkConnected(this)){
            try {
                String app_token = (String) SharedPreferenceUtils.get(this, "app_token", "");
                Call<MenuBean> menuCall = MyApplication.getNetApi().getMenu(app_token, "");
                menuCall.enqueue(new Callback<MenuBean>() {
                    @Override
                    public void onResponse(Call<MenuBean> call, Response<MenuBean> response) {
                        if (response.isSuccessful()) {
                            List<MenuBean.DataBean> body = response.body().getData();
                            if (body.size() > 0) {
                                MenuList.addAll(body);
                                menuAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<MenuBean> call, Throwable t) {

                    }
                });
            }catch (Exception e){
                Log.e("Exception", "provinceList:"+e.getMessage() );
            }
        }else{
            ToastUtils.showMessage(this,"请检查您的网络状况！");
        }
    }

    @Override
    protected void setClickListener() {
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                option = "ll";
                returnResult(option);
            }
        });
	/*	rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {


				switch (checkedId) {

					case R.id.rb1_mainAcitivity:

						break;
					case R.id.rb2_mainAcitivity:

						break;
					case R.id.rb3_mainAcitivity:


						break;
					case R.id.rbserch_mainAcitivity:

					case R.id.rb5_mainAcitivity:

						break;

					default:
						break;

				}

			}
		});*/
    }

    /*private class getItem implements RadioGroup.OnCheckedChangeListener{
        //这个接口是radioGroup专用的事件响应接口
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            //参数group是控件的id  checkedId是控件中的每一项的id
            // TODO Auto-generated method stub
            //Toast.makeText(MainActivityRadio.this, checkedId+"被击中了"+R.id.famale, Toast.LENGTH_SHORT).show();
            switch (checkedId)

            {
                case R.id.icon1_mainAcitivity:
                option="1";
            returnResult(option);
                    break;
                case R.id.icon2_mainAcitivity:
                    option="1";
                    returnResult(option);
                    break;
                case R.id.icon3_mainAcitivity:
                    option="1";
                    returnResult(option);
                    break;
                case R.id.icon4_mainAcitivity:
                    option="1";
                    returnResult(option);
                    break;
                default:
                    break;
            }

        }
    }*/
    private void clickTab() {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
        rb5.setChecked(false);
        rb6.setChecked(false);
        rb7.setChecked(false);
        rb8.setChecked(false);
        rb9.setChecked(false);
        rb10.setChecked(false);
        rb11.setChecked(false);
        rb12.setChecked(false);
        rb13.setChecked(false);
        rb14.setChecked(false);
        rb16.setChecked(false);
        rb17.setChecked(false);

    }

    @OnClick({R.id.icon16_mainAcitivity, R.id.icon17_mainAcitivity, R.id.icon1_mainAcitivity, R.id.icon2_mainAcitivity, R.id.icon3_mainAcitivity, R.id.icon4_mainAcitivity, R.id.icon44_mainAcitivity, R.id.icon5_mainAcitivity, R.id.icon6_mainAcitivity, R.id.icon7_mainAcitivity, R.id.icon8_mainAcitivity, R.id.icon9_mainAcitivity, R.id.icon10_mainAcitivity, R.id.icon11_mainAcitivity, R.id.icon12_mainAcitivity, R.id.icon13_mainAcitivity})
//,R.id.iv_1,R.id.rl_menu_fragment1,
    public void menuClick(View view) {
        Intent intent_read;
        switch (view.getId()) {
            case R.id.icon1_mainAcitivity:                   //3.搜索
                intent_read = new Intent(this, ClassifiedActivity1.class);
                intent_read.putExtra("title", "涉密采购");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon2_mainAcitivity:                   //4.地址

                intent_read = new Intent(this, ClassifiedActivity.class);
                intent_read.putExtra("title", "采购需求");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon3_mainAcitivity:                   //4.地址
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "成交公告");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon4_mainAcitivity:                   //3.搜索
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "单一来源公示");

                startActivityForResult(intent_read, 1);
                clickTab();

                break;
            case R.id.icon44_mainAcitivity:                   //4.地址

                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "中标公告");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon5_mainAcitivity:                   //4.地址
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "更正公告");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon6_mainAcitivity:                   //3.搜索
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "公开招标");

                startActivityForResult(intent_read, 1);
                clickTab();

                break;
            case R.id.icon7_mainAcitivity:                   //4.地址

                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "询价公告");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon8_mainAcitivity:                   //4.地址
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "竞争性谈判");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon9_mainAcitivity:                   //3.搜索
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "资格预审");

                startActivityForResult(intent_read, 1);
                clickTab();

                break;
            case R.id.icon10_mainAcitivity:                   //4.地址

                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "邀请公告");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon11_mainAcitivity:                   //4.地址
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "竞争性磋商");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon12_mainAcitivity:                   //3.搜索
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "废标流标");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon14_mainAcitivity:                   //4.地址
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "涉密采购");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon15_mainAcitivity:                   //3.搜索
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "采购需求");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon13_mainAcitivity:                   //4.地址

                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "其他公告");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon16_mainAcitivity:                   //3.搜索
                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "招标公告");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            case R.id.icon17_mainAcitivity:                   //4.地址

                intent_read = new Intent(this, bidding_class_activity1.class);
                intent_read.putExtra("title", "采购公告");

                startActivityForResult(intent_read, 1);
                clickTab();
                break;
            default:
                break;
        }

    }

    void returnResult(String sdata) {
        Intent result = new Intent();
        result.putExtra("option", sdata);
        setResult(ActivityCodeUtil.OPTION, result);
        finish();
    }


}
