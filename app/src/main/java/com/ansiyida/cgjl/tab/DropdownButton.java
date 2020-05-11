package com.ansiyida.cgjl.tab;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.bean.DropBean;
import com.ansiyida.cgjl.util.ToastUtils;

import java.util.List;

/**
 * Created by cuiMarker on 2016/12/13.
 */
public class DropdownButton extends RelativeLayout implements Checkable, View.OnClickListener, PopWinDownUtil.OnDismissLisener, AdapterView.OnItemClickListener {
    /**
     * 菜单按钮文字内容
     */
    public Window window;
    public LinearLayout line;
    public Boolean lable;
    public RecyclerView recyclerView;
    private TextView text_title;
    private TextView text;
    private EditText  edittext_class;
    /**
     * 菜单按钮底部的提示条
     */
    private View bLine;
    private boolean isCheced;
    private PopWinDownUtil popWinDownUtil;
    private Context mContext;
    private DropDownAdapter adapter;
    /**
     * 传入的数据
     */
    private List<DropBean> drops;
    /**
     * 当前被选择的item位置
     */
    public int selectPosition;

    public DropdownButton(Context context) {
        this(context, null);
    }

    public DropdownButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropdownButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        //菜单按钮的布局
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.dropdown_tab_button,this, true);
        text = (TextView) view.findViewById(R.id.textView);
       // bLine = view.findViewById(R.id.bottomLine);
        //点击事件，点击外部区域隐藏popupWindow
        setOnClickListener(this);

       // container_line=(LinearLayout)findViewById(R.id.container);
     //   Intent i1=new Intent(context,CityPickerActivity.class);
     //   LocalActivityManager localActivityManager=new LocalActivityManager(MainActivity.class,true);
     //   View view1=LocalActivityManager.startActivity("i1",i1).getDecorView();
       // container_line.addView(view,ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }
    /**
     * 添加数据，默认选择第一项
     * @param dropBeans
     */
    public void setData(List<DropBean> dropBeans,String name,TextView title,EditText edittitle){
        if(dropBeans == null)
            return;
        if( dropBeans.isEmpty())
            return;
        text_title=title;
        edittext_class=edittitle;
        drops = dropBeans;
        drops.get(0).setChoiced(true);
       // text.setText(drops.get(0).getName());
        text.setText(name);
        selectPosition = 0;
        View view = LayoutInflater.from(mContext).inflate(R.layout.dropdown_content, null);
      //  view.findViewById(R.id.content1).getBackground().setAlpha(100);//0~255透明度值
        view.findViewById(R.id.content).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                popWinDownUtil.hide();
            }
        });
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setOnItemClickListener(this);

        adapter = new DropDownAdapter(drops,mContext);
        listView.setAdapter(adapter);

        popWinDownUtil = new PopWinDownUtil(mContext,view,this);
        popWinDownUtil.setOnDismissListener(this);
    }

    public void setText(CharSequence content) {
        text.setText(content);
    }
    /**
     * 根据传过来的参数改变状态
     * @param checked
     */
    @Override
    public void setChecked(boolean checked) {
        isCheced = checked;
        Drawable icon;
        if (checked) {
            icon = getResources().getDrawable(R.mipmap.ic_dropdown_actived);
            text.setTextColor(getResources().getColor(R.color.text_blue));
         //   bLine.setVisibility(VISIBLE);
            try{

           recyclerView.setAlpha(0.1f);
            line.setVisibility(View.VISIBLE);
            popWinDownUtil.show();
                text.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);}
            catch (Exception e)
            {
                e.toString();
            }
        } else {
            try {
                icon = getResources().getDrawable(R.mipmap.ic_dropdown_normal);
                text.setTextColor(getResources().getColor(R.color.text_black));
                //   bLine.setVisibility(GONE);
                recyclerView.setAlpha(1f);
                line.setVisibility(View.GONE);
                popWinDownUtil.hide();
                text.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
            }
            catch (NullPointerException ex)
            {
                Log.i("null", ex.toString());

            }
        }
        //把箭头画到textView右边

    }
    @Override
    public boolean isChecked() {
        return isCheced;
    }
    @Override
    public void toggle() {
        setChecked(!isCheced);
    }
    @Override
    public void onClick(View v) {
   //     hideInput();
        setChecked(!isCheced);
    }

    @Override
    public void onDismiss() {
        setChecked(false);
    }
  /*  public  void hideInput() {
     //   InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
     //   inputMethodManager.hideSoftInputFromWindow(edittext_class.getWindowToken(), 0);
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

    }*/

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
     /*   if(selectPosition == position){
            return;
        }*/

        //    adapter.getView(position,view,parent).setT
        selectPosition = position;
        drops.get(selectPosition).setChoiced(false);
        drops.get(position).setChoiced(true);

        if(text_title!=null)
     //   {
            text_title.setText(drops.get(position).getName());
      //  text.setText(drops.get(position).getName());}
/*else
            text.setText(drops.get(position).getName());*/
        if(lable)
            text.setText(drops.get(position).getName());
        if(edittext_class!=null)
        edittext_class.setText(drops.get(position).getName());
        adapter.notifyDataSetChanged();

        popWinDownUtil.hide();
        if(onDropItemSelectListener != null){
            onDropItemSelectListener.onDropItemSelect(position);
        }
    }
    public int getselectPosition() {
        return selectPosition;
    }

    private OnDropItemSelectListener onDropItemSelectListener;
    public void setOnDropItemSelectListener(OnDropItemSelectListener onDropItemSelectListener){
        this.onDropItemSelectListener = onDropItemSelectListener;
    }
    public interface OnDropItemSelectListener{
        void onDropItemSelect(int Postion);
    }


    class DropDownAdapter extends BaseAdapter {
        private List<DropBean> drops;
        private Context context;
        int mPosition = 0;// 选中的位置
        public DropDownAdapter(List<DropBean> drops, Context context){
            this.drops = drops;
            this.context = context;
        }
        @Override
        public int getCount() {
            return drops.size();
        }
        @Override
        public Object getItem(int position) {
            return position;
        }
        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           final ViewHolder holder;
           // iv=viewHolder.dismiss;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.dropdown_item,parent,false);
                holder.tv = (TextView) convertView.findViewById(R.id.name);

                convertView.setTag(holder);
            }else{
                holder = (ViewHolder) convertView.getTag();
            }
            holder.tv.setText(drops.get(position).getName());
            if (selectPosition == position) {
                holder.tv.setTextColor(mContext.getResources().getColor(
                        R.color.text_blue));
                convertView.setBackgroundColor(mContext.getResources().getColor(
                        R.color.btn_none_click));
            } else {
                holder.tv.setTextColor(mContext.getResources().getColor(
                        R.color.text_black));
                convertView.setBackgroundColor(mContext.getResources().getColor(
                        R.color.white));
            }
           /* holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(v==holder.tv)
                    holder.tv.setTextColor(getResources().getColor(R.color.text_blue));
                }
            });*/
         /*   if(drops.get(position).isChoiced()){
                holder.tv.setTextColor(getResources().getColor(R.color.text_blue));
            }*/
            return convertView;
        }
        private class ViewHolder{
            TextView tv;
          //  ImageView tig;
        }
        public void setSelectedPosition(int position) {
            this.mPosition = position;
        }
    }
}
