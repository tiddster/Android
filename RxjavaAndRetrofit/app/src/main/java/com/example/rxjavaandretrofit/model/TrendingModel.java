package com.example.rxjavaandretrofit.model;

import com.example.rxjavaandretrofit.API;
import com.example.rxjavaandretrofit.bean.Trending;
import com.example.rxjavaandretrofit.bean.TrendingRepositoryItems;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendingModel implements ResponseModel{

    @Override
    public void GetTrending(ResponseListener responseListener) {
        Retrofit mRetrofit;
        Map<String,String> params;

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        params = new HashMap<>();
        params.put("lang","java");
        params.put("since","weekly");

        StringBuilder text = new StringBuilder();

        API api = mRetrofit.create(API.class);
        api.GetTrendingRepository(params).subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求结果
                .subscribe(new Observer<Trending<TrendingRepositoryItems>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Trending<TrendingRepositoryItems> value) {
                        for (int i = 0; i < value.getItems().size(); i++) {
                            text.append(value.getItems().get(i).getAdded_stars()).append("\n");
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        responseListener.requestFail();
                    }

                    @Override
                    public void onComplete() {
                        responseListener.requestSuccess();
                    }
                });
    }
}
