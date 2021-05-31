package com.example.rxjavaandretrofit.model;

import com.example.rxjavaandretrofit.bean.SearchingItems;

import java.util.List;

public interface ResponseListener {
    void requestOver();

    void requestFail();

    void requestSuccess(List<SearchingItems> list);
}
