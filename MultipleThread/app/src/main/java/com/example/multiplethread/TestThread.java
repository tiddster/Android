package com.example.multiplethread;

public class TestThread extends Thread{
    public void run(){
        System.out.println("hello world");
    }

    public static void main(String[] arg){
        Thread mThread = new TestThread();
        mThread.start();
    }
}
