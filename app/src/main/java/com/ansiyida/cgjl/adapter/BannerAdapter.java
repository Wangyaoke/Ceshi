package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.activity.PhotoDetailsActivity;
import com.ansiyida.cgjl.activity.VideoDetailsActivity;
import com.ansiyida.cgjl.activity.YanTaoActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ansiyida on 2017/11/28.
 */
public class BannerAdapter extends RecyclerView.Adapter {
    private ArrayList<String> picName;
    private ArrayList<String> picUrl;
    private ArrayList<String> jumpUrl;
    private Context context;
    public BannerAdapter(ArrayList<String> picUrl, ArrayList<String> picName,ArrayList<String> jumpUrl,Context context) {
        this.picUrl = picUrl;
        this.picName = picName;
        this.context=context.getApplicationContext();
        this.jumpUrl=jumpUrl;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new MyViewHolderBanner(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_banner, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MyViewHolderBanner holder1 = (MyViewHolderBanner) holder;
        Glide.with(context).load(picUrl.get(position)).centerCrop().into(holder1.banner_img);
        holder1.banner_title.setText(picName.get(position));
        holder1.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = jumpUrl.get(position);
                String[] arr = str.split("/");
                int length = arr.length;
                if (length > 2) {
                    String artype = arr[length - 2];
                    String id = arr[length - 1];
                    Intent intent;
                    switch (artype) {
                        case "P":
                            intent = new Intent(context, NewsDetailsActivity.class);
                            intent.putExtra("type", artype + "");
                            intent.putExtra("id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            break;
                        case "T":
                            intent = new Intent(context, PhotoDetailsActivity.class);
                            intent.putExtra("type", artype + "");
                            intent.putExtra("id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            break;
                        case "S":
                            intent = new Intent(context, VideoDetailsActivity.class);
                            intent.putExtra("type", artype + "");
                            intent.putExtra("id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            break;
                        case "C":
                            intent = new Intent(context, NewsDetailsActivity.class);
                            intent.putExtra("type", artype + "");
                            intent.putExtra("id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            break;
                        case "D":
                            intent = new Intent(context, YanTaoActivity.class);
                            intent.putExtra("jc_id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int screenWidth = dm.widthPixels;

        RelativeLayout.LayoutParams layoutParams= (RelativeLayout.LayoutParams) holder1.relativeLayout.getLayoutParams();
        layoutParams.width=screenWidth;
        holder1.relativeLayout.setLayoutParams(layoutParams);
//        holder1.banner_time.setText("2小时前");

    }

    class MyViewHolderBanner extends RecyclerView.ViewHolder {
        @Bind(R.id.banner_img)
        ImageView banner_img;
        @Bind(R.id.banner_title)
        TextView banner_title;
        @Bind(R.id.relative)
        RelativeLayout relativeLayout;
//        TextView banner_time;

        public MyViewHolderBanner(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    @Override
    public int getItemCount() {
        return picUrl.size();
    }
}
