package Doctor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yunt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import Bean.PatientBlood;
import Bean.PatientBloodDao;
import Bean.PatientDataBase;

public class DDetailsActivity extends AppCompatActivity {
    private PatientDataBase mPatientDataBase;
    private PatientBloodDao mPatientBloodDao;
    RecyclerView mRecyclerView;
    RecordAdapter mRecordAdapter;
    List<PatientBlood> mBloodList = new ArrayList<>();
    ImageView left;
    TextView nameText,ageText,timesText;
    String name;
    int age,ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_record);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        initView();
        Listener();
    }

    public void initView(){
        mPatientDataBase = PatientDataBase.getBLOODInstance(this);
        mPatientBloodDao = mPatientDataBase.getPatientBloodDao();

        Intent intent = getIntent();
        name = intent.getStringExtra("NAME");
        age = intent.getIntExtra("AGE",0);
        ID = intent.getIntExtra("ID",0);

        mRecyclerView = findViewById(R.id.recycleView4);
        left = findViewById(R.id.record_leave);
        nameText = findViewById(R.id.record_name);
        ageText = findViewById(R.id.record_age);
        timesText = findViewById(R.id.record_times);
        mRecyclerView = findViewById(R.id.recycleView4);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Create();

        nameText.setText(name);
        ageText.setText("年龄："+String.valueOf(age));
        timesText.setText("透析次数:"+mBloodList.size());
    }

    public void Create(){
        for(PatientBlood patientBlood : mPatientBloodDao.getAllBlood()){
            if(patientBlood.getId() == ID){
                mBloodList.add(patientBlood);
            }
        }
        mRecordAdapter = new RecordAdapter(mBloodList);
        mRecyclerView.setAdapter(mRecordAdapter);
    }

    public void Listener(){
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private class RecordHolder extends RecyclerView.ViewHolder{
        private TextView recordTime,weight,recordCR,pressure,recordDoctor;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            recordTime = itemView.findViewById(R.id.recordTime);
            recordCR = itemView.findViewById(R.id.recordCR);
            weight = itemView.findViewById(R.id.weight);
            pressure = itemView.findViewById(R.id.pressure);
            recordDoctor = itemView.findViewById(R.id.recordDoctor);
        }

        public void bind(PatientBlood patientBlood){
            recordTime.setText("透析时间："+String.valueOf(patientBlood.getThis_month())+"月"+String.valueOf(patientBlood.getThis_day())+"日");
            recordDoctor.setText(patientBlood.getDoctorName());
            recordCR.setText(String.valueOf(patientBlood.getCR()));
            weight.setText(String.valueOf(patientBlood.getAWeight())+" ~ "+String.valueOf(patientBlood.getBWeight()));
            pressure.setText(String.valueOf(patientBlood.getABloodP())+" ~ "+String.valueOf(patientBlood.getBBloodP()));
        }
    }

    private class RecordAdapter extends RecyclerView.Adapter<RecordHolder>{
        List<PatientBlood> mPatientBloodList;
        public RecordAdapter(List<PatientBlood> mPatientBloodList){
            this.mPatientBloodList = mPatientBloodList;
        }

        @NonNull
        @Override
        public RecordHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getApplicationContext());
            View view = layoutInflater.inflate(R.layout.d_record_item,parent,false);
            return new RecordHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecordHolder holder, int position) {
            PatientBlood patientBlood = mPatientBloodList.get(position);
            holder.bind(patientBlood);
        }

        @Override
        public int getItemCount() {
            return mPatientBloodList.size();
        }
    }
}
