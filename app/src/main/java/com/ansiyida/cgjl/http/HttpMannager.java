package com.ansiyida.cgjl.http;

import com.ansiyida.cgjl.listener.HttpObjectListener;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ansiyida on 2017/12/5.
 */
public class HttpMannager {
    public static void send(Call<ResponseBody> call, final HttpObjectListener listener, final int typeId){
        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                listener.onSuccess(call,response,typeId);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listener.onFailure(call,t,typeId);
            }
        });
    }
}
