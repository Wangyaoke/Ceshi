package com.ansiyida.cgjl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.base.BaseActivity;

import java.util.HashMap;

import butterknife.Bind;

public class SearchActivity extends BaseActivity {
    private String[] mStrs;
    @Bind(R.id.listView_searchActivity)
     ListView listView;
    @Bind(R.id.searchView_searchActivity)
     SearchView searchView;
    private ArrayAdapter<String> arrayAdapter;
    private String[] names = {"https://baike.baidu.com/item/%E8%83%A1%E6%AD%8C/312718?fr=aladdin"
            , "https://baike.baidu.com/item/%E9%9C%8D%E5%BB%BA%E5%8D%8E/249243"
            , "https://baike.baidu.com/item/%E6%9D%A8%E5%B9%82/149851?fr=aladdin"
            , "https://baike.baidu.com/item/%E8%BF%AA%E4%B8%BD%E7%83%AD%E5%B7%B4"
            , "https://baike.baidu.com/item/%E9%B9%BF%E6%99%97"
            , "https://baike.baidu.com/item/%E5%90%B4%E4%BA%AC/8698"
            , "https://baike.baidu.com/item/%E5%91%A8%E6%9D%B0%E4%BC%A6"};

    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search;
    }

    @Override
    protected void initView() {
        mStrs = new String[7];
        mStrs[0] = "胡歌";
        mStrs[1] = "霍建华";
        mStrs[2] = "杨幂";
        mStrs[3] = "迪丽热巴";
        mStrs[4] = "鹿晗";
        mStrs[5] = "吴京";
        mStrs[6] = "周杰伦";
        map = new HashMap<String, String>();
        int length=mStrs.length;
        for (int x=0;x<length;x++){
            map.put(mStrs[x], names[x]);
        }
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs);
        listView.setAdapter(arrayAdapter);
        listView.setVisibility(View.GONE);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SearchActivity.this,WebActivity.class);
                TextView view2= (TextView) view;
                intent.putExtra("url", map.get(view2.getText().toString().trim()));
                startActivity(intent);
            }
        });

        // 设置搜索文本监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            // 当点击搜索按钮时触发该方法
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            // 当搜索内容改变时触发该方法
            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)) {
                    listView.setVisibility(View.VISIBLE);
                    arrayAdapter.getFilter().filter(newText);
                } else {
                    arrayAdapter.getFilter().filter("");
                    listView.setVisibility(View.GONE);

                }
                return false;
            }
        });


    }

    @Override
    protected void initData() {

    }

    @Override
    protected void httpData() {

    }

    @Override
    protected void setClickListener() {

    }

}
