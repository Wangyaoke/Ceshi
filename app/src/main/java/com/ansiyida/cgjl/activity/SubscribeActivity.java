package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.MyDingYueAdapter;
import com.ansiyida.cgjl.adapter.SubscribeViewPagerAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DYTypeBean;
import com.ansiyida.cgjl.bean.MyDYItemBean;
import com.ansiyida.cgjl.fragment.SubscribeFragment;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.NoPreloadViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeActivity extends BaseActivity {
    @Bind(R.id.text_title)
    TextView title;
    @Bind(R.id.recyclerView_subscribe)
    RecyclerView recyclerView;
    @Bind(R.id.viewPager_subscribeActivity)
    NoPreloadViewPager viewPager;
    @Bind(R.id.tv_voice)
    TextView tv_voice;
    @Bind(R.id.tv_myDY)
    TextView tv_myDY;
    private ArrayList<String> list;
    private ArrayList<Integer> list2;
    private MyDingYueAdapter adapterTop;
    private LinearLayoutManager manager;
    private ArrayList<SubscribeFragment> fragmentList;
    private SubscribeViewPagerAdapter viewPagerAdapter;
    private ArrayList<Integer> idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_subscribe;
    }

    @Override
    protected void initView() {
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected void initData() {
        title.setText("我的订阅");
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        adapterTop = new MyDingYueAdapter(this, list, list2);
        recyclerView.setAdapter(adapterTop);
        fragmentList = new ArrayList<>();
        idList = new ArrayList<>();
    }

    @Override
    protected void httpData() {
        //获取非自定义订阅
        Call<DYTypeBean> call = MyApplication.getNetApi().getDingYueTypes();
        call.enqueue(new Callback<DYTypeBean>() {
            @Override
            public void onResponse(Call<DYTypeBean> call, Response<DYTypeBean> response) {
                if (response.isSuccessful()) {

                    List<DYTypeBean.DataBean> data = response.body().getData();
                    if (data != null && data.size() > 0) {
                        int index = 0;
                        for (DYTypeBean.DataBean dataBean : data) {
                            list.add(dataBean.getJst_name());
                            if (index == 0) {
                                list2.add(0);
                            } else {
                                list2.add(1);
                            }
                            idList.add(dataBean.getJst_id());
                            fragmentList.add(SubscribeFragment.newInstance(index));
                            index++;
                        }
                        viewPagerAdapter = new SubscribeViewPagerAdapter(getSupportFragmentManager(), fragmentList, idList);
                        viewPager.setAdapter(viewPagerAdapter);
                        adapterTop.notifyDataSetChanged();
                    }

                }

                call.cancel();
            }

            @Override
            public void onFailure(Call<DYTypeBean> call, Throwable t) {
                call.cancel();
            }
        });
    }

    @Override
    protected void setClickListener() {
        viewPager.setOnPageChangeListener(new NoPreloadViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                clickItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 获取自定义订阅网络请求
     */
    private void httpGetMyDY() {
        Call<MyDYItemBean> call2 = MyApplication.getNetApi().getMyDYItem((String) SharedPreferenceUtils.get(this, "app_token", ""));
        call2.enqueue(new Callback<MyDYItemBean>() {
            @Override
            public void onResponse(Call<MyDYItemBean> call, Response<MyDYItemBean> response) {
                if (response.isSuccessful()) {
                    MyDYItemBean.DataBean data = response.body().getData();
                    if (data != null) {
                        List<MyDYItemBean.DataBean.ListBean> list = data.getList();
                        if (list != null && list.size() > 0) {
                            StringBuffer sb = new StringBuffer();
                            for (MyDYItemBean.DataBean.ListBean listBean : list) {
                                sb.append(listBean.getJms_name() + " / ");
                            }
                            tv_voice.setText("已创建订阅");
                            tv_myDY.setText(sb.toString());

                        } else {
                            tv_voice.setText("试试创建主题订阅");
                        }
                    } else {
                        ToastUtils.showMessage(SubscribeActivity.this, response.body().getMessage());
                        tv_voice.setText("试试创建主题订阅");
                    }
                } else {
                    tv_voice.setText("试试创建主题订阅");
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<MyDYItemBean> call, Throwable t) {
                call.cancel();
            }
        });
    }

    public void clickItem(int position) {
        int length = list2.size();
        for (int x = 0; x < length; x++) {
            if (x == position) {
                list2.set(x, 0);
            } else {
                list2.set(x, 1);
            }
        }
        int firstPosition = manager.findFirstVisibleItemPosition();
        int lastPosition = manager.findLastVisibleItemPosition();
        View childAt = recyclerView.getChildAt(position - firstPosition);
        View childAt1 = recyclerView.getChildAt(lastPosition - position);
        if (childAt != null && childAt1 != null) {
            int left = childAt.getLeft();
            int right = childAt1.getLeft();
            recyclerView.smoothScrollBy((left - right) / 2, 0);
            LogUtil.i("childAt", "childAt!=null");
        } else {
            recyclerView.scrollToPosition(position);
            LogUtil.i("childAt", "childAt==null");
        }
        int currentItem = viewPager.getCurrentItem();
        if (currentItem != position) {
            viewPager.setCurrentItem(position);
        }
        adapterTop.notifyDataSetChanged();
    }

    @OnClick({R.id.image_back, R.id.relative_top})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back:                    //1.返回上一级
                this.finish();
                break;
            case R.id.relative_top:                  //2.创建主题订阅
                Intent intent = new Intent(this, MyDYActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        httpGetMyDY();
    }
}
