package com.ansiyida.cgjl.tab;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.util.LogUtil;

import java.util.List;

public class OtherAdapter extends BaseAdapter {
	private Context context;
	public List<ChannelItem> channelList;
	private TextView item_text;
	/** 是否可见 */
	boolean isVisible = true;
	/** 要删除的position */
	public int remove_position = -1;
	private String tableName;

	public OtherAdapter(Context context, List<ChannelItem> channelList,String tableName) {
		this.context = context;
		this.channelList = channelList;
		this.tableName = tableName;
	}

	@Override
	public int getCount() {
		return channelList == null ? 0 : channelList.size();
	}

	@Override
	public ChannelItem getItem(int position) {
		if (channelList != null && channelList.size() != 0) {
			return channelList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(context).inflate(R.layout.subscribe_category_item, null);
		item_text = (TextView) view.findViewById(R.id.text_item);
		ChannelItem channel = getItem(position);
		item_text.setText(channel.getName());
		if (!isVisible && (position == -1 + channelList.size())){
			item_text.setText("");
			item_text.setVisibility(View.GONE);
		}
		if(remove_position == position){
			item_text.setText("");
			item_text.setVisibility(View.GONE);

		}
		int length=channel.getName().length();
		if (length>4&&length<6){
			item_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,13);
		}else if(length>=6) {
			item_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
		}else {
			item_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,14);

		}
		return view;
	}

	/** 获取频道列表 */
	public List<ChannelItem> getChannnelLst() {
		return channelList;
	}

	/** 添加频道列表 */
	public void addItem(ChannelItem channel) {
		channel.setSelected(0);
		channelList.add(channel);
		notifyDataSetChanged();
	}
	public void saveSQL(){
		DbMannager mannager=DbMannager.getInstance();
		mannager.setData(channelList,tableName);
		LogUtil.i("sql", "操作之后OtherAdapter");
		mannager.showSQL(tableName);
	}

	/** 设置删除的position */
	public void setRemove(int position) {
		remove_position = position;
		notifyDataSetChanged();
		// notifyDataSetChanged();
	}

	/** 删除频道列表 */
	public void remove() {
		channelList.remove(remove_position);
		remove_position = -1;
		notifyDataSetChanged();
	}
	/** 设置频道列表 */
	public void setListDate(List<ChannelItem> list) {
		channelList = list;
	}

	/** 获取是否可见 */
	public boolean isVisible() {
		return isVisible;
	}

	/** 设置是否可见 */
	public void setVisible(boolean visible) {
		isVisible = visible;
	}
}