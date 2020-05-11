package com.ansiyida.cgjl.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ansiyida.cgjl.MainActivity;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;

import butterknife.Bind;

public class GuideActivity extends BaseActivity {
    @Bind(R.id.viewPager_guideActivity)
    ViewPager viewPager;
    @Bind(R.id.iv_1)
    ImageView iv_1;
    @Bind(R.id.iv_2)
    ImageView iv_2;
    @Bind(R.id.iv_3)
    ImageView iv_3;
    //private int [] imgArray={R.mipmap.yindao_1,R.mipmap.yindao_2,R.mipmap.yindao_3};
      private int[] imgArray={R.drawable.guideone,R.drawable.guidetwo,R.drawable.guidethree,R.drawable.guidefour};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(GuideActivity.this,"Android引导页","MainActivity");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {
        viewPager.setAdapter(new ViewPagerAdapter());
    }

    @Override
    protected void setClickListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    private void changeCircle(int position){

    }
    // 图片轮播图
    private class ViewPagerAdapter extends PagerAdapter {
        @SuppressLint("InflateParams")
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            View view = getLayoutInflater().inflate(R.layout.adapter_guide, null);
            ImageView imageView= (ImageView) view.findViewById(R.id.iv_guideAdapter);
            // 给imageview设置一个tag，保证异步加载图片时不会乱序
            imageView.setBackgroundResource(imgArray[position]);
            if(position==3){
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SharedPreferenceUtils.put(GuideActivity.this,"isFirstOpens","Y");
                        GuideActivity.this.startActivity(new Intent(GuideActivity.this,MainActivity.class));
                        GuideActivity.this.finish();
                    }
                });
            }
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return imgArray.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }

    }
}
