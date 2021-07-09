package Doctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.yunt.LoginActivity;
import com.example.yunt.R;

public class DPersonalInfoFragment extends Fragment {
    TextView logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.d_personal_info_fragment,container,false);
        initView(view);
        Listener();
        return view;
    }

    public void initView(View view){
        logout = view.findViewById(R.id.logout);
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
