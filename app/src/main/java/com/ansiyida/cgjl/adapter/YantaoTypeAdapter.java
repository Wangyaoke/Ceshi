package com.ansiyida.cgjl.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ansiyida.cgjl.R;

import java.util.ArrayList;

/**
 * Created by ansiyida on 2018/3/26.
 */
public class YantaoTypeAdapter extends BaseAdapter {
    private ArrayList<String> lists;
    public YantaoTypeAdapter(ArrayList<String> lists){
        this.lists=lists;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_yantao_type,parent,false );
            holder=new ViewHolder();
            holder.tab= (TextView) convertView.findViewById(R.id.tab);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }

        holder.tab.setText(lists.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tab;
    }
}
