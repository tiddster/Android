package Doctor;

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

public class DPersonalInfoFragment extends Fragment {
    TextView logout,doctorName,hospitalName;
    String hospital,name;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.d_personal_info_fragment,container,false);
        initView(view);
        Listener();
        return view;
    }

    public void initView(View view){
        SharedPreferences sp = getActivity().getSharedPreferences("GET", 0);
        hospital = sp.getString("HOSPITAL", null);
        name = sp.getString("DOCTORNAME", null);

        logout = view.findViewById(R.id.logout);
        doctorName = view.findViewById(R.id.info_name);
        hospitalName = view.findViewById(R.id.hospital);

        doctorName.setText(name);
        hospitalName.setText(hospital);
    }

    public void Listener(){
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
