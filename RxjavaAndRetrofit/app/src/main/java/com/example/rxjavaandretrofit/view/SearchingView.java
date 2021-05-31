package com.example.rxjavaandretrofit.view;

import com.example.rxjavaandretrofit.bean.SearchingItems;

import java.util.List;

public interface SearchingView {
    void ShowRepoInfo(List<SearchingItems> list);
    void ShowRepoDetails();
    void ShowError();
}
