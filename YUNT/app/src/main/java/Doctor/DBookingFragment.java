
package Doctor;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.yunt.R;

import java.util.ArrayList;
import java.util.Calendar;
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
    private static String name,hospital;
    Calendar mCalendar = Calendar.getInstance();
    CalendarView mCalendarView;
    int Day,Month,hour;

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

        SharedPreferences sp = getActivity().getSharedPreferences("GET", 0);
        hospital = sp.getString("HOSPITAL", null);
        name = sp.getString("DOCTORNAME", null);

        Month = mCalendar.get(Calendar.MONTH)+1;
        Day = mCalendar.get(Calendar.DATE);

        mRecyclerView = view.findViewById(R.id.bookRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        CreateList();

        mAdapter.setOnItemClick(new OnItemClick() {
            @Override
            public void onItemClick() {
            }
        });

        mCalendarView = view.findViewById(R.id.calendarView);
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Month = month + 1;
                Day = dayOfMonth;
                CreateList();
            }
        });
        /*
        mAdapter = new BookAdapter(mPatientInfoList);
        mRecyclerView.setAdapter(mAdapter);
         */
    }

    public void CreateList(){
        mPatientBookList = JudgeBookingList(Day,Month);
        mAdapter = new BookAdapter(mPatientBookList);
        mRecyclerView.setAdapter(mAdapter);
    }

    public List<PatientBook> JudgeBookingList(int day, int month){
        List<PatientBook> patientBookList = new ArrayList<>();
        Day = day;
        Month = month;
        for(PatientBook patientBook : mPatientBookDao.getDateList()){
            if(patientBook.getBook_month().equals(String.valueOf(Month)) && patientBook.getBook_day().equals(String.valueOf(Day)) && patientBook.getHospital().equals(hospital)){
                patientBookList.add(patientBook);
            }
        }
        return patientBookList;
    }

    private class BookHolder extends RecyclerView.ViewHolder{
        private TextView bookedName,bookedId,bookedHours;
        private ConstraintLayout bookItem;

        public BookHolder(@NonNull View itemView) {
            super(itemView);
            bookedName = itemView.findViewById(R.id.bookedName);
            bookedId = itemView.findViewById(R.id.bookedId);
            bookedHours = itemView.findViewById(R.id.bookedTime);
            bookItem = itemView.findViewById(R.id.book_item);
        }

        public  void bind(PatientBook patientBook, int position){
            bookedName.setText(patientBook.getName());
            bookedId.setText(String.valueOf(patientBook.getId()));
            bookedHours.setText(String.valueOf(patientBook.getHours())+"时");
            int Circum = patientBook.getCircumstance();
            if(Circum == 2){
                bookItem.setBackgroundResource(R.drawable.shape_gratient_item2);
                bookItem.setClickable(false);
            }
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

        private OnItemClick mOnItemClick;

        public void setOnItemClick(OnItemClick onItemClick){
            mOnItemClick = onItemClick;
        }

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
            holder.bookItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClick.onItemClick();
                    if(patientBook.getCircumstance() != 2) {
                        Intent intent = new Intent(getActivity(), DInsertNewBloodActivity.class);
                        intent.putExtra("ID", patientBook.getId());
                        intent.putExtra("NAME", patientBook.getName());
                        intent.putExtra("AGE", patientBook.getAge());
                        startActivity(intent);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mPatientBookList.size();
        }
    }

    private boolean isFirstLoading = true;
    @Override
    public void onResume() {
        super.onResume();
        if (!isFirstLoading) {
            CreateList();
        }
        isFirstLoading = false;
    }
}
