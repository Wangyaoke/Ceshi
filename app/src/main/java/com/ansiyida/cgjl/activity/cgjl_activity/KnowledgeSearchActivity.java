package com.ansiyida.cgjl.activity.cgjl_activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andview.refreshview.XRefreshView;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.cgjl_adapter.KnowledgeSearchAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.cgjl_bean.StudySearchBean;
import com.ansiyida.cgjl.bean.college_bean;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.cgjl_view.MyFloatLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KnowledgeSearchActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.edittext_search)
    EditText edittextSearch;
    @Bind(R.id.iv_search)
    ImageView ivSearch;
    @Bind(R.id.iv_delete)
    ImageView ivDelete;
    @Bind(R.id.relative)
    RelativeLayout relative;
    @Bind(R.id.noText)
    RelativeLayout noText;
    @Bind(R.id.search_recyclerView)
    RecyclerView searchRecyclerView;
    @Bind(R.id.MyFloatLayout)
    com.ansiyida.cgjl.view.cgjl_view.MyFloatLayout MyFloatLayout;
    @Bind(R.id.noText_image)
    ImageView noTextImage;
    @Bind(R.id.searchTake)
    RelativeLayout searchTake;
    @Bind(R.id.SearchTake_Del)
    ImageView SearchTakeDel;
    @Bind(R.id.no_search)
    RelativeLayout noSearch;
    @Bind(R.id.xrefreshView)
    XRefreshView xrefreshView;
    @Bind(R.id.qxSearch)
    TextView qxSearch;
    @Bind(R.id.back)
    ImageView back;
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.text_putOut)
    TextView textPutOut;
    private int pageNum = 1;
    private int pageCount = 10;
    private KnowledgeSearchAdapter knowledgeSearchAdapter;
    private List<StudySearchBean.DataBean.ListBean> list = new ArrayList<>();
    private List<String> searchMyFloat = new ArrayList<>();
    private String seachMyFloatStr;
    private String[] split;

    @Override
    protected int getContentView() {
        return R.layout.activity_knowledge_search;
    }

    @Override
    protected void initView() {
        knowledgeSearchAdapter = new KnowledgeSearchAdapter(list, this);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchRecyclerView.setAdapter(knowledgeSearchAdapter);

        xrefreshView.setPullLoadEnable(true);
        //允许下拉刷新
        xrefreshView.setPullRefreshEnable(true);
        back.setVisibility(View.INVISIBLE);
        imageBack.setVisibility(View.VISIBLE);
        textTitle.setText("学院");
        textPutOut.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        seachMyFloatStr = (String) SharedPreferenceUtils.get(KnowledgeSearchActivity.this, "SearchMyFloat", "");
        if (!seachMyFloatStr.equals("")) {
            split = seachMyFloatStr.split(",");
            for (int i = 0; i < split.length; i++) {
                if (i <= 20) {
                    if (!searchMyFloat.contains(split[i])) {
                        searchMyFloat.add(split[i]);
                    }
                }
            }
            if (searchMyFloat.size() > 0) {
                MyFloatLayout.setData((ArrayList<String>) searchMyFloat);
                SearchTakeDel.setVisibility(View.VISIBLE);
            } else {
                noSearch.setVisibility(View.VISIBLE);
            }
        } else {
            noSearch.setVisibility(View.VISIBLE);
        }

    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        back.setOnClickListener(this);
        qxSearch.setOnClickListener(this);
        ivDelete.setOnClickListener(this);
        SearchTakeDel.setOnClickListener(this);
        imageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        xrefreshView.setXRefreshViewListener(new XRefreshView.XRefreshViewListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                pageNum = 1;
                list.clear();
                httpseach(edittextSearch.getText().toString());

            }

            @Override
            public void onRefresh(boolean isPullDown) {

            }

            @Override
            public void onLoadMore(boolean isSilence) {
                //上拉加载
                pageNum++;
                httpseach(edittextSearch.getText().toString());
            }

            @Override
            public void onRelease(float direction) {

            }

            @Override
            public void onHeaderMove(double headerMovePercent, int offsetY) {

            }
        });
        //搜索列表收藏
        knowledgeSearchAdapter.setKnowledgeSearchListnear(new KnowledgeSearchAdapter.KnowledgeSearchListnear() {
            @Override
            public void collet(String id, int position) {
                Collet(id);
                list.get(position).setIsCollection(1);
            }

            @Override
            public void nocollet(String id, int position) {
                CancelCollet(id);
                list.get(position).setIsCollection(0);
            }
        });
        //流式布局回调
        MyFloatLayout.setMyFloatLayoutListnear(new MyFloatLayout.MyFloatLayoutListnear() {
            @Override
            public void onclick(String str) {
                edittextSearch.setText(str);
                searchTake.setVisibility(View.GONE);
                httpseach(str);
            }
        });
        edittextSearch.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 获得焦点
                    if (!"".equals(EditTextUtil.getEditTextStr(edittextSearch))) {
                        ivDelete.setVisibility(View.VISIBLE);
                    }
                    LogUtil.i("jiao", "获取焦点");
                    ivSearch.setClickable(true);

                } else {
                    // 失去焦点
                    LogUtil.i("jiao", "失去焦点");
                    ivSearch.setClickable(false);
                    ivDelete.setVisibility(View.GONE);
                }

            }


        });
        edittextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //这里是我要做的操作！
                    if (!edittextSearch.getText().toString().isEmpty()) {
                        noSearch.setVisibility(View.GONE);
                        seachMyFloatStr = edittextSearch.getText().toString() + "," + seachMyFloatStr;
                        SharedPreferenceUtils.put(KnowledgeSearchActivity.this, "SearchMyFloat", seachMyFloatStr);
                        split = seachMyFloatStr.split(",");
                        searchMyFloat.clear();
                        for (int i = 0; i < split.length; i++) {
                            if (i < 20) {
                                if (!searchMyFloat.contains(split[i])) {
                                    searchMyFloat.add(split[i]);
                                }
                            }
                        }
                        searchTake.setVisibility(View.GONE);
                        MyFloatLayout.removeChildView();
                        MyFloatLayout.setData((ArrayList<String>) searchMyFloat);
                        if (searchMyFloat.size() != 0) {
                            SearchTakeDel.setVisibility(View.VISIBLE);
                        } else {
                            noSearch.setVisibility(View.VISIBLE);
                        }
                        httpseach(edittextSearch.getText().toString());
                    } else {
                        ToastUtils.showMessage(KnowledgeSearchActivity.this, "查询内容可不能为空哦！");
                    }
                    return true;
                }
                return false;
            }
        });
        //编辑框输入监听
        edittextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String s1 = s.toString();
                if (!"".equals(s1)) {
                    ivDelete.setVisibility(View.VISIBLE);
                } else {
                    list.clear();
                    knowledgeSearchAdapter.setList(list, "");
                    noText.setVisibility(View.GONE);
                    ivDelete.setVisibility(View.GONE);
                    searchTake.setVisibility(View.VISIBLE);
                    xrefreshView.setVisibility(View.GONE);
                }
            }
        });
    }

    //查询数据
    public void httpseach(String searchText) {
        if (NetWorkUtils.isNetworkConnected(this)) {
            Call<StudySearchBean> call = MyApplication.getNetApi().getKnowledgeSearch((String) SharedPreferenceUtils.get(this, "app_token", ""), pageNum + "", pageCount + "", searchText);
            call.enqueue(new Callback<StudySearchBean>() {
                @Override
                public void onResponse(Call<StudySearchBean> call, Response<StudySearchBean> response) {
                    StudySearchBean body = response.body();
                    if (body != null) {
                        StudySearchBean.DataBean data = body.getData();
                        if (data != null) {
                            noSearch.setVisibility(View.GONE);
                            searchTake.setVisibility(View.GONE);
                            xrefreshView.setVisibility(View.VISIBLE);
                            noText.setVisibility(View.GONE);
                            list.addAll(data.getList());
                            Log.e("xxx", "onResponse: " + data.getList().toString());
                            knowledgeSearchAdapter.setList(list, edittextSearch.getText().toString());
                        } else {
                            if (pageNum == 1) {
                                noText.setVisibility(View.VISIBLE);
                                xrefreshView.setVisibility(View.GONE);
                            }
                        }
                    } else {
                        if (pageNum == 1) {
                            noText.setVisibility(View.VISIBLE);
                            xrefreshView.setVisibility(View.GONE);
                        }
                    }
                    xrefreshView.stopLoadMore();
                    xrefreshView.stopRefresh();
                    call.cancel();
                }

                @Override
                public void onFailure(Call<StudySearchBean> call, Throwable t) {
                    Log.e("KnowledgeSearchActivity", "onFailure: " + t.getMessage());
                }
            });
        } else {
            ToastUtils.showMessage(this, "请先检查一下您的网络状态！");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
        GoogleAssistant(KnowledgeSearchActivity.this,"Android学院搜索","KnowledgeSearchActivity");
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.iv_delete:
                edittextSearch.setText("");
                break;
            case R.id.qxSearch:
                finish();
                break;
            case R.id.SearchTake_Del:
                deleteHistory();
                backgroundAlpha(KnowledgeSearchActivity.this, (float) 0.5);
                break;
        }
    }

    private void deleteHistory() {
        View view = View.inflate(this,R.layout.delete_alert_layout,null);
        final PopupWindow popupWindow = new PopupWindow(view,this.getWindowManager().getDefaultDisplay().getWidth()/4*3,ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        TextView title = view.findViewById(R.id.delete_title);
        Button dismiss =view.findViewById(R.id.dismiss_btn);
        Button sure =view.findViewById(R.id.sure_btn);
        popupWindow.showAtLocation(view, Gravity.CENTER,0,0);

        title.setText("确定清空全部搜索记录？");
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferenceUtils.remove(KnowledgeSearchActivity.this, "SearchMyFloat");
                searchMyFloat.clear();
                MyFloatLayout.removeChildView();
                SearchTakeDel.setVisibility(View.GONE);
                noSearch.setVisibility(View.VISIBLE);
                seachMyFloatStr = "";
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(KnowledgeSearchActivity.this,1);
            }
        });
    }

    private void Collet(String id) {
        if ("true".equals((String) SharedPreferenceUtils.get(this, "vistor", ""))) {
            //收藏
            Call<college_bean> call = MyApplication.getNetApi().getCollectionRecord((String) SharedPreferenceUtils.get(KnowledgeSearchActivity.this, "app_token", ""), true, "knowledge", id);
            call.enqueue(new Callback<college_bean>() {
                @Override
                public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(KnowledgeSearchActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        knowledgeSearchAdapter.setList(list, edittextSearch.getText().toString());
                    }
                }

                @Override
                public void onFailure(Call<college_bean> call, Throwable t) {
                    Log.e("StudyOnActivity", "ColletonFailure: " + t.getMessage());
                }
            });
        } else {
            ToastUtils.showMessage(this, "请先登录");
        }
    }
    public void backgroundAlpha(Activity context, float bgAlpha) {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
    private void CancelCollet(String id) {
        if ("true".equals((String) SharedPreferenceUtils.get(this, "vistor", ""))) {
            //取消收藏
            Log.e("CancelCollet", (String) SharedPreferenceUtils.get(KnowledgeSearchActivity.this, "app_token", "") + "knowledge" + id);
            Call<college_bean> call = MyApplication.getNetApi().DELETECollectionRecord((String) SharedPreferenceUtils.get(KnowledgeSearchActivity.this, "app_token", ""), "knowledge", id);
            call.enqueue(new Callback<college_bean>() {
                @Override
                public void onResponse(Call<college_bean> call, Response<college_bean> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(KnowledgeSearchActivity.this, "取消收藏成功", Toast.LENGTH_SHORT).show();
                        knowledgeSearchAdapter.setList(list, edittextSearch.getText().toString());
                    }
                }

                @Override
                public void onFailure(Call<college_bean> call, Throwable t) {
                    Log.e("StudyOnActivity", "ColletonFailure: " + t.getMessage());
                }
            });
        } else {
            ToastUtils.showMessage(this, "请先登录");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        String knowSearchId = (String) SharedPreferenceUtils.get(KnowledgeSearchActivity.this, "KnowSearchId", "");
        Object knowSearchCollege = SharedPreferenceUtils.get(KnowledgeSearchActivity.this, "KnowSearchCollege","");
        if(!knowSearchId.equals("") && !knowSearchCollege.equals("")) {
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (knowSearchId.equals(list.get(i).getId()+"")) {
                        if(knowSearchCollege.equals("true")) {
                            list.get(i).setIsCollection(1);
                        }else{
                            list.get(i).setIsCollection(0);
                        }
                    }
                }
                knowledgeSearchAdapter.setList(list, edittextSearch.getText().toString());
            }
        }
    }
}
