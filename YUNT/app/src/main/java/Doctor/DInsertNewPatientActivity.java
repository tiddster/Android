package Doctor;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yunt.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Bean.PatientDataBase;
import Bean.PatientInfo;
import Bean.PatientInfoDao;

public class DInsertNewPatientActivity extends AppCompatActivity {
    private static String doctorName,hospital;
    EditText editName,editAge,editBlood;
    RadioButton maleButton,femaleButton;
    RadioGroup mRadioGroup;
    TextView reserve,textId,textDName,textHName;
    ImageView left;
    PatientInfoDao mPatientInfoDao;
    PatientDataBase mPatientDataBase;
    String sex;
    Calendar mCalendar = Calendar.getInstance();
    int year,month,day,index,id;
    String ID;
    List<PatientInfo> mPatientInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_new_patient);

        mPatientDataBase = PatientDataBase.getBasicInstance(this);
        mPatientInfoDao = mPatientDataBase.getPatientInfoDao();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initView();
        Listener();
    }

    public void initView(){
        SharedPreferences sp = getSharedPreferences("GET", 0);
        hospital = sp.getString("HOSPITAL", null);
        doctorName = sp.getString("DOCTORNAME", null);

        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editBlood = findViewById(R.id.editBlood);
        maleButton = findViewById(R.id.maleButton);
        femaleButton = findViewById(R.id.femaleButton);
        mRadioGroup = findViewById(R.id.sexGroup);
        textId = findViewById(R.id.textId);
        reserve = findViewById(R.id.reserve);
        left = findViewById(R.id.left);
        textDName = findViewById(R.id.d_new_patient_DName);
        textHName = findViewById(R.id.d_new_patient_HName);

        textHName.setText("就诊医院："+hospital);
        textDName.setText("就诊医生："+doctorName);

        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH+1);
        day = mCalendar.get(Calendar.DATE);

        Query();
        textId.setText(ID);
    }

    public void Listener(){
        mRadioGroup.check(maleButton.getId());
        sex = "男";

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == maleButton.getId()){
                    sex = "男";
                } else {
                    sex = "女";
                }
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(editName.getText()) && !TextUtils.isEmpty(editAge.getText()) && !TextUtils.isEmpty(editBlood.getText())){
                    Insert();
                    finish();
                }
                else
                    Toast.makeText(DInsertNewPatientActivity.this,"信息错误，请重新输入",Toast.LENGTH_SHORT).show();
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void Insert(){
        int age = Integer.parseInt(editAge.getText().toString());
        String blood = editBlood.getText().toString();
        if(JudgeAge(age)&&JudgeBlood(blood)) {
            PatientInfo patientInfo = new PatientInfo(editName.getText().toString(), id, sex, age, blood);
            patientInfo.setPassword("123456");
            patientInfo.setHospital(hospital);
            mPatientInfoDao.InsertPatient(patientInfo);
        } else {
            Toast.makeText(DInsertNewPatientActivity.this,"数据输入不符合实际",Toast.LENGTH_SHORT).show();
        }
    }

    public boolean JudgeAge(int n){
        if(n>0&&n<120){
            return true;
        } else {
            return false;
        }
    }

    public boolean JudgeBlood(String s){
        if(s.equals("A")||s.equals("AB")||s.equals("B")||s.equals("O")){
            return true;
        } else {
            return false;
        }
    }

    public void Query(){
        mPatientInfoList = mPatientInfoDao.getList();
        index = 1;
        ID = String.valueOf(year)+String.valueOf(month)+String.valueOf(day)+String.valueOf(index);
        id = Integer.parseInt(ID);
        if(mPatientInfoList.size()==0){
            index = 1;
        } else {
            for(PatientInfo patientInfo : mPatientInfoList){
                if(id == patientInfo.getNumber()){
                    index++;
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
