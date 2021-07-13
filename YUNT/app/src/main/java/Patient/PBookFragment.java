package Patient;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.room.Delete;

import com.example.yunt.R;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import Bean.PatientBlood;
import Bean.PatientBloodDao;
import Bean.PatientBook;
import Bean.PatientBookDao;
import Bean.PatientDataBase;

public class PBookFragment extends Fragment {
    //EditText editTime,editMonth,editDay;
    ImageButton bookingButton;
    TextView bookRemainder,interval_time;
    boolean isBook = false, isTouXi = false;
    public static int id;
    public static String name;
    public static int age;
    public static int sYear,sMonth,sDay;
    private PatientBook mPatientBook;
    private PatientBlood mPatientBlood;
    private PatientDataBase mPatientDataBase;
    private PatientDataBase nPatientDataBase;
    private PatientBookDao mPatientBookDao;
    private PatientBloodDao mPatientBloodDao;

    Calendar mCalendar = Calendar.getInstance();
    int Month, Day, Hour, Year, interval = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_booking_fragment, container, false);
        initView(view);
        Listener();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initView(View view) {
        Month = mCalendar.get(Calendar.MONTH)+1;
        Day = mCalendar.get(Calendar.DATE);
        Year = mCalendar.get(Calendar.YEAR);

        SharedPreferences sp = getActivity().getSharedPreferences("GET", 0);
        id = sp.getInt("ID", 0);
        name = sp.getString("NAME", null);
        age = sp.getInt("AGE",0);

        mPatientDataBase = PatientDataBase.getDateInstance(getActivity());
        nPatientDataBase = PatientDataBase.getBLOODInstance(getActivity());
        mPatientBookDao = mPatientDataBase.getDateDao();
        mPatientBloodDao = nPatientDataBase.getPatientBloodDao();

        bookingButton = view.findViewById(R.id.bookingButton);
        bookRemainder = view.findViewById(R.id.bookReminder);
        interval_time = view.findViewById(R.id.interval_time);

        for(PatientBlood patientBlood : mPatientBloodDao.getAllBlood()){
            if(patientBlood.getId() == id){
                sMonth = patientBlood.getNext_month();
                sDay = patientBlood.getNext_day();
                LocalDate oldDate = LocalDate.of(Year, Month, Day);
                LocalDate nextData = LocalDate.of(Year,sMonth,sDay);
                Period p = Period.between(oldDate, nextData);
                interval = p.getDays();
                isTouXi = true;
                break;
            }
        }

        if(isTouXi){
            if(interval>0)
                interval_time.setText("距离下次透析还剩"+interval+"天");
            else
                interval_time.setText("今天需要透析，请及时预约");
        }


        /*
        editTime = view.findViewById(R.id.booking_hours);
        bookingButton = view.findViewById(R.id.bookingButton);
        editDay = view.findViewById(R.id.booking_day);
        editMonth = view.findViewById(R.id.booking_month);
         */

        for (PatientBook patientBook : mPatientBookDao.getDateList()) {
            if (id == patientBook.getId() && patientBook.getCircumstance() == 1 && name.equals(patientBook.getName())) {
                mPatientBook = patientBook;

                isBook = true;
                bookingButton.setImageResource(R.drawable.clockok_foreground);
                bookRemainder.setText("您的预约时间为:" + patientBook.getBook_month() + "月" + patientBook.getBook_day() + "日" + patientBook.getHours() + "时");
                /*
                editMonth.setText(patientBook.book_month);
                editDay.setText(patientBook.book_day);
                editTime.setText(String.valueOf(patientBook.Hours));
                 */

                break;
            }
        }
    }

    public void Listener() {
        bookingButton.setOnClickListener(new View.OnClickListener() {
            TextView ok, no, showMD;
            CalendarView mCalendarView;
            EditText editHours;

            @Override
            public void onClick(View v) {
                if (!isBook) {
                    View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.p_time_picker, null, false);

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

                    ok = view.findViewById(R.id.ok);
                    no = view.findViewById(R.id.cancel);
                    showMD = view.findViewById(R.id.showMD);
                    mCalendarView = view.findViewById(R.id.calendarView2);
                    editHours = view.findViewById(R.id.editHours);

                    showMD.setText(Month + " 月 " + Day + " 日  ");

                    mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                            if(interval==0){
                                Toast.makeText(getActivity(),"今天是预约时间，无法更改日期",Toast.LENGTH_SHORT).show();
                            } else {
                                Month = month + 1;
                                Day = dayOfMonth;
                                showMD.setText(Month + " 月 " + Day + " 日  ");
                            }
                        }
                    });


                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!TextUtils.isEmpty(editHours.getText())) {
                                Hour = Integer.parseInt(editHours.getText().toString());
                                if(Day == mCalendar.get(Calendar.DAY_OF_MONTH)){
                                    if(Hour > mCalendar.get(Calendar.HOUR_OF_DAY)){
                                        Book(popupWindow);
                                    } else {
                                        Toast.makeText(getActivity(),"今日此时段已过，请重新选择",Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    if(Hour >= 8 && Hour <=16){
                                        Book(popupWindow);
                                    } else {
                                        Toast.makeText(getActivity(),"因透析时间过长，请选择8点至16点的时间",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            } else {
                                Toast.makeText(getActivity(), "请输入正确时间", Toast.LENGTH_SHORT);
                            }
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popupWindow.dismiss();
                        }
                    });

                    popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, -100);

                } else {
                    bookingButton.setImageResource(R.drawable.clock_foreground);
                    CancelBooking();
                    isBook = false;
                    bookRemainder.setText("您目前未有预约申请");
                    Toast.makeText(getActivity(),"取消成功",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void backgroundAlpha(float v) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = v; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

    public void InsertNewBooking() {
        mPatientBook = new PatientBook(name, id, String.valueOf(Month), String.valueOf(Day), Hour, 1,age);
        mPatientBookDao.InsertDate(mPatientBook);
    }

    public void CancelBooking() {
        mPatientBookDao.DeleteDate(mPatientBook);
    }

    public void Book(PopupWindow popupWindow){
        bookingButton.setImageResource(R.drawable.clockok_foreground);
        InsertNewBooking();
        Toast.makeText(getActivity(),"预约成功",Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
        bookRemainder.setText("您的预约时间为:" + Month + "月" + Day + "日" + Hour + "时");
        isBook = true;
    }
}
