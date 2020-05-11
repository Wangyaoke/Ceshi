package com.ansiyida.cgjl.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.cgjl_activity.AuditCenterActivity;
import com.ansiyida.cgjl.adapter.cgjl_adapter.GridViewAdapter;
import com.ansiyida.cgjl.adapter.cgjl_adapter.ListPopWindowAdapter;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.DropBean;
import com.ansiyida.cgjl.bean.cgjl_bean.OptionChildBean;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.AnimationUtil;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.util.permissions.PermissionListener;
import com.ansiyida.cgjl.util.permissions.PermissionUtil;
import com.ansiyida.cgjl.view.cgjl_view.FloatLayout;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.FormBody;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class recom_self_activity extends BaseActivity {
    @Bind(R.id.lingxian)
    EditText lingxian;
    @Bind(R.id.recom_gridview)
    GridView recomGridview;
    @Bind(R.id.image_back)
    ImageView imageBack;
    @Bind(R.id.text_title)
    TextView textTitle;
    @Bind(R.id.relative_top)
    RelativeLayout relativeTop;
    @Bind(R.id.CP_name)
    EditText CPName;
    @Bind(R.id.ywgjz_xing)
    TextView ywgjzXing;
    @Bind(R.id.CP_technology)
    EditText CPTechnology;
    @Bind(R.id.CPtechnology_num)
    TextView CPtechnologyNum;
    @Bind(R.id.CP_hold)
    EditText CPHold;
    @Bind(R.id.Cp_text_lingyu)
    EditText CpTextLingyu;
    @Bind(R.id.cp_xing)
    TextView cpXing;
    @Bind(R.id.CPtechnology_introduce)
    EditText CPtechnologyIntroduce;
    @Bind(R.id.CPtechnology_introduce_num)
    TextView CPtechnology_introduce_num;
    @Bind(R.id.contact_one)
    EditText contactOne;
    @Bind(R.id.contact_two)
    EditText contactTwo;
    @Bind(R.id.contact_three)
    EditText contactThree;
    @Bind(R.id.text_save)
    TextView textSave;
    @Bind(R.id.scroll)
    ScrollView scrollView;
    private PermissionUtil permissionUtil;
    private Bitmap bitmap;
    private List<DropBean> names;
    private Bundle bundle;
    private List<String> lingyu;
    //存放照片
    private List<String> mPicList = new ArrayList<>();
    private GridViewAdapter mGridViewAddImgAdapter;
    private int dropNum = 123;
    private boolean judgeNull;
    private String phone;
    private List<String> list = new ArrayList<>();
    private String[][] initlist;
    private List<String>qyxjList = new ArrayList<>();
    private ListPopupWindow listPopupWindow;
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Gson gson = new Gson();
            boolean contains = ((String) msg.obj).contains("成功");
            if(contains){
                popwindow();
            }else{
                ToastUtils.showMessage(recom_self_activity.this,"提交失败！");
            }
        }
    };
    private ListPopWindowAdapter listPopWindowAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GoogleAssistant(recom_self_activity.this,"Android产品自荐","recom_self_activity");
    }

    @Override
    protected int getContentView() {
        return R.layout.recom_layout;
    }

    @Override
    protected void initView() {
        getQYXJ();
        textTitle.setText("产品自荐");
        CpTextLingyu.setText("请选择专业领域");
        permissionUtil = new PermissionUtil(this);
        //初始化gridview
        mGridViewAddImgAdapter = new GridViewAdapter(recom_self_activity.this, mPicList);
        recomGridview.setAdapter(mGridViewAddImgAdapter);

        listPopupWindow = new ListPopupWindow(this);
        listPopWindowAdapter = new ListPopWindowAdapter(qyxjList, this);
        listPopupWindow.setAdapter(listPopWindowAdapter);
    }

    @Override
    protected void initData() {
        initlist = initlist();
    }

    @Override
    protected void httpData() {
        if(judgeNull) {
            //只保留一个手机号
            if (!contactOne.getText().toString().isEmpty()) {
                phone = contactOne.getText().toString();
            } else if (!contactTwo.getText().toString().isEmpty()) {
                phone = contactTwo.getText().toString();
            } else if (!contactThree.getText().toString().isEmpty()) {
                phone = contactThree.getText().toString();
            }
            String picStr = new String();
            for (int i = 0; i < mPicList.size(); i++) {
                Log.e("picStr", mPicList.get(i) );
                if(picStr.isEmpty()) {
                    picStr =mPicList.get(i)+",";
                }else{
                    picStr = picStr + mPicList.get(i)+",";
                }
            }
            if (SharedPreferenceUtils.get(this, "vistor", "").equals("true")) {
                FormBody build = new FormBody.Builder()
                        .add("title", CPName.getText().toString())
                        .add("img", picStr)
                        .add("companyName", CPHold.getText().toString())
                        .add("majorField", CpTextLingyu.getText().toString())
                        .add("degree", lingxian.getText().toString())
                        .add("productKeyword", CPTechnology.getText().toString())
                        .add("productSummary", CPtechnologyIntroduce.getText().toString())
                        .add("contact", phone)
                        .add("parentId", "")
                        .build();
                String format = String.format(Constant.URL + "product");
                Request build1 = new Request.Builder()
                        .addHeader("token", (String) SharedPreferenceUtils.get(recom_self_activity.this, "app_token", ""))
                        .url(format)
                        .post(build)
                        .build();
                MyApplication.client.newCall(build1).enqueue(new okhttp3.Callback() {
                    @Override
                    public void onFailure(okhttp3.Call call, IOException e) {
                        Log.e("onResponse", "onResponse: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(okhttp3.Call call, okhttp3.Response response) throws IOException {
                        handler.sendMessageDelayed(handler.obtainMessage(0, response.body().string()), 500);
                    }
                });
            }else{
                ToastUtils.showMessage(this,"请先登录");
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void setClickListener() {
        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lingxian.setText(qyxjList.get(position));
                listPopupWindow.dismiss();
            }
        });
        recomGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position != mPicList.size() - 1) {
                    if(position>5){
                    }else {
                        if (mPicList.size() < 5) {
                            initPopuWindow_dropMenu();
                        }
                        if (mPicList.size() == 2) {
                            ViewGroup.LayoutParams params = recomGridview.getLayoutParams();
                            params.height = 700;
                            recomGridview.setLayoutParams(params);
                        }
                    }
                }
            }
        });
        mGridViewAddImgAdapter.setGridViewListnear(new GridViewAdapter.GridViewListnear() {
            @Override
            public void removeImg(int position) {
                mPicList.remove(position);
                mGridViewAddImgAdapter.notifyDataSetChanged();
                if(mPicList.size()==2){
                    ViewGroup.LayoutParams params = recomGridview.getLayoutParams();
                    params.height = 350;
                    recomGridview.setLayoutParams(params);
                }
            }
        });
        lingxian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listPopupWindow.setAnchorView(lingxian);
                listPopupWindow.setWidth(lingxian.getMeasuredWidth());
                listPopWindowAdapter.notifyDataSetChanged();
                listPopupWindow.show();
            }
        });
        recomGridview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        CPTechnology.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 100) { //判断EditText中输入的字符数是不是已经大于61
                    CPTechnology.setText(s.toString().substring(0, 100)); //设置EditText只显示前面6位字符
                    CPTechnology.setSelection(100);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 100 - number;
                if (lenght > 0) {
                    CPtechnologyNum.setText(lenght + "");
                    CPtechnologyNum.setTextColor(getResources().getColor(R.color.back_dark));
                } else {
                    CPtechnologyNum.setText("最多一百字!");
                }
            }
        });
        CPtechnologyIntroduce.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 500) { //判断EditText中输入的字符数是不是已经大于61
                    CPtechnologyIntroduce.setText(s.toString().substring(0, 500)); //设置EditText只显示前面6位字符
                    CPtechnologyIntroduce.setSelection(500);//让光标移至末端
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                int number = s.length();
                int lenght = 500 - number;
                if (lenght > 0) {
                    CPtechnology_introduce_num.setText(lenght + "");
                    CPtechnology_introduce_num.setTextColor(getResources().getColor(R.color.back_dark));
                } else {
                    CPtechnology_introduce_num.setText("最多五百字!");
                }
            }
        });
        CPTechnology.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(CPTechnology.getText().length()>0) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });
        CPtechnologyIntroduce.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(CPtechnologyIntroduce.getText().length()>0) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                }
                return false;
            }
        });

    }

    @OnClick({R.id.image_back, R.id.Cp_text_lingyu, R.id.text_save})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.text_save:                 //5.提交
                judgeNull = judgeNull();
                if(judgeNull){
                    httpData();
                }
                break;
            case R.id.image_back:               //1.返回上一级
                this.finish();
                break;
            case R.id.Cp_text_lingyu:
                ZYLYPop();
                break;
            default:
                break;

        }
    }

    private boolean judgeNull() {
        if (!CPName.getText().toString().isEmpty() && !CPTechnology.getText().toString().isEmpty() && !CPHold.getText().toString().isEmpty()
                 && !CPtechnologyIntroduce.getText().toString().isEmpty() &&( !contactOne.getText().toString().isEmpty() || !contactTwo.getText().toString().isEmpty() || !contactThree.getText().toString().isEmpty())
        ) {
            if(CpTextLingyu.getText().toString().equals("请选择专业领域")){
                ToastUtils.showMessage(this,"请选择专业领域");
                return false;
            }
            if(lingxian.getText().toString().equals("请选择先进程度")){
                ToastUtils.showMessage(this,"请选择先进程度");
                return false;
            }
            return true;
        } else{
            ToastUtils.showMessage(recom_self_activity.this, "以上信息均需要全部填写完毕。");
            return  false;
        }
    }
    /**
     * 删除弹窗
     */
    private PopupWindow popupWindow_cache;
    private View contentView_cache;

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i("pic", "requestCode:" + requestCode + ",resultCode：" + resultCode);
        if (data != null) {
            if (requestCode == 3000) {//截取照片后
                if (data != null) {
                    if (saveCutPhoto(data)) {
                        bitmap = BitmapFactory.decodeFile(Constant.picPath_cp + "cp1.jpg");
                    }
                }
            } else if (requestCode == 7458) {
                //相册
                List<String> path = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                for (int i = 0; i <path.size() ; i++) {
                    String file = getRealPathFromURI(Uri.parse(path.get(i)));
                    mPicList.add(file);
                }

                mGridViewAddImgAdapter.notifyDataSetChanged();

            } else if (requestCode == 7459) {
                //拍照
                Bundle bundle = data.getExtras();  //bundle 可以存放键值对
                Bitmap bm = (Bitmap) bundle.get("data");
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), bm, null, null));
                String file = getRealPathFromURI(uri);
                mPicList.add(file);
                mGridViewAddImgAdapter.notifyDataSetChanged();
            }
        }
    }


    public boolean saveCutPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            LogUtil.i("pic", "extras不是null");
            //新建文件夹 先选好路径 再调用mkdir函数 现在是根目录下面的Ask文件夹
            Bitmap photo = extras.getParcelable("data");
            File nf = new File(Constant.picPath_cp);
            LogUtil.i("pic", "picPath:" + Constant.picPath_cp);
            if (!nf.exists()) {
                nf.mkdirs();//创建文件夹
            }
            //在根目录下面的ASk文件夹下 创建okkk.jpg文件
            File f = new File(nf, "cp1.jpg");
            FileOutputStream out = null;
            try {//打开输出流 将图片数据填入文件中
                out = new FileOutputStream(f);
                if (out != null && photo != null) {
                    photo.compress(Bitmap.CompressFormat.JPEG, 80, out);
                } else {
                    LogUtil.i("pic", "photo是null");
                    return false;
                }
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            LogUtil.i("pic", "extras是null");
        }
        return true;
    }

    void returnResult(String sdata) {

    }

    /**
     * 上传头像方式框
     */
    private PopupWindow popupWindow1;
    private View contentView1;

    private void initPopuWindow_dropMenu() {
        if (popupWindow1 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(recom_self_activity.this);
            contentView1 = mLayoutInflater.inflate(R.layout.popwindow_dropup, null);
            popupWindow1 = new PopupWindow(contentView1, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        RelativeLayout relativeLayout = (RelativeLayout) contentView1.findViewById(R.id.relative);
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endAnimation();
            }
        });
        Button cancle = (Button) contentView1.findViewById(R.id.cancel_btn);
        Button paiZhao = (Button) contentView1.findViewById(R.id.paiZhao_btn);
        Button xiangCe = (Button) contentView1.findViewById(R.id.xiangCe_btn);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endAnimation();
            }
        });
        paiZhao.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                permissionUtil.requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        Intent intent = new Intent();
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);   //拍照界面的隐式意图
                        startActivityForResult(intent, 7459);
                        popupWindow1.dismiss();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        ToastUtils.showMessage(recom_self_activity.this, "您刚拒绝了一个必要权限");

                    }

                    @Override
                    public void onShouldShowRationale(List<String> deniedPermission) {
                        LogUtil.i("permisson", "-----3----");

                    }
                });

            }
        });
        xiangCe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionUtil.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        /*LogUtil.i("permisson", "-----1----");
                        EasyImage.openGalleryPicker(recom_self_activity.this);*/
                        Intent intent = new Intent(recom_self_activity.this, MultiImageSelectorActivity.class);
                        // whether show camera
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                        // max select image amount
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 5-mPicList.size());//选择图片张数
                        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
                        // default select images (support array list)
                        intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, new ArrayList<String>());
                        startActivityForResult(intent, 7458);
                        popupWindow1.dismiss();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        ToastUtils.showMessage(recom_self_activity.this, "您刚拒绝了一个必要权限");

                    }

                    @Override
                    public void onShouldShowRationale(List<String> deniedPermission) {
                        LogUtil.i("permisson", "-----3----");

                    }
                });
            }
        });
        //产生背景变暗效果

        WindowManager.LayoutParams lp = recom_self_activity.this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        recom_self_activity.this.getWindow().setAttributes(lp);
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
//        popupWindow1.showAsDropDown(jiuCuo);
        popupWindow1.showAtLocation(contentView1, Gravity.CENTER, 0, 0);
        popupWindow1.update();
        popupWindow1.getContentView().startAnimation(AnimationUtil.createInAnimation(recom_self_activity.this, 300));
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = recom_self_activity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                recom_self_activity.this.getWindow().setAttributes(lp);
            }
        });
    }

    private Animation animationEnd;

    private void endAnimation() {
        if (animationEnd == null) {
            animationEnd = AnimationUtil.createOutAnimation(recom_self_activity.this, 500);
            animationEnd.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    popupWindow1.dismiss();
                    animation.cancel();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        popupWindow1.getContentView().startAnimation(animationEnd);
    }

    //uri转换为地址
    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = null;
        try {
            cursor = getContentResolver().query(contentURI, null, null, null, null);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        if (cursor == null) {
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    public void popwindow(){
        View view = View.inflate(this,R.layout.pop_ok_animation,null);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double  width = (double) dm.widthPixels/1.5;
        final PopupWindow popupWindow  = new PopupWindow(view, (int) width, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setOutsideTouchable(true);
        //关闭事件
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(recom_self_activity.this,(float)1);
                finish();
            }
        });
        ImageView pop_ok_image80 = view.findViewById(R.id.pop_ok_image80);
        ImageView pop_ok_image = view.findViewById(R.id.pop_ok_image);
        TextView AuditCenter = view.findViewById(R.id.go_AuditCenter);
        ImageView dismissImage = view.findViewById(R.id.dismiss_pop_ok);
        pop_ok_image80.setVisibility(View.VISIBLE);
        pop_ok_image.setVisibility(View.GONE);
        backgroundAlpha(this,(float) 0.5);
        dismissImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                backgroundAlpha(recom_self_activity.this,(float)1);
                finish();
            }
        });
        AuditCenter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(recom_self_activity.this, AuditCenterActivity.class);
                startActivity(intent);
            }
        });
        //popupWindow.showAsDropDown(FRRepresentative);
        popupWindow.showAtLocation(CPName, Gravity.CENTER,0,0);
        //开启动画
        pop_ok_image80.setImageResource(R.drawable.lottery_animlist);
        AnimationDrawable animationDrawable = (AnimationDrawable) pop_ok_image80.getDrawable();
        animationDrawable.start();
    }
    public void backgroundAlpha(Activity context, float bgAlpha)
    {
        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        context.getWindow().setAttributes(lp);
    }
    public void ZYLYPop(){
        View view = View.inflate(this,R.layout.zyly_popwindow,null);
        DisplayMetrics dm = getResources().getDisplayMetrics();
        double  width = (double) dm.widthPixels/1.5;
        final PopupWindow popupWindow  = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setOutsideTouchable(true);
        backgroundAlpha(recom_self_activity.this,(float) 0.5);
        final FloatLayout myFloatLayout = view.findViewById(R.id.MyFloatLayout);
        Button qx_btn = view.findViewById(R.id.qx_btn);
        Button qd_btn = view.findViewById(R.id.qd_btn);
        myFloatLayout.setAdapter(initlist,list);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(recom_self_activity.this,(float) 1);
            }
        });
        qx_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
        qd_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
                list = myFloatLayout.getList();
                String s = "";
                for (int i = 0; i <list.size() ; i++) {
                    if(s.equals("")){
                        s=list.get(i);
                    }else{
                        s = s+" "+list.get(i);
                    }
                }
                CpTextLingyu.setText(s);
            }
        });
        //关闭事件
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.showAtLocation(contactTwo, Gravity.CENTER,0,0);
    }

    private String [][] initlist() {
        final String[][] userArray = new String[1][1];
        Call<OptionChildBean> call = MyApplication.getNetApi().getoptioAll((String)SharedPreferenceUtils.get(this,"app_token",""),29);
        call.enqueue(new Callback<OptionChildBean>() {
            @Override
            public void onResponse(Call<OptionChildBean> call, Response<OptionChildBean> response) {
                if(response.isSuccessful()){
                    OptionChildBean body = response.body();
                    if(body.getStatus() == 200){
                        int z=0;
                        userArray[0] = new String[50];
                        List<OptionChildBean.DataBean.ChildrenBeanX> children1 = body.getData().getChildren();
                        for (int i = 0; i <children1.size(); i++) {
                            List<OptionChildBean.DataBean.ChildrenBeanX.ChildrenBean> children2 = body.getData().getChildren().get(i).getChildren();
                            for (int k = 0; k <children2.size(); k++) {
                                userArray[0][z++] = children2.get(k).getName();
                            }
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<OptionChildBean> call, Throwable t) {

            }
        });
        return userArray;
    }
    private void getQYXJ(){
        Call<OptionChildBean> call = MyApplication.getNetApi().getoptioAll((String)SharedPreferenceUtils.get(this,"app_token",""),4);
        call.enqueue(new Callback<OptionChildBean>() {
            @Override
            public void onResponse(Call<OptionChildBean> call, Response<OptionChildBean> response) {
                if(response.isSuccessful()){
                    OptionChildBean body = response.body();
                    if(body.getStatus() == 200){
                        for (int i = 0; i < body.getData().getChildren().size(); i++) {
                            qyxjList.add(body.getData().getChildren().get(i).getName());
                        }
                        names = new ArrayList<>();
                        lingyu = new ArrayList<>();
                        for (int i = 0; i <qyxjList.size(); i++) {
                            names.add(new DropBean(qyxjList.get(i)));;
                            Log.e("产品自荐先进程度", "initView: "+qyxjList.get(i) );
                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<OptionChildBean> call, Throwable t) {

            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}

