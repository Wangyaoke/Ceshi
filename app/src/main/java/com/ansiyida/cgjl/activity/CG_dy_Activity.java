package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.Fragment1ViewPagerAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.fragment.CgFragment;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

//import com.ansiyida.cgjl.fragment.HistoryFragment;

//
public class CG_dy_Activity extends BaseActivity {
    @Bind(R.id.viewPager_careFansActivity)
    ViewPager viewPager;
    @Bind(R.id.radioGroup)
    RadioGroup rg;
    @Bind(R.id.rb1_notice)
    RadioButton rb1;
    @Bind(R.id.rb2_law)
    RadioButton rb2;
    @Bind(R.id.rb3_classified_buy)
    RadioButton rb3;

    @Bind(R.id.image_back)
    ImageView image_back;
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.text_putOut)
    TextView text_putOut;

    private Drawable icon;
    private ArrayList<Fragment> list;
    private boolean isVisible = false;
    private boolean isVisible2 = false;
    private int selectCount;
    private CgFragment collectFragment1;
    private CgFragment collectFragment2;
    private CgFragment collectFragment3;
    private String KERWORD;
    private int localPosition = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        try
        {
            super.onCreate(savedInstanceState);
        }
        catch (Exception e)
        {
            e.toString();
                 }

    }

    @Override
    protected int getContentView() {
        try {
            return R.layout.activity_cg_dy;
        } catch (Exception E)
         {
             E.toString();
             return R.layout.activity_cg_dy;
         }

    }

    @Override
    protected void initView() {
        Intent intent =getIntent();
        setStateColor(this, true);
        title.setText(intent.getStringExtra("title"));
        KERWORD=intent.getStringExtra("keyword");
        text_putOut.setVisibility(View.GONE);
        int[] location = new int[2];

    }

    @Override
    protected void initData() {
        try {
        list = new ArrayList<>();
        collectFragment1 = new CgFragment();
        collectFragment1.mode="all";
        collectFragment1.cg_keyword=KERWORD;
        list.add(collectFragment1);
       collectFragment2 = new CgFragment();
       collectFragment2.mode="false";
       collectFragment2.cg_keyword=KERWORD;
        list.add(collectFragment2);
        collectFragment3= new CgFragment();
        collectFragment3.mode="true";
        collectFragment3.cg_keyword=KERWORD;
        list.add(collectFragment3);

        viewPager.setAdapter(new Fragment1ViewPagerAdapter(getSupportFragmentManager(), list));
      //  viewPager.setCurrentItem(0);
        rb1.setChecked(true); }
        catch (Exception e)
        {
            e.toString();
        }
    }

    @Override
    protected void httpData() {

    }
    public void refreshed() {
        Log.e("ViewPage", "refreshed: "+viewPager.getCurrentItem() );
     if(viewPager.getCurrentItem()==0)
        collectFragment1.refreshed ();
    else if(viewPager.getCurrentItem()==1)
         collectFragment2.refreshed ();
     else if(viewPager.getCurrentItem()==2)
         collectFragment3.refreshed ();
    }
    @Override
    protected void setClickListener() {
        //RadioGroup监听子控件
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb1_notice:

                        viewPager.setCurrentItem(0);
                        collectFragment1.refreshed ();
                        break;
                    case R.id.rb2_law:
                        viewPager.setCurrentItem(1);
                        collectFragment2.refreshed ();
                        break;
                    case R.id.rb3_classified_buy:

                        viewPager.setCurrentItem(2);
                        collectFragment3.refreshed ();
                        break;

                    default:
                        break;
                }
            }
        });

        //ViewPager监听滑动事件
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                localPosition = position;
                if (position == 0) {

                    clickTab();
                    rb1.setChecked(true);

                }
                else if (position == 1){

                    clickTab();
                    rb2.setChecked(true);

                }
                else {

                    clickTab();
                    rb3.setChecked(true);

                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick({R.id.image_back})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:               //1.返回上一级
                this.finish();
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
    private void clickTab() {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
    }

}
