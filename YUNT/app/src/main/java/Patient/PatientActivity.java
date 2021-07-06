package Patient;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yunt.R;

public class PatientActivity extends AppCompatActivity {
    ImageView notes,alarm,person;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient);

        initView();
        Listener();
    }

    public void initView(){
        alarm = findViewById(R.id.p_book);
        notes = findViewById(R.id.p_patient_info);
        person = findViewById(R.id.p_info);
    }

    public void Listener(){
        alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.setImageResource(R.drawable.clock3_foreground);
                notes.setImageResource(R.drawable.notes_foreground);
                person.setImageResource(R.drawable.person_foreground);
            }
        });

        notes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.setImageResource(R.drawable.clock_foreground);
                notes.setImageResource(R.drawable.notes2_foreground);
                person.setImageResource(R.drawable.person_foreground);
            }
        });

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarm.setImageResource(R.drawable.clock_foreground);
                notes.setImageResource(R.drawable.notes_foreground);
                person.setImageResource(R.drawable.person3_foreground);
            }
        });
    }
}
