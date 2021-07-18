package com.example.yunt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
    TextView exchangeButton,Title,loginButton,signUpButton;
    String DoctorName,Hospital;
    int DoctorId;
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

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


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
        signUpButton = findViewById(R.id.sign_up);

        signUpButton.setVisibility(View.GONE);
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
                    signUpButton.setVisibility(View.VISIBLE);
                    loginButton.setText("医生登录");
                }
                else {
                    type = 0;
                    mConstraintLayout.setBackgroundResource(R.drawable.gradient2);
                    loginButton.setBackgroundResource(R.drawable.shape_login_2);
                    Title.setTextColor(Color.parseColor("#01BAA7"));
                    loginButton.setTextColor(Color.parseColor("#01BAA7"));
                    signUpButton.setVisibility(View.GONE);
                    loginButton.setText("患者登录");
                }
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ADMLogIn()){
                    Intent intent = new Intent(LoginActivity.this,ADMActivity.class);
                    startActivity(intent);
                } else if (type == 0) {
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
                            editor.putInt("ID",DoctorId);
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

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
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
            if(doctorAccount.getId() == Integer.parseInt(account.getText().toString())
                    && doctorAccount.getPassword().equals(password.getText().toString())){
                DoctorName = doctorAccount.getAccount();
                Hospital = doctorAccount.getHospital();
                DoctorId = doctorAccount.getId();
                isLogIn = true;
                break;
            }
        }
        return isLogIn;
    }

    public boolean ADMLogIn(){
        boolean isLogIn = false;
            if(Integer.parseInt(account.getText().toString())==87654321
                    && password.getText().toString().equals("12345678")){
                isLogIn = true;
            }
        return isLogIn;
    }
}