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

import Bean.DoctorAccount;
import Bean.DoctorAccountDao;
import Bean.PatientDataBase;
import Bean.PatientInfo;
import Bean.PatientInfoDao;
import Doctor.DoctorActivity;
import Patient.PatientActivity;

public class LoginActivity extends AppCompatActivity {
    EditText account,password;
    TextView exchangeButton,Title;
    TextView loginButton;
    String DoctorName,Hospital;
    ConstraintLayout mConstraintLayout;
    int type = 0;
    public PatientDataBase mPatientDataBase;
    public PatientInfoDao mPatientInfoDao;
    public PatientDataBase nPatientDataBase;
    public DoctorAccountDao mDoctorAccountDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        initView();
        Listener();
    }

    public void initView() {
        mPatientDataBase = PatientDataBase.getBasicInstance(this);
        nPatientDataBase = PatientDataBase.getDAInstance(this);
        mPatientInfoDao = mPatientDataBase.getPatientInfoDao();
        mDoctorAccountDao = nPatientDataBase.getDADao();

        exchangeButton = findViewById(R.id.exchange);
        loginButton = findViewById(R.id.login_button);
        mConstraintLayout = findViewById(R.id.login_part);
        password = findViewById(R.id.Password);
        account = findViewById(R.id.Account);
        Title = findViewById(R.id.title);

        insert();
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
                            editor.putString("HOSPITAL",mPatientInfoDao.getById(Account).getHospital());
                            editor.commit();
                        } else {
                            editor.putInt("ID", mPatientInfoDao.getByName(account.getText().toString()).getNumber());
                            editor.putString("NAME", account.getText().toString());
                            editor.putInt("AGE",mPatientInfoDao.getByName(account.getText().toString()).getAge());
                            editor.putString("HOSPITAL",mPatientInfoDao.getByName(account.getText().toString()).getHospital());
                            editor.commit();
                        }

                        Intent intent = new Intent(LoginActivity.this, PatientActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (DoctorLogIn()){
                            SharedPreferences sp = getSharedPreferences("GET",0);
                            SharedPreferences.Editor editor = sp.edit();

                            editor.putString("HOSPITAL",Hospital);
                            editor.putString("DOCTORNAME",DoctorName);
                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, DoctorActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_SHORT).show();
                        }

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
                    && password.getText().toString().equals(patientInfo.getPassword())) {
                isLogIn = true;
                break;
            }
        }
        return isLogIn;
    }

    public boolean DoctorLogIn(){
        boolean isLogIn = false;
        List<DoctorAccount> doctorAccountList = mDoctorAccountDao.getAllDoctorAccount();
        for(DoctorAccount doctorAccount : doctorAccountList){
            if(doctorAccount.getAccount().equals(account.getText().toString())
                    && doctorAccount.getPassword().equals(password.getText().toString())){
                DoctorName = doctorAccount.getAccount();
                Hospital = doctorAccount.getHospital();
                isLogIn = true;
                break;
            }
        }
        return isLogIn;
    }

    public void insert(){
        DoctorAccount doctorAccount = new DoctorAccount("Mike","123456","AAA医院",1);
        DoctorAccount doctorAccount2 = new DoctorAccount("Tom","123456","BBB医院",2);
        DoctorAccount doctorAccount3 = new DoctorAccount("Tony","123456","AAA医院",3);
        mDoctorAccountDao.insertAccount(doctorAccount,doctorAccount2,doctorAccount3);
    }
}