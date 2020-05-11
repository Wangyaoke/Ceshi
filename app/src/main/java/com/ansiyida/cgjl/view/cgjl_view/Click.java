package com.ansiyida.cgjl.view.cgjl_view;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MotionEvent;

public class Click extends DrawerLayout {
    public Click(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.e("点击", "onInterceptTouchEvent: "+ev.getAction() );
        return super.onInterceptTouchEvent(ev);
    }
}
