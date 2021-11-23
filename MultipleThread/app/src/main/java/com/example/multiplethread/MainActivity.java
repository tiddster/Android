package com.example.multiplethread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Lock lock = new ReentrantLock();
    public void test1(){
        lock.lock();
        try{
            System.out.println("run");
        } finally {
            lock.unlock();
        }
    }

    //这个方法等价与上面的方法（synchronized等价与上面一大坨）
    public synchronized void test2(){
        System.out.println("run");
    }

}