package com.example.mvvm.model;

import com.example.mvvm.R;

import java.util.Random;

public class Model {
    public void getAccountData(String accountName, AccountCallBack accountCallBack){
        Random random = new Random();
        boolean isSuccess = random.nextBoolean();
        if(isSuccess){
            Account account = new Account(100,accountName);
            accountCallBack.onSuccess(account.toString());
        } else {
            accountCallBack.onFail();
        }
    }
}
