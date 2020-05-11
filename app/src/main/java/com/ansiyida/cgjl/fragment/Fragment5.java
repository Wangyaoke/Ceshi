package com.ansiyida.cgjl.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.BuyHistoryActivity;
import com.ansiyida.cgjl.activity.BuyvipActivity;
import com.ansiyida.cgjl.activity.CareActivity;
import com.ansiyida.cgjl.activity.CollectActivity;
import com.ansiyida.cgjl.activity.FansActivity;
import com.ansiyida.cgjl.activity.HistoryActivity;
import com.ansiyida.cgjl.activity.LoginActivity;
import com.ansiyida.cgjl.activity.MineTabActivity;
import com.ansiyida.cgjl.activity.PersonalDynamicActivity;
import com.ansiyida.cgjl.activity.SettingActivity;
import com.ansiyida.cgjl.activity.SubscribeActivity;
import com.ansiyida.cgjl.activity.UserFeedbackActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.AuditCenterActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.IndustrialParkAuditCenterActivity;
import com.ansiyida.cgjl.activity.cgjl_activity.ProjectFollowUpActivity;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.bean.DefaultBean2;
import com.ansiyida.cgjl.bean.UpdateUserMsgBean;
import com.ansiyida.cgjl.bean.UserBean;
import com.ansiyida.cgjl.bean.cgjl_bean.UpdateUserInfo;
import com.ansiyida.cgjl.db.DbMannager;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.ActivityCodeUtil;
import com.ansiyida.cgjl.util.AnimationUtil;
import com.ansiyida.cgjl.util.DeviceUtils;
import com.ansiyida.cgjl.util.FileUtilcll;
import com.ansiyida.cgjl.util.LogUtil;
import com.ansiyida.cgjl.util.NetWorkUtils;
import com.ansiyida.cgjl.util.SharedPreferenceUtils;
import com.ansiyida.cgjl.util.ToastUtils;
import com.ansiyida.cgjl.util.permissions.PermissionListener;
import com.ansiyida.cgjl.util.permissions.PermissionUtil;
import com.ansiyida.cgjl.view.glide_circle_photo.GlideCircleTransform;
import com.bumptech.glide.Glide;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
 * 我的模块
 * */
public class Fragment5 extends BaseFragment {
    @Bind(R.id.relative_message)
    RadioButton rb1;
    @Bind(R.id.linear_readHistory)
    RadioButton rb2;
    @Bind(R.id.linear_college)
    RadioButton rb3;
    @Bind(R.id.icon44_mainAcitivity)
    RadioButton rb4;
    @Bind(R.id.icon5_mainAcitivity)
    RadioButton rb5;
    @Bind(R.id.relative_setting)
    RadioButton rb6;
    @Bind(R.id.icon12_mainAcitivity)
    RadioButton rb7;

    @Bind(R.id.image_touXiang)
    ImageView image_touXiang;
    @Bind(R.id.iv_zhiKuIcon)
    ImageView iv_zhiKuIcon;
    @Bind(R.id.vip)
    ImageView vip;
    @Bind(R.id.mine_redCircle)
    ImageView mine_redCircle;
    @Bind(R.id.text_userName)
    TextView text_userName;
    @Bind(R.id.text_jianJie)
    TextView text_jianJie;
    @Bind(R.id.tab_1)
    TextView tab_1;
    @Bind(R.id.tab_2)
    TextView tab_2;
    @Bind(R.id.tab_3)
    TextView tab_3;
    @Bind(R.id.tab_4)
    TextView tab_4;
    @Bind(R.id.tab_5)
    TextView tab_5;
    @Bind(R.id.tv_careCount)
    TextView tv_careCount;
    @Bind(R.id.tv_fansCount)
    TextView tv_fansCount;
    @Bind(R.id.tv_dynamicCount)
    TextView tv_dynamicCount;
    @Bind(R.id.relative_login)
    RelativeLayout relative_login;
    @Bind(R.id.relative_loginNone)
    RelativeLayout relative_loginNone;

