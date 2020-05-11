package com.ansiyida.cgjl.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ansiyida.cgjl.MainActivity;
import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.adapter.ChildAdapter;
import com.ansiyida.cgjl.adapter.GroupAdapter;
import com.ansiyida.cgjl.adapter.NewsOneAdapter;
import com.ansiyida.cgjl.adapter.SearchRecordAdapter;
import com.ansiyida.cgjl.adapter.cgjl_adapter.PurchaseSearchAdapter;
import com.ansiyida.cgjl.adapter.cgjl_adapter.TitleRecyclerAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.DefaultBean;
import com.ansiyida.cgjl.bean.HotSearchBean;
import com.ansiyida.cgjl.bean.NewBean2;
import com.ansiyida.cgjl.bean.SearchHistoryBean;
import com.ansiyida.cgjl.bean.SearchUse;
import com.ansiyida.cgjl.bean.cgjl_bean.PurchaseInfoSearchBean;
import com.ansiyida.cgjl.bean.infoTypeBeanBean;
import com.ansiyida.cgjl.bean.purchaseDemandBean;
import com.ansiyida.cgjl.bean.purchaseSecretBean;
import com.ansiyida.cgjl.bean.sourceTypeBean;
import com.ansiyida.cgjl.binding.ViewBinder;
import com.ansiyida.cgjl.dialog.WheelDialogFragment2;
import com.ansiyida.cgjl.dialog.WheelDialogFragment_zj;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.model.CityEntity;
import com.ansiyida.cgjl.tab.DropdownButton_qy;
import com.ansiyida.cgjl.util.EditTextUtil;
import com.ansiyida.cgjl.util.JsonReadUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.TimeUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.view.FlowViewGroup;
import com.ansiyida.cgjl.view.LetterListView;
import com.ansiyida.cgjl.view.MyGridView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//import com.ansiyida.cgjl.view.NoPreloadViewPager;
/*
 * 搜索，地区
 * */
public class Fragment3 extends BaseFragment implements View.OnClickListener, AbsListView.OnScrollListener {
    private final static String CityFileName = "allcity.json";
    @Bind(R.id.xinxi_Type)
    DropdownButton_qy xinxiType;
    @Bind(R.id.xinxi_lin)
    LinearLayout xinxiLin;
    @Bind(R.id.resoure)
    DropdownButton_qy resoure;
    @Bind(R.id.resoure_lin)
    LinearLayout resoureLin;
    @Bind(R.id.time_tit)
    TextView timeTit;
    @Bind(R.id.start_time)
    Button startTime;
    @Bind(R.id.end_time)
    Button endTime;
    @Bind(R.id.time_lin)
    LinearLayout timeLin;
    @Bind(R.id.result)
    Button result;
    @Bind(R.id.sure)
    Button sure;
    @Bind(R.id.tit_rel)
    RelativeLayout titRel;
    @Bind(R.id.total_city_lv)
    ListView totalCityLv;
    @Bind(R.id.total_city_letters_lv)
    LetterListView totalCityLettersLv;
    @Bind(R.id.search_city_lv)
    ListView searchCityLv;
    @Bind(R.id.no_search_result_tv)
    TextView noSearchResultTv;
    @Bind(R.id.seven_Day)
    TextView sevenDay;
    @Bind(R.id.SS_Day)
    TextView SSDay;
    @Bind(R.id.Half_year)
    TextView HalfYear;
    @Bind(R.id.flowlayout)
    FlowViewGroup flowlayout;//
    @Bind(R.id.flowlayout1)
    FlowViewGroup flowlayout1;//
    @Bind(R.id.point_1)
    ImageView bt1;//
    @Bind(R.id.gridViewTop_fragment3)
    MyGridView gridView;
    @Bind(R.id.gridViewBottom_fragment3)
    MyGridView gridView_bottom;
    @Bind(R.id.tv_txt_no)
    TextView tv_txt;
    @Bind(R.id.tv_txt)
    TextView tv_txt1;
    @Bind(R.id.delete_fragment3)
    ImageView delete;
    @Bind(R.id.edittext_search)
    EditText editText;
    @Bind(R.id.iv_search)
    ImageView iv_search;
    @Bind(R.id.scrollView_visible)
    ScrollView scrollView_visible;      //没有搜索内容时显示
    @Bind(R.id.relative_fragment_lable)
    RelativeLayout relative_fragment_lable;
    @Bind(R.id.iv_delete)
    ImageView edit_delete;
    //搜索内容
    @Bind(R.id.iv_empty)
    ImageView iv_empty;
    @Bind(R.id.iv_repeat)
    ImageView iv_repeat;

    @Bind(R.id.relative)
    RelativeLayout relative;
    @Bind(R.id.search_title)
    TextView searchTitle;
    @Bind(R.id.search_full)
    TextView searchFull;
    @Bind(R.id.type_lin)
    LinearLayout typeLin;
    @Bind(R.id.no_search)
    RelativeLayout noSearch;
    @Bind(R.id.search_lin)
    LinearLayout searchLin;
    @Bind(R.id.shaixuan)
    TextView shaixuan;
    @Bind(R.id.draw_rel)
    DrawerLayout drawRel;
    @Bind(R.id.city_text)
    TextView cityText;
    @Bind(R.id.cur_city_name_tv)
    TextView curCityNameTv;
    @Bind(R.id.cur_city_re_get_location_tv)
    TextView curCityReGetLocationTv;
    @Bind(R.id.city_show)
    RelativeLayout cityShow;
    @Bind(R.id.rv_resouce)
    RecyclerView rvResouce;
    @Bind(R.id.tv_resouce)
    TextView tvResouce;
    @Bind(R.id.rel)
    RelativeLayout rel;
    @Bind(R.id.search_cgxq)
    TextView searchCgxq;
    @Bind(R.id.search_smcg)
    TextView searchSmcg;
    @Bind(R.id.P_search_recyclerView)
    RecyclerView PSearchRecyclerView;
    @Bind(R.id.xrefreshView)
    SmartRefreshLayout xrefreshView;
    private ImageView point_1;
    private Handler handler;
    private TextView overlay; // 对话框首字母TextView
    private OverlayThread overlayThread; // 显示首字母对话框
    private boolean mReady = false;
    private boolean isScroll = false;
    private boolean drawrelBol = false;
    private boolean citybol = true;
    private HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位
    protected List<CityEntity> hotCityList = new ArrayList<>();
    protected List<CityEntity> totalCityList = new ArrayList<>();
    protected List<CityEntity> curCityList = new ArrayList<>();
    protected List<CityEntity> searchCityList = new ArrayList<>();
    protected CityListAdapter cityListAdapter;
    protected SearchCityListAdapter searchCityListAdapter;
    private String locationCity, curSelCity;
    private String all_county = "";
    View showPupWindow = null; // 选择区域的view
    /**
     * 一级菜单名称数组
     **/
    ListView groupListView = null;
    ListView childListView = null;
    GroupAdapter groupAdapter = null;
    ChildAdapter childAdapter = null;

    TranslateAnimation animation;// 出现的动画效果
    // 屏幕的宽高
    public static int screen_width = 0;
    public static int screen_height = 0;
    PopupWindow mPopupWindow = null;
    private boolean tabStateArr = true;// 标记tab的选中状态，方便设置
    private ImageView image_back;


    private String citytext_drop = "";
    private String typetext_drop = "";
    private String sourecetext_drop = "";

    public Boolean frame3show = false;
    private ArrayList<SearchUse> record_list;
    private ArrayList<SearchUse> list;
    private SearchRecordAdapter adapterTop;
    private boolean flag;
    private boolean jiaoDianFlag = false;
    private ArrayList<SearchUse> listBottom;
    private int localPosition = 0;
    private int isFirst = 0;
    private SearchRecordAdapter adapterBottom;
    private TextView tv;
    private NewsOneAdapter adapter;
    private FragmentActivity activity;
    private ArrayList<NewBean2> lists;
    private int pageNum = 1;
    private int pageCount = 10;
    private String Type = Constant.searchType;
    private List<PurchaseInfoSearchBean.DataBean> PurchaseInfoSearchList = new ArrayList<>();
    private PurchaseSearchAdapter purchaseSearchAdapter;
    private List<String> TypeList = new ArrayList<>();
    private String XinxiStr = "";
    private String ResoureStr = "";
    private String StartTimeStr = "";
    private String endTimeStr = "";
    private String address;
    private String cityBJ = "";
    private Call<PurchaseInfoSearchBean> call;

