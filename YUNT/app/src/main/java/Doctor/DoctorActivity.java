package Doctor;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yunt.R;

public class DoctorActivity extends AppCompatActivity {
    ImageView alarm, patient, person;
    TextView Dtitle;
    int type = 2;
    Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor);

        initView();
        Listener();
    }

    //绑定
    public void initView(){
        alarm = findViewById(R.id.booking);
        patient = findViewById(R.id.d_patient_info);
        person = findViewById(R.id.d_self_info);
        Dtitle = findViewById(R.id.Dtitle);
        setFragment();
    }

    //设置监听
    public void Listener(){
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dtitle.setText("预约日历");
                alarm.setImageResource(R.drawable.clock2_foreground);
                patient.setImageResource(R.drawable.patient_foreground);
                person.setImageResource(R.drawable.person_foreground);
                type = 2;
                setFragment();
            }
        });

        patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dtitle.setText("患者信息");
                alarm.setImageResource(R.drawable.clock_foreground);
                patient.setImageResource(R.drawable.patient2_foreground);
                person.setImageResource(R.drawable.person_foreground);
                type = 1;
                setFragment();
            }
        });

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dtitle.setText("个人中心");
                alarm.setImageResource(R.drawable.clock_foreground);
                patient.setImageResource(R.drawable.patient_foreground);
                person.setImageResource(R.drawable.person2_foreground);
                type = 3;
                setFragment();
            }
        });
    }

    public void setFragment(){
        DBookingFragment dBookingFragment = new DBookingFragment();
        DInfoFragment dInfoFragment = new DInfoFragment();
        DPersonalInfoFragment dPersonalInfoFragment = new DPersonalInfoFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (type){
            case 1: mFragment = dInfoFragment; break;
            case 2: mFragment = dBookingFragment; break;
            case 3: mFragment = dPersonalInfoFragment; break;
        }

        fragmentTransaction.replace(R.id.container,mFragment);
        fragmentTransaction.commit();
    }
}
