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
import java.util.PropertyResourceBundle;

import Bean.PatientInfo;

public class DBookingFragment extends Fragment {
    public List<PatientInfo> mPatientInfoList = new ArrayList<>();
    public RecyclerView mRecyclerView;
    public BookAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.d_booking_fragment,container,false);
        initView(view);
        return view;
    }

    public void initView(View view){
        Create();
        mRecyclerView = view.findViewById(R.id.bookRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new BookAdapter(mPatientInfoList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public void Create(){
        for(int i=0; i<5; i++){
            PatientInfo patientInfo = new PatientInfo("ABC",i,"男",i+70,"AB");
            patientInfo.setCircumstance(i%3);
            patientInfo.setHours(9+i);
            mPatientInfoList.add(patientInfo);
        }
    }

    private class BookHolder extends RecyclerView.ViewHolder{
        private TextView bookedName,bookedId,bookedHours,bookedCircum;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            bookedName = itemView.findViewById(R.id.bookedName);
            bookedId = itemView.findViewById(R.id.bookedId);
            bookedHours = itemView.findViewById(R.id.bookedTime);
            bookedCircum = itemView.findViewById(R.id.bookedCircum);
        }

        public  void bind(PatientInfo patientInfo, int position){
            bookedName.setText(patientInfo.getName());
            bookedId.setText(String.valueOf(patientInfo.getNumber()));
            bookedHours.setText(String.valueOf(patientInfo.getHours())+":00");
            if(patientInfo.getCircumstance() == 1)
                bookedCircum.setText("待确认");
            else if(patientInfo.getCircumstance() == 2)
                bookedCircum.setText("已确认");
        }
    }

    private class BookAdapter extends  RecyclerView.Adapter<BookHolder>{
        private List<PatientInfo> mPatientInfoList;
        public BookAdapter(List<PatientInfo> list){
            mPatientInfoList = list;
        }

        @NonNull
        @Override
        public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.d_patient_book_item,parent,false);
            return new BookHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull BookHolder holder, int position) {
            PatientInfo patientInfo = mPatientInfoList.get(position);
            holder.bind(patientInfo,position);
        }

        @Override
        public int getItemCount() {
            return mPatientInfoList.size();
        }
    }
}