    private String keywordBJ;
    private List<sourceTypeBean.DataBean> sourceList = new ArrayList<>();
    private List<infoTypeBeanBean.DataBean> infoTypeList = new ArrayList<>();
    public boolean state = true;
    private Animation translateAnimation;
    private TitleRecyclerAdapter titleRecyclerAdapter;
    public boolean relBol = false;
    private long lastClickTime = 0L;
    // 两次点击间隔不能少于1000ms
    private static final int FAST_CLICK_DELAY_TIME = 1000;
    private boolean refrashBol = true;
    private Call<SearchHistoryBean> call1;

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment3;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        initType();
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        curCityNameTv.setText("全国");
        drawRel.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        address = curCityNameTv.getText().toString();

        purchaseSearchAdapter = new PurchaseSearchAdapter(PurchaseInfoSearchList, getActivity());
        PSearchRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        PSearchRecyclerView.setAdapter(purchaseSearchAdapter);

        titleRecyclerAdapter = new TitleRecyclerAdapter(getContext(), sourceList);
        rvResouce.setLayoutManager(new LinearLayoutManager(getContext()));
        rvResouce.setAdapter(titleRecyclerAdapter);
        flag = false;
        list = new ArrayList<>();
        listBottom = new ArrayList<>();
        record_list = new ArrayList<>();
        adapterTop = new SearchRecordAdapter(record_list, getActivity(), false, this);
        adapterBottom = new SearchRecordAdapter(listBottom, getActivity(), true, this);
        edit_delete.setVisibility(View.GONE);
        gridView_bottom.setAdapter(adapterBottom);
        textSmall();
        gridView.setAdapter(adapterTop);

        citytext_drop = (String) SharedPreferenceUtils.get(getContext(), "city_loction", "");
        int[] location = new int[2];
        animation = new TranslateAnimation(0, 0, -700, location[1]);
        animation.setDuration(500);

