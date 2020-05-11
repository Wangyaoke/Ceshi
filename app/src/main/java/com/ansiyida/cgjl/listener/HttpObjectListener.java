package com.ansiyida.cgjl.listener;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by ansiyida on 2017/12/5.
 */
public interface HttpObjectListener {
    void onSuccess(Call<ResponseBody> call, Response<ResponseBody> response,int typeId);
    void onFailure(Call<ResponseBody> call, Throwable t,int typeId);
}
