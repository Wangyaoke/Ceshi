package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ansiyida.cgjl.R;

/**
 * 子ListView适配器
 *
 * @author zihao
 *
 */
public class ChildAdapter extends BaseAdapter {

    Context mContext;
    String[] mChildArr;// 子item标题数组
    int mPosition = 0;// 选中的位置
    /**
     * 构造方法
     *
     * @param context
     */
    public ChildAdapter(Context context) {
        mContext = context;
    }

    /**
     * 为子ListVitem设置要显示的数据
     *
     * @param childArr
     */
    public void setChildData(String[] childArr) {
        this.mChildArr = childArr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.activity_dropdown_shi, null);
            holder.childText = (TextView) convertView
                    .findViewById(R.id.child_textView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.childText.setText(mChildArr[position]);
        if (mPosition != 0)
        { if (mPosition == position) {
            holder.childText.setTextColor(mContext.getResources().getColor(
                    R.color.text_blue));
            convertView.setBackgroundColor(mContext.getResources().getColor(
                    R.color.btn_none_click));
        } else {
            holder.childText.setTextColor(mContext.getResources().getColor(
                    R.color.text_black));
            convertView.setBackgroundColor(mContext.getResources().getColor(
                    R.color.color_gray_back));
        }}
        else {
            holder.childText.setTextColor(mContext.getResources().getColor(
                    R.color.text_black));
            convertView.setBackgroundColor(mContext.getResources().getColor(
                    R.color.color_gray_back));
        }
        return convertView;
    }

    static class ViewHolder {
        TextView childText;
    }

    /**
     * 获取item总数
     */
    @Override
    public int getCount() {
        if (mChildArr == null) {
            return 0;
        }
        return mChildArr.length;
    }

    /**
     * 获取某一个Item的内容
     */
    @Override
    public Object getItem(int position) {
        return mChildArr[position];
    }

    /**
     * 获取当前item的ID
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    public void setSelectedPosition(int position) {
        this.mPosition = position;
    }
}

