package com.example.rxjavaandretrofit.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.TrendingFailedFragment;
import com.example.rxjavaandretrofit.TrendingSuccessFragment;

public abstract class BaseActivity extends AppCompatActivity {
    public boolean connection;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);
        connection();
        setFragment();
    }

    public void setFragment() {
        TrendingSuccessFragment successFragment = new TrendingSuccessFragment();
        TrendingFailedFragment failedFragment = new TrendingFailedFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (connection)
            fragment = successFragment;
        else
            fragment = failedFragment;

        fragmentTransaction.add(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    public void connection() {
        ConnectivityManager conn = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = conn.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            connection = true;
        } else {
            connection = false;
        }
    }
}
