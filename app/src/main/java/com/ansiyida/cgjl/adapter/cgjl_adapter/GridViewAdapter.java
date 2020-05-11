package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.ansiyida.cgjl.R;
import com.bumptech.glide.Glide;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    private Activity mContext;
    private List<String> mList;
    private LayoutInflater inflater;

    public GridViewAdapter(Context mContext, List<String> mList) {
        this.mContext = (Activity) mContext;
        this.mList = mList;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        //return mList.size() + 1;//因为最后多了一个添加图片的ImageView
        int count = mList == null ? 1 : mList.size() + 1;
        if (count > 5) {
            return mList.size();
        } else {
            return count;
        }
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.cpzj_grid_item, parent,false);
        ImageView iv = (ImageView) convertView.findViewById(R.id.cpzj_image);
        ImageView del_image = convertView.findViewById(R.id.cpzj_del_image);
        del_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridViewListnear.removeImg(position);
            }
        });
        if (position < mList.size()) {
            //代表+号之前的需要正常显示图片
            String picUrl = mList.get(position); //图片路径
            Glide.with(mContext).load(picUrl).centerCrop().into(iv);
        } else {
            iv.setImageResource(R.mipmap.choosepick);//最后一个显示加号图片
            del_image.setVisibility(View.GONE);
        }
        return convertView;
    }

    public interface GridViewListnear{
        void removeImg(int position);
    }
    public GridViewListnear gridViewListnear;

    public void setGridViewListnear(GridViewListnear gridViewListnear){
        this.gridViewListnear = gridViewListnear;
    }

}
