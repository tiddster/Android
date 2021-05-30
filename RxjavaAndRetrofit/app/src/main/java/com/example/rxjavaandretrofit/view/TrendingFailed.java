package com.example.rxjavaandretrofit.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.base.BaseActivity;
import com.example.rxjavaandretrofit.base.BaseFragment;


public class TrendingFailed extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trending_error_fragment,container,false);
        return view;
    }
}
