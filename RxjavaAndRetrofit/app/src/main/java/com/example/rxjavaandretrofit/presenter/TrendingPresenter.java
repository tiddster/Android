package com.example.rxjavaandretrofit.presenter;

import com.example.rxjavaandretrofit.bean.TrendingRepositoryItems;
import com.example.rxjavaandretrofit.model.ResponseListener;
import com.example.rxjavaandretrofit.model.TrendingModel;
import com.example.rxjavaandretrofit.view.TrendingView;

import java.util.List;

public class TrendingPresenter {

    public TrendingModel trendingModel;
    public TrendingView trendingView;

    public TrendingPresenter(TrendingView trendingView){
        this.trendingView = trendingView;
        this.trendingModel = new TrendingModel();
    }

    public void GetTrendingInfo(){
        trendingModel.GetTrending(new ResponseListener() {
            @Override
            public void requestSuccess(List<TrendingRepositoryItems> list) {
                trendingView.ShowRepoInfo(list);
            }

            @Override
            public void requestOver() {

            }

            @Override
            public void requestFail() {
                trendingView.ShowError();
            }
        });
    }
}
