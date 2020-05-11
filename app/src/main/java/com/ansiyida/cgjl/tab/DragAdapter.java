package com.ansiyida.cgjl.tab;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.InterstingTabActivity;
import com.ansiyida.cgjl.activity.MineTabActivity;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.fragment.ChannalFragment;
import com.ansiyida.cgjl.util.LogUtil;

import java.util.List;

public class DragAdapter extends BaseAdapter {
    /**
     * TAG
     */
    private final static String TAG = "DragAdapter";
    /**
     * 是否显示底部的ITEM
     */
    private boolean isItemShow = false;
    /**
     * 控制的postion
     */
    private int holdPosition;
    /**
     * 是否改变
     */
    private boolean isChanged = false;
    /**
     * 是否可见
     */
    boolean isVisible = true;
    /**
     * 可以拖动的列表（即用户选择的频道列表）
     */
    public List<ChannelItem> channelList;
    /**
     * TextView 频道内容
     */
    private TextView item_text;
    /**
     * 要删除的position
     */
    public int remove_position = -1;
    private boolean flag;
    private DbMannager mannager;
    private Context activity;
    private ChannalFragment fragment;
    private MineTabActivity mineTabActivity;
    private InterstingTabActivity interstingTabActivity;
    private String tableName;

    public DragAdapter(Context activity, List<ChannelItem> channelList, Boolean flag, DbMannager mannager, ChannalFragment fragment, String tableName) {
        this.channelList = channelList;
        this.flag = flag;
        this.mannager = mannager;
        this.activity = activity;
        this.fragment = fragment;
        this.tableName = tableName;
    }

    public DragAdapter(Context activity, List<ChannelItem> channelList, Boolean flag, DbMannager mannager, MineTabActivity mineTabActivity, String tableName) {
        this.channelList = channelList;
        this.flag = flag;
        this.mannager = mannager;
        this.activity = activity;
        this.mineTabActivity = mineTabActivity;
        this.tableName = tableName;
    }
    public DragAdapter(Context activity, List<ChannelItem> channelList, Boolean flag, DbMannager mannager, InterstingTabActivity interstingTabActivity, String tableName) {
        this.channelList = channelList;
        this.flag = flag;
        this.mannager = mannager;
        this.activity = activity;
        this.interstingTabActivity = interstingTabActivity;
        this.tableName = tableName;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return channelList == null ? 0 : channelList.size();
    }

    @Override
    public ChannelItem getItem(int position) {
        // TODO Auto-generated method stub
        if (channelList != null && channelList.size() != 0) {
            return channelList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(activity).inflate(R.layout.subscribe_category_item, null);
        item_text = (TextView) view.findViewById(R.id.text_item);
        ImageView iv = (ImageView) view.findViewById(R.id.icon_new);
        ChannelItem channel = getItem(position);
        if (!flag) {
            if (!"mine_tab".equals(tableName)&&!"interst_tab".equals(tableName)) {

                if (position > 2) {//-----------------------------------------------------------------------------------------------------改动处4
                    iv.setVisibility(View.VISIBLE);
                } else {
                    iv.setVisibility(View.GONE);
                }
            }else {
                iv.setVisibility(View.VISIBLE);
            }
        }
        if ("channel2".equals(tableName)) {
            if (position > 2) {//-----------------------------------------------------------------------------------------------------改动处3
                item_text.setTextColor(activity.getResources().getColor(R.color.txt_color_day));
            } else {
                item_text.setTextColor(activity.getResources().getColor(R.color.tab_2_day));
            }
        }else {
            item_text.setTextColor(activity.getResources().getColor(R.color.txt_color_day));

        }

        if (fragment != null) {
            fragment.changeText();
        }
        if (mineTabActivity != null) {
            mineTabActivity.changeText();
        }
        if (interstingTabActivity != null) {
            interstingTabActivity.changeText();
        }
        item_text.setText(channel.getName());
        int length=channel.getName().length();
        if (length>4&&length<6){
            item_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
        }else if(length>=6) {
            item_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
        }else {
            item_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

        }
        if ((position == 0) || (position == 1)|| (position == 2)) {//-------------------------------------------------------------------------------改动处5
//			item_text.setTextColor(context.getResources().getColor(R.color.black));
            item_text.setEnabled(false);
        }
        if (isChanged && (position == holdPosition) && !isItemShow) {
            item_text.setText("");
            item_text.setSelected(true);
            item_text.setEnabled(true);
            isChanged = false;
        }
        if (!isVisible && (position == -1 + channelList.size())) {
            item_text.setText("");
            item_text.setSelected(true);
            item_text.setEnabled(true);
            item_text.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);
        }
        if (remove_position == position) {
            item_text.setText("");
            item_text.setVisibility(View.GONE);
            iv.setVisibility(View.GONE);

        }
        return view;
    }

    public void changeItem(boolean flag1) {
        flag = flag1;
        notifyDataSetChanged();
    }

    /**
     * 添加频道列表
     */
    public void addItem(ChannelItem channel) {
        channel.setSelected(1);
        channelList.add(channel);
        notifyDataSetChanged();
    }
    /**
     * 删除频道列表
     */
    public void remove() {
        channelList.remove(remove_position);
        remove_position = -1;
        notifyDataSetChanged();
    }
    public void addNewItem(ChannelItem channel){
        channelList.add(0, channel);
        notifyDataSetChanged();

    }

    public void saveSQL() {
        LogUtil.i("sql", "操作之前listLength:" + channelList.size());
        if ("mine_tab".equals(tableName)){
            mannager.clearTable(tableName);
        }
        mannager.setData(channelList, tableName);
        LogUtil.i("sql", "操作之后DragAdapter");
        mannager.showSQL(tableName);
    }

    /**
     * 拖动变更频道排序
     */
    public void exchange(int dragPostion, int dropPostion) {
        holdPosition = dropPostion;
        ChannelItem dragItem = getItem(dragPostion);
        Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
        if (dragPostion < dropPostion) {
            channelList.add(dropPostion + 1, dragItem);
            channelList.remove(dragPostion);
        } else {
            channelList.add(dropPostion, dragItem);
            channelList.remove(dragPostion + 1);
        }
        isChanged = true;
        notifyDataSetChanged();
    }

    /**
     * 获取频道列表
     */
    public List<ChannelItem> getChannnelLst() {
        return channelList;
    }

    /**
     * 设置删除的position
     */
    public void setRemove(int position) {
        remove_position = position;
        notifyDataSetChanged();
    }



    /**
     * 设置频道列表
     */
    public void setListDate(List<ChannelItem> list) {
        channelList = list;
    }

    /**
     * 获取是否可见
     */
    public boolean isVisible() {
        return isVisible;
    }

    /**
     * 设置是否可见
     */
    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    /**
     * 显示放下的ITEM
     */
    public void setShowDropItem(boolean show) {
        isItemShow = show;
    }
}