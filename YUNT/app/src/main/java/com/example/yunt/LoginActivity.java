package com.example.yunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    TextView exchangeButton;
    Button loginButton;
    int type = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        initView();
        Listener();
    }

    public void initView() {
        exchangeButton = findViewById(R.id.exchange);
        loginButton = findViewById(R.id.login_button);
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
                    Intent intent = new Intent(LoginActivity.this, PatientActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(LoginActivity.this, DoctorActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}