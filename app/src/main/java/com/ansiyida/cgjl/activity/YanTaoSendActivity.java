package com.ansiyida.cgjl.activity;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.YantaoTypeAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DefaultBean5;
import com.ansiyida.cgjl.bean.SendYanTaoTypeBean;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YanTaoSendActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.tab1)
    TextView tab1;
    @Bind(R.id.tab2)
    TextView tab2;
    @Bind(R.id.tab3)
    TextView tab3;
    @Bind(R.id.tab4)
    TextView tab4;
    @Bind(R.id.tab5)
    TextView tab5;

    @Bind(R.id.editText_title_yanTaoSend)
    EditText editText_title;
    @Bind(R.id.editText_des_yanTaoSend)
    EditText editText_des;
    @Bind(R.id.tv_editCount)
    TextView tv_editCount;
    @Bind(R.id.tv_titleCount)
    TextView tv_titleCount;
    @Bind(R.id.tv_type)
    TextView tv_type;

    @Bind(R.id.iv_point)
    ImageView iv_point;
    @Bind(R.id.relative_one)
    RelativeLayout relative_one;

    private ArrayList<String> tabList;
    private ArrayList<String> tabStringList;
    private ArrayList<Integer> tabIndexList;
    private int clickId=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_yan_tao_send;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        title.setText("提问");
        tabList = new ArrayList<>();
        tabStringList=new ArrayList<>();
        tabIndexList=new ArrayList<>();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        editText_des.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String str=s.toString();
                LogUtil.i("length","length:"+str.getBytes().length);
                int lenth=s.toString().length();
                tv_editCount.setText(lenth+"");
            }
        });
        editText_title.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                String str=s.toString();
                LogUtil.i("length","length:"+str.getBytes().length);
                int lenth=s.toString().length();
                tv_titleCount.setText(lenth+"");
            }
        });
    }

    @OnClick({R.id.image_back, R.id.text_edit,R.id.relative_one,R.id.btn_send})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:                    //1.返回上一级
                this.finish();

                break;
            case R.id.text_edit:                    //2.编辑标签

                initPopuWindow_report();
                break;
            case R.id.relative_one:                 //3.选择分类
                initPopuWindow_type();
                break;
            case R.id.btn_send:                     //4.提交
                if (isSend()){
                    LogUtil.i("ssdd","clickeId:"+clickId+",title:"+EditTextUtil.getEditTextStr(editText_title)+",des:"+EditTextUtil.getEditTextStr(editText_des)+",tab:"+getTabList());
                    Call<DefaultBean5> call=MyApplication.getNetApi().sendYanTao((String) SharedPreferenceUtils.get(this,"app_token",""),clickId+"",EditTextUtil.getEditTextStr(editText_title),EditTextUtil.getEditTextStr(editText_des),getTabList());
                    call.enqueue(new Callback<DefaultBean5>() {
                        @Override
                        public void onResponse(Call<DefaultBean5> call, Response<DefaultBean5> response) {

                            if (response.isSuccessful()){
                                String status = response.body().getStatus();
                                if ("0001".equals(status)){
                                    ToastUtils.showMessage(YanTaoSendActivity.this,"发表成功");
                                    YanTaoSendActivity.this.finish();
                                }else {
                                    ToastUtils.showMessage(YanTaoSendActivity.this,response.body().getMessage());
                                }
                            }else {
                                ToastUtils.showMessage(YanTaoSendActivity.this,"解析错误");

                            }
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<DefaultBean5> call, Throwable t) {
                            ToastUtils.showMessage(YanTaoSendActivity.this,"解析错误");
                            call.cancel();

                        }
                    });
                }
                break;
        }
    }

    /**
     * 编辑研讨标签
     */
    private PopupWindow popupWindow_sendYanTao;
    private View contentView_sendYanTao;
    private int editIndex = 1;
    private EditText edit_1, edit_2, edit_3, edit_4, edit_5;
    private TextView tv_addTable, tv_sure, tv_cancel;

    public void initPopuWindow_report() {
        if (popupWindow_sendYanTao == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_sendYanTao = mLayoutInflater.inflate(R.layout.popwindow_table, null);
            popupWindow_sendYanTao = new PopupWindow(contentView_sendYanTao, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relative_out= (RelativeLayout) contentView_sendYanTao.findViewById(R.id.relative_out);
        RelativeLayout relative_in= (RelativeLayout) contentView_sendYanTao.findViewById(R.id.relative_in);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow_sendYanTao.dismiss();
            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        if (edit_1 == null) {
            LogUtil.i("popwindow", "popwindow==null");
            edit_1 = (EditText) contentView_sendYanTao.findViewById(R.id.edit_1);
            edit_2 = (EditText) contentView_sendYanTao.findViewById(R.id.edit_2);
            edit_3 = (EditText) contentView_sendYanTao.findViewById(R.id.edit_3);
            edit_4 = (EditText) contentView_sendYanTao.findViewById(R.id.edit_4);
            edit_5 = (EditText) contentView_sendYanTao.findViewById(R.id.edit_5);
            tv_addTable = (TextView) contentView_sendYanTao.findViewById(R.id.tv_addTable);
            tv_sure = (TextView) contentView_sendYanTao.findViewById(R.id.tv_sure);
            tv_cancel = (TextView) contentView_sendYanTao.findViewById(R.id.tv_cancel);
        } else {
            LogUtil.i("popwindow", "popwindow!=null");
        }
        edit_1.setVisibility(View.VISIBLE);
        edit_2.setVisibility(View.GONE);
        edit_3.setVisibility(View.GONE);
        edit_4.setVisibility(View.GONE);
        edit_5.setVisibility(View.GONE);
        tv_addTable.setVisibility(View.VISIBLE);
        edit_1.setText("");
        edit_2.setText("");
        edit_3.setText("");
        edit_4.setText("");
        edit_5.setText("");
        if (tabList.size()>0){
            editIndex=0;
        }else {
            editIndex=1;
        }
        for (String tabStr : tabList) {
            if (editIndex==0){
                edit_1.setVisibility(View.VISIBLE);
                edit_1.setText(tabStr);
            }else if (editIndex==1){
                edit_2.setVisibility(View.VISIBLE);
                edit_2.setText(tabStr);

            }else if (editIndex==2){
                edit_3.setVisibility(View.VISIBLE);
                edit_3.setText(tabStr);
            }else if (editIndex==3){
                edit_4.setVisibility(View.VISIBLE);
                edit_4.setText(tabStr);
            }else if (editIndex==4){
                edit_5.setVisibility(View.VISIBLE);
                edit_5.setText(tabStr);
                tv_addTable.setVisibility(View.GONE);
            }
            editIndex++;
        }
        tv_sure.setOnClickListener(new View.OnClickListener() {//----------------------------------1.确定按钮
            @Override
            public void onClick(View v) {
                addList();
                popupWindow_sendYanTao.dismiss();
            }
        });
        tv_cancel.setOnClickListener(new View.OnClickListener() {//--------------------------------2.取消按钮
            @Override
            public void onClick(View v) {
                popupWindow_sendYanTao.dismiss();
            }
        });
        tv_addTable.setOnClickListener(new View.OnClickListener() {//------------------------------3.添加标签按钮
            @Override
            public void onClick(View v) {
                addTable();
            }
        });


        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().setAttributes(lp);
        popupWindow_sendYanTao.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_sendYanTao.setOutsideTouchable(true);
        popupWindow_sendYanTao.setFocusable(true);
//        popupWindow1.showAsDropDown(jiuCuo);
        popupWindow_sendYanTao.showAtLocation(contentView_sendYanTao, Gravity.CENTER, 0, 0);
        popupWindow_sendYanTao.update();
        popupWindow_sendYanTao.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }


    /**
     * 研讨分类
     */
    private PopupWindow popupWindow_type;
    private View contentView_type;
    private GridView gridView;
    public void initPopuWindow_type() {
        if (popupWindow_type == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
            contentView_type = mLayoutInflater.inflate(R.layout.popwindow_type, null);
            popupWindow_type = new PopupWindow(contentView_type, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        if (gridView==null){
            LogUtil.i("grid","null");
            gridView= (GridView) contentView_type.findViewById(R.id.gridView_popwindow_type);
            Call<SendYanTaoTypeBean> call= MyApplication.getNetApi().getYanTaoType();
            call.enqueue(new Callback<SendYanTaoTypeBean>() {
                @Override
                public void onResponse(Call<SendYanTaoTypeBean> call, Response<SendYanTaoTypeBean> response) {
                    if (response.isSuccessful()){
                        SendYanTaoTypeBean.DataBean data = response.body().getData();
                        if (data!=null){
                            List<SendYanTaoTypeBean.DataBean.NavListBean> navList = data.getNavList();
                            if (navList!=null&&navList.size()>0){
                                for (SendYanTaoTypeBean.DataBean.NavListBean navListBean:navList){
                                    tabStringList.add(navListBean.getJpt_name());
                                    tabIndexList.add(navListBean.getJpt_id());
                                }
                                gridView.setAdapter(new YantaoTypeAdapter(tabStringList));
                                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        tv_type.setText(tabStringList.get(position));
                                        tv_type.setTextColor(Color.parseColor("#474747"));
                                        clickId=tabIndexList.get(position);
                                        popupWindow_type.dismiss();
                                    }
                                });
                            }
                        }

                    }else {
                        ToastUtils.showMessage(YanTaoSendActivity.this,"解析错误1");
                    }


                    call.cancel();
                }

                @Override
                public void onFailure(Call<SendYanTaoTypeBean> call, Throwable t) {
                    call.cancel();
                    ToastUtils.showMessage(YanTaoSendActivity.this, "解析错误2");
                }
            });
        }

        //产生背景变暗效果
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow1.setBackgroundDrawable(cd);
//        cd.setCallback(null);
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.8f;
        this.getWindow().setAttributes(lp);
        popupWindow_type.setBackgroundDrawable(new BitmapDrawable());
        popupWindow_type.setOutsideTouchable(true);
        popupWindow_type.setFocusable(true);
        popupWindow_type.showAsDropDown(relative_one);
        popupWindow_type.showAtLocation(contentView_type, Gravity.CENTER, 0, 0);
        popupWindow_type.update();
//        popupWindow_type.getContentView().startAnimation(AnimationUtil.createInAnimation(this, 300));
        popupWindow_type.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 1f;
                getWindow().setAttributes(lp);
            }
        });
    }

    private void addTable() {
        if (editIndex == 1) {
            edit_2.setVisibility(View.VISIBLE);
            edit_2.requestFocus();//获取焦点 光标出现
            edit_3.setVisibility(View.GONE);
            edit_4.setVisibility(View.GONE);
            edit_5.setVisibility(View.GONE);
            tv_addTable.setVisibility(View.VISIBLE);
        } else if (editIndex == 2) {
            edit_3.setVisibility(View.VISIBLE);
            edit_3.requestFocus();//获取焦点 光标出现
            edit_4.setVisibility(View.GONE);
            edit_5.setVisibility(View.GONE);
            tv_addTable.setVisibility(View.VISIBLE);
        } else if (editIndex == 3) {
            edit_4.setVisibility(View.VISIBLE);
            edit_4.requestFocus();//获取焦点 光标出现
            edit_5.setVisibility(View.GONE);
            tv_addTable.setVisibility(View.VISIBLE);
        } else {
            edit_5.setVisibility(View.VISIBLE);
            edit_5.requestFocus();//获取焦点 光标出现
            tv_addTable.setVisibility(View.GONE);
        }
        editIndex++;
    }

    private void addList() {
        tabList.clear();
        String editTextStr1 = EditTextUtil.getEditTextStr(edit_1);
        if (!TextUtils.isEmpty(editTextStr1)) {
            tabList.add(editTextStr1);
        }
        String editTextStr2 = EditTextUtil.getEditTextStr(edit_2);
        if (!TextUtils.isEmpty(editTextStr2)) {
            tabList.add(editTextStr2);
        }
        String editTextStr3 = EditTextUtil.getEditTextStr(edit_3);
        if (!TextUtils.isEmpty(editTextStr3)) {
            tabList.add(editTextStr3);
        }
        String editTextStr4 = EditTextUtil.getEditTextStr(edit_4);
        if (!TextUtils.isEmpty(editTextStr4)) {
            tabList.add(editTextStr4);
        }
        String editTextStr5 = EditTextUtil.getEditTextStr(edit_5);
        if (!TextUtils.isEmpty(editTextStr5)) {
            tabList.add(editTextStr5);
        }
        tab1.setVisibility(View.GONE);
        tab2.setVisibility(View.GONE);
        tab3.setVisibility(View.GONE);
        tab4.setVisibility(View.GONE);
        tab5.setVisibility(View.GONE);
        int x=1;
        for (String tabStr : tabList) {
            if (x==1){
                tab1.setVisibility(View.VISIBLE);
                tab1.setText(tabStr);
            }else if (x==2){
                tab2.setVisibility(View.VISIBLE);
                tab2.setText(tabStr);
            }else if (x==3){
                tab3.setVisibility(View.VISIBLE);
                tab3.setText(tabStr);
            }else if (x==4){
                tab4.setVisibility(View.VISIBLE);
                tab4.setText(tabStr);
            }else if (x==5){
                tab5.setVisibility(View.VISIBLE);
                tab5.setText(tabStr);
            }
            x++;
        }
    }
    private boolean isSend(){
        if (clickId>0){
            if (EditTextUtil.getEditTextStr(editText_title).length()>0){
                return true;
            }else {
                ToastUtils.showMessage(this,"请填写问题标题");
                return false;
            }
        }else {
            ToastUtils.showMessage(this,"请选分类");
            return false;
        }
    }
    private String getTabList(){
        if (tabList.size()>0){
            StringBuffer sb=new StringBuffer();
            for (String str:tabList){
                sb.append(str+",");
            }
            return sb.toString();
        }else {
            return "";
        }
    }

}
