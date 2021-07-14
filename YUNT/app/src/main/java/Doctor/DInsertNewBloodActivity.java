package Doctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yunt.R;

import java.util.ArrayList;
import java.util.List;

import Bean.PatientBlood;
import Bean.PatientBloodDao;
import Bean.PatientBook;
import Bean.PatientBookDao;
import Bean.PatientDataBase;

public class DInsertNewBloodActivity extends AppCompatActivity {
    private static String doctorName,hospital;
    ImageView left;
    TextView reserve,nextTime,nameText,ageText,idText,doctorText,hospitalText;
    EditText AWEdit,BWEdit,APEdit,BPEdit,CREdit;
    PatientDataBase mPatientDataBase,nPatientDataBase;
    PatientBloodDao mPatientBloodDao;
    PatientBookDao mPatientBookDao;
    PatientBook mPatientBook;
    float AW,BW,AP,BP,CR;
    int nextMonth = 0,nextDay = 0;
    int ID;
    int age;
    String name;
    List<PatientBook> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_book_confirm_window);

        initView();
        Listener();
    }

    public void initView(){
        SharedPreferences sp = getSharedPreferences("GET", 0);
        hospital = sp.getString("HOSPITAL", null);
        doctorName = sp.getString("DOCTORNAME", null);

        mPatientDataBase = PatientDataBase.getBLOODInstance(this);
        mPatientBloodDao = mPatientDataBase.getPatientBloodDao();
        nPatientDataBase = PatientDataBase.getDateInstance(this);
        mPatientBookDao = nPatientDataBase.getDateDao();
        mList = mPatientBookDao.getDateList();

        Intent intent = getIntent();
        ID = intent.getIntExtra("ID",0);
        age = intent.getIntExtra("AGE",0);
        name = intent.getStringExtra("NAME");

        for(PatientBook patientBook : mList){
            if(patientBook.getId() == ID){
                mPatientBook = patientBook;
                break;
            }
        }

        left = findViewById(R.id.leave);
        reserve = findViewById(R.id.confirm);

        AWEdit = findViewById(R.id.Aweight);
        BWEdit = findViewById(R.id.Bweight);
        APEdit = findViewById(R.id.Apressure);
        BPEdit = findViewById(R.id.Bpressure);
        CREdit = findViewById(R.id.CR);
        nameText = findViewById(R.id.p_confirm_name);
        idText = findViewById(R.id.p_confirm_number);
        ageText = findViewById(R.id.p_confirm_age);
        hospitalText = findViewById(R.id.d_confirm_hospital);
        doctorText = findViewById(R.id.d_confirm_doctorName);

        nameText.setText(name);
        idText.setText(String.valueOf(ID));
        ageText.setText("年龄："+String.valueOf(age));
        doctorText.setText("就诊医生："+doctorName);
        hospitalText.setText("就诊医院："+hospital);


        nextTime = findViewById(R.id.nextTime);
    }

    public void Listener(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(JudgeNull(APEdit)&&JudgeNull(BPEdit)&&JudgeNull(AWEdit)&&JudgeNull(BWEdit)&&JudgeNull(CREdit)&&nextMonth!=0&&nextDay!=0){
                    AP = Float.parseFloat(APEdit.getText().toString());
                    BP = Float.parseFloat(BPEdit.getText().toString());
                    AW = Float.parseFloat(AWEdit.getText().toString());
                    BW = Float.parseFloat(BWEdit.getText().toString());
                    CR = Float.parseFloat(CREdit.getText().toString());
                    PatientBlood patientBlood = new PatientBlood(ID,BW,AW,AP,BP,CR,nextMonth,nextDay);
                    mPatientBloodDao.InsertBlood(patientBlood);
                    mPatientBook.setCircumstance(2);
                    mPatientBookDao.UpdateData(mPatientBook);
                    finish();
                } else {
                    Toast.makeText(DInsertNewBloodActivity.this,"请输入正确数据",Toast.LENGTH_SHORT).show();
                }
            }
        });

        nextTime.setOnClickListener(new View.OnClickListener() {
            CalendarView mCalendarView;
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.d_new_blood_popupwindow, null, false);

                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //参数为1.View 2.宽度 3.高度
                popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                popupWindow.setFocusable(true);

                backgroundAlpha(0.5f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //弹窗消失恢复原来的透明度
                        backgroundAlpha(1f);
                    }
                });

                mCalendarView = view.findViewById(R.id.calendarView3);
                mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        nextMonth = month + 1;
                        nextDay = dayOfMonth;
                        nextTime.setText("建议下次预约时间： " + String.valueOf(nextMonth) + "月"+ String.valueOf(nextDay) + "日");
                        popupWindow.dismiss();
                    }
                });

                popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, -100);
            }
        });
    }

    private void backgroundAlpha(float v) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = v; //0.0-1.0
        getWindow().setAttributes(lp);
    }

    public void InsertBlood(){

    }

    public boolean JudgeNull(EditText editText){
        return !TextUtils.isEmpty(editText.getText())&&editText.getText()!=null ? true : false;
    }

}
