package com.ansiyida.cgjl.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.BannerBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.NewNewsListBean;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.util.DpPxTools;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.FeedRootRecyclerView;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2017/11/8.
 */
public class NewsFragment extends BaseFragment {
    @Bind(R.id.recyclerView_newsFragment)
    FeedRootRecyclerView recyclerView;
    @Bind(R.id.xrefreshView_newsFragment)
    XRefreshView refreshView;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    private ArrayList<NewBean2> lists;
    private NewsOneAdapter adapter;
    private int index;
    private int position;
    private final MyHandler mHandler = new MyHandler(this);
    private RecyclerView.OnScrollListener listener;
    private DbMannager mannager;
    private String name;
    private String cid;
    private String pid;
    private int pageNum = 0;
    private HashMap<String, Object> tabMsg;
    private boolean adapterFlag = false;
    private int instance;
    private LinearLayoutManager mLayoutManager;
    private String share_title = "";
    private String share_des = "";
    private String share_img = "";
    private Bitmap myBitmap;

    /**
     * 使用静态的内部类，不会持有当前对象的引用
     */
    private static class MyHandler extends Handler {
        private final WeakReference<NewsFragment> mActivity;

        public MyHandler(NewsFragment activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity.get() == null) {
                return;
            }
            mActivity.get().todo(msg);
        }
    }

    /**
     * 处理消息
     *
     * @param msg 消息标记
     */
    private void todo(Message msg) {
        int what = msg.what;
    }

    public static NewsFragment newInstance(int position) {
        NewsFragment myFragment = new NewsFragment();
        myFragment.setPosition(position);
        return myFragment;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_news_fragment;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
//        loadingDialog.showDialog(getActivity(), "");
        instance = DpPxTools.dip2px(getActivity(), 300);
        mannager = DbMannager.getInstance();
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        if (lists == null) {
            LogUtil.i("fragm", "list==null,position:" + position);
            lists = new ArrayList<>();
        } else {
            LogUtil.i("fragm", "list!=null,position:" + position);
        }

        tabMsg = mannager.getTabMsg(position);
        String id = "1,94";
        if (tabMsg != null) {
            name = (String) tabMsg.get("name");
            id = (String) tabMsg.get("id");
            id.replace("，", ",");
        } else {
            name = "";
        }
        //新闻列表接口
        String[] split = id.split(",");
        cid = split[0];
        pid = split[1];
        LogUtil.i("name", "name:" + name + ",pid:" + pid + ",cid:" + cid);
        adapter = new NewsOneAdapter(lists, getActivity(), getActivity().getWindow());
        recyclerView.setAdapter(adapter);
        //允许加载更多
        refreshView.setPullLoadEnable(true);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(true);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(false);
        if (pageNum == 0) {
            refreshView.startRefresh();
        } else {
            if (lists.size() > 0) {
                String channelName = lists.get(0).getChannelName();
                if (channelName != null && !channelName.equals(name)) {
                    lists.clear();
                    adapter.notifyDataSetChanged();
                    refreshView.startRefresh();
                }
            }
        }
    }

    //分享回调
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            ToastUtils.showMessage(getActivity(), "分享成功");


        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            if (platform.toString().equals("QQ")) {
                ToastUtils.showMessage(getActivity(), "您未安装QQ客户端，无法分享");

            } else if (platform.toString().equals("QZONE")) {

                ToastUtils.showMessage(getActivity(), "您未安装QQ客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN")) {

                ToastUtils.showMessage(getActivity(), "您未安装微信客户端，无法分享");


            } else if (platform.toString().equals("WEIXIN_CIRCLE")) {

                ToastUtils.showMessage(getActivity(), "您未安装微信客户端，无法分享");
            } else {
                ToastUtils.showMessage(getActivity(), "分享失败,msg:" + t.getMessage());

            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            ToastUtils.showMessage(getActivity(), "分享取消");

        }
    };

    @OnClick({R.id.iv_repeat})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_repeat:                //1.点击重新加载
                iv_repeat.setClickable(false);
                notifyData();
                break;
        }
    }

    private int getScollYDistance() {
        int position = mLayoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = mLayoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtil.i("activity", "------------onPause-------------" + position);
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.i("activity", "------------onResume-------------" + position);
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i("activity", "------------onStart-------------" + position);

    }

    public int firstVisible = 0, visibleCount = 0, totalCount = 0;

    @Override
    protected void initData() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                notifyData();
            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                loadMore();
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });


        listener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    JCVideoPlayer.releaseAllVideos();
                    // 第一个可见位置
                    int firstItem = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(0));
