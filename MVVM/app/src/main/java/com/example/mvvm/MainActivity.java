package com.example.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.viewmodel.ViewModel;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ViewModel mvvmViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        mvvmViewModel = new ViewModel(getApplication(),binding);
        binding.setAccountViewMode(mvvmViewModel);
    }
}