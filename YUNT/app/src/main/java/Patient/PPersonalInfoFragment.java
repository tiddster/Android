package Patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.yunt.LoginActivity;
import com.example.yunt.R;

import Bean.PatientDataBase;
import Bean.PatientInfo;
import Bean.PatientInfoDao;

public class PPersonalInfoFragment extends Fragment {
    private static int id;
    TextView logout,nameText,hospitalText;
    private static String name,hospital;
    ConstraintLayout PasswordResetItem;
    private PatientDataBase mPatientDataBase;
    private PatientInfoDao mPatientInfoDao;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.p_personal_info_fragment,container,false);
        interview(view);
        listener();
        return view;
    }

    public void interview(View view){
        mPatientDataBase = PatientDataBase.getBasicInstance(getActivity());
        mPatientInfoDao = mPatientDataBase.getPatientInfoDao();

        SharedPreferences sp = getActivity().getSharedPreferences("GET", 0);
        id = sp.getInt("ID", 0);
        hospital = sp.getString("HOSPITAL", null);
        name = sp.getString("NAME", null);

        logout = view.findViewById(R.id.p_logout);
        nameText = view.findViewById(R.id.p_info_patient_name);
        hospitalText = view.findViewById(R.id.p_info_hospital_name);
        PasswordResetItem = view.findViewById(R.id.password_reset);

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

        PasswordResetItem.setOnClickListener(new View.OnClickListener() {
            EditText editPassword;
            TextView confirm;
            @Override
            public void onClick(View v) {
                View view = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.p_reset_password_popupwindow, null, false);

                final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                //参数为1.View 2.宽度 3.高度
                popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                popupWindow.setFocusable(true);

                editPassword = view.findViewById(R.id.editResetPassword);
                confirm = view.findViewById(R.id.passwordResetConfirm);

                backgroundAlpha(0.5f);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        //弹窗消失恢复原来的透明度
                        backgroundAlpha(1f);
                    }
                });

                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        PatientInfo patientInfo = mPatientInfoDao.getById(id);
                        if(!TextUtils.isEmpty(editPassword.getText())){
                            patientInfo.setPassword(editPassword.getText().toString());
                            mPatientInfoDao.UpdatePatient(patientInfo);
                            Toast.makeText(getActivity(),"修改成功",Toast.LENGTH_SHORT).show();
                            popupWindow.dismiss();
                        } else {
                            Toast.makeText(getActivity(),"请输入密码",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.CENTER, 0, -100);
            }
        });
    }

    private void backgroundAlpha(float v) {
        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
        lp.alpha = v; //0.0-1.0
        getActivity().getWindow().setAttributes(lp);
    }

}
