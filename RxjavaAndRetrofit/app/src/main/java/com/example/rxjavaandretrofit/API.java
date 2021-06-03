package com.example.rxjavaandretrofit;

import com.example.rxjavaandretrofit.bean.Searching;
import com.example.rxjavaandretrofit.bean.SearchingItems;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface API {
    //获取趋势仓库
    @GET("repositories")
    Observable<Searching<SearchingItems>> GetRepository(@Query("q") String language,
                                                        @Header("Authorization") String token,
                                                        @Query("sort") String Sort);
}
