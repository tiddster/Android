package com.example.upermission;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button mButton;
     private static final int PERMISSIONS_REQUEST_CALL_PHONE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        Listener();
    }

    public void initView(){
        mButton= findViewById(R.id.hello);
    }

    public void Listener(){
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckPermission();
            }
        });
    }

    public void CheckPermission(){
        //检查是否有permission.call_phone的权限
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            //如果 没有就申请权限
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            call();
        }
    }

    public void call(){
        //  打电话的代码
        Intent intent =  new Intent(Intent.ACTION_CALL);
        Uri data  =  Uri.parse("tel:"+"10086");
        intent.setData(data);
        try{
            startActivity(intent);
        } catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void  onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if (requestCode == PERMISSIONS_REQUEST_CALL_PHONE) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                call();
            } else {
                Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions, grantResults);
    }
}