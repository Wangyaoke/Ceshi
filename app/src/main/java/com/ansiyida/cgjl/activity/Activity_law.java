package com.ansiyida.cgjl.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.util.ActivityCodeUtil;

import butterknife.Bind;
import butterknife.OnClick;

//import com.zaaach.citypicker.CityPickerActivity;

public class Activity_law extends BaseActivity {
	@Bind(R.id.statute_icon_one)
	RadioButton rb1;
	@Bind(R.id.statute_icon_two)
	RadioButton rb2;
	@Bind(R.id.statute_icon_three)
	RadioButton rb3;
	@Bind(R.id.statute_icon_four)
	RadioButton rb4;
	@Bind(R.id.statute_icon_five)
	RadioButton rb5;
	@Bind(R.id.statute_icon_six)
	RadioButton rb6;
	@Bind(R.id.statute_icon_seven)
	RadioButton rb7;
	@Bind(R.id.statute_icon_eight)
	RadioButton rb8;
	@Bind(R.id.statute_icon_nine)
	RadioButton rb9;
	@Bind(R.id.statute_icon_ten)
	RadioButton rb10;
	@Bind(R.id.statute_icon_ele)
	RadioButton rb11;
	@Bind(R.id.statute_icon_twe)
	RadioButton rb12;

	ImageView bt1;//代替首页tab，点击刷新页面
	private RadioGroup rg;
	private RadioButton fame=null;
	private RadioButton fame2=null;
	private RadioButton fame3=null;
    public String option="";
	@SuppressLint("ClickableViewAccessibility")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	protected int getContentView() {
		return R.layout.activity_law;
	}

	@Override
	protected void initView() {
		rg=(RadioGroup) this.findViewById(R.id.icon_mainAcitivity);
		bt1=(ImageView)findViewById(R.id.point_1);
		option="国务院文件";
		bt1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				returnResult(option);
			}
		});

	}

	@Override
	protected void initData() {

	}

	@Override
	protected void httpData() {

	}

	@Override
	protected void setClickListener() {

	}

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
	}
	@OnClick({ R.id.statute_icon_one,R.id.statute_icon_three,R.id.statute_icon_two,R.id.statute_icon_ele,R.id.statute_icon_twe, R.id.statute_icon_four,R.id.statute_icon_five,R.id.statute_icon_six, R.id.statute_icon_seven,R.id.statute_icon_eight,R.id.statute_icon_nine, R.id.statute_icon_ten})//,R.id.iv_1,R.id.rl_menu_fragment1,
	public void menuClick(View view) {
		Intent intent_read;
		switch (view.getId()) {
			case R.id.statute_icon_ele:                   //3.搜索
				option="政策解读";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_twe:                   //3.搜索
				option="其他法规";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_one:                   //3.搜索
				option="国务院文件";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_two:                   //4.地址
				option="地方规章办法";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_three:                   //4.地址
				option="国际法规";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_four:                   //3.搜索
				option="相关法规";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_five:                   //4.地址
				option="军队颁布法规";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_six:                   //4.地址
				option="国家和军队联合颁布法规";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_eight:                   //3.搜索
				option="财政部规章";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_seven:                   //4.地址
				option="其他部委文件";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_nine:                   //4.地址
				option="财政部规范性文件";
				returnResult(option);
				clickTab();
				break;
			case R.id.statute_icon_ten:                   //3.搜索
				option="国家颁布法规";
				returnResult(option);
				clickTab();
				break;

			default:
				break;
		}

	}

	void returnResult(String sdata) {
		Intent result = new Intent();
		result.putExtra("option", sdata);
		setResult(ActivityCodeUtil.OPTION , result);
		finish();
	}


}
