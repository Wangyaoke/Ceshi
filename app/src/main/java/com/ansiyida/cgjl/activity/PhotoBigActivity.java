package com.ansiyida.cgjl.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.Fragment1ViewPagerAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.fragment.PhotoBigFragment;
import com.ansiyida.cgjl.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class PhotoBigActivity extends BaseActivity {
    @Bind(R.id.viewPager_photoBigActivity)
    ViewPager viewPager;
    @Bind(R.id.tv_number)
    TextView tv_number;
    @Bind(R.id.tv_save)
    TextView tv_save;
    private ArrayList<Fragment> lists;
    private String[] split;
    private int lenth;
    private int localPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_photo_big;
    }

    @Override
    protected void initView() {
        lists=new ArrayList<>();
    }

    @Override
    protected void initData() {
        String picString=getIntent().getStringExtra("picString");
        int fromPosition=getIntent().getIntExtra("fromPosition", 0);
        if (picString!=null&&!"".equals(picString)){
            split = picString.split(",");
            lenth = split.length;
            for (String picUrl: split){
                PhotoBigFragment fragment=new PhotoBigFragment();
                Bundle bundle=new Bundle();
                bundle.putString("picUrl",picUrl);
                fragment.setArguments(bundle);
                lists.add(fragment);
            }
            tv_number.setText((fromPosition+1)+"/"+lenth);
            viewPager.setAdapter(new Fragment1ViewPagerAdapter(getSupportFragmentManager(),lists));
            viewPager.setCurrentItem(fromPosition);

        }else {
            ToastUtils.showMessage(this, "未知错误");
            this.finish();
        }
    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                localPosition=position;
                tv_number.setText((position+1)+"/"+lenth);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @OnClick({R.id.tv_save})
    public void click(View view){
        switch (view.getId()){
            case R.id.tv_save:                  //1.保存图片按钮
                Glide.with(this).load(split[localPosition]).asBitmap().into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                        ToastUtils.showMessage(PhotoBigActivity.this, "保存成功");
                        MediaStore.Images.Media.insertImage(PhotoBigActivity.this.getContentResolver(), resource, "title", "description");
                        resource.recycle();
                    }
                });
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (lists!=null){
            lists.clear();
            lists=null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
        this.overridePendingTransition(R.anim.defaul2, R.anim.defaul);

    }
}
