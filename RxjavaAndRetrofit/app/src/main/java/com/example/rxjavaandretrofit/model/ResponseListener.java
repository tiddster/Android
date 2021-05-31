package com.example.rxjavaandretrofit.model;

import com.example.rxjavaandretrofit.bean.TrendingRepositoryItems;

import java.util.List;

public interface ResponseListener {
    void requestOver();

    void requestFail();

    void requestSuccess(List<TrendingRepositoryItems> list);
}
