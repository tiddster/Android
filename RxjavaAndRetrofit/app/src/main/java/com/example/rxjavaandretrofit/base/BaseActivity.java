package com.example.rxjavaandretrofit.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.view.SearchingFailed;
import com.example.rxjavaandretrofit.view.SearchingSuccess;

//用于管理fragment
public abstract class BaseActivity extends AppCompatActivity {
    public boolean connection;
    public Button restartButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.base_activity);

        initView();
        connection();
        setFragment();
    }

    //检查网络连接以切换fragment
    public void setFragment() {
        SearchingSuccess successFragment = new SearchingSuccess();
        SearchingFailed failedFragment = new SearchingFailed();

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragment_container);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (connection) {
            fragment = successFragment;
            restartButton.setVisibility(View.GONE);
        }
        else {
            fragment = failedFragment;
        }

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    //检查网络连接
    public void connection() {
        ConnectivityManager conn = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = conn.getActiveNetworkInfo();
        if (net != null && net.isConnected()) {
            connection = true;
        } else {
            connection = false;
            Toast.makeText(this, "啪的一下，网络没了", Toast.LENGTH_SHORT).show();
        }
    }

    public void initView(){}
}
