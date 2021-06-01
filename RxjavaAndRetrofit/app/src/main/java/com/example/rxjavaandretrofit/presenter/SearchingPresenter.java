package com.example.rxjavaandretrofit.presenter;

import com.example.rxjavaandretrofit.bean.SearchingItems;
import com.example.rxjavaandretrofit.model.ResponseListener;
import com.example.rxjavaandretrofit.model.SearchingModel;
import com.example.rxjavaandretrofit.view.SearchingView;

import java.util.List;

public class SearchingPresenter {

    public SearchingModel mSearchingModel;
    public SearchingView mSearchingView;

    public SearchingPresenter(SearchingView searchingView){
        this.mSearchingView = searchingView;
        this.mSearchingModel = new SearchingModel();
    }

    public void GetSearchingInfo(String language){
        mSearchingModel.GetSearching(new ResponseListener() {
            @Override
            public void requestSuccess(List<SearchingItems> list) {
                mSearchingView.ShowRepoInfo(list);
            }

            @Override
            public void requestOver() {

            }

            @Override
            public void requestFail() {
                mSearchingView.ShowError();
            }
        },language);
    }
}
