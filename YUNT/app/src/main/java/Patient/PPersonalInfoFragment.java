package Patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.yunt.LoginActivity;
import com.example.yunt.R;

public class PPersonalInfoFragment extends Fragment {
    TextView logout,nameText,hospitalText;
    String name,hospital;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_personal_info_fragment,container,false);
        interview(view);
        listener();
        return view;
    }

    public void interview(View view){
        SharedPreferences sp = getActivity().getSharedPreferences("GET", 0);
        hospital = sp.getString("HOSPITAL", null);
        name = sp.getString("NAME", null);

        logout = view.findViewById(R.id.p_logout);
        nameText = view.findViewById(R.id.p_info_patient_name);
        hospitalText = view.findViewById(R.id.p_info_hospital_name);

        nameText.setText(name);
        hospitalText.setText(hospital);
    }

    public void listener(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

}
