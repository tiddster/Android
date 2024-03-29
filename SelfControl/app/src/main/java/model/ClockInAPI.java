package model;


import java.time.Clock;
import java.util.List;

import bean.ClockInLabel;
import bean.ClockInLabelTitle;
import bean.Message;
import bean.ResponseDate;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ClockInAPI {
    @GET("api/v1/punch/")
    Call<ResponseDate<List<ClockInLabel>>> getLabels(@Header("token") String token);

    @POST("api/v1/punch/")
    Call<Message> toClockIn(@Header("token") String token,
                            @Body ClockInLabelTitle clockInLabelTitle);

    @HTTP(method = "DELETE", path = "api/v1/punch/", hasBody = true)
    Call<Message> removeLabel(@Header("token") String token,
                              @Body ClockInLabelTitle clockInLabelTitle);

    @GET("")
    Call<ResponseDate<Boolean>> isClockInToday(@Header("token") String token,
                                      @Url String url);
}
