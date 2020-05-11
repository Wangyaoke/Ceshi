package com.ansiyida.cgjl.util;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;

/**
 * Created by chenyaoxiang on 2017/12/8.
 */
public class AnimationUtil {
    //动画持续时间
    public final static int ANIMATION_IN_TIME=200;
    public final static int ANIMATION_OUT_TIME=200;

    public static Animation createInAnimation(Context context,int fromYDelta){
        AnimationSet set=new AnimationSet(context,null);
        set.setFillAfter(true);

        TranslateAnimation animation=new TranslateAnimation(0,0,fromYDelta,0);
        animation.setDuration(ANIMATION_IN_TIME);
        set.addAnimation(animation);

//        AlphaAnimation alphaAnimation=new AlphaAnimation(0,1);
//        alphaAnimation.setDuration(ANIMATION_IN_TIME);
//        set.addAnimation(alphaAnimation);


        return set;
    }
    public static Animation createInAnimation(Context context,int fromYDelta,int duration){
        AnimationSet set=new AnimationSet(context,null);
        set.setFillAfter(true);

        TranslateAnimation animation=new TranslateAnimation(0,0,fromYDelta,0);
        animation.setDuration(duration);
        set.addAnimation(animation);

        return set;
    }

    public static Animation createOutAnimation(Context context,int toYDelta){
        AnimationSet set=new AnimationSet(context,null);
        set.setFillAfter(true);

        TranslateAnimation animation=new TranslateAnimation(0,0,0,toYDelta);
        animation.setDuration(ANIMATION_OUT_TIME);
        set.addAnimation(animation);

//        AlphaAnimation alphaAnimation=new AlphaAnimation(1,0);
//        alphaAnimation.setDuration(ANIMATION_OUT_TIME);
//        set.addAnimation(alphaAnimation);


        return set;
    }
    private static final String TAG = AnimationUtil.class.getSimpleName();

}