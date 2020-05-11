package com.ansiyida.cgjl.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.util.ResUtil;
import com.ansiyida.cgjl.util.ScreenUtil;
import com.ansiyida.cgjl.view.WheelView;


/**
 * 滑动选择对话框
 *
 * @author Phoenix
 * @date 2016-10-8 13:54
 */
public class WheelDialogFragment_zj extends BaseDialogFragment implements WheelView.OnValueChangeListener {
    public static final String DIALOG_LEFT = "dialog_left";
    public static final String DIALOG_RIGHT = "dialog_right";
    public static final String DIALOG_WHEEL = "dialog_wheel";

    private WheelView wheelView;
    private TextView tvLeft, tvRight;

    private String[] dialogWheel;
    private String dialogLeft, dialogRight;
    private OnWheelDialogListener onWheelDialogListener;

    @Override
    public void onStart() {
        super.onStart();
        //设置对话框显示在底部
        getDialog().getWindow().setGravity(Gravity.BOTTOM);
        //设置对话框弹出动画，从底部滑入，从底部滑出
        getDialog().getWindow().getAttributes().windowAnimations = R.style.Dialog_Animation;
        //设置让对话框宽度充满屏幕
        getDialog().getWindow().setLayout(ScreenUtil.getScreenWidth(activity), getDialog().getWindow().getAttributes().height);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogWheel = bundle.getStringArray(DIALOG_WHEEL);
        dialogLeft = bundle.getString(DIALOG_LEFT);
        dialogRight = bundle.getString(DIALOG_RIGHT);
    }

    @Override
    protected int getLayoutID() {
        return R.layout.view_dialog_wheel_qy;
    }

    @Override
    protected void initView(View view) {
        tvLeft = (TextView) view.findViewById(R.id.tv_wheel_dialog_left);
        tvRight = (TextView) view.findViewById(R.id.tv_wheel_dialog_right);
        wheelView = (WheelView) view.findViewById(R.id.WheelView_dialog);
    }

    @Override
    protected void setSubView() {
        tvLeft.setText(dialogLeft);
        tvRight.setText(dialogRight);

        wheelView.refreshByNewDisplayedValues(dialogWheel);
        //设置是否可以上下无限滑动
        wheelView.setWrapSelectorWheel(false);
        wheelView.setDividerColor(ResUtil.getColor(R.color.qy_text_night_day));
        wheelView.setSelectedTextColor(ResUtil.getColor(R.color.qy_text_blake));
        wheelView.setNormalTextColor(ResUtil.getColor(R.color.qy_text_night_day));
    }

    @Override
    protected void initEvent() {
        //左边按钮
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWheelDialogListener != null) {
                    onWheelDialogListener.onClickLeft(WheelDialogFragment_zj.this, getWheelValue());
                }
            }
        });

        //右边按钮
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onWheelDialogListener != null) {
                    onWheelDialogListener.onClickRight(WheelDialogFragment_zj.this, getWheelValue());
                }
            }
        });
        wheelView.setOnValueChangedListener(new WheelView.OnValueChangeListener() {
            @Override
            public void onValueChange(WheelView picker, int oldVal, int newVal) {
                try {


                    String[] content = wheelView.getDisplayedValues();
                    if (content != null && onWheelDialogListener != null) {
                        onWheelDialogListener.onValueChange(WheelDialogFragment_zj.this, content[newVal - wheelView.getMinValue()]);
                    }
                }
                catch (Exception e)
                {
                    e.toString();
                }
            }
        });
    }

    @Override
    protected void onCancel() {

    }

    /**
     * 获取当前值
     *
     * @return
     */
    private String getWheelValue() {
        String[] content = wheelView.getDisplayedValues();
        return content == null ? null : content[wheelView.getValue() - wheelView.getMinValue()];
    }

    @Override
    public void onValueChange(WheelView picker, int oldVal, int newVal) {
        try {
            String[] content = wheelView.getDisplayedValues();
            if (content != null && onWheelDialogListener != null) {
                onWheelDialogListener.onValueChange(WheelDialogFragment_zj.this, content[newVal - wheelView.getMinValue()]);
            }
        }
         catch (Exception e)
        {
            e.toString();
           // Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();

        }
    }

    public interface OnWheelDialogListener {
        /**
         * 左边按钮单击事件回调
         *
         * @param dialog
         * @param value
         */

        void onClickLeft(DialogFragment dialog, String value);

        /**
         * 右边按钮单击事件回调
         *
         * @param dialog
         * @param value
         */
        void onClickRight(DialogFragment dialog, String value);

        /**
         * 滑动停止时的回调
         *
         * @param dialog
         * @param value
         */
        void onValueChange(DialogFragment dialog, String value);

    }

    /**
     * 对外开放的方法
     *
     * @param onWheelDialogListener
     */
    public void setWheelDialogListener(OnWheelDialogListener onWheelDialogListener) {
        this.onWheelDialogListener = onWheelDialogListener;
    }
}
