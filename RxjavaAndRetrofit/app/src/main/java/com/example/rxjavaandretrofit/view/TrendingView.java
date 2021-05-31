package com.example.rxjavaandretrofit.view;

import com.example.rxjavaandretrofit.bean.TrendingRepositoryItems;

import java.util.List;

public interface TrendingView {
    void ShowRepoInfo(List<TrendingRepositoryItems> list);
    void ShowRepoDetails();
    void ShowError();
}
