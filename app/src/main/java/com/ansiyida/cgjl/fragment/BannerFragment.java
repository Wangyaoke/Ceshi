package com.ansiyida.cgjl.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.activity.NewsDetailsActivity;
import com.ansiyida.cgjl.activity.PhotoDetailsActivity;
import com.ansiyida.cgjl.activity.VideoDetailsActivity;
import com.ansiyida.cgjl.activity.YanTaoActivity;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.util.LogUtil;
import com.bumptech.glide.Glide;

import butterknife.Bind;

/**
 * Created by ansiyida on 2018/3/21.
 */
public class BannerFragment extends BaseFragment {
    @Bind(R.id.iv_img)
    ImageView iv_img;
    private String img;

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_banner;
    }


    @Override
    protected void initView(View view, Bundle savedInstanceState) {

    }

    @Override
    protected void initData() {
        Bundle arguments = getArguments();
        img = arguments.getString("img", "");
        final String jumpUrl = arguments.getString("jumpUrl", "");

        final String[] arr = jumpUrl.split("/");
        LogUtil.i("jump", "position:" + arguments.getInt("position", -1));

        iv_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int length = arr.length;
                if (length > 2) {
                    String artype = arr[length - 2];
                    String id = arr[length - 1];
                    Intent intent;
                    switch (artype) {
                        case "P":
                            intent = new Intent(getActivity(), NewsDetailsActivity.class);
                            intent.putExtra("type", artype + "");
                            intent.putExtra("id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(intent);
                            break;
                        case "T":
                            intent = new Intent(getActivity(), PhotoDetailsActivity.class);
                            intent.putExtra("type", artype + "");
                            intent.putExtra("id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(intent);
                            break;
                        case "S":
                            intent = new Intent(getActivity(), VideoDetailsActivity.class);
                            intent.putExtra("type", artype + "");
                            intent.putExtra("id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(intent);
                            break;
                        case "C":
                            intent = new Intent(getActivity(), NewsDetailsActivity.class);
                            intent.putExtra("type", artype + "");
                            intent.putExtra("id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(intent);
                            break;
                        case "D":
                            intent = new Intent(getActivity(), YanTaoActivity.class);
                            intent.putExtra("jc_id", id + "");
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getActivity().startActivity(intent);
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        Glide.with(getActivity()).load(img).placeholder(R.mipmap.default_yantao).centerCrop().into(iv_img);
    }
}
