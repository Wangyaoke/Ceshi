package com.ansiyida.cgjl.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.SearchUse;
import com.ansiyida.cgjl.fragment.Fragment3;
import com.ansiyida.cgjl.util.DpPxTools;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2017/12/7.
 */
public class SearchRecordAdapter extends BaseAdapter {
    private ArrayList<SearchUse> list;
    private Context context;
    private boolean flag;
    private Fragment3 fragment3;

    public SearchRecordAdapter(ArrayList<SearchUse> list,Context context,boolean flag,Fragment3 fragment3){
        this.list=list;
        this.context=context.getApplicationContext();
        this.flag=flag;
        this.fragment3=fragment3;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView==null){
            holder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.adapter_search_record,null);
            holder.searchTitle= (TextView) convertView.findViewById(R.id.searchTitle);
            holder.tv_line= (TextView) convertView.findViewById(R.id.tv_line);
            holder.tv_rank= (TextView) convertView.findViewById(R.id.tv_rank);
          //  holder.image_delete= (ImageView) convertView.findViewById(R.id.image_delete);
            holder.iv_rank= (ImageView) convertView.findViewById(R.id.iv_rank);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        final SearchUse searchUse = list.get(position);
        holder.searchTitle.setText(searchUse.getText());
        if (flag){
            //搜索首页下方热搜排行榜
            RelativeLayout.LayoutParams rl= (RelativeLayout.LayoutParams) holder.searchTitle.getLayoutParams();
            rl.leftMargin= DpPxTools.dip2px(context,5);
            holder.searchTitle.setLayoutParams(rl);
           // holder.iv_rank.setVisibility(View.VISIBLE);
            holder.tv_rank.setVisibility(View.GONE);
            if (position%2==0){
                holder.tv_line.setVisibility(View.GONE);
            }else {
                holder.tv_line.setVisibility(View.GONE);

            }
            if (position==0){
          //      holder.iv_rank.setVisibility(View.VISIBLE);
                holder.tv_rank.setVisibility(View.GONE);
               // holder.iv_rank.setImageResource(R.mipmap.rank_one);
            }else if (position==1){
               // holder.iv_rank.setVisibility(View.VISIBLE);
               //holder.iv_rank.setImageResource(R.mipmap.rank_two);
                holder.tv_rank.setVisibility(View.GONE);
            }else if (position==2){
              //  holder.iv_rank.setVisibility(View.VISIBLE);
               // holder.iv_rank.setImageResource(R.mipmap.rank_three);
                holder.tv_rank.setVisibility(View.GONE);
            } else {
             //   holder.iv_rank.setVisibility(View.GONE);
                holder.tv_rank.setVisibility(View.GONE);

            }
         //   holder.image_delete.setVisibility(View.GONE);

        }else {
            //搜索记录
            RelativeLayout.LayoutParams rl= (RelativeLayout.LayoutParams) holder.searchTitle.getLayoutParams();
         //   rl.widthleftMargin= DpPxTools.dip2px(context,15);
            rl.width= RelativeLayout.LayoutParams.WRAP_CONTENT;
            holder.searchTitle.setLayoutParams(rl);
         //   holder.searchTitle.getWidth()
          //  holder.image_delete.setVisibility(View.VISIBLE);
            holder.iv_rank.setVisibility(View.GONE);
            holder.tv_line.setVisibility(View.GONE);
            holder.tv_rank.setVisibility(View.GONE);
         /*   holder.image_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Call<DefaultBean> call= MyApplication.getNetApi().deleteSearchHistory((String) SharedPreferenceUtils.get(context, "app_token", ""));
                    call.enqueue(new Callback<DefaultBean>() {
                        @Override
                        public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                            if (response.isSuccessful()) {
                                fragment3.removeItem(position);

                            }else {
                                ToastUtils.showMessage(context, "删除失败");

                            }
                        }

                        @Override
                        public void onFailure(Call<DefaultBean> call, Throwable throwable) {
                            ToastUtils.showMessage(context, "删除失败");

                        }
                    });
                }
            });*/

        }


        return convertView;
    }
    class ViewHolder{
        TextView searchTitle,tv_line,tv_rank;
        ImageView iv_rank;
    }
}
