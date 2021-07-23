package com.example.calculate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    EditText mEditText;
    TextView btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    TextView btn_plus, btn_minus, btn_multi, btn_division;
    TextView btn_clear,btn_delete;
    String text = "";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    public void initView() {
        // 一开始就获取editText焦点，并不弹出软键盘
        mEditText = findViewById(R.id.Edit_Space);
        mEditText.requestFocus();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mEditText.setShowSoftInputOnFocus(false);
        }

        btn_0 = findViewById(R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_plus = findViewById(R.id.btn_plus);
        btn_minus = findViewById(R.id.btn_minus);
        btn_multi = findViewById(R.id.btn_multi);
        btn_division = findViewById(R.id.btn_division);
        btn_delete = findViewById(R.id.btn_delete);
        btn_clear = findViewById(R.id.btn_clear);

        btn_0.setOnClickListener(this);
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multi.setOnClickListener(this);
        btn_division.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mEditText.setTextSize(50);
        switch (v.getId()){
            case R.id.btn_0: text += "0";
                break;
            case R.id.btn_1: text += "1";
                break;
            case R.id.btn_2: text += "2";
                break;
            case R.id.btn_3: text += "3";
                break;
            case R.id.btn_4: text += "4";
                break;
            case R.id.btn_5: text += "5";
                break;
            case R.id.btn_6: text += "6";
                break;
            case R.id.btn_7: text += "7";
                break;
            case R.id.btn_8: text += "8";
                break;
            case R.id.btn_9: text += "9";
                break;
            case R.id.btn_plus:
                if(insertSymbol()) text += "+";
                break;
            case R.id.btn_minus:
                if(insertSymbol()) text += "-";
                else if(text.length() == 0) text += "-";
                break;
            case R.id.btn_multi:
                if(insertSymbol()) text += "×";
                break;
            case R.id.btn_division:
                if(insertSymbol()) text += "÷";
                break;
            case R.id.btn_delete:
                if(text.length()>0) {
                    text = text.substring(0, text.length() - 1);
                }
                break;
            case R.id.btn_clear:text = "";
                break;

        }
        //字符多了就调整字符
        if(text.length() > 11 && text.length() <= 13){
            mEditText.setTextSize(40);
        } else if (text.length() > 13)
            mEditText.setTextSize(30);

        mEditText.setText(text);
        //光标移至末尾
        mEditText.setSelection(text.length());
    }

    //判断前一个字符是否是数字
    public boolean insertSymbol(){
        String temp = "";
        if(text.length()>=2)
            temp = text.substring(text.length()-1,text.length());
        for(int i=0; i<=9; i++){
            if(temp.equals(String.valueOf(i))){
                return true;
            }
        }
        return false;
    }
}