package com.example.rxjavaandretrofit;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface API {
    //获取趋势仓库
    @GET("/repo")
    Observable<Trending<TrendingRepositoryItems>> GetTrendingRepository(@QueryMap Map<String,String> params);

    @GET("/developer")
    Observable<Trending<TrendingDeveloperItems>> GetTrendingDeveloper(@QueryMap Map<String,String> params);
}
