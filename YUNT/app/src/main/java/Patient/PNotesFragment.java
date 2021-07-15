package Patient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yunt.R;

import java.util.ArrayList;
import java.util.List;

import Bean.PatientBlood;
import Bean.PatientBloodDao;
import Bean.PatientDataBase;
import Doctor.DDetailsActivity;

public class PNotesFragment extends Fragment {
    private PatientDataBase mPatientDataBase;
    private PatientBloodDao mPatientBloodDao;
    RecyclerView mRecyclerView;
    List<PatientBlood> mBloodList = new ArrayList<>();
    private static int id;
    private static String name;
    private RecordAdapter mRecordAdapter;
    TextView TouXiTimes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_patient_info_fragment,container,false);
        initView(view);
        return view;
    }

    public void initView(View view){
        mPatientDataBase = PatientDataBase.getBLOODInstance(getActivity());
        mPatientBloodDao = mPatientDataBase.getPatientBloodDao();

        SharedPreferences sp = getActivity().getSharedPreferences("GET", 0);
        id = sp.getInt("ID", 0);
        name = sp.getString("NAME", null);

        TouXiTimes = view.findViewById(R.id.p_touxi_times);
        mRecyclerView = view.findViewById(R.id.recycleView5);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Update();

        TouXiTimes.setText("透析次数:"+String.valueOf(mBloodList.size())+"次");
    }

    public void Update(){
        for(PatientBlood patientBlood : mPatientBloodDao.getAllBlood()){
            if(id == patientBlood.getId()){
                mBloodList.add(patientBlood);
            }
        }

        mRecordAdapter = new RecordAdapter(mBloodList);
        mRecyclerView.setAdapter(mRecordAdapter);
    }

    private class RecordHolder extends RecyclerView.ViewHolder{
        private TextView recordTime,weight,recordCR,pressure,recordDoctor;
        ConstraintLayout item;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            recordTime = itemView.findViewById(R.id.recordTime);
            recordCR = itemView.findViewById(R.id.recordCR);
            weight = itemView.findViewById(R.id.weight);
            pressure = itemView.findViewById(R.id.pressure);
            recordDoctor = itemView.findViewById(R.id.recordDoctor);
            item = itemView.findViewById(R.id.record_item);
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
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
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
