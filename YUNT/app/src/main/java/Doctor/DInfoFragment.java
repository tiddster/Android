package Doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yunt.R;

import java.util.ArrayList;
import java.util.List;

import Bean.PatientInfo;

public class DInfoFragment extends Fragment {
    public RecyclerView mRecyclerView;
    public PatientAdapter mPatientAdapter;
    public List<PatientInfo> mPatientInfoList = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.d_patient_info_fragment,container,false);
        initView(view);
        return view;
    }

    public void initView(View view) {
        Create();
        mRecyclerView = view.findViewById(R.id.d_patient_recycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPatientAdapter = new PatientAdapter(mPatientInfoList);
        mRecyclerView.setAdapter(mPatientAdapter);
    }

    public void Create(){
        for(int i=0; i<10; i++){
            PatientInfo patientInfo = new PatientInfo("xxx",i,"xx",i+60,"A");
            mPatientInfoList.add(patientInfo);
        }
    }

    private class PatientHolder extends RecyclerView.ViewHolder{
        private TextView name,id,sex,age,blood;

        public PatientHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.p_name);
            id = itemView.findViewById(R.id.p_id);
            sex = itemView.findViewById(R.id.p_sex);
            age = itemView.findViewById(R.id.p_age);
            blood = itemView.findViewById(R.id.p_blood);
        }

        public  void bind(PatientInfo patientInfo, int position){
            name.setText(patientInfo.getName());
            id.setText(String.valueOf(patientInfo.getNumber()));
            sex.setText(patientInfo.getSex());
            age.setText(String.valueOf(patientInfo.getAge()));
            blood.setText(patientInfo.getBlood());
        }
    }

    private class PatientAdapter extends RecyclerView.Adapter<PatientHolder>{
        private List<PatientInfo> mPatientInfoList;
        private PatientAdapter(List<PatientInfo> patientInfoList){
            mPatientInfoList = patientInfoList;
        }

        @NonNull
        @Override
        public PatientHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.d_patient_item, parent, false);
            return new PatientHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull PatientHolder holder, int position) {
            PatientInfo patientInfo = mPatientInfoList.get(position);
            holder.bind(patientInfo,position);
        }

        @Override
        public int getItemCount() {
            return mPatientInfoList.size();
        }
    }
}
