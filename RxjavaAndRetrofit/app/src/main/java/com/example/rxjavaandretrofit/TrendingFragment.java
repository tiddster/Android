package com.example.rxjavaandretrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.rxjavaandretrofit.base.BaseFragment;
import com.example.rxjavaandretrofit.model.GetResponse;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TrendingFragment extends BaseFragment implements GetResponse {
    public TextView repo,error;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v;
        if(connection){
            v = inflater.inflate(R.layout.trending_success_fragment, container, false);
            repo = v.findViewById(R.id.repo);
        }
        else {
            v = inflater.inflate(R.layout.trending_error_fragment, container, false);
            error = v.findViewById(R.id.error);
            error.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onCreate(null);
                    Toast.makeText(getActivity(), "ok", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
                        for(int i=0; i<value.getItems().size(); i++) {
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
