package com.example.mvvm.viewmodel;

import android.app.Application;
import android.view.Display;
import android.view.View;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvm.BR;
import com.example.mvvm.databinding.ActivityMainBinding;
import com.example.mvvm.model.AccountCallBack;
import com.example.mvvm.model.Model;

public class ViewModel extends BaseObservable {
    private ActivityMainBinding mBinding;
    private Model mModel;
    private String input;
    private String res;

    public ViewModel(Application application, ActivityMainBinding activityMainBinding){
        mBinding = activityMainBinding;
        mModel = new Model();
    }

    @Bindable
    public String getResult(){
        return res;
    }

    public void setResult(String result) {
        res = result;
        notifyPropertyChanged(BR.result);
    }

    public void getData(){
        input = mBinding.etAccount.getText().toString();
        if(mModel != null){
            mModel.getAccountData(input, new AccountCallBack() {
                @Override
                public void onSuccess(String message) {
                    setResult(message);
                }

                @Override
                public void onFail() {
                    setResult("failed");
                }
            });
        }
    }
}
