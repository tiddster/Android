package Doctor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
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
    EditText editName,editAge,editBlood;
    RadioButton maleButton,femaleButton;
    RadioGroup mRadioGroup;
    TextView reserve,textId;
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

        initView();
        Listener();
    }

    public void initView(){
        editName = findViewById(R.id.editName);
        editAge = findViewById(R.id.editAge);
        editBlood = findViewById(R.id.editBlood);
        maleButton = findViewById(R.id.maleButton);
        femaleButton = findViewById(R.id.femaleButton);
        mRadioGroup = findViewById(R.id.sexGroup);
        textId = findViewById(R.id.textId);
        reserve = findViewById(R.id.reserve);
        left = findViewById(R.id.left);

        year = mCalendar.get(Calendar.YEAR);
        month = mCalendar.get(Calendar.MONTH+1);
        day = mCalendar.get(Calendar.DATE);

        Query();
        textId.setText(ID);
    }

    public void Listener(){
        mRadioGroup.check(maleButton.getId());

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
        PatientInfo patientInfo = new PatientInfo(editName.getText().toString(),id,sex,Integer.parseInt(editAge.getText().toString()),editBlood.getText().toString());
        patientInfo.setPassword("123456");
        mPatientInfoDao.InsertPatient(patientInfo);
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
