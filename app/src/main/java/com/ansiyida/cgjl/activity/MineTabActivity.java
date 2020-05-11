package com.ansiyida.cgjl.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.InterestBean;
import com.ansiyida.cgjl.bean.UpdateUserMsgBean;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.tab.ChannelItem;
import com.ansiyida.cgjl.tab.DragAdapter;
import com.ansiyida.cgjl.tab.DragGrid;
import com.ansiyida.cgjl.tab.OtherAdapter;
import com.ansiyida.cgjl.tab.OtherGridView;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.SketchLengthFilter;
import com.ansiyida.cgjl.view.SketchTextWatcher;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MineTabActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.finish_text)
    TextView finish_text;
    @Bind(R.id.editText)
    EditText editText;
    /**
     * 用户栏目的GRIDVIEW
     */
    private DragGrid userGridView;
    /**
     * 其它栏目的GRIDVIEW
     */
    private OtherGridView otherGridView;
    /**
     * 用户栏目对应的适配器，可以拖动
     */
    DragAdapter userAdapter;
    /**
     * 其它栏目对应的适配器
     */
    OtherAdapter otherAdapter;
    /**
     * 其它栏目列表
     */
    ArrayList<ChannelItem> otherChannelList = new ArrayList<ChannelItem>();
    /**
     * 用户栏目列表
     */
    ArrayList<ChannelItem> userChannelList = new ArrayList<ChannelItem>();
    private boolean flag = false;
    /**
     * 是否在移动，由于这边是动画结束后才进行的数据更替，设置这个限制为了避免操作太频繁造成的数据错乱。
     */
    boolean isMove = false;
    private DbMannager mannager;
    private boolean isExit=true;
    @Bind(R.id.submit)
    TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_mine_tab;
    }

    @Override
    protected void initView() {
        setStateColor(this, true);
        mannager = DbMannager.getInstance();
        userGridView = (DragGrid) findViewById(R.id.userGridView);
        otherGridView = (OtherGridView) findViewById(R.id.otherGridView);
        userGridView.setMove(true);
        editText.addTextChangedListener(new SketchTextWatcher(editText, 6));
        InputFilter[] filters = {new SketchLengthFilter(6)};
        editText.setFilters(filters);
        submit.setVisibility(View.GONE);
        userChannelList = new ArrayList<ChannelItem>();
        otherChannelList = new ArrayList<ChannelItem>();
        mannager.clearTable("mine_tab");
    }

    @Override
    protected void initData() {
        textTitle.setText("编辑标签");

    }

    @Override
    protected void httpData() {
        Call<InterestBean> call= MyApplication.getNetApi().getMyInterest((String) SharedPreferenceUtils.get(this,"app_token",""));
        call.enqueue(new Callback<InterestBean>() {
            @Override
            public void onResponse(Call<InterestBean> call, Response<InterestBean> response) {
                if (response.isSuccessful()) {
                    InterestBean body = response.body();
                    List<InterestBean.DataBean.MyBean> myList = body.getData().getMy();
                    List<InterestBean.DataBean.OtherBean> otherList = body.getData().getOther();
//                    userChannelList = mannager.getChoiceItem("mine_tab");
//                    otherChannelList = mannager.getDefaultItem("mine_tab");
                    if (myList != null) {
                        for (InterestBean.DataBean.MyBean myBean : myList) {
                            ChannelItem item = new ChannelItem(myBean.getJl_id(), myBean.getJl_id(), myBean.getJl_type() + "", myBean.getJl_name(), 0, 1);
                            userChannelList.add(item);
                        }
                        LogUtil.i("sql7", "myLength:" + userChannelList.size());
                    }

                    if (otherList != null) {
                        for (InterestBean.DataBean.OtherBean otherBean : otherList) {
                            ChannelItem item = new ChannelItem(otherBean.getJl_id(), otherBean.getJl_id(), otherBean.getJl_type() + "", otherBean.getJl_name(), 0, 0);
                            otherChannelList.add(item);

                        }
                        LogUtil.i("sql7", "otherLength:" + otherChannelList.size());

                    }

                    userAdapter = new DragAdapter(MineTabActivity.this, userChannelList, userGridView.getFlag(), mannager, MineTabActivity.this, "mine_tab");
                    userGridView.setAdapter(userAdapter);
                    otherAdapter = new OtherAdapter(MineTabActivity.this, otherChannelList, "mine_tab");
                    otherGridView.setAdapter(otherAdapter);
                    //设置GRIDVIEW的ITEM的点击监听
                    otherGridView.setOnItemClickListener(MineTabActivity.this);
                    userGridView.setOnItemClickListener(MineTabActivity.this);

                } else {
                    ToastUtils.showMessage(MineTabActivity.this, "解析错误1");
                }


                call.cancel();
            }

            @Override
            public void onFailure(Call<InterestBean> call, Throwable t) {
                ToastUtils.showMessage(MineTabActivity.this, "解析错误2");
                call.cancel();

            }
        });
    }

    @Override
    protected void setClickListener() {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String str = s.toString();
                if (!"".equals(str)) {
                    submit.setVisibility(View.VISIBLE);
                } else {
                    submit.setVisibility(View.GONE);

                }
            }
        });
    }

    @OnClick({R.id.image_back, R.id.submit})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.image_back://返回按钮
                saveChannel();
                break;
            case R.id.submit://自定义标签提交按钮
                String trim = editText.getText().toString().trim();
                if (trim != null && !"".equals(trim)) {
                    editText.setText("");
                    ChannelItem channel = new ChannelItem(0, trim, 0, 1);
                    userAdapter.addNewItem(channel);

                } else {
                    ToastUtils.showMessage(this, "请输入标签内容");
                }
                break;

            default:
                break;
        }
    }

    /**
     * GRIDVIEW对应的ITEM点击监听接口
     */
    @Override
    public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
        //如果点击的时候，之前动画还没结束，那么就让点击事件无效
        if (isMove) {
            return;
        }

        switch (parent.getId()) {
            case R.id.userGridView:
                boolean flag = userGridView.getFlag();
                //position为 0，1 的不可以进行任何操作
                if (!flag) {
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
                                    MoveAnim(moveImageView, startLocation, endLocation, channel, userGridView);
                                    userAdapter.setRemove(position);
                                } catch (Exception localException) {
                                }
                            }
                        }, 50L);
                    }
                }

                break;
            case R.id.otherGridView:
                final ImageView moveImageView = getView(view);
                if (moveImageView != null) {
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
                                MoveAnim(moveImageView, startLocation, endLocation, channel, otherGridView);
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

    /**
     * 点击ITEM移动动画
     *
     * @param moveView
     * @param startLocation
     * @param endLocation
     * @param moveChannel
     * @param clickGridView
     */
    private void MoveAnim(View moveView, int[] startLocation, int[] endLocation, final ChannelItem moveChannel,
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
     *
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
        ViewGroup moveViewGroup = (ViewGroup) getWindow().getDecorView();
        LinearLayout moveLinearLayout = new LinearLayout(this);
        moveLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        moveViewGroup.addView(moveLinearLayout);
        return moveLinearLayout;
    }

    /**
     * 获取点击的Item的对应View，
     *
     * @param view
     * @return
     */
    private ImageView getView(View view) {
        view.destroyDrawingCache();
        view.setDrawingCacheEnabled(true);
        Bitmap cache = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        ImageView iv = new ImageView(this);
        iv.setImageBitmap(cache);
        return iv;
    }

    /**
     * 退出时候保存选择后数据库的设置
     */
    private void saveChannel() {
        if (isExit){
            isExit=false;
            if (userAdapter!=null){
                userAdapter.saveSQL();
                List<ChannelItem> channnelLst = userAdapter.getChannnelLst();
                StringBuffer sb_strandId=new StringBuffer();
                StringBuffer sb_userId=new StringBuffer();
                for (ChannelItem chan:channnelLst){
                    LogUtil.i("ooo","id:"+chan.getId()+",name:"+chan.getName());
                    if (chan.getId()==0){
                        if (!"".equals(chan.getName())){
                            sb_userId.append(chan.getName()+",");
                        }
                    }else {
                        sb_strandId.append(chan.getId()+",");
                    }
                }
                String strandId=sb_strandId.toString();
                String userId=sb_userId.toString();
                if ("".equals(strandId)){
                    strandId="----";
                }
                if ("".equals(userId)){
                    userId="----";
                }
                LogUtil.i("oo2","strand:"+strandId+",userId:"+userId);
                Call<UpdateUserMsgBean> call=MyApplication.getNetApi().updatePersonalChannel((String)SharedPreferenceUtils.get(this,"app_token",""),strandId,userId);
                call.enqueue(new Callback<UpdateUserMsgBean>() {
                    @Override
                    public void onResponse(Call<UpdateUserMsgBean> call, Response<UpdateUserMsgBean> response) {
                        if (response.isSuccessful()){
                            if("0001".equals(response.body().getStatus())){

                            }else {
                                ToastUtils.showMessage(MineTabActivity.this,"上传失败");

                            }
                        }else {
                            ToastUtils.showMessage(MineTabActivity.this,"解析错误1");

                        }

                        MineTabActivity.this.finish();
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<UpdateUserMsgBean> call, Throwable t) {
                        ToastUtils.showMessage(MineTabActivity.this,"解析错误2");
                        MineTabActivity.this.finish();
                        call.cancel();

                    }
                });
            }else {
                this.finish();
            }
        }

//        otherAdapter.saveSQL();
    }

    public void changeText() {
        if (!userGridView.getFlag()) {
            finish_text.setText(R.string.tab_finish);
        } else {
            finish_text.setText(R.string.tab_edit2);

        }
    }

    @OnClick(R.id.finish_text)
    public void finishText() {
        if (!userGridView.getFlag()){
            userGridView.setFlag();
            userAdapter.changeItem(userGridView.getFlag());
        }
    }

    @Override
    public void onBackPressed() {
        saveChannel();
    }
}