    @Bind(R.id.tv_dyCount)
    TextView tv_dyCount;
    @Bind(R.id.iv_1)
    ImageView iv_1;
    @Bind(R.id.iv_2)
    ImageView iv_2;
    @Bind(R.id.iv_3)
    ImageView iv_3;
    @Bind(R.id.iv_4)
    ImageView iv_4;
    @Bind(R.id.relative_cyy)
    RadioButton relativeCyy;
    @Bind(R.id.relative_gj)
    RadioButton relativeGj;
    private Bitmap bitmap;
    private DbMannager mannager;
    private boolean flag = false;
    private boolean isChangeHead = true;
    private String jianjie;
    private PermissionUtil permissionUtil;
    private String uniqueId;
    private String manufacturer;
    private String uri;

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment5;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        mannager = DbMannager.getInstance();
        permissionUtil = new PermissionUtil(getActivity());
        uniqueId = DeviceUtils.getUniqueId(getActivity());
        setLoginView();
    }

    @Override
    protected void initData() {
        manufacturer = Build.MANUFACTURER;
    }

    public void httpUserInfo() {
        try {
            if (NetWorkUtils.isNetworkConnected(getActivity())) {
                String app_token = (String) SharedPreferenceUtils.get(getActivity(), "app_token", "");
                if ("USER_LOGIN_TOKEN".equals(app_token.substring(0, 16))) {
                    Call<UserBean> call = MyApplication.getNetApi().getUserInfo(((String) SharedPreferenceUtils.get(getActivity(), "app_token", "")));
                    call.enqueue(new Callback<UserBean>() {
                        @Override
                        public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                            if (response.isSuccessful()) {
                                UserBean body = response.body();
                                if ("200".equals(body.getStatus())) {
                                    Log.e("登录", "成功");
                                    if (!"visitor".equals(body.getData().getloginType())) {
                                        UserBean.DataBean member = response.body().getData();
                                        if (member.getuserName() != null && !"".equals(member.getuserName())) {
                                            if (member.getid() != null && !"".equals(member.getid())) {
                                                initTab(member.getid() + "," + member.getuserName());
                                            } else {
                                                initTab(member.getuserName() + "");
                                            }
                                        } else {
                                            if (member.getid() != null && !"".equals(member.getid())) {
                                                initTab(member.getid());
                                            } else {
                                                initTab("");
                                            }
                                        }
                                        //Toast.makeText(getContext(), member.getisActive(), Toast.LENGTH_SHORT).show();
                                        SharedPreferenceUtils.put(getActivity(), "vipLevel", member.getvipLevel());
                                        SharedPreferenceUtils.put(getActivity(), "isActive", member.getisActive());
                                        String type = member.getloginType();
                                        if (type != null) {
                                            SharedPreferenceUtils.put(getActivity(), "jmi_type", type);
                                        }
                                        if (member.getisActive().equals("false")) {
                                            jianjie = "vip_non";
                                            vip.setImageResource(R.mipmap.vip_non);
                                        } else {
                                            jianjie = "vip";
                                            vip.setImageResource(R.mipmap.vip);
                                        }
                                        if (member.getheadImg() == null)
                                            initHead_name_jianJie(jianjie, member.getuserName(), "", member.getphone() + "");
                                        else
                                            initHead_name_jianJie(jianjie, member.getuserName(), member.getheadImg(), member.getphone() + "");
                                        SharedPreferenceUtils.put(getActivity(), "phoneNum", member.getphone() + "");
                                        relative_login.setVisibility(View.VISIBLE);
                                        relative_loginNone.setVisibility(View.GONE);
                                    } else {
                                        relative_login.setVisibility(View.GONE);
                                        relative_loginNone.setVisibility(View.VISIBLE);
                                        SharedPreferenceUtils.put(getContext(), "vistor", "false");
                                        Log.e("登录", "失败");
                                    }
                                } else {
                                    Log.e("登录", "失败");
                                    //请先登录
                                    relative_login.setVisibility(View.GONE);
                                    relative_loginNone.setVisibility(View.VISIBLE);
                                    SharedPreferenceUtils.put(getContext(), "vistor", "false");
                                }
                            } else {
                                Log.e("登录", "请先登录");
                                //请先登录
                                relative_login.setVisibility(View.GONE);
                                relative_loginNone.setVisibility(View.VISIBLE);
                                SharedPreferenceUtils.put(getContext(), "vistor", "false");
                            }
                            flag = true;
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<UserBean> call, Throwable t) {
                            flag = true;
                            String str_app = (String) SharedPreferenceUtils.get(getActivity(), "app_token", "");
                            if ("USER_LOGIN_TOKEN".equals(str_app.substring(0, 16))) {
                                SharedPreferenceUtils.put(getContext(), "vistor", "false");
                                SharedPreferenceUtils.put(getActivity(), "app_token", DeviceUtils.getUniqueId(getActivity()));
                                ToastUtils.showMessage(getActivity(), "您的账号在其他设备登录");
                                relative_login.setVisibility(View.GONE);
                                relative_loginNone.setVisibility(View.VISIBLE);
                            }
                            call.cancel();
                        }
                    });
                } else {

                    final Call<DefaultBean2> call1 = MyApplication.getNetApi().visitor(uniqueId);
                    call1.enqueue(new Callback<DefaultBean2>() {
                        public void onResponse(Call<DefaultBean2> call, Response<DefaultBean2> response) {
                            if (response.isSuccessful()) {
                                SharedPreferenceUtils.put(getActivity(), "app_token", response.body().getdate());
                            }
                        }

                        public void onFailure(Call<DefaultBean2> call, Throwable t) {
                            ToastUtils.showMessage(getActivity(), t.toString());
                            call.cancel();
                        }

                    });

                }

            } else {
                ToastUtils.showMessage(getActivity(), "当前网络不可用");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.toString();
        }
    }

    private void clickTab() {
        rb1.setChecked(false);
        rb2.setChecked(false);
        rb3.setChecked(false);
        rb4.setChecked(false);
        rb5.setChecked(false);
        rb6.setChecked(false);
        rb7.setChecked(false);
        relativeCyy.setChecked(false);

    }

    @Override
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.i("pic", "requestCode:" + requestCode + ",resultCode：" + resultCode);
        if (data != null) {
            if (requestCode == 3) {//截取照片后
                if ("xiaomi".equalsIgnoreCase(manufacturer)) {
                    UploadPicture(Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
                }else{
                    uri = setPicToView(data);
                    UploadPicture(uri);
                }
            } else if (requestCode == 7458) {
                //相册
                Uri data1 = data.getData();
                startPhotoZoom(data1);

            } else if (requestCode == 7459) {
                //拍照
                Bundle bundle = data.getExtras();  //bundle 可以存放键值对
                Bitmap bm = (Bitmap) bundle.get("data");
                Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bm, null, null));
                startPhotoZoom(uri);
            }
        }
    }

    private void UploadPicture(String  s) {
        File file1 = new File(s);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file1);

        //2、创建MultipartBody.Part，其中需要注意第一个参数需要与服务器对应
        final MultipartBody.Part part = MultipartBody.Part.createFormData("file", file1.getName(), requestFile);
        ToastUtils.showMessage(getContext(), "正在上传....");
        Call<UpdateUserMsgBean> call2 = MyApplication.getNetApi().updateTouXiang((String) SharedPreferenceUtils.get(getActivity(), "app_token", ""), part);
        call2.enqueue(new Callback<UpdateUserMsgBean>() {
            @Override
            public void onResponse(Call<UpdateUserMsgBean> call, Response<UpdateUserMsgBean> response) {

                if (response.isSuccessful()) {
                    String data2 = response.body().getData();

                    if (data2 != null) {
                        ToastUtils.showMessage(getActivity(), "上传成功");
                        Glide.with(getActivity()).load(data2).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(getActivity())).into(image_touXiang);
                    }
                    //去修改用户信息，才能刷新头像
                    updateUserInfo(data2, "");
                } else {
                    ToastUtils.showMessage(getActivity(), "上传失败");
                }
                call.cancel();
            }

            @Override
            public void onFailure(Call<UpdateUserMsgBean> call, Throwable t) {

                call.cancel();
                ToastUtils.showMessage(getActivity(), "上传失败");
            }
        });
    }

    private String setPicToView(Intent picdata) {
        Bundle extras = picdata.getExtras();
        if (extras != null) {
            Bitmap photo = extras.getParcelable("data");
            //图片路径
            String urlpath = FileUtilcll.saveFile(getActivity(), "temphead.jpg", photo);

            return urlpath;
        }
        return null;
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        // 调用系统中自带的图片剪裁
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        // 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        //这个字符串可以自己定义,例如判断华为就填写huawei,魅族就填写meizu
        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
            //裁剪后的图片Uri路径，uritempFile为Uri类变量
            Uri uritempFile = Uri.parse("file://" + "/" + Environment.getExternalStorageDirectory().getPath() + "/" + "small.jpg");
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uritempFile);
            intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        }else{
            intent.putExtra("return-data", true);
        }
        startActivityForResult(intent, 3);


    }

    public boolean saveCutPhoto(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras != null) {
            LogUtil.i("pic", "extras不是null");

            //新建文件夹 先选好路径 再调用mkdir函数 现在是根目录下面的Ask文件夹
            Bitmap photo = extras.getParcelable("data");
            File nf = new File(Constant.picPath);
            LogUtil.i("pic", "picPath:" + Constant.picPath);
            if (!nf.exists()) {
                nf.mkdirs();//创建文件夹
            }
            //在根目录下面的ASk文件夹下 创建okkk.jpg文件
            File f = new File(nf, "touxiang.jpg");
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

    @OnClick({R.id.image_touXiang, R.id.relative_setting, R.id.text_edit, R.id.relative_zhiKu, R.id.relative_dingyYue, R.id.icon5_mainAcitivity,
            R.id.relative_feedback, R.id.relative_message, R.id.linear_college, R.id.icon44_mainAcitivity, R.id.icon12_mainAcitivity,
            R.id.linear_readHistory, R.id.linear_guanZhu, R.id.linear_fans, R.id.relative_loginNone, R.id.linear_dynamic, R.id.RoundImageView_loginNone, R.id.relative_cyy,R.id.relative_gj})
    public void click(View view) {
        if (flag) {
            switch (view.getId()) {
                case R.id.image_touXiang:
                    //1.点击进入个人信息编辑
                    String vistor = (String) SharedPreferenceUtils.get(getActivity(), "vistor", "");
                    if ("true".equals(vistor)) {
                        initPopuWindow_dropMenu();
                    } else {
                        getActivity().startActivityForResult(new Intent(getActivity(), LoginActivity.class), ActivityCodeUtil.LOGINACTIVITY);
                    }

                    break;
                case R.id.relative_setting:         //2.系统设置
                    clickTab();
                    getActivity().startActivityForResult(new Intent(getActivity(), SettingActivity.class), ActivityCodeUtil.SETTINGACTIVITY);
                    break;
                case R.id.text_edit:                //3.个人标签“编辑”按钮
                    getActivity().startActivityForResult(new Intent(getActivity(), MineTabActivity.class), ActivityCodeUtil.MINETABACTIVITY);

                    break;
                case R.id.icon12_mainAcitivity:           //4.申请智库号认证
                    clickTab();
                    initPopuWindow_dropMenu("拨打客服电话：010-87654321");
                    LogUtil.i("token", "token-->" + (String) SharedPreferenceUtils.get(getActivity(), "app_token", ""));
                    break;
//            case R.id.relative_keFu:            //5.情报客服
//
//                initPopuWindow_dropMenu("移动端情报客服功能暂未开通，请使用电脑浏览器访问www.jisuanweilai.com.cn完成认证");
//
//                break;
                case R.id.relative_feedback:        //6.用户反馈
                    startActivity(new Intent(getActivity(), UserFeedbackActivity.class));

                    break;
                case R.id.relative_message:         //7.消息通知
                    clickTab();
                    if ("true".equals(((String) SharedPreferenceUtils.get(getActivity(), "vistor", "")))) {
                        getActivity().startActivity(new Intent(getContext(), AuditCenterActivity.class));
                    } else {
                        ToastUtils.showMessage(getActivity(), "请先登录");
                    }

                    break;
                case R.id.icon44_mainAcitivity:         //7.消息通知
                    clickTab();
                    if ("true".equals(((String) SharedPreferenceUtils.get(getActivity(), "vistor", "")))) {
                        getActivity().startActivityForResult(new Intent(getActivity(), BuyvipActivity.class), ActivityCodeUtil.MESSAGEACTIVITY);
                    } else {
                        ToastUtils.showMessage(getActivity(), "请先登录");
                    }

                    break;
                case R.id.linear_college:           //8.收藏
                    try {
                        clickTab();
                        if ("true".equals(((String) SharedPreferenceUtils.get(getActivity(), "vistor", "")))) {
                            Intent intent_college = new Intent(getActivity(), CollectActivity.class);
                            //  intent_college.putExtra("titleName", "收藏");
                            startActivityForResult(intent_college, ActivityCodeUtil.COLLEGEACTIVITY);
                        } else {
                            ToastUtils.showMessage(getActivity(), "请先登录");
                        }

                    } catch (Exception e) {
                        e.toString();
                    }

                    break;
                case R.id.icon5_mainAcitivity:           //8.收藏
                    clickTab();
                    if ("true".equals(((String) SharedPreferenceUtils.get(getActivity(), "vistor", "")))) {
                        Intent intent_college = new Intent(getActivity(), BuyHistoryActivity.class);
                        intent_college.putExtra("titleName", "购买记录");
                        startActivityForResult(intent_college, ActivityCodeUtil.COLLEGEACTIVITY);
                    } else {
                        ToastUtils.showMessage(getActivity(), "请先登录");
                    }


                    break;
                case R.id.linear_readHistory:        //9.阅读历史
                    clickTab();
                    Intent intent_read = new Intent(getActivity(), HistoryActivity.class);
                    startActivityForResult(intent_read, ActivityCodeUtil.HISTORYACTIVITY);
                    break;
                case R.id.linear_guanZhu:           //11.点击关注
                    Intent intent_guanZhu = new Intent(getActivity(), CareActivity.class);
                    getActivity().startActivityForResult(intent_guanZhu, ActivityCodeUtil.CAREACTIVITY);

                    break;
                case R.id.linear_fans:           //11.点击粉丝
                    Intent intent_fans = new Intent(getActivity(), FansActivity.class);
                    getActivity().startActivityForResult(intent_fans, ActivityCodeUtil.FANSACTIVITY);

                    break;
                case R.id.RoundImageView_loginNone:     //12.未登录状态点击
                    getActivity().startActivityForResult(new Intent(getActivity(), LoginActivity.class), ActivityCodeUtil.LOGINACTIVITY);

                    break;
                case R.id.linear_dynamic:          //13.动态
                    getActivity().startActivityForResult(new Intent(getActivity(), PersonalDynamicActivity.class), ActivityCodeUtil.PERSONALDYNAMICACTIVITY);
                    break;
                case R.id.relative_dingyYue:        //14.我的订阅
                    if ("true".equals(((String) SharedPreferenceUtils.get(getActivity(), "vistor", "")))) {
                        getActivity().startActivityForResult(new Intent(getActivity(), SubscribeActivity.class), ActivityCodeUtil.SUBSCRIBEACTIVITY);
                    } else {
                        ToastUtils.showMessage(getActivity(), "请先登录");
                    }
                    break;
                case R.id.relative_cyy:
                    if ("true".equals(((String) SharedPreferenceUtils.get(getActivity(), "vistor", "")))) {
                        getActivity().startActivity(new Intent(getActivity(), IndustrialParkAuditCenterActivity.class));
                    } else {
                        ToastUtils.showMessage(getContext(), "请先登录");
                    }
                    break;
                case R.id.relative_gj:
                    if ("true".equals(((String) SharedPreferenceUtils.get(getActivity(), "vistor", "")))) {
                        getActivity().startActivity(new Intent(getActivity(), ProjectFollowUpActivity.class));
                    } else {
                        ToastUtils.showMessage(getContext(), "请先登录");
                    }
                    break;
                default:
                    break;
            }
        }

    }

    private void setDYHead(List<String> imgArray, String dyCount) {
        tv_dyCount.setText(dyCount);

        if (imgArray != null) {
            int length = imgArray.size();
            for (int x = 0; x < 4; x++) {
                if (x < length) {
                    if (x == 0) {
                        iv_1.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imgArray.get(x)).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(getActivity())).into(iv_1);
                    } else if (x == 1) {
                        iv_2.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imgArray.get(x)).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(getActivity())).into(iv_2);
                    } else if (x == 2) {
                        iv_3.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imgArray.get(x)).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(getActivity())).into(iv_3);
                    } else {
                        iv_4.setVisibility(View.VISIBLE);
                        Glide.with(getActivity()).load(imgArray.get(x)).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(getActivity())).into(iv_4);
                    }
                } else {
                    if (x == 0) {
                        iv_1.setVisibility(View.GONE);
                    } else if (x == 1) {
                        iv_2.setVisibility(View.GONE);
                    } else if (x == 2) {
                        iv_3.setVisibility(View.GONE);
                    } else {
                        iv_4.setVisibility(View.GONE);
                    }
                }
            }
        }

    }

    public void initHead_name_jianJie() {
        if ("Z".equals((String) SharedPreferenceUtils.get(getActivity(), "jmi_type", ""))) {
            iv_zhiKuIcon.setVisibility(View.VISIBLE);
        } else {
            iv_zhiKuIcon.setVisibility(View.GONE);

        }
        if (getActivity() == null) {
            return;
        }
        String jianJie = (String) SharedPreferenceUtils.get(getActivity(), "jianJie", "待完善");
        text_jianJie.setText(jianJie);
        String name = (String) SharedPreferenceUtils.get(getActivity(), "userName", "待完善");
        text_userName.setText(name);
        String touXiangUrl = (String) SharedPreferenceUtils.get(getActivity(), "user_touXiang", "");
        Glide.with(this).load(touXiangUrl).placeholder(Constant.default_touXiang).transform(new GlideCircleTransform(getActivity())).into(image_touXiang);
        String strand_id = (String) SharedPreferenceUtils.get(getActivity(), "strand_id", "");
        if (!"".equals(strand_id)) {
            initTab(strand_id);
        }
    }

    public void initHead_name_jianJie(String jianJie, String name, String touXiangUrl, String phone) {

        if (getActivity() == null) {
            return;
        }
        SharedPreferenceUtils.put(getActivity(), "jianJie", jianJie);
        SharedPreferenceUtils.put(getActivity(), "phoneNum", phone);
        if ("vip".equals(jianJie))
            text_jianJie.setTextColor(this.getResources().getColor(R.color.vip));
        else
            text_jianJie.setTextColor(this.getResources().getColor(R.color.white));
        //text_jianJie.setText("VIP");
        SharedPreferenceUtils.put(getActivity(), "userName", name);
        text_userName.setText(name);
        LogUtil.i("headIcon", "fragment5:" + touXiangUrl);
        //  if(!"".equals(touXiangUrl))
        // {
        if (isChangeHead) {
            Glide.with(this).load(touXiangUrl).placeholder(Constant.default_touXiang).skipMemoryCache(true).transform(new GlideCircleTransform(getActivity())).into(image_touXiang);
            isChangeHead = false;
        } else {
            if (!touXiangUrl.equals((String) SharedPreferenceUtils.get(getActivity(), "user_touXiang", ""))) {
                Glide.with(this).load(touXiangUrl).placeholder(Constant.default_touXiang).skipMemoryCache(true).transform(new GlideCircleTransform(getActivity())).into(image_touXiang);
            }
        }
        //   }
        SharedPreferenceUtils.put(getActivity(), "user_touXiang", touXiangUrl);
        if ("Z".equals((String) SharedPreferenceUtils.get(getActivity(), "jmi_type", ""))) {
            iv_zhiKuIcon.setVisibility(View.VISIBLE);
        } else {
            iv_zhiKuIcon.setVisibility(View.GONE);

        }
    }

    public void initTab(String tabName) {
        if (tabName != null && !"".equals(tabName)) {
            SharedPreferenceUtils.put(getActivity(), "strand_id", tabName);
            String[] tabArray = tabName.split(",");
            int lenth = tabArray.length;
            if (lenth > 0) {
                tab_1.setVisibility(View.GONE);
                tab_2.setVisibility(View.GONE);
                tab_3.setVisibility(View.GONE);
                tab_4.setVisibility(View.GONE);
                tab_5.setVisibility(View.GONE);
                if (lenth > 0) {
                    for (int x = 0; x < lenth; x++) {
                        if (x < 5) {
                            if (x == 0) {
                                tab_1.setText(tabArray[x] + " /");
                                tab_1.setVisibility(View.VISIBLE);
                            } else if (x == 1) {
                                tab_2.setText(tabArray[x] + " /");
                                tab_2.setVisibility(View.VISIBLE);

                            } else if (x == 2) {
                                tab_3.setText(tabArray[x] + " /");
                                tab_3.setVisibility(View.VISIBLE);

                            } else if (x == 3) {
                                tab_4.setText(tabArray[x] + " /");
                                tab_4.setVisibility(View.VISIBLE);

                            } else if (x == 4) {
                                tab_5.setText(tabArray[x] + "  ");
//                                tab_5.setVisibility(View.VISIBLE);
                            }
                        } else {
                            break;
                        }
                    }

                }

            }
        } else {
            tab_1.setVisibility(View.GONE);
            tab_2.setVisibility(View.GONE);
            tab_3.setVisibility(View.GONE);
            tab_4.setVisibility(View.GONE);
            tab_5.setVisibility(View.GONE);
        }

    }

    public void setLoginView() {
        //String app_token = (String) SharedPreferenceUtils.get(getActivity(), "app_token", "");
        if ("true".equals(SharedPreferenceUtils.get(getActivity(), "vistor", ""))) {
            httpUserInfo();
            Log.e("登录", "true");
        } else {
            Log.e("登录", "false");
            flag = true;
            relative_login.setVisibility(View.GONE);
            relative_loginNone.setVisibility(View.VISIBLE);
            isChangeHead = true;
            Call<DefaultBean2> call1 = MyApplication.getNetApi().visitor(uniqueId);
            call1.enqueue(new Callback<DefaultBean2>() {
                public void onResponse(Call<DefaultBean2> call1, Response<DefaultBean2> response) {
                    if (response.isSuccessful()) {
                        SharedPreferenceUtils.put(getActivity(), "app_token", response.body().getdate());
                    }
                }

                public void onFailure(Call<DefaultBean2> call1, Throwable t) {
                    ToastUtils.showMessage(getActivity(), t.toString());
                    call1.cancel();
                }

            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        //  mFirebaseAnalytics.setCurrentScreen(getActivity(),"","");
        LogUtil.i("fragment", "onResume");
    }

    @Override
    public void onStart() {
        super.onStart();
        LogUtil.i("fragment", "onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.i("fragment", "onStop");
    }

    //动态权限申请后处理
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 123:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + "01053620367");
                    intent.setData(data);
                    startActivity(intent);
                } else {
                    ToastUtils.showMessage(getActivity(), "您刚拒绝了一个必要权限");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    /**
     * 智库号弹框
     */
    private PopupWindow popupWindow2;
    private View contentView2;

    private void initPopuWindow_dropMenu(String html) {
        final Activity activity = getActivity();
        if (popupWindow2 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(activity);
            contentView2 = mLayoutInflater.inflate(R.layout.popwindow_dilog, null);
            popupWindow2 = new PopupWindow(contentView2, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
//        String html="<p>移动端智库号的认证暂未开通，请使用电脑浏览器访问<font color=\"#0a8cb0\">www.jisuanweilai.com.cn</font>完成认证</p>";

//        String html="移动端智库号的认证暂未开通，请使用电脑浏览器访问www.jisuanweilai.com.cn完成认证";
        RelativeLayout relative_out = (RelativeLayout) contentView2.findViewById(R.id.relative_out);
        RelativeLayout relative_in = (RelativeLayout) contentView2.findViewById(R.id.relative_in);
        relative_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        relative_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent intent = new Intent(Intent.ACTION_CALL);
                if (Build.VERSION.SDK_INT >= 23) {
                    int checkCallPhonePermission = ContextCompat.checkSelfPermission(activity,
                            Manifest.permission.CALL_PHONE);
                    if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{
                                Manifest.permission.CALL_PHONE
                        }, 123);
                        return;
                    } else {
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        Uri data = Uri.parse("tel:" + "01053620367");
                        intent.setData(data);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + "01053620367");
                    intent.setData(data);
                    startActivity(intent);
                }


            }
        });

        TextView close = (TextView) contentView2.findViewById(R.id.iv_close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow2.dismiss();
            }
        });
        TextView btn_copy = (TextView) contentView2.findViewById(R.id.btn_copy);
        btn_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:" + "01087654321");
                intent.setData(data);
                startActivity(intent);
            }
        });

        //产生背景变暗效果
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f;
        activity.getWindow().setAttributes(lp);
        popupWindow2.setBackgroundDrawable(new BitmapDrawable());
        popupWindow2.setOutsideTouchable(true);
        popupWindow2.setFocusable(true);
        popupWindow2.showAtLocation(contentView2, Gravity.CENTER, 0, 0);
        popupWindow2.update();
        popupWindow2.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                lp.alpha = 1f;
                activity.getWindow().setAttributes(lp);
            }
        });
    }

    /**
     * 上传头像方式框
     */
    private PopupWindow popupWindow1;
    private View contentView1;

    private void initPopuWindow_dropMenu() {
        if (popupWindow1 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(getActivity());
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
                permissionUtil.requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        LogUtil.i("permisson", "-----1----");
                        //EasyImage.openCamera(getActivity());
                        Intent intent = new Intent();
                        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);   //拍照界面的隐式意图
                        startActivityForResult(intent, 7459);
                        popupWindow1.dismiss();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        ToastUtils.showMessage(getActivity(), "您刚拒绝了一个必要权限");

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
                permissionUtil.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        Intent intentToPickPic = new Intent(Intent.ACTION_PICK, null);
                        // 如果限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型" 所有类型则写 "image/*"
                        intentToPickPic.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/jpeg");
                        startActivityForResult(intentToPickPic, 7458);
                        popupWindow1.dismiss();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        ToastUtils.showMessage(getActivity(), "您刚拒绝了一个必要权限");

                    }

                    @Override
                    public void onShouldShowRationale(List<String> deniedPermission) {
                        LogUtil.i("permisson", "-----3----");

                    }
                });
            }
        });
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = 0.4f;
        getActivity().getWindow().setAttributes(lp);
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
//        popupWindow1.showAsDropDown(jiuCuo);
        popupWindow1.showAtLocation(contentView1, Gravity.CENTER, 0, 0);
        popupWindow1.update();
        popupWindow1.getContentView().startAnimation(AnimationUtil.createInAnimation(getActivity(), 300));
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
                lp.alpha = 1f;
                getActivity().getWindow().setAttributes(lp);
            }
        });
    }

    private Animation animationEnd;

    private void endAnimation() {
        if (animationEnd == null) {
            animationEnd = AnimationUtil.createOutAnimation(getActivity(), 500);
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

    private void updateUserInfo(String headImg, String phone) {
        if (NetWorkUtils.isNetworkConnected(getActivity())) {
            Call<UpdateUserInfo> call = MyApplication.getNetApi().updateUserInfo((String) SharedPreferenceUtils.get(getContext(), "app_token", ""), headImg, phone);
            call.enqueue(new Callback<UpdateUserInfo>() {
                @Override
                public void onResponse(Call<UpdateUserInfo> call, Response<UpdateUserInfo> response) {
                    if (response.isSuccessful()) {
                        Log.e("修改用户信息", "成功");
                    } else {
                        Log.e("修改用户信息", "失败");
                    }
                }

                @Override
                public void onFailure(Call<UpdateUserInfo> call, Throwable t) {
                    Log.e("onFailure", "updateUserInfo_onFailure: " + t.getMessage());
                }
            });
        } else {
            ToastUtils.showMessage(getContext(), "请检查您的网络状况");
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
}
