package model;

import android.util.Log;

import java.util.List;

import bean.ClockInLabel;
import bean.ClockInLabelTitle;
import bean.Message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;

public class ClockInModel {
    Retrofit mRetrofit = new Retrofit.Builder()
            .baseUrl("http://39.99.53.8:2333/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ClockInAPI mClockInAPI = mRetrofit.create(ClockInAPI.class);

    public void getClockInLabels(String token,ClockInResponseListener clockInResponseListener){
        mClockInAPI.getLabels(token).enqueue(new Callback<List<ClockInLabel>>() {
            @Override
            public void onResponse(Call<List<ClockInLabel>> call, Response<List<ClockInLabel>> response) {
                clockInResponseListener.clockInRequestLabelSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<ClockInLabel>> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }

    public void toClockIn(String token, String title, ClockInResponseListener clockInResponseListener){
        mClockInAPI.toClockIn(token,title).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }

    public void removeLabel(String token, ClockInLabelTitle clockInLabelTitle, ClockInResponseListener clockInResponseListener){
        mClockInAPI.removeLabel(token, clockInLabelTitle).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                clockInResponseListener.removeLabelSuccess("删除成功");
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                clockInResponseListener.clockInRequestFail(t.getMessage());
            }
        });
    }
}
