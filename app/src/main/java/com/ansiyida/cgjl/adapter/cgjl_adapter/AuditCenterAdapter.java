package com.ansiyida.cgjl.adapter.cgjl_adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ansiyida.cgjl.fragment.cgjl_fragment.CP_QYAuditFragment;

import java.util.List;

public class AuditCenterAdapter extends FragmentPagerAdapter {
    private List<CP_QYAuditFragment> list;
    private Context context;

    public AuditCenterAdapter(FragmentManager fm, List<CP_QYAuditFragment> list, Context context) {
        super(fm);
        this.list = list;
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
