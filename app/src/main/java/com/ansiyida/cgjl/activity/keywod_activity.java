package com.ansiyida.cgjl.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.SearchRecordAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.SearchUse;
import com.ansiyida.cgjl.fragment.Fragment3;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.MyGridView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class keywod_activity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView text_title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;
    @Bind(R.id.editText_key)
    EditText editText_key;
    @Bind(R.id.gridViewTop_fragment3)
    MyGridView gridView;
    private ArrayList<SearchUse> record_list;
    private ArrayList<SearchUse> list;
    private SearchRecordAdapter adapterTop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {

        return R.layout.keyword_layout;
    }

    @Override
    protected void initView() {
        text_title.setText("添加关键词");
        text_putOut.setText("提交");
        list = new ArrayList<>();
        Fragment3 fragment3=new Fragment3();
        record_list = new ArrayList<>();
        adapterTop = new SearchRecordAdapter(record_list,keywod_activity.this, false, fragment3);
        gridView.setAdapter(adapterTop);

    }
    private void visibleKeyBoard() {
        InputMethodManager imm = (InputMethodManager) editText_key.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }
    @Override
    protected void initData() {

        editText_key.setText("");
        editText_key.requestFocus();
        visibleKeyBoard();
        editText_key.setSelection(editText_key.getText().length());
    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        editText_key.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {
                   LogUtil.i("jiao", "获取焦点");
                } else {
                    // 失去焦点
                   LogUtil.i("jiao", "失去焦点");
                }

            }


        });
        editText_key.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //这里是我要做的操作！
                    if(list.size()<3)
                    {   list.add(new SearchUse( EditTextUtil.getEditTextStr(editText_key), ""));
                    textSmall();
                    adapterTop.notifyDataSetChanged();}
                    else
                        ToastUtils.showMessage(keywod_activity.this,"关键词不能超过三个！");
                    return true;
                }
                return false;
            }
        });
        //编辑框输入监听
        editText_key.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (!"".equals(s1)){
                  //  editText_key.setVisibility(View.VISIBLE);
                }else {
                   // editText_key.setVisibility(View.GONE);
                }
            }
        });

    }
    private void textSmall() {
        int lenth = list.size();
        record_list.clear();
        for (int x = 0; x < lenth; x++) {
            record_list.add(list.get(x));
        }
        adapterTop.notifyDataSetChanged();
    }
    @OnClick({R.id.image_back, R.id.text_putOut})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.text_putOut:                 //5.用户协议
                String msg2 = "";
                if(list.size()>0)
                {   for(int i=0;i<list.size();i++)
                {   if(i==list.size()-1)
                    msg2=msg2+list.get(i).getText();
                else
                    msg2=msg2+list.get(i).getText()+"/";}
                returnResult(msg2);}
                else
               this.finish();
                break;
            case R.id.image_back:               //1.返回上一级

                String msg3 = "";
                if(list.size()>0)
                {
                    for(int i=0;i<list.size();i++)
                {   if(i==list.size()-1)
                        msg3=msg3+list.get(i).getText();
                        else
                    msg3=msg3+list.get(i).getText()+"/";}
                returnResult(msg3);}
                else
                    this.finish();
                break;
            default:
                break;

        }
    }



    void returnResult(String sdata) {
        Intent result = new Intent();
        result.putExtra("keyword", sdata);
        setResult(ActivityCodeUtil.KEYWORD , result);
        finish();
    }

    @Override
    public void onBackPressed() {
        String msg3 = "";
        if(list.size()>0)
        {
            for(int i=0;i<list.size();i++)
            {   if(i==list.size()-1)
                msg3=msg3+list.get(i).getText();
            else
                msg3=msg3+list.get(i).getText()+"/";}
            Intent result = new Intent();
            result.putExtra("keyword", msg3);
            setResult(ActivityCodeUtil.KEYWORD , result);}

           // this.finish();
        super.onBackPressed();

    }
}

