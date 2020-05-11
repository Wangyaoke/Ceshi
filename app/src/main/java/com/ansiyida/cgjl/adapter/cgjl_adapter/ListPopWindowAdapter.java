package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;

import java.util.List;

public class ListPopWindowAdapter extends BaseAdapter {
    private List<String > list;
    private Context context;

    public ListPopWindowAdapter(List<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.listpopwindow_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.recy_text);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));
        int setPopItemSize = (int) SharedPreferenceUtils.get(context, "SetPopItemSize", 0);
        if(setPopItemSize!=0) {
            viewHolder.textView.setTextSize(setPopItemSize);
        }
        return convertView;
    }
    class ViewHolder{
        TextView textView;
    }
}
