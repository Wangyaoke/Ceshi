package com.ansiyida.cgjl.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.DropBean;
import com.ansiyida.cgjl.bean.dydetail_bean;
import com.ansiyida.cgjl.bean.infoTypeBeanBean;
import com.ansiyida.cgjl.bean.sourceTypeBean;
import com.ansiyida.cgjl.dialog.WheelDialogFragment;
import com.ansiyida.cgjl.dialog.WheelDialogFragment1;
import com.ansiyida.cgjl.tab.DropdownButton;
import com.ansiyida.cgjl.tab.DropdownButton_dy;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dy_add_layout extends BaseActivity
 {

     protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
     }

     @Override
     protected int getContentView() {
         return R.layout.activity_dyadd;
     }

     @Override
     protected void initView() {

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




 }
