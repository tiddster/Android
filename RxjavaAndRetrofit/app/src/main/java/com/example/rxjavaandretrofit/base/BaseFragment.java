package com.example.rxjavaandretrofit.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseFragment extends Fragment{
    public Retrofit mRetrofit;
    public Map<String,String> params;
    public boolean connection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRetrofit();
        connection();
    }

    public void setRetrofit(){
        mRetrofit = new Retrofit.Builder()
                .baseUrl("https://trendings.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        params = new HashMap<>();
        params.put("lang","java");
        params.put("since","weekly");
    }

    public void connection(){
        ConnectivityManager conn = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = conn.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            connection = true;
        } else {
            connection = false;
        }
    }
}
