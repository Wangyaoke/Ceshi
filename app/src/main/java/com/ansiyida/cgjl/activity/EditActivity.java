package com.ansiyida.cgjl.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;
import com.ansiyida.cgjl.bean.UpdateUserMsgBean;
import com.ansiyida.cgjl.dialog.CommentDialog2;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.util.AnimationUtil;
import com.ansiyida.cgjl.util.LogUtil;
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
import pl.aprilapps.easyphotopicker.EasyImage;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends BaseActivity {
    @Bind(R.id.relative_touXiang)
    RelativeLayout relative_touXiang;
    @Bind(R.id.relative_jianJie)
    RelativeLayout relative_jianJie;
    @Bind(R.id.relative_userName)
    RelativeLayout relative_userName;
    @Bind(R.id.image_touXiang)
    ImageView image_touxiang;
    @Bind(R.id.text_userName)
    TextView text_userName;
    @Bind(R.id.text_jianJie)
    TextView text_jianJie;
    @Bind(R.id.text_title)
    TextView text_title;
    @Bind(R.id.image_back)
    ImageView image_back;
    private PermissionUtil permissionUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_edit;
    }

    @Override
    protected void initView() {
        text_title.setText("编辑资料");
        initHead_name_jianJie();
        setStateColor(this, true);
        permissionUtil = new PermissionUtil(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {
        relative_touXiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopuWindow_dropMenu();
            }
        });
        relative_jianJie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJianJieDialog();
            }
        });
        relative_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserNameDialog();
            }
        });

        image_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditActivity.this.finish();
            }
        });
    }


    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


    // --------------------------------自定义函数-------------------------------------
    private void initHead_name_jianJie() {
        String jianJie = (String) SharedPreferenceUtils.get(this, "jianJie", "待完善");
        text_jianJie.setText(jianJie);
        String name = (String) SharedPreferenceUtils.get(this, "userName", "待完善");
        text_userName.setText(name);
        String touXiangUrl = (String) SharedPreferenceUtils.get(this, "user_touXiang", "");
        LogUtil.i("headIcon","EditActivity:"+touXiangUrl);
        Glide.with(this).load(touXiangUrl).placeholder(Constant.default_touXiang).skipMemoryCache(true).transform(new GlideCircleTransform(this)).into(image_touxiang);
    }

    private void showJianJieDialog() {
        String jianJie = text_jianJie.getText().toString().trim();
        if ("待完善".equals(jianJie)) {
            jianJie = "";
        }
       CommentDialog2 commentDialog2= new CommentDialog2("请输入简介内容", jianJie, 30, new CommentDialog2.SendListener() {
            @Override
            public void sendComment(String inputText) {
                    final String str=inputText;
                    Call<UpdateUserMsgBean> call2 = MyApplication.getNetApi().updateMiaoShu(inputText, (String) SharedPreferenceUtils.get(EditActivity.this, "app_token", ""));
                    call2.enqueue(new Callback<UpdateUserMsgBean>() {
                        @Override
                        public void onResponse(Call<UpdateUserMsgBean> call, Response<UpdateUserMsgBean> response) {
                            if (response.isSuccessful()) {
                                if ("0001".equals(response.body().getStatus())){
                                    text_jianJie.setText(str);
                                    SharedPreferenceUtils.put(EditActivity.this, "jianJie", str);
                                }else {
                                    ToastUtils.showMessage(EditActivity.this, response.body().getMessage());
                                }
                            } else {
                                ToastUtils.showMessage(EditActivity.this, "上传失败");
                            }
                            call.cancel();
                        }

                        @Override
                        public void onFailure(Call<UpdateUserMsgBean> call, Throwable t) {
                            call.cancel();
                            ToastUtils.showMessage(EditActivity.this, "上传失败");
                        }
                    });

                }
        }, EditActivity.this, "保存");
//        commentDialog2.setFlag(false);
        commentDialog2.show(EditActivity.this.getSupportFragmentManager(), "comment");
    }

    private void showUserNameDialog() {
        String name = text_userName.getText().toString().trim();
        if ("待完善".equals(name)) {
            name = "";
        }
        CommentDialog2 commentDialog2 = new CommentDialog2("请输入用户名", name, 14, new CommentDialog2.SendListener() {
            @Override
            public void sendComment(String inputText) {
                final String str=inputText;
                Call<UpdateUserMsgBean> call2 = MyApplication.getNetApi().updateName(inputText, (String) SharedPreferenceUtils.get(EditActivity.this, "app_token", ""));
                call2.enqueue(new Callback<UpdateUserMsgBean>() {
                    @Override
                    public void onResponse(Call<UpdateUserMsgBean> call, Response<UpdateUserMsgBean> response) {
                        if (response.isSuccessful()) {
                            if ("0001".equals(response.body().getStatus())){
                                text_userName.setText(str);
                                SharedPreferenceUtils.put(EditActivity.this, "userName", str);
                            }else {
                                ToastUtils.showMessage(EditActivity.this, response.body().getMessage());

                            }

                        } else {
                            ToastUtils.showMessage(EditActivity.this, "上传失败");
                        }
                        call.cancel();
                    }

                    @Override
                    public void onFailure(Call<UpdateUserMsgBean> call, Throwable t) {
                        call.cancel();
                        ToastUtils.showMessage(EditActivity.this, "上传失败");
                    }
                });

            }
        }, EditActivity.this, "保存");
        commentDialog2.show(EditActivity.this.getSupportFragmentManager(), "comment");
    }

    /**
     * 裁剪图片方法实现
     *
     * @param uri
     */
    public void startPhotoZoom(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        // crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3000);
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
                if (out!=null&&photo!=null){
                    photo.compress(Bitmap.CompressFormat.JPEG, 80, out);
                }else {
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

    /**
     * 上传头像方式框
     */
    private PopupWindow popupWindow1;
    private View contentView1;

    private void initPopuWindow_dropMenu() {
        if (popupWindow1 == null) {
            LayoutInflater mLayoutInflater = LayoutInflater.from(this);
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
                permissionUtil.requestPermissions(new String[]{Manifest.permission.CAMERA,Manifest.permission.RECORD_AUDIO}, new PermissionListener() {
                    @Override
                    public void onGranted() {
                        LogUtil.i("permisson", "-----1----");
                        EasyImage.openCamera(EditActivity.this);
                        popupWindow1.dismiss();
                    }

                    @Override
                    public void onDenied(List<String> deniedPermission) {
                        ToastUtils.showMessage(EditActivity.this, "您刚拒绝了一个必要权限");

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
                EasyImage.openGalleryPicker(EditActivity.this);
                popupWindow1.dismiss();
            }
        });
        WindowManager.LayoutParams lp = this.getWindow().getAttributes();
        lp.alpha = 0.4f;
        this.getWindow().setAttributes(lp);
        popupWindow1.setBackgroundDrawable(new BitmapDrawable());
        popupWindow1.setOutsideTouchable(true);
        popupWindow1.setFocusable(true);
//        popupWindow1.showAsDropDown(jiuCuo);
        popupWindow1.showAtLocation(contentView1, Gravity.CENTER, 0, 0);
        popupWindow1.update();
        popupWindow1.getContentView().startAnimation(AnimationUtil.createInAnimation(this, 300));
        popupWindow1.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams lp = EditActivity.this.getWindow().getAttributes();
                lp.alpha = 1f;
                EditActivity.this.getWindow().setAttributes(lp);
            }
        });
    }

    private Animation animationEnd;

    private void endAnimation() {
        if (animationEnd == null) {
            animationEnd = AnimationUtil.createOutAnimation(EditActivity.this, 500);
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

}
