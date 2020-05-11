package com.ansiyida.cgjl.listener;

import android.support.v7.widget.RecyclerView;

/**
 * Created by ansiyida on 2017/11/30.
 */
public interface IFragment1Presenter {
    void renderTop();
    void renderViewpager();
    void showMenu();
    void pageMove(int position,RecyclerView recyclerView);

}
