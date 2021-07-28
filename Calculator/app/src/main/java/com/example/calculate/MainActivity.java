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
import android.widget.Toast;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEditText;
    TextView btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_p;
    TextView btn_plus, btn_minus, btn_multi, btn_division, btn_power, btn_left, btn_right, btn_ln;
    TextView btn_clear, btn_delete, btn_equal;
    String text = "";
    String reg = "[0-9]\\d*\\.?\\d*";

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
        btn_p = findViewById(R.id.btn_point);

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
        btn_p.setOnClickListener(this);
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

            case R.id.btn_point:
                editable.insert(index, ".");
                text = editable.toString();
                break;
            case R.id.btn_ln:
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
                text = toSuffix(toStringList(text)) == null ? text : String.valueOf(calculate(toSuffix(toStringList(text))));
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
        if (temp.equals(")")) {
            return true;
        }
        return false;
    }

    public List<String> toStringList(String s) {
        List<String> stringList = new ArrayList<>();
        int i = 0;
        String str;
        char c;
        do {
            c = s.charAt(i);//获取元素
            if (c == '+' || c == '-' || c == '×' || c == '÷' || c == '(' || c == ')' || c == '^') {
                //如果元素是运算符直接添加到List
                stringList.add(c + "");
                i++;
            } else if (Character.isDigit(c) || c == '.') {
                //如果元素是数字和小数点
                str = "";
                while (i < s.length() && (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {//直至下个不是小数点或数字的位置
                    str += "" + s.charAt(i++);
                }
                stringList.add(str);
            } else {
                i++;
            }
        } while (i < s.length());

        return stringList;
    }

    public List<String> toSuffix(List<String> strings) {
        Stack<String> symbolStack = new Stack<>();
        Stack<String> tempStack = new Stack<>();
        boolean isError= false;

        for (String string : strings) {
            if (string.matches(reg)) {
                tempStack.push(string);
            } else if (isOperator(string)) {
                while (true) {
                    if (symbolStack.isEmpty() || "(".equals(symbolStack.peek())) {
                        symbolStack.push(string);
                        break;
                    } else if (priority(string) > priority(symbolStack.peek())) {
                        symbolStack.push(string);
                        break;
                    } else {
                        tempStack.push(symbolStack.pop());
                    }
                }
            } else if ("(".equals(string)) {
                symbolStack.push(string);
            } else if (")".equals(string)) {
                while (true) {
                    String top = symbolStack.pop();
                    if ("(".equals(top)) {
                        break;
                    } else {
                        tempStack.push(top);
                    }
                }
            } else {
                Toast.makeText(this, "表达式错误", Toast.LENGTH_SHORT).show();
                isError = true;
            }
        }

        if(!isError) {
            while (!symbolStack.isEmpty()) {
                tempStack.push(symbolStack.pop());
            }
            Stack<String> resStack = new Stack<>();
            while (!tempStack.isEmpty()) {
                resStack.push(tempStack.pop());
            }

            List<String> res = new ArrayList<>();
            while (!resStack.isEmpty()) {
                res.add(resStack.pop());
            }
            return res;
        } else {
            return null;
        }
    }

    public float calculate(List<String> stringList) {
        Stack<String> calculateStack = new Stack<>();
        for (String string : stringList) {
            if (string.matches(reg)) {
                calculateStack.push(string);
            } else {
                float num2 = Float.parseFloat(calculateStack.pop());
                float num1 = Float.parseFloat(calculateStack.pop());
                float res = 0;
                if (string.equals("+")) {
                    res = num1 + num2;
                } else if (string.equals("-")) {
                    res = num1 - num2;
                } else if (string.equals("×")) {
                    res = num1 * num2;
                } else if (string.equals("÷")) {
                    res = num1 / num2;
                } else if(string.equals("^")){
                    res = (float) Math.pow(num1,num2);
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //计算过的结果再次入栈
                calculateStack.push("" + res);
            }
        }
        return Float.parseFloat(calculateStack.pop());
    }

    public  int priority(String str) {
        if (str.equals("^")) return 3;
        else if (str.equals("×") || str.equals("÷")) return 2;
        else if (str.equals("+") || str.equals("-")) return 1;
        else return 0;
    }


    public boolean isOperator(String val) {
        return "+".equals(val) || "-".equals(val) || "×".equals(val) || "÷".equals(val) || val.equals("^");
    }
}