package com.example.yunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import Bean.PatientDataBase;
import Bean.PatientInfo;
import Bean.PatientInfoDao;
import Doctor.DoctorActivity;
import Patient.PatientActivity;

public class LoginActivity extends AppCompatActivity {
    EditText account,password;
    TextView exchangeButton;
    Button loginButton;
    int type = 0;
    public PatientDataBase mPatientDataBase;
    public PatientInfoDao mPatientInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        initView();
        Listener();
    }

    public void initView() {
        mPatientDataBase = PatientDataBase.getBasicInstance(this);
        mPatientInfoDao = mPatientDataBase.getPatientInfoDao();

        exchangeButton = findViewById(R.id.exchange);
        loginButton = findViewById(R.id.login_button);

        password = findViewById(R.id.Password);
        account = findViewById(R.id.Account);
    }

    public void Listener() {
        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0) {
                    type = 1;
                    loginButton.setBackgroundColor(Color.parseColor("#2196F3"));
                    loginButton.setText("医生登录");
                }
                else {
                    type = 0;
                    loginButton.setBackgroundColor(Color.parseColor("#4CAF50"));
                    loginButton.setText("患者登录");
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0) {
                    if(PatientLogIn()) {
                        Intent intent = new Intent(LoginActivity.this, PatientActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Intent intent = new Intent(LoginActivity.this, DoctorActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    public boolean PatientLogIn(){
        boolean isLogIn = false;
        List<PatientInfo> patientInfoList = mPatientInfoDao.getList();
        for(PatientInfo patientInfo : patientInfoList){
            if(account.getText().toString().equals(String.valueOf(patientInfo.getNumber())) && password.getText().toString().equals(patientInfo.getPassword()))
                isLogIn = true;
        }
        return isLogIn;
    }
}