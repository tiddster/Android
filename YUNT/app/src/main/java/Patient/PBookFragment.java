package Patient;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.room.Delete;

import com.example.yunt.R;

import Bean.PatientBook;
import Bean.PatientBookDao;
import Bean.PatientDataBase;

public class PBookFragment extends Fragment {
    EditText editTime,editMonth,editDay;
    ImageButton bookingButton;
    boolean isBook = false;
    public static int id;
    public static String name;
    private PatientBook mPatientBook;
    private PatientDataBase mPatientDataBase;
    private PatientBookDao mPatientBookDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_booking_fragment,container,false);
        initView(view);
        Listener();
        return view;
    }

    public void initView(View view){
        SharedPreferences sp = getActivity().getSharedPreferences("GET",0);
        id = sp.getInt("ID",0);
        name = sp.getString("NAME", null);

        Toast.makeText(getActivity(),"你好哇！ "+ name +" "+id,Toast.LENGTH_SHORT).show();

        mPatientDataBase = PatientDataBase.getDateInstance(getActivity());
        mPatientBookDao = mPatientDataBase.getDateDao();

        editTime = view.findViewById(R.id.booking_hours);
        bookingButton = view.findViewById(R.id.bookingButton);
        editDay = view.findViewById(R.id.booking_day);
        editMonth = view.findViewById(R.id.booking_month);

        for(PatientBook patientBook : mPatientBookDao.getDateList()){
            if(id == patientBook.getId() && patientBook.getCircumstance() == 1 && name.equals(patientBook.getName())){
                mPatientBook = patientBook;

                isBook = true;
                bookingButton.setImageResource(R.drawable.clockok_foreground);
                editMonth.setText(patientBook.book_month);
                editDay.setText(patientBook.book_day);
                editTime.setText(String.valueOf(patientBook.Hours));

                break;
            }
        }
    }

    public void Listener(){
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editTime.getText())&&!TextUtils.isEmpty(editMonth.getText())&&!TextUtils.isEmpty(editDay.getText())) {

                    int month = Integer.parseInt(editMonth.getText().toString());
                    int day = Integer.parseInt(editDay.getText().toString());
                    int hours = Integer.parseInt(editTime.getText().toString());

                    if(month>0&&month<13&&hours<25&&hours>=0&&day>0&&day<=31){
                            if (isBook) {
                                bookingButton.setImageResource(R.drawable.clock_foreground);
                                CancelBooking();
                                isBook = false;
                                Toast.makeText(getActivity(),"取消成功",Toast.LENGTH_SHORT).show();
                            } else {
                                bookingButton.setImageResource(R.drawable.clockok_foreground);
                                InsertNewBooking();
                                isBook = true;
                                Toast.makeText(getActivity(),"预约成功",Toast.LENGTH_SHORT).show();
                            }
                    } else {
                        Toast.makeText(getActivity(),"请输入正确时间",Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(),"您还未输入预约时间",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void InsertNewBooking(){
        mPatientBook = new PatientBook(name,id,editMonth.getText().toString(),editDay.getText().toString(),Integer.parseInt(editTime.getText().toString()),1);
        mPatientBookDao.InsertDate(mPatientBook);
    }

    public void CancelBooking(){
        mPatientBook.setCircumstance(3);
        mPatientBookDao.UpdateData(mPatientBook);
    }
}
