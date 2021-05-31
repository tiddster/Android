package com.example.rxjavaandretrofit.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.base.BaseFragment;
import com.example.rxjavaandretrofit.bean.SearchingItems;
import com.example.rxjavaandretrofit.presenter.SearchingPresenter;

import java.util.List;

public class SearchingSuccess extends BaseFragment implements SearchingView {
    public TextView repo;
    public SearchingPresenter mSearchingPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trending_success_fragment, container, false);
        initView(v);
        return v;
    }

    public void initView(View view){
        mSearchingPresenter = new SearchingPresenter(this);
        mSearchingPresenter.GetSearchingInfo();

        repo = view.findViewById(R.id.repo);
    }

    @Override
    public void ShowRepoInfo(List<SearchingItems> list) {
        StringBuilder text = new StringBuilder();
        for(int i=0; i<list.size(); i++){
            text.append(list.get(i).getFull_name()).append("\n");
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
