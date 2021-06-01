package com.example.rxjavaandretrofit.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rxjavaandretrofit.R;
import com.example.rxjavaandretrofit.base.BaseActivity;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchingActivity extends BaseActivity {

    //重新加载的按钮
    public void onClickRestart(View view){
        connection();
        setFragment();
    }

    @Override
    public void initView(){
        restartButton = findViewById(R.id.restartButton);
    }

    @Override
    public void Listener(){

    }
}