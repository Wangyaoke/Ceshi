package com.ansiyida.cgjl.util;

import android.os.AsyncTask;

public class GetPrepayId extends AsyncTask {
    String str;
    public  GetPrepayId(String str)
    {
        this.str=str;
    }
    @Override
    protected  void onPreExecute(){
        super.onPreExecute();
    }
    @Override
    protected Object doInBackground(Object[] objects) {
        String url="https://api.mch.weixin.qq.com/pay/unifiedorder";
        byte[] buf=Util.httpPost(url,str);
        String content=new String(buf);
        return content;
    }
}
