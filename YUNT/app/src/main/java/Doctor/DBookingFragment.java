package Doctor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yunt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;

import Bean.PatientBook;
import Bean.PatientBookDao;
import Bean.PatientDataBase;
import Bean.PatientInfo;

public class DBookingFragment extends Fragment {
    public List<PatientBook> mPatientBookList = new ArrayList<>();
    public RecyclerView mRecyclerView;
    public BookAdapter mAdapter;
    private PatientDataBase mPatientDataBase;
    private PatientBookDao mPatientBookDao;
    CalendarView mCalendarView;
    int day,month,hour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.d_booking_fragment,container,false);
        initView(view);
        return view;
    }

    public void initView(View view){
        mPatientDataBase = PatientDataBase.getDateInstance(getActivity());
        mPatientBookDao = mPatientDataBase.getDateDao();



        mRecyclerView = view.findViewById(R.id.bookRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        CreateList();

        mCalendarView = view.findViewById(R.id.calendarView);
        /*
        mAdapter = new BookAdapter(mPatientInfoList);
        mRecyclerView.setAdapter(mAdapter);
         */
    }

    public void CreateList(){
        mPatientBookList = mPatientBookDao.getDateList();
        mAdapter = new BookAdapter(mPatientBookList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private class BookHolder extends RecyclerView.ViewHolder{
        private TextView bookedName,bookedId,bookedHours,bookedCircum;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            bookedName = itemView.findViewById(R.id.bookedName);
            bookedId = itemView.findViewById(R.id.bookedId);
            bookedHours = itemView.findViewById(R.id.bookedTime);
        }

        public  void bind(PatientBook patientBook, int position){
            bookedName.setText(patientBook.getName());
            bookedId.setText(String.valueOf(patientBook.getId()));
            bookedHours.setText(String.valueOf(patientBook.getHours())+"时");
            /*
            bookedHours.setText(String.valueOf(patientInfo.getHours())+":00");
            if(patientInfo.getCircumstance() == 1)
                bookedCircum.setText("待确认");
            else if(patientInfo.getCircumstance() == 2) {
                bookedCircum.setText("已确认");
                bookedName.setText("*"+patientInfo.getName());
            }
             */
        }
    }

    private class BookAdapter extends  RecyclerView.Adapter<BookHolder>{
        private List<PatientBook> mPatientBookList;
        public BookAdapter(List<PatientBook> list){
            mPatientBookList = list;
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
            PatientBook patientBook = mPatientBookList.get(position);
            holder.bind(patientBook,position);
        }

        @Override
        public int getItemCount() {
            return mPatientBookList.size();
        }
    }
}