// 最后一个可见位置
                    int lastItem = recyclerView.getChildLayoutPosition(recyclerView.getChildAt(recyclerView.getChildCount() - 1));
                    int childCount = recyclerView.getChildCount();
                    LogUtil.i("videoTest2", "firstItem:" + firstItem + "---------lastItem:" + lastItem + ",childCount:" + childCount);
                    int length = lastItem - firstItem + 1;
                    boolean flag = true;
                    for (int x = 0; x < length; x++) {
                        View childAt = recyclerView.getChildAt(x);
                        if (childAt != null) {
                            JCVideoPlayerStandard player = (JCVideoPlayerStandard) childAt.findViewById(R.id.videoPlayer);
                            if (player.currentState == 2) {
                                flag = false;
                                break;
                            }
                        }
                    }
                    if (flag) {
                        JCVideoPlayer.releaseAllVideos();
                    }
                }
            }


            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

            }
        };
        if ("视频".equals(name)) {
            recyclerView.addOnScrollListener(listener);
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            LogUtil.i("cyx1", "隐藏界面");
            JCVideoPlayer.releaseAllVideos();

        } else {
            LogUtil.i("cyx1", "显示界面");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        LogUtil.i("des", "onDestroyView:" + position);

        unbindDrawables(recyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.i("des", "destroy:" + position);
        JCVideoPlayer.releaseAllVideos();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        if (lists != null) {
            lists.clear();
            lists = null;
        }
        if (tabMsg != null) {
            tabMsg.clear();
        }
        LogUtil.i("activity", "------------destroy-------------" + position);

    }

    private void unbindDrawables(View view) {
        if (view.getBackground() != null) {
            view.getBackground().setCallback(null);
        }
        if (view instanceof ViewGroup && !(view instanceof AdapterView)) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                unbindDrawables(((ViewGroup) view).getChildAt(i));
            }
            ((ViewGroup) view).removeAllViews();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        JCVideoPlayer.releaseAllVideos();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
        LogUtil.i("des", "onStop:" + position);
    }

    /**
     * 点击tab刷新当前页面
     */
    public void btnRefresh() {
        if (refreshView != null) {
            refreshView.startRefresh();
        }
    }

    public void notifyData() {
        pageNum = 1;
        LogUtil.i("con", "pid:" + pid + ",cid:" + cid + ",pageNum:" + pageNum + ",pageCount:20,interst:" + mannager.getInterst());
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
//            Call<NewsListBean> call2 = MyApplication.getNetApi().getNewListByType(pid, cid, pageNum + "", "20", (String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), mannager.getInterst());
        //    Call<NewNewsListBean> call2 = MyApplication.getNetApi().getcaigou("" , "", "", "", "", "","","");

           Call<NewNewsListBean> call2 = MyApplication.getNetApi().getNewNewsList(pageNum + "", pid, cid, (String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), mannager.getInterst());
            call2.enqueue(new Callback<NewNewsListBean>() {
                @Override
                public void onResponse(Call<NewNewsListBean> call, Response<NewNewsListBean> response) {
                    if (response.isSuccessful()) {
                        List<NewNewsListBean.DataBean> data = response.body().getData();
                        lists.clear();
                        if (data != null && data.size() > 0) {
                            LogUtil.i("json3", "content.size():" + data.size());
                            for (NewNewsListBean.DataBean dataBean : data) {
                                NewBean2 newBean = new NewBean2();
                                newBean.setComnum("0");//评论数（目前只有研讨需要这个字段）
                                newBean.setArtype(dataBean.getType());//新闻类型
                                newBean.setAuthor(dataBean.getSourceName());//来源
                                newBean.setVideoUrl(dataBean.getVideoLink() + "");//视频地址
                                newBean.setDateformat(dataBean.getPublishDate());//时间
                                newBean.setDes(dataBean.getSummary());//描述
                                newBean.setHeadurl(dataBean.getSourceLogo());//头像Url
                                newBean.setId(dataBean.getId() + "");//id
                                String[] imgs = dataBean.getImgs();
                                if (imgs != null && imgs.length > 0) {
                                    newBean.setImg(imgs[0]);//新闻列表缩略图
                                } else {
                                    newBean.setImg("");
                                }
                                newBean.setImgArray(imgs);//图片数组
                                newBean.setTitle(dataBean.getTitle());
                                newBean.setVtime(dataBean.getVideoTime() + "");
                                newBean.setChannelName(name);
                                String isTop = dataBean.getIsTop();
                                if ("Y".equals(isTop)) {
                                    newBean.setZhiDing("yes");
                                } else {
                                    newBean.setZhiDing("no");
                                }
                                if ("热点".equals(name) || "推荐".equals(name)) {
                                    newBean.setClickNum(dataBean.getThumbsUpNum() + "");//点赞数（目前只有研讨需要这个字段）
                                }
                                if ("视频".equals(name)) {
                                    newBean.setIsVideo("是");
                                } else {
                                    newBean.setIsVideo("否");
                                }
                                lists.add(newBean);
                            }
                            iv_empty.setVisibility(View.GONE);

                        } else {
                            iv_empty.setVisibility(View.VISIBLE);

                        }
                        if ("XXXX".equals(name)) {//此处决定是否显示Banner(将‘XXXX’换成‘热点’就会显示Banner)
                            Call<BannerBean> call2 = MyApplication.getNetApi().getBanner("1");
                            call2.enqueue(new Callback<BannerBean>() {
                                @Override
                                public void onResponse(Call<BannerBean> call, Response<BannerBean> response) {
                                    if (response.isSuccessful()) {
                                        NewBean2 newBean2 = new NewBean2();
                                        newBean2.setBannerBean(response.body());
                                        newBean2.setArtype("热点");
                                        lists.add(0, newBean2);
                                        adapter.notifyDataSetChanged();
                                        adapterFlag = true;
                                    }
                                    call.cancel();
                                }

                                @Override
                                public void onFailure(Call<BannerBean> call, Throwable t) {

                                    call.cancel();
                                }
                            });

                        } else {
                            adapter.notifyDataSetChanged();
                        }
                    } else {
                        iv_empty.setVisibility(View.VISIBLE);
                        LogUtil.i("json3", "response is fail");
                    }
                    call.cancel();
                    iv_repeat.setVisibility(View.GONE);
                    refreshView.stopRefresh();
                    iv_repeat.setClickable(true);

                }

                @Override
                public void onFailure(Call<NewNewsListBean> call, Throwable t) {
                    LogUtil.i("json3", "onFailure");
                    iv_repeat.setClickable(true);
                    refreshView.stopRefresh();
                    iv_repeat.setVisibility(View.VISIBLE);
                    iv_empty.setVisibility(View.GONE);
                    call.cancel();
                }
            });
        } else {
            ToastUtils.showMessage(getActivity(), "当前网络不可用");
            iv_repeat.setClickable(true);
            refreshView.stopRefresh();
            iv_repeat.setVisibility(View.VISIBLE);
            iv_empty.setVisibility(View.GONE);
        }
    }

    private void loadMore() {
        pageNum++;
        LogUtil.i("page", "pageNum:" + pageNum);
//        Call<NewsListBean> call2 = MyApplication.getNetApi().getNewListByType(pid, cid, pageNum + "", "20", (String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), mannager.getInterst());
        Call<NewNewsListBean> call2 = MyApplication.getNetApi().getNewNewsList(pageNum + "", pid, cid, (String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), mannager.getInterst());
        call2.enqueue(new Callback<NewNewsListBean>() {
            @Override
            public void onResponse(Call<NewNewsListBean> call, Response<NewNewsListBean> response) {
                if (response.isSuccessful()) {
                    List<NewNewsListBean.DataBean> data = response.body().getData();
                    if (data != null && data.size() > 0) {
                        for (NewNewsListBean.DataBean dataBean : data) {
                            NewBean2 newBean = new NewBean2();
                            newBean.setComnum("0");//评论数（目前只有研讨需要这个字段）
                            newBean.setArtype(dataBean.getType());//新闻类型
                            newBean.setAuthor(dataBean.getSourceName());//来源
                            newBean.setVideoUrl(dataBean.getVideoLink() + "");//视频地址
                            newBean.setDateformat(dataBean.getPublishDate());//时间
                            newBean.setDes(dataBean.getSummary());//描述
                            newBean.setHeadurl(dataBean.getSourceLogo());//头像Url
                            newBean.setId(dataBean.getId() + "");//id
                            String[] imgs = dataBean.getImgs();
                            if (imgs != null && imgs.length > 0) {
                                newBean.setImg(imgs[0]);//新闻列表缩略图
                            } else {
                                newBean.setImg("");
                            }
                            newBean.setImgArray(imgs);//图片数组
                            newBean.setTitle(dataBean.getTitle());
                            newBean.setVtime(dataBean.getVideoTime() + "");
                            newBean.setChannelName(name);
                            String isTop = dataBean.getIsTop();
                            if ("Y".equals(isTop)) {
                                newBean.setZhiDing("yes");
                            } else {
                                newBean.setZhiDing("no");
                            }
                            if ("热点".equals(name) || "推荐".equals(name)) {
                                newBean.setClickNum(dataBean.getThumbsUpNum() + "");//点赞数（目前只有研讨需要这个字段）
                            }
                            if ("视频".equals(name)) {
                                newBean.setIsVideo("是");
                            } else {
                                newBean.setIsVideo("否");
                            }
                            lists.add(newBean);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        ToastUtils.showMessage(getActivity(), "已加载到底部");
                        pageNum--;
                    }

                } else {
                    LogUtil.i("json3", "response is fail");
                }
                refreshView.stopLoadMore();
                call.cancel();
            }

            @Override
            public void onFailure(Call<NewNewsListBean> call, Throwable t) {
                LogUtil.i("json3", "onFailure");
                refreshView.stopLoadMore();
                call.cancel();
            }
        });
    }


}
