package model;

import android.util.Log;

import java.util.List;

import bean.ClockInLabel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClockInModel {
    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ClockInAPI mClockInAPI = mRetrofit.create(ClockInAPI.class);

    public void getClockInLabels(String token,ClockInResponseListener clockInResponseListener){
        mClockInAPI.getLabels(token).enqueue(new Callback<List<ClockInLabel>>() {
            @Override
            public void onResponse(Call<List<ClockInLabel>> call, Response<List<ClockInLabel>> response) {
                clockInResponseListener.clockInRequestSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ClockInLabel>> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t);
            }
        });
    }
}
