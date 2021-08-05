package model;


import java.util.List;

import bean.ClockInLabel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ClockInAPI {
    @GET("punch")
    Call<List<ClockInLabel>> getLabels(@Header("token") String token);
}
