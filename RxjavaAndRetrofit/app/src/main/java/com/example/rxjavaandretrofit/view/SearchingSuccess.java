package com.example.rxjavaandretrofit.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.base.BaseFragment;
import com.example.rxjavaandretrofit.bean.Searching;
import com.example.rxjavaandretrofit.bean.SearchingItems;
import com.example.rxjavaandretrofit.presenter.SearchingPresenter;

import java.util.ArrayList;
import java.util.List;

public class SearchingSuccess extends BaseFragment implements SearchingView {
    public TextView repo;
    public TextView searchTitle;
    public EditText search;
    public ImageView Mag;
    public SearchingPresenter mSearchingPresenter;
    public RecyclerView mRecyclerView;
    public SearchAdapter mAdapter;
    public List<SearchingItems> mItemsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.trending_success_fragment, container, false);
        initView(v);
        Listener();
        return v;
    }

    //视图相关---------------------------------------------------------------------------------------
    public void initView(View view) {
        mSearchingPresenter = new SearchingPresenter(this);

        mRecyclerView = view.findViewById(R.id.SearchingRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        searchTitle = view.findViewById(R.id.search_title);
        search = view.findViewById(R.id.search_edit);
        Mag = view.findViewById(R.id.mag);
        //repo = view.findViewById(R.id.repo);

        Show_Search_Title(true);
    }

    public void Listener() {
        Mag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToSearch(search.getText().toString());
                search.setText("");
                Show_Search_Title(true);
            }
        });

        searchTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_Search_Title(false);
            }
        });
    }

    //显示与隐藏标题：true显示标题，false显示搜索框
    public void Show_Search_Title(boolean show_search) {
        if (show_search) {
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

    //列表相关---------------------------------------------------------------------------------------
    private class SearchHodler extends RecyclerView.ViewHolder{
        private TextView repo_name, repo_des, repo_lang, fork_num, star_num,login_name,address;
        private int mPosition;
        private ConstraintLayout mConstraintLayout;

        public SearchHodler(@NonNull View itemView) {
            super(itemView);

            repo_name = itemView.findViewById(R.id.repo_name);
            repo_des = itemView.findViewById(R.id.repo_dec);
            repo_lang = itemView.findViewById(R.id.language);
            fork_num = itemView.findViewById(R.id.fork_num);
            star_num = itemView.findViewById(R.id.star_num);
            login_name = itemView.findViewById(R.id.login_name);
            address = itemView.findViewById(R.id.url);

            mConstraintLayout = itemView.findViewById(R.id.ItemLayout);
        }

        public void bind(SearchingItems searchingItems,int position) {
            mPosition = position;
            repo_name.setText(searchingItems.getFull_name());
            repo_des.setText(searchingItems.getDescription());
            repo_lang.setText(searchingItems.getLanguage());
            fork_num.setText(String.valueOf(searchingItems.getForks()));
            star_num.setText(String.valueOf(searchingItems.getStargazers_count()));
            login_name.setText(searchingItems.getOwner().getLogin());
            address.setText(searchingItems.getGit_url());

            //当false的时候，没有地址和简介
            if(!searchingItems.isShow_details()){
                address.setVisibility(View.GONE);
                repo_des.setVisibility(View.GONE);
            } else {
                address.setVisibility(View.VISIBLE);
                repo_des.setVisibility(View.VISIBLE);
            }
        }
    }

    private class SearchAdapter extends RecyclerView.Adapter<SearchHodler>{
        private List<SearchingItems> mSearchingItemsList;

        private OnItemClick mOnItemClick;

        public void setOnItemClick(OnItemClick onItemClick){
            mOnItemClick = onItemClick;
        }

        public SearchAdapter(List<SearchingItems> searchingItemsList) {
            mSearchingItemsList = searchingItemsList;
        }

        @NonNull
        @Override
        public SearchHodler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.items, parent, false);
            return new SearchHodler(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SearchHodler holder, int position) {
            SearchingItems searchingItems = mSearchingItemsList.get(position);
            holder.bind(searchingItems,position);
            if(mOnItemClick != null) {
                holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClick.onItemClick();
                        if(!searchingItems.isShow_details()){
                            searchingItems.setShow_details(true);
                            holder.bind(searchingItems,position);
                        } else {
                            searchingItems.setShow_details(false);
                            holder.bind(searchingItems,position);
                        }
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mSearchingItemsList.size();
        }
    }
    //----------------------------------------------------------------------------------------------

    //搜索相关---------------------------------------------------------------------------------------
    public void ToSearch(String lang) {
        mSearchingPresenter.GetSearchingInfo(lang);
    }

    @Override
    public void ShowRepoInfo(List<SearchingItems> list) {
        mItemsList = list;
        mAdapter = new SearchAdapter(mItemsList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick() {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void ShowRepoDetails() {

    }

    @Override
    public void ShowError() {
        Toast.makeText(getActivity(), "你搜了个寂寞", Toast.LENGTH_SHORT).show();
    }
    //----------------------------------------------------------------------------------------------
}
