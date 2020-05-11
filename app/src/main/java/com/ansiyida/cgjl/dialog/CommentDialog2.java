package com.ansiyida.cgjl.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansiyida.cgjl.R;

/**
 * Created by ansiyida on 2017/12/15.
 */
public class CommentDialog2 extends DialogFragment implements View.OnClickListener {

    //点击发表，内容不为空时的回调
    public SendListener sendListener;
    private TextView tv_send;
    private String hintText, text;

    private Dialog dialog;
    private EditText et_content;
    private ImageView iv_emoji;
    private Activity activity;
    private TextView tv_cancel;
    private int textCount;
    private String rightBtnName;
    private boolean flag=true;

    public CommentDialog2() {
    }

    @SuppressLint("ValidFragment")
    public CommentDialog2(String hintText, String text, int textCount, SendListener sendBackListener, Activity activity,String rightBtnName) {//提示文字
        this.hintText = hintText;
        this.sendListener = sendBackListener;
        this.activity = activity;
        this.text = text;
        this.textCount = textCount;
        this.rightBtnName=rightBtnName;
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        dialog = new Dialog(getActivity(), R.style.Comment_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        View contentView = View.inflate(getActivity(), R.layout.comment_dialog_layout, null);
        dialog.setContentView(contentView);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.alpha = 1;
        lp.dimAmount = 0.0f;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        WindowManager.LayoutParams lp2 = getActivity().getWindow().getAttributes();
        lp2.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp2);
//        iv_emoji = (ImageView) contentView.findViewById(R.id.iv_emoji);
        et_content = (EditText) contentView.findViewById(R.id.et_comment);
        TextView tv_cancel = (TextView) contentView.findViewById(R.id.tv_cancel);
        TextView tv_commit = (TextView) contentView.findViewById(R.id.tv_commit);
        final TextView textLength = (TextView) contentView.findViewById(R.id.textCount);
        tv_commit.setText(rightBtnName);
        et_content.setHint(hintText);
        et_content.setText(text);
        et_content.setSelection(text.length());
        InputFilter[] filters = {new InputFilter.LengthFilter(textCount)};
        et_content.setFilters(filters);
        textLength.setText(text.length() + "/" + textCount);
        et_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.toString().length();
                if (length >= 0) {
                    if (length > textCount) {

                    } else {
                        textLength.setText(length + "/" + textCount);

                    }

                }

            }
        });
//        et_content.addTextChangedListener(new SketchTextWatcher(et_content, textCount));

//        InputFilter[] filters = {new SketchLengthFilter(textCount)};
//        et_content.setFilters(filters);

        tv_cancel.setOnClickListener(this);
        tv_commit.setOnClickListener(this);
        return dialog;
    }


    public void cleanText() {
        et_content.setText("");
    }

//    @Override
//    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//    }
//
//    @Override
//    public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//    }

    /**
     * 输入过程中调用方法
     */
//    @Override
//    public void afterTextChanged(Editable s) {
//        if (s.length() > 0) {
//            tv_send.setEnabled(true);
//            tv_send.setTextColor(Color.BLACK);
//        } else {
//            tv_send.setEnabled(false);
//            tv_send.setTextColor(Color.GRAY);
//        }
//    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                dialog.dismiss();
                break;
            case R.id.tv_commit:
                checkContent();
                break;
        }
    }

    private void checkContent() {
        String content = et_content.getText().toString().trim();
        if (flag){
            if (TextUtils.isEmpty(content)) {
                Toast.makeText(getContext(), "内容不能为空", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        sendListener.sendComment(content);
        dismiss();
    }
    public void setFlag(boolean flag){
        this.flag=flag;
    }

    public interface SendListener {
        void sendComment(String inputText);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        WindowManager.LayoutParams lp2 = getActivity().getWindow().getAttributes();
        lp2.alpha = 1f;
        getActivity().getWindow().setAttributes(lp2);
    }
}