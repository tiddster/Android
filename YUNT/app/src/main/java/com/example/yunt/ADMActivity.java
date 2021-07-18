package com.example.yunt;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Update;

import org.w3c.dom.Text;

import java.util.List;

import Bean.DoctorAccount;
import Bean.DoctorAccountDao;
import Bean.PatientDataBase;
import Bean.PatientInfo;
import Bean.PatientInfoDao;

public class ADMActivity extends AppCompatActivity {
    int type = 0; //0:患者 1：医师
    PatientInfoDao mPatientInfoDao;
    DoctorAccountDao mDoctorAccountDao;
    PatientDataBase mPatientDataBase,nPatientDataBase;
    RecyclerView mRecyclerView;
    AdmAdapter mAdmAdapter;
    AdmDAdapter mAdmDAdapter;
    TextView mTextView,title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adm);

        initView();
        Listener();
    }

    public void initView(){
        mPatientDataBase = PatientDataBase.getBasicInstance(this);
        mPatientInfoDao = mPatientDataBase.getPatientInfoDao();
        nPatientDataBase = PatientDataBase.getDAInstance(this);
        mDoctorAccountDao = nPatientDataBase.getDADao();

        mTextView = findViewById(R.id.adm_exchange);
        title = findViewById(R.id.adm_title);
        mRecyclerView = findViewById(R.id.admRecycleView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UpdateUI();
    }

    public void UpdateUI(){
        if(type == 0) {
            mAdmAdapter = new AdmAdapter(mPatientInfoDao.getList());
            mRecyclerView.setAdapter(mAdmAdapter);
        } else {
            mAdmDAdapter = new AdmDAdapter(mDoctorAccountDao.getAllDoctorAccount());
            mRecyclerView.setAdapter(mAdmDAdapter);
        }
    }

    public void Listener(){
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type==0){
                    type = 1;
                    title.setText("医生列表");
                    UpdateUI();
                } else {
                    type = 0;
                    title.setText("医生列表");
                    UpdateUI();
                }
            }
        });
    }

    private class AdmHolder extends RecyclerView.ViewHolder{

        private TextView name,id,sex,age,blood;
        private ConstraintLayout item;

        public AdmHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.p_name);
            id = itemView.findViewById(R.id.p_id);
            sex = itemView.findViewById(R.id.p_sex);
            age = itemView.findViewById(R.id.p_age);
            blood = itemView.findViewById(R.id.p_blood);
            item = itemView.findViewById(R.id.d_patient_info_item);
        }

        public  void bind(PatientInfo patientInfo, int position){
            name.setText(patientInfo.getName());
            id.setText(String.valueOf(patientInfo.getNumber()));
            sex.setText(patientInfo.getSex());
            age.setText(String.valueOf(patientInfo.getAge()));
            blood.setText(patientInfo.getBlood());
        }
    }

    private class AdmAdapter extends RecyclerView.Adapter<AdmHolder>{
        private List<PatientInfo> mPatientInfoList;
        private AdmAdapter(List<PatientInfo> patientInfoList){
            mPatientInfoList = patientInfoList;
        }

        @NonNull
        @Override
        public AdmHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.d_patient_item, parent, false);
            return new AdmHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AdmHolder holder, int position) {
            PatientInfo patientInfo = mPatientInfoList.get(position);
            holder.bind(patientInfo,position);
            holder.item.setOnClickListener(new View.OnClickListener() {
                TextView delete;
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.adm_delete, null, false);

                    final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    //参数为1.View 2.宽度 3.高度
                    popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                    popupWindow.setFocusable(true);

                    delete = view.findViewById(R.id.delete_confirm);

                    backgroundAlpha(0.5f);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            //弹窗消失恢复原来的透明度
                            backgroundAlpha(1f);
                        }
                    });

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPatientInfoDao.DeletePatient(patientInfo);
                            UpdateUI();
                            popupWindow.dismiss();
                        }
                    });

                    popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, -100);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mPatientInfoList.size();
        }
    }

    private class AdmDHolder extends RecyclerView.ViewHolder{
        private TextView name,id,hospital;
        private ConstraintLayout item;
        public AdmDHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adm_d_name);
            id = itemView.findViewById(R.id.adm_id);
            hospital = itemView.findViewById(R.id.adm_hospital);
            item = itemView.findViewById(R.id.d_patient_info_item);
        }

        public void bind(DoctorAccount doctorAccount){
            name.setText(doctorAccount.getAccount());
            id.setText(String.valueOf(doctorAccount.getId()));
            hospital.setText(doctorAccount.getHospital());
        }
    }

    private class AdmDAdapter extends RecyclerView.Adapter<AdmDHolder>{
        private List<DoctorAccount> mDoctorAccounts;
        private AdmDAdapter(List<DoctorAccount> DoctorAccountList){
            mDoctorAccounts = DoctorAccountList;
        }
        @NonNull
        @Override
        public AdmDHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getApplicationContext());
            View view = inflater.inflate(R.layout.adm_d_item, parent, false);
            return new AdmDHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AdmDHolder holder, int position) {
            DoctorAccount doctorAccount = mDoctorAccounts.get(position);
            holder.bind(doctorAccount);
            holder.item.setOnClickListener(new View.OnClickListener() {
                TextView delete;
                @Override
                public void onClick(View v) {
                    View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.adm_delete, null, false);

                    final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    //参数为1.View 2.宽度 3.高度
                    popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
                    popupWindow.setFocusable(true);

                    delete = view.findViewById(R.id.delete_confirm);

                    backgroundAlpha(0.5f);
                    popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            //弹窗消失恢复原来的透明度
                            backgroundAlpha(1f);
                        }
                    });

                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mDoctorAccountDao.DeleteAccount(doctorAccount);
                            UpdateUI();
                            popupWindow.dismiss();
                        }
                    });

                    popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, -100);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDoctorAccounts.size();
        }
    }

    private void backgroundAlpha(float v) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = v; //0.0-1.0
        getWindow().setAttributes(lp);
    }
}
