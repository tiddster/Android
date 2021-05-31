package com.example.rxjavaandretrofit.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.base.BaseFragment;
import com.example.rxjavaandretrofit.bean.TrendingDeveloperItems;
import com.example.rxjavaandretrofit.bean.TrendingRepositoryItems;
import com.example.rxjavaandretrofit.presenter.TrendingPresenter;

import java.util.List;

public class TrendingSuccess extends BaseFragment implements TrendingView {
    public TextView repo;
    public TrendingPresenter mTrendingPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trending_success_fragment, container, false);
        initView(v);
        return v;
    }

    public void initView(View view){
        mTrendingPresenter = new TrendingPresenter(this);
        mTrendingPresenter.GetTrendingInfo();

        repo = view.findViewById(R.id.repo);
    }

    @Override
    public void ShowRepoInfo(List<TrendingRepositoryItems> list) {
        StringBuilder text = new StringBuilder();
        for(int i=0; i<list.size(); i++){
            text.append(list.get(i).getRepo()).append("\n");
        }
        repo.setText(text);
    }

    @Override
    public void ShowRepoDetails() {

    }

    @Override
    public void ShowError() {
        Toast.makeText(getActivity(),"返回趋势失败", Toast.LENGTH_SHORT).show();
    }
}
