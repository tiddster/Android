package com.example.rxjavaandretrofit.model;

import com.example.rxjavaandretrofit.API;
import com.example.rxjavaandretrofit.bean.Searching;
import com.example.rxjavaandretrofit.bean.SearchingItems;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchingModel implements ResponseModel{

    @Override
    public void GetSearching(ResponseListener responseListener) {
        Retrofit mRetrofit;
        String language,token;

        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/search/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        language = "Python";
        token = "token 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";

        List<SearchingItems> list = new ArrayList<>();

        API api = mRetrofit.create(API.class);
        api.GetRepository(language,token).subscribeOn(Schedulers.io())               //在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())  //回到主线程去处理请求结果
                .subscribe(new Observer<Searching<SearchingItems>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Searching<SearchingItems> value) {
                        for (int i = 0; i < value.getItems().size(); i++) {
                            list.add(value.getItems().get(i));
                        }
                        responseListener.requestSuccess(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        responseListener.requestFail();
                    }

                    @Override
                    public void onComplete() {
                        responseListener.requestOver();
                    }
                });
    }
}
