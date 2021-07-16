package com.example.yunt;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import Bean.DoctorAccount;
import Bean.DoctorAccountDao;
import Bean.PatientDataBase;
import Bean.PatientInfo;
import Doctor.DInsertNewPatientActivity;

public class SignUpActivity extends AppCompatActivity {
    public PatientDataBase nPatientDataBase;
    public DoctorAccountDao mDoctorAccountDao;
    EditText editName,editHospital,editPassword,editAge;
    int index;
    ImageView left;
    TextView confirmSignUp,DId;
    int id;
    String ID;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_sign_up);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


        initView();
        Listener();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void initView(){
        nPatientDataBase = PatientDataBase.getDAInstance(this);
        mDoctorAccountDao = nPatientDataBase.getDADao();

        editName = findViewById(R.id.d_editName);
        editHospital = findViewById(R.id.d_editHospital);
        editPassword = findViewById(R.id.d_editPassword);
        editAge = findViewById(R.id.d_editAge);
        left = findViewById(R.id.leave_sign_up);
        confirmSignUp = findViewById(R.id.confirm_sign_up);
        DId = findViewById(R.id.d_textId);

        Query();
        DId.setText(String.valueOf(ID));
    }

    public void Listener(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        confirmSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editName.getText()) && !TextUtils.isEmpty(editAge.getText())
                        && !TextUtils.isEmpty(editHospital.getText()) && !TextUtils.isEmpty(editPassword.getText())){
                    if(Integer.parseInt(editAge.getText().toString())<65&&Integer.parseInt(editAge.getText().toString())>28){
                        DoctorAccount doctorAccount = new DoctorAccount(editName.getText().toString(),editPassword.getText().toString(),editHospital.getText().toString(),id);
                        mDoctorAccountDao.insertAccount(doctorAccount);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this,"年龄不符合要求,无法注册",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SignUpActivity.this,"信息错误，请重新输入",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void Query(){
        List<DoctorAccount> mDoctorAccounts =  mDoctorAccountDao.getAllDoctorAccount();
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH+2);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        index = 1;

        System.out.println(mDoctorAccounts.size());

        ID = String.valueOf(year)+String.valueOf(month)+String.valueOf(day)+String.valueOf(index);
        id = Integer.parseInt(ID);

        if(mDoctorAccounts.size() == 0){
            index = 1;
        } else {
            for(DoctorAccount doctorAccount : mDoctorAccounts){
                System.out.println(id +" " +doctorAccount.getId());
                if(id == doctorAccount.getId()){
                    index ++;
                    ID = String.valueOf(year)+String.valueOf(month)+String.valueOf(day)+String.valueOf(index);
                    id = Integer.parseInt(ID);
                } else {
                    ID = String.valueOf(year)+String.valueOf(month)+String.valueOf(day)+String.valueOf(index);
                    id = Integer.parseInt(ID);
                    break;
                }
            }
        }
    }
}
