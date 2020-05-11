package com.ansiyida.cgjl.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MainActivity;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.tab.ChannelItem;
import com.ansiyida.cgjl.tab.DragAdapter;
import com.ansiyida.cgjl.tab.DragGrid;
import com.ansiyida.cgjl.tab.OtherAdapter;
import com.ansiyida.cgjl.tab.OtherGridView;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.view.BottomToTopFinishLayout;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2017/12/14.
 */
public class  ChannalFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @Bind(R.id.finish_text)
    TextView finish_text;
    /** 用户栏目的GRIDVIEW */
    @Bind(R.id.userGridView)
    DragGrid userGridView;
    /** 其它栏目的GRIDVIEW */
    @Bind(R.id.otherGridView)
    OtherGridView otherGridView;
    /** 用户栏目对应的适配器，可以拖动 */

    DragAdapter userAdapter;
    /** 其它栏目对应的适配器 */
    OtherAdapter otherAdapter;
    /** 其它栏目列表 */
    ArrayList<ChannelItem> otherChannelList = new ArrayList<ChannelItem>();
    /** 用户栏目列表 */
    ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    private boolean flag=false;
    /** 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。 */
    boolean isMove = false;
    private DbMannager mannager;
    private Animation translate;
    @Bind(R.id.root_view)
    BottomToTopFinishLayout finishLayout;
    private MainActivity activity;
    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.subscribe_activity;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        activity= (MainActivity) getActivity();
        mannager = DbMannager.getInstance();
        translate = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_bottom_to_top);
        finishLayout.setOnFinishListener(new BottomToTopFinishLayout.OnFinishListener() {
            @Override
            public void onFinish() {
//              activity.cancelTab();
//				overridePendingTransition(R.anim.defaul2, R.anim.anim_bottom_to_top);
                cancelFragment();
            }
        });
