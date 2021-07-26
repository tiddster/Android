package com.example.calculate;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEditText;
    TextView btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9;
    TextView btn_plus, btn_minus, btn_multi, btn_division, btn_power, btn_left, btn_right, btn_ln;
    TextView btn_clear, btn_delete, btn_equal;
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
        btn_power = findViewById(R.id.btn_power);
        btn_left = findViewById(R.id.btn_left);
        btn_right = findViewById(R.id.btn_right);
        btn_ln = findViewById(R.id.btn_ln);
        btn_equal = findViewById(R.id.btn_equal);

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
        btn_power.setOnClickListener(this);
        btn_left.setOnClickListener(this);
        btn_right.setOnClickListener(this);
        btn_ln.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        mEditText.setTextSize(50);
        Editable editable = mEditText.getText();
        int index = mEditText.getSelectionStart();
        boolean isCompute = false;
        switch (v.getId()) {
            case R.id.btn_0:
                //在光标处插入符号
                editable.insert(index, "0");
                text = editable.toString();
                break;
            case R.id.btn_1:
                editable.insert(index, "1");
                text = editable.toString();
                break;
            case R.id.btn_2:
                editable.insert(index, "2");
                text = editable.toString();
                break;
            case R.id.btn_3:
                editable.insert(index, "3");
                text = editable.toString();
                break;
            case R.id.btn_4:
                editable.insert(index, "4");
                text = editable.toString();
                break;
            case R.id.btn_5:
                editable.insert(index, "5");
                text = editable.toString();
                break;
            case R.id.btn_6:
                editable.insert(index, "6");
                text = editable.toString();
                break;
            case R.id.btn_7:
                editable.insert(index, "7");
                text = editable.toString();
                break;
            case R.id.btn_8:
                editable.insert(index, "8");
                text = editable.toString();
                break;
            case R.id.btn_9:
                editable.insert(index, "9");
                text = editable.toString();
                break;
            case R.id.btn_left:
                editable.insert(index, "(");
                text = editable.toString();
                break;
            case R.id.btn_right:
                editable.insert(index, ")");
                text = editable.toString();
                break;

            case R.id.btn_ln:
                editable.insert(index, "ln()");
                text = editable.toString();
                break;
            case R.id.btn_power:
                if (insertSymbol(index))
                    editable.insert(index, "^");
                text = editable.toString();
                break;
            case R.id.btn_plus:
                if (insertSymbol(index))
                    editable.insert(index, "+");
                text = editable.toString();
                break;
            case R.id.btn_minus:
                if (insertSymbol(index)) editable.insert(index, "-");
                else if (text.length() == 0) editable.insert(index, "-");
                text = editable.toString();
                break;
            case R.id.btn_multi:
                if (insertSymbol(index))
                    editable.insert(index, "×");
                text = editable.toString();
                break;
            case R.id.btn_division:
                if (insertSymbol(index))
                    editable.insert(index, "÷");
                text = editable.toString();

                break;

            case R.id.btn_delete:
                if (index > 0) {
                    editable.delete(index - 1, index);
                    text = editable.toString();
                }
                break;
            case R.id.btn_clear:
                text = "";
                break;
            case R.id.btn_equal:
                text = Suffix(text);
                isCompute = true;
                break;
        }
        //字符多了就调整字符
        if (text.length() > 11 && text.length() <= 13) {
            mEditText.setTextSize(40);
        } else if (text.length() > 13)
            mEditText.setTextSize(30);

        mEditText.setText(text);

        //光标移至正确的位置
        if (isCompute) {
            mEditText.setSelection(text.length());
        } else {
            if (index + 1 <= text.length())
                mEditText.setSelection(index + 1);
            else if (index == text.length())
                mEditText.setSelection(index);
            else
                mEditText.setSelection(index - 1);
        }
    }

    //判断前一个字符是否是数字
    public boolean insertSymbol(int index) {
        String temp = "";
        if (text.length() > 0)
            temp = text.substring(index - 1, index);
        for (int i = 0; i <= 9; i++) {
            if (temp.equals(String.valueOf(i))) {
                return true;
            }
        }
        if (temp == ")") {
            return true;
        }
        return false;
    }

    public String Suffix(String text) {
        Stack<String> numStack = new Stack<>();
        Stack<String> symbolStack = new Stack<>();
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = text.toCharArray();
        String str = "";
        boolean beforeIsNumber = false;
        for (char c : chars) {
            if (isNum(c)) {
                str += c;
                beforeIsNumber = true;
            } else if (c == '(') {
                if (beforeIsNumber) {
                    numStack.push(str);
                    beforeIsNumber = false;
                }
                str = String.valueOf(c);
                numStack.push(str);

            } else if (c == ')') {
                while (true) {
                    String top = symbolStack.pop();
                    if ("(".equals(top)) {
                        break;
                    } else {
                        numStack.push(top);
                    }
                }
            } else {
                while (true) {
                    if (beforeIsNumber) {
                        numStack.push(str);
                        beforeIsNumber = false;
                    }
                    str = String.valueOf(c);
                    if (symbolStack.isEmpty() || symbolStack.peek().equals("(")) {
                        symbolStack.push(str);
                        break;
                    } else if (priority(str) > priority(symbolStack.peek())) {
                        symbolStack.push(str);
                        break;
                    } else {
                        numStack.push(symbolStack.pop());
                    }
                }
            }
        }
        if(beforeIsNumber){
            numStack.push(str);
        }
        while(!symbolStack.isEmpty()){
            numStack.push(symbolStack.pop());
        }
        while (!numStack.isEmpty()) {
            stringBuilder.append(numStack.pop());
        }
        return stringBuilder.toString();
    }

    public int priority(String str) {
        if (str.equals("^")) return 3;
        else if (str.equals("×") || str.equals("÷")) return 2;
        else if (str.equals("+") || str.equals("-")) return 1;
        else return 0;
    }


    public static boolean isNum(char c) {
        return c >= 48 && c <= 57;
    }
}