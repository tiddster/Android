package com.example.rxjavaandretrofit.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.base.BaseFragment;
import com.example.rxjavaandretrofit.bean.SearchingItems;
import com.example.rxjavaandretrofit.presenter.SearchingPresenter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;

public class SearchingSuccess extends BaseFragment implements SearchingView {
    public boolean searched = false;
    public String searchingText = null;
    public TextView searchTitle,searching;
    public EditText search;
    public ImageView Mag,sort;
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
        searching = view.findViewById(R.id.searching);
        sort = view.findViewById(R.id.sort);
        searching.setVisibility(View.GONE);
        sort.setVisibility(View.INVISIBLE);

        Show_Search_Title(true);
    }

    public void Listener() {
        Mag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchingText = search.getText().toString();
                ToSearch(searchingText,null);
                search.setText("");
                searched = true;
                Show_Search_Title(true);
                searching.setVisibility(View.VISIBLE);

                //清空上一次搜索数据
                if (mRecyclerView.getChildCount() > 0 ) {
                    mRecyclerView.setAdapter(null);
                }

                closeKeyboard();
            }
        });

        searchTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Show_Search_Title(false);
            }
        });

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initPopupWindow();
            }
        });
    }

    //显示与隐藏标题：true显示标题，false显示搜索框
    public void Show_Search_Title(boolean show_search) {
        if (show_search) {
            Mag.setVisibility(View.INVISIBLE);
            search.setVisibility(View.INVISIBLE);
            searchTitle.setVisibility(View.VISIBLE);
            if(searched){
                searchTitle.setText("G I T  R E P O");
                sort.setVisibility(View.VISIBLE);
            }
        } else {
            Mag.setVisibility(View.VISIBLE);
            search.setVisibility(View.VISIBLE);
            searchTitle.setVisibility(View.INVISIBLE);
            sort.setVisibility(View.INVISIBLE);
        }
    }

    //自动关闭软键盘
    private void closeKeyboard() {
        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    //----------------------------------------------------------------------------------------------



    //搜索相关---------------------------------------------------------------------------------------
    public void ToSearch(String lang, String sort) {
        mSearchingPresenter.GetSearchingInfo(lang,sort);
    }

    @Override
    public void ShowRepoInfo(List<SearchingItems> list) {
        searching.setVisibility(View.GONE);
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
    public void ShowError() {
        Toast.makeText(getActivity(), "搜半天搜了个寂寞", Toast.LENGTH_SHORT).show();
        sort.setVisibility(View.GONE);
        searching.setVisibility(View.GONE);
    }
    //----------------------------------------------------------------------------------------------


    //弹窗相关---------------------------------------------------------------------------------------
    public void initPopupWindow(){
        View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.popupwindow, null, false);
        Button default_sort,stars_sort,forks_sort,update_sort;
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        //参数为1.View 2.宽度 3.高度
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
        popupWindow.setFocusable(true);

        default_sort = view.findViewById(R.id.default_sort);
        stars_sort = view.findViewById(R.id.stars_sort);
        forks_sort = view.findViewById(R.id.forks_sort);
        update_sort = view.findViewById(R.id.update_sort);

        ClickListener(default_sort,"default",popupWindow);
        ClickListener(stars_sort,"stars",popupWindow);
        ClickListener(forks_sort,"forks",popupWindow);
        ClickListener(update_sort,"updated",popupWindow);

        popupWindow.showAsDropDown(sort);
    }

    public void ClickListener(Button button, String SORT,PopupWindow popupWindow){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searching.setVisibility(View.VISIBLE);
                if (mRecyclerView.getChildCount() > 0 ) {
                    mRecyclerView.setAdapter(null);
                }

                ToSearch(searchingText, SORT);
                popupWindow.dismiss();
            }
        });
    }
    //----------------------------------------------------------------------------------------------



    //列表相关---------------------------------------------------------------------------------------
    private class SearchHodler extends RecyclerView.ViewHolder{
        private TextView repo_name, repo_des, repo_lang, fork_num, star_num,login_name,address;
        private int mPosition;
        private ConstraintLayout mConstraintLayout;
        private ImageView avatar;

        public SearchHodler(@NonNull View itemView) {
            super(itemView);

            repo_name = itemView.findViewById(R.id.repo_name);
            repo_des = itemView.findViewById(R.id.repo_dec);
            repo_lang = itemView.findViewById(R.id.language);
            fork_num = itemView.findViewById(R.id.fork_num);
            star_num = itemView.findViewById(R.id.star_num);
            login_name = itemView.findViewById(R.id.login_name);
            address = itemView.findViewById(R.id.url);
            avatar = itemView.findViewById(R.id.avatar);

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
            /*
            new Thread(new Runnable() {
                @Override
                public void run() {
                    mBitmap = getHttpBitmap(searchingItems.getOwner().getAvatar_url());
                }
            }).start();

             */
            show_details(searchingItems);
            //当false的时候，没有地址和简介
        }

        public void show_details(SearchingItems searchingItems){
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
        private static final String TAG = "HaveLoaded";
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

        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        public void onBindViewHolder(@NonNull SearchHodler holder, int position) {
            SearchingItems searchingItems = mSearchingItemsList.get(position);
            holder.bind(searchingItems,position);

            holder.avatar.setImageDrawable(getContext().getDrawable(R.mipmap.github));
            final String url = searchingItems.getOwner().getAvatar_url();
            new LoadImage(holder.avatar).execute(url);

            //holder.avatar.setTag(url);
            /*
            AsyncTask asyncTask = new AsyncTask<Void, Void, Bitmap>() {
                @Override
                protected Bitmap doInBackground(Void... voids) {
                    URL imageUrl = null;
                    Bitmap bitmap = null;
                    InputStream inputStream = null;
                    try {
                        imageUrl = new URL(url);
                        HttpURLConnection conn = (HttpURLConnection) imageUrl.openConnection();
                        conn.setDoInput(true);
                        conn.connect();
                        inputStream = conn.getInputStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        inputStream.close();
                        return bitmap;
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Bitmap bitmap) {
                    super.onPostExecute(bitmap);
                    if(url.equals(holder.avatar.getTag())) {
                        holder.avatar.setImageBitmap(bitmap);
                    }
                }
            }.execute();

             */

            if(mOnItemClick != null) {
                holder.mConstraintLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnItemClick.onItemClick();
                        if(!searchingItems.isShow_details()){
                            searchingItems.setShow_details(true);
                            holder.show_details(searchingItems);
                        } else {
                            searchingItems.setShow_details(false);
                            holder.show_details(searchingItems);
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

}