//        fragmentScrollTop();

    }
    public void cancelFragment(){
        activity.setReturn(false);
        saveChannel();
        String localChannel= (String) SharedPreferenceUtils.get(getActivity(),"localChannel","");
        if (!"".equals(localChannel)){
            int channelisChooice = mannager.getChannelisChooice(localChannel);
            if (channelisChooice>0){
                channelisChooice--;
            }
            activity.updateData(channelisChooice);
        }else {
            activity.updateData(0);
        }
    }
    public void fragmentScrollTop(){
        finishLayout.scrollTop();

    }
    public void fragmentScrollOrigin(){
        finishLayout.scrollOrigin();
        showFragment();
        userChannelList.clear();
        otherChannelList.clear();
        userChannelList.addAll(mannager.getChoiceItem("channel2"));
        otherChannelList.addAll(mannager.getDefaultItem("channel2"));
        userAdapter.notifyDataSetChanged();
        otherAdapter.notifyDataSetChanged();
    }
    public void showFragment(){
        userAdapter.changeItem(true);
        boolean flag = userGridView.getFlag();
        if (!flag){
            userGridView.setFlag();
        }
    }

    @Override
    protected void initData() {
        userChannelList = mannager.getChoiceItem("channel2");
        otherChannelList = mannager.getDefaultItem("channel2");
        userAdapter = new DragAdapter(getActivity(), userChannelList,userGridView.getFlag(),mannager,this,"channel2");
        userGridView.setAdapter(userAdapter);
        otherAdapter = new OtherAdapter(getActivity(), otherChannelList,"channel2");
        otherGridView.setAdapter(this.otherAdapter);
        //设置GRIDVIEW的ITEM的点击监听
        otherGridView.setOnItemClickListener(this);
        userGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        //如果点击的时候，之前动画还没结束，那么就让点击事件无效
        if(isMove){
            return;
        }

        switch (parent.getId()) {
            case R.id.userGridView:
                boolean flag=userGridView.getFlag();
                //position为 0，1 的不可以进行任何操作
                if (!flag){
                    if (position != 0 && position != 1&& position != 2) {//-------------------------------------------------------------------改动处6
                        final ImageView moveImageView = getView(view);
                        if (moveImageView != null) {
                            TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                            final int[] startLocation = new int[2];
                            newTextView.getLocationInWindow(startLocation);
                            final ChannelItem channel = ((DragAdapter) parent.getAdapter()).getItem(position);//获取点击的频道内容
                            otherAdapter.setVisible(false);
                            //添加到最后一个
                            otherAdapter.addItem(channel);
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    try {
                                        int[] endLocation = new int[2];
                                        //获取终点的坐标
                                        otherGridView.getChildAt(otherGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                        MoveAnim(moveImageView, startLocation , endLocation, channel,userGridView);
                                        userAdapter.setRemove(position);
                                    } catch (Exception localException) {
                                    }
                                }
                            }, 50L);
                        }
                    }
                }else {
                    MainActivity activity = (MainActivity) getActivity();
//                    activity.moveChannel(position);
                    fragmentScrollTop();
                    activity.setReturn(false);
                    saveChannel();
                    activity.updateData(position);
                }

                break;
            case R.id.otherGridView:
                final ImageView moveImageView = getView(view);
                if (moveImageView != null){
                    TextView newTextView = (TextView) view.findViewById(R.id.text_item);
                    final int[] startLocation = new int[2];
                    newTextView.getLocationInWindow(startLocation);
                    final ChannelItem channel = ((OtherAdapter) parent.getAdapter()).getItem(position);
                    userAdapter.setVisible(false);
                    //添加到最后一个
                    userAdapter.addItem(channel);
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            try {
                                int[] endLocation = new int[2];
                                //获取终点的坐标
                                userGridView.getChildAt(userGridView.getLastVisiblePosition()).getLocationInWindow(endLocation);
                                MoveAnim(moveImageView, startLocation , endLocation, channel,otherGridView);
                                otherAdapter.setRemove(position);
                            } catch (Exception localException) {
                            }
                        }
                    }, 50L);
                }
                break;
            default:
                break;
        }

    }
    @OnClick(R.id.finish_text)
    public void finishText(){
        userGridView.setFlag();
        userAdapter.changeItem(userGridView.getFlag());
    }
    public void changeText(){
        if (!userGridView.getFlag()){
            finish_text.setText(R.string.tab_finish);
        }else {
            finish_text.setText(R.string.tab_edit);
        }
    }

    @OnClick(R.id.delete_tab)
    public void moveToTop(){
        fragmentScrollTop();
        cancelFragment();
    }

    /**
     * 点击ITEM移动动画
     * @param moveView
     * @param startLocation
     * @param endLocation
     * @param moveChannel
     * @param clickGridView
     */
    private void MoveAnim(View moveView, int[] startLocation,int[] endLocation, final ChannelItem moveChannel,
                          final GridView clickGridView) {
        int[] initLocation = new int[2];
        //获取传递过来的VIEW的坐标
        moveView.getLocationInWindow(initLocation);
        //得到要移动的VIEW,并放入对应的容器中
        final ViewGroup moveViewGroup = getMoveViewGroup();
        final View mMoveView = getMoveView(moveViewGroup, moveView, initLocation);
        //创建移动动画
        TranslateAnimation moveAnimation = new TranslateAnimation(
                startLocation[0], endLocation[0], startLocation[1],
                endLocation[1]);
        moveAnimation.setDuration(300L);//动画时间
        //动画配置
        AnimationSet moveAnimationSet = new AnimationSet(true);
        moveAnimationSet.setFillAfter(false);//动画效果执行完毕后，View对象不保留在终止的位置
        moveAnimationSet.addAnimation(moveAnimation);
        mMoveView.startAnimation(moveAnimationSet);
        moveAnimationSet.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {
                isMove = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                moveViewGroup.removeView(mMoveView);
                // instanceof 方法判断2边实例是不是一样，判断点击的是DragGrid还是OtherGridView
                if (clickGridView instanceof DragGrid) {
                    otherAdapter.setVisible(true);
                    otherAdapter.notifyDataSetChanged();
                    userAdapter.remove();
                } else {
                    userAdapter.setVisible(true);
                    userAdapter.notifyDataSetChanged();
                    otherAdapter.remove();
                }
                isMove = false;
            }
        });
    }

    /**
     * 获取移动的VIEW，放入对应ViewGroup布局容器
     * @param viewGroup
     * @param view
     * @param initLocation
     * @return
     */
    private View getMoveView(ViewGroup viewGroup, View view, int[] initLocation) {
        int x = initLocation[0];
        int y = initLocation[1];
        viewGroup.addView(view);
        LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mLayoutParams.leftMargin = x;
        mLayoutParams.topMargin = y;
        view.setLayoutParams(mLayoutParams);
        return view;
    }

    /**
     * 创建移动的ITEM对应的ViewGroup布局容器
     */
    private ViewGroup getMoveViewGroup() {
        ViewGroup moveViewGroup = (ViewGroup) getActivity().getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(getActivity());
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }

    /**
     * 获取点击的Item的对应View，
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(getActivity());
        iv.setImageBitmap(cache);
        return iv;
    }

    /** 退出时候保存选择后数据库的设置  */
    public void saveChannel() {
        //ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).deleteAllChannel();
        //ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).saveUserChannel(userAdapter.getChannnelLst());
        //ChannelManage.getManage(AppApplication.getApp().getSQLHelper()).saveOtherChannel(otherAdapter.getChannnelLst());
        userAdapter.saveSQL();
        otherAdapter.saveSQL();
        String idString=mannager.getChoiceChannelId("channel2");
        LogUtil.i("json3", "我传的是："+idString);

        if (idString!=null){
            Call<ResponseBody> call = MyApplication.getNetApi().uploadChannel(idString,(String) SharedPreferenceUtils.get(getActivity(), "app_token", ""));
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        LogUtil.i("json3", "json:" + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                        LogUtil.i("json3", "catch");

                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    LogUtil.i("json3", "onFailure");

                    call.cancel();
                }
            });
        }else {
            LogUtil.i("json3", "idString==null");

        }

    }

//    @Override
//    public void onBackPressed() {
//        saveChannel();
//        finishLayout.startAnimation(translate);
//        translate.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                translate.cancel();
////                ChannelActivity.this.finish();
////                overridePendingTransition(R.anim.defaul2,R.anim.defaul2);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
//    }
}
