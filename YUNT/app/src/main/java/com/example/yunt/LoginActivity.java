package com.example.yunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
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
    TextView exchangeButton,Title;
    TextView loginButton;
    ConstraintLayout mConstraintLayout;
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
        mConstraintLayout = findViewById(R.id.login_part);
        password = findViewById(R.id.Password);
        account = findViewById(R.id.Account);
        Title = findViewById(R.id.title);
    }

    public void Listener() {
        exchangeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0) {
                    type = 1;
                    mConstraintLayout.setBackgroundResource(R.drawable.gradient1);
                    loginButton.setBackgroundResource(R.drawable.shape_login);
                    Title.setTextColor(Color.parseColor("#87B8FD"));
                    loginButton.setTextColor(Color.parseColor("#87B8FD"));
                    loginButton.setText("医生登录");
                }
                else {
                    type = 0;
                    mConstraintLayout.setBackgroundResource(R.drawable.gradient2);
                    loginButton.setBackgroundResource(R.drawable.shape_login_2);
                    Title.setTextColor(Color.parseColor("#01BAA7"));
                    loginButton.setTextColor(Color.parseColor("#01BAA7"));
                    loginButton.setText("患者登录");
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (type == 0) {
                    if(PatientLogIn()) {
                        SharedPreferences sp = getSharedPreferences("GET",0);
                        SharedPreferences.Editor editor = sp.edit();

                        char s = account.getText().toString().charAt(0);
                        if(s >= '0'&& s<='9') {
                            int Account = Integer.parseInt(account.getText().toString());
                            editor.putInt("ID", Account);
                            editor.putString("NAME", mPatientInfoDao.getById(Account).getName());
                            editor.putInt("AGE",mPatientInfoDao.getById(Account).getAge());
                            editor.commit();
                        } else {
                            editor.putInt("ID", mPatientInfoDao.getByName(account.getText().toString()).getNumber());
                            editor.putString("NAME", account.getText().toString());
                            editor.putInt("AGE",mPatientInfoDao.getByName(account.getText().toString()).getAge());
                            editor.commit();
                        }

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
            if(( account.getText().toString().equals(patientInfo.getName())
                    ||account.getText().toString().equals(String.valueOf(patientInfo.getNumber())) )
                    && password.getText().toString().equals(patientInfo.getPassword()))
                isLogIn = true;
        }
        return isLogIn;
    }
}