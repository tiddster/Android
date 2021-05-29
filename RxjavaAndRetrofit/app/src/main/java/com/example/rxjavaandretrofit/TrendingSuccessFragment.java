package com.example.rxjavaandretrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavaandretrofit.base.BaseFragment;
import com.example.rxjavaandretrofit.bean.Trending;
import com.example.rxjavaandretrofit.bean.TrendingRepositoryItems;
import com.example.rxjavaandretrofit.model.GetResponse;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TrendingSuccessFragment extends BaseFragment implements GetResponse {
    public TextView repo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trending_success_fragment, container, false);
        repo = v.findViewById(R.id.repo);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        GetTrendingRepo();
    }

    @Override
    public void GetTrendingRepo() {
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
                        repo.setText(text);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(getActivity(), "返回成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void GetTrendingDeveloper() {

    }

    @Override
    public void GetLanguage() {

    }
}
