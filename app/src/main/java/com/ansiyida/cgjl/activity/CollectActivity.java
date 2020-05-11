package com.ansiyida.cgjl.activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.GroupAdapter1;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.fragment.CollectFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

//import com.ansiyida.cgjl.fragment.HistoryFragment;

//
public class CollectActivity extends BaseActivity {
    @Bind(R.id.collet_framelayout)
    FrameLayout collet_framelayout;
    @Bind(R.id.radioGroup)
    RadioGroup rg;
    @Bind(R.id.rb1_notice)
    RadioButton rb1;
    @Bind(R.id.rb2_law)
    RadioButton rb2;
    @Bind(R.id.rb3_classified_buy)
    RadioButton rb3;
    @Bind(R.id.rb4_buy_demand)
    RadioButton rb4;
    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;
    @Bind(R.id.rb_knowledge)
    RadioButton rbKnowledge;
    private Boolean rb4_sel = false;
    private Drawable icon;
    private ArrayList<Fragment> list;
    private boolean isVisible = false;
    private boolean isVisible2 = false;
    private int selectCount;
    private CollectFragment collectFragment1;
    private CollectFragment collectFragment2;
    private CollectFragment collectFragment3;
    private CollectFragment collectFragment4;
    private CollectFragment collectFragmentKnowledge;
    private int localPosition = 0;
    private boolean tabStateArr = true;
    private PopupWindow mPopupWindow = null;
    private GroupAdapter1 groupAdapter = null;
    private String[] GroupNameArray = {"全部", "只看提醒"};
    private View showPupWindow = null;
    private ListView groupListView = null;
    private TranslateAnimation animation;// 出现的动画效果
    private String drop_string = "";
    private int mmposition = 0;
    private Drawable drawableWeiHui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(CollectActivity.this,"Android收藏","CollectActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_collect;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        title.setText("收藏");
        text_putOut.setVisibility(View.GONE);
        int[] location = new int[2];
        animation = new TranslateAnimation(0, 0, -700, location[1]);
        animation.setDuration(500);

        //定义底部标签图片大小  
        drawableWeiHui = getResources().getDrawable(R.mipmap.collect_down);
        drawableWeiHui.setBounds(0,0,35,15);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度  
        rb4.setCompoundDrawables(null,null,drawableWeiHui,null);
    }

    @Override
    protected void initData() {
        collectFragment1 = new CollectFragment();
        collectFragment1.mode = "purchaseInfo";

        collectFragment2 = new CollectFragment();
        collectFragment2.mode = "viewpointInfo";

        collectFragmentKnowledge = new CollectFragment();
        collectFragmentKnowledge.mode = "knowledge";

        collectFragment3 = new CollectFragment();
        collectFragment3.mode = "purchaseSecret";

        collectFragment4 = new CollectFragment();
        collectFragment4.mode = "purchaseDemand";
        rb1.setChecked(true);
        getSupportFragmentManager().beginTransaction().replace(R.id.collet_framelayout,collectFragment1).commit();
    }

    @Override
    protected void httpData() {

    }

    public void refreshed() {
        httpData();
    }
    @Override
    protected void setClickListener() {
        //RadioGroup监听子控件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId !=R.id.rb4_buy_demand) {
                    //定义底部标签图片大小  
                    drawableWeiHui = getResources().getDrawable(R.mipmap.collect_down);
                    drawableWeiHui.setBounds(0,0,35,15);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度  
                    rb4.setCompoundDrawables(null,null,drawableWeiHui,null);
                }
                switch (checkedId) {
                    case R.id.rb1_notice:
                        getSupportFragmentManager().beginTransaction().replace(R.id.collet_framelayout,collectFragment1).commit();
                        break;
                    case R.id.rb2_law:
                        getSupportFragmentManager().beginTransaction().replace(R.id.collet_framelayout,collectFragment2).commit();
                        break;
                    case R.id.rb_knowledge:
                        getSupportFragmentManager().beginTransaction().replace(R.id.collet_framelayout,collectFragmentKnowledge).commit();
                        break;
                    case R.id.rb3_classified_buy:
                        getSupportFragmentManager().beginTransaction().replace(R.id.collet_framelayout,collectFragment3).commit();
                        break;
                    case R.id.rb4_buy_demand:
                        getSupportFragmentManager().beginTransaction().replace(R.id.collet_framelayout,collectFragment4).commit();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void setTabState(boolean state) {
        //定义底部标签图片大小  
        tabStateArr = !state;
        if (state) {// 选中状态
            drawableWeiHui = getResources().getDrawable(R.mipmap.collect_blue_up);
            showPupupWindow();
        } else {
            drawableWeiHui = getResources().getDrawable(R.mipmap.collect_blue_down);
            mPopupWindow.dismiss();
        }
        drawableWeiHui.setBounds(0,0,35,15);//第一0是距左右边距离，第二0是距上下边距离，第三69长度,第四宽度  
        rb4.setCompoundDrawables(null,null, drawableWeiHui,null);
    }

    public void initPopuWindow(View view) {
        /* 第一个参数弹出显示view 后两个是窗口大小 */
        mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // mPopupWindow = new PopupWindow(view, screen_width, screen_height);
        /* 设置背景显示 */
        ColorDrawable dw = new ColorDrawable(Color.TRANSPARENT);
        mPopupWindow.setBackgroundDrawable(dw);
        //mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.jc_title_bg));
        /* 设置触摸外面时消失 */
        mPopupWindow.setOutsideTouchable(true);

        //  mPopupWindow.update();
        //   mPopupWindow.setTouchable(true);
        /* 设置点击menu以外其他地方以及返回键退出 */
        mPopupWindow.setFocusable(true);

        /**
         * 1.解决再次点击MENU键无反应问题 2.sub_view是PopupWindow的子View
         */
        //  view.setFocusableInTouchMode(true);
        //  mPopupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                setTabState(false);
            }
        });
    }

    private void showPupupWindow() {
        //     if (mPopupWindow == null) {
        showPupWindow = LayoutInflater.from(this).inflate(
                R.layout.dropdown_dropdown_item1, null);
        initPopuWindow(showPupWindow);

        groupListView = (ListView) showPupWindow
                .findViewById(R.id.listView1);


        groupAdapter = new GroupAdapter1(this, GroupNameArray);
        groupListView.setAdapter(groupAdapter);
        // }
        groupAdapter.setSelectedPosition(mmposition);
        groupListView.setOnItemClickListener(new MyItemClick());
        showPupWindow.setAnimation(animation);
        showPupWindow.startAnimation(animation);
        mPopupWindow.showAsDropDown(rb4, -5, 10);

    }

    class MyItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            drop_string = groupListView.getItemAtPosition(position).toString();
            if (drop_string.equals("全部")) {
                collectFragment4.flag = false;
                collectFragment4.refreshed();
            } else {
                collectFragment4.flag = true;
                collectFragment4.refreshed();
            }
            mPopupWindow.dismiss();
            mmposition = position;
            groupAdapter.setSelectedPosition(mmposition);
        }
    }

    @OnClick({R.id.image_back, R.id.rb4_buy_demand})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:               //1.返回上一级

                this.finish();
                break;
            case R.id.text_putOut:              //2.“编辑”按钮

                break;
            case R.id.deleteAll:                //3.左上角“全部删除”按钮


                break;
            case R.id.edit_cancel:              //4.“取消”按钮

                break;
            case R.id.rb4_buy_demand:              //4.“取消”按钮
                if (rb4_sel)
                    setTabState(tabStateArr);
                rb4_sel = true;
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (list != null) {
            list.clear();
            list = null;
        }
    }

}
