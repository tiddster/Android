package Patient;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.yunt.R;

public class PatientActivity extends AppCompatActivity {
    ImageView notes,clock,person;
    TextView pTitle;
    int type = 2;
    Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);

        initView();
        Listener();
    }
    //绑定
    public void initView(){
        notes = findViewById(R.id.p_patient_info);
        clock = findViewById(R.id.p_book);
        person = findViewById(R.id.p_info);
        pTitle = findViewById(R.id.ptitle);
        setFragment();
    }
    //监听
    public void Listener(){
        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pTitle.setText("透析记录");
                notes.setImageResource(R.drawable.notes2_foreground);
                clock.setImageResource(R.drawable.clock_foreground);
                person.setImageResource(R.drawable.person_foreground);
                type = 1;
                setFragment();
            }
        });

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pTitle.setText("预约提醒");
                notes.setImageResource(R.drawable.notes_foreground);
                clock.setImageResource(R.drawable.clock3_foreground);
                person.setImageResource(R.drawable.person_foreground);
                type = 2;
                setFragment();
            }
        });

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pTitle.setText("个人信息");
                notes.setImageResource(R.drawable.notes_foreground);
                clock.setImageResource(R.drawable.clock_foreground);
                person.setImageResource(R.drawable.person3_foreground);
                type = 3;
                setFragment();
            }
        });
    }

    public void setFragment(){
        PBookFragment pBookFragment = new PBookFragment();
        PNotesFragment pNotesFragment = new PNotesFragment();
        PPersonalInfoFragment pPersonalInfoFragment = new PPersonalInfoFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (type){
            case 1: mFragment = pNotesFragment; break;
            case 2: mFragment = pBookFragment; break;
            case 3: mFragment = pPersonalInfoFragment; break;
        }

        fragmentTransaction.replace(R.id.container2,mFragment);
        fragmentTransaction.commit();
    }

}
