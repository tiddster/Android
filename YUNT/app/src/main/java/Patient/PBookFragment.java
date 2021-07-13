package Patient;

import android.content.SharedPreferences;
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
import androidx.fragment.app.Fragment;
import androidx.room.Delete;

import com.example.yunt.R;

import java.util.Calendar;

import Bean.PatientBook;
import Bean.PatientBookDao;
import Bean.PatientDataBase;

public class PBookFragment extends Fragment {
    //EditText editTime,editMonth,editDay;
    ImageButton bookingButton;
    TextView bookRemainder;
    boolean isBook = false;
    public static int id;
    public static String name;
    public static int age;
    private PatientBook mPatientBook;
    private PatientDataBase mPatientDataBase;
    private PatientBookDao mPatientBookDao;
    Calendar mCalendar = Calendar.getInstance();
    int Month, Day, Hour;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_booking_fragment, container, false);
        initView(view);
        Listener();
        return view;
    }

    public void initView(View view) {
        SharedPreferences sp = getActivity().getSharedPreferences("GET", 0);
        id = sp.getInt("ID", 0);
        name = sp.getString("NAME", null);
        age = sp.getInt("AGE",0);

        mPatientDataBase = PatientDataBase.getDateInstance(getActivity());
        mPatientBookDao = mPatientDataBase.getDateDao();

        bookingButton = view.findViewById(R.id.bookingButton);
        bookRemainder = view.findViewById(R.id.bookReminder);

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

                    Month = mCalendar.get(Calendar.MONTH)+1;
                    Day = mCalendar.get(Calendar.DATE);
                    showMD.setText(Month + " 月 " + Day + " 日  ");

                    mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                        @Override
                        public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                            Month = month + 1;
                            Day = dayOfMonth;
                            showMD.setText(Month + " 月 " + Day + " 日  ");
                        }
                    });


                    ok.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!TextUtils.isEmpty(editHours.getText())) {
                                Hour = Integer.parseInt(editHours.getText().toString());
                                if (Hour >= 8 && Hour <= 14) {
                                    bookingButton.setImageResource(R.drawable.clockok_foreground);
                                    InsertNewBooking();
                                    Toast.makeText(getActivity(),"预约成功",Toast.LENGTH_SHORT).show();
                                    popupWindow.dismiss();
                                    bookRemainder.setText("您的预约时间为:" + Month + "月" + Day + "日" + Hour + "时");
                                    isBook = true;
                                } else {
                                    Toast.makeText(getActivity(), "因透析时间较长，请选择8点至14点的时间", Toast.LENGTH_SHORT).show();
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

                /*
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

                 */

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
}
