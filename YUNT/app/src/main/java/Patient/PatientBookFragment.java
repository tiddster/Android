package Patient;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.yunt.R;

public class PatientBookFragment extends Fragment {
    EditText editTime;
    ImageView bookingButton;
    boolean isBook = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_booking_fragment,container,false);
        initView(view);
        Listener();
        return view;
    }

    public void initView(View view){
        editTime = view.findViewById(R.id.booking_time);
        bookingButton = view.findViewById(R.id.booking_button);
    }

    public void Listener(){
        bookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(editTime.getText())) {
                    if (isBook) {
                        bookingButton.setImageResource(R.drawable.clock_foreground);
                    } else {
                        bookingButton.setImageResource(R.drawable.clockok_foreground);
                    }
                }
            }
        });
    }

}
