package com.example.rxjavaandretrofit.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.base.BaseFragment;
import com.example.rxjavaandretrofit.bean.SearchingItems;
import com.example.rxjavaandretrofit.presenter.SearchingPresenter;

import java.util.List;

public class SearchingSuccess extends BaseFragment implements SearchingView {
    public TextView repo;
    public TextView searchTitle;
    public EditText search;
    public ImageView Mag;
    public SearchingPresenter mSearchingPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trending_success_fragment, container, false);
        initView(v);
        Listener();
        return v;
    }

    //视图相关---------------------------------------------------------------------------------------
    public void initView(View view){
        mSearchingPresenter = new SearchingPresenter(this);

        searchTitle = view.findViewById(R.id.search_title);
        search = view.findViewById(R.id.search_edit);
        Mag = view.findViewById(R.id.mag);
        repo = view.findViewById(R.id.repo);

        Show_Search_Title(true);
    }

    public void Listener(){
        Mag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(search.getText().toString() == null){
                    Toast.makeText(getActivity(),"好像还没有输入搜索内容呢",Toast.LENGTH_SHORT).show();
                } else {
                    ToSearch(search.getText().toString());
                    Show_Search_Title(true);
                }
            }
        });

        searchTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_Search_Title(false);
            }
        });
    }

    public void Show_Search_Title(boolean show_search){
        if(show_search){
            Mag.setVisibility(View.INVISIBLE);
            search.setVisibility(View.INVISIBLE);
            searchTitle.setVisibility(View.VISIBLE);
        } else {
            Mag.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);
            searchTitle.setVisibility(View.INVISIBLE);
        }
    }
    //----------------------------------------------------------------------------------------------

    //搜索相关---------------------------------------------------------------------------------------
    public void ToSearch(String lang){
        mSearchingPresenter.GetSearchingInfo(lang);
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
        Toast.makeText(getActivity(),"搜索失败", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
}
