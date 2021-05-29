package com.example.rxjavaandretrofit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavaandretrofit.base.BaseFragment;


public class TrendingFailedFragment extends BaseFragment {
    TextView error;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                          Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.trending_error_fragment,container,false);
        error = view.findViewById(R.id.error);
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"ok",Toast.LENGTH_SHORT);
            }
        });
        return view;
    }
}