        activity = getActivity();
        lists = new ArrayList<>();
        adapter = new NewsOneAdapter(lists, activity, getActivity().getWindow());
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(activity);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        resoure.setText("来源");
        xinxiType.setText("类型");
        editText.setCursorVisible(false);
        xrefreshView.setHeaderHeight(30);
        xrefreshView.setFooterHeight(30);
        onclick();
    }

    private void initType() {
        if (Constant.searchType.equals("title")) {
            Log.e("高亮", "initView:title ");
            Type = Constant.searchType;
            searchTitle.setTextColor(this.getResources().getColor(R.color.area_btn_textcolor));
            searchFull.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchSmcg.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchCgxq.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchTitle.setTextSize(15);
            searchFull.setTextSize(14);
            searchSmcg.setTextSize(14);
            searchCgxq.setTextSize(14);
        } else if (Constant.searchType.equals("full")) {
            Log.e("高亮", "initView:full ");
            Type = Constant.searchType;
            searchFull.setTextColor(this.getResources().getColor(R.color.area_btn_textcolor));
            searchTitle.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchSmcg.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchCgxq.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchFull.setTextSize(15);
            searchTitle.setTextSize(14);
            searchSmcg.setTextSize(14);
            searchCgxq.setTextSize(14);
        } else if (Constant.searchType.equals("smcg")) {
            Log.e("高亮", "initView:smcg ");
            Type = Constant.searchType;
            searchSmcg.setTextColor(this.getResources().getColor(R.color.area_btn_textcolor));
            searchFull.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchTitle.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchCgxq.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchSmcg.setTextSize(15);
            searchTitle.setTextSize(14);
            searchFull.setTextSize(14);
            searchCgxq.setTextSize(14);
        } else if (Constant.searchType.equals("cgxq")) {
            Log.e("高亮", "initView:cgxq ");
            Type = Constant.searchType;
            searchCgxq.setTextColor(this.getResources().getColor(R.color.area_btn_textcolor));
            searchFull.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchSmcg.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchTitle.setTextColor(this.getResources().getColor(R.color.back_dark));
            searchCgxq.setTextSize(15);
            searchTitle.setTextSize(14);
            searchFull.setTextSize(14);
            searchSmcg.setTextSize(14);
        }
    }

    private void onclick() {
        totalCityLv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        adapter.setCgxqListnear(new NewsOneAdapter.CGXQListnear() {
            @Override
            public void CGXQ(int position, String bj) {

            }
        });
        titleRecyclerAdapter.setTitleRecyclerListnear(new TitleRecyclerAdapter.TitleRecyclerListnear() {
            @Override
            public void TitleClick(int position) {
                if (state) {
                    state = !state;
                } else {
                    state = !state;
                }
                pageNum = 1;
                PurchaseInfoSearchList.clear();
                ResoureStr = sourceList.get(position).getcode();
                resoure.setText(ResoureStr);
                JudgeResouce(ResoureStr);
                titleRecyclerAdapter.setSelection(position);
                httpData_RecyclerView("", EditTextUtil.getEditTextStr(editText), "", "", "");
            }
        });
        curCityReGetLocationTv.setOnClickListener(this);
        resoure.setOnClickListener(this);
        xinxiType.setOnClickListener(this);
        startTime.setOnClickListener(this);
        endTime.setOnClickListener(this);
        result.setOnClickListener(this);
        sure.setOnClickListener(this);
        sevenDay.setOnClickListener(this);
        SSDay.setOnClickListener(this);
        HalfYear.setOnClickListener(this);
        searchTitle.setOnClickListener(this);
        searchFull.setOnClickListener(this);
        shaixuan.setOnClickListener(this);
        drawRel.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                drawrelBol = true;
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                drawrelBol = false;
                super.onDrawerClosed(drawerView);
            }
        });
        drawRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() != R.id.tv_resouce) {
                    if (!state) {
                        onMove();
                    }
                }
                Toast.makeText(activity, v.getId() + "", Toast.LENGTH_SHORT).show();
            }
        });

        purchaseSearchAdapter.setPurchaseSearchListnear(new PurchaseSearchAdapter.PurchaseSearchListnear() {
            @Override
            public void intent(Intent intent) {
                startActivityForResult(intent, 100);
            }
        });
        xrefreshView.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore( RefreshLayout refreshLayout) {
                //加载更多
                if (!editText.getText().toString().isEmpty()) {
                    pageNum++;
                    changeState();
                    judgeOnMove();
                    refrashBol=false;
                    httpData_RecyclerView("", editText.getText().toString(), "", "", "");
                } else {
                    ToastUtils.showMessage(getActivity(), "您想搜索的内容是什么呢？");
                    xrefreshView.finishLoadMore();
                }
            }

            @Override
            public void onRefresh( RefreshLayout refreshLayout) {
                //刷新
                if (!editText.getText().toString().isEmpty()) {
                    pageNum = 1;
                    PurchaseInfoSearchList.clear();
                    changeState();
                    judgeOnMove();
                    refrashBol=false;
                    httpData_RecyclerView("", editText.getText().toString(), "", "", "");

                } else {
                    ToastUtils.showMessage(getContext(), "您想搜索的内容是什么呢？");
                    xrefreshView.finishRefresh();
                }
            }
        });
    }

    private void JudgeResouce(String value) {
        if (value.equals("全军武器装备采购网") | value.equals("武警物资采购网") | value.equals("军队采购网")) {
            if (!address.equals("全国")) {
                cityBJ = address;
                address = "全国";
            } else {
                address = "全国";
            }
            curCityNameTv.setText("全国");
            resoure.setText(value);
            ResoureStr = value;
        } else {
            if (address.equals("全国")) {
                if (cityBJ.isEmpty()) {
                    curCityNameTv.setText(address);
                } else {
                    curCityNameTv.setText(cityBJ);
                }
            } else {
                curCityNameTv.setText(address);
            }
            resoure.setText(value);
            ResoureStr = value;
        }
    }

    public void changeState() {
        state = true;
        relBol = false;
    }

    @Override
    protected void initData() {
        getSource();
        getInfoType();
        searchCgxq.setOnClickListener(this);
        searchSmcg.setOnClickListener(this);
        bt1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                typetext_drop = "";
                sourecetext_drop = "";
                unvisibleKeyBoard();
                MainActivity activity1 = (MainActivity) getActivity();
                editText.setText("");
                editText.setCursorVisible(false);
                citytext_drop = (String) SharedPreferenceUtils.get(getContext(), "city_loction", "");
                if (frame3show) {
                    scrollView_visible.setVisibility(View.VISIBLE);
                    relative_fragment_lable.setVisibility(View.GONE);
                    //  httpSearchHistory();
                    refreshdip();
                    //  getActivity().getSupportFragmentManager().beginTransaction().hide(this);
                    shaixuan.setVisibility(View.GONE);
                    changeState();
                    Constant.searchType = "title";
                    Type = "title";
                    initType();
                    rel.setVisibility(View.INVISIBLE);
                    ResoureStr="";
                    rvResouce.smoothScrollToPosition(0);
                    titleRecyclerAdapter.setSelection(0);
                    frame3show = false;
                } else {
                    shaixuan.setVisibility(View.GONE);
                    changeState();
                    Constant.searchType = "title";
                    Type = "title";

                    initType();
                    rel.setVisibility(View.INVISIBLE);
                    activity1.back();
                }

            }
        });
        //------------------------------------------------------------------------------------------------上面gridview的点击事件
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                result();
                SearchUse searchUse = record_list.get(position);
                String str = searchUse.getText();
                editText.setText(str);
                SharedPreferenceUtils.put(getActivity(), "searchString", str);
                scrollView_visible.setVisibility(View.GONE);

                //     setFocus();
                if (isFirst == 0) {
                    visibleKeyBoard();
                }
                pageNum = 1;
                PurchaseInfoSearchList.clear();
                changeState();
                Constant.titlelist.clear();
                Constant.fulllist.clear();
                PSearchRecyclerView.setAdapter(purchaseSearchAdapter);
                httpData_RecyclerView("", EditTextUtil.getEditTextStr(editText), sourecetext_drop, citytext_drop, typetext_drop);

            }
        });

        //------------------------------------------------------------------------------------------------下面gridview的点击事件
        gridView_bottom.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                result();
                SearchUse searchUse = listBottom.get(position);
                String str = searchUse.getText();
                editText.setText(str);
                scrollView_visible.setVisibility(View.GONE);
                //   setFos();
                if (isFirst == 0) {
                    visibleKeyBoard();
                }
                pageNum = 1;
                PurchaseInfoSearchList.clear();
                changeState();
                Constant.titlelist.clear();
                Constant.fulllist.clear();
                PSearchRecyclerView.setAdapter(purchaseSearchAdapter);
                httpData_RecyclerView("", EditTextUtil.getEditTextStr(editText), sourecetext_drop, citytext_drop, typetext_drop);
            }
        });

        //------------------------------------------------------------------------------------------------搜索按钮
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchNext();
            }
        });
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                state = true;
                editText.setCursorVisible(true);
                editText.setFocusableInTouchMode(true);
            }
        });
        //输入框的焦点问题
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    changeState();
                    // 获得焦点
                    editText.setCursorVisible(true);
                    if (!"".equals(EditTextUtil.getEditTextStr(editText))) {
                        edit_delete.setVisibility(View.VISIBLE);
                    }
                    LogUtil.i("jiao", "获取焦点");
                    iv_search.setClickable(true);
                    httpSearchHistory();

                }

            }


        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    Constant.titlelist.clear();
                    Constant.fulllist.clear();
                    searchNext();
                    return true;
                }
                return false;
            }
        });
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setSelection(s.length());//让光标移至末端
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //删除全部搜索记录
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopuWindow_cache();
            }
        });

        //编辑框输入监听
        editText.addTextChangedListener(new TextWatcher() {
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
                    edit_delete.setVisibility(View.VISIBLE);
                } else {
                    typeLin.setVisibility(View.GONE);
                    searchLin.setVisibility(View.VISIBLE);
                    edit_delete.setVisibility(View.GONE);
                    PurchaseInfoSearchList.clear();
                    purchaseSearchAdapter.notifyDataSetChanged();
                }
            }
        });
        tvResouce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (System.currentTimeMillis() - lastClickTime < FAST_CLICK_DELAY_TIME) {
                    return;
                }
                lastClickTime = System.currentTimeMillis();
                if (!refrashBol) {
                    state = true;
                    refrashBol = true;
                    onMove();
                } else {
                    onMove();
                }
            }
        });

        //编辑器清空按钮监听
        edit_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setText("");
                httpData_RecyclerView("", editText.getText().toString(), "", "", "");
            }
        });
        httoHotSearch();
        Constant.titlelist.clear();
        Constant.fulllist.clear();
        PSearchRecyclerView.setAdapter(purchaseSearchAdapter);
        httpSearchHistory();
    }

    private void textSmall() {
        try {
            flowlayout.removeAllViews();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.adapter_search_history, flowlayout, false);
                    tv.setText(list.get(i).getText());
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            result();
                            String str = ((TextView) v).getText().toString();
                            editText.setText(str);
                            editText.setCursorVisible(false);
                            scrollView_visible.setVisibility(View.GONE);
                            if (isFirst == 0) {
                                visibleKeyBoard();
                            }
                            frame3show = true;
                            pageNum = 1;
                            PurchaseInfoSearchList.clear();
                            changeState();
                            Constant.titlelist.clear();
                            Constant.fulllist.clear();
                            httpData_RecyclerView("", EditTextUtil.getEditTextStr(editText), sourecetext_drop, citytext_drop, typetext_drop);
                        }
                    });
                    flowlayout.addView(tv);
                }
            } else
                tv_txt1.setVisibility(View.GONE);
        } catch (Exception gg) {
            gg.toString();
        }
    }

    private void textSmall2() {
        try {
            flowlayout1.removeAllViews();
            if (listBottom.size() > 0) {
                for (int i = 0; i < listBottom.size(); i++) {
                    tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.adapter_search_history, flowlayout, false);

                    tv.setText(listBottom.get(i).getText());
                    tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            result();
                            String str = ((TextView) v).getText().toString();
                            editText.setText(str);
                            editText.setCursorVisible(false);
                            scrollView_visible.setVisibility(View.GONE);
                            if (isFirst == 0) {
                                visibleKeyBoard();
                            }
                            frame3show = true;
                            pageNum = 1;
                            PurchaseInfoSearchList.clear();
                            changeState();
                            Constant.titlelist.clear();
                            Constant.fulllist.clear();
                            httpData_RecyclerView("", EditTextUtil.getEditTextStr(editText), sourecetext_drop, citytext_drop, typetext_drop);
                        }
                    });
                    flowlayout1.addView(tv);
                }
            }
        } catch (Exception gg) {
            gg.toString();
        }
    }

    private void httpData_RecyclerView(String apptken, final String keyword, String source, String citylocation, String typetextdrop) {
        if (pageNum == 1) {
            lists.clear();
            PurchaseInfoSearchList.clear();

        }

        if (NetWorkUtils.isNetworkConnected(getContext())) {
            Log.e("frgament3", "httpData_RecyclerView: " + (String) SharedPreferenceUtils.get(getActivity(), "app_token", "") + pageNum + "" + pageCount + "" + keyword + XinxiStr + curCityNameTv.getText().toString() + ResoureStr + StartTimeStr + " 00:00:00" + endTimeStr + " 00:00:00" + Type);
            if (Type.equals("title") || Type.equals("full")) {
                shaixuan.setVisibility(View.VISIBLE);
                if (StartTimeStr.equals("") && endTimeStr.equals("")) {
                    call = MyApplication.getNetApi().getPurchaseInfoSearch((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "", keyword, ResoureStr, curCityNameTv.getText().toString(), XinxiStr, StartTimeStr, endTimeStr, Type);
                } else {
                    Log.e("frgament3", "httpData_RecyclerView: " + (String) SharedPreferenceUtils.get(getActivity(), "app_token", "") + pageNum + "" + pageCount + "" + keyword + XinxiStr + curCityNameTv.getText().toString() + ResoureStr + StartTimeStr + " 00:00:00" + endTimeStr + " 00:00:00" + Type);
                    call = MyApplication.getNetApi().getPurchaseInfoSearch((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "", keyword, ResoureStr, curCityNameTv.getText().toString(), XinxiStr, StartTimeStr + " 00:00:00", endTimeStr + " 00:00:00", Type);
                }
                call.enqueue(new Callback<PurchaseInfoSearchBean>() {
                    @Override
                    public void onResponse(Call<PurchaseInfoSearchBean> call, Response<PurchaseInfoSearchBean> response) {
                        if (response.isSuccessful()) {
                            PurchaseInfoSearchBean body = response.body();
                            if (body != null) {
                                if (body.getStatus() == 200) {
                                    if (body.getData().size() > 0) {
                                        Log.e("frag", "httpData_RecyclerView: " + body.getData().size());
                                        if (!relBol) {
                                            rel.setVisibility(View.VISIBLE);
                                            relBol = true;
                                        }
                                        relative_fragment_lable.setVisibility(View.GONE);
                                        searchLin.setVisibility(View.GONE);
                                        typeLin.setVisibility(View.VISIBLE);
                                        PSearchRecyclerView.setVisibility(View.VISIBLE);
                                        PurchaseInfoSearchList.addAll(body.getData());
                                        purchaseSearchAdapter.setKeyword(keyword, Type, PurchaseInfoSearchList);
                                    } else {
                                        if (!relBol) {
                                            rel.setVisibility(View.VISIBLE);
                                            relBol = true;
                                        }
                                        if (pageNum == 1) {
                                            PSearchRecyclerView.setVisibility(View.GONE);
                                            relative_fragment_lable.setVisibility(View.VISIBLE);
                                        } else {
                                            ToastUtils.showMessage(getContext(), "数据加载到底！");
                                        }
                                    }
                                } else {
                                    if (!relBol) {
                                        rel.setVisibility(View.VISIBLE);
                                        relBol = true;
                                    }
                                    if (pageNum == 1) {
                                        PSearchRecyclerView.setVisibility(View.GONE);
                                        relative_fragment_lable.setVisibility(View.VISIBLE);
                                    } else {
                                        ToastUtils.showMessage(getContext(), "数据加载到底！");
                                    }
                                }
                            } else {
                                if (!relBol) {
                                    rel.setVisibility(View.VISIBLE);
                                    relBol = true;
                                }
                                if (pageNum == 1) {
                                    PSearchRecyclerView.setVisibility(View.GONE);
                                    relative_fragment_lable.setVisibility(View.VISIBLE);
                                } else {
                                    ToastUtils.showMessage(getContext(), "数据加载到底！");
                                }
                            }
                        } else {
                            if (!relBol) {
                                rel.setVisibility(View.VISIBLE);
                                relBol = true;
                            }
                            if (pageNum == 1) {
                                PSearchRecyclerView.setVisibility(View.GONE);
                                relative_fragment_lable.setVisibility(View.VISIBLE);
                            }
                        }
                        call.cancel();
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                    }

                    @Override
                    public void onFailure(Call<PurchaseInfoSearchBean> call, Throwable t) {
                        call.cancel();
                        ToastUtils.showMessage(getContext(), "数据查询：" + t.getMessage());
                        iv_repeat.setVisibility(View.GONE);
                        iv_repeat.setClickable(false);
                    }
                });
            } else if (Type.equals("smcg")) {
                shaixuan.setVisibility(View.INVISIBLE);
                rel.setVisibility(View.GONE);
                SmcgCgxqHide();
                Call<purchaseSecretBean> call = MyApplication.getNetApi().getpurchaseSecret((String) SharedPreferenceUtils.get(getContext(), "app_token", ""), pageNum + "", "20", false, keyword);
                // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                call.enqueue(new Callback<purchaseSecretBean>() {
                    @Override
                    public void onResponse(Call<purchaseSecretBean> call, Response<purchaseSecretBean> response) {
                        if (response.isSuccessful()) {
                            purchaseSecretBean body = response.body();
                            if (body.getData() != null) {
                                searchLin.setVisibility(View.GONE);
                                typeLin.setVisibility(View.VISIBLE);
                                PSearchRecyclerView.setVisibility(View.VISIBLE);
                                List<purchaseSecretBean.DataBean.list_law_bean> list = body.getData().getlist_law_bean();
                                if (list != null && list.size() > 0) {
                                    relative_fragment_lable.setVisibility(View.GONE);
                                    Log.e("涉密采购", "onResponse: " + list.size());
                                    for (purchaseSecretBean.DataBean.list_law_bean listBean : list) {
                                        NewBean2 newBean = new NewBean2();
                                        newBean.setArtype("SM");
                                        newBean.setIsCollect(listBean.getisCollection());
                                        newBean.setId(listBean.getid());
                                        newBean.setTitle(listBean.gettitle());
                                        newBean.setLable(listBean.getsecretDegree());
                                        if (listBean.getpublishTime() != null) {
                                            newBean.setJca_time("发布日期 " + TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                        } else {
                                            //  newBean.setJca_time( "发布日期 1234-12-12");
                                            newBean.setJca_time("发布日期                      ");
                                        }
                                        if (listBean.getendTime() != null) {
                                            newBean.setVtime("有效时限 " + TimeUtils.mmtime_Time(listBean.getendTime()));
                                        } else {
                                            newBean.setVtime("有效时限                      ");
                                        }
                                        lists.add(newBean);
                                    }

                                    adapter.notifyDataSetChanged();
                                } else {
                                    LogUtil.i("yantao", "else");
                                    if (pageNum == 1) {
                                        lists.clear();
                                        adapter.notifyDataSetChanged();
                                        relative_fragment_lable.setVisibility(View.VISIBLE);
                                    }

                                }

                            } else {
                                if (pageNum == 1) {
                                    lists.clear();
                                    adapter.notifyDataSetChanged();
                                    relative_fragment_lable.setVisibility(View.VISIBLE);
                                }
                            }

                        } else {
                            if (pageNum == 1) {
                                lists.clear();
                                adapter.notifyDataSetChanged();
                                relative_fragment_lable.setVisibility(View.VISIBLE);
                            }
                        }

                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<purchaseSecretBean> call, Throwable t) {
                        call.cancel();
                        ToastUtils.showMessage(getContext(), t.toString());
                        iv_repeat.setVisibility(View.GONE);
                    }
                });
            } else if (Type.equals("cgxq")) {
                shaixuan.setVisibility(View.INVISIBLE);
                rel.setVisibility(View.GONE);
                SmcgCgxqHide();
                Call<purchaseDemandBean> call = MyApplication.getNetApi().getpurchaseDemand1((String) SharedPreferenceUtils.get(getContext(), "app_token", ""), pageNum + "", "20", keyword);
                // Call<YanTaoBean> call = MyApplication.getNetApi().yanTaoList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), pageNum + "", pageCount + "");
                call.enqueue(new Callback<purchaseDemandBean>() {
                    @Override
                    public void onResponse(Call<purchaseDemandBean> call, Response<purchaseDemandBean> response) {
                        if (response.isSuccessful()) {
                            relative_fragment_lable.setVisibility(View.GONE);
                            searchLin.setVisibility(View.GONE);
                            typeLin.setVisibility(View.VISIBLE);
                            PSearchRecyclerView.setVisibility(View.VISIBLE);
                            purchaseDemandBean body = response.body();
                            if (body.getData() != null) {
                                List<purchaseDemandBean.DataBean> list = body.getData();
                                if (list != null && list.size() > 0) {
                                    for (purchaseDemandBean.DataBean listBean : list) {
                                        NewBean2 newBean = new NewBean2();
                                        newBean.setArtype("CX");
                                        newBean.setIsCollect(listBean.getisCollection());
                                        newBean.setId(listBean.getid());
                                        newBean.setTitle(listBean.gettitle());
                                        newBean.setVtime(TimeUtils.mmtime_Time(listBean.getpublishTime()));
                                        lists.add(newBean);
                                    }
                                    adapter.notifyDataSetChanged();
                                    iv_repeat.setVisibility(View.GONE);
                                } else {
                                    if (pageNum == 1) {
                                        relative_fragment_lable.setVisibility(View.VISIBLE);
                                        iv_repeat.setVisibility(View.GONE);
                                        lists.clear();
                                        adapter.notifyDataSetChanged();
                                    }
                                }
                            } else {
                                if (pageNum == 1) {
                                    relative_fragment_lable.setVisibility(View.VISIBLE);
                                    iv_repeat.setVisibility(View.GONE);
                                    lists.clear();
                                    adapter.notifyDataSetChanged();
                                }
                            }

                        } else {
                            if (pageNum == 1) {
                                typeLin.setVisibility(View.VISIBLE);
                                relative_fragment_lable.setVisibility(View.VISIBLE);
                                iv_repeat.setVisibility(View.GONE);
                                adapter.notifyDataSetChanged();
                            }
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<purchaseDemandBean> call, Throwable t) {
                        call.cancel();
                        ToastUtils.showMessage(getContext(), t.toString());
                        iv_repeat.setVisibility(View.GONE);
                    }
                });
            }
        } else {
            relative_fragment_lable.setVisibility(View.GONE);
            iv_repeat.setVisibility(View.VISIBLE);
            ToastUtils.showMessage(getContext(), "当前网络不可用");
        }
        xrefreshView.finishLoadMore();
        xrefreshView.finishRefresh();
    }

    private void SmcgCgxqHide() {
        if (pageNum == 1) {
            lists.clear();
        }
        shaixuan.setVisibility(View.GONE);
        rel.setVisibility(View.GONE);
        relBol = false;
    }

    private void searchNext() {
        if (!EditTextUtil.getEditTextStr(editText).equals(keywordBJ)) {
            result();
        }
        String editTextStr = EditTextUtil.getEditTextStr(editText);
        keywordBJ = editTextStr;
        // String editTextStr="";
        if (!"".equals(editTextStr)) {
            //   SharedPreferenceUtils.put(getActivity(), "searchString", editTextStr);
            scrollView_visible.setVisibility(View.GONE);
            relative_fragment_lable.setVisibility(View.VISIBLE);
            //    setFocus();
            visibleKeyBoard();
            iv_search.setClickable(false);
            frame3show = true;
            if (rel.getVisibility() == View.VISIBLE) {
                //rel.setVisibility(View.VISIBLE);
            } else {
                onMove();
            }
            changeState();
            httpData_RecyclerView("", EditTextUtil.getEditTextStr(editText), sourecetext_drop, citytext_drop, typetext_drop);
            visibleKeyBoard();
        } else {
            ToastUtils.showMessage(getActivity(), "请输入有效内容");
        }

    }

    private void httpSearchHistory() {
        if (listBottom.size() == 0) {
            httoHotSearch();
        }
        call1 = MyApplication.getNetApi().getSearchHistoryList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), 1 + "", "20");
        call1.enqueue(new Callback<SearchHistoryBean>() {
            @Override
            public void onResponse(Call<SearchHistoryBean> call, Response<SearchHistoryBean> response) {
                if (response.isSuccessful()) {
                    SearchHistoryBean body = response.body();
                    if (body.getData() != null) {
                        List<SearchHistoryBean.DataBean.listbean> searchRecordList = body.getData().getlist();
                        if (searchRecordList.size() > 0) {
                            list.clear();
                            delete.setVisibility(View.VISIBLE);
                            tv_txt1.setVisibility(View.VISIBLE);
                            for (SearchHistoryBean.DataBean.listbean searchBean : searchRecordList) {
                                list.add(new SearchUse(searchBean.getkeyword(), searchBean.getid() + ""));
                            }
                            LogUtil.i("nihao", "size:" + list.size());
                            textSmall();
                            //  adapterTop.notifyDataSetChanged();
                        }
                    }
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<SearchHistoryBean> call, Throwable throwable) {
                call.cancel();

            }
        });
    }

    private void httoHotSearch() {
        try {
            Call<HotSearchBean> call = MyApplication.getNetApi().hotSearchList((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), 1 + "", "20");
            call.enqueue(new Callback<HotSearchBean>() {
                @Override
                public void onResponse(Call<HotSearchBean> call, Response<HotSearchBean> response) {
                    if (response.isSuccessful()) {
                        listBottom.clear();
                        List<HotSearchBean.DataBean> dataList = response.body().getData();

                        for (HotSearchBean.DataBean dataBean : dataList) {
                            listBottom.add(new SearchUse(dataBean.getkeyword(), dataBean.getid() + ""));
                        }
                        //    adapterBottom.notifyDataSetChanged();
                        textSmall2();
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<HotSearchBean> call, Throwable throwable) {
                    call.cancel();
                }
            });
        } catch (Exception E) {
            throw E;
        }
    }

    public void refreshdip() {
        delete.setVisibility(View.GONE);
        tv_txt1.setVisibility(View.GONE);
        listBottom.clear();
        // record_list.clear();
        // adapterTop.notifyDataSetChanged();
        adapterBottom.notifyDataSetChanged();
        flowlayout.removeAllViews();
        citytext_drop = (String) SharedPreferenceUtils.get(getContext(), "city_loction", "");
        httoHotSearch();
        httpSearchHistory();
    }

    public void cursorvisible() {
        editText.setText("");
        editText.setFocusable(false);
        editText.setCursorVisible(false);
    }

    public void removeAll() {
        int length = list.size();
        StringBuffer sb = new StringBuffer();
        for (int x = 0; x < length; x++) {
            sb.append(list.get(x).getId() + ",");
        }
        String str = sb.toString();
        if (!"".equals(str)) {
            Call<DefaultBean> call = MyApplication.getNetApi().deleteSearchHistory((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""));
            call.enqueue(new Callback<DefaultBean>() {
                @Override
                public void onResponse(Call<DefaultBean> call, Response<DefaultBean> response) {
                    if (response.isSuccessful()) {
                        delete.setVisibility(View.GONE);
                        tv_txt1.setVisibility(View.GONE);
                        flowlayout.removeAllViews();
                    } else {
                        ToastUtils.showMessage(getActivity(), "删除失败");
                    }
                    call.cancel();
                }

                @Override
                public void onFailure(Call<DefaultBean> call, Throwable throwable) {
                    ToastUtils.showMessage(getActivity(), "删除失败");
                    call.cancel();

                }
            });

        } else {
            ToastUtils.showMessage(getActivity(), "无记录可删除");
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (isFirst == 0) {
            jiaoDianFlag = true;
        }
        if (shaixuan.getVisibility() == View.VISIBLE || Type.equals("smcg") || Type.equals("cgxq")) {
            httpData_RecyclerView("", editText.getText().toString(), "", "", "");
        }
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent.KEYCODE_BACK) {
                    Log.e("onresume", "onKey: " + Type);
                    if (drawrelBol) {
                        drawrelBol = false;
                        drawRel.closeDrawers();
                        return true;
                    } else if (shaixuan.getVisibility() == View.VISIBLE || Type.equals("smcg") || Type.equals("cgxq")) {
                        shaixuan.setVisibility(View.GONE);
                        rel.setVisibility(View.GONE);
                        pageNum = 1;
                        changeState();
                        Constant.searchType = "title";
                        Type = "title";
                        ResoureStr="";
                        rvResouce.smoothScrollToPosition(0);
                        titleRecyclerAdapter.setSelection(0);
                        initType();
                        return false;
                    } else {
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.searchhide();
                        Type = "null";
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public void onBackPressed() {
        citytext_drop = (String) SharedPreferenceUtils.get(getContext(), "city_loction", "");
        editText.setText("");
        editText.setCursorVisible(false);

        // citytext_drop="";
        typetext_drop = "";
        sourecetext_drop = "";
        if (frame3show) {
            scrollView_visible.setVisibility(View.VISIBLE);
            relative_fragment_lable.setVisibility(View.GONE);
            //  httpSearchHistory();
            refreshdip();
            //  getActivity().getSupportFragmentManager().beginTransaction().hide(this);
            frame3show = false;
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClick(View view) {
        long time = System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = new Date(time);
        String t1 = format.format(d1);

        Calendar ca = Calendar.getInstance();//得到一个Calendar的实例
        ca.setTime(new Date()); //设置时间为当前时间
        switch (view.getId()) {
            case R.id.search_title:
                if (!Type.equals("title")) {
                    judgeOnMove();
                    Type = "title";
                    Constant.searchType = "title";
                    searchTitle.setTextColor(this.getResources().getColor(R.color.area_btn_textcolor));
                    searchTitle.setTextSize(15);
                    searchFull.setTextSize(14);
                    searchCgxq.setTextSize(14);
                    searchSmcg.setTextSize(14);
                    searchFull.setTextColor(this.getResources().getColor(R.color.back_dark));
                    searchSmcg.setTextColor(this.getResources().getColor(R.color.back_dark));
                    searchCgxq.setTextColor(this.getResources().getColor(R.color.back_dark));
                    pageNum = 1;
                    if (!editText.getText().toString().isEmpty()) {

                        changeState();
                        PSearchRecyclerView.setAdapter(purchaseSearchAdapter);
                        httpData_RecyclerView("", editText.getText().toString(), "", "", "");
                    }
                }
                break;
            case R.id.search_full:
                if (!Type.equals("full")) {
                    judgeOnMove();
                    Type = "full";
                    Constant.searchType = "full";
                    pageNum = 1;
                    searchFull.setTextColor(this.getResources().getColor(R.color.area_btn_textcolor));
                    searchTitle.setTextSize(14);
                    searchCgxq.setTextSize(14);
                    searchSmcg.setTextSize(14);
                    searchFull.setTextSize(15);
                    searchTitle.setTextColor(this.getResources().getColor(R.color.back_dark));
                    searchSmcg.setTextColor(this.getResources().getColor(R.color.back_dark));
                    searchCgxq.setTextColor(this.getResources().getColor(R.color.back_dark));
                    if (!editText.getText().toString().isEmpty()) {
                        changeState();
                        PSearchRecyclerView.setAdapter(purchaseSearchAdapter);
                        httpData_RecyclerView("", editText.getText().toString(), "", "", "");
                    }
                }
                break;
            case R.id.search_cgxq:
                if (!Type.equals("cgxq")) {
                    judgeOnMove();
                    Type = "cgxq";
                    Constant.searchType = "cgxq";
                    pageNum = 1;
                    searchCgxq.setTextColor(this.getResources().getColor(R.color.area_btn_textcolor));
                    searchTitle.setTextSize(14);
                    searchCgxq.setTextSize(15);
                    searchSmcg.setTextSize(14);
                    searchFull.setTextSize(14);
                    searchTitle.setTextColor(this.getResources().getColor(R.color.back_dark));
                    searchSmcg.setTextColor(this.getResources().getColor(R.color.back_dark));
                    searchFull.setTextColor(this.getResources().getColor(R.color.back_dark));
                    if (!editText.getText().toString().isEmpty()) {
                        PSearchRecyclerView.setAdapter(adapter);
                        changeState();
                        httpData_RecyclerView("", editText.getText().toString(), "", "", "");
                    }
                }
                break;
            case R.id.search_smcg:
                if (!Type.equals("smcg")) {
                    judgeOnMove();
                    Type = "smcg";
                    Constant.searchType = "smcg";
                    pageNum = 1;
                    searchSmcg.setTextColor(this.getResources().getColor(R.color.area_btn_textcolor));
                    searchTitle.setTextSize(14);
                    searchCgxq.setTextSize(14);
                    searchSmcg.setTextSize(15);
                    searchFull.setTextSize(14);
                    searchTitle.setTextColor(this.getResources().getColor(R.color.back_dark));
                    searchCgxq.setTextColor(this.getResources().getColor(R.color.back_dark));
                    searchFull.setTextColor(this.getResources().getColor(R.color.back_dark));
                    if (!editText.getText().toString().isEmpty()) {
                        changeState();
                        PSearchRecyclerView.setAdapter(adapter);
                        httpData_RecyclerView("", editText.getText().toString(), "", "", "");
                    }
                }
                break;
            case R.id.shaixuan:
                judgeOnMove();
                state = true;
                int visibility = searchLin.getVisibility();
                if (visibility != 0) {
                    drawrelBol = true;
                    drawRel.openDrawer(Gravity.END);
                    //能筛选
                    getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//沉浸状态栏
                    getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                    getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
                    setStatusBarFullTransparent();
                    BaseActivity.setStateColor(getActivity(), true);

                    initTotalCityList();
                    cityListAdapter = new CityListAdapter(getActivity(), totalCityList, hotCityList);
                    totalCityLv.setAdapter(cityListAdapter);
                    totalCityLv.setOnScrollListener(this);
                    totalCityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            if (position > 1) {
                                if (ResoureStr.equals("全军武器装备采购网") || ResoureStr.equals("军队采购网") || ResoureStr.equals("武警物资采购网")) {
                                    ToastUtils.showMessage(getContext(), "有关武器的来源，地址都是全国哦！");
                                } else {
                                    CityEntity cityEntity = totalCityList.get(position);
                                    curCityNameTv.setText(cityEntity.getName());
                                    address = cityEntity.getName();
                                }
                            }
                        }
                    });
                    totalCityLettersLv.setOnTouchingLetterChangedListener(new LetterListViewListener());
                    initOverlay();
                    handler = new Handler();
                    overlayThread = new OverlayThread();
                    searchCityListAdapter = new SearchCityListAdapter(getActivity(), searchCityList);
                    searchCityLv.setAdapter(searchCityListAdapter);
                    locationCity = (String) SharedPreferenceUtils.get(getActivity(), "city_loction", "");
                    curSelCity = locationCity;
                    if (address.isEmpty()) {
                        address = "全国";
                    }
                    initListener();
                }
                break;
            case R.id.resoure:
                try {
                    TypeList.clear();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_BACK, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                    bundle.putString(WheelDialogFragment2.DIALOG_LEFT, "取消");
                    bundle.putString(WheelDialogFragment2.DIALOG_RIGHT, "确定");
                    //   bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, ResUtil.getStringArray(R.array.main_home_menu));
                    TypeList.clear();
                    for (int i = 0; i < sourceList.size(); i++) {
                        TypeList.add(sourceList.get(i).getcode());
                    }
                    bundle.putStringArray(WheelDialogFragment2.DIALOG_WHEEL, (String[]) TypeList.toArray(new String[TypeList.size()]));

                    WheelDialogFragment_zj dialogFragment1 = WheelDialogFragment_zj.newInstance(WheelDialogFragment_zj.class, bundle);
                    dialogFragment1.setWheelDialogListener(new WheelDialogFragment_zj.OnWheelDialogListener() {
                        @Override
                        public void onClickLeft(DialogFragment dialog, String value) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickRight(DialogFragment dialog, String value) {
                            dialog.dismiss();
                            JudgeResouce(value);
                        }

                        @Override
                        public void onValueChange(DialogFragment dialog, String value) {
                        }
                    });

                    dialogFragment1.show(getFragmentManager(), "");
                } catch (Exception e) {
                    e.toString();

                }
                break;
            case R.id.xinxi_Type:
                try {
                    TypeList.clear();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_BACK, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                    bundle.putString(WheelDialogFragment2.DIALOG_LEFT, "取消");
                    bundle.putString(WheelDialogFragment2.DIALOG_RIGHT, "确定");
                    //   bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, ResUtil.getStringArray(R.array.main_home_menu));
                    TypeList.clear();
                    for (int i = 0; i < infoTypeList.size(); i++) {
                        if (infoTypeList.get(i).getcode().equals("涉密采购") | infoTypeList.get(i).getcode().equals("采购需求")) {
                        } else {
                            TypeList.add(infoTypeList.get(i).getname());
                        }

                    }

                    bundle.putStringArray(WheelDialogFragment2.DIALOG_WHEEL, (String[]) TypeList.toArray(new String[TypeList.size()]));
                    // dropdownButton2.setData(types,"类型",text_title,edit_view_type);
                    // dropdownButton_policyType.setData(names,"全部法规",null,edit_view_class);
                    WheelDialogFragment_zj dialogFragment1 = WheelDialogFragment_zj.newInstance(WheelDialogFragment_zj.class, bundle);
                    dialogFragment1.setWheelDialogListener(new WheelDialogFragment_zj.OnWheelDialogListener() {
                        @Override
                        public void onClickLeft(DialogFragment dialog, String value) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickRight(DialogFragment dialog, String value) {
                            dialog.dismiss();
                            for (int i = 0; i < infoTypeList.size(); i++) {
                                if (value.equals(infoTypeList.get(i).getname())) {
                                    xinxiType.setText(infoTypeList.get(i).getcode());
                                    XinxiStr = infoTypeList.get(i).getcode();
                                }
                            }
                        }

                        @Override
                        public void onValueChange(DialogFragment dialog, String value) {

                        }
                    });

                    dialogFragment1.show(getFragmentManager(), "");
                } catch (Exception e) {
                    e.toString();

                }
                break;
            case R.id.start_time:
                try {
                    TypeList.clear();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_BACK, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                    bundle.putString(WheelDialogFragment2.DIALOG_LEFT, "取消");
                    bundle.putString(WheelDialogFragment2.DIALOG_RIGHT, "确定");
                    //   bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, ResUtil.getStringArray(R.array.main_home_menu));
                    TypeList.clear();
                    TypeList.add("2010");
                    TypeList.add("2011");
                    TypeList.add("2012");
                    TypeList.add("2013");
                    TypeList.add("2014");
                    TypeList.add("2015");
                    TypeList.add("2016");
                    TypeList.add("2017");
                    TypeList.add("2018");
                    TypeList.add("2019");
                    bundle.putStringArray(WheelDialogFragment2.DIALOG_WHEEL, (String[]) TypeList.toArray(new String[TypeList.size()]));
                    // dropdownButton2.setData(types,"类型",text_title,edit_view_type);
                    // dropdownButton_policyType.setData(names,"全部法规",null,edit_view_class);
                    WheelDialogFragment_zj dialogFragment1 = WheelDialogFragment_zj.newInstance(WheelDialogFragment_zj.class, bundle);
                    dialogFragment1.setWheelDialogListener(new WheelDialogFragment_zj.OnWheelDialogListener() {
                        @Override
                        public void onClickLeft(DialogFragment dialog, String value) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickRight(DialogFragment dialog, String value) {
                            dialog.dismiss();
                            sevenDay.setTextColor(Color.BLACK);
                            SSDay.setTextColor(Color.BLACK);
                            HalfYear.setTextColor(Color.BLACK);

                            startTime.setText(value);
                            StartTimeStr = value + "-01-01";
                        }

                        @Override
                        public void onValueChange(DialogFragment dialog, String value) {
                            //     Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialogFragment1.show(getFragmentManager(), "");
                } catch (Exception e) {
                    e.toString();

                }
                break;
            case R.id.end_time:

                try {
                    TypeList.clear();
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_BACK, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE, false);
                    bundle.putBoolean(WheelDialogFragment2.DIALOG_CANCELABLE_TOUCH_OUT_SIDE, false);
                    bundle.putString(WheelDialogFragment2.DIALOG_LEFT, "取消");
                    bundle.putString(WheelDialogFragment2.DIALOG_RIGHT, "确定");
                    //   bundle.putStringArray(WheelDialogFragment.DIALOG_WHEEL, ResUtil.getStringArray(R.array.main_home_menu));
                    TypeList.clear();

                    TypeList.add("2010");
                    TypeList.add("2011");
                    TypeList.add("2012");
                    TypeList.add("2013");
                    TypeList.add("2014");
                    TypeList.add("2015");
                    TypeList.add("2016");
                    TypeList.add("2017");
                    TypeList.add("2018");
                    TypeList.add("2019");
                    bundle.putStringArray(WheelDialogFragment2.DIALOG_WHEEL, (String[]) TypeList.toArray(new String[TypeList.size()]));
                    // dropdownButton2.setData(types,"类型",text_title,edit_view_type);
                    // dropdownButton_policyType.setData(names,"全部法规",null,edit_view_class);
                    WheelDialogFragment_zj dialogFragment1 = WheelDialogFragment_zj.newInstance(WheelDialogFragment_zj.class, bundle);
                    dialogFragment1.setWheelDialogListener(new WheelDialogFragment_zj.OnWheelDialogListener() {
                        @Override
                        public void onClickLeft(DialogFragment dialog, String value) {
                            dialog.dismiss();
                        }

                        @Override
                        public void onClickRight(DialogFragment dialog, String value) {
                            dialog.dismiss();
                            sevenDay.setTextColor(Color.BLACK);
                            SSDay.setTextColor(Color.BLACK);
                            HalfYear.setTextColor(Color.BLACK);

                            endTime.setText(value);
                            endTimeStr = value + "-12-25";
                        }

                        @Override
                        public void onValueChange(DialogFragment dialog, String value) {
                            //     Toast.makeText(getApplicationContext(), value, Toast.LENGTH_SHORT).show();
                        }
                    });

                    dialogFragment1.show(getFragmentManager(), "");
                } catch (Exception e) {
                    e.toString();

                }
                break;
            case R.id.result:
                result();
                break;
            case R.id.sure:
                drawRel.closeDrawers();
                PurchaseInfoSearchList.clear();
                pageNum = 1;
                changeState();
                for (int i = 0; i < sourceList.size(); i++) {
                    if (ResoureStr.equals(sourceList.get(i).getcode())) {
                        titleRecyclerAdapter.setSelection(i);
                        rvResouce.smoothScrollToPosition(i);
                    }
                }
                httpData_RecyclerView("", editText.getText().toString(), "", "", "");
                break;
            case R.id.seven_Day:
                sevenDay.setTextColor(getResources().getColor(R.color.yellow));
                SSDay.setTextColor(Color.BLACK);
                HalfYear.setTextColor(Color.BLACK);

                String oldDate = getOldDate(-7);
                StartTimeStr = oldDate;
                endTimeStr = t1;
                break;
            case R.id.SS_Day:
                sevenDay.setTextColor(Color.BLACK);
                SSDay.setTextColor(getResources().getColor(R.color.yellow));
                HalfYear.setTextColor(Color.BLACK);
                String oldDate1 = getOldMouth(-1);
                StartTimeStr = oldDate1;
                endTimeStr = t1;
                break;
            case R.id.Half_year:
                sevenDay.setTextColor(Color.BLACK);
                SSDay.setTextColor(Color.BLACK);
                HalfYear.setTextColor(getResources().getColor(R.color.yellow));
                String oldDate2 = getOldMouth(-6);

                StartTimeStr = oldDate2;
                endTimeStr = t1;
                break;
            case R.id.cur_city_re_get_location_tv:

                curCityNameTv.setText("全国");
                address = curCityNameTv.getText().toString();
                break;
        }
    }

    private void result() {
        address = "全国";
        resoure.setText("来源");
        xinxiType.setText("类型");
        startTime.setText("    ");
        endTime.setText("    ");

        StartTimeStr = "";
        endTimeStr = "";
        ResoureStr = "";
        XinxiStr = "";
        curCityNameTv.setText(address);
        SSDay.setTextColor(Color.BLACK);
        HalfYear.setTextColor(Color.BLACK);
        sevenDay.setTextColor(Color.BLACK);
    }


    class MyItemClick implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            groupAdapter.setSelectedPosition(position);

            //   if (childAdapter == null) {
            childAdapter = new ChildAdapter(getContext());
            childListView.setAdapter(childAdapter);
            //    }

            Message msg = new Message();
            msg.what = 20;
            msg.arg1 = position;
            handler.sendMessage(msg);
        }

    }

    private void visibleKeyBoard() {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
//        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0) ;
    }

    private void unvisibleKeyBoard() {
        InputMethodManager imm = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        //  imm.toggleSoftInput(0, InputMethodManager.HIDE_IMPLICIT_ONLY);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    private PopupWindow popupWindow_cache;
    private View contentView_cache;

    public void initPopuWindow_cache() {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.5f;
        getActivity().getWindow().setAttributes(lp);
        View view = View.inflate(getActivity(), R.layout.delete_alert_layout, null);
        final PopupWindow popupWindow = new PopupWindow(view, getActivity().getWindowManager().getDefaultDisplay().getWidth() / 4 * 3, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(true);
        TextView title = view.findViewById(R.id.delete_title);
        Button dismiss = view.findViewById(R.id.dismiss_btn);
        Button sure = view.findViewById(R.id.sure_btn);
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
        title.setText("确定删除全部搜索记录吗？");
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeAll();
                popupWindow.dismiss();
            }
        });
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });

    }

    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getActivity().getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //虚拟键盘也透明
            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    private void initListener() {

    }

    /**
     * 总城市适配器
     */
    private class CityListAdapter extends BaseAdapter {
        private Context context;
        private List<CityEntity> totalCityList;
        private List<CityEntity> hotCityList;
        private LayoutInflater inflater;
        final int VIEW_TYPE = 3;

        CityListAdapter(Context context,
                        List<CityEntity> totalCityList,
                        List<CityEntity> hotCityList) {
            this.context = context;
            this.totalCityList = totalCityList;
            this.hotCityList = hotCityList;
            inflater = LayoutInflater.from(context);

            alphaIndexer = new HashMap<>();

            for (int i = 0; i < totalCityList.size(); i++) {
                // 当前汉语拼音首字母
                String currentStr = totalCityList.get(i).getKey();
                String previewStr = (i - 1) >= 0 ? totalCityList.get(i - 1).getKey() : " ";
                if (!previewStr.equals(currentStr)) {
                    String name = getAlpha(currentStr);
                    alphaIndexer.put(name, i);
                }
            }
        }

        @Override
        public int getViewTypeCount() {
            return VIEW_TYPE;
        }

        @Override
        public int getItemViewType(int position) {
            return position < 2 ? position : 2;
        }

        @Override
        public int getCount() {
            return totalCityList == null ? 0 : totalCityList.size();
        }

        @Override
        public Object getItem(int position) {
            return totalCityList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final TextView curCityNameTv;
            ViewHolder holder;
            int viewType = getItemViewType(position);
            if (null == convertView) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.city_list_item_layout, null);
                ViewBinder.bind(holder, convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CityEntity cityEntity = totalCityList.get(position);
            holder.cityKeyTv.setVisibility(View.VISIBLE);
            holder.cityKeyTv.setText(getAlpha(cityEntity.getKey()));
            holder.cityNameTv.setText(cityEntity.getName());

            if (position >= 1) {
                CityEntity preCity = totalCityList.get(position - 1);
                if (preCity.getKey().equals(cityEntity.getKey())) {
                    holder.cityKeyTv.setVisibility(View.GONE);
                } else {
                    holder.cityKeyTv.setVisibility(View.VISIBLE);
                }
            }
            if (cityEntity.getName().equals("定位") || cityEntity.getName().equals("热门")) {
                holder.cityKeyTv.setVisibility(View.GONE);
                holder.cityNameTv.setVisibility(View.GONE);
            }
            return convertView;
        }

        private class ViewHolder {
            @com.ansiyida.cgjl.binding.Bind(R.id.city_name_tv)
            TextView cityNameTv;
            @com.ansiyida.cgjl.binding.Bind(R.id.city_key_tv)
            TextView cityKeyTv;
        }
    }

    /**
     * 热门城市适配器
     */
    private class HotCityListAdapter extends BaseAdapter {

        private List<CityEntity> cityEntities;
        private LayoutInflater inflater;

        HotCityListAdapter(Context mContext, List<CityEntity> cityEntities) {
            this.cityEntities = cityEntities;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return cityEntities == null ? 0 : cityEntities.size();
        }

        @Override
        public Object getItem(int position) {
            return cityEntities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (null == convertView) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.city_list_grid_item_layout, null);
                ViewBinder.bind(holder, convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            CityEntity cityEntity = cityEntities.get(position);

            holder.cityNameTv.setText(cityEntity.getName());
            return convertView;
        }

        private class ViewHolder {
            @com.ansiyida.cgjl.binding.Bind(R.id.city_list_grid_item_name_tv)
            TextView cityNameTv;
        }
    }

    /**
     * 搜索城市列表适配器
     */
    public class SearchCityListAdapter extends BaseAdapter {

        private List<CityEntity> cityEntities;
        private LayoutInflater inflater;

        SearchCityListAdapter(Context mContext, List<CityEntity> cityEntities) {
            this.cityEntities = cityEntities;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return cityEntities == null ? 0 : cityEntities.size();
        }

        @Override
        public Object getItem(int position) {
            return cityEntities.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (null == convertView) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.city_list_item_layout, null);
                ViewBinder.bind(holder, convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            CityEntity cityEntity = cityEntities.get(position);
            holder.cityKeyTv.setVisibility(View.GONE);
            holder.cityNameTv.setText(cityEntity.getName());
            return convertView;
        }


        private class ViewHolder {
            @com.ansiyida.cgjl.binding.Bind(R.id.city_name_tv)
            TextView cityNameTv;
            @com.ansiyida.cgjl.binding.Bind(R.id.city_key_tv)
            TextView cityKeyTv;
        }
    }

    /**
     * 获得首字母
     */
    private String getAlpha(String key) {
        if (key.equals("0")) {
            return "";
        } else if (key.equals("1")) {
            return "";
        } else {
            return key;
        }
    }

    /**
     * 初始化全部城市列表
     */
    public void initTotalCityList() {
        hotCityList.clear();
        totalCityList.clear();
        curCityList.clear();

        String cityListJson = JsonReadUtil.getJsonStr(getActivity(), CityFileName);
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(cityListJson);
            JSONArray array = jsonObject.getJSONArray("City");
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                String name = object.getString("name");
                String key = object.getString("key");
                String pinyin = object.getString("full");
                String first = object.getString("first");
                String cityCode = object.getString("code");

                CityEntity cityEntity = new CityEntity();
                cityEntity.setName(name);
                cityEntity.setKey(key);
                cityEntity.setPinyin(pinyin);
                cityEntity.setFirst(first);
                cityEntity.setCityCode(cityCode);

                if (key.equals("热门")) {
                    hotCityList.add(cityEntity);
                } else {
                    if (!cityEntity.getKey().equals("0") && !cityEntity.getKey().equals("1")) {
                        curCityList.add(cityEntity);
                    }
                    totalCityList.add(cityEntity);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化汉语拼音首字母弹出提示框
     */
    private void initOverlay() {
        mReady = true;
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        overlay = (TextView) inflater.inflate(R.layout.overlay, null);
        overlay.setVisibility(View.INVISIBLE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                PixelFormat.TRANSLUCENT);
        WindowManager windowManager = (WindowManager) getActivity()
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.addView(overlay, lp);
    }

    /**
     * 设置overlay不可见
     */
    public class OverlayThread implements Runnable {
        @Override
        public void run() {
            overlay.setVisibility(View.GONE);
        }
    }

    private class LetterListViewListener implements LetterListView.OnTouchingLetterChangedListener {
        @Override
        public void onTouchingLetterChanged(final String s) {
            isScroll = false;
            if (alphaIndexer.get(s) != null) {
                int position = alphaIndexer.get(s);
                totalCityLv.setSelection(position);
                overlay.setText(s);
                overlay.setVisibility(View.VISIBLE);
                handler.removeCallbacks(overlayThread);
                // 延迟让overlay为不可见
                handler.postDelayed(overlayThread, 700);
            }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == SCROLL_STATE_TOUCH_SCROLL
                || scrollState == SCROLL_STATE_FLING) {
            isScroll = true;
        } else {
            isScroll = false;
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int i, int i1, int i2) {

    }

    public static String getOldDate(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.DATE, date.get(Calendar.DATE) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    public static String getOldMouth(int distanceDay) {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate = new Date();
        Calendar date = Calendar.getInstance();
        date.setTime(beginDate);
        date.set(Calendar.MONTH, date.get(Calendar.MONTH) + distanceDay);
        Date endDate = null;
        try {
            endDate = dft.parse(dft.format(date.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dft.format(endDate);
    }

    public void getSource() {
        sourceList.clear();
        Call<sourceTypeBean> call = MyApplication.getNetApi().getsource((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""));
        call.enqueue(new Callback<sourceTypeBean>() {

            @Override
            public void onResponse(Call<sourceTypeBean> call, Response<sourceTypeBean> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        sourceList.addAll(response.body().getData());
                        titleRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<sourceTypeBean> call, Throwable t) {

            }
        });
    }

    public void getInfoType() {
        infoTypeList.clear();
        Call<infoTypeBeanBean> call = MyApplication.getNetApi().getinfoType((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""));
        call.enqueue(new Callback<infoTypeBeanBean>() {
            @Override
            public void onResponse(Call<infoTypeBeanBean> call, Response<infoTypeBeanBean> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData() != null) {
                        infoTypeList.addAll(response.body().getData());
                        titleRecyclerAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<infoTypeBeanBean> call, Throwable t) {

            }
        });
    }

    public void onMove() {
        int width = rvResouce.getWidth();//获取控件的宽度
        int[] viewLocation = new int[2];
        rvResouce.getLocationInWindow(viewLocation);
        int viewX = viewLocation[0]; // x 坐标
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int WMwidth = wm.getDefaultDisplay().getWidth();
        int abs = Math.abs((viewX - WMwidth));
        if (abs <= 100) {
            translateAnimation = new TranslateAnimation(1, -(width), 1, 1);
            translateAnimation(-(width));
            if (!ResoureStr.equals("")) {
                for (int i = 0; i < sourceList.size(); i++) {
                    if (ResoureStr.equals(sourceList.get(i).getcode())) {
                        titleRecyclerAdapter.setSelection(i);
                        rvResouce.smoothScrollToPosition(i);
                    }
                }
            }
        } else if (abs >= 100) {
            translateAnimation = new TranslateAnimation(1, width, 1, 1);
            translateAnimation(width);
        }
    }

    private void translateAnimation(final float i) {
        translateAnimation.setDuration(100);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                //animation.
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                int left = (rel.getLeft() + (int) (i - 1));
                int top = rel.getTop();
                int width = rel.getWidth();
                int height = rel.getHeight();
                rel.clearAnimation();
                rel.layout(left + 1, top, left + width, top + height);//移动后返回的坐标位置
            }
        });
        rel.startAnimation(translateAnimation);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 200) {
            judgeOnMove();
        }
    }

    public void judgeOnMove() {
        //判断来源是否在外面
        int[] viewLocation = new int[2];
        rvResouce.getLocationInWindow(viewLocation);
        int viewX = viewLocation[0]; // x 坐标
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        int WMwidth = wm.getDefaultDisplay().getWidth();
        int abs = Math.abs((viewX - WMwidth));
        if (abs >= 100) {
            onMove();
        }
    }
}
