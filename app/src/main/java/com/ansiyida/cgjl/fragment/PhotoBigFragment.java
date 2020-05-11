package com.ansiyida.cgjl.fragment;

import android.os.Bundle;
import android.view.View;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseFragment;
import com.ansiyida.cgjl.view.photo.PhotoView;
import com.bumptech.glide.Glide;

/**
 * Created by ansiyida on 2018/3/2.
 */
public class PhotoBigFragment extends BaseFragment {

    @Override
    protected void initTheam() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_fragment_photo_big;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Bundle arguments = getArguments();
        String picUrl = arguments.getString("picUrl");
        PhotoView photoView= (PhotoView) view.findViewById(R.id.photoView);
        photoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    getActivity().finish();
                    getActivity().overridePendingTransition(R.anim.defaul2, R.anim.defaul);
                }catch (Exception e){

                }
            }
        });
        //Log.e("PhotoPicurl", "initView: "+picUrl );
        Glide.with(this).load(picUrl).skipMemoryCache(true).error(R.mipmap.icon_placeholder).into(photoView);
    }

    @Override
    protected void initData() {

    }
}
