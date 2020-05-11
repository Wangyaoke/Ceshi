package com.ansiyida.cgjl.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.SearchBean;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2017/11/8.
 */
public class SearchFragment extends BaseFragment {
    @Bind(R.id.recyclerView_newsFragment)
    RecyclerView recyclerView;
    @Bind(R.id.xrefreshView_newsFragment)
    XRefreshView refreshView;
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;
    private ArrayList<NewBean2> lists;
    private NewsOneAdapter adapter;
    private int index;
    private int position;
    private final MyHandler mHandler = new MyHandler(this);
    private RecyclerView.OnScrollListener listener;
    private DbMannager mannager;
    private int pageNum = 1;
    private int pageCount = 10;
    private String newType;

    /**
     * 使用静态的内部类，不会持有当前对象的引用
     */
    private static class MyHandler extends Handler {
        private final WeakReference<SearchFragment> mActivity;

        public MyHandler(SearchFragment activity) {
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
        int ms = msg.what;
    }

    public static SearchFragment newInstance(int position, String searchStr) {
        SearchFragment myFragment = new SearchFragment();
        myFragment.setPosition(position);
        myFragment.setNewType(searchStr);
        return myFragment;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setNewType(String newType) {
        this.newType = newType;

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
        mannager = DbMannager.getInstance();
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        lists = new ArrayList<>();
        adapter = new NewsOneAdapter(lists, getActivity(),getActivity().getWindow());
        recyclerView.setAdapter(adapter);
//        notifyData();
        //允许加载更多
        refreshView.setPullLoadEnable(false);
        //允许下拉刷新
        refreshView.setPullRefreshEnable(false);
        //滑动到底部自动加载更多
        refreshView.setAutoLoadMore(true);
//        switch (position){
//            case 0:
//                newType="N";
//
//                break;
//            case 1:
//                newType="S";
//
//                break;
//            case 2:
//                newType="T";
//
//                break;
//            case 3:
//                newType="C";
//
//                break;
//            case 4:
//                newType="D";
//
//                break;
//        }
        httpData();
    }

    @Override
    protected void initData() {
        refreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
//                mHandler.sendEmptyMessageDelayed(200, 1000);
                httpData();
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
                    JCVideoPlayer.releaseAllVideos();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        };
        recyclerView.addOnScrollListener(listener);

        iv_repeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpData();
            }
        });
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
//    private long timeBegin=0;
   public void httpData() {
      /*   final String searchString = (String) SharedPreferenceUtils.get(getActivity(), "searchString", "---");
        LogUtil.i("share", "searchString:" + searchString + ",position:" + position + ",type:" + newType);
        lists.clear();
        adapter.notifyDataSetChanged();
        pageNum = 1;
        if (!"---".equals(searchString)) {
//            timeBegin=System.currentTimeMillis();
            Call<SearchBean> call = MyApplication.getNetApi().getSearchList(searchString, newType, pageNum + "", pageCount + "",(String) SharedPreferenceUtils.get(getActivity(),"app_token",""));
            LogUtil.i("hhh","sechstring:"+searchString+",newtype:"+newType+",pageNum:"+pageNum+",pageCount:"+pageCount+",app:"+(String) SharedPreferenceUtils.get(getActivity(),"app_token",""));
            call.enqueue(new Callback<SearchBean>() {
                @Override
                public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                    if (response.isSuccessful()) {
//                        LogUtil.i("time","搜索字段:“"+searchString+"”,类型:“"+newType+"”,总计用时:"+(System.currentTimeMillis()-timeBegin)+"毫秒");
                        lists.clear();
                        SearchBean body = response.body();
                        String status = body.getStatus();
                        List<SearchBean.DataBean.ContentBean> contentList = body.getData().getContent();
                        LogUtil.i("log","size:"+contentList.size());
                        if (contentList != null && contentList.size() > 0) {
                            for (SearchBean.DataBean.ContentBean contentBean : contentList) {
                                NewBean2 newBean = new NewBean2();
                                newBean.setArtype(contentBean.getSelftype());
                                newBean.setAuthor(contentBean.getSource());
                                newBean.setComnum(contentBean.getComnum() + "");
                                newBean.setContent(contentBean.getContent());
                                String isusetime = contentBean.getIsusetime();
                                if (isusetime != null && isusetime.length() > 10) {
                                    isusetime = isusetime.substring(0, 10);
                                }
                                newBean.setDateformat(isusetime);
                                newBean.setId(contentBean.getPkey() + "");
                                newBean.setImg(contentBean.getImgpath());
                                if (contentBean.getImgArray()!=null){
                                    newBean.setImgArray(contentBean.getImgArray());
                                }
                                String translateTitle = contentBean.getTranslateTitle();
                                if (TextUtils.isEmpty(translateTitle)){
                                    translateTitle=contentBean.getTitle();
                                }
                                newBean.setTitle(translateTitle);
                                newBean.setVtime(contentBean.getVtime());
                                lists.add(newBean);
                            }
                            adapter.notifyDataSetChanged();
                            iv_repeat.setVisibility(View.GONE);
                            iv_empty.setVisibility(View.GONE);
                        }else {
                            iv_repeat.setVisibility(View.GONE);
                            iv_empty.setVisibility(View.VISIBLE);
                        }


                    } else {
                        LogUtil.i("share", "isFauile");
                        iv_repeat.setVisibility(View.VISIBLE);
                        iv_empty.setVisibility(View.GONE);
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<SearchBean> call, Throwable throwable) {
                    //无内容
                    lists.clear();
                    LogUtil.i("share", "onFailure");
                    adapter.notifyDataSetChanged();
                    iv_repeat.setVisibility(View.GONE);
                    iv_empty.setVisibility(View.VISIBLE);
                    call.cancel();
                }
            });
        }*/

    }


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser){
//            LogUtil.i("share","显示：shareString:"+searchStr);
//        }else {
//            LogUtil.i("share","隐藏：shareString:"+searchStr);
//
//        }
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        JCVideoPlayer.releaseAllVideos();
        mHandler.removeCallbacksAndMessages(null);
        if (lists!=null){
            lists.clear();
            lists=null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        JCVideoPlayer.releaseAllVideos();
    }


    private void loadMore() {
    /*    pageNum++;
        LogUtil.i("page", "pageNum:" + pageNum);
        String searchString = (String) SharedPreferenceUtils.get(getActivity(), "searchString", "---");
        if (!"---".equals(searchString)) {
            Call<SearchBean> call = MyApplication.getNetApi().getSearchList(searchString, newType, pageNum + "", pageCount + "", (String) SharedPreferenceUtils.get(getActivity(),"app_token",""));
            call.enqueue(new Callback<SearchBean>() {
                @Override
                public void onResponse(Call<SearchBean> call, Response<SearchBean> response) {
                    if (response.isSuccessful()) {
                        SearchBean body = response.body();
                        String status = body.getStatus();
                        List<SearchBean.DataBean.ContentBean> contentList = body.getData().getContent();
                        if (contentList != null && contentList.size() > 0) {
                            for (SearchBean.DataBean.ContentBean contentBean : contentList) {
                                NewBean2 newBean = new NewBean2();
                                newBean.setArtype(contentBean.getSelftype());
                                newBean.setAuthor(contentBean.getSource());
                                newBean.setComnum(contentBean.getComnum() + "");
                                newBean.setContent(contentBean.getContent());
                                String isusetime = contentBean.getIsusetime();
                                if (isusetime != null && isusetime.length() > 10) {
                                    isusetime = isusetime.substring(0, 10);
                                }
                                if (contentBean.getImgArray()!=null){
                                    newBean.setImgArray(contentBean.getImgArray());
                                }
                                newBean.setDateformat(isusetime);
                                newBean.setId(contentBean.getPkey() + "");
                                newBean.setImg(contentBean.getImgpath());
                                String translateTitle = contentBean.getTranslateTitle();
                                if (TextUtils.isEmpty(translateTitle)){
                                    translateTitle=contentBean.getTitle();
                                }
                                newBean.setTitle(translateTitle);
                                newBean.setVtime(contentBean.getVtime());
                                lists.add(newBean);
                            }
                            adapter.notifyDataSetChanged();
                        }


                    } else {
                        LogUtil.i("share", "isFauile");

                    }
                    refreshView.stopLoadMore();
                }

                @Override
                public void onFailure(Call<SearchBean> call, Throwable throwable) {
                    //无内容
                    ToastUtils.showMessage(getActivity(), "无数据可加载");
                    refreshView.stopLoadMore();
                }
            });
        }*/
    }


}
